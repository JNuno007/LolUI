/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lolui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lolbll.EquipaServices;
import lolbll.MembroEquipaServices;
import loldal.model.Equipa;
import loldal.model.Membroequipa;
import loldal.model.Pais;

/**
 * FXML Controller class
 *
 * @author joaoc
 */
public class FXMLManageInfoTeamController implements Initializable {

    @FXML private ImageView imgBack;
    @FXML private ListView listViewEquipas;
    @FXML private ImageView previousTeamLogo;
    @FXML private ImageView previousCountry;
    @FXML private ImageView newTeamLogo;
    @FXML private ImageView newCountry;
    @FXML private ImageView removeTopImg;
    @FXML private ImageView removeJungleImg;
    @FXML private ImageView removeMidImg;
    @FXML private ImageView removeAdcImg;
    @FXML private ImageView removeSupportImg;
    @FXML private ImageView removeCoachImg;
    @FXML private ImageView changeCoachImg;
    @FXML private ImageView changeTopImg;
    @FXML private ImageView changeJunglerImg;
    @FXML private ImageView changeMidImg;
    @FXML private ImageView changeAdcImg;
    @FXML private ImageView changeSupportImg;
    @FXML private TextField newName;
    @FXML private TextField newInitials;
    @FXML private Label previousName;
    @FXML private Label previousInitials;
    @FXML private Label coachLbl;
    @FXML private Label topLbl;
    @FXML private Label junglerLbl;
    @FXML private Label midLbl;
    @FXML private Label adcLbl;
    @FXML private Label supportLbl;
    @FXML private Button btnSelectImage;

    private FileChooser fileChooser;
    private File fileImagem;
    private List<Equipa> listaPesquisa;
    private ObservableList<Equipa> equipasObs;
    private Equipa equipaAtual;
    private List<Membroequipa> listaMembrosAtuais;
    private List<Membroequipa> listaNovosMembros;
    private Pais pais;
    private Membroequipa membro;

    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.listarEquipas();
        this.atribuirElementos();
        
