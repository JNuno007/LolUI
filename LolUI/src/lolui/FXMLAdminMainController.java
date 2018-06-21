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
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author joaoc
 */
public class FXMLAdminMainController implements Initializable {

    @FXML private GridPane gridpane;
    
     //AnchorPanes
    
    @FXML private AnchorPane teamMain;
    @FXML private AnchorPane playerMain;
    @FXML private AnchorPane tournamentMain;
    @FXML private AnchorPane teamOptions;
    @FXML private AnchorPane playerOptions;
    @FXML private AnchorPane tournamentOptions;
       
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for (Node node : gridpane.getChildren()) {
            if(node.isVisible()){
                node.setEffect(new GaussianBlur());
                node.setOnMouseEntered((MouseEvent t) -> {
                    node.setEffect(null);
                    node.setCursor(Cursor.HAND);
                });
                node.setOnMouseExited((MouseEvent t) -> {
                    node.setEffect(new GaussianBlur());
                });
            }
        }
    } 
    
    @FXML public void teamMainClick(){
        teamMain.setVisible(false);
        playerMain.setVisible(true);
        tournamentMain.setVisible(true);
        teamOptions.setVisible(true);
        playerOptions.setVisible(false);
        tournamentOptions.setVisible(false);
    }
    
    @FXML public void playerMainClick(){
        teamMain.setVisible(true);
        playerMain.setVisible(false);
        tournamentMain.setVisible(true);
        teamOptions.setVisible(false);
        playerOptions.setVisible(true);
        tournamentOptions.setVisible(false);
    }
    
    @FXML public void tournamentMainClick(){
        teamMain.setVisible(true);
        playerMain.setVisible(true);
        tournamentMain.setVisible(false);
        teamOptions.setVisible(false);
        playerOptions.setVisible(false);
        tournamentOptions.setVisible(true);
    }
}
