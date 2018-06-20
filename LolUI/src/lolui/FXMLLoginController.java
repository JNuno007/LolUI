/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lolui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author vaaz
 */
public class FXMLLoginController implements Initializable {

    
    @FXML private ImageView imgBack;
    
    @FXML
    private BorderPane mainPane;
    
    @FXML
    private GridPane currentGrid;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML public void closePopUp(MouseEvent event){
        Stage stage = (Stage) this.imgBack.getScene().getWindow();
        stage.close();
    }
    
    @FXML public void loginSuccessefull(MouseEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLUserActionBar.fxml"));
        Parent root = loader.load();
        FXMLUserActionBarController controller = loader.getController();
        
        this.closePopUp(event);
        controller.carregaPainelAdmin();
    }
    
}
