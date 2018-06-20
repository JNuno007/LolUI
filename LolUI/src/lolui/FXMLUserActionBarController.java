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
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Line;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author joaoc
 */
public class FXMLUserActionBarController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    //Id's das imagens
    @FXML
    private ImageView imgTournament;
    
    @FXML
    private ImageView imgTeams;
            
    @FXML
    private ImageView imgPlayer;
    
    @FXML
    private ImageView imgAdmin;
    
    
    //Textos das imagens
    @FXML
    private Label lblTournament;
    
    @FXML
    private Label lblTeams;
    
    @FXML
    private Label lblPlayer;
            
    @FXML
    private Label lblAdmin;
            
    @FXML
    private Line line;
    
    @FXML
    private BorderPane mainPane;
    
    @FXML
    private GridPane currentGrid;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ScaleTransition st = new ScaleTransition();
        
        fadeInAnimation();
        
        st.setNode(line);
        st.setDuration(Duration.seconds(1));
        st.setToX(-1500);
        st.play();
    } 
    
    public void fadeInAnimation(){
        
        FadeTransition ft = new FadeTransition(Duration.millis(1000),imgTournament);
        ft.setFromValue(0.1);
        ft.setToValue(1.0);
        ft.play();
        
        ft = new FadeTransition(Duration.millis(1000),imgTeams);
        ft.setFromValue(0.1);
        ft.setToValue(1.0);
        ft.play();
        
        ft = new FadeTransition(Duration.millis(1000),imgPlayer);
        ft.setFromValue(0.1);
        ft.setToValue(1.0);
        ft.play();
        
        ft = new FadeTransition(Duration.millis(1000),imgAdmin);
        ft.setFromValue(0.1);
        ft.setToValue(1.0);
        ft.play();
        
        ft = new FadeTransition(Duration.millis(1000),lblTournament);
        ft.setFromValue(0.1);
        ft.setToValue(1.0);
        ft.play();
        
        ft = new FadeTransition(Duration.millis(1000),lblTeams);
        ft.setFromValue(0.1);
        ft.setToValue(1.0);
        ft.play();
        
        ft = new FadeTransition(Duration.millis(1000),lblPlayer);
        ft.setFromValue(0.1);
        ft.setToValue(1.0);
        ft.play();
        
        ft = new FadeTransition(Duration.millis(1000),lblAdmin);
        ft.setFromValue(0.1);
        ft.setToValue(1.0);
        ft.play();
        
        ft.setOnFinished((event) -> {
        try {
            currentGrid = FXMLLoader.load(getClass().getResource("FXMLTournamentMain.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLUserActionBarController.class.getName()).log(Level.SEVERE, null, ex);
        }
            mainPane.setCenter(currentGrid);
        });
    }
    
    
    public void btnTeamsClicked(){
        try {
            currentGrid = FXMLLoader.load(getClass().getResource("FXMLTeamsMain.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLUserActionBarController.class.getName()).log(Level.SEVERE, null, ex);
        }
        mainPane.setCenter(currentGrid);
    }
    
    public void btnPlayersClicked(){
        try {
            currentGrid = FXMLLoader.load(getClass().getResource("FXMLPlayersMain.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLUserActionBarController.class.getName()).log(Level.SEVERE, null, ex);
        }
        mainPane.setCenter(currentGrid);
    }
    
//    public void btnAdminClicked(){
//        this.imgAdmin.setOnMouseClicked((event) -> {
//            try {
//                currentGrid = FXMLLoader.load(getClass().getResource("FXMLAdminMain.fxml"));
//            } catch (IOException ex) {
//                Logger.getLogger(FXMLUserActionBarController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            mainPane.setCenter(currentGrid);
//        });
//    }
    
    public void btnTournamentsClicked(){
        try {
            currentGrid = FXMLLoader.load(getClass().getResource("FXMLTournamentMain.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLUserActionBarController.class.getName()).log(Level.SEVERE, null, ex);
        }
        mainPane.setCenter(currentGrid);
    }
    
    @FXML public void handleImageAction(MouseEvent event) throws IOException{
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLLogin.fxml"));
        Parent root = loader.load();
        FXMLLoginController controller = loader.getController();
        this.prepareStage(root);
    }
    
    public void prepareStage(Parent root){
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.setY(350);
        stage.setX(700);
        stage.getIcons().add(new Image(LolUI.class.getResourceAsStream("pics/lol.png")));
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(this.imgAdmin.getScene().getWindow());
        stage.showAndWait();
    }
    
    public void carregaPainelAdmin(){
        System.out.println("NO METODO carregaPainelAdmin");
        try {
            currentGrid = FXMLLoader.load(getClass().getResource("FXMLAdminMain.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLUserActionBarController.class.getName()).log(Level.SEVERE, null, ex);
        }
        mainPane.setCenter(currentGrid);
    }
}
