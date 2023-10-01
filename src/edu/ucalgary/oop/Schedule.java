package edu.ucalgary.oop;

import java.io.*;
import java.util.*;
import java.util.regex.*;

/**
 * The Schedule class is used to organize the tasks/feedings/etc. of
 * each animal into a generated, readable schedule, for this project.
 * 
 * @author Nicole Izdraila, Mariia Podgaietska, Afrah Mohammad, Axel Sanchez
 * @since 2023-03-23
 */
public class Schedule {
    private HashMap<Integer, ToDo> schedule = new HashMap<Integer, ToDo>();
    private ArrayList<FeedingSchedule> feedings = new ArrayList<FeedingSchedule>();
    private ArrayList<CleaningCage> cageCleanings = new ArrayList<CleaningCage>();
    private ArrayList<Treatment> treatments = new ArrayList<Treatment>();;
    private ArrayList<Integer> backupVolunteerHours = new ArrayList<Integer>();

    /**
     * constructor for Schedule class
     */
    public Schedule() {

        for (int i = 0; i < 24; i++) {
            schedule.put(i, new ToDo());
        }
    }

    /**
     * adds corresponding tasks to the schedule, and calculates the time
     * respective to the tasks added
     */
    public void addTasksToSchedule() {
        for (Treatment treatment : treatments) {
            int treatmentStartHour = treatment.getSTART_HOUR();
            int treatmentMaxWindow = treatment.getTASK().getMAXWINDOW();
            int currentWindow = 0;
            boolean taskAssigned = false;
            while (currentWindow < treatmentMaxWindow) {
                int currentHour = treatmentStartHour + currentWindow;
                if (currentHour > 23) {
                    currentHour = currentHour - 24;
                }
                if (schedule.get(currentHour).getTimeRemaining() >= treatment.getTASK().getDURATION()) {
                    schedule.get(currentHour).addTask(
                            treatment.getTASK().getDESCRIPTION() + " (" + treatment.getANIMAL().getNAME() + ")");
                    int newTimeRemaining = schedule.get(currentHour).getTimeRemaining()
                            - treatment.getTASK().getDURATION();
                    schedule.get(currentHour).updateTimeRemaining(newTimeRemaining);
                    taskAssigned = true;
                    break;
                } else {
                    currentWindow++;
                }
            }
            if (!taskAssigned) {
                callBackupVolunteer(treatmentStartHour, null, treatment);

            }
        }
    }

    /**
     * adds corresponding feeding times to the schedule, and
     * calculates the time respective to the feeding times that are added
     */
    public void addFeedingToSchedule() {
        for (FeedingSchedule feeding : feedings) {
            int feedingStartHour = feeding.getStartHour();
            int feedingMaxWindow = feeding.getTimeWindow();
            int currentWindow = 0;
            String species = feeding.getSpecies();
            boolean taskAssigned = false;
            boolean speciesAlreadyFed = false;

            Pattern pattern = Pattern.compile(species);

            while (currentWindow < feedingMaxWindow) {
                int currentHour = feedingStartHour + currentWindow;

                if (currentHour > 23) {
                    currentHour = currentHour - 24;
                }

                for (String otherTasks : schedule.get(currentHour).getToDoList()) {
                    Matcher matcher = pattern.matcher(otherTasks);
                    if (matcher.find() && !schedule.get(currentHour).getToDoList().isEmpty()) {
                        speciesAlreadyFed = true;
                    }
                }

                if (schedule.get(currentHour).getToDoList().isEmpty())
                    speciesAlreadyFed = false;

                if (speciesAlreadyFed) {
                    if (schedule.get(currentHour).getTimeRemaining() >= feeding.getDuration()) {
                        schedule.get(currentHour).addTask(feeding.getDescription() + " " + feeding.getName());
                        int newTimeRemaining = schedule.get(currentHour).getTimeRemaining() - feeding.getDuration();
                        schedule.get(currentHour).updateTimeRemaining(newTimeRemaining);
                        taskAssigned = true;
                        break;
                    } else {
                        currentWindow++;
                    }
                } else {
                    if (schedule.get(currentHour).getTimeRemaining() >= feeding.getDuration() + feeding.getPrep()) {
                        schedule.get(currentHour).addTask(feeding.getDescription() + " " + feeding.getName());
                        int newTimeRemaining = schedule.get(currentHour).getTimeRemaining()
                                - (feeding.getDuration() + feeding.getPrep());
                        schedule.get(currentHour).updateTimeRemaining(newTimeRemaining);
                        taskAssigned = true;
                        break;
                    } else {
                        currentWindow++;
                    }
                }
            }
            if (!taskAssigned) {
                callBackupVolunteer(feedingStartHour, feeding, null);
            }
        }
    }

    /**
     * retrieves all the names (nicknames) of the animals currently at
     * the shelter, returns it as a String
     * 
     * @return String of all animal names
     */
    public String getAllNames() {
        StringBuilder sb = new StringBuilder();
        for (Treatment treatment : treatments) {
            if (!sb.toString().contains(treatment.getANIMAL().getNAME()))
                sb.append(treatment.getANIMAL().getNAME()).append("\n");
        }
        return sb.toString();
    }

