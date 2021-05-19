package scanner;

import java.util.List;

/**
 * Class for parse command line arguments
 */
public class CommandLineArgParser {
    final private String[] argv;
    private List<Integer> ports;
    private List<Address> addresses;

    public CommandLineArgParser(String[] argv) {
        this.argv = argv;
    }

    //private String filePath;

    public void Parse() {
        try {
            if (argv[0].compareTo("scan") != 0) {
                throw new IllegalArgumentException("Not found command: scan");
            }
            for (int i = 1; i < argv.length; i++) {
                switch (argv[i]) {
                    case ("-h"):
                        if (argv[i + 1] == null) {
                            throw new IllegalArgumentException("Not found any arguments after key: -h");
                        }
                        if (argv[i + 1].matches(",")) {

                        } else if (argv[i + 1].matches("-")) {

                        } else {

                        }
                }
            }

            if (argv[3].compareTo("-p") != 0) {
                throw new IllegalArgumentException("Not found key: -p");
            }
            if (argv[4] == null) {
                throw new IllegalArgumentException("Not found any arguments after key: -p");
            } else {

            }
            if (argv[5].compareTo("-t") == 0) {
                if (argv[6] == null) {
                    throw new IllegalArgumentException("Not found any arguments after key: -t");
                }
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

