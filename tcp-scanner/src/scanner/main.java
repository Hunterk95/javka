package scanner;

import java.util.ArrayList;

public class main {
    public static void main(String[] args) {
        CommandLineArgParser parser = new CommandLineArgParser(args);
        parser.parse();

        try {
            TcpScanner scanner = new TcpScanner(parser.getAddresses());
            scanner.scan(parser.getNumOfThreads());

            for (Address address : parser.getAddresses().getAllUnits()) {
                ArrayList<Integer> ports = (ArrayList<Integer>) address.getAllPorts();
                ArrayList<Boolean> connectionsResult = (ArrayList<Boolean>) address.getAllConnectionsResult();

                for (int i = 0; i < address.getPortsNum(); i++) {
                    System.out.println(address.getAddress() + ":" + ports.get(i) + " connection " +
                            (connectionsResult.get(i) ? "successful" : "unsuccessful"));
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
