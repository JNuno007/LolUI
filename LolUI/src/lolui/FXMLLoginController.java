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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author vaaz
 */
public class FXMLLoginController implements Initializable {

    
    @FXML private ImageView imgBack;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML public void closePopUp(MouseEvent event){
        Stage stage = (Stage) this.imgBack.getScene().getWindow();
        stage.close();
    }
    
}
