import java.io.*;
import java.util.*;


 // This is the main application that processes zoo animals from a file.
public class App {
    public static void main(String[] args) {
        System.out.println("\n\n Welcome to My Zoo Program\n\n");

        // list to store animal objects
        ArrayList<Animal> animals = new ArrayList<>();

        // HashMap to store names from animalNames.txt
        HashMap<String, Queue<String>> namesMap = loadAnimalNames("animalNames.txt");

        // Read and process arriving animals
        readArrivingAnimals("arrivingAnimals.txt", namesMap, animals);

        // Output results
        System.out.println("\nNumber of animals: " + Animal.numOfAnimals);
        for (Animal animal : animals) {
            System.out.println(animal);
        }

        // Save report to file
        writeReport("newAnimals.txt", animals);
    }

  
     // Reads names from animalNames.txt and stores them in a HashMap.
    private static HashMap<String, Queue<String>> loadAnimalNames(String filePath) {
        HashMap<String, Queue<String>> namesMap = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            String currentSpecies = null;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                if (line.contains("Names:")) {
                    currentSpecies = line.replace(" Names:", "").trim();
                    namesMap.put(currentSpecies, new LinkedList<>());
                } else if (currentSpecies != null) {
                    String[] names = line.split(", ");
                    Collections.addAll(namesMap.get(currentSpecies), names);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading animal names: " + e.getMessage());
        }
        return namesMap;
    }
    
     // Reads arriving animals from arrivingAnimals.txt and creates animal objects.
    private static void readArrivingAnimals(String filePath, HashMap<String, Queue<String>> namesMap, ArrayList<Animal> animals) {
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) continue;

                // Extract age and species
                String[] words = line.split(" ");
                int age = Integer.parseInt(words[0]);
                String species = words[4];

                // Capitalize species name properly
                species = species.substring(0, 1).toUpperCase() + species.substring(1).toLowerCase();

                // Assign a name or use default
                String name = namesMap.getOrDefault(species, new LinkedList<>()).poll();
                if (name == null) name = "Unnamed " + species;

                // Create the correct animal subclass
                Animal animal = createAnimal(species, name, age);
                if (animal != null) animals.add(animal);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
        }
    }

    
     // Creates an animal object based on species.
    private static Animal createAnimal(String species, String name, int age) {
        if (species.equals("Hyena")) return new Hyena(name, age);
        if (species.equals("Lion")) return new Lion(name, age);
        if (species.equals("Tiger")) return new Tiger(name, age);
        if (species.equals("Bear")) return new Bear(name, age);
        return null;
    }

    
     // Writes the list of animals to newAnimals.txt.
    private static void writeReport(String filePath, ArrayList<Animal> animals) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Zoo New Animals Report\n");
            writer.write("======================\n");

            for (Animal animal : animals) {
                writer.write(animal + "\n");
            }
            System.out.println("Report saved to " + filePath);
        } catch (IOException e) {
            System.out.println("Error writing report: " + e.getMessage());
        }
    }
}
