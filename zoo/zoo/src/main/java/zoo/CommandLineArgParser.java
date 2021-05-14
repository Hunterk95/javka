package zoo;

public class CommandLineArgParser {
    private  ConfigType configType = ConfigType.json;
    private  String filePath;

    public CommandLineArgParser(String[] argv, Zoo zoo) {
        for (String arg : argv) {
            if (arg.contains("-configtype=")) {
                switch (arg.split("-configtype=")[1]) {
                    case "xml":
                        configType = ConfigType.xml;
                        break;
                    case "json":
                        configType = ConfigType.json;
                        break;
                    default:
                        throw new Error("What the type?");
                }
                continue;
            }
            if (arg.contains("-configfile=")) {
                filePath = arg.split("-configfile=")[1];
                continue;
            }
            filePath = arg;
        }

        if(configType == ConfigType.json){
            zoo.addAnimals(filePath);
        } else if(configType == ConfigType.xml){
            zoo.addAnimalsXml(filePath);
        }
    }

    public ConfigType getConfigType() {
        return configType;
    }

    public String getFilePath() {
        return filePath;
    }
}

