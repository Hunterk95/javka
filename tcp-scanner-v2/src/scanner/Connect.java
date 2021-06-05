package scanner;

import java.net.InetAddress;

public class Connect {
    final private InetAddress host;
    final private int port;
    final private Boolean successOfConnection;

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

    public InetAddress getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public Boolean getSuccessOfConnection() {
        return successOfConnection;
    }
}
