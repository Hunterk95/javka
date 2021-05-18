package scanner;

import java.io.IOException;
import java.net.*;

public class TcpScanner {
    Address address;

    public boolean connect(Address address) {
        Socket client = null;

        try {
            client = new Socket(address.getAddress(), address.getPort());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(client == null) return false;
        return client.isConnected();
    }
}
