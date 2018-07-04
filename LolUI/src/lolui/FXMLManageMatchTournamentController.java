/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lolui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lolbll.ImagesTeamServices;
import lolbll.TorneioServices;
import loldal.model.Encontro;
import loldal.model.Equipa;
import loldal.model.Ronda;
import loldal.model.Torneio;

/**
 * FXML Controller class
 *
 * @author joaoc
 */
public class FXMLManageMatchTournamentController implements Initializable {

    @FXML
    private ImageView imgBack;

    @FXML
    private BorderPane parentBorderPane;

    @FXML
    private ListView listaTorneios;

    @FXML
    private TextField searchBar;

    @FXML
    private Pane paneT8;
    @FXML
    private Pane paneT16;

    //Torneios de 8
    //GridPane -> Encontros do Torneio
    @FXML
    private GridPane gridT8R1E1;
    @FXML
    private GridPane gridT8R1E2;
    @FXML
    private GridPane gridT8R1E3;
    @FXML
    private GridPane gridT8R1E4;

    @FXML
    private GridPane gridT8R2E1;
    @FXML
    private GridPane gridT8R2E2;

    @FXML
    private GridPane gridT8R3E1;

    //Ronda 1
    //Imagens T - Torneiro (8 ou 16 Equipas) R-Ronda E- Encontro
    @FXML
    private ImageView imgT8R1E1Eq1;
    @FXML
    private ImageView imgT8R1E1Eq2;
    @FXML
    private ImageView imgT8R1E2Eq1;
    @FXML
    private ImageView imgT8R1E2Eq2;
    @FXML
    private ImageView imgT8R1E3Eq1;
    @FXML
    private ImageView imgT8R1E3Eq2;
    @FXML
    private ImageView imgT8R1E4Eq1;
    @FXML
    private ImageView imgT8R1E4Eq2;

    //Labels
    @FXML
    private Label lblT8R1E1Eq1;
    @FXML
    private Label lblT8R1E1Eq2;
    @FXML
    private Label lblT8R1E2Eq1;
    @FXML
    private Label lblT8R1E2Eq2;
    @FXML
    private Label lblT8R1E3Eq1;
    @FXML
    private Label lblT8R1E3Eq2;
    @FXML
    private Label lblT8R1E4Eq1;
    @FXML
    private Label lblT8R1E4Eq2;

    //Ronda 2
    //Imagens
    @FXML
    private ImageView imgT8R2E1Eq1;
    @FXML
    private ImageView imgT8R2E1Eq2;
    @FXML
    private ImageView imgT8R2E2Eq1;
    @FXML
    private ImageView imgT8R2E2Eq2;

    //Labels
    @FXML
    private Label lblT8R2E1Eq1;
    @FXML
    private Label lblT8R2E1Eq2;
    @FXML
    private Label lblT8R2E2Eq1;
    @FXML
    private Label lblT8R2E2Eq2;

    //Ronda 3
    //Imagens
    @FXML
    private ImageView imgT8R3E1Eq1;
    @FXML
    private ImageView imgT8R3E1Eq2;

    //Labels
    @FXML
    private Label lblT8R3E1Eq1;
    @FXML
    private Label lblT8R3E1Eq2;

    //Torneios de 16
    //GridPane -> Encontros do Torneio
    @FXML
    private GridPane gridT16R1E1;
    @FXML
    private GridPane gridT16R1E2;
    @FXML
    private GridPane gridT16R1E3;
    @FXML
    private GridPane gridT16R1E4;
    @FXML
    private GridPane gridT16R1E5;
    @FXML
    private GridPane gridT16R1E6;
    @FXML
    private GridPane gridT16R1E7;
    @FXML
    private GridPane gridT16R1E8;

    @FXML
    private GridPane gridT16R2E1;
    @FXML
    private GridPane gridT16R2E2;
    @FXML
    private GridPane gridT16R2E3;
    @FXML
    private GridPane gridT16R2E4;
    @FXML
    private GridPane gridT16R3E1;
    @FXML
    private GridPane gridT16R3E2;
    @FXML
    private GridPane gridT16R4E1;

    //Ronda 1
    //Imagens
    @FXML
    private ImageView imgT16R1E1Eq1;
    @FXML
    private ImageView imgT16R1E1Eq2;
    @FXML
    private ImageView imgT16R1E2Eq1;
    @FXML
    private ImageView imgT16R1E2Eq2;
    @FXML
    private ImageView imgT16R1E3Eq1;
    @FXML
    private ImageView imgT16R1E3Eq2;
    @FXML
    private ImageView imgT16R1E4Eq1;
    @FXML
    private ImageView imgT16R1E4Eq2;
    @FXML
    private ImageView imgT16R1E5Eq1;
    @FXML
    private ImageView imgT16R1E5Eq2;
    @FXML
    private ImageView imgT16R1E6Eq1;
    @FXML
    private ImageView imgT16R1E6Eq2;
    @FXML
    private ImageView imgT16R1E7Eq1;
    @FXML
    private ImageView imgT16R1E7Eq2;
    @FXML
    private ImageView imgT16R1E8Eq1;
    @FXML
    private ImageView imgT16R1E8Eq2;

