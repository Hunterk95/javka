package scanner;

import java.io.IOException;
import java.net.*;

public class TcpScanner {
    AddressesToConnect addresses;
    Address address;

    public TcpScanner(AddressesToConnect addresses) {
        this.addresses = addresses;
    }

    public void scan(int numOfTreads) {
        System.out.println(connect(addresses.getNext()));
    }

    public boolean connect(Address address) {
        Socket client = new Socket();
        try {
            client.connect(new InetSocketAddress(address.getAddress(), address.getPort()), 100);
            System.out.println("Address: " + address.getAddress().toString());
            System.out.println("Port: " + address.getPort());
        } catch (IOException e) {
            //e.printStackTrace();
        }
        return client.isConnected();
    }
}
