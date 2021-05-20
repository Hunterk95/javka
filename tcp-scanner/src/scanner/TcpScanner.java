package scanner;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;

public class TcpScanner {
    AddressesToConnect addresses;

    public TcpScanner(AddressesToConnect addresses) {
        this.addresses = addresses;
    }

    public void scan(int numOfTreads) {
        for (Address nextAddress = addresses.getNext(); nextAddress != null; nextAddress = addresses.getNext()) {
            nextAddress.setSuccessConnect(connect(nextAddress));
        }
        printScanResult();
    }

    public boolean connect(Address address) {
        Socket client = new Socket();
        try {
            client.connect(new InetSocketAddress(address.getAddress(), address.getPort()), 10);
        } catch (IOException e) {
            //e.printStackTrace();
        }
        return client.isConnected();
    }

    public void printScanResult(){
        for (Address address : addresses.getAllUnits()) {
            ArrayList<Integer> ports = (ArrayList<Integer>) address.getAllPorts();
            ArrayList<Boolean> connectionsResult = (ArrayList<Boolean>) address.getAllConnectionsResult();

            for (int i = 0; i < address.getPortsNum(); i++) {
                System.out.println(address.getAddress() + ":" + ports.get(i) + " connection " +
                        (connectionsResult.get(i) ? "successful" : "unsuccessful"));
            }
        }
    }

    public void saveScanResult(){
        for (Address address : addresses.getAllUnits()) {
            ArrayList<Integer> ports = (ArrayList<Integer>) address.getAllPorts();
            ArrayList<Boolean> connectionsResult = (ArrayList<Boolean>) address.getAllConnectionsResult();

            for (int i = 0; i < address.getPortsNum(); i++) {
                System.out.println(address.getAddress() + ":" + ports.get(i) + " connection " +
                        (connectionsResult.get(i) ? "successful" : "unsuccessful"));
            }
        }
    }
}
