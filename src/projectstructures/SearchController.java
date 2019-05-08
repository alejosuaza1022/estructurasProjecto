/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectstructures;

import entities.Trees;
import entities.Url;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author s105e11
 */
public class SearchController implements Initializable {
    
    private ObservableList<Url> observable;

    @FXML
    private Button btnSearchTag;
    @FXML
    private Button btnSearchUrl;
    @FXML
    private TextField txtSearch;
    @FXML
    private ListView<Url> listResult;
    @FXML
    private VBox searchWindow;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void searchByTag(MouseEvent event) {
        String toSearch = txtSearch.getText();
        String[] keyWords = toSearch.split(" ");
        ArrayList<Url> searchResult = Trees.getInstance().searchByKeyWord(keyWords);
        observable = FXCollections.observableArrayList(searchResult);
        listResult.setItems(observable);
    }

    @FXML
    private void searchByUrl(MouseEvent event) {
        String toSearch = "www." + txtSearch.getText();
        ArrayList<Url> searchResult = Trees.getInstance().searchByUrl(toSearch);
        observable = FXCollections.observableArrayList(searchResult);
        listResult.setItems(observable);
    }

    @FXML
    private void urlClicked(MouseEvent event) throws IOException {
        Url urlClicked = listResult.getSelectionModel().getSelectedItem();
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("urlWindow.fxml"));
        Parent urlWindow = loader.load();
        
        Scene urlScene = new Scene(urlWindow);
        UrlWindowController controler = loader.getController();
        controler.initData(urlClicked);
        BorderPane parent = (BorderPane) searchWindow.getParent();
        parent.setCenter(urlWindow);
    }
}
