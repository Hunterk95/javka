package scanner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Collections;
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
            ArrayList preQueue = new ArrayList<Runnable>(hosts.size() * ports.size());
            for (InetAddress host : hosts) {
                for (int port : ports) {
                    preQueue.add(new ThreadConnect(host, port, connectionsResults));
                }
            }
            Collections.shuffle(preQueue);
            queue.addAll(preQueue);
        } catch (Exception e) {

        }

        ThreadPoolExecutor executor = new ThreadPoolExecutor(numOfTreads, numOfTreads, 100, TimeUnit.MILLISECONDS, queue);
        executor.prestartAllCoreThreads();
        while (executor.getTaskCount() != executor.getCompletedTaskCount()) {
        }
        executor.shutdown();
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
            result.append(connect).append("\n");
        }
        return result.toString();
    }

    public void saveJson(String fileName){
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();
        try (FileOutputStream file = new FileOutputStream(fileName)){
            writer.writeValue(file, connectionsResults);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
