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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import loldal.model.Membroequipa;
import loldal.model.Pais;

/**
 * FXML Controller class
 *
 * @author joaoc
 */
public class FXMLCreateTeamController implements Initializable {

    @FXML private ImageView imgBack;
    
    @FXML private BorderPane parentBorderPane;
    
    @FXML private TextField txtUsername;
    
    @FXML private TextField txtInitials;
    
    @FXML private ImageView countrySelected;
    
    //FXML da grelha direita
    
    @FXML private Button btnTop; 
    
    @FXML private Button btnJng; 
    
    @FXML private Button btnMid; 
    
    @FXML private Button btnAdc; 
    
    @FXML private Button btnSup; 
    
    @FXML private Button btnCoach; 
    
    @FXML private ImageView imgTop;
    
    @FXML private ImageView imgJng;
    
    @FXML private ImageView imgMid;
    
    @FXML private ImageView imgAdc;
    
    @FXML private ImageView imgSup;
    
    @FXML private ImageView imgCoach;
    
    private Pais pais;
    
    private Membroequipa membro;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        parentBorderPane.getStyleClass().add("borderPane");
    }    
    
    @FXML public void closePopUp(){
        Stage stage = (Stage) this.imgBack.getScene().getWindow();
        stage.close();
    }
    
    @FXML public void createCountryAction(MouseEvent event) throws IOException{
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLCountrySelection.fxml"));
        Parent root = loader.load();
        FXMLCountrySelectionController controller = loader.getController();
        controller.preencheGridPaises();
        //this.preparePopUpElements(event, controller);
        //Metodo para preencher a Janela de PopUp
        this.prepareCountryStage(root, controller);
    }
    
    public void prepareCountryStage(Parent root, FXMLCountrySelectionController controller){
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
        if(controller.getPaisSelected()!=null){
            countrySelected.setImage(controller.getCountryImageSelected());
            this.pais = controller.getPaisSelected();
            System.out.println(this.pais.getNome());
        }
        
    }
    
    @FXML public void selectMemberAction(MouseEvent event) throws IOException{
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLPlayerSelection.fxml"));
        Parent root = loader.load();
        FXMLPlayerSelectionController controller = loader.getController();
        if(event.getSource() == btnTop){
            controller.preencheGridPane("TOP");
            controller.setPosicao("TOP");
        }
        if(event.getSource() == btnJng){
            controller.preencheGridPane("JNG");
            controller.setPosicao("JNG");
        }
        if(event.getSource() == btnMid){
            controller.preencheGridPane("MID");
            controller.setPosicao("MID");
        }
        if(event.getSource() == btnAdc){
            controller.preencheGridPane("ADC");
            controller.setPosicao("ADC");
        }
        if(event.getSource() == btnSup){
            controller.preencheGridPane("SUP");
            controller.setPosicao("SUP");
        }
        if(event.getSource() == btnCoach){
            controller.preencheGridPane("null");
            controller.setPosicao("null");
        }
        //Metodo para preencher a Janela de PopUp
        this.prepareSelectMemberStage(root, controller);
    }
    
    public void prepareSelectMemberStage(Parent root, FXMLPlayerSelectionController controller){
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
        //TODO
        if(controller.getMembroSelected()!=null){
            membro = controller.getMembroSelected();
            this.setImagefromMemberSelected(membro, controller);
        }
    }
    
    public void setImagefromMemberSelected(Membroequipa m, FXMLPlayerSelectionController controller){
        if(m.getPosicao()!=null && m.getPosicao().getSigla().equals("TOP")){
            imgTop.setImage(controller.getMemberImageSelected());
            btnTop.setText("Edit");
        }
        if(m.getPosicao()!=null && m.getPosicao().getSigla().equals("JNG")){
            imgJng.setImage(controller.getMemberImageSelected());
            btnJng.setText("Edit");
        }
        if(m.getPosicao()!=null && m.getPosicao().getSigla().equals("MID")){
            imgMid.setImage(controller.getMemberImageSelected());
            btnMid.setText("Edit");
        }
        if(m.getPosicao()!=null && m.getPosicao().getSigla().equals("ADC")){
            imgAdc.setImage(controller.getMemberImageSelected());
            btnAdc.setText("Edit");
        }
        if(m.getPosicao()!=null && m.getPosicao().getSigla().equals("SUP")){
            imgSup.setImage(controller.getMemberImageSelected());
            btnSup.setText("Edit");
        }
        if(m.getPosicao()==null){
            imgCoach.setImage(controller.getMemberImageSelected());
            btnCoach.setText("Edit");
        }
    }
    
}
