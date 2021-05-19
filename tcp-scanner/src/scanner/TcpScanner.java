package scanner;

import java.io.IOException;
import java.net.*;

public class TcpScanner {
    AddressesToConnect addresses;
    Address address;

    public TcpScanner(AddressesToConnect addresses){
        this.addresses = addresses;
    }

    public void scan(int numOfTreads){
        System.out.println(connect(addresses.getNext()));
    }

    public boolean connect(Address address) {
        Socket client = null;

        try {
            client = new Socket(address.getAddress(), address.getPort());
        } catch (IOException e) {
            //e.printStackTrace();
        }
        if(client == null) return false;
        return client.isConnected();
    }
}