    //Labels
    @FXML
    private Label lblT16R1E1Eq1;
    @FXML
    private Label lblT16R1E1Eq2;
    @FXML
    private Label lblT16R1E2Eq1;
    @FXML
    private Label lblT16R1E2Eq2;
    @FXML
    private Label lblT16R1E3Eq1;
    @FXML
    private Label lblT16R1E3Eq2;
    @FXML
    private Label lblT16R1E4Eq1;
    @FXML
    private Label lblT16R1E4Eq2;
    @FXML
    private Label lblT16R1E5Eq1;
    @FXML
    private Label lblT16R1E5Eq2;
    @FXML
    private Label lblT16R1E6Eq1;
    @FXML
    private Label lblT16R1E6Eq2;
    @FXML
    private Label lblT16R1E7Eq1;
    @FXML
    private Label lblT16R1E7Eq2;
    @FXML
    private Label lblT16R1E8Eq1;
    @FXML
    private Label lblT16R1E8Eq2;

    //Ronda 2
    //Imagens
    @FXML
    private ImageView imgT16R2E1Eq1;
    @FXML
    private ImageView imgT16R2E1Eq2;
    @FXML
    private ImageView imgT16R2E2Eq1;
    @FXML
    private ImageView imgT16R2E2Eq2;
    @FXML
    private ImageView imgT16R2E3Eq1;
    @FXML
    private ImageView imgT16R2E3Eq2;
    @FXML
    private ImageView imgT16R2E4Eq1;
    @FXML
    private ImageView imgT16R2E4Eq2;

    //Labels
    @FXML
    private Label lblT16R2E1Eq1;
    @FXML
    private Label lblT16R2E1Eq2;
    @FXML
    private Label lblT16R2E2Eq1;
    @FXML
    private Label lblT16R2E2Eq2;
    @FXML
    private Label lblT16R2E3Eq1;
    @FXML
    private Label lblT16R2E3Eq2;
    @FXML
    private Label lblT16R2E4Eq1;
    @FXML
    private Label lblT16R2E4Eq2;

    //Ronda 3
    //Imagens
    @FXML
    private ImageView imgT16R3E1Eq1;
    @FXML
    private ImageView imgT16R3E1Eq2;
    @FXML
    private ImageView imgT16R3E2Eq1;
    @FXML
    private ImageView imgT16R3E2Eq2;

    //Labels
    @FXML
    private Label lblT16R3E1Eq1;
    @FXML
    private Label lblT16R3E1Eq2;
    @FXML
    private Label lblT16R3E2Eq1;
    @FXML
    private Label lblT16R3E2Eq2;

    //Ronda 4    
    //Imagens
    @FXML
    private ImageView imgT16R4E1Eq1;
    @FXML
    private ImageView imgT16R4E1Eq2;

    //Labels
    @FXML
    private Label lblT16R4E1Eq1;
    @FXML
    private Label lblT16R4E1Eq2;

    private ObservableList<Torneio> torneiosObs;

    private List<Torneio> listaPesquisa;

    private Torneio t;
    
    URL url;
    