        btnSelectImage.setOnAction(
                new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent e) {
                fileChooser = new FileChooser();
                setExtFilters(fileChooser);
                fileImagem = fileChooser.showOpenDialog((Stage) previousName.getScene().getWindow());
                if (fileImagem != null) {
                    newTeamLogo.setImage(new Image(fileImagem.toURI().toString()));
                }
            }
        });
        
    }

    @FXML
    public void closePopUp() {
        Stage stage = (Stage) this.imgBack.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    public void createCountryAction(MouseEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLCountrySelection.fxml"));
        Parent root = loader.load();
        FXMLCountrySelectionController controller = loader.getController();
        controller.preencheGridPaises();
        this.prepareCountryStage(root, controller);
    }
    
    @FXML
    public void removeMemberAction(MouseEvent event) throws IOException {

        if (event.getSource() == this.removeTopImg) {
            removeTopImg.setVisible(false);
            this.removeMemberFromTempList("TOP");
            this.topLbl.setText("[Player not assigned]");
            
        }
        
        if (event.getSource() == this.removeJungleImg) {
            removeJungleImg.setVisible(false);
            this.removeMemberFromTempList("JNG");
            this.junglerLbl.setText("[Player not assigned]");
        }
        
        if (event.getSource() == this.removeMidImg) {
            removeMidImg.setVisible(false);
            this.removeMemberFromTempList("MID");
            this.midLbl.setText("[Player not assigned]");
        }
        
        if (event.getSource() == this.removeAdcImg) {
            removeAdcImg.setVisible(false);
            this.removeMemberFromTempList("ADC");
            this.adcLbl.setText("[Player not assigned]");
        }
        
        if (event.getSource() == this.removeSupportImg) {
            removeSupportImg.setVisible(false);
            this.removeMemberFromTempList("SUP");
            this.supportLbl.setText("[Player not assigned]");
        }
        
        if (event.getSource() == this.removeCoachImg) {
            removeCoachImg.setVisible(false);
            this.removeMemberFromTempList("COACH");
            this.coachLbl.setText("[Coach not assigned]");
        }
    }
    
    public void removeMemberFromTempList(String pos){
        System.out.println(this.listaNovosMembros.size());
        Iterator <Membroequipa> it = this.listaNovosMembros.iterator();
        while(it.hasNext()){
            Membroequipa me = it.next();
            
            if(me.getPosicao() == null){
                if(pos.equals("COACH")){
                   it.remove();
                   
                }
            }else{
                if(me.getPosicao().getSigla().toString().equals(pos)){
                    it.remove();
                }
            }
        }
        System.out.println(this.listaNovosMembros.size());
    }

    public void prepareCountryStage(Parent root, FXMLCountrySelectionController controller) {
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
        if (controller.getPaisSelected() != null) {
            newCountry.setImage(controller.getCountryImageSelected());
            this.pais = controller.getPaisSelected();
            System.out.println(this.pais.getNome());
        }
    }
    
    public void listarEquipas() {
        // -- CRIAR MÉTODO NA BLL
        listaPesquisa = EquipaServices.listaEquipas();
        listaPesquisa.sort(Comparator.comparing((equipa) -> equipa.getNome()));
        equipasObs = FXCollections.observableArrayList(listaPesquisa);
        this.listViewEquipas.setItems(equipasObs);
    }

     public void atribuirElementos() {
        this.listViewEquipas.getSelectionModel().selectedItemProperty().addListener((observable) -> {
            equipaAtual = (Equipa) listViewEquipas.getSelectionModel().getSelectedItem();
            if (equipaAtual != null) {
                //Atribuir Elementos Atuais
                this.pais = null;
                System.out.println("Pais: " + pais);
                this.atribuirElementosAnteriores(equipaAtual);
                this.atribuirNovosElementos(equipaAtual);
                this.setTeamMembers(equipaAtual);
            }
        });
    }
    public void atribuirElementosAnteriores(Equipa equipa){
        this.setPreviousTeamLogo(equipa);
        this.setPreviousTeamName(equipa);
        this.setPreviousTeamInitials(equipa);
        this.setPreviousTeamCountry(equipa);
    }
    
    public void atribuirNovosElementos(Equipa equipa){
        this.setNewTeamLogo(equipa);
        this.setNewName(equipa);
        this.setNewInitials(equipa);
        this.setNewCountry(equipa);
    }
    
    
    public void setPreviousTeamLogo(Equipa equipa){
        if (getClass().getResourceAsStream("pics/teams/" + equipa.getSigla().toLowerCase() + ".png") != null) {
            this.previousTeamLogo.setImage(new Image(FXMLPlayersMainController.class.getResourceAsStream("pics/teams/" + equipa.getSigla().toLowerCase() + ".png")));
        } else {
            this.previousTeamLogo.setImage(new Image(FXMLPlayersMainController.class.getResourceAsStream("pics/players/unknown.png")));
        }
    }
    
    public void setPreviousTeamName(Equipa equipa){
        this.previousName.setText(equipa.getNome());
    }
    
    public void setPreviousTeamInitials(Equipa equipa){
        this.previousInitials.setText(equipa.getSigla());
    }
    
    public void setPreviousTeamCountry(Equipa equipa){
        if (getClass().getResourceAsStream("pics/countries/" + equipa.getPais().getSigla().toLowerCase() + ".png") != null) {
            this.previousCountry.setImage(new Image(FXMLPlayersMainController.class.getResourceAsStream("pics/countries/" + equipa.getPais().getSigla().toLowerCase() + ".png")));
        } else {
            this.previousCountry.setImage(new Image(FXMLPlayersMainController.class.getResourceAsStream("pics/players/unknown.png")));
        }
    }
    
    public void setNewName(Equipa equipa){
        this.newName.setText(equipa.getNome());
    }
    
    public void setNewInitials(Equipa equipa){
        this.newInitials.setText(equipa.getSigla());
    }
    
    public void setNewCountry(Equipa equipa){
        if (getClass().getResourceAsStream("pics/countries/" + equipa.getPais().getSigla().toLowerCase() + ".png") != null) {
            this.newCountry.setImage(new Image(FXMLPlayersMainController.class.getResourceAsStream("pics/countries/" + equipa.getPais().getSigla().toLowerCase() + ".png")));
        } else {
            this.newCountry.setImage(new Image(FXMLPlayersMainController.class.getResourceAsStream("pics/players/unknown.png")));
        }
    }
    
    public void setNewTeamLogo(Equipa equipa){
        if (getClass().getResourceAsStream("pics/teams/" + equipa.getSigla().toLowerCase() + ".png") != null) {
            this.newTeamLogo.setImage(new Image(FXMLPlayersMainController.class.getResourceAsStream("pics/teams/" + equipa.getSigla().toLowerCase() + ".png")));
        } else {
            this.newTeamLogo.setImage(new Image(FXMLPlayersMainController.class.getResourceAsStream("pics/players/unknown.png")));
        }
    }
    
    public void setTeamMembers(Equipa equipa){
        
        boolean teamHasCoach = false;
        boolean teamHasTop = false;
        boolean teamHasJungler = false;
        boolean teamHasMid = false;
        boolean teamHasAdc = false;
        boolean teamHasSupport = false;
        
        this.listaNovosMembros = this.listaMembrosAtuais = MembroEquipaServices.getAllMembrosFromTeam(equipa);
        
        if(listaMembrosAtuais.size() == 6){
            for(Membroequipa me: listaMembrosAtuais){
                if(me.getPosicao() ==  null){
                    this.coachLbl.setText(me.getNome());
                    this.removeCoachImg.setVisible(true);
                }else{
                    if(me.getPosicao().getSigla().toString().equals("TOP")){
                        this.topLbl.setText(me.getNome());
                        this.removeTopImg.setVisible(true);
                    }
                    if(me.getPosicao().getSigla().toString().equals("JNG")){
                        this.junglerLbl.setText(me.getNome());
                        this.removeJungleImg.setVisible(true);
                    }
                    if(me.getPosicao().getSigla().toString().equals("MID")){
                        this.midLbl.setText(me.getNome());
                        this.removeMidImg.setVisible(true);
                    }
                    if(me.getPosicao().getSigla().toString().equals("ADC")){
                        this.adcLbl.setText(me.getNome());
                        this.removeAdcImg.setVisible(true);
                    }
                    if(me.getPosicao().getSigla().toString().equals("SUP")){
                        this.supportLbl.setText(me.getNome());
                        this.removeSupportImg.setVisible(true);
                    }
                }
            }
        }else{
            if(listaMembrosAtuais.isEmpty()){
                this.removeCoachImg.setVisible(false);
                this.removeTopImg.setVisible(false);
                this.removeJungleImg.setVisible(false);
                this.removeMidImg.setVisible(false);
                this.removeAdcImg.setVisible(false);
                this.removeSupportImg.setVisible(false);
                this.coachLbl.setText("[Coach not assigned]");
                this.topLbl.setText("[Player not assigned]");
                this.junglerLbl.setText("[Player not assigned]");
                this.midLbl.setText("[Player not assigned]");
                this.adcLbl.setText("[Player not assigned]");
                this.supportLbl.setText("[Player not assigned]");
            }else{
                for(Membroequipa me: listaMembrosAtuais){
                    if(me.getPosicao() == null){
                        this.coachLbl.setText(me.getNome());
                        this.removeCoachImg.setVisible(true);
                        teamHasCoach = true;
                    }else{
                        if(me.getPosicao().getSigla().equals("TOP")){
                            this.topLbl.setText(me.getNome());
                            this.removeTopImg.setVisible(true);
                            teamHasTop = true;
                        }
                        if(me.getPosicao().getSigla().equals("JNG")){
                            this.junglerLbl.setText(me.getNome());
                            this.removeJungleImg.setVisible(true);
                            teamHasJungler = true;
                        }
                        if(me.getPosicao().getSigla().equals("MID")){
                            this.midLbl.setText(me.getNome());
                            this.removeMidImg.setVisible(true);
                            teamHasMid = true;
                        }
                        if(me.getPosicao().getSigla().equals("ADC")){
                            this.adcLbl.setText(me.getNome());
                            this.removeAdcImg.setVisible(true);
                            teamHasAdc = true;
                        }
                        if(me.getPosicao().getSigla().equals("SUP")){
                            this.supportLbl.setText(me.getNome());
                            this.removeSupportImg.setVisible(true);
                            teamHasSupport = true;
                        }
                    }
                }
                if(!teamHasCoach){
                    this.coachLbl.setText("[Coach not assigned]");
                    this.removeCoachImg.setVisible(false);
                }
                if(!teamHasTop){
                    this.topLbl.setText("[Player not assigned]");
                    this.removeTopImg.setVisible(false);
                }
                if(!teamHasJungler){
                    this.junglerLbl.setText("[Player not assigned]");
                    this.removeJungleImg.setVisible(false);
                }
                if(!teamHasMid){
                    this.midLbl.setText("[Player not assigned]");
                    this.removeMidImg.setVisible(false);
                }
                if(!teamHasAdc){
                    this.adcLbl.setText("[Player not assigned]");
                    this.removeAdcImg.setVisible(false);
                }
                if(!teamHasSupport){
                    this.supportLbl.setText("[Player not assigned]");
                    this.removeSupportImg.setVisible(false);
                }
                
            }
        }
    }
    
    @FXML
    public void selectMemberAction(MouseEvent event) throws IOException {
        Membroequipa membroTemp = null;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLPlayerSelection.fxml"));
        Parent root = loader.load();
        FXMLPlayerSelectionController controller = loader.getController();
        if (event.getSource() == changeTopImg) {
            for (Membroequipa me: listaMembrosAtuais){
                if(me.getPosicao() != null && me.getPosicao().getSigla().equals("TOP")){
                    membroTemp = me;
                }
            }
            controller.preencheGridPaneComMembroExra("TOP", membroTemp);
            controller.setPosicao("TOP");
        }
        if (event.getSource() == changeJunglerImg) {
            for (Membroequipa me: listaMembrosAtuais){
                if(me.getPosicao() != null && me.getPosicao().getSigla().equals("JNG")){
                    membroTemp = me;
                }
            }
            controller.preencheGridPaneComMembroExra("JNG", membroTemp);
            controller.setPosicao("JNG");
        }
        if (event.getSource() == changeMidImg) {
            for (Membroequipa me: listaMembrosAtuais){
                if(me.getPosicao() != null && me.getPosicao().getSigla().equals("MID")){
                    membroTemp = me;
                }
            }
            controller.preencheGridPaneComMembroExra("MID", membroTemp);
            controller.setPosicao("MID");
        }
        if (event.getSource() == changeAdcImg) {
            for (Membroequipa me: listaMembrosAtuais){
                if(me.getPosicao() != null && me.getPosicao().getSigla().equals("ADC")){
                    membroTemp = me;
                }
            }
            controller.preencheGridPaneComMembroExra("ADC", membroTemp);
            controller.setPosicao("ADC");
        }   
        if (event.getSource() == changeSupportImg) {
            for (Membroequipa me: listaMembrosAtuais){
                if(me.getPosicao() != null && me.getPosicao().getSigla().equals("SUP")){
                    membroTemp = me;
                }
            }
            controller.preencheGridPaneComMembroExra("SUP", membroTemp);
            controller.setPosicao("SUP");
        }
        if (event.getSource() == changeCoachImg) {
            for (Membroequipa me: listaMembrosAtuais){
                if(me.getPosicao() == null){
                    membroTemp = me;
                }
            }
            controller.preencheGridPaneComMembroExra("null", membroTemp);
            controller.setPosicao("null");
        }
        //Metodo para preencher a Janela de PopUp
        this.prepareSelectMemberStage(root, controller);
    }

    public void prepareSelectMemberStage(Parent root, FXMLPlayerSelectionController controller) {
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
        if (controller.getMembroSelected() != null) {
            this.imprimeLista();
            membro = controller.getMembroSelected();
            if(membro.getPosicao() == null){
                coachLbl.setText(membro.getNome());
                this.replaceMember(membro);
                removeCoachImg.setVisible(true);
            }else{
                if(membro.getPosicao().getSigla().equals("TOP")){
                    removeTopImg.setVisible(true);
                }
                if(membro.getPosicao().getSigla().equals("JNG")){
                    junglerLbl.setText(membro.getNome());
                    removeJungleImg.setVisible(true);
                }
                if(membro.getPosicao().getSigla().equals("MID")){
                    midLbl.setText(membro.getNome());
                    removeMidImg.setVisible(true);
                }
                if(membro.getPosicao().getSigla().equals("ADC")){
                    adcLbl.setText(membro.getNome());
                    removeAdcImg.setVisible(true);
                }
                if(membro.getPosicao().getSigla().equals("SUP")){
                    supportLbl.setText(membro.getNome());
                    removeSupportImg.setVisible(true);
                }
            }
            this.replaceMember(membro);
            this.imprimeLista();
        }
    }
    
    public void imprimeLista(){
        for (Membroequipa me : listaNovosMembros){
            System.out.println(me.getNome());
        }
    }
    
    public void replaceMember(Membroequipa membro){
        Iterator <Membroequipa> it = this.listaNovosMembros.iterator();
        
        while(it.hasNext()){
            Membroequipa me = it.next();
            
            if(membro.getPosicao() == null){
                if(me.getPosicao() == null){
                    it.remove();
                }
            }else{
                if(me.getPosicao() != null){
                    if(membro.getPosicao().getSigla().equals("TOP")){
                        if(me.getPosicao().getSigla().equals("TOP")){
                            it.remove();
                        }
                    }
                    if(membro.getPosicao().getSigla().equals("JNG")){
                        if(me.getPosicao().getSigla().equals("JNG")){
                            it.remove();
                        }
                    }
                    if(membro.getPosicao().getSigla().equals("MID")){
                        if(me.getPosicao().getSigla().equals("MID")){
                            it.remove();
                        }
                    }
                    if(membro.getPosicao().getSigla().equals("ADC")){
                        if(me.getPosicao().getSigla().equals("ADC")){
                            it.remove();
                        }
                    }
                    if(membro.getPosicao().getSigla().equals("SUP")){
                        if(me.getPosicao().getSigla().equals("SUP")){
                            it.remove();
                        }
                    }   
                }
            }  
        }
        if(membro.getPosicao() == null){
            coachLbl.setText(membro.getNome());
        }
        if(membro.getPosicao().getSigla().equals("TOP")){
            topLbl.setText(membro.getNome());
        }
        if(membro.getPosicao().getSigla().equals("JNG")){
            junglerLbl.setText(membro.getNome());
        }
        if(membro.getPosicao().getSigla().equals("MID")){
            midLbl.setText(membro.getNome());
        }
        if(membro.getPosicao().getSigla().equals("ADC")){
            adcLbl.setText(membro.getNome());
        }
        if(membro.getPosicao().getSigla().equals("SUP")){
            supportLbl.setText(membro.getNome());
        }
        listaNovosMembros.add(membro);
    }
    
    
    
    private void setExtFilters(FileChooser chooser) {
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
    }
    
    
    
}
