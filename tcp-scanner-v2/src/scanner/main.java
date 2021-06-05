package scanner;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class main {

    private static final Logger logger = LogManager.getLogger(CommandLineArgParser.class.getName());
    static {
        org.apache.log4j.PropertyConfigurator.configure("src/scanner/log4j2-test.properties");
        logger.info("=================");
        logger.info("TCP-SCANNER START");
    }

    public static void main(String[] args) {
        CommandLineArgParser parser = new CommandLineArgParser(args);
        parser.parse();

        TcpScanner scanner = new TcpScanner(parser.getHosts(), parser.getPorts());
        scanner.scan(parser.getNumOfThreads());

        scanner.saveJson("scan-result.json");
        //System.out.println(scanner);
        logger.info("TCP-SCANNER STOP");
    }
}
