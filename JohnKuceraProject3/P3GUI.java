/*
* File: P3GUI.java
* Author: John Kucera
* Date: February 19, 2020
* Purpose: This Java program creates a GUI for accepting a list of integers
* OR a list of fractions and sorts it in ascending or descending order,
* returning that sorted list. To do so, a Binary Search Tree is constructed
* to allow in-order traversal in BST.java.
*/

// import of necessary java classes
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

// P3GUI class
public class P3GUI extends JFrame {
    // Instance Variables
    private String originalList;
    private String sortedList;
    
    // Window Components
    private static JLabel originalLbl = new JLabel("Original List");
    private static JLabel sortedLbl = new JLabel("Sorted List");
    private static JTextField originalTxt = new JTextField(null, 25);
    private static JTextField sortedTxt = new JTextField(null, 25);
    private static JButton performBtn = new JButton("Perform Sort");
    private static JRadioButton ascendRad = new JRadioButton("Ascending");
    private static JRadioButton descendRad = new JRadioButton("Descending");
    private static JRadioButton intRad = new JRadioButton("Integer");
    private static JRadioButton fracRad = new JRadioButton("Fraction");
    private ButtonGroup sortBtns = new ButtonGroup();
    private ButtonGroup typeBtns = new ButtonGroup();
    private TitledBorder sortTitledBorder, typeTitledBorder;
    
    // Method to add components to titled border
    public void addCompForBorder(Border border, JRadioButton b1, JRadioButton b2,
        Container container) {
            JPanel comp = new JPanel(new GridLayout(2, 1));
            comp.setBorder(border);
            comp.add(b1);
            comp.add(b2);
            container.add(comp);
    } // end of method

    // Perform Button Listener
    class PerformBtnListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Text Field -> Listener
            originalList = originalTxt.getText();
            try {
                if (originalList.isEmpty()) {
                    throw new NullPointerException();
                }
                
                // Convert original list into tokens
                String[] tokens = originalList.split(" ");
                // BST creation and value insertion for Integers
                if (intRad.isSelected()) {
                    BST<Integer> bstInt = new BST<>();
                    for (String token : tokens) {
                        bstInt.insertNode(Integer.parseInt(token));
                    }
                    // Determine order of sorting
                    if (ascendRad.isSelected()) {
                        sortedList = bstInt.getAscend();
                    }
                    else if (descendRad.isSelected()) {
                        sortedList = bstInt.getDescend();
                    }
                    sortedTxt.setText(sortedList);
                    // Detect duplicates
                    boolean hasDuplicate = false;
                    outerfor:
                    for (int i = 0; i < tokens.length; i++) {
                        for (int j = i + 1; j < tokens.length; j++) {
                            if (j != i && tokens[j].equals(tokens[i])) {
                                hasDuplicate = true;
                                break outerfor;
                            }
                        }
                    }
                    if (hasDuplicate) {
                        JOptionPane.showMessageDialog(null, 
                            "This list contains 1 or more duplicate values.");
                    }
                    else {
                        JOptionPane.showMessageDialog(null, 
                            "This list contains no duplicate values.");
                    } // end of else
                } // end of outer if

                // BST creation and value insertion for Fractions
                if (fracRad.isSelected()) {
                    BST<Fraction> bstFra = new BST<>();
                    for (String token : tokens) {
                        Fraction fraction = new Fraction(token);
                        bstFra.insertNode(fraction);
                    }
                    // Determine order of sorting
                    if (ascendRad.isSelected()) {
                        sortedList = bstFra.getAscend();
                    }
                    else if (descendRad.isSelected()) {
                        sortedList = bstFra.getDescend();
                    }
                    sortedTxt.setText(sortedList);
                    // Detect duplicates
                    boolean hasDuplicate = false;
                    outerfor:
                    for (int i = 0; i < tokens.length; i++) {
                        for (int j = i + 1; j < tokens.length; j++) {
                            if (j != i && tokens[j].equals(tokens[i])) {
                                hasDuplicate = true;
                                break outerfor;
                            }
                        }
                    }
                    if (hasDuplicate) {
                        JOptionPane.showMessageDialog(null, 
                            "This list contains 1 or more duplicate values.");
                    }
                    else {
                        JOptionPane.showMessageDialog(null, 
                            "This list contains no duplicate values.");
                    } // end of else
                } // end of outer if
            } // end of try
            
            // Handling invalid input
            catch (NullPointerException ex) { // Empty Input
                JOptionPane.showMessageDialog(null, 
                    "Please enter a list of integers OR a list of fractions.");
            }
            catch (NumberFormatException ex) { // Non-numeric input
                JOptionPane.showMessageDialog(null, 
                    "Your list is invalid due to non-numeric input. Please try again.");
            }
            catch (MalformedFractionException ex) { // Malformed fraction input
                JOptionPane.showMessageDialog(null, 
                    "Your list is invalid due to a malformed fraction. Please try again.");
            } // end of catch
        } // end of method
    } // end of listener

    // GUI Creation
    public P3GUI() {
        // Frame Creation
        super("John's Binary Search Tree Sort"); // Titling frame
        setSize(400, 250);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Panels
        JPanel originalPanel = new JPanel(); // Original List
        originalPanel.add(originalLbl);
        originalPanel.add(originalTxt);
        
        JPanel sortedPanel = new JPanel(); // Sorted List
        sortedPanel.add(sortedLbl);
        sortedPanel.add(sortedTxt);
        sortedTxt.setEditable(false);
        
        JPanel performPanel = new JPanel(); // Perform Button
        performPanel.add(performBtn);
        performBtn.addActionListener(new PerformBtnListener());
        
        JPanel radioPanel = new JPanel(new GridLayout(1, 2)); // Radio buttons
        
        // Sort order border
        sortTitledBorder = BorderFactory.createTitledBorder("Sort Order");
        addCompForBorder(sortTitledBorder, ascendRad, descendRad, radioPanel);
        sortBtns.add(ascendRad);
        sortBtns.add(descendRad);
        ascendRad.setSelected(true);
        
        // Numeric type border
        typeTitledBorder = BorderFactory.createTitledBorder("Numeric Type");
        addCompForBorder(typeTitledBorder, intRad, fracRad, radioPanel);
        typeBtns.add(intRad);
        typeBtns.add(fracRad);
        intRad.setSelected(true);
        
        // Layout and main panel
        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.add(originalPanel);
        main.add(sortedPanel);
        main.add(performPanel);
        main.add(radioPanel);
        add(main);
    } // end of GUI Creation

    // Main method
    public static void main(String[] args) {
        P3GUI gui = new P3GUI();
    } // end of main method
} // end of class
