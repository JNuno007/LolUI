/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lolui;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import lolbll.EncontroServices;
import lolbll.EquipaServices;
import lolbll.ImagesMemberServices;
import lolbll.ImagesTeamServices;
import lolbll.JogoServices;
import lolbll.TorneioServices;
import loldal.model.Encontro;
import loldal.model.Equipa;
import loldal.model.Jogo;
import loldal.model.Membroequipa;
import loldal.model.Ronda;
import loldal.model.Torneio;

/**
 * FXML Controller class
 *
 * @author vaaz
 */
public class FXMLTeamsMainController extends Application implements Initializable {

    //Border Pane da direita
    @FXML
    private BorderPane borderRight;

    @FXML
    private ListView listaEquipas;
    @FXML
    private TextField searchBar;

    //Imagens da Team
    @FXML
    private ImageView imglogoTeam;
    @FXML
    private ImageView imgCountry;

    //imagens dos Players
    @FXML
    private ImageView imgPlayerTop;
    @FXML
    private ImageView imgPlayerJungle;
    @FXML
    private ImageView imgPlayerMid;
    @FXML
    private ImageView imgPlayerADC;
    @FXML
    private ImageView imgPlayerSupport;

    //Labels da Team
    @FXML
    private Label lblNomeEquipa;
    @FXML
    private Label lblSiglaEquipa;
    @FXML
    private Label lblTrofeus;
    @FXML
    private Label lblKDA;

    //Labels dos Players
    @FXML
    private Label lblNomeTop;
    @FXML
    private Label lblNomeJungle;
    @FXML
    private Label lblNomeMid;
    @FXML
    private Label lblNomeADC;
    @FXML
    private Label lblNomeSupport;

    //Info Icons
    @FXML
    private ImageView infoTop;
    @FXML
    private ImageView infoJungle;
    @FXML
    private ImageView infoMid;
    @FXML
    private ImageView infoADC;
    @FXML
    private ImageView infoSupport;

    //FXML's da Tabela de Resultados de Encontros
    @FXML
    private TableView<Encontro> tableResultados;
    @FXML
    private TableColumn<Encontro, Date> dataTR;
    @FXML
    private TableColumn<Encontro, Encontro> blueTeamTR;
    @FXML
    private TableColumn<Encontro, Integer> vitoriasBlueTeamTR;
    @FXML
    private TableColumn<Encontro, String> vsTR;
    @FXML
    private TableColumn<Encontro, Integer> vitoriasRedTeamTR;
    @FXML
    private TableColumn<Encontro, Encontro> redTeamTR;
    @FXML
    private TableColumn<Encontro, String> torneioTR;
    @FXML
    private TableColumn<Encontro, Ronda> rondaTR;

    //FXML's da Tabela de Incoming Games
    @FXML
    private TableView<Encontro> tableIncomingGames;
    @FXML
    private TableColumn<Encontro, Date> dataTIG;
    @FXML
    private TableColumn<Encontro, Encontro> blueTeamTIG;
    @FXML
    private TableColumn<Encontro, String> vsTIG;
    @FXML
    private TableColumn<Encontro, Encontro> redTeamTIG;
    @FXML
    private TableColumn<Encontro, String> torneioTIG;
    @FXML
    private TableColumn<Encontro, Ronda> rondaTIG;

    private List<Equipa> teams;
    private ObservableList<Equipa> teamsObs;
    private Equipa eq;
    private ObservableList<Encontro> observableResults;
    private ObservableList<Encontro> observableIncomingGames;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.preencherListaEquipas();
        this.pesquisarNaLista();
        this.atribuirElementos();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void preencherListaEquipas() {
        // --BLL
        teams = EquipaServices.listaEquipas();
        teams.sort(Comparator.comparing((equipa) -> equipa.getNome().toLowerCase()));
        teamsObs = FXCollections.observableArrayList(teams);
        this.listaEquipas.setItems(teamsObs);
    }

    public void pesquisarNaLista() {
        searchBar.setOnKeyReleased((event) -> {
            listaEquipas.getSelectionModel().clearSelection();
            List<Equipa> temp = new ArrayList<>();
            String texto = searchBar.getText();
            if (texto.isEmpty()) {
                teamsObs = FXCollections.observableArrayList(teams);
                this.listaEquipas.setItems(teamsObs);
                temp.clear();
            } else {
                for (Equipa eq : teams) {
                    if (eq.getNome().toLowerCase().contains(texto.toLowerCase())) {
                        temp.add(eq);
                    }
                }
                teamsObs = FXCollections.observableArrayList(temp);
                this.listaEquipas.setItems(teamsObs);
            }

        });
    }