    ResourceBundle rb;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        parentBorderPane.getStyleClass().add("borderPane");
        this.listaPesquisa = new ArrayList<>();
        this.listarTorneios();
        this.pesquisarNaLista();
        this.atribuirElementos();
        this.url = url;
        this.rb = rb;
        paneT8.setVisible(false);
        paneT16.setVisible(false);
        System.out.println("AQUI ESTOU");
    }

    @FXML
    public void closePopUp() {
        Stage stage = (Stage) this.imgBack.getScene().getWindow();
        stage.close();
    }

    //TODO
    @FXML
    public void handleGridAction(MouseEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLMatchGameTournament.fxml"));
        Parent root = loader.load();
        FXMLMatchGameTournamentController controller = loader.getController();
        this.preparePopUpElements(event, controller);
        //Metodo para preencher a Janela de PopUp
        this.prepareStage(root);
    }
    
    public void prepareStage(Parent root) {
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.setY(380);
        stage.setX(255);
        stage.getIcons().add(new Image(LolUI.class.getResourceAsStream("pics/lol.png")));
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(this.imgT16R1E8Eq2.getScene().getWindow());
        stage.showAndWait();
        this.initialize(url, rb);
    }

    public void preparePopUpElements(MouseEvent event, FXMLMatchGameTournamentController controller) {

        Encontro en = new Encontro();
        
        if (event.getSource() == gridT8R1E1) {
            System.out.println("ENTREI");
            en = this.getEncontroByGripPane(0, 0);
        }

        if (event.getSource() == gridT8R1E2) {
            en = this.getEncontroByGripPane(0, 1);
        }

        if (event.getSource() == gridT8R1E3) {
            en = this.getEncontroByGripPane(0, 2);
        }

        if (event.getSource() == gridT8R1E4) {
            en = this.getEncontroByGripPane(0, 3);
        }

        if (event.getSource() == gridT8R2E1) {
            en = this.getEncontroByGripPane(1, 0);
        }

        if (event.getSource() == gridT8R2E2) {
            en = this.getEncontroByGripPane(1, 1);
        }

        if (event.getSource() == gridT8R3E1) {
            en = this.getEncontroByGripPane(2, 0);
        }
        
        if(event.getSource() == gridT16R1E1){
            en = this.getEncontroByGripPane(0, 0);
        }
        
        if(event.getSource() == gridT16R1E2){
            en = this.getEncontroByGripPane(0, 1);
        }
        
        if(event.getSource() == gridT16R1E3){
            en = this.getEncontroByGripPane(0, 2);
        }
        
        if(event.getSource() == gridT16R1E4){
            en = this.getEncontroByGripPane(0, 3);
        }
        
        if(event.getSource() == gridT16R1E5){
            en = this.getEncontroByGripPane(0, 4);
        }
        
        if(event.getSource() == gridT16R1E6){
            en = this.getEncontroByGripPane(0, 5);
        }
        
        if(event.getSource() == gridT16R1E7){
            en = this.getEncontroByGripPane(0, 6);
        }
        
        if(event.getSource() == gridT16R1E8){
            en = this.getEncontroByGripPane(0, 7);
        }
        
        if(event.getSource() == gridT16R2E1){
            en = this.getEncontroByGripPane(1, 0);
        }
        
        if(event.getSource() == gridT16R2E2){
            en = this.getEncontroByGripPane(1, 1);
        }
        
        if(event.getSource() == gridT16R2E3){
            en = this.getEncontroByGripPane(1, 2);
        }
        
        if(event.getSource() == gridT16R2E4){
            en = this.getEncontroByGripPane(1, 3);
        }
        
        if(event.getSource() == gridT16R3E1){
            en = this.getEncontroByGripPane(2, 0);
        }
        
        if(event.getSource() == gridT16R3E2){
            en = this.getEncontroByGripPane(2, 1);
        }
        
        if(event.getSource() == gridT16R4E1){
            en = this.getEncontroByGripPane(3, 0);
        }

        controller.preencheTabsLabelsComboboxesEquipas(en);
        
    }
    
    public Encontro getEncontroByGripPane(int nronda, int nencontro) {
        List<Ronda> rondas = new ArrayList<>();
        rondas.addAll(t.getRondas());
        rondas.sort(Comparator.comparing((ronda) -> ronda.getId()));
        List<Encontro> encontros = new ArrayList<>();
        encontros.addAll(rondas.get(nronda).getEncontros());
        encontros.sort(Comparator.comparing((encontro) -> encontro.getId()));

        Encontro en = encontros.get(nencontro);

        return en;
    }

    public void listarTorneios() {
        // -- CRIAR MÉTODO NA BLL
        List<Torneio> list = TorneioServices.listaTorneios();

        for (Torneio t : list) {
            if (!t.getEstado().equals("FINALIZADO")) {
                listaPesquisa.add(t);
            }
        }

        listaPesquisa.sort(Comparator.comparing((torneio) -> torneio.getDatafim()));
        Collections.reverse(listaPesquisa);
        torneiosObs = FXCollections.observableArrayList(listaPesquisa);
        this.listaTorneios.setItems(torneiosObs);
    }

    public void pesquisarNaLista() {
        searchBar.setOnKeyReleased((event) -> {
            List<Torneio> temp = new ArrayList<>();
            String texto = searchBar.getText();
            if (texto.isEmpty()) {
                torneiosObs = FXCollections.observableArrayList(listaPesquisa);
                this.listaTorneios.setItems(torneiosObs);
                temp.clear();
            } else {
                for (Torneio tr : listaPesquisa) {
                    if (tr.getNome().toLowerCase().contains(texto.toLowerCase())) {
                        temp.add(tr);
                    }
                }
                torneiosObs = FXCollections.observableArrayList(temp);
                this.listaTorneios.setItems(torneiosObs);
            }
        });
    }

    public void atribuirElementos() {
        this.listaTorneios.getSelectionModel().selectedItemProperty().addListener((observable) -> {
            t = (Torneio) listaTorneios.getSelectionModel().getSelectedItem();
            if (t != null) {
                //métodos para atribuir elementos
                if (t.getNumrondas().intValue() == 3) {
                    this.paneT8.setVisible(true);
                    this.paneT16.setVisible(false);
                    this.ativaGridT8();
                    this.atribuirImagens8(t);
                    this.atribuirResultados8(t);
                    this.inativaGridEncontroWinnerT8(t);
                } else {
                    if (t.getNumrondas().intValue() == 4) {
                        this.paneT8.setVisible(false);
                        this.paneT16.setVisible(true);
                        this.ativaGridT16();
                        this.atribuirImagens16(t);
                        this.atribuirResultados16(t);
                        this.inativaGridEncontroWinnerT16(t);
                    }
                }
            }
        });
    }

    public void atribuirImagens8(Torneio t) {
        //Passar Set List para Array List das Rondas e obter os Encontros daquela ronda
        //A cada ronda dar clear à lista de Encontros para ficar somente os encontros da ronda escolhida
        List<Ronda> rondas = new ArrayList<>();
        rondas.addAll(t.getRondas());
        rondas.sort(Comparator.comparing((ronda) -> ronda.getId()));
        List<Encontro> encontros = new ArrayList<>();
        encontros.addAll(rondas.get(0).getEncontros());
        encontros.sort(Comparator.comparing((encontro) -> encontro.getId()));
        //Ronda 1
        //Logo equipa 1 e equipa 2, Encontro 1
        this.atribuirLogo(encontros.get(0).getEquipaByEquipa1(), imgT8R1E1Eq1);
        this.atribuirLogo(encontros.get(0).getEquipaByEquipa2(), imgT8R1E1Eq2);
        //Logo equipa 1 e equipa 2, Encontro 2
        this.atribuirLogo(encontros.get(1).getEquipaByEquipa1(), imgT8R1E2Eq1);
        this.atribuirLogo(encontros.get(1).getEquipaByEquipa2(), imgT8R1E2Eq2);
        //Logo equipa 1 e equipa 2, Encontro 3
        this.atribuirLogo(encontros.get(2).getEquipaByEquipa1(), imgT8R1E3Eq1);
        this.atribuirLogo(encontros.get(2).getEquipaByEquipa2(), imgT8R1E3Eq2);
        //Logo equipa 1 e equipa 2, Encontro 4
        this.atribuirLogo(encontros.get(3).getEquipaByEquipa1(), imgT8R1E4Eq1);
        this.atribuirLogo(encontros.get(3).getEquipaByEquipa2(), imgT8R1E4Eq2);

        if (t.getRondaatual().intValue() > 1) {
            //Ronda2
            encontros.clear();
            encontros.addAll(rondas.get(1).getEncontros());
            encontros.sort(Comparator.comparing((encontro) -> encontro.getId()));
            //Logo equipa 1 e equipa 2, Encontro 1
            this.atribuirLogo(encontros.get(0).getEquipaByEquipa1(), imgT8R2E1Eq1);
            this.atribuirLogo(encontros.get(0).getEquipaByEquipa2(), imgT8R2E1Eq2);
            //Logo equipa 1 e equipa 2, Encontro 2
            this.atribuirLogo(encontros.get(1).getEquipaByEquipa1(), imgT8R2E2Eq1);
            this.atribuirLogo(encontros.get(1).getEquipaByEquipa2(), imgT8R2E2Eq2);
        } else {
            this.inativaGrid(gridT8R2E1);
            this.inativaGrid(gridT8R2E2);
        }

        if (t.getRondaatual().intValue() > 2) {
            //Ronda3
            encontros.clear();
            encontros.addAll(rondas.get(2).getEncontros());
            //encontros.sort(Comparator.comparing((encontro) -> encontro.getId()));
            //Logo equipa 1 e equipa 2, Encontro 1
            this.atribuirLogo(encontros.get(0).getEquipaByEquipa1(), imgT8R3E1Eq1);
            this.atribuirLogo(encontros.get(0).getEquipaByEquipa2(), imgT8R3E1Eq2);
        } else {
            this.inativaGrid(gridT8R3E1);
        }

    }

    public void atribuirImagens16(Torneio t) {
        //Passar Set List para Array List das Rondas e obter os Encontros daquela ronda
        //A cada ronda dar clear à lista de Encontros para ficar somente os encontros da ronda escolhida
        List<Ronda> rondas = new ArrayList<>();
        rondas.addAll(t.getRondas());
        rondas.sort(Comparator.comparing((ronda) -> ronda.getId()));
        List<Encontro> encontros = new ArrayList<>();
        encontros.addAll(rondas.get(0).getEncontros());
        encontros.sort(Comparator.comparing((encontro) -> encontro.getId()));
        //Ronda 1
        //Logo equipa 1 e equipa 2, Encontro 1
        this.atribuirLogo(encontros.get(0).getEquipaByEquipa1(), imgT16R1E1Eq1);
        this.atribuirLogo(encontros.get(0).getEquipaByEquipa2(), imgT16R1E1Eq2);
        //Logo equipa 1 e equipa 2, Encontro 2
        this.atribuirLogo(encontros.get(1).getEquipaByEquipa1(), imgT16R1E2Eq1);
        this.atribuirLogo(encontros.get(1).getEquipaByEquipa2(), imgT16R1E2Eq2);
        //Logo equipa 1 e equipa 2, Encontro 3
        this.atribuirLogo(encontros.get(2).getEquipaByEquipa1(), imgT16R1E3Eq1);
        this.atribuirLogo(encontros.get(2).getEquipaByEquipa2(), imgT16R1E3Eq2);
        //Logo equipa 1 e equipa 2, Encontro 4
        this.atribuirLogo(encontros.get(3).getEquipaByEquipa1(), imgT16R1E4Eq1);
        this.atribuirLogo(encontros.get(3).getEquipaByEquipa2(), imgT16R1E4Eq2);
        //Logo equipa 1 e equipa 2, Encontro 5
        this.atribuirLogo(encontros.get(4).getEquipaByEquipa1(), imgT16R1E5Eq1);
        this.atribuirLogo(encontros.get(4).getEquipaByEquipa2(), imgT16R1E5Eq2);
        //Logo equipa 1 e equipa 2, Encontro 6
        this.atribuirLogo(encontros.get(5).getEquipaByEquipa1(), imgT16R1E6Eq1);
        this.atribuirLogo(encontros.get(5).getEquipaByEquipa2(), imgT16R1E6Eq2);
        //Logo equipa 1 e equipa 2, Encontro 7
        this.atribuirLogo(encontros.get(6).getEquipaByEquipa1(), imgT16R1E7Eq1);
        this.atribuirLogo(encontros.get(6).getEquipaByEquipa2(), imgT16R1E7Eq2);
        //Logo equipa 1 e equipa 2, Encontro 8
        this.atribuirLogo(encontros.get(7).getEquipaByEquipa1(), imgT16R1E8Eq1);
        this.atribuirLogo(encontros.get(7).getEquipaByEquipa2(), imgT16R1E8Eq2);

        if (t.getRondaatual().intValue() > 1) {
            //Ronda 2
            encontros.clear();
            encontros.addAll(rondas.get(1).getEncontros());
            encontros.sort(Comparator.comparing((encontro) -> encontro.getId()));
            //Logo equipa 1 e equipa 2, Encontro 1
            this.atribuirLogo(encontros.get(0).getEquipaByEquipa1(), imgT16R2E1Eq1);
            this.atribuirLogo(encontros.get(0).getEquipaByEquipa2(), imgT16R2E1Eq2);
            //Logo equipa 1 e equipa 2, Encontro 2
            this.atribuirLogo(encontros.get(1).getEquipaByEquipa1(), imgT16R2E2Eq1);
            this.atribuirLogo(encontros.get(1).getEquipaByEquipa2(), imgT16R2E2Eq2);
            //Logo equipa 1 e equipa 2, Encontro 3
            this.atribuirLogo(encontros.get(2).getEquipaByEquipa1(), imgT16R2E3Eq1);
            this.atribuirLogo(encontros.get(2).getEquipaByEquipa2(), imgT16R2E3Eq2);
            //Logo equipa 1 e equipa 2, Encontro 4
            this.atribuirLogo(encontros.get(3).getEquipaByEquipa1(), imgT16R2E4Eq1);
            this.atribuirLogo(encontros.get(3).getEquipaByEquipa2(), imgT16R2E4Eq2);
        } else {
            this.inativaGrid(gridT16R2E1);
            this.inativaGrid(gridT16R2E2);
            this.inativaGrid(gridT16R2E3);
            this.inativaGrid(gridT16R2E4);
        }

        if (t.getRondaatual().intValue() > 2) {
            //Ronda 3
            encontros.clear();
            encontros.addAll(rondas.get(2).getEncontros());
            encontros.sort(Comparator.comparing((encontro) -> encontro.getId()));
            //Logo equipa 1 e equipa 2, Encontro 1
            this.atribuirLogo(encontros.get(0).getEquipaByEquipa1(), imgT16R3E1Eq1);
            this.atribuirLogo(encontros.get(0).getEquipaByEquipa2(), imgT16R3E1Eq2);
            //Logo equipa 1 e equipa 2, Encontro 2
            this.atribuirLogo(encontros.get(1).getEquipaByEquipa1(), imgT16R3E2Eq1);
            this.atribuirLogo(encontros.get(1).getEquipaByEquipa2(), imgT16R3E2Eq2);
        } else {
            this.inativaGrid(gridT16R3E1);
            this.inativaGrid(gridT16R3E2);
        }

        if (t.getRondaatual().intValue() > 3) {
            //Ronda 4
            encontros.clear();
            encontros.addAll(rondas.get(3).getEncontros());
            encontros.sort(Comparator.comparing((encontro) -> encontro.getId()));
            //Logo equipa 1 e equipa 2, Encontro 1
            this.atribuirLogo(encontros.get(0).getEquipaByEquipa1(), imgT16R4E1Eq1);
            this.atribuirLogo(encontros.get(0).getEquipaByEquipa2(), imgT16R4E1Eq2);
        } else {
            this.inativaGrid(gridT16R4E1);
        }
    }

    public void atribuirLogo(Equipa eq, ImageView img) {
        if(ImagesTeamServices.existsOnMap(eq.getNome())){
            img.setImage(new Image(ImagesTeamServices.getOriginalPath(eq.getNome())));
        }else{
            if (FXMLPlayersMainController.class.getResourceAsStream("pics/teams/" + eq.getSigla().toLowerCase() + ".png") != null) {
                img.setImage(new Image(FXMLPlayersMainController.class.getResourceAsStream("pics/teams/" + eq.getSigla().toLowerCase() + ".png")));
            } else {
                img.setImage(new Image(FXMLPlayersMainController.class.getResourceAsStream("pics/players/unknown.png")));
            }
        }
    }

    public void atribuirResultados8(Torneio t) {
        //Passar Set List para Array List das Rondas e obter os Encontros daquela ronda
        //A cada ronda dar clear à lista de Encontros para ficar somente os encontros da ronda escolhida
        //Ordenar por ID : testList.sort(Comparator.comparing(ClassName::getFieldName));
        List<Ronda> rondas = new ArrayList<>();
        rondas.addAll(t.getRondas());
        rondas.sort(Comparator.comparing((ronda) -> ronda.getId()));
        List<Encontro> encontros = new ArrayList<>();
        encontros.addAll(rondas.get(0).getEncontros());
        encontros.sort(Comparator.comparing((encontro) -> encontro.getId()));

        //Ronda 1
        //resultado Equipa 1 e Equipa 2, do Encontro 1
        this.atribuirLabel(encontros.get(0).getVitoriaequipa1().toString(), lblT8R1E1Eq1);
        this.atribuirLabel(encontros.get(0).getVitoriaequipa2().toString(), lblT8R1E1Eq2);
        //resultado Equipa 1 e Equipa 2, do Encontro 2
        this.atribuirLabel(encontros.get(1).getVitoriaequipa1().toString(), lblT8R1E2Eq1);
        this.atribuirLabel(encontros.get(1).getVitoriaequipa2().toString(), lblT8R1E2Eq2);
        //resultado Equipa 1 e Equipa 2, do Encontro 3
        this.atribuirLabel(encontros.get(2).getVitoriaequipa1().toString(), lblT8R1E3Eq1);
        this.atribuirLabel(encontros.get(2).getVitoriaequipa2().toString(), lblT8R1E3Eq2);
        //resultado Equipa 1 e Equipa 2, do Encontro 4
        this.atribuirLabel(encontros.get(3).getVitoriaequipa1().toString(), lblT8R1E4Eq1);
        this.atribuirLabel(encontros.get(3).getVitoriaequipa2().toString(), lblT8R1E4Eq2);

        if (t.getRondaatual().intValue() > 1) {
            //Ronda 2
            encontros.clear();
            encontros.addAll(rondas.get(1).getEncontros());
            encontros.sort(Comparator.comparing((encontro) -> encontro.getId()));
            //resultado Equipa 1 e Equipa 2, do Encontro 1
            this.atribuirLabel(encontros.get(0).getVitoriaequipa1().toString(), lblT8R2E1Eq1);
            this.atribuirLabel(encontros.get(0).getVitoriaequipa2().toString(), lblT8R2E1Eq2);
            //resultado Equipa 1 e Equipa 2, do Encontro 2
            this.atribuirLabel(encontros.get(1).getVitoriaequipa1().toString(), lblT8R2E2Eq1);
            this.atribuirLabel(encontros.get(1).getVitoriaequipa2().toString(), lblT8R2E2Eq2);

        }

        if (t.getRondaatual().intValue() > 2) {
            //Ronda 3
            encontros.clear();
            encontros.addAll(rondas.get(2).getEncontros());
            encontros.sort(Comparator.comparing((encontro) -> encontro.getId()));
            //resultado Equipa 1 e Equipa 2, do Encontro 1
            this.atribuirLabel(encontros.get(0).getVitoriaequipa1().toString(), lblT8R3E1Eq1);
            this.atribuirLabel(encontros.get(0).getVitoriaequipa2().toString(), lblT8R3E1Eq2);
        }

    }

    public void atribuirResultados16(Torneio t) {
        //Passar Set List para Array List das Rondas e obter os Encontros daquela ronda
        //A cada ronda dar clear à lista de Encontros para ficar somente os encontros da ronda escolhida
        //Ordenar por ID : testList.sort(Comparator.comparing(ClassName::getFieldName));
        List<Ronda> rondas = new ArrayList<>();
        rondas.addAll(t.getRondas());
        rondas.sort(Comparator.comparing((ronda) -> ronda.getId()));
        List<Encontro> encontros = new ArrayList<>();
        encontros.addAll(rondas.get(0).getEncontros());
        encontros.sort(Comparator.comparing((encontro) -> encontro.getId()));

        //Ronda 1
        //resultado Equipa 1 e Equipa 2, do Encontro 1
        this.atribuirLabel(encontros.get(0).getVitoriaequipa1().toString(), lblT16R1E1Eq1);
        this.atribuirLabel(encontros.get(0).getVitoriaequipa2().toString(), lblT16R1E1Eq2);
        //resultado Equipa 1 e Equipa 2, do Encontro 2
        this.atribuirLabel(encontros.get(1).getVitoriaequipa1().toString(), lblT16R1E2Eq1);
        this.atribuirLabel(encontros.get(1).getVitoriaequipa2().toString(), lblT16R1E2Eq2);
        //resultado Equipa 1 e Equipa 2, do Encontro 3
        this.atribuirLabel(encontros.get(2).getVitoriaequipa1().toString(), lblT16R1E3Eq1);
        this.atribuirLabel(encontros.get(2).getVitoriaequipa2().toString(), lblT16R1E3Eq2);
        //resultado Equipa 1 e Equipa 2, do Encontro 4
        this.atribuirLabel(encontros.get(3).getVitoriaequipa1().toString(), lblT16R1E4Eq1);
        this.atribuirLabel(encontros.get(3).getVitoriaequipa2().toString(), lblT16R1E4Eq2);
        //resultado Equipa 1 e Equipa 2, do Encontro 5
        this.atribuirLabel(encontros.get(4).getVitoriaequipa1().toString(), lblT16R1E5Eq1);
        this.atribuirLabel(encontros.get(4).getVitoriaequipa2().toString(), lblT16R1E5Eq2);
        //resultado Equipa 1 e Equipa 2, do Encontro 6
        this.atribuirLabel(encontros.get(5).getVitoriaequipa1().toString(), lblT16R1E6Eq1);
        this.atribuirLabel(encontros.get(5).getVitoriaequipa2().toString(), lblT16R1E6Eq2);
        //resultado Equipa 1 e Equipa 2, do Encontro 7
        this.atribuirLabel(encontros.get(6).getVitoriaequipa1().toString(), lblT16R1E7Eq1);
        this.atribuirLabel(encontros.get(6).getVitoriaequipa2().toString(), lblT16R1E7Eq2);
        //resultado Equipa 1 e Equipa 2, do Encontro 8
        this.atribuirLabel(encontros.get(7).getVitoriaequipa1().toString(), lblT16R1E8Eq1);
        this.atribuirLabel(encontros.get(7).getVitoriaequipa2().toString(), lblT16R1E8Eq2);

        if (t.getRondaatual().intValue() > 1) {
            //Ronda 2
            encontros.clear();
            encontros.addAll(rondas.get(1).getEncontros());
            encontros.sort(Comparator.comparing((encontro) -> encontro.getId()));
            //resultado Equipa 1 e Equipa 2, do Encontro 1
            this.atribuirLabel(encontros.get(0).getVitoriaequipa1().toString(), lblT16R2E1Eq1);
            this.atribuirLabel(encontros.get(0).getVitoriaequipa2().toString(), lblT16R2E1Eq2);
            //resultado Equipa 1 e Equipa 2, do Encontro 2
            this.atribuirLabel(encontros.get(1).getVitoriaequipa1().toString(), lblT16R2E2Eq1);
            this.atribuirLabel(encontros.get(1).getVitoriaequipa2().toString(), lblT16R2E2Eq2);
            //resultado Equipa 1 e Equipa 2, do Encontro 3
            this.atribuirLabel(encontros.get(2).getVitoriaequipa1().toString(), lblT16R2E3Eq1);
            this.atribuirLabel(encontros.get(2).getVitoriaequipa2().toString(), lblT16R2E3Eq2);
            //resultado Equipa 1 e Equipa 2, do Encontro 4
            this.atribuirLabel(encontros.get(3).getVitoriaequipa1().toString(), lblT16R2E4Eq1);
            this.atribuirLabel(encontros.get(3).getVitoriaequipa2().toString(), lblT16R2E4Eq2);
        }

        if (t.getRondaatual().intValue() > 2) {
            //Ronda 3
            encontros.clear();
            encontros.addAll(rondas.get(2).getEncontros());
            encontros.sort(Comparator.comparing((encontro) -> encontro.getId()));
            //resultado Equipa 1 e Equipa 2, do Encontro 1
            this.atribuirLabel(encontros.get(0).getVitoriaequipa1().toString(), lblT16R3E1Eq1);
            this.atribuirLabel(encontros.get(0).getVitoriaequipa2().toString(), lblT16R3E1Eq2);
            //resultado Equipa 1 e Equipa 2, do Encontro 2
            this.atribuirLabel(encontros.get(1).getVitoriaequipa1().toString(), lblT16R3E2Eq1);
            this.atribuirLabel(encontros.get(1).getVitoriaequipa2().toString(), lblT16R3E2Eq2);
        }

        if (t.getRondaatual().intValue() > 3) {
            //Ronda 4
            encontros.clear();
            encontros.addAll(rondas.get(3).getEncontros());
            encontros.sort(Comparator.comparing((encontro) -> encontro.getId()));
            //resultado Equipa 1 e Equipa 2, do Encontro 1
            this.atribuirLabel(encontros.get(0).getVitoriaequipa1().toString(), lblT16R4E1Eq1);
            this.atribuirLabel(encontros.get(0).getVitoriaequipa2().toString(), lblT16R4E1Eq2);
        }
    }
    
    public void inativaGridEncontroWinnerT8(Torneio t){
        List<Ronda> rondas = new ArrayList<>();
        rondas.addAll(t.getRondas());
        rondas.sort(Comparator.comparing((ronda) -> ronda.getId()));
        List<Encontro> encontros = new ArrayList<>();
        encontros.addAll(rondas.get(0).getEncontros());
        encontros.sort(Comparator.comparing((encontro) -> encontro.getId()));
        
        
        System.out.println(t.getRondas());
        
        if(t.getRondaatual().intValue() >= 1){
            if(encontros.get(0).getEquipaByVencedor()!=null){
                gridT8R1E1.setDisable(true);
            }
            if(encontros.get(1).getEquipaByVencedor()!=null){
                gridT8R1E2.setDisable(true);
            }
            if(encontros.get(2).getEquipaByVencedor()!=null){
                gridT8R1E3.setDisable(true);
            }
            if(encontros.get(3).getEquipaByVencedor()!=null){
                gridT8R1E4.setDisable(true);
            }
        }
        if(t.getRondaatual().intValue() >= 2){
            encontros.clear();
            encontros.addAll(rondas.get(1).getEncontros());
            encontros.sort(Comparator.comparing((encontro) -> encontro.getId()));
            
            if(encontros.get(0).getEquipaByVencedor()!=null){
                gridT8R2E1.setDisable(true);
            }
            if(encontros.get(1).getEquipaByVencedor()!=null){
                gridT8R2E2.setDisable(true);
            }
        }
        if(t.getRondaatual().intValue() >= 3){
            encontros.clear();
            encontros.addAll(rondas.get(2).getEncontros());
            encontros.sort(Comparator.comparing((encontro) -> encontro.getId()));
            
            if(encontros.get(0).getEquipaByVencedor()!=null){
                gridT8R3E1.setDisable(true);
            }
        }
    }
    
    public void inativaGridEncontroWinnerT16(Torneio t){
        List<Ronda> rondas = new ArrayList<>();
        rondas.addAll(t.getRondas());
        rondas.sort(Comparator.comparing((ronda) -> ronda.getId()));
        List<Encontro> encontros = new ArrayList<>();
        encontros.addAll(rondas.get(0).getEncontros());
        encontros.sort(Comparator.comparing((encontro) -> encontro.getId()));
        
        if(t.getRondaatual().intValue() >= 1){
            if(encontros.get(0).getEquipaByVencedor()!=null){
                gridT16R1E1.setDisable(true);
            }
            if(encontros.get(1).getEquipaByVencedor()!=null){
                gridT16R1E2.setDisable(true);
            }
            if(encontros.get(2).getEquipaByVencedor()!=null){
                gridT16R1E3.setDisable(true);
            }
            if(encontros.get(3).getEquipaByVencedor()!=null){
                gridT16R1E4.setDisable(true);
            }
            if(encontros.get(4).getEquipaByVencedor()!=null){
                gridT16R1E5.setDisable(true);
            }
            if(encontros.get(5).getEquipaByVencedor()!=null){
                gridT16R1E6.setDisable(true);
            }
            if(encontros.get(6).getEquipaByVencedor()!=null){
                gridT16R1E7.setDisable(true);
            }
            if(encontros.get(7).getEquipaByVencedor()!=null){
                gridT16R1E8.setDisable(true);
            }
        }
        if(t.getRondaatual().intValue() >= 2){
            encontros.clear();
            encontros.addAll(rondas.get(1).getEncontros());
            encontros.sort(Comparator.comparing((encontro) -> encontro.getId()));
            
            if(encontros.get(0).getEquipaByVencedor()!=null){
                gridT16R2E1.setDisable(true);
            }
            if(encontros.get(1).getEquipaByVencedor()!=null){
                gridT16R2E2.setDisable(true);
            }
            if(encontros.get(2).getEquipaByVencedor()!=null){
                gridT16R2E3.setDisable(true);
            }
            if(encontros.get(3).getEquipaByVencedor()!=null){
                gridT16R2E4.setDisable(true);
            }
        }
        if(t.getRondaatual().intValue() >= 3){
            encontros.clear();
            encontros.addAll(rondas.get(2).getEncontros());
            encontros.sort(Comparator.comparing((encontro) -> encontro.getId()));
            
            if(encontros.get(0).getEquipaByVencedor()!=null){
                gridT16R3E1.setDisable(true);
            }
            if(encontros.get(1).getEquipaByVencedor()!=null){
                gridT16R3E2.setDisable(true);
            }
        }
        if(t.getRondaatual().intValue() >= 4){
            encontros.clear();
            encontros.addAll(rondas.get(3).getEncontros());
            encontros.sort(Comparator.comparing((encontro) -> encontro.getId()));
            
            if(encontros.get(0).getEquipaByVencedor()!=null){
                gridT16R4E1.setDisable(true);
            }
        }
    }

    public void atribuirLabel(String st, Label lbl) {
        lbl.setText(st);
    }

    public void inativaGrid(GridPane grid) {
        for (Node node : grid.getChildren()) {
            node.setVisible(false);
        }
        grid.setDisable(true);
    }

    public void ativaGrid(GridPane grid) {
        for (Node node : grid.getChildren()) {
            node.setVisible(true);
        }
        grid.setDisable(false);
    }

    public void ativaGridT8() {

        this.ativaGrid(gridT8R2E1);
        this.ativaGrid(gridT8R2E2);
        this.ativaGrid(gridT8R3E1);
    }

    public void ativaGridT16() {
        this.ativaGrid(gridT16R2E1);
        this.ativaGrid(gridT16R2E2);
        this.ativaGrid(gridT16R2E3);
        this.ativaGrid(gridT16R2E4);

        this.ativaGrid(gridT16R2E1);
        this.ativaGrid(gridT16R2E2);

        this.ativaGrid(gridT16R3E1);
    }

}
