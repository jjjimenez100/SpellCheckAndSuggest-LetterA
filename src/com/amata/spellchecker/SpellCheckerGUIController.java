/*
 *  Class that contains all scripts or methods to be invoked when any action is performed within the GUI itself. (FXML FILE).
 *  This method serves as the controller class, the fxml serves as the view class, and the SpellCheckerfunction class serves as the model class.
 *  (MVC Design)
 *  This class will not handle any data processing and instead, will invoke methods of the spell checker function to process data.
*/
package com.amata.spellchecker;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 */
public class SpellCheckerGUIController{
  @FXML
  private TextField inputField;
  @FXML //annotations to let the fxml identify these instances despite of them being private.
  private Button checkSpellingBtn;
  @FXML
  private Button resetBtn;
  @FXML
  private ListView suggestionsView;
  @FXML
  private Label statusLbl;
  @FXML
  private SpellCheckerFunctions function;
  @FXML
  private MenuItem close;
  @FXML
  private MenuItem about;
  
  /**
   * Field used for user input
   */
  @FXML
  private void onActionResetBtn(){
      inputField.clear();
      statusLbl.setText("");
      suggestionsView.getItems().clear(); 
  }
  /**
   * Statements to be executed on action performed at check spelling button
   * Creates an object of the ErrorHandling class and SpellCheckerFunctions class, passing any data processing logic to them.
   * Displays the word “Correct Spelling” if the word inputted by the user is correct
   */
  @FXML
  private void onActionCheckSpellingBtn(){ 
      ErrorHandling validation = new ErrorHandling(inputField.getText());
      function = new 
        SpellCheckerFunctions("words.txt"); //exact location of database
      suggestionsView.getItems().clear();
      if(validation.isValidInput()){
          if(function.searchDatabase(validation.getStrInput())){
              statusLbl.setText("Correct Spelling!"); 
          }
          else if(validation.getStrLength() == 5){
              function.checkSpelling(2, validation.getStrInput());
              statusLbl.setText("Incorrect Spelling!");
          }
          else if(validation.getStrLength()<5 && validation.getStrLength()>1){
              function.checkSpelling(3, validation.getStrInput());
              statusLbl.setText("Incorrect Spelling!");
          }
          else{
              statusLbl.setText("Too long.");
          }  
          fillSuggestionsView();
      }
      else{
          statusLbl.setText("Invalid Input.");
      }
  }
  /**
   * Fills in the listview GUI of fxml with the list of suggestions coming from SpellCheckerFunctions.
   */
  @FXML
  private void fillSuggestionsView(){
      if(!function.isEmpty())
        suggestionsView.getItems().addAll(function.getSuggestions());
      else
          suggestionsView.getItems().add("No suggestions available.");
  }
  /**
   * Statement that executes after performing any action on btnClose.
   */
  @FXML
  private void onActionClose(){
      Platform.exit();
      System.exit(0);
  }
  /**
   * Displays the manual and specification of the program
   */
  @FXML
  private void onActionAbout(){
      AboutDialogBox.displayAbout("Information", "Automata Theory and Formal Language.\n\nThis spell"
              + "checker is limited with the ff functionalities: \n 1. Only words that start with letter a can be spell checked"
              + "\n 2. Maximum of 5 letters in 1 word \n 3. Minimum of 1 letter \n 4. No non-character is allowed. \n 5. Type checking is applied.\n\n "
              + "Submitted by: \n\tJimenez // Sampang // Nulud // Castro // Feliciano \n\t CS-201 \n Submitted to: \n\t Ms. Nevada Salas");
  }
}
