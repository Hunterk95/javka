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

    public Boolean prepareNext() {
        nextPort = ports.getNext();
        if (nextPort == null) {
            return false;
        }
        return true;
    }

    public InetAddress getAddress() {
        return address;
    }

    public Integer getPort() {
        return nextPort;
    }

    public int getPortsNum() {
        return ports.getUnitsNum();
    }

    public void setSuccessConnect(boolean success) {
        ports.setSuccessConnect(success);
    }

    public List<Boolean> getAllConnectionsResult() {
        return ports.getAllConnectionsResult();
    }

    public List<Integer> getAllPorts() {
        return ports.getAllUnits();
    }
}
