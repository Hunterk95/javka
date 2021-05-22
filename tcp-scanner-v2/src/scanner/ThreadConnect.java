package scanner;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.List;

public class ThreadConnect implements Runnable {
    InetAddress host;
    int port;
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
            synchronized (connectionsResults) {
                connectionsResults.add(new Connect(host, port, client.isConnected()));
            }
        } catch (IOException e) {
            //e.printStackTrace();
            synchronized (connectionsResults) {
                connectionsResults.add(new Connect(host, port, false));
            }
        }
    }
}
