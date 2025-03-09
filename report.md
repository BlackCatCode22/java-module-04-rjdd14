Design & Implementation
Animal Class (Animals.java)

This class defines the basic attributes (name, age, species) and includes getters, setters, and a toString() method.
It also tracks the total number of animals created.
Subclasses (ZooAnimals.java)

The Hyena, Lion, Tiger, and Bear classes extend Animal.
Each subclass simply calls the Animal constructor with the correct species name.
Main Program (App.java)

Reads arriving animals from arrivingAnimals.txt.
Assigns names from animalNames.txt.
Uses an ArrayList to store animals and a HashMap to match species to available names.
Outputs a report in newAnimals.txt.