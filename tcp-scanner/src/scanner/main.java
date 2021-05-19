package scanner;

import java.net.InetAddress;
import java.util.ArrayList;

public class main {
    public static void main(String[] args) {
        CommandLineArgParser parse = new CommandLineArgParser(args);
        ArrayList<Address> address = new ArrayList<>();
        ArrayList<Integer> ports = new ArrayList<>();
        AddressesToConnect addresses;
        ports.add(1);
        try{
            address.add(new Address(InetAddress.getByName("192.168.2.1"), ports));
            addresses = new AddressesToConnect(address);
            TcpScanner scanner = new TcpScanner(addresses);
            scanner.scan(1);

        } catch (Exception e){
            System.out.println(e.getMessage());
        }


    }
}
