/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectstructures;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;

/**
 *
 * @author asb1022
 */
public class FXMLDocumentController implements Initializable {

    Parent root;
    @FXML
    private TextField textField;
    @FXML
    private Button button;

  
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        button.setOnAction((event) -> {
            try {
                this.root = FXMLLoader.load(getClass().getResource("añadirURL.fxml"));
            }
            catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Scene scence = new Scene(root);
            Stage window  = (Stage)((Node)event.getSource()).getScene( ).getWindow();
            window.setScene(scence);
            window.show();
        });

    }

}
