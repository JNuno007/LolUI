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
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
    
    //CREATE NEW TEAM
    @FXML public void createTeamAction(MouseEvent event) throws IOException{
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLCreateTeam.fxml"));
        Parent root = loader.load();
        FXMLCreateTeamController controller = loader.getController();
        //this.preparePopUpElements(event, controller);
        //Metodo para preencher a Janela de PopUp
        this.prepareCreateTeamStage(root);
    }
    
    public void prepareCreateTeamStage(Parent root){
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.setY(300);
        stage.setX(200);
        stage.getIcons().add(new Image(LolUI.class.getResourceAsStream("pics/lol.png")));
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(this.teamMain.getScene().getWindow());
        stage.showAndWait();
    }

//    private void prepareCreateTeamElements(MouseEvent event, FXMLPopUpTeamController controller) {
//        //TODO
//    }
    
    // ---------------------------------------------------------------------------------------------
    
    //MANAGE INFO TEAM
    @FXML public void manageInfoTeamAction(MouseEvent event) throws IOException{
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLManageInfoTeam.fxml"));
        Parent root = loader.load();
        FXMLManageInfoTeamController controller = loader.getController();
        //this.prepareManageInfoTeamElements(event, controller);
        //Metodo para preencher a Janela de PopUp
        this.prepareManageInfoTeamStage(root);
    }
    
    public void prepareManageInfoTeamStage(Parent root){
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.setY(300);
        stage.setX(200);
        stage.getIcons().add(new Image(LolUI.class.getResourceAsStream("pics/lol.png")));
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(this.teamMain.getScene().getWindow());
        stage.showAndWait();
    }

//    private void prepareManageInfoTeamElements(MouseEvent event, FXMLPopUpTeamController controller) {
//        //TODO
//    }
    
    // -----------------------------------------------------------------------------------------------
    
    //CREATE NEW TOURNAMENT
    
     @FXML public void createTournamentAction(MouseEvent event) throws IOException{
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLCreateNewTournament.fxml"));
        Parent root = loader.load();
        FXMLCreateNewTournamentController controller = loader.getController();
        //this.prepareCreateTournamenElements(event, controller);
        //Metodo para preencher a Janela de PopUp
        this.prepareCreateTournamenStage(root);
    }
    
    public void prepareCreateTournamenStage(Parent root){
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.setY(300);
        stage.setX(200);
        stage.getIcons().add(new Image(LolUI.class.getResourceAsStream("pics/lol.png")));
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(this.teamMain.getScene().getWindow());
        stage.showAndWait();
    }

//    private void prepareCreateTournamenElements(MouseEvent event, FXMLPopUpTeamController controller) {
//        //TODO
//    }
    
    //MANAGE INFO TOURNAMENT
    
    @FXML public void manageInfoTournamentAction(MouseEvent event) throws IOException{
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLManageInfoTournament.fxml"));
        Parent root = loader.load();
        FXMLManageInfoTournamentController controller = loader.getController();
        //this.prepareManageInfoTournamenElements(event, controller);
        //Metodo para preencher a Janela de PopUp
        this.prepareCreateTournamenStage(root);
    }
    
    public void prepareManageInfoTournamenStage(Parent root){
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.setY(300);
        stage.setX(200);
        stage.getIcons().add(new Image(LolUI.class.getResourceAsStream("pics/lol.png")));
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(this.teamMain.getScene().getWindow());
        stage.showAndWait();
    }

//    private void prepareManageInfoTournamenElements(MouseEvent event, FXMLPopUpTeamController controller) {
//        //TODO
//    }
    
    //MANAGE TOURNAMENT MATCHES
    
    @FXML public void manageMatchTournamentAction(MouseEvent event) throws IOException{
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLManageMatchTournament.fxml"));
        Parent root = loader.load();
        FXMLManageMatchTournamentController controller = loader.getController();
        //this.prepareManageMatchTournamenElements(event, controller);
        //Metodo para preencher a Janela de PopUp
        this.prepareManageMatchTournamenStage(root);
    }
    
    public void prepareManageMatchTournamenStage(Parent root){
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.setY(300);
        stage.setX(200);
        stage.getIcons().add(new Image(LolUI.class.getResourceAsStream("pics/lol.png")));
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(this.teamMain.getScene().getWindow());
        stage.showAndWait();
    }

//    private void prepareManageMatchTournamenElements(MouseEvent event, FXMLPopUpTeamController controller) {
//        //TODO
//    }
    
    // ----------------------------------------------------------------------------
    
    // CREATE NEW MEMBER
    
    @FXML public void createMemberAction(MouseEvent event) throws IOException{
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLCreateNewMember.fxml"));
        Parent root = loader.load();
        FXMLCreateNewMemberController controller = loader.getController();
        //this.prepareCreateMemberElements(event, controller);
        //Metodo para preencher a Janela de PopUp
        this.prepareCreateMemberStage(root);
    }
    
    public void prepareCreateMemberStage(Parent root){
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.setY(300);
        stage.setX(200);
        stage.getIcons().add(new Image(LolUI.class.getResourceAsStream("pics/lol.png")));
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(this.teamMain.getScene().getWindow());
        stage.showAndWait();
    }

//    private void prepareCreateMemberElements(MouseEvent event, FXMLPopUpTeamController controller) {
//        //TODO
//    }
    
    //MANAGE INFO MEMBER
    
    @FXML public void manageInfoMemberAction(MouseEvent event) throws IOException{
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLManageInfoMember.fxml"));
        Parent root = loader.load();
        FXMLManageInfoMemberController controller = loader.getController();
        //this.prepareManageInfoMemberElements(event, controller);
        //Metodo para preencher a Janela de PopUp
        this.prepareManageInfoMemberStage(root);
    }
    
    public void prepareManageInfoMemberStage(Parent root){
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.setY(300);
        stage.setX(200);
        stage.getIcons().add(new Image(LolUI.class.getResourceAsStream("pics/lol.png")));
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(this.teamMain.getScene().getWindow());
        stage.showAndWait();
    }

//    private void prepareManageInfoMemberElements(MouseEvent event, FXMLPopUpTeamController controller) {
//        //TODO
//    }
    
}
