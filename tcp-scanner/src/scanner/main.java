package scanner;

public class main {
    public static void main(String[] args) {
        CommandLineArgParser parser = new CommandLineArgParser(args);
        parser.parse();

        try {
            TcpScanner scanner = new TcpScanner(parser.getAddresses());
            scanner.scan(parser.getNumOfThreads());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