    public void atribuirElementos() {
        this.listaEquipas.getSelectionModel().selectedItemProperty().addListener((observable) -> {
            eq = (Equipa) listaEquipas.getSelectionModel().getSelectedItem();
            if (eq != null) {
                this.atribuirLogo(eq);
                this.atribuirLabels(eq);
                this.atribuirImagensJogadores(eq);
                this.atribuirLabelNomes(eq);
                this.atribuirImgPais(eq);
                this.atribuirNumTrofeus(eq);
                this.atribuirKDA(eq);
                this.initializeResultsTables(eq);
                this.initializeIncomingGamesTables(eq);
                this.borderRight.setVisible(true);
            }
        });
    }

    public void atribuirLogo(Equipa eq) {
        if(ImagesTeamServices.existsOnMap(eq.getNome())){
            imglogoTeam.setImage(new Image(ImagesTeamServices.getOriginalPath(eq.getNome())));
        }else{
           if (FXMLPlayersMainController.class.getResourceAsStream("pics/teams/" + eq.getSigla().toLowerCase() + ".png") != null) {
                imglogoTeam.setImage(new Image(FXMLPlayersMainController.class.getResourceAsStream("pics/teams/" + eq.getSigla().toLowerCase() + ".png")));
            } else {
                imglogoTeam.setImage(new Image(FXMLPlayersMainController.class.getResourceAsStream("pics/players/unknown.png")));
            } 
        }
    }

    public void atribuirImgPais(Equipa eq) {
        if (FXMLPlayersMainController.class.getResourceAsStream("pics/countries/" + eq.getPais().getSigla().toLowerCase() + ".png") != null) {
            imgCountry.setImage(new Image(FXMLPlayersMainController.class.getResourceAsStream("pics/countries/" + eq.getPais().getSigla().toLowerCase() + ".png")));
        } else {
            imgCountry.setImage(new Image(FXMLPlayersMainController.class.getResourceAsStream("pics/countries/unknown.png")));
        }
    }

    public void atribuirLabels(Equipa eq) {
        lblNomeEquipa.setText(eq.getNome());
        lblSiglaEquipa.setText(eq.getSigla());
    }

    public void atribuirImagensJogadores(Equipa eq) {

        if (eq.isAtivo()) {
            for (Membroequipa me : (Collection<Membroequipa>) eq.getMembroequipas()) {
                this.preencheStatsPlayerExistente(me, "TOP", imgPlayerTop, this.infoTop);
                this.preencheStatsPlayerExistente(me, "JNG", imgPlayerJungle, this.infoJungle);
                this.preencheStatsPlayerExistente(me, "MID", imgPlayerMid, this.infoMid);
                this.preencheStatsPlayerExistente(me, "ADC", imgPlayerADC, this.infoADC);
                this.preencheStatsPlayerExistente(me, "SUP", imgPlayerSupport, this.infoSupport);
            }
        } else {
            this.atribuirImagensInativos(eq);
        }
    }

    public void preencheStatsPlayerExistente(Membroequipa me, String pos, ImageView img, ImageView info) {
        if (me.getCargo().equals("player") && me.getPosicao().toString().equals(pos)) {
            
            if(ImagesMemberServices.existsOnMap(me.getNome())){
                img.setImage(new Image(ImagesMemberServices.getOriginalPath(me.getNome())));
            }else{
                if (FXMLPlayersMainController.class.getResourceAsStream("pics/players/" + me.getNome() + ".png") != null) {
                    img.setImage(new Image(FXMLPlayersMainController.class.getResourceAsStream("pics/players/" + me.getNome() + ".png")));
                } else {
                    img.setImage(new Image(FXMLPlayersMainController.class.getResourceAsStream("pics/players/unknown.png")));
                }
            }
            info.setVisible(true);
        }
    }

    public void preencheStatsPlayerInexistente(ImageView img, Label lbl, ImageView info) {
        img.setImage(new Image(FXMLPlayersMainController.class.getResourceAsStream("pics/players/unknown.png")));
        lbl.setText("[Player not assigned]");
        info.setVisible(false);
    }

