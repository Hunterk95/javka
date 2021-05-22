package scanner;

public class main {
    public static void main(String[] args) {
        CommandLineArgParser parser = new CommandLineArgParser(args);
        parser.parse();

        try {
            TcpScanner scanner = new TcpScanner(parser.getHosts(), parser.getPorts());
            scanner.scan(parser.getNumOfThreads());
            System.out.println(scanner);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
