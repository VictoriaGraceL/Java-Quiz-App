// Package name where your project is located
package com.mycompany.quizapplication;

// Importing the Java Swing library for creating GUI (Graphical User Interface)
import javax.swing.*;
// Importing ActionListener to listen to button clicks
import java.awt.event.*;

// This is your main class which creates the Quiz app GUI and handles user interactions
public class QuizTest extends JFrame implements ActionListener {

    // This is the question label where we will show the current question
    JLabel label;

    // This is an array of radio buttons for multiple-choice answers
    JRadioButton[] radioButtons = new JRadioButton[4];

    // These are the two buttons: one for "Next" question, one for showing the result
    JButton btnNext, btnResult;

    // ButtonGroup makes sure only one radio button can be selected at a time
    ButtonGroup bg;

    // 'count' will track how many correct answers the user got
    int count = 0;
    // 'current' will track the current question number
    int current = 0;

    // Constructor of the QuizTest class, where we build the interface
    QuizTest(String title) {
        // This calls the JFrame constructor to create a window with the title you passed
        super(title);

        // Initialize the label to show the question
        label = new JLabel();
        add(label); // Add the label to the window

        // Create a ButtonGroup to link radio buttons together
        bg = new ButtonGroup();
        // Create 4 radio buttons and add them to the window and the button group
        for (int i = 0; i < 4; i++) {
            radioButtons[i] = new JRadioButton();
            add(radioButtons[i]);
            bg.add(radioButtons[i]);
        }

        // Initialize the "Next" button
        btnNext = new JButton("Next");
        // Initialize the "Result" button (we will hide it until the end)
        btnResult = new JButton("Result");
        btnResult.setVisible(false); // Hide the result button at the start

        // Listen for clicks on both buttons
        btnNext.addActionListener(this);
        btnResult.addActionListener(this);

        // Add the buttons to the window
        add(btnNext);
        add(btnResult);

        // Set positions (x, y, width, height) of the label
        label.setBounds(30, 40, 500, 20);
        // Set positions of the radio buttons
        for (int i = 0; i < 4; i++) {
            radioButtons[i].setBounds(50, 80 + i * 30, 500, 20);
        }

        // Set positions of the buttons
        btnNext.setBounds(100, 240, 100, 30);
        btnResult.setBounds(270, 240, 100, 30);

        // Close the app when the window is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Use manual layout (no layout manager)
        setLayout(null);
        // Set the position of the window on the screen
        setLocation(250, 100);
        // Set the window size
        setSize(600, 350);
        // Make the window visible
        setVisible(true);

        // Load the first question
        setQuestion();
    }

    // Method to display the current question and its answers
    void setQuestion() {
        // Clear the previous selection
        bg.clearSelection();

        // Show different questions based on the current question number
        switch (current) {
            case 0:
                label.setText("Q1: Which is the official language for Android Development?");
                radioButtons[0].setText("A - Java");
                radioButtons[1].setText("B - Kotlin");
                radioButtons[2].setText("C - C++");
                radioButtons[3].setText("D - JavaScript");
                break;

            case 1:
                label.setText("Q2: What is the size of a boolean variable?");
                radioButtons[0].setText("A - 8 bit");
                radioButtons[1].setText("B - 16 bit");
                radioButtons[2].setText("C - 32 bit");
                radioButtons[3].setText("D - Don't know");
                break;

            case 2:
                label.setText("Q3: What is the default value of a boolean variable?");
                radioButtons[0].setText("A - true");
                radioButtons[1].setText("B - false");
                radioButtons[2].setText("C - null");
                radioButtons[3].setText("D - Not defined");
                break;

            case 3:
                label.setText("Q4: What's the default value of a long variable?");
                radioButtons[0].setText("A - 0");
                radioButtons[1].setText("B - 0.0");
                radioButtons[2].setText("C - 0L");
                radioButtons[3].setText("D - Don't know");
                break;

            case 4:
                label.setText("Q5: What kind of variables can a class consist of?");
                radioButtons[0].setText("A - class, instance");
                radioButtons[1].setText("B - class, local, instance");
                radioButtons[2].setText("C - class only");
                radioButtons[3].setText("D - class and local");
                break;

            case 5:
                label.setText("Q6: What is function overloading?");
                radioButtons[0].setText("A - Same name, different parameters");
                radioButtons[1].setText("B - Same name, different return type");
                radioButtons[2].setText("C - Methods with same name");
                radioButtons[3].setText("D - None of these");
                break;

            case 6:
                label.setText("Q7: What is Serialization?");
                radioButtons[0].setText("A - Writing object state to another object");
                radioButtons[1].setText("B - Writing object state to a byte stream");
                radioButtons[2].setText("C - Both above");
                radioButtons[3].setText("D - None above");
                break;

            case 7:
                label.setText("Q8: Which of the following is thread-safe?");
                radioButtons[0].setText("A - StringBuilder");
                radioButtons[1].setText("B - StringBuffer");
                radioButtons[2].setText("C - Both");
                radioButtons[3].setText("D - None");
                break;

            case 8:
                label.setText("Q9: What is the size of a boolean variable?");
                radioButtons[0].setText("A - 8 bit");
                radioButtons[1].setText("B - 16 bit");
                radioButtons[2].setText("C - 32 bit");
                radioButtons[3].setText("D - Not precisely defined");
                break;

            case 9:
                label.setText("Q10: Which is the official language for Android development?");
                radioButtons[0].setText("A - Kotlin");
                radioButtons[1].setText("B - Java");
                radioButtons[2].setText("C - C++");
                radioButtons[3].setText("D - Not defined");
                break;
        }
    }

    // Method to check if the selected answer is correct
    boolean checkAnswer() {
        // Each case checks if the correct radio button is selected for the current question
        return switch (current) {
            case 0 -> radioButtons[1].isSelected(); // Kotlin
            case 1 -> radioButtons[2].isSelected(); // 32 bit
            case 2 -> radioButtons[1].isSelected(); // false
            case 3 -> radioButtons[2].isSelected(); // 0L
            case 4 -> radioButtons[1].isSelected(); // all three
            case 5 -> radioButtons[0].isSelected(); // overloading = same name, different parameters
            case 6 -> radioButtons[1].isSelected(); // to byte stream
            case 7 -> radioButtons[1].isSelected(); // StringBuffer
            case 8 -> radioButtons[3].isSelected(); // Not precisely defined
            case 9 -> radioButtons[0].isSelected();
            default -> // Kotlin
                // If somehow an invalid question number is passed
                    false;
        };
    }

    // This method is called when either the Next or Result button is clicked
    public void actionPerformed(ActionEvent e) {
        // If the Next button was clicked
        if (e.getSource() == btnNext) {
            // Check if the answer is correct and increase the correct count if it is
            if (checkAnswer()) count++;

            // Move to the next question
            current++;

            // If all 10 questions are done
            if (current == 10) {
                btnNext.setEnabled(false); // Disable the Next button
                btnResult.setVisible(true); // Show the Result button
            } else {
                // Load the next question
                setQuestion();
            }
        }

        // If the Result button was clicked
        if (e.getSource() == btnResult) {
            if (checkAnswer()) count++; // Check last answer

            // Show the final result in a pop-up box
            JOptionPane.showMessageDialog(this, "Correct Answers = " + count + " out of 10");
            // Close the application
            System.exit(0);
        }
    }

    // Main method to start the quiz
    public static void main(String[] args) {
        // Create a new quiz window
        new QuizTest("Java Quiz App");
    }
}
