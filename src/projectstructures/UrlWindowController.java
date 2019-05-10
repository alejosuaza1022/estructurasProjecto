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
import java.util.ArrayList;
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
    private ArrayList<KeyWord> keyWords;
    private ObservableList<KeyWord> observable; 

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
        this.url = url;
        keyWords = Trees.getInstance().getKeyWordList();
        lblUrl.setText(url.toString());
        TextFields.bindAutoCompletion(txtKeyWord, keyWords);
        observable = FXCollections.observableArrayList(url.getKeyWords());
        listKeyWords.setItems(observable);
        
    }

    @FXML
    private void deleteURL(MouseEvent event) {
    }

    @FXML
    private void addKeyWord(MouseEvent event) {
        String stringKeyWord = txtKeyWord.getText();
        if(keyWords.contains(new KeyWord(stringKeyWord))) {
            KeyWord oldKeyWord = keyWords.get(keyWords.indexOf(new KeyWord(stringKeyWord)));
            url.AddKeyWord(oldKeyWord);
            oldKeyWord.AddUrl(url);
        } else {
            KeyWord newKeyWord = new KeyWord(stringKeyWord);
            newKeyWord.AddUrl(url);
            url.AddKeyWord(newKeyWord);
            Trees.getInstance().addKeyWord(newKeyWord);
        }
        observable.setAll(url.getKeyWords());
    }
    
  
}
