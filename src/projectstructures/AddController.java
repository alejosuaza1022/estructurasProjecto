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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author s105e11
 */
public class AddController implements Initializable {

    @FXML
    private Button btnAdd;
    @FXML
    private TextField txtUrl;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btnAdd.setOnKeyTyped((event) -> {
            String toComplete = txtUrl.getText();
            System.out.println(toComplete);
            if(!toComplete.startsWith("www.")){
                toComplete = "www."+toComplete;
            }
            txtUrl.setText(toComplete);
        });
    }    

    @FXML
    private void addUrl(MouseEvent event) {
        String url = txtUrl.getText();
        Url newUrl = new Url(url);
        BinaryTree<Url> urls = Trees.getInstance().getUrlTree();
        if(urls.getItem(newUrl) == null)
            urls.insert(newUrl);
         txtUrl.setText("www.");
        //Mandar a la ventana de la url para editar las palabras clave.
    }

    @FXML
    private void addWWW(KeyEvent event) {
        String toComplete = txtUrl.getText();
        if(!toComplete.startsWith("www.")){
            txtUrl.setText("");
            toComplete = "www.";
            txtUrl.setText(toComplete);
        }
    }
    
}
