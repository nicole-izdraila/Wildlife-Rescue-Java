package edu.ucalgary.oop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * This is the main class for the Wildlife Rescue Centre application.
 * It contains the entry point for the program and initializes
 * the necessary objects to run the application.
 * 
 * To run this file, please go to the README.md file given in the GitHub
 * repository,
 * where it gives the necessary commands to compile and run this program
 * depending on the
 * operating system you're on
 * 
 * @author Nicole Izdraila, Mariia Podgaietska, Afrah Mohammad, Axel Sanchez
 * @since 2023-03-23
 */

public class Main {
  /**
   * The main method for the Wildlife Rescue Centre program. Returns a GUI
   * interface for
   * users to interact with, where they can see the overall generated schedule and
   * the
   * list of animal (nicknames) that are currently present in the given SQL
   * database
   * file.
   *
   * @param args The command-line arguments for the program. The program requires
   *             a single argument, which is the path to the configuration file.
   */
  public static void main(String[] args) {

    String url = "jdbc:mysql://localhost:3306/EWR";
    String username = "oop";
    String password = "password"; // Enter your personal password

    ArrayList<Animal> animals = new ArrayList<>();
    ArrayList<Task> tasks = new ArrayList<>();
    ArrayList<Treatment> treatments = new ArrayList<>();

    try (Connection connection = DriverManager.getConnection(url, username, password)) {

      Statement animalsQuery = connection.createStatement();
      ResultSet animalsResultSet = animalsQuery.executeQuery("SELECT * FROM ANIMALS");

      while (animalsResultSet.next()) {
        int id = animalsResultSet.getInt("AnimalID");
        String name = animalsResultSet.getString("AnimalNickname");
        String species = animalsResultSet.getString("AnimalSpecies");
        animals.add(new Animal(id, name, species));
      }

      Statement tasksQuery = connection.createStatement();
      ResultSet tasksResultSet = tasksQuery.executeQuery("SELECT * FROM TASKS");

      while (tasksResultSet.next()) {
        int id = tasksResultSet.getInt("TaskID");
        String description = tasksResultSet.getString("Description");
        int duration = tasksResultSet.getInt("Duration");
        int maxWindow = tasksResultSet.getInt("MaxWindow");
        tasks.add(new Task(id, description, duration, maxWindow));
      }

      Statement treatmentsQuery = connection.createStatement();
      ResultSet treatmentsResultSet = treatmentsQuery.executeQuery("SELECT * FROM TREATMENTS");

      while (treatmentsResultSet.next()) {
        int id = treatmentsResultSet.getInt("TreatmentID");
        int animalId = treatmentsResultSet.getInt("AnimalID");
        int taskId = treatmentsResultSet.getInt("TaskID");
        int startHour = treatmentsResultSet.getInt("StartHour");

        for (Animal animal : animals)
          if (animal.getID() == animalId)
            for (Task task : tasks)
              if (task.getID() == taskId)
                treatments.add(new Treatment(id, animal, task, startHour));
      }

    } catch (SQLException e) {
      System.out.println("Connection to the database failed. Try checking the password!");
      e.printStackTrace();
    } catch (IllegalArgumentException e) {
      System.out.println("Error: " + e);
      e.printStackTrace();
    }

    Schedule schedule = new Schedule();

    for (Animal animal : animals) {
      if (animal.getFeedingSchedule() != null)
        schedule.addFeeding(animal.getFeedingSchedule());
      schedule.addCageCleaning(animal.getCleaningCage());
    }

    for (Treatment treatment : treatments) {
      schedule.addTreatment(treatment);
    }

    schedule.addTasksToSchedule();

    schedule.addFeedingToSchedule();

    schedule.addCageCleaningToSchedule();

    EventQueue.invokeLater(() -> {
      JFrame frame = new JFrame("Animal Care Schedule");
      frame.setSize(800, 600);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      JLabel title = new JLabel("Welcome to the Animal Wildlife Rescue Centre Database!", SwingConstants.CENTER);
      JLabel instruction = new JLabel("What would you like to do?", SwingConstants.CENTER);

      JPanel panel = new JPanel(new BorderLayout());

      JPanel topPanel = new JPanel(new GridLayout(2, 1));
      topPanel.add(title);
      topPanel.add(instruction);
      panel.add(topPanel, BorderLayout.NORTH);

      JTextArea textArea = new JTextArea();
      textArea.setEditable(false);
      JScrollPane scrollPane = new JScrollPane(textArea);
      panel.add(scrollPane, BorderLayout.CENTER);

      JButton scheduleButton = new JButton("Generate Schedule");
      scheduleButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          String scheduleString = schedule.printSchedule();
          textArea.setText(scheduleString);
        }
      });

      JPanel buttonPanel = new JPanel();
      buttonPanel.setLayout(new FlowLayout());
      buttonPanel.add(scheduleButton);

      JButton editButton = new JButton("Edit");
      editButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          textArea.setEditable(true);
        }
      });
      buttonPanel.add(editButton);

      JButton saveToFileButton = new JButton("Save Schedule to File");
      saveToFileButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

          JFileChooser fileChooser = new JFileChooser();
          int option = fileChooser.showSaveDialog(frame);
          if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            String fileName = file.getAbsolutePath();
            if (textArea.isEditable()) {
              String modifiedSchedule = textArea.getText();
              schedule.printScheduleToFile(modifiedSchedule, fileName);
            } else {
              String scheduleString = schedule.printSchedule();
              schedule.printScheduleToFile(scheduleString, fileName);
            }
            JOptionPane.showMessageDialog(frame, "Schedule has been saved to file:\n" + fileName);
          }
        }
      });
      buttonPanel.add(saveToFileButton);

      panel.add(buttonPanel, BorderLayout.SOUTH);

      frame.add(panel);
      frame.setVisible(true);
    });

  }
}