    public void atribuirImagensInativos(Equipa eq) {
        boolean topFound = false;
        boolean jngFound = false;
        boolean midFound = false;
        boolean adcFound = false;
        boolean supFound = false;

        for (Membroequipa me : (Collection<Membroequipa>) eq.getMembroequipas()) {
            for (int i = 0; i < 5; i++) {
                switch (i) {
                    //caso TOP
                    case 0:
                        if (me.getPosicao()!= null && me.getPosicao().toString().equals("TOP")) {
                            topFound = true;
                            this.preencheStatsPlayerExistente(me, "TOP", imgPlayerTop, this.infoTop);
                        }
                        break;
                    case 1:
                        if (me.getPosicao()!= null && me.getPosicao().toString().equals("JNG")) {
                            jngFound = true;
                            this.preencheStatsPlayerExistente(me, "JNG", imgPlayerJungle, this.infoJungle);
                        }
                        break;
                    case 2:
                        if (me.getPosicao()!= null && me.getPosicao().toString().equals("MID")) {
                            midFound = true;
                            this.preencheStatsPlayerExistente(me, "MID", imgPlayerMid, this.infoMid);
                        }
                        break;
                    case 3:
                        if (me.getPosicao()!= null && me.getPosicao().toString().equals("ADC")) {
                            adcFound = true;
                            this.preencheStatsPlayerExistente(me, "ADC", imgPlayerADC, this.infoADC);
                        }
                        break;
                    case 4:
                        if (me.getPosicao()!= null && me.getPosicao().toString().equals("SUP")) {
                            supFound = true;
                            this.preencheStatsPlayerExistente(me, "SUP", imgPlayerSupport, this.infoSupport);
                        }
                        break;
                }
            }
        }

        if (!topFound) {
            this.preencheStatsPlayerInexistente(imgPlayerTop, lblNomeTop, this.infoTop);
        }
        if (!jngFound) {
            this.preencheStatsPlayerInexistente(imgPlayerJungle, lblNomeJungle, this.infoJungle);
        }
        if (!midFound) {
            this.preencheStatsPlayerInexistente(imgPlayerMid, lblNomeMid, this.infoMid);
        }
        if (!adcFound) {
            this.preencheStatsPlayerInexistente(imgPlayerADC, lblNomeADC, this.infoADC);
        }
        if (!supFound) {
            this.preencheStatsPlayerInexistente(imgPlayerSupport, lblNomeSupport, this.infoSupport);
        }
    }

    public void atribuirLabelNomes(Equipa eq) {
        for (Membroequipa me : (Collection<Membroequipa>) eq.getMembroequipas()) {
            if (me.getCargo().equals("player") && me.getPosicao().toString().equals("TOP")) {
                this.lblNomeTop.setText(me.getNome());
            }

            if (me.getCargo().equals("player") && me.getPosicao().toString().equals("JNG")) {
                this.lblNomeJungle.setText(me.getNome());
            }

            if (me.getCargo().equals("player") && me.getPosicao().toString().equals("MID")) {
                this.lblNomeMid.setText(me.getNome());
            }

            if (me.getCargo().equals("player") && me.getPosicao().toString().equals("ADC")) {
                this.lblNomeADC.setText(me.getNome());
            }

            if (me.getCargo().equals("player") && me.getPosicao().toString().equals("SUP")) {
                this.lblNomeSupport.setText(me.getNome());
            }
        }
    }

    public void atribuirNumTrofeus(Equipa eq) {
        // -- BLL
        List<Torneio> temp = TorneioServices.listaNumTrofeusEquipa(eq);
        this.lblTrofeus.setText("" + temp.size());
    }

