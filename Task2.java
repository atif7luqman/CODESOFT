import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Task2 extends JFrame {
    private JTextField englishField, mathField, physicsField, chemistryField;
    private JLabel resultLabel, obtainedMarksLabel, averagePercentageLabel;
    private JTextField obtainedMarksField, averagePercentageField;

    public Task2() {
        // Set the window title
        setTitle("STUDENT GRADE CALCULATOR");

        // Create labels and text fields for subjects
        JLabel englishLabel = new JLabel("English:");
        englishField = new JTextField(10);

        JLabel mathLabel = new JLabel("Maths:");
        mathField = new JTextField(10);

        JLabel physicsLabel = new JLabel("Physics:");
        physicsField = new JTextField(10);

        JLabel chemistryLabel = new JLabel("Chemistry:");
        chemistryField = new JTextField(10);

        // Create labels and text fields for ObtainedMarks and AveragePercentage
        obtainedMarksLabel = new JLabel("Total Marks:");
        obtainedMarksField = new JTextField(10);
        obtainedMarksField.setEditable(false);

        averagePercentageLabel = new JLabel("Average Percentage:");
        averagePercentageField = new JTextField(10);
        averagePercentageField.setEditable(false);

        // Create a button to calculate grades
        JButton calculateButton = new JButton("Calculate Grade");
        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculateGrade();
            }
        });

        // Create a label to display the result
        resultLabel = new JLabel();

        // Create a panel to hold components
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2));
        panel.add(englishLabel);
        panel.add(englishField);
        panel.add(mathLabel);
        panel.add(mathField);
        panel.add(physicsLabel);
        panel.add(physicsField);
        panel.add(chemistryLabel);
        panel.add(chemistryField);
        panel.add(obtainedMarksLabel);
        panel.add(obtainedMarksField);
        panel.add(averagePercentageLabel);
        panel.add(averagePercentageField);
        panel.add(calculateButton);
        panel.add(resultLabel);

        // Add the panel to the frame
        add(panel);

        // Set the frame size and make it visible
        setSize(400, 250); // Increased the height to accommodate the new fields
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void calculateGrade() {
        try {
            float english = Float.parseFloat(englishField.getText());
            float math = Float.parseFloat(mathField.getText());
            float physics = Float.parseFloat(physicsField.getText());
            float chemistry = Float.parseFloat(chemistryField.getText());

            float obtainedMarks = english + math + physics + chemistry;
            float avgP = obtainedMarks / 4;

            // Set the obtainedMarks and averagePercentageField values
            obtainedMarksField.setText(Float.toString(obtainedMarks));
            averagePercentageField.setText(Float.toString(avgP) + "%");

            if (avgP >= 90)
                resultLabel.setText("A");
            else if (avgP >= 80)
                resultLabel.setText("B");
            else if (avgP >= 70)
                resultLabel.setText("C");
            else if (avgP >= 60)
                resultLabel.setText("D");
            else if (avgP >= 50)
                resultLabel.setText("E");
            else
                resultLabel.setText("F");
        } catch (NumberFormatException e) {
            resultLabel.setText("Please enter valid numeric values for marks.");
            obtainedMarksField.setText("");
            averagePercentageField.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Task2();
            }
        });
    }
}
