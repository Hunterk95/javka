package scanner;

import java.net.InetAddress;

public class Connect {
    private InetAddress host;
    private int port;
    private Boolean successOfConnection;

    public Connect(InetAddress host, int port, Boolean successOfConnection) {
        this.host = host;
        this.port = port;
        this.successOfConnection = successOfConnection;
    }

    @Override
    public String toString() {
        return new StringBuilder().append(host).append(":").append(port).append(" connection ")
                .append(successOfConnection ? "successful" : "unsuccessful").toString();
    }
}
