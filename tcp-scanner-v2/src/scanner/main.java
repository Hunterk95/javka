package scanner;

public class main {
    public static void main(String[] args) {
        CommandLineArgParser parser = new CommandLineArgParser(args);
        parser.parse();


        TcpScanner scanner = new TcpScanner(parser.getHosts(), parser.getPorts());
        scanner.scan(parser.getNumOfThreads());

        scanner.saveJson("scan-result.json");
        //System.out.println(scanner);

    }
}
