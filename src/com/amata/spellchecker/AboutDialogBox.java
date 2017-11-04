/*
 * Class that pop out an alert box / window to the user
 * displaying the technical specifications of the program.
 *
 */
package com.amata.spellchecker;

import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AboutDialogBox {
    
    public static void displayAbout(String title, String message){
        Stage mainStage = new Stage();
        
        mainStage.setResizable(false); //make alert box non-resizable
        mainStage.initStyle(StageStyle.UNDECORATED);
        mainStage.initModality(Modality.APPLICATION_MODAL); //prevent any actions on the stacked stage
        mainStage.setTitle(title);
        mainStage.setMinWidth(150);
        
        Label information = new Label(message);
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> { //add statements to be executed when action is performed at closeButton
            mainStage.close();
        });
        information.setStyle("-fx-font: 12 System;"); //a little bit of styling for the text 
        VBox layout = new VBox(10);
        layout.getChildren().addAll(information, closeButton);
        layout.setAlignment(Pos.CENTER); //fixed location of dialog box
        
        Scene aboutScene = new Scene(layout);
        mainStage.setScene(aboutScene);
        mainStage.show(); //stack the alert box stage to the main stage(spell checker program)
    }
}
