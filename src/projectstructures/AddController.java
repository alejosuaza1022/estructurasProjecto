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
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import entities.KeyWord;
import java.util.TreeSet;
import java.util.stream.Stream;
import javax.swing.JOptionPane;
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
    @FXML
    private Button btnAddC;
    @FXML
    private TextField txtCant;

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
        String url = txtUrl.getText().trim();
        if(!url.equals("www.")){
            Url newUrl = new Url(url);
            BinaryTree<Url> urls = Trees.getInstance().getUrlTree();
            if(urls.insert(newUrl)) JOptionPane.showMessageDialog(null, " agregado ");
            txtUrl.setText("www.");
        }
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
        
      private void generateUrlAndKeyWords(){
        Random rm = new Random();
      
        Long numRept = Long.parseLong(txtCant.getText());
        BinaryTree<Url> urls = Trees.getInstance().getUrlTree();
        Trees keyWords = Trees.getInstance();
        Stream.iterate(0, x -> x+1).limit(numRept).forEach(x -> {
            String url = "";
            String keyW = "";
            int numCaracUrl = rm.nextInt(4) + 5;
            int numCaracKW = rm.nextInt(4) + 5;
            for (int j = 0; j < numCaracUrl; j++) {
                url += (char)( rm.nextInt(26) + 97);
                keyW += (char)( rm.nextInt(58) + 65);
                //if(j < numCaracKW) keyW += (char)( rm.nextInt(58) + 65);
            }
            keyWords.addKeyWord(new KeyWord(keyW));
            urls.insert(new Url("www." + url + ".com"));
        });
      }
      
      private void addKeyWToUrl(){
           ArrayList<KeyWord> keyWords = Trees.getInstance().getKeyWordList();
           BinaryTree<Url> urls = Trees.getInstance().getUrlTree();
          for (Url url : urls) {
              int numKeyWordsByUrl = new Random().nextInt(10);
              Stream.iterate(0, x -> x+1).limit(numKeyWordsByUrl).forEach(x -> {
                    KeyWord key = keyWords.get(new Random().nextInt(keyWords.size()));
                    url.AddKeyWord(key);
                    key.addUrl(url);
              });
          }
      }

    @FXML
    private void createRandomUrl(MouseEvent event) {
       if(txtCant.getText().trim() != ""){
            generateUrlAndKeyWords();
            addKeyWToUrl();
       }
        
    }
    
    
}
