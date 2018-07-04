/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lolui;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lolbll.EncontroServices;
import lolbll.EquipaServices;
import lolbll.EquipaTorneioServices;
import lolbll.HibernateBLL;
import lolbll.RegiaoServices;
import lolbll.TorneioServices;
import loldal.model.Encontro;
import loldal.model.Equipa;
import loldal.model.Equipatorneio;
import loldal.model.Pais;
import loldal.model.Regiao;
import loldal.model.Ronda;
import loldal.model.Torneio;
import lolui.exceptions.InsertTournamentDBException;

/**
 * FXML Controller class
 *
 * @author vaaz
 */
public class FXMLManageInfoTournamentController implements Initializable {

    @FXML
    private ImageView imgBack;

    @FXML
    private BorderPane parentBorderPane;

    @FXML
    private ComboBox comboType;

    @FXML
    private ListView listaTorneios;

    @FXML
    private TextField searchBar;

    @FXML
    private ImageView imgCountryRegion;

    @FXML
    private DatePicker dataInicio;

    @FXML
    private Spinner<Integer> spinPrize;

    @FXML
    private RadioButton rb8Teams;

    @FXML
    private RadioButton rb16Teams;

    @FXML
    private TextField txtFullName;

    @FXML
    private TextField txtInitials;

    //ImageViews e Labels das Equipas existentes no torneio
    @FXML
    private ImageView imgMatch1BlueTeam;
    @FXML
    private ImageView imgMatch1RedTeam;
    @FXML
    private ImageView imgMatch2BlueTeam;
    @FXML
    private ImageView imgMatch2RedTeam;
    @FXML
    private ImageView imgMatch3BlueTeam;
    @FXML
    private ImageView imgMatch3RedTeam;
    @FXML
    private ImageView imgMatch4BlueTeam;
    @FXML
    private ImageView imgMatch4RedTeam;
    @FXML
    private ImageView imgMatch5BlueTeam;
    @FXML
    private ImageView imgMatch5RedTeam;
    @FXML
    private ImageView imgMatch6BlueTeam;
    @FXML
    private ImageView imgMatch6RedTeam;
    @FXML
    private ImageView imgMatch7BlueTeam;
    @FXML
    private ImageView imgMatch7RedTeam;
    @FXML
    private ImageView imgMatch8BlueTeam;
    @FXML
    private ImageView imgMatch8RedTeam;

    @FXML
    private Label lblMatch1BlueTeam;
    @FXML
    private Label lblMatch1RedTeam;
    @FXML
    private Label lblMatch2BlueTeam;
    @FXML
    private Label lblMatch2RedTeam;
    @FXML
    private Label lblMatch3BlueTeam;
    @FXML
    private Label lblMatch3RedTeam;
    @FXML
    private Label lblMatch4BlueTeam;
    @FXML
    private Label lblMatch4RedTeam;
    @FXML
    private Label lblMatch5BlueTeam;
    @FXML
    private Label lblMatch5RedTeam;
    @FXML
    private Label lblMatch6BlueTeam;
    @FXML
    private Label lblMatch6RedTeam;
    @FXML
    private Label lblMatch7BlueTeam;
    @FXML
    private Label lblMatch7RedTeam;
    @FXML
    private Label lblMatch8BlueTeam;
    @FXML
    private Label lblMatch8RedTeam;

    private List<Torneio> listaPesquisa;

    private ObservableList<Torneio> torneiosObs;

    private Torneio t;

    private Pais pais;

    private Regiao regiao;

    private List<Equipa> listaSelecionados;

    private List<Equipa> listaFiltrada; //lista que contem as equipas de um Pais/Regiao

    private Equipa equipa;

