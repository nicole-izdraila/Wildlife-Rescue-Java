package edu.ucalgary.oop;

import org.junit.Test;

/*
 * @author Nicole Izdraila, Mariia Podgaietska, Afrah Mohammad, Axel Sanchez
 * @since 2023-03-23
 */

public class TaskTest {

  @Test
  public void testTaskConstructorValidInput() {
    Task task = new Task(1, "Fix the door", 30, 60);

    assert task.getID() == 1;
    assert task.getDESCRIPTION().equals("Fix the door");
    assert task.getDURATION() == 30;
    assert task.getMAXWINDOW() == 60;
  }

  @Test
  public void testTaskConstructorInvalidId() {
    try {
      Task task = new Task(-1, "Fix the door", 30, 60);
      assert false : "Expected IllegalArgumentException for invalid ID";
    } catch (IllegalArgumentException e) {
      assert e.getMessage().equals("Invalid input for Task");
    }
  }

  @Test
  public void testTaskConstructorNullDescription() {
    try {
      Task task = new Task(1, null, 30, 60);
      assert false : "Expected IllegalArgumentException for null description";
    } catch (IllegalArgumentException e) {
      assert e.getMessage().equals("Invalid input for Task");
    }
  }

  @Test
  public void testTaskConstructorEmptyDescription() {
    try {
      Task task = new Task(1, " ", 30, 60);
      assert false : "Expected IllegalArgumentException for empty description";
    } catch (IllegalArgumentException e) {
      assert e.getMessage().equals("Invalid input for Task");
    }
  }

  @Test
  public void testTaskConstructorInvalidDuration() {
    try {
      Task task = new Task(1, "Fix the door", 0, 60);
      assert false : "Expected IllegalArgumentException for invalid duration";
    } catch (IllegalArgumentException e) {
      assert e.getMessage().equals("Invalid input for Task");
    }
  }

  @Test
  public void testTaskConstructorInvalidMaxWindow() {
    try {
      Task task = new Task(1, "Fix the door", 30, 0);
      assert false : "Expected IllegalArgumentException for invalid max window";
    } catch (IllegalArgumentException e) {
      assert e.getMessage().equals("Invalid input for Task");
    }
  }

  @Test
  public void testToString() {
    Task task = new Task(1, "Fix the door", 30, 60);
    String expected = "ID: 1, Description: Fix the door, Duration: 30, Max Window: 60";

    assert task.toString().equals(expected);
  }
}
