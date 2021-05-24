package zoo;

/**
 * Class for parse command line arguments
 */
public class CommandLineArgParser {
    private String[] argv;
    private ConfigType configType = ConfigType.JSON;
    private String filePath;

    /**
     * Create parser
     *
     * @param argv command line arguments
     */
    public CommandLineArgParser(String[] argv) {
        this.argv = argv;
    }

    /**
     * Method to parse command line arguments by key=value
     * and create Zoo by command line arguments
     * @return Zoo with animals
     */
    public Zoo parse() {
        for (String arg : argv) {
            if (arg.contains("-configtype=")) {
                if (arg.split("-configtype=").length < 2) {
                    throw (new IllegalArgumentException("After key -configtype= must be a value"));
                }
                switch (arg.split("-configtype=")[1]) {
                    case "XML":
                        configType = ConfigType.XML;
                        break;
                    case "JSON":
                        configType = ConfigType.JSON;
                        break;
                    case "DATABASE":
                        configType = ConfigType.DATABASE;
                        break;
                    default:
                        throw new Error("No such configtype: " + arg.split("-configtype=")[1]);
                }
                continue;
            }
            if (arg.contains("-configfile=")) {
                if (arg.split("-configfile=").length < 2) {
                    throw (new IllegalArgumentException("After key -configfile= must be a value"));
                }
                filePath = arg.split("-configfile=")[1] + ' ';
                continue;
            }
            filePath = arg + ' ';
        }
        filePath = filePath.trim();
        return createZoo();
    }

    /**
     * Method to create Zoo and add animals
     * @return Zoo with animals
     */
    private Zoo createZoo() {
        Zoo zoo = new Zoo();

        switch (configType) {
            case XML:
                zoo.addAnimalsXml(filePath);
                break;
            case JSON:
                zoo.addAnimalsJson(filePath);
                break;
            case DATABASE:
                zoo.addAnimalsDatabase(filePath);
                break;
            default:
                throw (new IllegalStateException("configType error"));
        }
        return zoo;
    }
}

