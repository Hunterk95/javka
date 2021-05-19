package scanner;

import java.net.InetAddress;
import java.util.List;

public class Address {
    private PortsToConnect ports;
    private Integer nextPort = 0;
    private InetAddress address;

    public Address(InetAddress address, List<Integer> ports) {
        this.address = address;
        this.ports = new PortsToConnect(ports);
    }

    public InetAddress getAddress() {
        nextPort = ports.getNext();
        if (nextPort == null) {
            return null;
        }
        return address;
    }

    public Integer getPort() {
        return nextPort;
    }
}
