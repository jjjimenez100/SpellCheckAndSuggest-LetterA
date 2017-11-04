/*
 * Class that contains all spell checking algorithms of the spell checker program.
 *
 */
package com.amata.spellchecker;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;
public class SpellCheckerFunctions {
    protected SpellCheckerFunctions(String filePath){
        this.filePath = filePath;
    }
    /**
     * Method that does a linear search on the text file database, looking for a match on the string word parameter.
     * @param word word to be searched and matched
     * @return returns true if a match is found, else false.
     */
    protected boolean searchDatabase(String word){
        Scanner databaseReader = null;
        try{
            databaseReader = new Scanner(new File(filePath)); //
        }
        catch(FileNotFoundException e){ //in case directory does not exist
            System.out.println("No such file in directory exists.");
        }
        while(databaseReader.hasNextLine()){
            if(word.equals(databaseReader.nextLine())) //check each letter on the .txt database file
                return true;    //if there's a match, return true
        }
        databaseReader.close(); //close the file stream after execution to prevent errors
        return false; //else return false
    }
    /**
     * Method that adds every letter on the alphabet to the word, then tries to permute the new word trying to find any matches with the database.
     * @param word word to be used for appending
     */
    private void appendLetter(String word){
        String holder;
        for(int index=0; index<alphabet.length; index++){
            holder = word;
            holder += alphabet[index];
            extractChars(holder); 
        }
    }
    /**
     * Method that replaces letters on each nth position of the word with every letter in the alphabet.
     * @param word string to be tested by replacing every nth index letter of the word with every letter in the alphabet.
     */
    private void replaceLetter(String word){
        String holder;                       
        int charPosition = word.length()-1;
        StringBuilder replacedLetter;
        for(; charPosition!=0; charPosition--){
            replacedLetter = new StringBuilder(word);
            for(int index=0; index<alphabet.length; index++){
                replacedLetter.setCharAt(charPosition, alphabet[index]);
                holder = replacedLetter.toString();
                if(searchDatabase(holder)){
                suggestedWords.add(holder);
            }
            }
        }
    }
    /**
     * Method that removes the last letter in the word.
     * @param word string to be tested
     */
    private void removeLetter(String word){
        word = word.substring(0, word.length()-1);
        if(searchDatabase(word)){
                addSuggestion(word);
            } //
    }
    /** Convert the word parameter to its char array equivalent. (Easier access to each nth index)
     * method that extract letters, get its permutation and checks database if there's a word match with it
     * @param word convert all the characters in a String object into a character array
     */
    private void extractChars(String word){
        char[] extractedChars = word.toCharArray();
        int wordLength = word.length();
        getPermutations(extractedChars, wordLength);  //call the main permutation method to get all possible permutations
    }
    /**
     * Method that calls the swap method to swap each letter of the word, and to get all possible permutations of the word.
     * @param extractedChars - the char array equivalent of the word being spell checked.
     * @param wordLength - gets the length of the word
     */
    private void getPermutations(char[] extractedChars, int wordLength){ 
        if(wordLength == 1){
            String holder = "";
            for(int index=0; index<extractedChars.length; index++){
                holder += extractedChars[index];
            }
            if(searchDatabase(holder)){
                    addSuggestion(holder); 
                }
            return;
        }
        for(int counter=0; counter<wordLength; counter++){
            swap(extractedChars, counter, wordLength-1); //swap two unswapped chars on the string
            getPermutations(extractedChars, wordLength-1); //recursively get the permutations of the swapped chars of the string
            swap(extractedChars, counter, wordLength-1); //backtrack or unswap the two
        }
    }
    /**
     * Method that swaps to the nth index of letters.
     * @param extractedChars - the char array equivalent of the word being spell checked.
     * @param indexSwap1 - index of letter to be swapped and check if there's a match in database
     * @param indexSwap2 - index of letter to be swapped and check if there's a match in database
     */
    private void swap(char[] extractedChars, int indexSwap1, int indexSwap2){
        char holder = extractedChars[indexSwap1];
        extractedChars[indexSwap1] = extractedChars[indexSwap2];
        extractedChars[indexSwap2] = holder;
    } 
    /**
     * Method that adds the suggestedWord parameter to the suggestedWords container.
     * @param suggestedWord word to be added so suggestedWords container.
     */
    private void addSuggestion(String suggestedWord){
        suggestedWords.add(suggestedWord);
    }
    /**
     * Method to return the container of suggestedWords.
     * @return - returns a SortedSet of suggested words
     */
    protected SortedSet<String> getSuggestions(){
        return suggestedWords;
    }
    /**
     * Checks if suggested words is empty
     * @return - returns true if the suggested word container is empty;  else false
     */
    protected boolean isEmpty(){
        if(suggestedWords.isEmpty())
            return true;
        else
            return false;
    }
    /**
     * Method to invoke the private spell checking algorithms of this class.
     * @param type varies upon usage; 2 for check spelling algorithms applicable for words with length equal to 5,
     * 3 for check spelling algorithms applicable for word with length less than 5 but greater than 1
     * @param word the word to be spell checked.
     */
    protected void checkSpelling(int type, String word){
        switch (type) {
            case 2:
                replaceLetter(word);
                removeLetter(word);
                extractChars(word);
                break;
            case 3:
                replaceLetter(word);
                appendLetter(word);
                removeLetter(word);
                extractChars(word);
                break;
            default:
                System.out.println("Program encountered an error.");
        }
    }
    /**
     * Creation of private sorted set data structure.
     * Prevents duplicates and sorts them alphabetically and by word length.
     * (Using our own implementation of compare)
     * 
     */
    private SortedSet<String> suggestedWords = new TreeSet<>(new Comparator<String>(){
       @Override
        public int compare(String firstWord, String secondWord) {  //implementing our own compare method
                if (firstWord.length() > secondWord.length()) { //to sort out words BY LENGTH
                   return -1; //descending, change both of them inversely to get ascending order of the tree set
                } else if (firstWord.length() < secondWord.length()) {
                   return 1;
                }
                return firstWord.compareTo(secondWord);
              }
        }
    );
    private String filePath;
    private char alphabet[] = "abcdefghijklmnopqrstuvwxyz".toCharArray();
}