    public void atribuirKDA(Equipa eq) {
        //Formula do KDA = (sumKills + sumAssists) / sumDeaths

        int totKills = 0;
        int totAssists = 0;
        int totDeaths = 0;
        float kdaRatio = 0;
        DecimalFormat df2 = new DecimalFormat(".##");

        //listas de jogos onde a Equipa jogou no lado da Equipa1 e do lado da Equipa2, respectivamente
        // -- BLL
        List<Jogo> listaJogosEquipa1 = JogoServices.listaJogosEquipa1(eq);
        // -- BLL
        List<Jogo> listaJogosEquipa2 = JogoServices.listaJogosEquipa2(eq);

        if (listaJogosEquipa1.isEmpty() && listaJogosEquipa2.isEmpty()) {
            this.lblKDA.setText("0");
        } else {
            for (Jogo j : listaJogosEquipa1) {
                totKills += j.getKillsequipa1().intValue();
                totAssists += j.getAssistsequipa1().intValue();
                totDeaths += j.getDeathsequipa1().intValue();
            }

            for (Jogo j : listaJogosEquipa2) {
                totKills += j.getKillsequipa2().intValue();
                totAssists += j.getAssistsequipa2().intValue();
                totDeaths += j.getDeathequipa2().intValue();
            }
            
            if(totDeaths == 0){
                kdaRatio = (float) (totKills + totAssists);
            }else{
                kdaRatio = (float) (totKills + totAssists) / totDeaths;
            }
                            
            if(kdaRatio == 0){
                this.lblKDA.setText("0");
            }else{
                this.lblKDA.setText(df2.format(kdaRatio));
        }
                
        }

    }

    public List<Encontro> getEncontrosFromTeam(Equipa eq) {
        //Todos os Encontros
        // -- BLL
        List<Encontro> encontrosFromTeam = EncontroServices.listaEncontrosFromTeam(eq);
        return encontrosFromTeam;
    }

