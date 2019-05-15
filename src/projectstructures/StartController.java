/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectstructures;

import arbolBB.Vistaa;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Steven
 */
public class StartController implements Initializable {
    
    private double x, y;
    private final String IDLE_BUTTON_STYLE = "-fx-background-color: transparent; -fx-text-fill: #f0f0f0;";
    private final String HOVER_BUTTON_STYLE = "-fx-background-color: #f9bb04; -fx-text-fill: #000000";
    private final String PRESSED_BUTTON_STYLE = "-fx-background-color: #505050; -fx-text-fill: #f0f0f0";

    @FXML
    private ImageView btnClose;
    @FXML
    private Button btnSearch;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnTree;
    @FXML
    private BorderPane startMenu;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void close(MouseEvent event) {
        Stage stage = (Stage) startMenu.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void search(MouseEvent event) {
        loadUI("search");
    }

    @FXML
    private void add(MouseEvent event) {
        loadUI("add");
    }

    @FXML
    private void tree(MouseEvent event) {
        int seleccion = JOptionPane.showOptionDialog(null," ¿Cuál arbol desea ver? ", "elección de arbol",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,null, 
                new String[] { "UrlTree", "KeyWordTree" }, 0);
        System.out.println(seleccion);
//        int seleccion = JOptionPane.showOptionDialog(null,
//       JOptionPane.YES_NO_CANCEL_OPTION,
//       JOptionPane.QUESTION_MESSAGE,
//        
//   new Object[] { "opcion 1", "opcion 2", "opcion 3" },   // null para YES, NO y CANCEL
//   "opcion 1");
//        JOptionPane.showConfirmDialog(parentComponent, event, IDLE_BUTTON_STYLE, 0)
        Vistaa c = new Vistaa(0);
        c.show();
        
    }

    @FXML
    private void toolBarDragged(MouseEvent event) {
        Stage stage = (Stage) startMenu.getScene().getWindow();
        stage.setX(event.getScreenX() - x);
        stage.setY(event.getScreenY() - y);
    }

    @FXML
    private void toolBarPressed(MouseEvent event) {
        x = event.getSceneX();
        y = event.getSceneY();
    }

    @FXML
    private void btnHoverOff(MouseEvent event) {
        Button button = (Button) event.getSource();
        button.setStyle(IDLE_BUTTON_STYLE);
    }

    @FXML
    private void btnHoverOn(MouseEvent event) {
        Button button = (Button) event.getSource();
        button.setStyle(HOVER_BUTTON_STYLE);
    }

    @FXML
    private void btnPressed(MouseEvent event) {
        Button button = (Button) event.getSource();
        button.setStyle(PRESSED_BUTTON_STYLE);
    }

    @FXML
    private void btnRealeased(MouseEvent event) {
        Button button = (Button) event.getSource();
        button.setStyle(HOVER_BUTTON_STYLE);
    }
    
    private void loadUI(String ui) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(ui+".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(StartController.class.getName()).log(Level.SEVERE, null, ex);
        }
        startMenu.setCenter(root);
    }
}
