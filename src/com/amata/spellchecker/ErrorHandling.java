/**
 * Class designed to handle input validation and error handling of user inputs.
 */
package com.amata.spellchecker;
import java.util.InputMismatchException;
import java.util.Scanner;
public class ErrorHandling {
    private String strInput;
    private int strLength;
    protected ErrorHandling(String word){
        strInput = word.trim();
    }
     /**
     * Method that checks for the validity of user inputs.
     * @return true if user input is valid
     */
    protected boolean isValidInput(){
        try {
            String[] testCase = strInput.split("\\s+"); //one or more whitespace characters
            if(testCase.length != 1){ //if na split siya into two words or what, di siya valid input. one word lang dapat.
                System.out.println(testCase.length);
                return false;
            }
            else if(!(strInput.charAt(0) == 'a'))
                return false;
            else if(hasNumeric())
                return false;
            else if(strInput.equals("")){
                return false; //if user input is purely numbers/digits only, return false
            }
            strLength = strInput.length();
            return true;
        } catch (Exception e) {
                 System.out.println("Invalid Input. Try again.");
            return false;
        }    
    }
    /**
     * Method that checks if user input has numeric characters with it.
     * @return true - if user input has numeric characters; else - false
     */
    private boolean hasNumeric(){
        if(strInput.matches(".*\\d+.*")) //Type checking ^ \\d wildcard character for digits (regex)
            return true;
        else
            return false;
    }
    /**
     * Method to return the validated strInput of user.
     * @return validated string input of user.
     */
    protected String getStrInput(){          
        return strInput; 
    }
    /**
     * Method that gets the length of strInput of user.
     * @return  length of strInput
     */
    protected int getStrLength(){
        return strLength;
    }
}
