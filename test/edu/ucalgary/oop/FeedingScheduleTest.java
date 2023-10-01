package edu.ucalgary.oop;

import org.junit.Test;
import static org.junit.Assert.*;

/*
 * @author Nicole Izdraila, Mariia Podgaietska, Afrah Mohammad, Axel Sanchez
 * @since 2023-03-23
 */

public class FeedingScheduleTest {

  @Test
  public void testGetSpecies() {
    FeedingSchedule schedule = new FeedingSchedule("Buddy", "porcupine", "crepescular");
    assertEquals("porcupine", schedule.getSpecies());
  }

  @Test
  public void testGetName() {
    FeedingSchedule schedule = new FeedingSchedule("Buddy", "porcupine", "crepescular");
    assertEquals("Buddy", schedule.getName());
  }

  @Test
  public void testGetDescription() {
    FeedingSchedule schedule = new FeedingSchedule("Buddy", "porcupine", "crepescular");
    assertEquals("Feed porcupine", schedule.getDescription());
  }

  @Test
  public void testGetDuration() {
    FeedingSchedule schedule = new FeedingSchedule("Buddy", "porcupine", "crepescular");
    assertEquals(5, schedule.getDuration());
  }

  @Test
  public void testGetStartHour() {
    FeedingSchedule schedule1 = new FeedingSchedule("Buddy", "porcupine", "crepescular");
    FeedingSchedule schedule2 = new FeedingSchedule("Max", "dog", "nocturnal");
    FeedingSchedule schedule3 = new FeedingSchedule("Charlie", "cat", "diurnal");

    assertEquals(19, schedule1.getStartHour());
    assertEquals(0, schedule2.getStartHour());
    assertEquals(8, schedule3.getStartHour());
  }

  @Test
  public void testGetTimeWindow() {
    FeedingSchedule schedule = new FeedingSchedule("Buddy", "porcupine", "crepescular");
    assertEquals(3, schedule.getTimeWindow());
  }

  @Test
  public void testGetPrep() {
    FeedingSchedule schedule1 = new FeedingSchedule("Buddy", "porcupine", "crepescular");
    FeedingSchedule schedule2 = new FeedingSchedule("Max", "coyote", "nocturnal");
    FeedingSchedule schedule3 = new FeedingSchedule("Charlie", "cat", "diurnal");

    assertEquals(0, schedule1.getPrep());
    assertEquals(10, schedule2.getPrep());
    assertEquals(0, schedule3.getPrep());
  }

  @Test
  public void testToString() {
    FeedingSchedule schedule = new FeedingSchedule("Buddy", "porcupine", "crepescular");
    assertEquals("To Feed Buddy, Start time: 19, Time Window: 3, Duration: 5, Prep: 0", schedule.toString());
  }
}
