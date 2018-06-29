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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author joaoc
 */
public class FXMLMatchGameTournamentController implements Initializable {
    
    @FXML
    private ImageView imgBack;
    
    @FXML
    private BorderPane parentBorderPane;
    
    @FXML
    private GridPane gridPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        parentBorderPane.getStyleClass().add("borderPane");
        this.prepareActionsGrid();
    }
    
    @FXML
    public void closePopUp() {
        Stage stage = (Stage) this.imgBack.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    public void createItemAction(MouseEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLItemSelection.fxml"));
        Parent root = loader.load();
        FXMLItemSelectionController controller = loader.getController();
        controller.preencheGridItems();
        //Metodo para preencher a Janela de PopUp
        this.prepareItemStage(root, controller);
    }

    public void prepareItemStage(Parent root, FXMLItemSelectionController controller) {
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
//        if (controller.getItemSelected() != null) {
//            imgNewCountryPlayer.setImage(controller.getCountryImageSelected());
//            this.pais = controller.getPaisSelected();
//            System.out.println(this.pais.getNome());
//        }
    }
    
    @FXML
    public void createSpellAction(MouseEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLSpellSelection.fxml"));
        Parent root = loader.load();
        FXMLSpellSelectionController controller = loader.getController();
        controller.preencheGridSpells();
        //Metodo para preencher a Janela de PopUp
        this.prepareSpellStage(root, controller);
    }

    public void prepareSpellStage(Parent root, FXMLSpellSelectionController controller) {
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
//        if (controller.getItemSelected() != null) {
//            imgNewCountryPlayer.setImage(controller.getCountryImageSelected());
//            this.pais = controller.getPaisSelected();
//            System.out.println(this.pais.getNome());
//        }
    }
    
    @FXML
    public void createChampionAction(MouseEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLChampionSelection.fxml"));
        Parent root = loader.load();
        FXMLChampionSelectionController controller = loader.getController();
        controller.preencheGridChampions();
        //Metodo para preencher a Janela de PopUp
        this.prepareChampionStage(root, controller);
    }

    public void prepareChampionStage(Parent root, FXMLChampionSelectionController controller) {
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
//        if (controller.getItemSelected() != null) {
//            imgNewCountryPlayer.setImage(controller.getCountryImageSelected());
//            this.pais = controller.getPaisSelected();
//            System.out.println(this.pais.getNome());
//        }
    }
    
    @FXML
    public void createRunaAction(MouseEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLRunaSelection.fxml"));
        Parent root = loader.load();
        FXMLRunaSelectionController controller = loader.getController();
        controller.preencheGridRunas();
        //Metodo para preencher a Janela de PopUp
        this.prepareRunaStage(root, controller);
    }

    public void prepareRunaStage(Parent root, FXMLRunaSelectionController controller) {
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
//        if (controller.getItemSelected() != null) {
//            imgNewCountryPlayer.setImage(controller.getCountryImageSelected());
//            this.pais = controller.getPaisSelected();
//            System.out.println(this.pais.getNome());
//        }
    }
    
    public void prepareActionsGrid(){
        for(Node node: gridPane.getChildren()){
            System.out.println(GridPane.getColumnIndex(node));
            if(GridPane.getColumnIndex(node)!=null && GridPane.getRowIndex(node)!=null && GridPane.getColumnIndex(node)==4 && GridPane.getRowIndex(node)>0){
                node.setCursor(Cursor.HAND);
                node.setOnMouseClicked((event) -> {
                    try {
                        this.createItemAction(event);
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLMatchGameTournamentController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            }
            if(GridPane.getColumnIndex(node)!=null && GridPane.getRowIndex(node)!=null && GridPane.getColumnIndex(node)==6 && GridPane.getRowIndex(node)>0){
                node.setCursor(Cursor.HAND);
                node.setOnMouseClicked((event) -> {
                    try {
                        this.createSpellAction(event);
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLMatchGameTournamentController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            }
            if(GridPane.getColumnIndex(node)!=null && GridPane.getRowIndex(node)!=null && GridPane.getColumnIndex(node)==7 && GridPane.getRowIndex(node)>0){
                node.setCursor(Cursor.HAND);
                node.setOnMouseClicked((event) -> {
                    try {
                        this.createChampionAction(event);
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLMatchGameTournamentController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            }
            if(GridPane.getColumnIndex(node)!=null && GridPane.getRowIndex(node)!=null && GridPane.getColumnIndex(node)==8 && GridPane.getRowIndex(node)>0){
                node.setCursor(Cursor.HAND);
                node.setOnMouseClicked((event) -> {
                    try {
                        this.createRunaAction(event);
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLMatchGameTournamentController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            }
        }
    }

}
