package edu.ucalgary.oop;

/**
 * The Task class is used for the Main Java file. It takes in
 * a task Id, the task description, the duration and the max window that the
 * tasks must be completed in.
 * 
 * @author Nicole Izdraila, Mariia Podgaietska, Afrah Mohammad, Axel Sanchez
 * @since 2023-03-23
 */
public class Task {
  private final int ID;
  private final String DESCRIPTION;
  private final int DURATION;
  private final int MAXWINDOW;

  /**
   * Constructor for the Task class
   * 
   * @param ID
   * @param DESCRIPTION
   * @param DURATION
   * @param MAXWINDOW
   * @throws IllegalArgumentException
   */
  public Task(int ID, String DESCRIPTION, int DURATION, int MAXWINDOW) throws IllegalArgumentException {
    if (ID <= 0 || DESCRIPTION == null || DESCRIPTION.trim().isEmpty() || DURATION <= 0 || MAXWINDOW <= 0) {
      throw new IllegalArgumentException("Invalid input for Task");
    }

    this.ID = ID;
    this.DESCRIPTION = DESCRIPTION;
    this.DURATION = DURATION;
    this.MAXWINDOW = MAXWINDOW;
  }

  /**
   * returns the ID of the task
   * 
   * @return ID of task
   */
  public int getID() {
    return ID;
  }

  /**
   * returns the description of task
   * 
   * @return the description of task
   */
  public String getDESCRIPTION() {
    return DESCRIPTION;
  }

  /**
   * returns the duration of task
   * 
   * @return ID of animal
   */
  public int getDURATION() {
    return DURATION;
  }

  /**
   * returns the max window that the task must be completed
   * 
   * @return maxwindow of task
   */
  public int getMAXWINDOW() {
    return MAXWINDOW;
  }

  /**
   * returns a string of the task ID, task description,
   * task duration, and max window
   * 
   * @return formatted String of what was listed above
   *
   */
  @Override
  public String toString() {
    return "ID: " + ID + ", Description: " + DESCRIPTION + ", Duration: " + DURATION + ", Max Window: " + MAXWINDOW;
  }
}
