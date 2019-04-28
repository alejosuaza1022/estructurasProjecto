/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectstructures;

import entities.BinaryTree;
import entities.Trees;
import entities.Url;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author asb1022
 */
public class AñadirURLController implements Initializable {

    Trees tree = Trees.InstanceTress;
    /**
     * Initializes the controller class.
     */
    @FXML
    Button btnAddUrl;
    @FXML 
    TextField txtURl;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
    }    
    
}
