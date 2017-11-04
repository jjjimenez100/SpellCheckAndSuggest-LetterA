/*
 *   Main class to execute helper classes and the spellchecker program itself.
 */
package com.amata.spellchecker;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SpellCheckerMain extends Application {
       
    @Override 
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("SpellCheckerGUI.fxml")); //let the main class know which GUI should be linked to it    
        Scene scene = new Scene(root);
        
        stage.initStyle(StageStyle.UNDECORATED); //transparent view
        stage.setTitle("Spelling Checker");
        stage.setScene(scene);
        
        stage.show();
        AboutDialogBox.displayAbout("Information", "Automata Theory and Formal Language.\n\nThis spell"
              + "checker is limited with the ff functionalities: \n 1. Only words that start with letter a can be spell checked"
              + "\n 2. Maximum of 5 letters in 1 word \n 3. Minimum of 1 letter \n 4. No non-character is allowed. \n 5. Type checking is applied.\n\n "
              + "Submitted by: \n\tJimenez // Sampang // Nulud // Castro // Feliciano \n\t CS-201 \n Submitted to: \n\t Ms. Nevada Salas");
    } //pop out the specifications and limitations of this program to the end user

    public static void main(String[] args) {
        launch(args);
    }
    
}
