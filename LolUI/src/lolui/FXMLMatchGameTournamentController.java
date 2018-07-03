/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lolui;

import lolui.exceptions.EmptyBuildException;
import lolui.exceptions.InsereMatchStatsException;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lolbll.BuildServices;
import lolbll.EncontroServices;
import lolbll.EstatisticasMembroJogoServices;
import lolbll.HibernateBLL;
import lolbll.JogoServices;
import lolbll.RondaServices;
import lolbll.RunaEscolhidaServices;
import lolbll.RunesServices;
import lolbll.SpellEscolhidoServices;
import lolbll.SpellServices;
import lolbll.TorneioServices;
import loldal.model.Build;
import loldal.model.Champion;
import loldal.model.Encontro;
import loldal.model.Equipa;
import loldal.model.Estatisticasmembrojogo;
import loldal.model.Item;
import loldal.model.Jogo;
import loldal.model.Membroequipa;
import loldal.model.Runa;
import loldal.model.Runaescolhida;
import loldal.model.RunaescolhidaId;
import loldal.model.Spell;
import loldal.model.Spellescolhido;
import loldal.model.SpellescolhidoId;

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
    private GridPane gridPaneBlueTeam;

    @FXML
    private GridPane gridPaneRedTeam;

    @FXML
    private Tab tabBlueTeam;
    @FXML
    private Tab tabRedTeam;

    //Labels das equipas
    @FXML
    private Label lblBlueTeamTop;
    @FXML
    private Label lblBlueTeamJng;
    @FXML
    private Label lblBlueTeamMid;
    @FXML
    private Label lblBlueTeamAdc;
    @FXML
    private Label lblBlueTeamSup;

    @FXML
    private Label lblRedTeamTop;
    @FXML
    private Label lblRedTeamJng;
    @FXML
    private Label lblRedTeamMid;
    @FXML
    private Label lblRedTeamAdc;
    @FXML
    private Label lblRedTeamSup;

    //Labels
    @FXML
    private Label lblTournament;
    @FXML
    private Label lblRound;
    @FXML
    private Label lblMatch;
    @FXML
    private Label lblGame;
    //ComboBoxes
    @FXML
    private ComboBox cbWinner;

    //ListaItems por jogador
    private List<Item> listItemBlueTeamTop;
    private List<Item> listItemBlueTeamJng;
    private List<Item> listItemBlueTeamMid;
    private List<Item> listItemBlueTeamAdc;
    private List<Item> listItemBlueTeamSup;

    private List<Item> listItemRedTeamTop;
    private List<Item> listItemRedTeamJng;
    private List<Item> listItemRedTeamMid;
    private List<Item> listItemRedTeamAdc;
    private List<Item> listItemRedTeamSup;

    //ImageViews dos Items de cada Jogador BLUE TEAM
    @FXML
    private ImageView imgItem1BlueTeamTop;
    @FXML
    private ImageView imgItem2BlueTeamTop;
    @FXML
    private ImageView imgItem3BlueTeamTop;
    @FXML
    private ImageView imgItem4BlueTeamTop;
    @FXML
    private ImageView imgItem5BlueTeamTop;
    @FXML
    private ImageView imgItem6BlueTeamTop;

    @FXML
    private ImageView imgItem1BlueTeamJng;
    @FXML
    private ImageView imgItem2BlueTeamJng;
    @FXML
    private ImageView imgItem3BlueTeamJng;
    @FXML
    private ImageView imgItem4BlueTeamJng;
    @FXML
    private ImageView imgItem5BlueTeamJng;
    @FXML
    private ImageView imgItem6BlueTeamJng;

    @FXML
    private ImageView imgItem1BlueTeamMid;
    @FXML
    private ImageView imgItem2BlueTeamMid;
    @FXML
    private ImageView imgItem3BlueTeamMid;
    @FXML
    private ImageView imgItem4BlueTeamMid;
    @FXML
    private ImageView imgItem5BlueTeamMid;
    @FXML
    private ImageView imgItem6BlueTeamMid;

    @FXML
    private ImageView imgItem1BlueTeamAdc;
    @FXML
    private ImageView imgItem2BlueTeamAdc;
    @FXML
    private ImageView imgItem3BlueTeamAdc;
    @FXML
    private ImageView imgItem4BlueTeamAdc;
    @FXML
    private ImageView imgItem5BlueTeamAdc;
    @FXML
    private ImageView imgItem6BlueTeamAdc;

    @FXML
    private ImageView imgItem1BlueTeamSup;
    @FXML
    private ImageView imgItem2BlueTeamSup;
    @FXML
    private ImageView imgItem3BlueTeamSup;
    @FXML
    private ImageView imgItem4BlueTeamSup;
    @FXML
    private ImageView imgItem5BlueTeamSup;
    @FXML
    private ImageView imgItem6BlueTeamSup;

    //ImageViews dos Items de cada Jogador RED TEAM
    @FXML
    private ImageView imgItem1RedTeamTop;
    @FXML
    private ImageView imgItem2RedTeamTop;
    @FXML
    private ImageView imgItem3RedTeamTop;
    @FXML
    private ImageView imgItem4RedTeamTop;
    @FXML
    private ImageView imgItem5RedTeamTop;
    @FXML
    private ImageView imgItem6RedTeamTop;

    @FXML
    private ImageView imgItem1RedTeamJng;
    @FXML
    private ImageView imgItem2RedTeamJng;
    @FXML
    private ImageView imgItem3RedTeamJng;
    @FXML
    private ImageView imgItem4RedTeamJng;
    @FXML
    private ImageView imgItem5RedTeamJng;
    @FXML
    private ImageView imgItem6RedTeamJng;

    @FXML
    private ImageView imgItem1RedTeamMid;
    @FXML
    private ImageView imgItem2RedTeamMid;
    @FXML
    private ImageView imgItem3RedTeamMid;
    @FXML
    private ImageView imgItem4RedTeamMid;
    @FXML
    private ImageView imgItem5RedTeamMid;
    @FXML
    private ImageView imgItem6RedTeamMid;

    @FXML
    private ImageView imgItem1RedTeamAdc;
    @FXML
    private ImageView imgItem2RedTeamAdc;
    @FXML
    private ImageView imgItem3RedTeamAdc;
    @FXML
    private ImageView imgItem4RedTeamAdc;
    @FXML
    private ImageView imgItem5RedTeamAdc;
    @FXML
    private ImageView imgItem6RedTeamAdc;

    @FXML
    private ImageView imgItem1RedTeamSup;
    @FXML
    private ImageView imgItem2RedTeamSup;
    @FXML
    private ImageView imgItem3RedTeamSup;
    @FXML
    private ImageView imgItem4RedTeamSup;
    @FXML
    private ImageView imgItem5RedTeamSup;
    @FXML
    private ImageView imgItem6RedTeamSup;

    //List Spells dos Jogadores
    private List<Spell> listSpellBlueTeamTop;
    private List<Spell> listSpellBlueTeamJng;
    private List<Spell> listSpellBlueTeamMid;
    private List<Spell> listSpellBlueTeamAdc;
    private List<Spell> listSpellBlueTeamSup;

    private List<Spell> listSpellRedTeamTop;
    private List<Spell> listSpellRedTeamJng;
    private List<Spell> listSpellRedTeamMid;
    private List<Spell> listSpellRedTeamAdc;
    private List<Spell> listSpellRedTeamSup;

    //ImageView dos Spells dos Jogadores Equipa Blue Team
    @FXML
    private ImageView imgSpell1BlueTeamTop;
    @FXML
    private ImageView imgSpell2BlueTeamTop;
    @FXML
    private ImageView imgSpell1BlueTeamJng;
    @FXML
    private ImageView imgSpell2BlueTeamJng;
    @FXML
    private ImageView imgSpell1BlueTeamMid;
    @FXML
    private ImageView imgSpell2BlueTeamMid;
    @FXML
    private ImageView imgSpell1BlueTeamAdc;
    @FXML
    private ImageView imgSpell2BlueTeamAdc;
    @FXML
    private ImageView imgSpell1BlueTeamSup;
    @FXML
    private ImageView imgSpell2BlueTeamSup;

    //ImageView dos Spells dos Jogadores Equipa Red Team
    @FXML
    private ImageView imgSpell1RedTeamTop;
    @FXML
    private ImageView imgSpell2RedTeamTop;
    @FXML
    private ImageView imgSpell1RedTeamJng;
    @FXML
    private ImageView imgSpell2RedTeamJng;
    @FXML
    private ImageView imgSpell1RedTeamMid;
    @FXML
    private ImageView imgSpell2RedTeamMid;
    @FXML
    private ImageView imgSpell1RedTeamAdc;
    @FXML
    private ImageView imgSpell2RedTeamAdc;
    @FXML
    private ImageView imgSpell1RedTeamSup;
    @FXML
    private ImageView imgSpell2RedTeamSup;

    //Champions de cada jogador
    private Champion championBlueTeamTop;
    private Champion championBlueTeamJng;
    private Champion championBlueTeamMid;
    private Champion championBlueTeamAdc;
    private Champion championBlueTeamSup;

    private Champion championRedTeamTop;
    private Champion championRedTeamJng;
    private Champion championRedTeamMid;
    private Champion championRedTeamAdc;
    private Champion championRedTeamSup;

    //ImageView dos Champions
    @FXML
    ImageView imgChampBlueTeamTop;
    @FXML
    ImageView imgChampBlueTeamJng;
    @FXML
    ImageView imgChampBlueTeamMid;
    @FXML
    ImageView imgChampBlueTeamAdc;
    @FXML
    ImageView imgChampBlueTeamSup;

    @FXML
    ImageView imgChampRedTeamTop;
    @FXML
    ImageView imgChampRedTeamJng;
    @FXML
    ImageView imgChampRedTeamMid;
    @FXML
    ImageView imgChampRedTeamAdc;
    @FXML
    ImageView imgChampRedTeamSup;

    private List<Champion> champsSelecionados;

    //Runes de cada Jogador
    private Runa runaBlueTeamTop;
    private Runa runaBlueTeamJng;
    private Runa runaBlueTeamMid;
    private Runa runaBlueTeamAdc;
    private Runa runaBlueTeamSup;

    private Runa runaRedTeamTop;
    private Runa runaRedTeamJng;
    private Runa runaRedTeamMid;
    private Runa runaRedTeamAdc;
    private Runa runaRedTeamSup;

    //ImageView das Runas
    @FXML
    private ImageView imgRunaBlueTeamTop;
    @FXML
    private ImageView imgRunaBlueTeamJng;
    @FXML
    private ImageView imgRunaBlueTeamMid;
    @FXML
    private ImageView imgRunaBlueTeamAdc;
    @FXML
    private ImageView imgRunaBlueTeamSup;

    @FXML
    private ImageView imgRunaRedTeamTop;
    @FXML
    private ImageView imgRunaRedTeamJng;
    @FXML
    private ImageView imgRunaRedTeamMid;
    @FXML
    private ImageView imgRunaRedTeamAdc;
    @FXML
    private ImageView imgRunaRedTeamSup;

    //Spinners da Blue Team
    @FXML
    private TextField spinCSBlueTeamTop;
    @FXML
    private TextField spinCSBlueTeamJng;
    @FXML
    private TextField spinCSBlueTeamMid;
    @FXML
    private TextField spinCSBlueTeamAdc;
    @FXML
    private TextField spinCSBlueTeamSup;

    @FXML
    private TextField spinKillsBlueTeamTop;
    @FXML
    private TextField spinKillsBlueTeamJng;
    @FXML
    private TextField spinKillsBlueTeamMid;
    @FXML
    private TextField spinKillsBlueTeamAdc;
    @FXML
    private TextField spinKillsBlueTeamSup;

    @FXML
    private TextField spinDeathsBlueTeamTop;
    @FXML
    private TextField spinDeathsBlueTeamJng;
    @FXML
    private TextField spinDeathsBlueTeamMid;
    @FXML
    private TextField spinDeathsBlueTeamAdc;
    @FXML
    private TextField spinDeathsBlueTeamSup;

    @FXML
    private TextField spinAssistsBlueTeamTop;
    @FXML
    private TextField spinAssistsBlueTeamJng;
    @FXML
    private TextField spinAssistsBlueTeamMid;
    @FXML
    private TextField spinAssistsBlueTeamAdc;
    @FXML
    private TextField spinAssistsBlueTeamSup;

    //Spinners da Red Team
    @FXML
    private TextField spinCSRedTeamTop;
    @FXML
    private TextField spinCSRedTeamJng;
    @FXML
    private TextField spinCSRedTeamMid;
    @FXML
    private TextField spinCSRedTeamAdc;
    @FXML
    private TextField spinCSRedTeamSup;

    @FXML
    private TextField spinKillsRedTeamTop;
    @FXML
    private TextField spinKillsRedTeamJng;
    @FXML
    private TextField spinKillsRedTeamMid;
    @FXML
    private TextField spinKillsRedTeamAdc;
    @FXML
    private TextField spinKillsRedTeamSup;

    @FXML
    private TextField spinDeathsRedTeamTop;
    @FXML
    private TextField spinDeathsRedTeamJng;
    @FXML
    private TextField spinDeathsRedTeamMid;
    @FXML
    private TextField spinDeathsRedTeamAdc;
    @FXML
    private TextField spinDeathsRedTeamSup;

    @FXML
    private TextField spinAssistsRedTeamTop;
    @FXML
    private TextField spinAssistsRedTeamJng;
    @FXML
    private TextField spinAssistsRedTeamMid;
    @FXML
    private TextField spinAssistsRedTeamAdc;
    @FXML
    private TextField spinAssistsRedTeamSup;
    
    private Encontro encontro;
    
    private URL url;
    
    private ResourceBundle rb;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        parentBorderPane.getStyleClass().add("borderPane");
        this.prepareActionsGrid();
        champsSelecionados = new ArrayList<>();
        this.prepareLists();
        this.prepareTextFieldListeners();
        this.url = url;
        this.rb = rb;
    }

    @FXML
    public void closePopUp() throws IOException {
        
        Stage stage = (Stage) this.imgBack.getScene().getWindow();
        stage.close();
    }
    
    public void prepareLists(){
        listItemBlueTeamTop= new ArrayList<>();
        listItemBlueTeamJng= new ArrayList<>();
        listItemBlueTeamMid= new ArrayList<>();
        listItemBlueTeamAdc= new ArrayList<>();
        listItemBlueTeamSup= new ArrayList<>();
        listItemRedTeamTop= new ArrayList<>();
        listItemRedTeamJng= new ArrayList<>();
        listItemRedTeamMid= new ArrayList<>();
        listItemRedTeamAdc= new ArrayList<>();
        listItemRedTeamSup= new ArrayList<>();
        listSpellBlueTeamTop= new ArrayList<>();
        listSpellBlueTeamJng= new ArrayList<>();
        listSpellBlueTeamMid= new ArrayList<>();
        listSpellBlueTeamAdc= new ArrayList<>();
        listSpellBlueTeamSup= new ArrayList<>();
        listSpellRedTeamTop= new ArrayList<>();
        listSpellRedTeamJng= new ArrayList<>();
        listSpellRedTeamMid= new ArrayList<>();
        listSpellRedTeamAdc= new ArrayList<>();
        listSpellRedTeamSup= new ArrayList<>();
    }
    
    public void prepareTextFieldListeners(){
        for(Node node: gridPaneBlueTeam.getChildren()){
            if(node instanceof TextField){
                this.addOnlyIntegerListener((TextField)node);
                ((TextField) node).setText("0");
            }
        }
        for(Node node: gridPaneRedTeam.getChildren()){
            if(node instanceof TextField){
                this.addOnlyIntegerListener((TextField)node);
                ((TextField) node).setText("0");
            }
        }
    }
    
    public void addOnlyIntegerListener(TextField textField){
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*")) {
                    textField.setText(newValue.replaceAll("[^\\d]", ""));
                }
                if (textField.getText().length() > 4) {
                    textField.setText(textField.getText().substring(0, 4));
                }
            }
        });
    }

    @FXML
    public void createItemAction(MouseEvent event, int tab, int player) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLItemSelection.fxml"));
        Parent root = loader.load();
        FXMLItemSelectionController controller = loader.getController();
        controller.preencheGridItems();
        if (tab == 0) {
            if (player == 1) {
                if (listItemBlueTeamTop != null) {
                    controller.preencheItemSelecionadosInicio(listItemBlueTeamTop);
                }
            }
            if (player == 2) {
                if (listItemBlueTeamJng != null) {
                    controller.preencheItemSelecionadosInicio(listItemBlueTeamJng);
                }
            }
            if (player == 3) {
                if (listItemBlueTeamMid != null) {
                    controller.preencheItemSelecionadosInicio(listItemBlueTeamMid);
                }
            }
            if (player == 4) {
                if (listItemBlueTeamAdc != null) {
                    controller.preencheItemSelecionadosInicio(listItemBlueTeamAdc);
                }
            }
            if (player == 5) {
                if (listItemBlueTeamSup != null) {
                    controller.preencheItemSelecionadosInicio(listItemBlueTeamSup);
                }
            }
        }
        if (tab == 1) {
            if (player == 1) {
                if (listItemRedTeamTop != null) {
                    controller.preencheItemSelecionadosInicio(listItemRedTeamTop);
                }
            }
            if (player == 2) {
                if (listItemRedTeamJng != null) {
                    controller.preencheItemSelecionadosInicio(listItemRedTeamJng);
                }
            }
            if (player == 3) {
                if (listItemRedTeamMid != null) {
                    controller.preencheItemSelecionadosInicio(listItemRedTeamMid);
                }
            }
            if (player == 4) {
                if (listItemRedTeamAdc != null) {
                    controller.preencheItemSelecionadosInicio(listItemRedTeamAdc);
                }
            }
            if (player == 5) {
                if (listItemRedTeamSup != null) {
                    controller.preencheItemSelecionadosInicio(listItemRedTeamSup);
                }
            }
        }
        //Metodo para preencher a Janela de PopUp
        this.prepareItemStage(root, controller, tab, player);
    }

    public void prepareItemStage(Parent root, FXMLItemSelectionController controller, int tab, int player) {
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
        if (tab == 0) {
            this.limpaListaBuild(tab, player);
            if (player == 1) {
                if (controller.getItemList() != null) {
                    listItemBlueTeamTop = controller.getItemList();
                    System.out.println(listItemBlueTeamTop);
                    this.preencheBuildPlayer(listItemBlueTeamTop, imgItem1BlueTeamTop, imgItem2BlueTeamTop, imgItem3BlueTeamTop, imgItem4BlueTeamTop, imgItem5BlueTeamTop, imgItem6BlueTeamTop);
                }
            }
            if (player == 2) {
                if (controller.getItemList() != null) {
                    listItemBlueTeamJng = controller.getItemList();
                    this.preencheBuildPlayer(listItemBlueTeamJng, imgItem1BlueTeamJng, imgItem2BlueTeamJng, imgItem3BlueTeamJng, imgItem4BlueTeamJng, imgItem5BlueTeamJng, imgItem6BlueTeamJng);
                }
            }
            if (player == 3) {
                if (controller.getItemList() != null) {
                    listItemBlueTeamMid = controller.getItemList();
                    this.preencheBuildPlayer(listItemBlueTeamMid, imgItem1BlueTeamMid, imgItem2BlueTeamMid, imgItem3BlueTeamMid, imgItem4BlueTeamMid, imgItem5BlueTeamMid, imgItem6BlueTeamMid);
                }
            }
            if (player == 4) {
                if (controller.getItemList() != null) {
                    listItemBlueTeamAdc = controller.getItemList();
                    this.preencheBuildPlayer(listItemBlueTeamAdc, imgItem1BlueTeamAdc, imgItem2BlueTeamAdc, imgItem3BlueTeamAdc, imgItem4BlueTeamAdc, imgItem5BlueTeamAdc, imgItem6BlueTeamAdc);
                }
            }
            if (player == 5) {
                if (controller.getItemList() != null) {
                    listItemBlueTeamSup = controller.getItemList();
                    this.preencheBuildPlayer(listItemBlueTeamSup, imgItem1BlueTeamSup, imgItem2BlueTeamSup, imgItem3BlueTeamSup, imgItem4BlueTeamSup, imgItem5BlueTeamSup, imgItem6BlueTeamSup);
                }
            }
        }
        if (tab == 1) {
            this.limpaListaBuild(tab, player);
            if (player == 1) {
                if (controller.getItemList() != null) {
                    listItemRedTeamTop = controller.getItemList();
                    this.preencheBuildPlayer(listItemRedTeamTop, imgItem1RedTeamTop, imgItem2RedTeamTop, imgItem3RedTeamTop, imgItem4RedTeamTop, imgItem5RedTeamTop, imgItem6RedTeamTop);
                }
            }
            if (player == 2) {
                if (controller.getItemList() != null) {
                    listItemRedTeamJng = controller.getItemList();
                    this.preencheBuildPlayer(listItemRedTeamJng, imgItem1RedTeamJng, imgItem2RedTeamJng, imgItem3RedTeamJng, imgItem4RedTeamJng, imgItem5RedTeamJng, imgItem6RedTeamJng);
                }
            }
            if (player == 3) {
                if (controller.getItemList() != null) {
                    listItemRedTeamMid = controller.getItemList();
                    this.preencheBuildPlayer(listItemRedTeamMid, imgItem1RedTeamMid, imgItem2RedTeamMid, imgItem3RedTeamMid, imgItem4RedTeamMid, imgItem5RedTeamMid, imgItem6RedTeamMid);
                }
            }
            if (player == 4) {
                if (controller.getItemList() != null) {
                    listItemRedTeamAdc = controller.getItemList();
                    this.preencheBuildPlayer(listItemRedTeamAdc, imgItem1RedTeamAdc, imgItem2RedTeamAdc, imgItem3RedTeamAdc, imgItem4RedTeamAdc, imgItem5RedTeamAdc, imgItem6RedTeamAdc);
                }
            }
            if (player == 5) {
                if (controller.getItemList() != null) {
                    listItemRedTeamSup = controller.getItemList();
                    this.preencheBuildPlayer(listItemRedTeamSup, imgItem1RedTeamSup, imgItem2RedTeamSup, imgItem3RedTeamSup, imgItem4RedTeamSup, imgItem5RedTeamSup, imgItem6RedTeamSup);
                }
            }
        }
    }

    public void preencheBuildPlayer(List<Item> list, ImageView img1, ImageView img2, ImageView img3, ImageView img4, ImageView img5, ImageView img6) {

        if (list.size() > 0) {
            img1.setImage(new Image(getClass().getResourceAsStream("pics/items/" + list.get(0).getNome().toLowerCase() + ".png")));
        }
        if (list.size() > 1) {
            img2.setImage(new Image(getClass().getResourceAsStream("pics/items/" + list.get(1).getNome().toLowerCase() + ".png")));
        }
        if (list.size() > 2) {
            img3.setImage(new Image(getClass().getResourceAsStream("pics/items/" + list.get(2).getNome().toLowerCase() + ".png")));
        }
        if (list.size() > 3) {
            img4.setImage(new Image(getClass().getResourceAsStream("pics/items/" + list.get(3).getNome().toLowerCase() + ".png")));
        }
        if (list.size() > 4) {
            img5.setImage(new Image(getClass().getResourceAsStream("pics/items/" + list.get(4).getNome().toLowerCase() + ".png")));
        }
        if (list.size() > 5) {
            img6.setImage(new Image(getClass().getResourceAsStream("pics/items/" + list.get(5).getNome().toLowerCase() + ".png")));
        }

    }

    public void preencheSpellsPlayer(List<Spell> list, ImageView img1, ImageView img2) {
        if (list.size() > 0) {
            img1.setImage(new Image(getClass().getResourceAsStream("pics/spells/" + list.get(0).getNome().toLowerCase() + ".png")));
        }
        if (list.size() > 1) {
            img2.setImage(new Image(getClass().getResourceAsStream("pics/spells/" + list.get(1).getNome().toLowerCase() + ".png")));
        }
    }

    public void preencheChampionsPlayer(Champion champ, ImageView img) {
        img.setImage(new Image(getClass().getResourceAsStream("pics/champs/" + champ.getNome().toLowerCase() + ".png")));
    }

    public void preencheRunaPlayer(Runa runa, ImageView img) {
        img.setImage(new Image(getClass().getResourceAsStream("pics/runes/" + runa.getNome().toLowerCase() + ".png")));
    }

    @FXML
    public void createSpellAction(MouseEvent event, int tab, int player) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLSpellSelection.fxml"));
        Parent root = loader.load();
        FXMLSpellSelectionController controller = loader.getController();
        controller.preencheGridSpells();
        if (tab == 0) {
            if (player == 1) {
                if (listSpellBlueTeamTop != null) {
                    controller.preencheSpellSelecionadosInicio(listSpellBlueTeamTop);
                }
            }
            if (player == 2) {
                if (listSpellBlueTeamJng != null) {
                    controller.preencheSpellSelecionadosInicio(listSpellBlueTeamJng);
                }
            }
            if (player == 3) {
                if (listSpellBlueTeamMid != null) {
                    controller.preencheSpellSelecionadosInicio(listSpellBlueTeamMid);
                }
            }
            if (player == 4) {
                if (listSpellBlueTeamAdc != null) {
                    controller.preencheSpellSelecionadosInicio(listSpellBlueTeamAdc);
                }
            }
            if (player == 5) {
                if (listSpellBlueTeamSup != null) {
                    controller.preencheSpellSelecionadosInicio(listSpellBlueTeamSup);
                }
            }

        }
        if (tab == 1) {
            if (player == 1) {
                if (listSpellRedTeamTop != null) {
                    controller.preencheSpellSelecionadosInicio(listSpellRedTeamTop);
                }
            }
            if (player == 2) {
                if (listSpellRedTeamJng != null) {
                    controller.preencheSpellSelecionadosInicio(listSpellRedTeamJng);
                }
            }
            if (player == 3) {
                if (listSpellRedTeamMid != null) {
                    controller.preencheSpellSelecionadosInicio(listSpellRedTeamMid);
                }
            }
            if (player == 4) {
                if (listSpellRedTeamAdc != null) {
                    controller.preencheSpellSelecionadosInicio(listSpellRedTeamAdc);
                }
            }
            if (player == 5) {
                if (listSpellRedTeamSup != null) {
                    controller.preencheSpellSelecionadosInicio(listSpellRedTeamSup);
                }
            }

        }

        //Metodo para preencher a Janela de PopUp
        this.prepareSpellStage(root, controller, tab, player);
    }

    public void prepareSpellStage(Parent root, FXMLSpellSelectionController controller, int tab, int player) {
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
        if (tab == 0) {
            this.limpaListaSpells(tab, player);
            if (player == 1) {
                if (controller.getSpellsList() != null) {
                    listSpellBlueTeamTop = controller.getSpellsList();
                    this.preencheSpellsPlayer(listSpellBlueTeamTop, imgSpell1BlueTeamTop, imgSpell2BlueTeamTop);
                    System.out.println(listSpellBlueTeamTop);
                }
            }
            if (player == 2) {
                if (controller.getSpellsList() != null) {
                    listSpellBlueTeamJng = controller.getSpellsList();
                    this.preencheSpellsPlayer(listSpellBlueTeamJng, imgSpell1BlueTeamJng, imgSpell2BlueTeamJng);
                }
            }
            if (player == 3) {
                if (controller.getSpellsList() != null) {
                    listSpellBlueTeamMid = controller.getSpellsList();
                    this.preencheSpellsPlayer(listSpellBlueTeamMid, imgSpell1BlueTeamMid, imgSpell2BlueTeamMid);
                }
            }
            if (player == 4) {
                if (controller.getSpellsList() != null) {
                    listSpellBlueTeamAdc = controller.getSpellsList();
                    this.preencheSpellsPlayer(listSpellBlueTeamAdc, imgSpell1BlueTeamAdc, imgSpell2BlueTeamAdc);
                }
            }
            if (player == 5) {
                if (controller.getSpellsList() != null) {
                    listSpellBlueTeamSup = controller.getSpellsList();
                    this.preencheSpellsPlayer(listSpellBlueTeamSup, imgSpell1BlueTeamSup, imgSpell2BlueTeamSup);
                }
            }
        }

        if (tab == 1) {
            this.limpaListaSpells(tab, player);
            if (player == 1) {
                if (controller.getSpellsList() != null) {
                    listSpellRedTeamTop = controller.getSpellsList();
                    this.preencheSpellsPlayer(listSpellRedTeamTop, imgSpell1RedTeamTop, imgSpell2RedTeamTop);
                }
            }
            if (player == 2) {
                if (controller.getSpellsList() != null) {
                    listSpellRedTeamJng = controller.getSpellsList();
                    this.preencheSpellsPlayer(listSpellRedTeamJng, imgSpell1RedTeamJng, imgSpell2RedTeamJng);
                }
            }
            if (player == 3) {
                if (controller.getSpellsList() != null) {
                    listSpellRedTeamMid = controller.getSpellsList();
                    this.preencheSpellsPlayer(listSpellRedTeamMid, imgSpell1RedTeamMid, imgSpell2RedTeamMid);
                }
            }
            if (player == 4) {
                if (controller.getSpellsList() != null) {
                    listSpellRedTeamAdc = controller.getSpellsList();
                    this.preencheSpellsPlayer(listSpellRedTeamAdc, imgSpell1RedTeamAdc, imgSpell2RedTeamAdc);
                }
            }
            if (player == 5) {
                if (controller.getSpellsList() != null) {
                    listSpellRedTeamSup = controller.getSpellsList();
                    this.preencheSpellsPlayer(listSpellRedTeamSup, imgSpell1RedTeamSup, imgSpell2RedTeamSup);
                }
            }
        }
    }

    @FXML
    public void createChampionAction(MouseEvent event, int tab, int player) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLChampionSelection.fxml"));
        Parent root = loader.load();
        FXMLChampionSelectionController controller = loader.getController();
        controller.preencheGridChampions();
        //Metodo para preencher a Janela de PopUp
        this.prepareChampionStage(root, controller, tab, player);
    }

    public void prepareChampionStage(Parent root, FXMLChampionSelectionController controller, int tab, int player) {
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
        
        if (tab == 0) {
            if (player == 1) {
                System.out.println(championBlueTeamTop);
                if (controller.getChampionSelected()!= null && !champsSelecionados.contains(controller.getChampionSelected())) {
                    if (championBlueTeamTop == null) {
                        championBlueTeamTop = controller.getChampionSelected();
                        champsSelecionados.add(controller.getChampionSelected());
                        this.preencheChampionsPlayer(controller.getChampionSelected(), imgChampBlueTeamTop);
                    } else {
                            champsSelecionados.remove(championBlueTeamTop);
                            championBlueTeamTop = controller.getChampionSelected();
                            champsSelecionados.add(controller.getChampionSelected());
                            this.preencheChampionsPlayer(controller.getChampionSelected(), imgChampBlueTeamTop);
                    }
                } else {
                    if(champsSelecionados.contains(controller.getChampionSelected())){
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Warning");
                        alert.setHeaderText("Something went wrong.");
                        alert.setContentText("The champion " + controller.getChampionSelected() + " is already selected by another player.");
                        alert.showAndWait();
                    }
                }
            }
            if (player == 2) {
                if (controller.getChampionSelected()!= null && !champsSelecionados.contains(controller.getChampionSelected())) {
                    if (championBlueTeamJng == null) {
                        championBlueTeamJng = controller.getChampionSelected();
                        champsSelecionados.add(controller.getChampionSelected());
                        this.preencheChampionsPlayer(controller.getChampionSelected(), imgChampBlueTeamJng);
                    } else {
                        champsSelecionados.remove(championBlueTeamJng);
                        championBlueTeamJng = controller.getChampionSelected();
                        champsSelecionados.add(controller.getChampionSelected());
                        this.preencheChampionsPlayer(controller.getChampionSelected(), imgChampBlueTeamJng);
                    }
                } else {
                    if(champsSelecionados.contains(controller.getChampionSelected())){
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Warning");
                        alert.setHeaderText("Something went wrong.");
                        alert.setContentText("The champion " + controller.getChampionSelected() + " is already selected by another player.");
                        alert.showAndWait();
                    }
                }
            }
            if (player == 3) {
                if (controller.getChampionSelected()!= null && !champsSelecionados.contains(controller.getChampionSelected())) {
                    if (championBlueTeamMid == null) {
                        championBlueTeamMid = controller.getChampionSelected();
                        champsSelecionados.add(controller.getChampionSelected());
                        this.preencheChampionsPlayer(controller.getChampionSelected(), imgChampBlueTeamMid);
                    } else {
                        champsSelecionados.remove(championBlueTeamMid);
                        championBlueTeamMid = controller.getChampionSelected();
                        champsSelecionados.add(controller.getChampionSelected());
                        this.preencheChampionsPlayer(controller.getChampionSelected(), imgChampBlueTeamMid);
                    }
                } else {
                    if(champsSelecionados.contains(controller.getChampionSelected())){
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Warning");
                        alert.setHeaderText("Something went wrong.");
                        alert.setContentText("The champion " + controller.getChampionSelected() + " is already selected by another player.");
                        alert.showAndWait();
                    }
                }
            }
            if (player == 4) {
                if (controller.getChampionSelected()!= null && !champsSelecionados.contains(controller.getChampionSelected())) {
                    if (championBlueTeamAdc == null) {
                        championBlueTeamAdc = controller.getChampionSelected();
                        champsSelecionados.add(controller.getChampionSelected());
                        this.preencheChampionsPlayer(controller.getChampionSelected(), imgChampBlueTeamAdc);
                    } else {
                        champsSelecionados.remove(championBlueTeamAdc);
                        championBlueTeamAdc = controller.getChampionSelected();
                        champsSelecionados.add(controller.getChampionSelected());
                        this.preencheChampionsPlayer(controller.getChampionSelected(), imgChampBlueTeamAdc);
                    }
                } else {
                    if(champsSelecionados.contains(controller.getChampionSelected())){
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Warning");
                        alert.setHeaderText("Something went wrong.");
                        alert.setContentText("The champion " + controller.getChampionSelected() + " is already selected by another player.");
                        alert.showAndWait();
                    }
                }
            }
            if (player == 5) {
                if (controller.getChampionSelected()!= null && !champsSelecionados.contains(controller.getChampionSelected())) {
                    if (championBlueTeamSup == null) {
                        championBlueTeamSup = controller.getChampionSelected();
                        champsSelecionados.add(controller.getChampionSelected());
                        this.preencheChampionsPlayer(controller.getChampionSelected(), imgChampBlueTeamSup);
                    } else {
                        champsSelecionados.remove(championBlueTeamSup);
                        championBlueTeamSup = controller.getChampionSelected();
                        champsSelecionados.add(controller.getChampionSelected());
                        this.preencheChampionsPlayer(controller.getChampionSelected(), imgChampBlueTeamSup);
                    }
                } else {
                    if(champsSelecionados.contains(controller.getChampionSelected())){
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Warning");
                        alert.setHeaderText("Something went wrong.");
                        alert.setContentText("The champion " + controller.getChampionSelected() + " is already selected by another player.");
                        alert.showAndWait();
                    }
                }
            }
        }
        if (tab == 1) {
            if (player == 1) {
                if (controller.getChampionSelected()!= null && !champsSelecionados.contains(controller.getChampionSelected())) {
                    if (championRedTeamTop == null) {
                        championRedTeamTop = controller.getChampionSelected();
                        champsSelecionados.add(controller.getChampionSelected());
                        this.preencheChampionsPlayer(controller.getChampionSelected(), imgChampRedTeamTop);
                    } else {
                        champsSelecionados.remove(championRedTeamTop);
                        championRedTeamTop = controller.getChampionSelected();
                        champsSelecionados.add(controller.getChampionSelected());
                        this.preencheChampionsPlayer(controller.getChampionSelected(), imgChampRedTeamTop);
                    }
                } else {
                    if(champsSelecionados.contains(controller.getChampionSelected())){
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Warning");
                        alert.setHeaderText("Something went wrong.");
                        alert.setContentText("The champion " + controller.getChampionSelected() + " is already selected by another player.");
                        alert.showAndWait();
                    }
                }
            }
            if (player == 2) {
                if (controller.getChampionSelected()!= null && !champsSelecionados.contains(controller.getChampionSelected())) {
                    if (championRedTeamJng == null) {
                        championRedTeamJng = controller.getChampionSelected();
                        champsSelecionados.add(controller.getChampionSelected());
                        this.preencheChampionsPlayer(controller.getChampionSelected(), imgChampRedTeamJng);
                    } else {
                        champsSelecionados.remove(championRedTeamJng);
                        championRedTeamJng = controller.getChampionSelected();
                        champsSelecionados.add(controller.getChampionSelected());
                        this.preencheChampionsPlayer(controller.getChampionSelected(), imgChampRedTeamJng);
                    }
                } else {
                    if(champsSelecionados.contains(controller.getChampionSelected())){
                       Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Warning");
                        alert.setHeaderText("Something went wrong.");
                        alert.setContentText("The champion " + controller.getChampionSelected() + " is already selected by another player.");
                        alert.showAndWait(); 
                    }
                }
            }
            if (player == 3) {
                if (controller.getChampionSelected()!= null && !champsSelecionados.contains(controller.getChampionSelected())) {
                    if (championRedTeamMid == null) {
                        championRedTeamMid = controller.getChampionSelected();
                        champsSelecionados.add(controller.getChampionSelected());
                        this.preencheChampionsPlayer(controller.getChampionSelected(), imgChampRedTeamMid);
                    } else {
                        champsSelecionados.remove(championRedTeamMid);
                        championRedTeamMid = controller.getChampionSelected();
                        champsSelecionados.add(controller.getChampionSelected());
                        this.preencheChampionsPlayer(controller.getChampionSelected(), imgChampRedTeamMid);
                    }
                } else {
                    if(champsSelecionados.contains(controller.getChampionSelected())){
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Warning");
                        alert.setHeaderText("Something went wrong.");
                        alert.setContentText("The champion " + controller.getChampionSelected() + " is already selected by another player.");
                        alert.showAndWait();
                    }
                }
            }
            if (player == 4) {
                if (controller.getChampionSelected()!= null && !champsSelecionados.contains(controller.getChampionSelected())) {
                    if (championRedTeamAdc == null) {
                        championRedTeamAdc = controller.getChampionSelected();
                        champsSelecionados.add(controller.getChampionSelected());
                        this.preencheChampionsPlayer(controller.getChampionSelected(), imgChampRedTeamAdc);
                    } else {
                        champsSelecionados.remove(championRedTeamAdc);
                        championRedTeamAdc = controller.getChampionSelected();
                        champsSelecionados.add(controller.getChampionSelected());
                        this.preencheChampionsPlayer(controller.getChampionSelected(), imgChampRedTeamAdc);
                    }
                } else {
                    if(champsSelecionados.contains(controller.getChampionSelected())){
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Warning");
                        alert.setHeaderText("Something went wrong.");
                        alert.setContentText("The champion " + controller.getChampionSelected() + " is already selected by another player.");
                        alert.showAndWait();
                    }
                }
            }
            if (player == 5) {
                if (controller.getChampionSelected()!= null && !champsSelecionados.contains(controller.getChampionSelected())) {
                    if (championRedTeamSup == null) {
                        championRedTeamSup = controller.getChampionSelected();
                        champsSelecionados.add(controller.getChampionSelected());
                        this.preencheChampionsPlayer(controller.getChampionSelected(), imgChampRedTeamSup);
                    } else {
                        champsSelecionados.remove(championRedTeamSup);
                        championRedTeamSup = controller.getChampionSelected();
                        champsSelecionados.add(controller.getChampionSelected());
                        this.preencheChampionsPlayer(controller.getChampionSelected(), imgChampRedTeamSup);
                    }
                } else {
                    if(champsSelecionados.contains(controller.getChampionSelected())){
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Warning");
                        alert.setHeaderText("Something went wrong.");
                        alert.setContentText("The champion " + controller.getChampionSelected() + " is already selected by another player.");
                        alert.showAndWait();
                    }
                }
            }
        }
        System.out.println(champsSelecionados);
    }

    @FXML
    public void createRunaAction(MouseEvent event, int tab, int player) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLRunaSelection.fxml"));
        Parent root = loader.load();
        FXMLRunaSelectionController controller = loader.getController();
        controller.preencheGridRunas();
        //Metodo para preencher a Janela de PopUp
        this.prepareRunaStage(root, controller, tab, player);
    }

    public void prepareRunaStage(Parent root, FXMLRunaSelectionController controller, int tab, int player) {
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
        if (tab == 0) {
            if (player == 1) {
                if(controller.getRunaSelected()!=null){
                    runaBlueTeamTop = controller.getRunaSelected();
                    this.preencheRunaPlayer(controller.getRunaSelected(), imgRunaBlueTeamTop);
                }
            }
            if (player == 2) {
                if(controller.getRunaSelected()!=null){
                    runaBlueTeamJng = controller.getRunaSelected();
                    this.preencheRunaPlayer(controller.getRunaSelected(), imgRunaBlueTeamJng);
                }
            }
            if (player == 3) {
                if(controller.getRunaSelected()!=null){
                    runaBlueTeamMid = controller.getRunaSelected();
                    this.preencheRunaPlayer(controller.getRunaSelected(), imgRunaBlueTeamMid);
                }
            }
            if (player == 4) {
                if(controller.getRunaSelected()!=null){
                    runaBlueTeamAdc = controller.getRunaSelected();
                    this.preencheRunaPlayer(controller.getRunaSelected(), imgRunaBlueTeamAdc);
                }
            }
            if (player == 5) {
                if(controller.getRunaSelected()!=null){
                    runaBlueTeamSup = controller.getRunaSelected();
                    this.preencheRunaPlayer(controller.getRunaSelected(), imgRunaBlueTeamSup);
                }
            }
        }
        if (tab == 1) {
            if (player == 1) {
                if(controller.getRunaSelected()!=null){
                    runaRedTeamTop = controller.getRunaSelected();
                    this.preencheRunaPlayer(controller.getRunaSelected(), imgRunaRedTeamTop);
                }
            }
            if (player == 2) {
                if(controller.getRunaSelected()!=null){
                    runaRedTeamJng = controller.getRunaSelected();
                    this.preencheRunaPlayer(controller.getRunaSelected(), imgRunaRedTeamJng);
                }
            }
            if (player == 3) {
                if(controller.getRunaSelected()!=null){
                    runaRedTeamMid = controller.getRunaSelected();
                    this.preencheRunaPlayer(controller.getRunaSelected(), imgRunaRedTeamMid);
                }
            }
            if (player == 4) {
                if(controller.getRunaSelected()!=null){
                    runaRedTeamAdc = controller.getRunaSelected();
                    this.preencheRunaPlayer(controller.getRunaSelected(), imgRunaRedTeamAdc);
                }
            }
            if (player == 5) {
                if(controller.getRunaSelected()!=null){
                    runaRedTeamSup = controller.getRunaSelected();
                    this.preencheRunaPlayer(controller.getRunaSelected(), imgRunaRedTeamSup);
                }
            }
        }

    }

    public void prepareActionsGrid() {
        //Tab Blue team selected int tab = 0, Tab Red team selected int tab = 1
        for (Node node : gridPaneBlueTeam.getChildren()) {
            System.out.println(GridPane.getColumnIndex(node));
            if (GridPane.getColumnIndex(node) != null && GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) == 4 && GridPane.getRowIndex(node) > 0) {
                node.setCursor(Cursor.HAND);
                node.setOnMouseClicked((event) -> {
                    try {
                        if (tabBlueTeam.isSelected()) {
                            if (GridPane.getRowIndex(node) == 1) {
                                this.createItemAction(event, 0, 1);
                            }
                            if (GridPane.getRowIndex(node) == 2) {
                                this.createItemAction(event, 0, 2);
                            }
                            if (GridPane.getRowIndex(node) == 3) {
                                this.createItemAction(event, 0, 3);
                            }
                            if (GridPane.getRowIndex(node) == 4) {
                                this.createItemAction(event, 0, 4);
                            }
                            if (GridPane.getRowIndex(node) == 5) {
                                this.createItemAction(event, 0, 5);
                            }
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLMatchGameTournamentController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            }
            if (GridPane.getColumnIndex(node) != null && GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) == 6 && GridPane.getRowIndex(node) > 0) {
                node.setCursor(Cursor.HAND);
                node.setOnMouseClicked((event) -> {
                    try {
                        if (tabBlueTeam.isSelected()) {
                            if (GridPane.getRowIndex(node) == 1) {
                                this.createSpellAction(event, 0, 1);
                            }
                            if (GridPane.getRowIndex(node) == 2) {
                                this.createSpellAction(event, 0, 2);
                            }
                            if (GridPane.getRowIndex(node) == 3) {
                                this.createSpellAction(event, 0, 3);
                            }
                            if (GridPane.getRowIndex(node) == 4) {
                                this.createSpellAction(event, 0, 4);
                            }
                            if (GridPane.getRowIndex(node) == 5) {
                                this.createSpellAction(event, 0, 5);
                            }
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLMatchGameTournamentController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            }
            if (GridPane.getColumnIndex(node) != null && GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) == 7 && GridPane.getRowIndex(node) > 0) {
                node.setCursor(Cursor.HAND);
                node.setOnMouseClicked((event) -> {
                    try {
                        if (tabBlueTeam.isSelected()) {
                            if (GridPane.getRowIndex(node) == 1) {
                                this.createChampionAction(event, 0, 1);
                            }
                            if (GridPane.getRowIndex(node) == 2) {
                                this.createChampionAction(event, 0, 2);
                            }
                            if (GridPane.getRowIndex(node) == 3) {
                                this.createChampionAction(event, 0, 3);
                            }
                            if (GridPane.getRowIndex(node) == 4) {
                                this.createChampionAction(event, 0, 4);
                            }
                            if (GridPane.getRowIndex(node) == 5) {
                                this.createChampionAction(event, 0, 5);
                            }
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLMatchGameTournamentController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            }
            if (GridPane.getColumnIndex(node) != null && GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) == 8 && GridPane.getRowIndex(node) > 0) {
                node.setCursor(Cursor.HAND);
                node.setOnMouseClicked((event) -> {
                    try {
                        if (tabBlueTeam.isSelected()) {
                            if (GridPane.getRowIndex(node) == 1) {
                                this.createRunaAction(event, 0, 1);
                            }
                            if (GridPane.getRowIndex(node) == 2) {
                                this.createRunaAction(event, 0, 2);
                            }
                            if (GridPane.getRowIndex(node) == 3) {
                                this.createRunaAction(event, 0, 3);
                            }
                            if (GridPane.getRowIndex(node) == 4) {
                                this.createRunaAction(event, 0, 4);
                            }
                            if (GridPane.getRowIndex(node) == 5) {
                                this.createRunaAction(event, 0, 5);
                            }
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLMatchGameTournamentController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            }
        }

        for (Node node : gridPaneRedTeam.getChildren()) {
            System.out.println(GridPane.getColumnIndex(node));
            if (GridPane.getColumnIndex(node) != null && GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) == 4 && GridPane.getRowIndex(node) > 0) {
                node.setCursor(Cursor.HAND);
                node.setOnMouseClicked((event) -> {
                    try {
                        if (tabRedTeam.isSelected()) {
                            if (GridPane.getRowIndex(node) == 1) {
                                this.createItemAction(event, 1, 1);
                            }
                            if (GridPane.getRowIndex(node) == 2) {
                                this.createItemAction(event, 1, 2);
                            }
                            if (GridPane.getRowIndex(node) == 3) {
                                this.createItemAction(event, 1, 3);
                            }
                            if (GridPane.getRowIndex(node) == 4) {
                                this.createItemAction(event, 1, 4);
                            }
                            if (GridPane.getRowIndex(node) == 5) {
                                this.createItemAction(event, 1, 5);
                            }
                        }

                    } catch (IOException ex) {
                        Logger.getLogger(FXMLMatchGameTournamentController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            }
            if (GridPane.getColumnIndex(node) != null && GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) == 6 && GridPane.getRowIndex(node) > 0) {
                node.setCursor(Cursor.HAND);
                node.setOnMouseClicked((event) -> {
                    try {
                        if (tabRedTeam.isSelected()) {
                            if (GridPane.getRowIndex(node) == 1) {
                                this.createSpellAction(event, 1, 1);
                            }
                            if (GridPane.getRowIndex(node) == 2) {
                                this.createSpellAction(event, 1, 2);
                            }
                            if (GridPane.getRowIndex(node) == 3) {
                                this.createSpellAction(event, 1, 3);
                            }
                            if (GridPane.getRowIndex(node) == 4) {
                                this.createSpellAction(event, 1, 4);
                            }
                            if (GridPane.getRowIndex(node) == 5) {
                                this.createSpellAction(event, 1, 5);
                            }
                        }

                    } catch (IOException ex) {
                        Logger.getLogger(FXMLMatchGameTournamentController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            }
            if (GridPane.getColumnIndex(node) != null && GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) == 7 && GridPane.getRowIndex(node) > 0) {
                node.setCursor(Cursor.HAND);
                node.setOnMouseClicked((event) -> {
                    try {
                        if (tabRedTeam.isSelected()) {
                            if (GridPane.getRowIndex(node) == 1) {
                                this.createChampionAction(event, 1, 1);
                            }
                            if (GridPane.getRowIndex(node) == 2) {
                                this.createChampionAction(event, 1, 2);
                            }
                            if (GridPane.getRowIndex(node) == 3) {
                                this.createChampionAction(event, 1, 3);
                            }
                            if (GridPane.getRowIndex(node) == 4) {
                                this.createChampionAction(event, 1, 4);
                            }
                            if (GridPane.getRowIndex(node) == 5) {
                                this.createChampionAction(event, 1, 5);
                            }
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLMatchGameTournamentController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            }
            if (GridPane.getColumnIndex(node) != null && GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) == 8 && GridPane.getRowIndex(node) > 0) {
                node.setCursor(Cursor.HAND);
                node.setOnMouseClicked((event) -> {
                    try {
                        if (tabRedTeam.isSelected()) {
                            if (GridPane.getRowIndex(node) == 1) {
                                this.createRunaAction(event, 1, 1);
                            }
                            if (GridPane.getRowIndex(node) == 2) {
                                this.createRunaAction(event, 1, 2);
                            }
                            if (GridPane.getRowIndex(node) == 3) {
                                this.createRunaAction(event, 1, 3);
                            }
                            if (GridPane.getRowIndex(node) == 4) {
                                this.createRunaAction(event, 1, 4);
                            }
                            if (GridPane.getRowIndex(node) == 5) {
                                this.createRunaAction(event, 1, 5);
                            }
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLMatchGameTournamentController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            }
        }
    }

    public void limpaListaSpells(int tab, int player) {
        if (tab == 0) {
            if (player == 1) {
                imgSpell1BlueTeamTop.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgSpell2BlueTeamTop.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
            }
            if (player == 2) {
                imgSpell1BlueTeamJng.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgSpell2BlueTeamJng.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
            }
            if (player == 3) {
                imgSpell1BlueTeamMid.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgSpell2BlueTeamMid.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
            }
            if (player == 4) {
                imgSpell1BlueTeamAdc.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgSpell2BlueTeamAdc.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
            }
            if (player == 5) {
                imgSpell1BlueTeamSup.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgSpell2BlueTeamSup.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
            }
        }
        if (tab == 1) {
            if (player == 1) {
                imgSpell1RedTeamTop.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgSpell2RedTeamTop.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
            }
            if (player == 2) {
                imgSpell1RedTeamJng.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgSpell2RedTeamJng.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
            }
            if (player == 3) {
                imgSpell1RedTeamMid.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgSpell2RedTeamMid.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
            }
            if (player == 4) {
                imgSpell1RedTeamAdc.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgSpell2RedTeamAdc.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
            }
            if (player == 5) {
                imgSpell1RedTeamSup.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgSpell2RedTeamSup.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
            }
        }
    }

    public void limpaListaBuild(int tab, int player) {
        if (tab == 0) {
            if (player == 1) {
                imgItem1BlueTeamTop.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem2BlueTeamTop.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem3BlueTeamTop.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem4BlueTeamTop.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem5BlueTeamTop.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem6BlueTeamTop.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
            }
            if (player == 2) {
                imgItem1BlueTeamJng.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem2BlueTeamJng.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem3BlueTeamJng.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem4BlueTeamJng.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem5BlueTeamJng.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem6BlueTeamJng.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
            }
            if (player == 3) {
                imgItem1BlueTeamMid.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem2BlueTeamMid.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem3BlueTeamMid.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem4BlueTeamMid.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem5BlueTeamMid.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem6BlueTeamMid.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
            }
            if (player == 4) {
                imgItem1BlueTeamAdc.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem2BlueTeamAdc.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem3BlueTeamAdc.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem4BlueTeamAdc.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem5BlueTeamAdc.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem6BlueTeamAdc.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
            }
            if (player == 5) {
                imgItem1BlueTeamSup.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem2BlueTeamSup.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem3BlueTeamSup.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem4BlueTeamSup.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem5BlueTeamSup.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem6BlueTeamSup.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
            }
        }
        if (tab == 1) {
            if (player == 1) {
                imgItem1RedTeamTop.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem2RedTeamTop.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem3RedTeamTop.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem4RedTeamTop.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem5RedTeamTop.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem6RedTeamTop.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
            }
            if (player == 2) {
                imgItem1RedTeamJng.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem2RedTeamJng.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem3RedTeamJng.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem4RedTeamJng.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem5RedTeamJng.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem6RedTeamJng.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
            }
            if (player == 3) {
                imgItem1RedTeamMid.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem2RedTeamMid.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem3RedTeamMid.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem4RedTeamMid.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem5RedTeamMid.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem6RedTeamMid.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
            }
            if (player == 4) {
                imgItem1RedTeamAdc.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem2RedTeamAdc.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem3RedTeamAdc.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem4RedTeamAdc.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem5RedTeamAdc.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem6RedTeamAdc.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
            }
            if (player == 5) {
                imgItem1RedTeamSup.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem2RedTeamSup.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem3RedTeamSup.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem4RedTeamSup.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem5RedTeamSup.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem6RedTeamSup.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
            }
        }
    }

    public void preencheTabsLabelsComboboxesEquipas(Encontro en) {
        encontro = en;
        tabBlueTeam.setText(en.getEquipaByEquipa1().getNome());
        tabRedTeam.setText(en.getEquipaByEquipa2().getNome());
        this.setTeamMemberNames(en.getEquipaByEquipa1(), en.getEquipaByEquipa2());
        this.setComboBoxes(en.getEquipaByEquipa1(), en.getEquipaByEquipa2());
        this.preencheLabelsTorneio(en);
    }

    public void setTeamMemberNames(Equipa eq1, Equipa eq2) {
        for (Membroequipa m : (Collection<Membroequipa>) eq1.getMembroequipas()) {
            if (m.getCargo().equals("player") && m.getPosicao().toString().equals("TOP")) {
                lblBlueTeamTop.setText(m.getNome());
            }
            if (m.getCargo().equals("player") && m.getPosicao().toString().equals("JNG")) {
                lblBlueTeamJng.setText(m.getNome());
            }
            if (m.getCargo().equals("player") && m.getPosicao().toString().equals("MID")) {
                lblBlueTeamMid.setText(m.getNome());
            }
            if (m.getCargo().equals("player") && m.getPosicao().toString().equals("ADC")) {
                lblBlueTeamAdc.setText(m.getNome());
            }
            if (m.getCargo().equals("player") && m.getPosicao().toString().equals("SUP")) {
                lblBlueTeamSup.setText(m.getNome());
            }
        }

        for (Membroequipa m : (Collection<Membroequipa>) eq2.getMembroequipas()) {
            if (m.getCargo().equals("player") && m.getPosicao().toString().equals("TOP")) {
                lblRedTeamTop.setText(m.getNome());
            }
            if (m.getCargo().equals("player") && m.getPosicao().toString().equals("JNG")) {
                lblRedTeamJng.setText(m.getNome());
            }
            if (m.getCargo().equals("player") && m.getPosicao().toString().equals("MID")) {
                lblRedTeamMid.setText(m.getNome());
            }
            if (m.getCargo().equals("player") && m.getPosicao().toString().equals("ADC")) {
                lblRedTeamAdc.setText(m.getNome());
            }
            if (m.getCargo().equals("player") && m.getPosicao().toString().equals("SUP")) {
                lblRedTeamSup.setText(m.getNome());
            }
        }

    }

    public void preencheLabelsTorneio(Encontro en) {
        lblTournament.setText(en.getRonda().getTorneio().getNome());
        lblRound.setText(en.getRonda().getDescricao());
        lblMatch.setText("Manage " + en.getEquipaByEquipa1().getSigla() + " vs " + en.getEquipaByEquipa2().getSigla() + " Match");
        lblGame.setText("Game " + (en.getJogos().size() + 1));
    }

    public void setComboBoxes(Equipa eq1, Equipa eq2) {
        cbWinner.getItems().addAll(eq1.getNome(), eq2.getNome());
    }

    public void verificaElementos() throws InsereMatchStatsException {
        //verificar se existe vencedor
        if (cbWinner.getValue() == null) {
            throw new InsereMatchStatsException("Please select the winner of the match");
        }

        //verificar se toda a gente tem Champions
        if (champsSelecionados.size() != 10 || champsSelecionados == null) {
            throw new InsereMatchStatsException("Please pick a champion for each player");
        }

        //verificar se toda a gente tem spells
        if (listSpellBlueTeamTop == null || listSpellBlueTeamTop.size() != 2
                || listSpellBlueTeamJng == null || listSpellBlueTeamJng.size() != 2
                || listSpellBlueTeamMid == null || listSpellBlueTeamMid.size() != 2
                || listSpellBlueTeamAdc == null || listSpellBlueTeamAdc.size() != 2
                || listSpellBlueTeamSup == null || listSpellBlueTeamSup.size() != 2
                || listSpellRedTeamTop == null || listSpellRedTeamTop.size() != 2
                || listSpellRedTeamJng == null || listSpellRedTeamJng.size() != 2
                || listSpellRedTeamMid == null || listSpellRedTeamMid.size() != 2
                || listSpellRedTeamAdc == null || listSpellRedTeamAdc.size() != 2
                || listSpellBlueTeamSup == null || listSpellBlueTeamSup.size() != 2) {
            throw new InsereMatchStatsException("Please pick two spells for each player");
        }

        //verificar se toda a gente tem runas
        if (runaBlueTeamTop == null || runaBlueTeamJng == null || runaBlueTeamMid == null || runaBlueTeamAdc == null
                || runaBlueTeamSup == null || runaRedTeamTop == null || runaRedTeamJng == null || runaRedTeamMid == null
                || runaRedTeamAdc == null || runaRedTeamSup == null) {
            throw new InsereMatchStatsException("Please pick one rune for each player");
        }
        
        for(Node node: gridPaneBlueTeam.getChildren()){
            if(node instanceof TextField){
                if(((TextField)node).getText().isEmpty()){
                    ((TextField)node).setText("0");
                }
            }
        }
        for(Node node: gridPaneRedTeam.getChildren()){
            if(node instanceof TextField){
                if(((TextField)node).getText().isEmpty()){
                    ((TextField)node).setText("0");
                }
            }
        }
    }

    public void warningAlert() throws InsereMatchStatsException{
            this.verificaElementos();
    }
    
    public void advertWarningAlert() throws EmptyBuildException{
        if (listItemBlueTeamTop == null || listItemBlueTeamTop.isEmpty()
                || listItemBlueTeamJng == null || listItemBlueTeamJng.isEmpty()
                || listItemBlueTeamMid == null || listItemBlueTeamMid.isEmpty()
                || listItemBlueTeamAdc == null || listItemBlueTeamAdc.isEmpty()
                || listItemBlueTeamSup == null || listItemBlueTeamSup.isEmpty()
                || listItemRedTeamTop == null || listItemRedTeamTop.isEmpty()
                || listItemRedTeamJng == null || listItemRedTeamJng.isEmpty()
                || listItemRedTeamMid == null || listItemRedTeamMid.isEmpty()
                || listItemRedTeamAdc == null || listItemRedTeamAdc.isEmpty()
                || listItemRedTeamSup == null || listItemRedTeamSup.isEmpty()) {
            throw new EmptyBuildException("You have one or more builds that have any items. Do you wish to proceed? This operation will be final.");
        }
    }
    
    public void matchInput(){
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Warning");
        alert.setHeaderText("Pay Attention.");
        alert.setContentText("This operation will be final. Are you sure you want to save the match?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK){
            this.gravarMatchBD();
        }
    }
    
    @FXML
    public void saveOnClick() {
        this.save();
        HibernateBLL.clearCache();
    }
    
    public void save(){
        try{
            this.warningAlert();
            this.advertWarningAlert();
            this.matchInput();
            this.atualizaElementosTorneio();
            try {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Operation Successfull");
                alert.setContentText("Your new member was created!");
                alert.showAndWait();
                HibernateBLL.clearCache();
                this.closePopUp();
            } catch (IOException ex) {
                Logger.getLogger(FXMLMatchGameTournamentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }catch (InsereMatchStatsException ex) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Something went wrong.");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        } catch (EmptyBuildException ex) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Warning");
            alert.setHeaderText("Pay Attention.");
            alert.setContentText(ex.getMessage());
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == ButtonType.OK){
                try {
                    this.gravarMatchBD();
                    this.atualizaElementosTorneio();
                    Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
                    alert2.setTitle("Success");
                    alert2.setHeaderText("Operation Successfull");
                    alert2.setContentText("The match was created!");
                    alert2.showAndWait();
                    HibernateBLL.clearCache();
                    this.closePopUp();
                } catch (IOException ex1) {
                    Logger.getLogger(FXMLMatchGameTournamentController.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        }
    }
    
    public void atualizaElementosTorneio(){
        EncontroServices.verificaEncontroConcluído(encontro);
        RondaServices.verificaRondaConcluida(encontro.getRonda());
    }

    //TODO
    /*
    
    Fazer a gravação do Jogo do Encontro e fazer todas as associações
        - criar o Jogo e associar ao encontro
        - criar EstatisticasMembroJogo e associar ao Jogo e ao Membro da equipa
        - associar Build, Spells, Runas a cada estatistica
        - associar o winner do jogo
    
    Fazer as verificações todas para saber se alguém já ganhou o encontro
        - Se sim, associar à proxima ronda e ao encontro em questão
    
    
     */
    
    public void gravarMatchBD(){
        Jogo jogo = new Jogo();
        jogo.setEquipaByEquipa1(encontro.getEquipaByEquipa1());
        jogo.setEquipaByEquipa2(encontro.getEquipaByEquipa2());
       
        if(cbWinner.getValue().toString().equals(encontro.getEquipaByEquipa1().toString())){
            jogo.setEquipaByVencedor(encontro.getEquipaByEquipa1());
            encontro.setVitoriaequipa1(new BigDecimal(encontro.getVitoriaequipa1().intValue()+1));
        }
        if(cbWinner.getValue().toString().equals(encontro.getEquipaByEquipa2().toString())){
            jogo.setEquipaByVencedor(encontro.getEquipaByEquipa2());
            encontro.setVitoriaequipa2(new BigDecimal(encontro.getVitoriaequipa2().intValue()+1));
        }
        
        jogo.setAssistsequipa1(new BigDecimal(this.getTotalAssistsEquipa1()));
        jogo.setAssistsequipa2(new BigDecimal(this.getTotalAssistsEquipa2()));
        jogo.setKillsequipa1(new BigDecimal(this.getTotalKillsEquipa1()));
        jogo.setKillsequipa2(new BigDecimal(this.getTotalKillsEquipa2()));
        jogo.setDeathsequipa1(new BigDecimal(this.getTotalDeathsEquipa1()));
        jogo.setDeathequipa2(new BigDecimal(this.getTotalDeathsEquipa2()));
        
        jogo.setJogoatual(new BigDecimal(encontro.getJogos().size()+1));
        jogo.setEstado("FINALIZADO");
        
        jogo.setEncontro(encontro);
        JogoServices.saveJogo(jogo);
        
        this.gravaEstatisticasMembroEquipa1(jogo);
        this.gravaEstatisticasMembroEquipa2(jogo);
        
        encontro.getRonda().getTorneio().setEstado("COMECADO");
        TorneioServices.saveTorneio(encontro.getRonda().getTorneio());
        
        //Fazer método que verifique se a ronda está terminada
        /*
            Verificar se o nº de winners corresponde ao numero de encontros (size da lista de encontros da ronda)
            Se sim, mudar a variavel rondaAtual no torneio
            Criar nova Ronda, verificar o numero da ronda e numero de vencedores para determinar o número de encontros a criar
        */
    }
    
    public void gravaEstatisticasMembroEquipa1(Jogo jogo){
        for(Membroequipa me: (Set<Membroequipa>)encontro.getEquipaByEquipa1().getMembroequipas()){
            Estatisticasmembrojogo est = new Estatisticasmembrojogo();
            if(me.getPosicao()!=null && me.getPosicao().getSigla().equals("TOP")){
                est.setMembroequipa(me);
                est.setJogo(jogo);
                est.setChampion(championBlueTeamTop);
                est.setAssists(new BigDecimal(Integer.parseInt(this.spinAssistsBlueTeamTop.getText())));
                est.setKills(new BigDecimal(Integer.parseInt(this.spinKillsBlueTeamTop.getText())));
                est.setDeaths(new BigDecimal(Integer.parseInt(this.spinDeathsBlueTeamTop.getText())));
                est.setCs(new BigDecimal(Integer.parseInt(this.spinCSBlueTeamTop.getText())));
                EstatisticasMembroJogoServices.saveEstatisticasMembroJogo(est);
                
                for(Item it: listItemBlueTeamTop){
                    Build build = new Build();
                    build.setItem(it);
                    build.setEstatisticasmembrojogo(est);
                    BuildServices.saveBuild(build);
                }
                for(Spell sp: listSpellBlueTeamTop){
                    Spellescolhido s = new Spellescolhido();
                    SpellescolhidoId id = new SpellescolhidoId(sp.getId(),est.getId());
                    s.setId(id);
                    s.setSpell(sp);
                    s.setEstatisticasmembrojogo(est);
                    SpellEscolhidoServices.saveSpellEscolhido(s);
                }
                
                Runaescolhida r = new Runaescolhida();
                RunaescolhidaId id = new RunaescolhidaId(runaBlueTeamTop.getId(), est.getId());
                r.setId(id);
                r.setEstatisticasmembrojogo(est);
                r.setRuna(runaBlueTeamTop);
                RunaEscolhidaServices.saveRunaEscolhida(r);
            }
            if(me.getPosicao()!=null && me.getPosicao().getSigla().equals("JNG")){
                est.setMembroequipa(me);
                est.setJogo(jogo);
                est.setChampion(championBlueTeamJng);
                est.setAssists(new BigDecimal(Integer.parseInt(this.spinAssistsBlueTeamJng.getText())));
                est.setKills(new BigDecimal(Integer.parseInt(this.spinKillsBlueTeamJng.getText())));
                est.setDeaths(new BigDecimal(Integer.parseInt(this.spinDeathsBlueTeamJng.getText())));
                est.setCs(new BigDecimal(Integer.parseInt(this.spinCSBlueTeamJng.getText())));
                EstatisticasMembroJogoServices.saveEstatisticasMembroJogo(est);
                
                for(Item it: listItemBlueTeamJng){
                    Build build = new Build();
                    build.setItem(it);
                    build.setEstatisticasmembrojogo(est);
                    BuildServices.saveBuild(build);
                }
                for(Spell sp: listSpellBlueTeamJng){
                    Spellescolhido s = new Spellescolhido();
                    SpellescolhidoId id = new SpellescolhidoId(sp.getId(),est.getId());
                    s.setId(id);
                    s.setSpell(sp);
                    s.setEstatisticasmembrojogo(est);
                    SpellEscolhidoServices.saveSpellEscolhido(s);
                }
                
                Runaescolhida r = new Runaescolhida();
                RunaescolhidaId id = new RunaescolhidaId(runaBlueTeamJng.getId(), est.getId());
                r.setId(id);
                r.setEstatisticasmembrojogo(est);
                r.setRuna(runaBlueTeamJng);
                RunaEscolhidaServices.saveRunaEscolhida(r); 
            }
            if(me.getPosicao()!=null && me.getPosicao().getSigla().equals("MID")){
                est.setMembroequipa(me);
                est.setJogo(jogo);
                est.setChampion(championBlueTeamMid);
                est.setAssists(new BigDecimal(Integer.parseInt(this.spinAssistsBlueTeamMid.getText())));
                est.setKills(new BigDecimal(Integer.parseInt(this.spinKillsBlueTeamMid.getText())));
                est.setDeaths(new BigDecimal(Integer.parseInt(this.spinDeathsBlueTeamMid.getText())));
                est.setCs(new BigDecimal(Integer.parseInt(this.spinCSBlueTeamMid.getText())));
                EstatisticasMembroJogoServices.saveEstatisticasMembroJogo(est);
                
                for(Item it: listItemBlueTeamMid){
                    Build build = new Build();
                    build.setItem(it);
                    build.setEstatisticasmembrojogo(est);
                    BuildServices.saveBuild(build);
                }
                for(Spell sp: listSpellBlueTeamMid){
                    Spellescolhido s = new Spellescolhido();
                    SpellescolhidoId id = new SpellescolhidoId(sp.getId(),est.getId());
                    s.setId(id);
                    s.setSpell(sp);
                    s.setEstatisticasmembrojogo(est);
                    SpellEscolhidoServices.saveSpellEscolhido(s);
                }
                
                Runaescolhida r = new Runaescolhida();
                RunaescolhidaId id = new RunaescolhidaId(runaBlueTeamMid.getId(), est.getId());
                r.setId(id);
                r.setEstatisticasmembrojogo(est);
                r.setRuna(runaBlueTeamMid);
                RunaEscolhidaServices.saveRunaEscolhida(r);
            }
            if(me.getPosicao()!=null && me.getPosicao().getSigla().equals("ADC")){
                est.setMembroequipa(me);
                est.setJogo(jogo);
                est.setChampion(championBlueTeamAdc);
                est.setAssists(new BigDecimal(Integer.parseInt(this.spinAssistsBlueTeamAdc.getText())));
                est.setKills(new BigDecimal(Integer.parseInt(this.spinKillsBlueTeamAdc.getText())));
                est.setDeaths(new BigDecimal(Integer.parseInt(this.spinDeathsBlueTeamAdc.getText())));
                est.setCs(new BigDecimal(Integer.parseInt(this.spinCSBlueTeamAdc.getText())));
                EstatisticasMembroJogoServices.saveEstatisticasMembroJogo(est);
                
                for(Item it: listItemBlueTeamAdc){
                    Build build = new Build();
                    build.setItem(it);
                    build.setEstatisticasmembrojogo(est);
                    BuildServices.saveBuild(build);
                }
                for(Spell sp: listSpellBlueTeamAdc){
                    Spellescolhido s = new Spellescolhido();
                    SpellescolhidoId id = new SpellescolhidoId(sp.getId(),est.getId());
                    s.setId(id);
                    s.setSpell(sp);
                    s.setEstatisticasmembrojogo(est);
                    SpellEscolhidoServices.saveSpellEscolhido(s);
                }
                
                Runaescolhida r = new Runaescolhida();
                RunaescolhidaId id = new RunaescolhidaId(runaBlueTeamAdc.getId(), est.getId());
                r.setId(id);
                r.setEstatisticasmembrojogo(est);
                r.setRuna(runaBlueTeamAdc);
                RunaEscolhidaServices.saveRunaEscolhida(r);
            }
            if(me.getPosicao()!=null && me.getPosicao().getSigla().equals("SUP")){
                est.setMembroequipa(me);
                est.setJogo(jogo);
                est.setChampion(championBlueTeamSup);
                est.setAssists(new BigDecimal(Integer.parseInt(this.spinAssistsBlueTeamSup.getText())));
                est.setKills(new BigDecimal(Integer.parseInt(this.spinKillsBlueTeamSup.getText())));
                est.setDeaths(new BigDecimal(Integer.parseInt(this.spinDeathsBlueTeamSup.getText())));
                est.setCs(new BigDecimal(Integer.parseInt(this.spinCSBlueTeamSup.getText())));
                EstatisticasMembroJogoServices.saveEstatisticasMembroJogo(est);
                
                for(Item it: listItemBlueTeamSup){
                    Build build = new Build();
                    build.setItem(it);
                    build.setEstatisticasmembrojogo(est);
                    BuildServices.saveBuild(build);
                }
                for(Spell sp: listSpellBlueTeamSup){
                    Spellescolhido s = new Spellescolhido();
                    SpellescolhidoId id = new SpellescolhidoId(sp.getId(),est.getId());
                    s.setId(id);
                    s.setSpell(sp);
                    s.setEstatisticasmembrojogo(est);
                    SpellEscolhidoServices.saveSpellEscolhido(s);
                }
                
                Runaescolhida r = new Runaescolhida();
                RunaescolhidaId id = new RunaescolhidaId(runaBlueTeamSup.getId(), est.getId());
                r.setId(id);
                r.setEstatisticasmembrojogo(est);
                r.setRuna(runaBlueTeamSup);
                RunaEscolhidaServices.saveRunaEscolhida(r);
            }
        }
    }
    
    public void gravaEstatisticasMembroEquipa2(Jogo jogo){
        for(Membroequipa me: (Set<Membroequipa>)encontro.getEquipaByEquipa2().getMembroequipas()){
            Estatisticasmembrojogo est = new Estatisticasmembrojogo();
            if(me.getPosicao()!=null && me.getPosicao().getSigla().equals("TOP")){
                est.setMembroequipa(me);
                est.setJogo(jogo);
                est.setChampion(championRedTeamTop);
                est.setAssists(new BigDecimal(Integer.parseInt(this.spinAssistsRedTeamTop.getText())));
                est.setKills(new BigDecimal(Integer.parseInt(this.spinKillsRedTeamTop.getText())));
                est.setDeaths(new BigDecimal(Integer.parseInt(this.spinDeathsRedTeamTop.getText())));
                est.setCs(new BigDecimal(Integer.parseInt(this.spinCSRedTeamTop.getText())));
                EstatisticasMembroJogoServices.saveEstatisticasMembroJogo(est);
                
                for(Item it: listItemRedTeamTop){
                    Build build = new Build();
                    build.setItem(it);
                    build.setEstatisticasmembrojogo(est);
                    BuildServices.saveBuild(build);
                }
                for(Spell sp: listSpellRedTeamTop){
                    Spellescolhido s = new Spellescolhido();
                    SpellescolhidoId id = new SpellescolhidoId(sp.getId(),est.getId());
                    s.setId(id);
                    s.setSpell(sp);
                    s.setEstatisticasmembrojogo(est);
                    SpellEscolhidoServices.saveSpellEscolhido(s);
                }
                
                Runaescolhida r = new Runaescolhida();
                RunaescolhidaId id = new RunaescolhidaId(runaRedTeamTop.getId(), est.getId());
                r.setId(id);
                r.setEstatisticasmembrojogo(est);
                r.setRuna(runaRedTeamTop);
                RunaEscolhidaServices.saveRunaEscolhida(r);
            }
            if(me.getPosicao()!=null && me.getPosicao().getSigla().equals("JNG")){
                est.setMembroequipa(me);
                est.setJogo(jogo);
                est.setChampion(championRedTeamJng);
                est.setAssists(new BigDecimal(Integer.parseInt(this.spinAssistsRedTeamJng.getText())));
                est.setKills(new BigDecimal(Integer.parseInt(this.spinKillsRedTeamJng.getText())));
                est.setDeaths(new BigDecimal(Integer.parseInt(this.spinDeathsRedTeamJng.getText())));
                est.setCs(new BigDecimal(Integer.parseInt(this.spinCSRedTeamJng.getText())));
                EstatisticasMembroJogoServices.saveEstatisticasMembroJogo(est);
                
                for(Item it: listItemRedTeamJng){
                    Build build = new Build();
                    build.setItem(it);
                    build.setEstatisticasmembrojogo(est);
                    BuildServices.saveBuild(build);
                }
                for(Spell sp: listSpellRedTeamJng){
                    Spellescolhido s = new Spellescolhido();
                    SpellescolhidoId id = new SpellescolhidoId(sp.getId(),est.getId());
                    s.setId(id);
                    s.setSpell(sp);
                    s.setEstatisticasmembrojogo(est);
                    SpellEscolhidoServices.saveSpellEscolhido(s);
                }
                
                Runaescolhida r = new Runaescolhida();
                RunaescolhidaId id = new RunaescolhidaId(runaRedTeamJng.getId(), est.getId());
                r.setId(id);
                r.setEstatisticasmembrojogo(est);
                r.setRuna(runaRedTeamJng);
                RunaEscolhidaServices.saveRunaEscolhida(r); 
            }
            if(me.getPosicao()!=null && me.getPosicao().getSigla().equals("MID")){
                est.setMembroequipa(me);
                est.setJogo(jogo);
                est.setChampion(championRedTeamMid);
                est.setAssists(new BigDecimal(Integer.parseInt(this.spinAssistsRedTeamMid.getText())));
                est.setKills(new BigDecimal(Integer.parseInt(this.spinKillsRedTeamMid.getText())));
                est.setDeaths(new BigDecimal(Integer.parseInt(this.spinDeathsRedTeamMid.getText())));
                est.setCs(new BigDecimal(Integer.parseInt(this.spinCSRedTeamMid.getText())));
                EstatisticasMembroJogoServices.saveEstatisticasMembroJogo(est);
                
                for(Item it: listItemRedTeamMid){
                    Build build = new Build();
                    build.setItem(it);
                    build.setEstatisticasmembrojogo(est);
                    BuildServices.saveBuild(build);
                }
                for(Spell sp: listSpellRedTeamMid){
                    Spellescolhido s = new Spellescolhido();
                    SpellescolhidoId id = new SpellescolhidoId(sp.getId(),est.getId());
                    s.setId(id);
                    s.setSpell(sp);
                    s.setEstatisticasmembrojogo(est);
                    SpellEscolhidoServices.saveSpellEscolhido(s);
                }
                
                Runaescolhida r = new Runaescolhida();
                RunaescolhidaId id = new RunaescolhidaId(runaRedTeamMid.getId(), est.getId());
                r.setId(id);
                r.setEstatisticasmembrojogo(est);
                r.setRuna(runaRedTeamMid);
                RunaEscolhidaServices.saveRunaEscolhida(r);
            }
            if(me.getPosicao()!=null && me.getPosicao().getSigla().equals("ADC")){
                est.setMembroequipa(me);
                est.setJogo(jogo);
                est.setChampion(championRedTeamAdc);
                est.setAssists(new BigDecimal(Integer.parseInt(this.spinAssistsRedTeamAdc.getText())));
                est.setKills(new BigDecimal(Integer.parseInt(this.spinKillsRedTeamAdc.getText())));
                est.setDeaths(new BigDecimal(Integer.parseInt(this.spinDeathsRedTeamAdc.getText())));
                est.setCs(new BigDecimal(Integer.parseInt(this.spinCSRedTeamAdc.getText())));               
                EstatisticasMembroJogoServices.saveEstatisticasMembroJogo(est);
                
                for(Item it: listItemRedTeamAdc){
                    Build build = new Build();
                    build.setItem(it);
                    build.setEstatisticasmembrojogo(est);
                    BuildServices.saveBuild(build);
                }
                for(Spell sp: listSpellRedTeamAdc){
                    Spellescolhido s = new Spellescolhido();
                    SpellescolhidoId id = new SpellescolhidoId(sp.getId(),est.getId());
                    s.setId(id);
                    s.setSpell(sp);
                    s.setEstatisticasmembrojogo(est);
                    SpellEscolhidoServices.saveSpellEscolhido(s);
                }
                
                Runaescolhida r = new Runaescolhida();
                RunaescolhidaId id = new RunaescolhidaId(runaRedTeamAdc.getId(), est.getId());
                r.setId(id);
                r.setEstatisticasmembrojogo(est);
                r.setRuna(runaRedTeamAdc);
                RunaEscolhidaServices.saveRunaEscolhida(r);
            }
            if(me.getPosicao()!=null && me.getPosicao().getSigla().equals("SUP")){
                est.setMembroequipa(me);
                est.setJogo(jogo);
                est.setChampion(championRedTeamSup);
                est.setAssists(new BigDecimal(Integer.parseInt(this.spinAssistsRedTeamSup.getText())));
                est.setKills(new BigDecimal(Integer.parseInt(this.spinKillsRedTeamSup.getText())));
                est.setDeaths(new BigDecimal(Integer.parseInt(this.spinDeathsRedTeamSup.getText())));
                est.setCs(new BigDecimal(Integer.parseInt(this.spinCSRedTeamSup.getText())));
                EstatisticasMembroJogoServices.saveEstatisticasMembroJogo(est);
                
                for(Item it: listItemRedTeamSup){
                    Build build = new Build();
                    build.setItem(it);
                    build.setEstatisticasmembrojogo(est);
                    BuildServices.saveBuild(build);
                }
                for(Spell sp: listSpellRedTeamSup){
                    Spellescolhido s = new Spellescolhido();
                    SpellescolhidoId id = new SpellescolhidoId(sp.getId(),est.getId());
                    s.setId(id);
                    s.setSpell(sp);
                    s.setEstatisticasmembrojogo(est);
                    SpellEscolhidoServices.saveSpellEscolhido(s);
                }
                
                Runaescolhida r = new Runaescolhida();
                RunaescolhidaId id = new RunaescolhidaId(runaRedTeamSup.getId(), est.getId());
                r.setId(id);
                r.setEstatisticasmembrojogo(est);
                r.setRuna(runaRedTeamSup);
                RunaEscolhidaServices.saveRunaEscolhida(r);
            }
        }
    }
    
    public int getTotalAssistsEquipa1(){
        return (Integer.parseInt(this.spinAssistsBlueTeamTop.getText()) + Integer.parseInt(this.spinAssistsBlueTeamJng.getText()) +
                Integer.parseInt(this.spinAssistsBlueTeamMid.getText()) +
                Integer.parseInt(this.spinAssistsBlueTeamAdc.getText()) + Integer.parseInt(this.spinAssistsBlueTeamSup.getText()));
    }

    private int getTotalAssistsEquipa2() {
        return (Integer.parseInt(this.spinAssistsRedTeamTop.getText()) + Integer.parseInt(this.spinAssistsRedTeamJng.getText()) +
                Integer.parseInt(this.spinAssistsRedTeamMid.getText()) +
                Integer.parseInt(this.spinAssistsRedTeamAdc.getText()) + Integer.parseInt(this.spinAssistsRedTeamSup.getText()));
    }
    
    public int getTotalKillsEquipa1(){
        return (Integer.parseInt(this.spinKillsBlueTeamTop.getText()) + Integer.parseInt(this.spinKillsBlueTeamJng.getText()) +
                Integer.parseInt(this.spinKillsBlueTeamMid.getText()) +
                Integer.parseInt(this.spinKillsBlueTeamAdc.getText()) + Integer.parseInt(this.spinKillsBlueTeamSup.getText()));
    }

    private int getTotalKillsEquipa2() {
        return (Integer.parseInt(this.spinKillsRedTeamTop.getText()) + Integer.parseInt(this.spinKillsRedTeamJng.getText()) +
                Integer.parseInt(this.spinKillsRedTeamMid.getText()) +
                Integer.parseInt(this.spinKillsRedTeamAdc.getText()) + Integer.parseInt(this.spinKillsRedTeamSup.getText()));
    }
    
    public int getTotalDeathsEquipa1(){
        return (Integer.parseInt(this.spinDeathsBlueTeamTop.getText()) + Integer.parseInt(this.spinDeathsBlueTeamJng.getText()) +
                Integer.parseInt(this.spinDeathsBlueTeamMid.getText()) +
                Integer.parseInt(this.spinDeathsBlueTeamAdc.getText()) + Integer.parseInt(this.spinDeathsBlueTeamSup.getText()));
    }

    private int getTotalDeathsEquipa2() {
        return (Integer.parseInt(this.spinDeathsRedTeamTop.getText()) + Integer.parseInt(this.spinDeathsRedTeamJng.getText()) +
                Integer.parseInt(this.spinDeathsRedTeamMid.getText()) +
                Integer.parseInt(this.spinDeathsRedTeamAdc.getText()) + Integer.parseInt(this.spinDeathsRedTeamSup.getText()));
    }
    
}
