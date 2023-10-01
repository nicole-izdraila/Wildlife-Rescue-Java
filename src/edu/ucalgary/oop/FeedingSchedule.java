package edu.ucalgary.oop;

/**
 * The feedingSchedule class is used for this project. It takes in
 * an animal species, name, whether they need to be fed, duration of task,
 * starthour, timewindow and prep
 * 
 * @author Nicole Izdraila, Mariia Podgaietska, Afrah Mohammad, Axel Sanchez
 * @since 2023-03-23
 */
public class FeedingSchedule {
    private String species;
    private String name;
    private String description = "Feed";
    private int duration;
    private int startHour;
    private int timeWindow;
    private int prep;

    /**
     * Constructor for the FeedingSchedule class
     * 
     * @param name
     * @param species
     * @param type
     */
    public FeedingSchedule(String name, String species, String type) {
        this.duration = 5;
        this.timeWindow = 3;
        this.species = species;
        this.name = name;

        if (type.equals("crepescular"))
            this.startHour = 19;
        else if (type.equals("nocturnal"))
            this.startHour = 0;
        else
            this.startHour = 8;

        if (species.equals("porcupine") || species.equals("raccoon") || species.equals("beaver")) {
            this.prep = 0;
        } else if (species.equals("coyote")) {
            this.prep = 10;
        } else {
            this.prep = 0;
        }

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
     * returns the description of the task
     * 
     * @return description of the task
     */
    public String getDescription() {
        return description + " " + species;
    }

    /**
     * returns the duration of the task
     * 
     * @return duration of the task
     */
    public int getDuration() {
        return duration;
    }

    /**
     * returns the start hour that the task can be performed
     * 
     * @return starthour of the task
     */
    public int getStartHour() {
        return startHour;
    }

    /**
     * returns the time window that the task can be performed
     * 
     * @return timeWindow of task
     */
    public int getTimeWindow() {
        return timeWindow;
    }

    /**
     * returns the prep time of the task
     * 
     * @return prep of task
     */
    public int getPrep() {
        return prep;
    }

    /**
     * returns a string of the task description, name, start hour
     * time window, dration, and prep time
     * 
     * @return formatted String of what was listed above
     *
     */
    @Override
    public String toString() {
        return "To " + description + " " + name + ", Start time: " + startHour + ", Time Window: " + timeWindow
                + ", Duration: " + duration + ", Prep: " + prep;
    }

}