    /**
     * String output for if a backup volunteer is needed depending on the feeding
     * times, when time exceeds the hour, then a backup volunteer is called.
     * 
     * @param feeding
     */
    public void callBackupVolunteer(int hour, FeedingSchedule feeding, Treatment treatment) {
        int duration = 0;
        int maxWindow = 0;
        int currentWindow = 0;
        if (feeding != null) {
            duration = feeding.getDuration();
            maxWindow = feeding.getTimeWindow();
        } else if (treatment != null) {
            duration = treatment.getTASK().getDURATION();
            maxWindow = treatment.getTASK().getMAXWINDOW();
        }
        while (currentWindow < maxWindow) {
            int currentHour = hour + currentWindow;
            if (currentHour > 23) {
                currentHour = currentHour - 24;
            }
            int timeRemainingWithVolunteer = 120 - schedule.get(hour).getTimeRemaining();
            if (timeRemainingWithVolunteer >= duration) {
                if (feeding != null)
                    schedule.get(currentHour).addTask(feeding.getDescription() + " " + feeding.getName());
                else if (treatment != null)
                    schedule.get(currentHour).addTask(
                            treatment.getTASK().getDESCRIPTION() + " (" + treatment.getANIMAL().getNAME() + ")");
                int newTimeRemaining = timeRemainingWithVolunteer - duration;
                schedule.get(currentHour).updateTimeRemaining(newTimeRemaining);
                backupVolunteerHours.add(currentHour);
                break;
            } else {
                currentWindow++;
            }
        }
    }

    /**
     * adds the cage cleaning task to the schedule, and calculates the
     * corresponding time after the cleaning is done.
     */
    public void addCageCleaningToSchedule() {
        for (CleaningCage cageCleaning : cageCleanings) {
            int cageCleaningDuration = cageCleaning.getDuration();
            String cageCleaningDescription = cageCleaning.getDescription();

            for (int i = 0; i < 24; i++) {
                if (schedule.get(i).getTimeRemaining() >= cageCleaningDuration) {
                    schedule.get(i).addTask(cageCleaningDescription + " " + cageCleaning.getName());
                    int newTimeRemaining = schedule.get(i).getTimeRemaining() - cageCleaningDuration;
                    schedule.get(i).updateTimeRemaining(newTimeRemaining);
                    break;
                }
            }
        }
    }

    /**
     * adds feeding tasks based on the FeedingSchedule object
     * 
     * @param feeding
     */
    public void addFeeding(FeedingSchedule feeding) {
        feedings.add(feeding);
    }

    /**
     * adds the cage cleaning tasks based on the CleaningCage object
     * 
     * @param cageCleaning
     */
    public void addCageCleaning(CleaningCage cageCleaning) {
        cageCleanings.add(cageCleaning);
    }

    /**
     * adds the treatment tasks based on the Treatment object
     * 
     * @param treatment
     */
    public void addTreatment(Treatment treatment) {
        treatments.add(treatment);
    }

    /**
     * a getter for recieving the treatments
     * 
     * @return ArrayList<Treatment> treatments
     */
    public ArrayList<Treatment> getTreatment() {
        return treatments;
    }

    /**
     * a getter for recieving the feeding tasks
     * 
     * @return ArrayList<FeedingSchedule> feedings
     */
    public ArrayList<FeedingSchedule> getFeeding() {
        return feedings;
    }

    /**
     * a getter for recieving the cage cleaning tasks
     * 
     * @return ArrayList<CleaningCage> cageCleanings
     */
    public ArrayList<CleaningCage> getCageCleaning() {
        return cageCleanings;
    }

    /**
     * a getter for recieving the final schedule
     * 
     * @return HashMap<Integer, ToDo> schedule
     */
    public HashMap<Integer, ToDo> getSchedule() {
        return schedule;
    }

    /**
     * prints the schedule to the made GUI in the Main class, with the
     * corresponding times (00:00 to 23:00), and the respective tasks associated
     * with the times
     * 
     * @return String representation of the schedule
     */
    public String printSchedule() {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 24; i++) {
            if (this.backupVolunteerHours.contains(i))
                sb.append(i).append(":00 (Backup Volunteer needed)\n").append(schedule.get(i)).append("\n");
            else
                sb.append(i).append(":00\n").append(schedule.get(i)).append("\n");
        }
        return sb.toString();
    }

    /**
     * If the edit mode is on, it prints the schedule to the made GUI in the Main class, with the
     * corresponding times (00:00 to 23:00), and the respective tasks associated
     * with the times with the modified schedule.
     * 
     * @return String representation of the user modified schedule
     */

    public void printScheduleToFile(String modifiedSchedule, String outName) {
        FileWriter out = null;
        System.out.println("Attempting to write to file " + outName);
        try {
            out = new FileWriter(outName);
            out.write(modifiedSchedule);
        } catch (IOException e) {
            System.out.println("Error writing to file " + outName);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    System.out.println("Error closing file " + outName);
                }
            }
        }
    }
}