    //Torneio
    private String fullName;
    private String initials;
    private Date dataInicioTorneio;
    private int prizepool;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        parentBorderPane.getStyleClass().add("borderPane");
        comboType.getItems().addAll("Country", "Region", "All");
        comboType.getSelectionModel().selectLast();
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(20000, 25000000, 20000, 20000);
        spinPrize.setValueFactory(valueFactory);
        this.listarTorneios();
        this.pesquisarNaLista();
        this.atribuirElementos();
    }

    public void atribuirElementos() {
        this.listaTorneios.getSelectionModel().selectedItemProperty().addListener((observable) -> {
            t = (Torneio) listaTorneios.getSelectionModel().getSelectedItem();
            if (t != null) {
                this.setComboType(t);
                this.setRButtonSelected(t);
                this.setTextFieldsAndSpinner(t);
                this.setShowTeamList(t);
                this.setDate(t);
                this.setTeamList(t);
                this.setListas(t);
            }
        });
    }

    public void setListas(Torneio t) {
        this.listaSelecionados = new ArrayList<>();
        for (Equipatorneio et : (Set<Equipatorneio>) t.getEquipatorneios()) {
            listaSelecionados.add(et.getEquipa());
        }
    }

    public void setTeamList(Torneio t) {
        List<Ronda> rondas = new ArrayList<>();
        rondas.addAll(t.getRondas());
        rondas.sort(Comparator.comparing((ronda) -> ronda.getId()));
        List<Encontro> encontros = new ArrayList<>();
        encontros.addAll(rondas.get(0).getEncontros());
        encontros.sort(Comparator.comparing((encontro) -> encontro.getId()));

        if (t.getEquipatorneios().size() == 8) {
            lblMatch1BlueTeam.setText(encontros.get(0).getEquipaByEquipa1().getNome());
            lblMatch1RedTeam.setText(encontros.get(0).getEquipaByEquipa2().getNome());
            lblMatch2BlueTeam.setText(encontros.get(1).getEquipaByEquipa1().getNome());
            lblMatch2RedTeam.setText(encontros.get(1).getEquipaByEquipa2().getNome());
            lblMatch3BlueTeam.setText(encontros.get(2).getEquipaByEquipa1().getNome());
            lblMatch3RedTeam.setText(encontros.get(2).getEquipaByEquipa2().getNome());
            lblMatch4BlueTeam.setText(encontros.get(3).getEquipaByEquipa1().getNome());
            lblMatch4RedTeam.setText(encontros.get(3).getEquipaByEquipa2().getNome());
        }
        if (t.getEquipatorneios().size() == 16) {
            lblMatch1BlueTeam.setText(encontros.get(0).getEquipaByEquipa1().getNome());
            lblMatch1RedTeam.setText(encontros.get(0).getEquipaByEquipa2().getNome());
            lblMatch2BlueTeam.setText(encontros.get(1).getEquipaByEquipa1().getNome());
            lblMatch2RedTeam.setText(encontros.get(1).getEquipaByEquipa2().getNome());
            lblMatch3BlueTeam.setText(encontros.get(2).getEquipaByEquipa1().getNome());
            lblMatch3RedTeam.setText(encontros.get(2).getEquipaByEquipa2().getNome());
            lblMatch4BlueTeam.setText(encontros.get(3).getEquipaByEquipa1().getNome());
            lblMatch4RedTeam.setText(encontros.get(3).getEquipaByEquipa2().getNome());
            lblMatch5BlueTeam.setText(encontros.get(4).getEquipaByEquipa1().getNome());
            lblMatch5RedTeam.setText(encontros.get(4).getEquipaByEquipa2().getNome());
            lblMatch6BlueTeam.setText(encontros.get(5).getEquipaByEquipa1().getNome());
            lblMatch6RedTeam.setText(encontros.get(5).getEquipaByEquipa2().getNome());
            lblMatch7BlueTeam.setText(encontros.get(6).getEquipaByEquipa1().getNome());
            lblMatch7RedTeam.setText(encontros.get(6).getEquipaByEquipa2().getNome());
            lblMatch8BlueTeam.setText(encontros.get(7).getEquipaByEquipa1().getNome());
            lblMatch8RedTeam.setText(encontros.get(7).getEquipaByEquipa2().getNome());
        }
    }

    public void setDate(Torneio t) {
        DateFormat df = new SimpleDateFormat("yy.MM.dd");
        Date today = t.getDatafim();
        String reportDate = df.format(today);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy.MM.dd");
        LocalDate localDate = LocalDate.parse(reportDate, formatter);
        Date date = new Date(10, 10, 10);
        this.dataInicio.setValue(localDate);
    }

    public void setShowTeamList(Torneio t) {
        if (t.getEquipatorneios().size() == 8) {
            imgMatch1BlueTeam.setVisible(true);
            imgMatch1RedTeam.setVisible(true);
            imgMatch2BlueTeam.setVisible(true);
            imgMatch2RedTeam.setVisible(true);
            imgMatch3BlueTeam.setVisible(true);
            imgMatch3RedTeam.setVisible(true);
            imgMatch4BlueTeam.setVisible(true);
            imgMatch4RedTeam.setVisible(true);
            imgMatch5BlueTeam.setVisible(false);
            imgMatch5RedTeam.setVisible(false);
            imgMatch6BlueTeam.setVisible(false);
            imgMatch6RedTeam.setVisible(false);
            imgMatch7BlueTeam.setVisible(false);
            imgMatch7RedTeam.setVisible(false);
            imgMatch8BlueTeam.setVisible(false);
            imgMatch8RedTeam.setVisible(false);

            lblMatch1BlueTeam.setVisible(true);
            lblMatch1RedTeam.setVisible(true);
            lblMatch2BlueTeam.setVisible(true);
            lblMatch2RedTeam.setVisible(true);
            lblMatch3BlueTeam.setVisible(true);
            lblMatch3RedTeam.setVisible(true);
            lblMatch4BlueTeam.setVisible(true);
            lblMatch4RedTeam.setVisible(true);
            lblMatch5BlueTeam.setVisible(false);
            lblMatch5RedTeam.setVisible(false);
            lblMatch6BlueTeam.setVisible(false);
            lblMatch6RedTeam.setVisible(false);
            lblMatch7BlueTeam.setVisible(false);
            lblMatch7RedTeam.setVisible(false);
            lblMatch8BlueTeam.setVisible(false);
            lblMatch8RedTeam.setVisible(false);
        }
        if (t.getEquipatorneios().size() == 16) {
            imgMatch1BlueTeam.setVisible(true);
            imgMatch1RedTeam.setVisible(true);
            imgMatch2BlueTeam.setVisible(true);
            imgMatch2RedTeam.setVisible(true);
            imgMatch3BlueTeam.setVisible(true);
            imgMatch3RedTeam.setVisible(true);
            imgMatch4BlueTeam.setVisible(true);
            imgMatch4RedTeam.setVisible(true);
            imgMatch5BlueTeam.setVisible(true);
            imgMatch5RedTeam.setVisible(true);
            imgMatch6BlueTeam.setVisible(true);
            imgMatch6RedTeam.setVisible(true);
            imgMatch7BlueTeam.setVisible(true);
            imgMatch7RedTeam.setVisible(true);
            imgMatch8BlueTeam.setVisible(true);
            imgMatch8RedTeam.setVisible(true);

            lblMatch1BlueTeam.setVisible(true);
            lblMatch1RedTeam.setVisible(true);
            lblMatch2BlueTeam.setVisible(true);
            lblMatch2RedTeam.setVisible(true);
            lblMatch3BlueTeam.setVisible(true);
            lblMatch3RedTeam.setVisible(true);
            lblMatch4BlueTeam.setVisible(true);
            lblMatch4RedTeam.setVisible(true);
            lblMatch5BlueTeam.setVisible(true);
            lblMatch5RedTeam.setVisible(true);
            lblMatch6BlueTeam.setVisible(true);
            lblMatch6RedTeam.setVisible(true);
            lblMatch7BlueTeam.setVisible(true);
            lblMatch7RedTeam.setVisible(true);
            lblMatch8BlueTeam.setVisible(true);
            lblMatch8RedTeam.setVisible(true);
        }
    }

    public void showList() {
        if (rb8Teams.isSelected()) {
            imgMatch1BlueTeam.setVisible(true);
            imgMatch1RedTeam.setVisible(true);
            imgMatch2BlueTeam.setVisible(true);
            imgMatch2RedTeam.setVisible(true);
            imgMatch3BlueTeam.setVisible(true);
            imgMatch3RedTeam.setVisible(true);
            imgMatch4BlueTeam.setVisible(true);
            imgMatch4RedTeam.setVisible(true);
            imgMatch5BlueTeam.setVisible(false);
            imgMatch5RedTeam.setVisible(false);
            imgMatch6BlueTeam.setVisible(false);
            imgMatch6RedTeam.setVisible(false);
            imgMatch7BlueTeam.setVisible(false);
            imgMatch7RedTeam.setVisible(false);
            imgMatch8BlueTeam.setVisible(false);
            imgMatch8RedTeam.setVisible(false);

            lblMatch1BlueTeam.setVisible(true);
            lblMatch1RedTeam.setVisible(true);
            lblMatch2BlueTeam.setVisible(true);
            lblMatch2RedTeam.setVisible(true);
            lblMatch3BlueTeam.setVisible(true);
            lblMatch3RedTeam.setVisible(true);
            lblMatch4BlueTeam.setVisible(true);
            lblMatch4RedTeam.setVisible(true);
            lblMatch5BlueTeam.setVisible(false);
            lblMatch5RedTeam.setVisible(false);
            lblMatch6BlueTeam.setVisible(false);
            lblMatch6RedTeam.setVisible(false);
            lblMatch7BlueTeam.setVisible(false);
            lblMatch7RedTeam.setVisible(false);
            lblMatch8BlueTeam.setVisible(false);
            lblMatch8RedTeam.setVisible(false);
        }
        if (rb16Teams.isSelected()) {
            imgMatch1BlueTeam.setVisible(true);
            imgMatch1RedTeam.setVisible(true);
            imgMatch2BlueTeam.setVisible(true);
            imgMatch2RedTeam.setVisible(true);
            imgMatch3BlueTeam.setVisible(true);
            imgMatch3RedTeam.setVisible(true);
            imgMatch4BlueTeam.setVisible(true);
            imgMatch4RedTeam.setVisible(true);
            imgMatch5BlueTeam.setVisible(true);
            imgMatch5RedTeam.setVisible(true);
            imgMatch6BlueTeam.setVisible(true);
            imgMatch6RedTeam.setVisible(true);
            imgMatch7BlueTeam.setVisible(true);
            imgMatch7RedTeam.setVisible(true);
            imgMatch8BlueTeam.setVisible(true);
            imgMatch8RedTeam.setVisible(true);

            lblMatch1BlueTeam.setVisible(true);
            lblMatch1RedTeam.setVisible(true);
            lblMatch2BlueTeam.setVisible(true);
            lblMatch2RedTeam.setVisible(true);
            lblMatch3BlueTeam.setVisible(true);
            lblMatch3RedTeam.setVisible(true);
            lblMatch4BlueTeam.setVisible(true);
            lblMatch4RedTeam.setVisible(true);
            lblMatch5BlueTeam.setVisible(true);
            lblMatch5RedTeam.setVisible(true);
            lblMatch6BlueTeam.setVisible(true);
            lblMatch6RedTeam.setVisible(true);
            lblMatch7BlueTeam.setVisible(true);
            lblMatch7RedTeam.setVisible(true);
            lblMatch8BlueTeam.setVisible(true);
            lblMatch8RedTeam.setVisible(true);
        }
    }

    public void setComboType(Torneio t) {
        if (t.getTipo().equals(new BigDecimal(1))) {
            this.comboType.getSelectionModel().select(2);
            this.comboType.setDisable(true);
            listaFiltrada = EquipaServices.listaEquipasAtivas();
        }
        if (t.getTipo().equals(new BigDecimal(2))) {
            this.comboType.getSelectionModel().select(1);
            this.comboType.setDisable(true);
            this.imgCountryRegion.setImage(new Image(getClass().getResourceAsStream("pics/regions/" + t.getRegiao().getSigla().toLowerCase() + ".png")));
            this.imgCountryRegion.setOnMouseEntered((event) -> {
                Tooltip.install(this.imgCountryRegion, new Tooltip(t.getRegiao().getDescricao()));
            });
            regiao = t.getRegiao();
            listaFiltrada = EquipaServices.listaEquipasRegiao(regiao.getSigla());
        }
        if (t.getTipo().equals(new BigDecimal(3))) {
            List<Equipatorneio> listaET = new ArrayList<>();
            listaET.addAll(t.getEquipatorneios());
            this.comboType.getSelectionModel().select(0);
            this.comboType.setDisable(true);
            this.imgCountryRegion.setImage(new Image(getClass().getResourceAsStream("pics/countries/" + listaET.get(0).getEquipa().getPais().getSigla().toLowerCase() + ".png")));
            this.imgCountryRegion.setOnMouseEntered((event) -> {
                Tooltip.install(this.imgCountryRegion, new Tooltip(listaET.get(0).getEquipa().getPais().getNome()));
            });
            pais = listaET.get(0).getEquipa().getPais();
            listaFiltrada = EquipaServices.listaEquipasPais(pais.getSigla());
        }
    }

    @FXML
    public void createTeamAction(MouseEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLTeamSelection.fxml"));
        Parent root = loader.load();
        FXMLTeamSelectionController controller = loader.getController();

        controller.preencheGridEquipasFiltrada(this.listaFiltradaDisponiveis());
        //Metodo para preencher a Janela de PopUp
        this.prepareTeamStage(event, root, controller);
    }

    public void prepareTeamStage(MouseEvent event, Parent root, FXMLTeamSelectionController controller) {
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
        if (controller.getEquipaSelected() != null) {
            this.equipa = controller.getEquipaSelected();
            if (!verificaEquipaExisteLista(this.equipa)) {
                if (event.getSource().toString().contains(imgMatch1BlueTeam.getId())) {
                    this.substituiDaLista(equipa, lblMatch1BlueTeam);
                }
                if (event.getSource().toString().contains(imgMatch1RedTeam.getId())) {
                    this.substituiDaLista(equipa, lblMatch1RedTeam);
                }
                if (event.getSource().toString().contains(imgMatch2BlueTeam.getId())) {
                    this.substituiDaLista(equipa, lblMatch2BlueTeam);
                }
                if (event.getSource().toString().contains(imgMatch2RedTeam.getId())) {
                    this.substituiDaLista(equipa, lblMatch2RedTeam);
                }
                if (event.getSource().toString().contains(imgMatch3BlueTeam.getId())) {
                    this.substituiDaLista(equipa, lblMatch3BlueTeam);
                }
                if (event.getSource().toString().contains(imgMatch3RedTeam.getId())) {
                    this.substituiDaLista(equipa, lblMatch3RedTeam);
                }
                if (event.getSource().toString().contains(imgMatch4BlueTeam.getId())) {
                    this.substituiDaLista(equipa, lblMatch4BlueTeam);
                }
                if (event.getSource().toString().contains(imgMatch4RedTeam.getId())) {
                    this.substituiDaLista(equipa, lblMatch4RedTeam);
                }
                if (event.getSource().toString().contains(imgMatch5BlueTeam.getId())) {
                    this.substituiDaLista(equipa, lblMatch5BlueTeam);
                }
                if (event.getSource().toString().contains(imgMatch5RedTeam.getId())) {
                    this.substituiDaLista(equipa, lblMatch5RedTeam);
                }
                if (event.getSource().toString().contains(imgMatch6BlueTeam.getId())) {
                    this.substituiDaLista(equipa, lblMatch6BlueTeam);
                }
                if (event.getSource().toString().contains(imgMatch6RedTeam.getId())) {
                    this.substituiDaLista(equipa, lblMatch6RedTeam);
                }
                if (event.getSource().toString().contains(imgMatch7BlueTeam.getId())) {
                    this.substituiDaLista(equipa, lblMatch7BlueTeam);
                }
                if (event.getSource().toString().contains(imgMatch7RedTeam.getId())) {
                    this.substituiDaLista(equipa, lblMatch7RedTeam);
                }
                if (event.getSource().toString().contains(imgMatch8BlueTeam.getId())) {
                    this.substituiDaLista(equipa, lblMatch8BlueTeam);
                }
                if (event.getSource().toString().contains(imgMatch8RedTeam.getId())) {
                    this.substituiDaLista(equipa, lblMatch8RedTeam);
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("Something went wrong.");
                alert.setContentText("The selected team is already in the tournament.");
                alert.showAndWait();
            }
        }
    }

    public List<Equipa> listaFiltradaDisponiveis() {
        List<Equipa> temp = new ArrayList<>();

        for (Equipa eq : listaFiltrada) {
            if (!listaSelecionados.contains(eq)) {
                temp.add(eq);
            }
        }
        return temp;
    }

    public boolean verificaEquipaExisteLista(Equipa equipa) {
        boolean existe = false;
        for (Equipa eq : listaSelecionados) {
            if (eq.equals(equipa)) {
                existe = true;
            }
        }
        return existe;
    }

    public void substituiDaLista(Equipa equipa, Label lbl) {
        if (equipa != null && !equipa.getNome().equals(lbl.getText())) {
            Iterator<Equipa> it = this.listaSelecionados.iterator();
            while (it.hasNext()) {
                Equipa eq = it.next();
                if (eq.getNome().equals(lbl.getText())) {
                    it.remove();
                }
            }
            listaSelecionados.add(equipa);
            lbl.setText(equipa.getNome());
        }
    }

    public void setTextFieldsAndSpinner(Torneio t) {
        this.txtFullName.setText(t.getNome());
        this.txtInitials.setText(t.getSigla());
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(20000, 25000000, t.getPremio().intValue(), 20000);
        spinPrize.setValueFactory(valueFactory);
        this.spinPrize.setValueFactory(valueFactory);
    }

    public void setRButtonSelected(Torneio t) {
        if (t.getEquipatorneios().size() == 8) {
            this.rb8Teams.setSelected(true);
        }
        if (t.getEquipatorneios().size() == 16) {
            this.rb16Teams.setSelected(true);
        }
    }

    public void listarTorneios() {
        // -- CRIAR MÉTODO NA BLL
        listaPesquisa = TorneioServices.listaTorneiosNaoInicializados();
        listaPesquisa.sort(Comparator.comparing((torneio) -> torneio.getDatafim()));
        Collections.reverse(listaPesquisa);
        torneiosObs = FXCollections.observableArrayList(listaPesquisa);
        this.listaTorneios.setItems(torneiosObs);
        System.out.println(listaPesquisa);
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

    @FXML
    public void closePopUp() {
        Stage stage = (Stage) this.imgBack.getScene().getWindow();
        stage.close();
    }

    public void verificaTorneio() throws InsertTournamentDBException {
        //verificar nome do torneio (se já existe e tamanho)
        //descrição igual ao nome
        //verificar initials (tamanho)
        //verificar se a lista com equipas selecionadas contêm 8 ou 16 elementos consoante o RadioButton
        //verificar se a data nao esta vazia

        if (txtFullName.getText().isEmpty()) {
            throw new InsertTournamentDBException("Please add Tournament name");
        }

        if (txtFullName.getText().length() > 50) {
            throw new InsertTournamentDBException("Tournament Name too long, maximum 50 characters");
        }

//        for (Torneio torneio : TorneioServices.listaTorneios()) {
//            if (!torneio.equals(t) && torneio.getNome().toLowerCase().equals(txtFullName.getText().toLowerCase())) {
//                throw new InsertTournamentDBException("Tournament Name already exists");
//            }
//        }

        if (!txtFullName.getText().toLowerCase().equals(t.getNome().toLowerCase())){
            for (Torneio torneio : TorneioServices.listaTorneios()) {
                if (txtFullName.getText().toLowerCase().equals(torneio.getNome().toLowerCase())) {
                    throw new InsertTournamentDBException("Tournament Name already exists");
                }
            }
        }

        if (txtInitials.getText().isEmpty()) {
            throw new InsertTournamentDBException("Please add Tournament initials");
        }

        if (txtInitials.getText().length() > 3 || txtInitials.getText().contains(" ")) {
            throw new InsertTournamentDBException("Tournament initials can't have spaces and/or are too long, maximum 3 characters");
        }

        if (rb8Teams.isSelected() && listaSelecionados.size() != 8) {
            throw new InsertTournamentDBException("Please fill all the team's list");
        }

        if (rb16Teams.isSelected() && listaSelecionados.size() != 16) {
            throw new InsertTournamentDBException("Please fill all the team's list");
        }

        if (dataInicio.getValue() == null) {
            throw new InsertTournamentDBException("Please add a Date");
        }

        if (!rb8Teams.isSelected() && !rb16Teams.isSelected()) {
            throw new InsertTournamentDBException("Please select the number of teams");
        }

        if (listaSelecionados.isEmpty()) {
            throw new InsertTournamentDBException("Please select the number of teams");
        }
    }
    
    
    
    

    public void getUserInput() {
        fullName = txtFullName.getText();
        initials = txtInitials.getText();
        dataInicioTorneio = java.sql.Date.valueOf(dataInicio.getValue());
        prizepool = spinPrize.getValue();
    }

    @FXML
    public void saveOnClick() {
        this.save();
        HibernateBLL.clearCache();
    }

    public void save() {
        try {
            System.out.println(t.getEquipatorneios());
            this.verificaTorneio();
            this.getUserInput();
            this.gravarTorneio();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Operation Successfull");
            alert.setContentText("Your new member was created!");
            alert.showAndWait();
            this.closePopUp();
            System.out.println(t.getEquipatorneios());
        } catch (InsertTournamentDBException ex) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Something went wrong.");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }

    public void gravarTorneio() {
        t.setNome(fullName);
        t.setDescricao(fullName);
        t.setSigla(initials);
        t.setDatafim(dataInicioTorneio);
        t.setPremio(new BigDecimal(prizepool));
        this.eliminaEquipasTorneioOriginais();
        this.adicionaEquipasTorneio();
        this.atribuiEquipaEncontros();
    }
    
    public void atribuiEquipaEncontros(){
        List<Ronda> rondas = new ArrayList<>();
        rondas.addAll(t.getRondas());
        rondas.sort(Comparator.comparing((ronda) -> ronda.getId()));
        List<Encontro> encontros = new ArrayList<>();
        encontros.addAll(rondas.get(0).getEncontros());
        encontros.sort(Comparator.comparing((encontro) -> encontro.getId()));
        
        if(rb8Teams.isSelected()){
            encontros.get(0).setEquipaByEquipa1(this.equipaLabel(lblMatch1BlueTeam));
            encontros.get(0).setEquipaByEquipa2(this.equipaLabel(lblMatch1RedTeam));
            encontros.get(1).setEquipaByEquipa1(this.equipaLabel(lblMatch2BlueTeam));
            encontros.get(1).setEquipaByEquipa2(this.equipaLabel(lblMatch2RedTeam));
            encontros.get(2).setEquipaByEquipa1(this.equipaLabel(lblMatch3BlueTeam));
            encontros.get(2).setEquipaByEquipa2(this.equipaLabel(lblMatch3RedTeam));
            encontros.get(3).setEquipaByEquipa1(this.equipaLabel(lblMatch4BlueTeam));
            encontros.get(3).setEquipaByEquipa2(this.equipaLabel(lblMatch4RedTeam));
            EncontroServices.updateEncontro(encontros.get(0));
            EncontroServices.updateEncontro(encontros.get(1));
            EncontroServices.updateEncontro(encontros.get(2));
            EncontroServices.updateEncontro(encontros.get(3));
        }
        if(rb16Teams.isSelected()){
            encontros.get(0).setEquipaByEquipa1(this.equipaLabel(lblMatch1BlueTeam));
            encontros.get(0).setEquipaByEquipa2(this.equipaLabel(lblMatch1RedTeam));
            encontros.get(1).setEquipaByEquipa1(this.equipaLabel(lblMatch2BlueTeam));
            encontros.get(1).setEquipaByEquipa2(this.equipaLabel(lblMatch2RedTeam));
            encontros.get(2).setEquipaByEquipa1(this.equipaLabel(lblMatch3BlueTeam));
            encontros.get(2).setEquipaByEquipa2(this.equipaLabel(lblMatch3RedTeam));
            encontros.get(3).setEquipaByEquipa1(this.equipaLabel(lblMatch4BlueTeam));
            encontros.get(3).setEquipaByEquipa2(this.equipaLabel(lblMatch4RedTeam));
            encontros.get(4).setEquipaByEquipa1(this.equipaLabel(lblMatch5BlueTeam));
            encontros.get(4).setEquipaByEquipa2(this.equipaLabel(lblMatch5RedTeam));
            encontros.get(5).setEquipaByEquipa1(this.equipaLabel(lblMatch6BlueTeam));
            encontros.get(5).setEquipaByEquipa2(this.equipaLabel(lblMatch6RedTeam));
            encontros.get(6).setEquipaByEquipa1(this.equipaLabel(lblMatch7BlueTeam));
            encontros.get(6).setEquipaByEquipa2(this.equipaLabel(lblMatch7RedTeam));
            encontros.get(7).setEquipaByEquipa1(this.equipaLabel(lblMatch8BlueTeam));
            encontros.get(7).setEquipaByEquipa2(this.equipaLabel(lblMatch8RedTeam));
            EncontroServices.updateEncontro(encontros.get(0));
            EncontroServices.updateEncontro(encontros.get(1));
            EncontroServices.updateEncontro(encontros.get(2));
            EncontroServices.updateEncontro(encontros.get(3));
            EncontroServices.updateEncontro(encontros.get(4));
            EncontroServices.updateEncontro(encontros.get(5));
            EncontroServices.updateEncontro(encontros.get(6));
            EncontroServices.updateEncontro(encontros.get(7));
        }
        
    }
    
    public Equipa equipaLabel(Label lbl){
        Equipa eq = new Equipa();
        for(Equipa e: listaSelecionados){
            if(e.getNome().equals(lbl.getText())){
                eq = e;
            }
        }
        return eq;
    }

    public void eliminaEquipasTorneioOriginais() {
        List<Equipatorneio> list = EquipaTorneioServices.getListaDeTorneio(t);
        for (Equipatorneio et : list) {
            EquipaTorneioServices.deleteEquipaTorneio(et);
        }
    }

    public void adicionaEquipasTorneio() {
        for (Equipa eq : listaSelecionados) {
            Equipatorneio et = new Equipatorneio();
            et.setEquipa(eq);
            et.setTorneio(t);
            et.setClassificacao("Unassigned");
            EquipaTorneioServices.saveEquipaTorneio(et);
        }
    }
}
