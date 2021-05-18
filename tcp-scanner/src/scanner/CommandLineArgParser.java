package scanner;

/**
 * Class for parse command line arguments
 */
public class CommandLineArgParser {

    //private String filePath;

    public CommandLineArgParser(String[] argv) {
        try {
            if (argv[0].compareTo("scan") != 0) {
                throw new IllegalArgumentException("Not found command: scan");
            }
            if (argv[1].compareTo("-h") != 0) {
                throw new IllegalArgumentException("Not found key: -h");
            }
            if (argv[3].compareTo("-p") != 0) {
                throw new IllegalArgumentException("Not found key: -p");
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Arguments error");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}

