/*
* File: MalformedFractionException.java
* Author: John Kucera
* Date: February 19, 2020
* Purpose: This java program is meant to accompany P3GUI.java. It is a user
* defined checked exception that handles situations where expression input
* includes malformed fractions - specifically, those that are not of the 
* #/# format, decimals excluded.
*/

// Constructor
public class MalformedFractionException extends Exception {
    public MalformedFractionException() {
        super();
    }
}
