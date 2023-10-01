package edu.ucalgary.oop;

import org.junit.Test;
import org.junit.Assert;

/*
 * @author Nicole Izdraila, Mariia Podgaietska, Afrah Mohammad, Axel Sanchez
 * @since 2023-03-23
 */

public class AnimalTest {

  @Test
  public void testAnimalConstructor() {
    Animal animal = new Animal(1, "Mowgli", "fox");
    Assert.assertEquals(1, animal.getID());
    Assert.assertEquals("Mowgli", animal.getNAME());
    Assert.assertEquals("fox", animal.getSPECIES());
    Assert.assertEquals("nocturnal", animal.getTYPE());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAnimalConstructorWithInvalidID() {
    Animal animal = new Animal(-1, "Mowgli", "fox");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAnimalConstructorWithEmptyName() {
    Animal animal = new Animal(1, "", "fox");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAnimalConstructorWithEmptySpecies() {
    Animal animal = new Animal(1, "Mowgli", "");
  }

  @Test
  public void testAnimalToString() {
    Animal animal = new Animal(1, "Mowgli", "fox");
    String expected = "ID: 1, Name: Mowgli, Species: fox, Type: nocturnal";
    Assert.assertEquals(expected, animal.toString());
  }
}
