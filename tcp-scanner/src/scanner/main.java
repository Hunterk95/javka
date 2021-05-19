package scanner;

import java.net.InetAddress;
import java.util.ArrayList;

public class main {
    public static void main(String[] args) {
        CommandLineArgParser parser = new CommandLineArgParser(args);
        parser.parse();
        try {

            TcpScanner scanner = new TcpScanner(parser.getAddresses());
            scanner.scan(1);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }
}
