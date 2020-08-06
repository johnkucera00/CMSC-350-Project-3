/*
* File: Fraction.java
* Author: John Kucera
* Date: February 19, 2020
* Purpose: This Java program is meant to accompany P3GUI.java and is 
* responsible for holding the constructor for Fraction objects.
*/

// import of necessary java classes
import java.text.DecimalFormat;

// Fraction class
public class Fraction implements Comparable<Fraction> {
    
    // Instance Variables
    private double numer = 0.0;
    private double denom = 0.0;
    private double value = 0.0;
    
    // Fraction Constructor
    public Fraction(String fraction) throws MalformedFractionException {
        String[] toArray = fraction.split("/");
        // Ensure that fraction is composed of 2 numbers
        if (toArray.length != 2) { 
            throw new MalformedFractionException();
        }
        numer = Integer.parseInt(toArray[0]);
        denom = Integer.parseInt(toArray[1]);
        this.value = numer / denom;
    } // end of method
    
    // compareTo method
    public int compareTo(Fraction fraction) {
        if (this.value == fraction.getValue()) {
            return 0;
        }
        else if (this.value > fraction.value) {
            return 1;
        }
        else {
            return -1;
        } // end of else
    } // end of method
    
    // toString method
    public String toString() {
        DecimalFormat toDecimal = new DecimalFormat();
        String fracString = toDecimal.format(numer) + "/" + toDecimal.format(denom);
        return fracString;
    } // end of method
    
    // getValue method, returns double
    private Double getValue() {
        return this.value;
    } // end of method
} // end of class
