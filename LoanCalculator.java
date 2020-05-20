
/**
 * LoanCalculator
 * 
 * This class builds a JFrame top level container called LoanCalculator
 * and performs an action when a calculate button is triggered
 * Specifics:
 * A user will enter an Annual Interest Rate, Number of Years, and 
 * Loan Amount and press the calculate button. Once pressed the 
 * Monthly Payment and Total Payment will appear.
 * 
 * @author SDantzler
 * */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.text.NumberFormat;

public class LoanCalculator extends JFrame implements ActionListener {

   // instance variables
   private JPanel contentPane;
   private double val1, val2, val3, answer;
   private JTextField input1, input2, input3, sumMonthly, sumTotal;
   private JButton calculate;
   private NumberFormat nf = NumberFormat.getNumberInstance();

   /**
    * Launch the application.
    */
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               new LoanCalculator();
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      });
   }// end main method

   /**
    * Create the frame.
    */
   public LoanCalculator() {

      // Create a new frame
      JFrame loanCalc = new JFrame("Loan Calculator");

      // Terminate the program when the user closes the application
      loanCalc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      // Bounds of the content pane
      setBounds(100, 100, 450, 300);
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      contentPane.setLayout(new BorderLayout(0, 0));
      setContentPane(contentPane);

      // Add a new Panel with a specific Layout
      JPanel panel = new JPanel();
      GridLayout gl = new GridLayout(7, 2);
      panel.setLayout(gl);

      // Text based labels and fields added to the panel
      JLabel label1 = new JLabel("Annual Interest Rate (%):");
      input1 = new JTextField(10);
      panel.add(label1);
      panel.add(input1);

      JLabel label2 = new JLabel("Number of Years:");
      input2 = new JTextField(10);
      panel.add(label2);
      panel.add(input2);

      JLabel label3 = new JLabel("Loan Amount:");
      input3 = new JTextField(10);
      panel.add(label3);
      panel.add(input3);

      JLabel label4 = new JLabel("Monthly Payment:");
      sumMonthly = new JTextField(10);
      panel.add(label4);
      panel.add(sumMonthly);

      JLabel label5 = new JLabel("Total Payment:");
      sumTotal = new JTextField(10);
      panel.add(label5);
      panel.add(sumTotal);

      // Pushes the calculate button to the 2nd column
      JLabel label6 = new JLabel("");
      panel.add(label6);

      loanCalc.add(panel, BorderLayout.CENTER);

      // Create a Calculate button
      calculate = new JButton("Calculate");
      calculate.setText("Calculate");
      panel.add(calculate);

      // Add Action Listener and Tool Tip for calculate
      calculate.addActionListener(this);
      calculate.setToolTipText("Press this button to calculate loan payments.");

      // Display the frame
      loanCalc.setVisible(true);

   }// end SimpleCalculator method

   /**
    * This method triggers an action if the calculator button is pressed
    */
   public void actionPerformed(ActionEvent ae) {
      if (ae.getSource() == calculate) {
         sumPayments();
      }
   }// end actionPerformed method

   /**
    * This method calculates the monthly and total payments from the inputed
    * values and populates the text fields with the results
    */
   public void sumPayments() {

      // monthly payment: (i * A) / (1 -(1 + i)^ -n)
      // total payment: monthly payment * n
      val1 = Double.parseDouble(input1.getText());
      val2 = Double.parseDouble(input2.getText());
      val3 = Double.parseDouble(input3.getText());

      // calculate payments
      double i = val1 / (100.0 * 12.0);
      double n = val2 * 12.0;
      String answerMod;
      nf.setMaximumFractionDigits(2); // format the output

      answer = (i * val3) / (1.0 - Math.pow(1.0 + i, -1.0 * n));
      answerMod = nf.format(answer); // apply format
      sumMonthly.setText("" + answerMod);

      answer = answer * n;
      answerMod = nf.format(answer); // apply format
      sumTotal.setText("" + answerMod);
   }// end sumMonthly method

}// end class LoanCalculator
