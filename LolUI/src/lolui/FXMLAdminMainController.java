/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lolui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author joaoc
 */
public class FXMLAdminMainController implements Initializable {

    @FXML private GridPane gridpane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for (Node node : gridpane.getChildren()) {  
            node.setEffect(new GaussianBlur());
            node.setOnMouseEntered((MouseEvent t) -> {
                node.setEffect(null);
            });
            node.setOnMouseExited((MouseEvent t) -> {
                node.setEffect(new GaussianBlur());
            });
        }
    }    
}
