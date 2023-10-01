package edu.ucalgary.oop;

import java.util.HashMap;
import org.junit.Test;
import static org.junit.Assert.*;

/*
 * @author Nicole Izdraila, Mariia Podgaietska, Afrah Mohammad, Axel Sanchez
 * @since 2023-03-23
 */

public class ScheduleTest {

  @Test
  public void testConstructor() {
    Schedule schedule = new Schedule();
    assertNotNull(schedule.getSchedule());
    assertTrue(schedule.getSchedule() instanceof HashMap);
  }

  @Test
  public void testAddFeeding() {
    Schedule schedule = new Schedule();
    FeedingSchedule feeding = new FeedingSchedule("Panda", "fox", "crepuscular");
    schedule.addFeeding(feeding);
    assertEquals(1, schedule.getFeeding().size());
    assertEquals(feeding, schedule.getFeeding().get(0));
  }

  @Test
  public void testAddCageCleaning() {
    Schedule schedule = new Schedule();
    CleaningCage cageCleaning = new CleaningCage("Panda", "porcupine");
    schedule.addCageCleaning(cageCleaning);
    assertEquals(1, schedule.getCageCleaning().size());
    assertEquals(cageCleaning, schedule.getCageCleaning().get(0));
  }

  @Test
  public void testAddTreatment() {
    Schedule schedule = new Schedule();
    Animal panda = new Animal(1, "Bamboo", "coyote");
    Task treatmentTask = new Task(1, "Treat panda", 1, 1);
    Treatment treatment = new Treatment(1, panda, treatmentTask, 1);
    schedule.addTreatment(treatment);
    assertEquals(1, schedule.getTreatment().size());
    assertEquals(treatment, schedule.getTreatment().get(0));
  }
}
