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
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import lolbll.SpellServices;

/**
 *
 * @author joaoc
 */
public class FXMLMainScreenController implements Initializable {

    @FXML
    private Button btn;

    @FXML
    private Label label;

    @FXML
    private ImageView logo;

    Scene scene;
    
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        //loadMainScreen(event);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SpellServices.getListaSpells();
        
        TranslateTransition transition = new TranslateTransition();
        ScaleTransition st = new ScaleTransition();

        //mover o logo para cima
        transition.setDuration(Duration.seconds(1));
        //transition.setNode(logo);
        transition.setToY(-365);
        transition.setToX(10);

        //    transition.play();      
        //dar scale negativa ao logo
        st.setDuration(Duration.seconds(1));
        st.setByX(-0.5f);
        st.setByY(-0.5f);

        
        
//        this.logo.setOnMouseClicked((event) -> {
            ParallelTransition pt = new ParallelTransition(logo, transition, st);
            pt.play();
            pt.setOnFinished((ActionEvent evento) -> {
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("FXMLUserActionBar.fxml"));
                } catch (IOException ex) {
                    Logger.getLogger(FXMLMainScreenController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Scene scene = new Scene(root);
                Stage stage = (Stage) logo.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            });
//        });
        
        
//        transition.setAutoReverse(true);
//        transition.setCycleCount(2);

    }

}
