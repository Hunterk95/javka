package scanner;

import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class TcpScanner {
    private List<InetAddress> hosts;
    private List<Integer> ports;
    private List<Connect> connectionsResults = new ArrayList<>();

    public TcpScanner(List<InetAddress> hosts, List<Integer> ports) {
        this.hosts = hosts;
        this.ports = ports;
    }

    public void scan(int numOfTreads) {
        LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();
        try {
            for (InetAddress host : hosts) {
                for (int port : ports) {
                    queue.put(new ThreadConnect(host, port, connectionsResults));
                }
            }
        } catch (Exception e) {

        }
        ThreadPoolExecutor executor = new ThreadPoolExecutor(numOfTreads, numOfTreads, 100, TimeUnit.MILLISECONDS, queue);
        executor.prestartAllCoreThreads();
        while (executor.getTaskCount() != executor.getCompletedTaskCount()) {

        }
        executor.shutdown();
        printScanResult();
    }

    public void printScanResult() {
        for (Connect connect : connectionsResults) {
            System.out.println(connect);
        }
    }

    public List<Connect> getConnectionsResults() {
        return connectionsResults;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Connect connect : connectionsResults) {
            result.append(connect).append("/n");
        }
        return result.toString();
    }
}
