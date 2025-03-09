package dennisMohle.myZoo.com;

public class Animal {
    private String animalName; // Animal's name
    private int age;           // Animal's age
    private String species;    // Animal's species

    public static int numOfAnimals = 0; // track number of animals created


    // Constructor to create an animal with a name, species, and age.
  
    public Animal(String name, String species, int age) {
        System.out.println("A new Animal object was created.");
        this.animalName = name;
        this.species = species;
        this.age = age;
        numOfAnimals++; // Increase animal count
    }

  
     // Default constructor 
    public Animal() {
        System.out.println("A new Animal object was created.");
        numOfAnimals++;
    }

    // Getter for name
    public String getName() {
        return animalName;
    }

    // Setter for name
    public void setName(String name) {
        this.animalName = name;
    }

    // Getter for age
    public int getAge() {
        return age;
    }

    // Setter for age
    public void setAge(int age) {
        this.age = age;
    }

    // Getter for species
    public String getSpecies() {
        return species;
    }

    // Setter for species
    public void setSpecies(String species) {
        this.species = species;
    }

   
     // Returns a formatted string representing the animal.
    public String toString() {
        return animalName + " (" + age + " years old, " + species + ")";
    }
}
