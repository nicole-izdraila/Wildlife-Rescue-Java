package edu.ucalgary.oop;

import org.junit.Before;
import org.junit.Test;

/*
 * @author Nicole Izdraila, Mariia Podgaietska, Afrah Mohammad, Axel Sanchez
 * @since 2023-03-23
 */

import static org.junit.Assert.*;

public class TreatmentTest {
  private Animal testAnimal;
  private Task testTask;

  @Before
  public void setUp() {
    testAnimal = new Animal(1, "Test Animal", "Test Species");
    testTask = new Task(1, "Test Task", 30, 4);
  }

  @Test
  public void testValidTreatmentCreation() {
    Treatment treatment = new Treatment(1, testAnimal, testTask, 2);

    assertEquals(1, treatment.getID());
    assertEquals(testAnimal, treatment.getANIMAL());
    assertEquals(testTask, treatment.getTASK());
    assertEquals(2, treatment.getSTART_HOUR());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidTreatmentCreation() {
    new Treatment(0, null, null, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidTreatmentCreationInvalidId() {
    new Treatment(0, testAnimal, testTask, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidTreatmentCreationNullAnimal() {
    new Treatment(1, null, testTask, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidTreatmentCreationNullTask() {
    new Treatment(1, testAnimal, null, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidTreatmentCreationNegativeStartHour() {
    new Treatment(1, testAnimal, testTask, -1);
  }
}
