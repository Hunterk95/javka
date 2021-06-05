package scanner;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.List;

public class ThreadConnect implements Runnable {
    private static final Logger logger = LogManager.getLogger(CommandLineArgParser.class.getName());

    final private InetAddress host;
    final private int port;
    private List<Connect> connectionsResults;

    public ThreadConnect(InetAddress host, int port, List<Connect> connectionsResults) {
        this.host = host;
        this.port = port;
        this.connectionsResults = connectionsResults;
    }

    @Override
    public void run() {
        connect(host, port);
    }

    public void connect(InetAddress host, int port) {
        try (Socket client = new Socket()) {
            client.connect(new InetSocketAddress(host, port), 10);
            connectionsResults.add(new Connect(host, port, client.isConnected()));
        } catch (SocketTimeoutException e) {
            connectionsResults.add(new Connect(host, port, false));
        } catch (IOException e){
            connectionsResults.add(new Connect(host, port, false));
            logger.error(e.getMessage(), e);
        }
    }
}
