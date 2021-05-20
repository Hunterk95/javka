package scanner;

import java.io.IOException;
import java.net.*;

public class TcpScanner {
    AddressesToConnect addresses;

    public TcpScanner(AddressesToConnect addresses) {
        this.addresses = addresses;
    }

    public void scan(int numOfTreads) {
        for (Address nextAddress = addresses.getNext(); nextAddress != null; nextAddress = addresses.getNext()) {
            nextAddress.setSuccessConnect(connect(nextAddress));
        }
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
}
