/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectstructures;

import entities.KeyWord;
import entities.Trees;
import entities.Url;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author s105e11
 */
public class UrlWindowController implements Initializable {
    
    public Url url;

    @FXML
    private TextField txtKeyWord;
    @FXML
    private Button btnAdd;
    @FXML
    private ListView<KeyWord> listKeyWords;
    @FXML
    private Label lblUrl;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void itemSelected(MouseEvent event) {
        
    }
    
    public void initData(Url url) {
        lblUrl.setText(url.toString());
        
            TextFields.bindAutoCompletion(txtKeyWord, Trees.getInstance().getKeyWordList());
        ObservableList<KeyWord> observable = FXCollections.observableArrayList(url.getKeyWords());
        listKeyWords.setItems(observable);
        
    }
    
}
