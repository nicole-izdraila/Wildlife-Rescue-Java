package edu.ucalgary.oop;

import java.util.regex.*;

/**
 * The Animal class is used for this project. It takes in
 * an animal Id, animal name, animal species, animal type (nocturnal,
 * crepescular, diurnal),
 * feeding schedule object and cage cleaning object.
 * 
 * @author Nicole Izdraila, Mariia Podgaietska, Afrah Mohammad, Axel Sanchez
 * @since 2023-03-23
 */
public class Animal {
  private final int ID;
  private final String NAME;
  private final String SPECIES;
  private final String TYPE;
  private FeedingSchedule feedingSchedule;
  private CleaningCage cleaningCage;

  /**
   * Constructor for the Animal class
   * 
   * @param ID
   * @param NAME
   * @param SPECIES
   * @param TYPE
   * @param feedingSchedule
   * @param cleaningCage
   * @throws IllegalArgumentException
   */
  public Animal(int ID, String NAME, String SPECIES) throws IllegalArgumentException {
    if (ID <= 0 || NAME == null || NAME.trim().isEmpty() || SPECIES == null || SPECIES.trim().isEmpty()) {
      throw new IllegalArgumentException("Invalid input for Animal");
    }

    this.ID = ID;
    this.NAME = NAME;
    this.SPECIES = SPECIES;

    if (SPECIES.equals("fox") || SPECIES.equals("raccoon")) {
      this.TYPE = "nocturnal";
    } else if (SPECIES.equals("coyote") || SPECIES.equals("porcupine")) {
      this.TYPE = "crepescular";
    } else {
      this.TYPE = "diurnal";
    }

    Pattern pattern = Pattern.compile("^[a-zA-Z]+$");
    Matcher matcher = pattern.matcher(this.NAME);

    if (matcher.find()) {
      this.feedingSchedule = new FeedingSchedule(this.NAME, this.SPECIES, this.TYPE);
    }
    this.cleaningCage = new CleaningCage(this.NAME, this.SPECIES);
  }

  /**
   * returns the ID of the animal
   * 
   * @return ID of animal
   */
  public int getID() {
    return this.ID;
  }

  /**
   * returns the name of the animal
   * 
   * @return NAME of animal
   */
  public String getNAME() {
    return this.NAME;
  }

  /**
   * returns the species of the animal
   * 
   * @return SPECIES of animal
   */
  public String getSPECIES() {
    return this.SPECIES;
  }

  /**
   * returns the type of the animal
   * 
   * @return TYPE of animal
   */
  public String getTYPE() {
    return this.TYPE;
  }

  /**
   * returns the feeding schedule of the animal
   * 
   * @return feedingSchedule of animal
   */
  public FeedingSchedule getFeedingSchedule() {
    return this.feedingSchedule;
  }

  /**
   * returns the cage cleaning time of the animal
   * 
   * @return cleaningCage of animal
   */
  public CleaningCage getCleaningCage() {
    return this.cleaningCage;
  }

  /**
   * returns a string of the Animal id, name,
   * species, and type
   * 
   * @return formatted String of what was listed above
   *
   */
  @Override
  public String toString() {
    return "ID: " + ID + ", Name: " + NAME + ", Species: " + SPECIES + ", Type: " + TYPE;
  }

}
