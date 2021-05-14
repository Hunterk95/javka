package zoo;

public class Main {

    public static void main(String[] argv) {
        String filePath = new String();
        boolean jsonFile = false;
        boolean xmlFile  = false;

        for (String arg:argv){
            switch (arg){
                case "--json":
                    jsonFile = true;
                    break;
                case "--xml":
                    xmlFile = true;
                    break;
                default:
                    filePath = arg;
            }
        }
        if (jsonFile == false && xmlFile == false ){
            jsonFile = true;
        }
        if (jsonFile && xmlFile){
            jsonFile = true;
            xmlFile = false;
        }

        //String filePath = argv[0];

        // Create zoo
        Zoo zoo = new Zoo();
        // Add animals to the zoo

        if(jsonFile){
            zoo.addAnimals(filePath);
        } else if(xmlFile){
            zoo.addAnimalsXml(filePath);
        }


        // Create user action trigger
        ActionTrigger trigger = new ActionTrigger(zoo);

        AnimalType herbivore = AnimalType.HERBIVORE;
        AnimalType carnivore = AnimalType.CARNIVORE;

        // All of the following actions are up to users choice
        zoo.printAllStates();
        trigger.setMorning();
        zoo.printAllStates();

        trigger.visit(herbivore);
        zoo.printAllStates();
//        trigger.visit(carnivore);
        trigger.feedAnimals(herbivore);
        zoo.printAllStates();

        trigger.setNight();
        zoo.printAllStates();

        trigger.setMorning();
        zoo.printAllStates();

        trigger.setThunder();
        zoo.printAllStates();
        trigger.feedAnimals(carnivore);
        zoo.printAllStates();

        trigger.feedAnimals(herbivore);
        zoo.printAllStates();
        trigger.setNight();
        zoo.printAllStates();

        trigger.setMorning();
        zoo.printAllStates();
    }
}