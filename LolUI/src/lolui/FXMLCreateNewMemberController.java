/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lolui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author vaaz
 */
public class FXMLCreateNewMemberController implements Initializable {

    @FXML private ImageView imgBack;
    
    @FXML private BorderPane parentBorderPane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        parentBorderPane.getStyleClass().add("borderPane");
    }  
    
    @FXML public void closePopUp(){
        Stage stage = (Stage) this.imgBack.getScene().getWindow();
        stage.close();
    }
    
    //CREATE NEW TEAM
    @FXML public void createCountryAction(MouseEvent event) throws IOException{
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLCountrySelection.fxml"));
        Parent root = loader.load();
        FXMLCountrySelectionController controller = loader.getController();
        controller.preencheGridPaises();
        //this.preparePopUpElements(event, controller);
        //Metodo para preencher a Janela de PopUp
        this.prepareCountryStage(root);
    }
    
    public void prepareCountryStage(Parent root){
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.setY(350);
        stage.setX(650);
        stage.getIcons().add(new Image(LolUI.class.getResourceAsStream("pics/lol.png")));
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(this.imgBack.getScene().getWindow());
        stage.showAndWait();
    }

//    private void prepareCreateTeamElements(MouseEvent event, FXMLPopUpTeamController controller) {
//        //TODO
//    }
    
}
