package edu.ucalgary.oop;

import org.junit.Test;
import static org.junit.Assert.*;

/*
 * @author Nicole Izdraila, Mariia Podgaietska, Afrah Mohammad, Axel Sanchez
 * @since 2023-03-23
 */

public class CleaningCageTest {

  @Test
  public void testGetDuration() {
    CleaningCage cage1 = new CleaningCage("Buddy", "porcupine");
    CleaningCage cage2 = new CleaningCage("Max", "dog");

    assertEquals(10, cage1.getDuration());
    assertEquals(5, cage2.getDuration());
  }

  @Test
  public void testGetSpecies() {
    CleaningCage cage = new CleaningCage("Buddy", "porcupine");
    assertEquals("porcupine", cage.getSpecies());
  }

  @Test
  public void testGetName() {
    CleaningCage cage = new CleaningCage("Buddy", "porcupine");
    assertEquals("Buddy", cage.getName());
  }

  @Test
  public void testGetDescription() {
    CleaningCage cage = new CleaningCage("Buddy", "porcupine");
    assertEquals("Clean cage for porcupine", cage.getDescription());
  }

  @Test
  public void testToString() {
    CleaningCage cage = new CleaningCage("Buddy", "porcupine");
    assertEquals("To Clean cage for: Buddy, Duration: 10", cage.toString());
  }
}
