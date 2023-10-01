package edu.ucalgary.oop;

/**
 * The Treatment class is used for this project. It takes in
 * an animal Id, the Animal object, the Task object and the start hour for the
 * respective treatment being done per. animal.
 * 
 * @author Nicole Izdraila, Mariia Podgaietska, Afrah Mohammad, Axel Sanchez
 * @since 2023-03-23
 */
public class Treatment {
  private final int ID;
  private final Animal ANIMAL;
  private final Task TASK;
  private final int START_HOUR;

  /**
   * Constructor for the Treatment class
   * 
   * @param ID
   * @param ANIMAL
   * @param TASK
   * @param START_HOUR
   * @throws IllegalArgumentException
   */
  public Treatment(int ID, Animal ANIMAL, Task TASK, int START_HOUR) throws IllegalArgumentException {
    if (ID == 0 || ANIMAL == null || TASK == null || START_HOUR < 0) {
      throw new IllegalArgumentException("All fields must be filled out");
    }
    this.ID = ID;
    this.ANIMAL = ANIMAL;
    this.TASK = TASK;
    this.START_HOUR = START_HOUR;
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
   * returns the Animal object
   * 
   * @return Animal object
   */
  public Animal getANIMAL() {
    return this.ANIMAL;
  }

  /**
   * returns the Task object
   * 
   * @return Task object
   */
  public Task getTASK() {
    return this.TASK;
  }

  /**
   * returns the start hour of the task
   * 
   * @return start hour of the task
   */
  public int getSTART_HOUR() {
    return this.START_HOUR;
  }

  /**
   * returns a string of the Animal name, task description,
   * start hour, duration and max window
   * 
   * @return formatted String of what was listed above
   *
   */
  @Override
  public String toString() {
    return "ANIMAL name: " + ANIMAL.getNAME() + ", Task description: " + TASK.getDESCRIPTION() + ", Start Hour: "
        + START_HOUR + ", Duration: " + TASK.getDURATION() + ", Max Window: " + TASK.getMAXWINDOW();
  }
}
