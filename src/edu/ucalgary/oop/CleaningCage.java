package edu.ucalgary.oop;

/**
 * The CageCleaning class is used for this project. It takes in the
 * species, name, duration and whether the cage is cleaned
 * 
 * @author Nicole Izdraila, Mariia Podgaietska, Afrah Mohammad, Axel Sanchez
 * @since 2023-03-23
 */
public class CleaningCage {

    private String species;
    private String name;
    private String description = "Clean cage";
    private int duration;

    /**
     * Constructor for the CageCleaning class
     * 
     * @param species
     * @param name
     */
    public CleaningCage(String name, String species) {
        this.species = species;
        this.name = name;

        if (species.equals("porcupine"))
            this.duration = 10;
        else
            this.duration = 5;
    }

    /**
     * returns the duration it takes to clean the cage
     * 
     * @return duration of cage cleaning
     */
    public int getDuration() {
        return duration;
    }

    /**
     * returns the species of the animal
     * 
     * @return species of animal
     */
    public String getSpecies() {
        return species;
    }

    /**
     * returns the name of the animal
     * 
     * @return name of animal
     */
    public String getName() {
        return name;
    }

    /**
     * returns the description fro the animal
     * 
     * @return description of task
     */
    public String getDescription() {
        return description + " for " + species;
    }

    /**
     * returns a string of the task description, animal name, and duration to
     * complete
     * 
     * @return formatted String of what was listed above
     *
     */
    @Override
    public String toString() {
        return "To " + description + " for: " + name + ", Duration: " + duration;
    }

}
