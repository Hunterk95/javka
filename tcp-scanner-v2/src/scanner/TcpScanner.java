package scanner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

public class TcpScanner {

    private static final Logger logger = LogManager.getLogger(CommandLineArgParser.class.getName());

    private final List<InetAddress> hosts;
    private final List<Integer> ports;
    private final List<Connect> connectionsResults = new ArrayList<>();

    public TcpScanner(List<InetAddress> hosts, List<Integer> ports) {
        logger.info("Create scanner to " + hosts.size() + " hosts and " + ports.size() + " ports" );
        this.hosts = hosts;
        this.ports = ports;
    }

    public void scan(int numOfThreads) {
        logger.debug("prepare to scan in " + numOfThreads + " threads");
        LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();
        try {
            ArrayList<Runnable> preQueue = new ArrayList<>(hosts.size() * ports.size());
            for (InetAddress host : hosts) {
                for (int port : ports) {
                    preQueue.add(new ThreadConnect(host, port, connectionsResults));
                }
            }
            logger.debug("prepare to scan with " + preQueue.size() + " connection");
            Collections.shuffle(preQueue);
            queue.addAll(preQueue);
        } catch (Exception e) {
            logger.error("scan error: ", e);
        }

        ThreadPoolExecutor executor = new ThreadPoolExecutor(numOfThreads, numOfThreads, 100, TimeUnit.MILLISECONDS, queue);
        executor.prestartAllCoreThreads();
        logger.debug("start scan");
        while (executor.getTaskCount() != executor.getCompletedTaskCount()) {
        }
        executor.shutdown();
        try {
            executor.awaitTermination(200, TimeUnit.MILLISECONDS);
            logger.debug("end scan");
            logger.info("scanned " + connectionsResults.size() + " connections");
        } catch (InterruptedException e) {
            logger.error("scan error: ", e);
        }
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

    public void saveJson(String fileName) {
        logger.debug("save JSON results to file " + fileName);
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();
        try (FileOutputStream file = new FileOutputStream(fileName)) {
            synchronized (connectionsResults) {
                writer.writeValue(file, connectionsResults);
            }
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage(), e);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }
}