    private void initializeResultsTables(Equipa eq) {
        String vs = "vs";
        List<Encontro> encontrosFromTeam = this.getEncontrosFromTeam(eq);

        //Lista onde vamos guardar os Encontros com o estado 'CONCLUIDO'
        List<Encontro> encontrosConcluidos = new ArrayList<>();

        for (Encontro e : encontrosFromTeam) {
            if (e.getEstado().equals("FINALIZADO")) {//NEEDS CHANGE - ESTADO = 'CONCLUIDO'
                encontrosConcluidos.add(e);
            }
        }

        //Tabela de Resultados
        this.tableResultados.refresh();
        this.dataTR.setCellValueFactory(new PropertyValueFactory<>("data"));
//        this.blueTeamTR.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getEquipaByEquipa1().getSigla()));
        this.vitoriasBlueTeamTR.setCellValueFactory(new PropertyValueFactory<>("vitoriaequipa1"));
        this.vsTR.setCellValueFactory(c -> new SimpleStringProperty(vs));
        this.vitoriasRedTeamTR.setCellValueFactory(new PropertyValueFactory<>("vitoriaequipa2"));

        this.torneioTR.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getRonda().getTorneio().getDescricao()));
        this.rondaTR.setCellValueFactory(new PropertyValueFactory<>("ronda"));
        this.redTeamTR.setCellValueFactory(c -> new SimpleObjectProperty<Encontro>(c.getValue()));
        this.blueTeamTR.setCellValueFactory(c -> new SimpleObjectProperty<Encontro>(c.getValue()));

        this.blueTeamTR.setCellFactory(param -> {
            ImageView imageview = new ImageView();
            imageview.setFitHeight(40);
            imageview.setFitWidth(40);
            ///imageview.setImage(imageComputer); //uncommenting this places the image on all cells, even empty ones
            //Set up the Table
            //imageview.setImage(new Image(FXMLPlayersMainController.class.getResourceAsStream("pics/teams/" + param.getEquipaByEquipa1().getSigla() + ".png")));

            TableCell<Encontro, Encontro> cell = new TableCell<Encontro, Encontro>() {

                @Override
                public void updateItem(Encontro item, boolean empty) {
                    if (item != null) {
                        //System.out.println("pics/teams/" + item.getEquipaByEquipa1().getSigla().toLowerCase() + ".png");
                        imageview.setImage(new Image(FXMLTeamsMainController.class.getResourceAsStream("pics/teams/" + item.getEquipaByEquipa1().getSigla().toLowerCase() + ".png")));
                    } else {
                        imageview.setImage(null);
                    }
                }
            };

            // Attach the imageview to the cell
            cell.setGraphic(imageview);
            return cell;
        });

        this.redTeamTR.setCellFactory(param -> {
            ImageView imageview = new ImageView();
            imageview.setFitHeight(40);
            imageview.setFitWidth(40);
            ///imageview.setImage(imageComputer); //uncommenting this places the image on all cells, even empty ones
            //Set up the Table
            //imageview.setImage(new Image(FXMLPlayersMainController.class.getResourceAsStream("pics/teams/" + param.getEquipaByEquipa1().getSigla() + ".png")));

            TableCell<Encontro, Encontro> cell = new TableCell<Encontro, Encontro>() {

                @Override
                public void updateItem(Encontro item, boolean empty) {
                    if (item != null) {
                        //System.out.println("pics/teams/" + item.getEquipaByEquipa1().getSigla().toLowerCase() + ".png");
                        imageview.setImage(new Image(FXMLTeamsMainController.class.getResourceAsStream("pics/teams/" + item.getEquipaByEquipa2().getSigla().toLowerCase() + ".png")));
                    } else {
                        imageview.setImage(null);
                    }
                }
            };

            // Attach the imageview to the cell
            cell.setGraphic(imageview);
            return cell;
        });

        //Atribuir os restultados obtidos à ObservableList
        this.observableResults = FXCollections.observableArrayList(encontrosConcluidos);

        //Atribuir os dados da ObservableList à tabela
        this.tableResultados.setItems(observableResults);

    }

    private void initializeIncomingGamesTables(Equipa eq) {
        String vs = "vs";
        List<Encontro> encontrosFromTeam = this.getEncontrosFromTeam(eq);

        //Lista onde vamos guardar os Encontros com o estado 'PENDENTE'
        List<Encontro> encontrosPendentes = new ArrayList<>();

        for (Encontro e : encontrosFromTeam) {
            if (e.getEstado().equals("CRIADO")) { //NEEDS CHANGE - ESTADO = 'PENDENTE'
                encontrosPendentes.add(e);
            }
        }

        //Tabela de Próximos Jogos
        this.tableResultados.refresh();
        this.dataTIG.setCellValueFactory(new PropertyValueFactory<>("data"));
        this.blueTeamTIG.setCellValueFactory(c -> new SimpleObjectProperty<Encontro>(c.getValue()));
        this.vsTIG.setCellValueFactory(c -> new SimpleStringProperty(vs));
        this.redTeamTIG.setCellValueFactory(c -> new SimpleObjectProperty<Encontro>(c.getValue()));
        this.torneioTIG.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getRonda().getTorneio().getDescricao()));
        this.rondaTIG.setCellValueFactory(new PropertyValueFactory<>("ronda"));
        //Atribuir os restultados obtidos à ObservableList

        this.blueTeamTIG.setCellFactory(param -> {
            ImageView imageview = new ImageView();
            imageview.setFitHeight(40);
            imageview.setFitWidth(40);
            ///imageview.setImage(imageComputer); //uncommenting this places the image on all cells, even empty ones
            //Set up the Table
            //imageview.setImage(new Image(FXMLPlayersMainController.class.getResourceAsStream("pics/teams/" + param.getEquipaByEquipa1().getSigla() + ".png")));

            TableCell<Encontro, Encontro> cell = new TableCell<Encontro, Encontro>() {

                @Override
                public void updateItem(Encontro item, boolean empty) {
                    if (item != null) {
                        //System.out.println("pics/teams/" + item.getEquipaByEquipa1().getSigla().toLowerCase() + ".png");
                        imageview.setImage(new Image(FXMLTeamsMainController.class.getResourceAsStream("pics/teams/" + item.getEquipaByEquipa1().getSigla().toLowerCase() + ".png")));
                    } else {
                        imageview.setImage(null);
                    }
                }
            };

            // Attach the imageview to the cell
            cell.setGraphic(imageview);
            return cell;
        });

        this.redTeamTIG.setCellFactory(param -> {
            ImageView imageview = new ImageView();
            imageview.setFitHeight(40);
            imageview.setFitWidth(40);
            ///imageview.setImage(imageComputer); //uncommenting this places the image on all cells, even empty ones
            //Set up the Table
            //imageview.setImage(new Image(FXMLPlayersMainController.class.getResourceAsStream("pics/teams/" + param.getEquipaByEquipa1().getSigla() + ".png")));

            TableCell<Encontro, Encontro> cell = new TableCell<Encontro, Encontro>() {

                @Override
                public void updateItem(Encontro item, boolean empty) {
                    if (item != null) {
                        //System.out.println("pics/teams/" + item.getEquipaByEquipa1().getSigla().toLowerCase() + ".png");
                        imageview.setImage(new Image(FXMLTeamsMainController.class.getResourceAsStream("pics/teams/" + item.getEquipaByEquipa2().getSigla().toLowerCase() + ".png")));
                    } else {
                        imageview.setImage(null);
                    }
                }
            };

            // Attach the imageview to the cell
            cell.setGraphic(imageview);
            return cell;
        });

        this.observableIncomingGames = FXCollections.observableArrayList(encontrosPendentes);

        //Atribuir os dados da ObservableList à tabela
        this.tableIncomingGames.setItems(observableIncomingGames);
    }

    @FXML
    public void handleImageAction(MouseEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLPopUpPlayer.fxml"));
        Parent root = loader.load();
        FXMLPopUpPlayerController controller = loader.getController();

        //Metodo para preencher a Janela de PopUp
        this.preparePopUpElements(event, controller);

        this.prepareStage(root);
    }

    public void prepareStage(Parent root) {
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.setY(350);
        stage.setX(700);
        stage.getIcons().add(new Image(LolUI.class.getResourceAsStream("pics/lol.png")));
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(this.infoTop.getScene().getWindow());
        stage.addEventHandler(KeyEvent.KEY_RELEASED, (KeyEvent event) -> {
            if (KeyCode.ESCAPE == event.getCode()) {
                stage.close();
            }
        });
        stage.showAndWait();
    }

    public void preparePopUpElements(MouseEvent event, FXMLPopUpPlayerController controller) {
        if (event.getSource().toString().contains("infoTop")) {
            controller.setPlayerImage(this.eq, "TOP");
            controller.setCountryImage(this.eq, "TOP");
            controller.setPlayerName(this.eq, "TOP");
            controller.setPlayerAge(this.eq, "TOP");
            controller.setTeamImage(this.eq, "TOP");
            controller.setPositionImage(this.eq, "TOP");
            controller.setTop3Champs(this.eq, "TOP");
            controller.setKDA(this.eq, "TOP");
        }

        if (event.getSource().toString().contains("infoJungle")) {
            controller.setPlayerImage(this.eq, "JNG");
            controller.setCountryImage(this.eq, "JNG");
            controller.setPlayerName(this.eq, "JNG");
            controller.setPlayerAge(this.eq, "JNG");
            controller.setTeamImage(this.eq, "JNG");
            controller.setPositionImage(this.eq, "JNG");
            controller.setTop3Champs(this.eq, "JNG");
            controller.setKDA(this.eq, "JNG");
        }

        if (event.getSource().toString().contains("infoMid")) {
            controller.setPlayerImage(this.eq, "MID");
            controller.setCountryImage(this.eq, "MID");
            controller.setPlayerName(this.eq, "MID");
            controller.setPlayerAge(this.eq, "MID");
            controller.setTeamImage(this.eq, "MID");
            controller.setPositionImage(this.eq, "MID");
            controller.setTop3Champs(this.eq, "MID");
            controller.setKDA(this.eq, "MID");
        }

        if (event.getSource().toString().contains("infoADC")) {
            controller.setPlayerImage(this.eq, "ADC");
            controller.setCountryImage(this.eq, "ADC");
            controller.setPlayerName(this.eq, "ADC");
            controller.setPlayerAge(this.eq, "ADC");
            controller.setTeamImage(this.eq, "ADC");
            controller.setPositionImage(this.eq, "ADC");
            controller.setTop3Champs(this.eq, "ADC");
            controller.setKDA(this.eq, "ADC");
        }

        if (event.getSource().toString().contains("infoSupport")) {
            controller.setPlayerImage(this.eq, "SUP");
            controller.setCountryImage(this.eq, "SUP");
            controller.setPlayerName(this.eq, "SUP");
            controller.setPlayerAge(this.eq, "SUP");
            controller.setTeamImage(this.eq, "SUP");
            controller.setPositionImage(this.eq, "SUP");
            controller.setTop3Champs(this.eq, "SUP");
            controller.setKDA(this.eq, "SUP");
        }
    }

    @FXML
    public void animateImagesOnEnter() {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), this.imglogoTeam);
        scaleTransition.setFromX(1f);
        scaleTransition.setFromY(1f);
        scaleTransition.setToX(1.1f);
        scaleTransition.setToY(1.1f);
        scaleTransition.playFromStart();
    }

    @FXML
    public void animateImagesOnExit() {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100), this.imglogoTeam);
        scaleTransition.setFromX(1.1f);
        scaleTransition.setFromY(1.1f);
        scaleTransition.setToX(1f);
        scaleTransition.setToY(1f);
        scaleTransition.playFromStart();
    }

}
