/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lolui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import loldal.model.Champion;
import loldal.model.Encontro;
import loldal.model.Equipa;
import loldal.model.Item;
import loldal.model.Membroequipa;
import loldal.model.Runa;
import loldal.model.Spell;

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
    
    @FXML private Tab tabBlueTeam;
    @FXML private Tab tabRedTeam;
    
    //Labels das equipas
    
    @FXML private Label lblBlueTeamTop;
    @FXML private Label lblBlueTeamJng;
    @FXML private Label lblBlueTeamMid;
    @FXML private Label lblBlueTeamAdc;
    @FXML private Label lblBlueTeamSup;
    
    @FXML private Label lblRedTeamTop;
    @FXML private Label lblRedTeamJng;
    @FXML private Label lblRedTeamMid;
    @FXML private Label lblRedTeamAdc;
    @FXML private Label lblRedTeamSup;
    
    //ComboBoxes
    
    @FXML private ComboBox cbTournament;
    @FXML private ComboBox cbRound;
    @FXML private ComboBox cbMatch;
    @FXML private ComboBox cbGame;
    @FXML private ComboBox cbWinner;
    
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
    @FXML private ImageView imgItem1BlueTeamTop;
    @FXML private ImageView imgItem2BlueTeamTop;
    @FXML private ImageView imgItem3BlueTeamTop;
    @FXML private ImageView imgItem4BlueTeamTop;
    @FXML private ImageView imgItem5BlueTeamTop;
    @FXML private ImageView imgItem6BlueTeamTop;
    
    @FXML private ImageView imgItem1BlueTeamJng;
    @FXML private ImageView imgItem2BlueTeamJng;
    @FXML private ImageView imgItem3BlueTeamJng;
    @FXML private ImageView imgItem4BlueTeamJng;
    @FXML private ImageView imgItem5BlueTeamJng;
    @FXML private ImageView imgItem6BlueTeamJng;
    
    @FXML private ImageView imgItem1BlueTeamMid;
    @FXML private ImageView imgItem2BlueTeamMid;
    @FXML private ImageView imgItem3BlueTeamMid;
    @FXML private ImageView imgItem4BlueTeamMid;
    @FXML private ImageView imgItem5BlueTeamMid;
    @FXML private ImageView imgItem6BlueTeamMid;
    
    @FXML private ImageView imgItem1BlueTeamAdc;
    @FXML private ImageView imgItem2BlueTeamAdc;
    @FXML private ImageView imgItem3BlueTeamAdc;
    @FXML private ImageView imgItem4BlueTeamAdc;
    @FXML private ImageView imgItem5BlueTeamAdc;
    @FXML private ImageView imgItem6BlueTeamAdc;
    
    @FXML private ImageView imgItem1BlueTeamSup;
    @FXML private ImageView imgItem2BlueTeamSup;
    @FXML private ImageView imgItem3BlueTeamSup;
    @FXML private ImageView imgItem4BlueTeamSup;
    @FXML private ImageView imgItem5BlueTeamSup;
    @FXML private ImageView imgItem6BlueTeamSup;
    
    
    //ImageViews dos Items de cada Jogador RED TEAM
    @FXML private ImageView imgItem1RedTeamTop;
    @FXML private ImageView imgItem2RedTeamTop;
    @FXML private ImageView imgItem3RedTeamTop;
    @FXML private ImageView imgItem4RedTeamTop;
    @FXML private ImageView imgItem5RedTeamTop;
    @FXML private ImageView imgItem6RedTeamTop;
    
    @FXML private ImageView imgItem1RedTeamJng;
    @FXML private ImageView imgItem2RedTeamJng;
    @FXML private ImageView imgItem3RedTeamJng;
    @FXML private ImageView imgItem4RedTeamJng;
    @FXML private ImageView imgItem5RedTeamJng;
    @FXML private ImageView imgItem6RedTeamJng;
    
    @FXML private ImageView imgItem1RedTeamMid;
    @FXML private ImageView imgItem2RedTeamMid;
    @FXML private ImageView imgItem3RedTeamMid;
    @FXML private ImageView imgItem4RedTeamMid;
    @FXML private ImageView imgItem5RedTeamMid;
    @FXML private ImageView imgItem6RedTeamMid;
    
    @FXML private ImageView imgItem1RedTeamAdc;
    @FXML private ImageView imgItem2RedTeamAdc;
    @FXML private ImageView imgItem3RedTeamAdc;
    @FXML private ImageView imgItem4RedTeamAdc;
    @FXML private ImageView imgItem5RedTeamAdc;
    @FXML private ImageView imgItem6RedTeamAdc;
    
    @FXML private ImageView imgItem1RedTeamSup;
    @FXML private ImageView imgItem2RedTeamSup;
    @FXML private ImageView imgItem3RedTeamSup;
    @FXML private ImageView imgItem4RedTeamSup;
    @FXML private ImageView imgItem5RedTeamSup;
    @FXML private ImageView imgItem6RedTeamSup;
    
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
    
    @FXML private ImageView imgSpell1BlueTeamTop;
    @FXML private ImageView imgSpell2BlueTeamTop;
    @FXML private ImageView imgSpell1BlueTeamJng;
    @FXML private ImageView imgSpell2BlueTeamJng;
    @FXML private ImageView imgSpell1BlueTeamMid;
    @FXML private ImageView imgSpell2BlueTeamMid;
    @FXML private ImageView imgSpell1BlueTeamAdc;
    @FXML private ImageView imgSpell2BlueTeamAdc;
    @FXML private ImageView imgSpell1BlueTeamSup;
    @FXML private ImageView imgSpell2BlueTeamSup;
    
    //ImageView dos Spells dos Jogadores Equipa Red Team
    
    @FXML private ImageView imgSpell1RedTeamTop;
    @FXML private ImageView imgSpell2RedTeamTop;
    @FXML private ImageView imgSpell1RedTeamJng;
    @FXML private ImageView imgSpell2RedTeamJng;
    @FXML private ImageView imgSpell1RedTeamMid;
    @FXML private ImageView imgSpell2RedTeamMid;
    @FXML private ImageView imgSpell1RedTeamAdc;
    @FXML private ImageView imgSpell2RedTeamAdc;
    @FXML private ImageView imgSpell1RedTeamSup;
    @FXML private ImageView imgSpell2RedTeamSup;
    
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
    
    @FXML ImageView imgChampBlueTeamTop;
    @FXML ImageView imgChampBlueTeamJng;
    @FXML ImageView imgChampBlueTeamMid;
    @FXML ImageView imgChampBlueTeamAdc;
    @FXML ImageView imgChampBlueTeamSup;
    
    @FXML ImageView imgChampRedTeamTop;
    @FXML ImageView imgChampRedTeamJng;
    @FXML ImageView imgChampRedTeamMid;
    @FXML ImageView imgChampRedTeamAdc;
    @FXML ImageView imgChampRedTeamSup;
    
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
    
    @FXML private ImageView imgRunaBlueTeamTop;
    @FXML private ImageView imgRunaBlueTeamJng;
    @FXML private ImageView imgRunaBlueTeamMid;
    @FXML private ImageView imgRunaBlueTeamAdc;
    @FXML private ImageView imgRunaBlueTeamSup;
    
    @FXML private ImageView imgRunaRedTeamTop;
    @FXML private ImageView imgRunaRedTeamJng;
    @FXML private ImageView imgRunaRedTeamMid;
    @FXML private ImageView imgRunaRedTeamAdc;
    @FXML private ImageView imgRunaRedTeamSup;
    
    //Spinners da Blue Team
    
    @FXML private Spinner<Integer> spinCSBlueTeamTop;
    @FXML private Spinner<Integer> spinCSBlueTeamJng;
    @FXML private Spinner<Integer> spinCSBlueTeamMid;
    @FXML private Spinner<Integer> spinCSBlueTeamAdc;
    @FXML private Spinner<Integer> spinCSBlueTeamSup;
    
    @FXML private Spinner<Integer> spinKillsBlueTeamTop;
    @FXML private Spinner<Integer> spinKillsBlueTeamJng;
    @FXML private Spinner<Integer> spinKillsBlueTeamMid;
    @FXML private Spinner<Integer> spinKillsBlueTeamAdc;
    @FXML private Spinner<Integer> spinKillsBlueTeamSup;
    
    @FXML private Spinner<Integer> spinDeathsBlueTeamTop;
    @FXML private Spinner<Integer> spinDeathsBlueTeamJng;
    @FXML private Spinner<Integer> spinDeathsBlueTeamMid;
    @FXML private Spinner<Integer> spinDeathsBlueTeamAdc;
    @FXML private Spinner<Integer> spinDeathsBlueTeamSup;
    
    @FXML private Spinner<Integer> spinAssistsBlueTeamTop;
    @FXML private Spinner<Integer> spinAssistsBlueTeamJng;
    @FXML private Spinner<Integer> spinAssistsBlueTeamMid;
    @FXML private Spinner<Integer> spinAssistsBlueTeamAdc;
    @FXML private Spinner<Integer> spinAssistsBlueTeamSup;
    
    //Spinners da Red Team
    
    @FXML private Spinner<Integer> spinCSRedTeamTop;
    @FXML private Spinner<Integer> spinCSRedTeamJng;
    @FXML private Spinner<Integer> spinCSRedTeamMid;
    @FXML private Spinner<Integer> spinCSRedTeamAdc;
    @FXML private Spinner<Integer> spinCSRedTeamSup;
    
    @FXML private Spinner<Integer> spinKillsRedTeamTop;
    @FXML private Spinner<Integer> spinKillsRedTeamJng;
    @FXML private Spinner<Integer> spinKillsRedTeamMid;
    @FXML private Spinner<Integer> spinKillsRedTeamAdc;
    @FXML private Spinner<Integer> spinKillsRedTeamSup;
    
    @FXML private Spinner<Integer> spinDeathsRedTeamTop;
    @FXML private Spinner<Integer> spinDeathsRedTeamJng;
    @FXML private Spinner<Integer> spinDeathsRedTeamMid;
    @FXML private Spinner<Integer> spinDeathsRedTeamAdc;
    @FXML private Spinner<Integer> spinDeathsRedTeamSup;
    
    @FXML private Spinner<Integer> spinAssistsRedTeamTop;
    @FXML private Spinner<Integer> spinAssistsRedTeamJng;
    @FXML private Spinner<Integer> spinAssistsRedTeamMid;
    @FXML private Spinner<Integer> spinAssistsRedTeamAdc;
    @FXML private Spinner<Integer> spinAssistsRedTeamSup;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        parentBorderPane.getStyleClass().add("borderPane");
        this.prepareActionsGrid();
        champsSelecionados = new ArrayList<>();
        this.prepareSpinners();
    }
    
    @FXML
    public void closePopUp() {
        Stage stage = (Stage) this.imgBack.getScene().getWindow();
        stage.close();
    }
    
    public void prepareSpinners(){
        spinCSBlueTeamTop.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, 0));
        spinCSBlueTeamJng.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, 0));
        spinCSBlueTeamMid.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, 0));
        spinCSBlueTeamAdc.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, 0));
        spinCSBlueTeamSup.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, 0));

        spinKillsBlueTeamTop.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, 0));
        spinKillsBlueTeamJng.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, 0));
        spinKillsBlueTeamMid.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, 0));
        spinKillsBlueTeamAdc.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, 0));
        spinKillsBlueTeamSup.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, 0));

        spinDeathsBlueTeamTop.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, 0));
        spinDeathsBlueTeamJng.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, 0));
        spinDeathsBlueTeamMid.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, 0));
        spinDeathsBlueTeamAdc.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, 0));
        spinDeathsBlueTeamSup.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, 0));

        spinAssistsBlueTeamTop.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, 0));
        spinAssistsBlueTeamJng.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, 0));
        spinAssistsBlueTeamMid.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, 0));
        spinAssistsBlueTeamAdc.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, 0));
        spinAssistsBlueTeamSup.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, 0));
        
        spinCSRedTeamTop.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, 0));
        spinCSRedTeamJng.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, 0));
        spinCSRedTeamMid.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, 0));
        spinCSRedTeamAdc.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, 0));
        spinCSRedTeamSup.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, 0));

        spinKillsRedTeamTop.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, 0));
        spinKillsRedTeamJng.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, 0));
        spinKillsRedTeamMid.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, 0));
        spinKillsRedTeamAdc.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, 0));
        spinKillsRedTeamSup.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, 0));

        spinDeathsRedTeamTop.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, 0));
        spinDeathsRedTeamJng.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, 0));
        spinDeathsRedTeamMid.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, 0));
        spinDeathsRedTeamAdc.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, 0));
        spinDeathsRedTeamSup.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, 0));

        spinAssistsRedTeamTop.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, 0));
        spinAssistsRedTeamJng.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, 0));
        spinAssistsRedTeamMid.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, 0));
        spinAssistsRedTeamAdc.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, 0));
        spinAssistsRedTeamSup.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, 0));
    }
    
    @FXML
    public void createItemAction(MouseEvent event, int tab, int player) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLItemSelection.fxml"));
        Parent root = loader.load();
        FXMLItemSelectionController controller = loader.getController();
        controller.preencheGridItems();
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
        if(tab == 0){
            this.limpaListaBuild(tab, player);
            if(player == 1){
                if(controller.getItemList()!=null && controller.getItemList().size()>0){
                    listItemBlueTeamTop = controller.getItemList();
                    System.out.println(listItemBlueTeamTop);
                    this.preencheBuildPlayer(listItemBlueTeamTop, imgItem1BlueTeamTop, imgItem2BlueTeamTop, imgItem3BlueTeamTop, imgItem4BlueTeamTop, imgItem5BlueTeamTop, imgItem6BlueTeamTop);
                }
            }
            if(player == 2){
                if(controller.getItemList()!=null && controller.getItemList().size()>0){
                    listItemBlueTeamJng  = controller.getItemList();
                    this.preencheBuildPlayer(listItemBlueTeamJng, imgItem1BlueTeamJng, imgItem2BlueTeamJng, imgItem3BlueTeamJng, imgItem4BlueTeamJng, imgItem5BlueTeamJng, imgItem6BlueTeamJng);
                }
            }
            if(player == 3){
                if(controller.getItemList()!=null && controller.getItemList().size()>0){
                    listItemBlueTeamMid = controller.getItemList();
                    this.preencheBuildPlayer(listItemBlueTeamMid, imgItem1BlueTeamMid, imgItem2BlueTeamMid, imgItem3BlueTeamMid, imgItem4BlueTeamMid, imgItem5BlueTeamMid, imgItem6BlueTeamMid);
                }
            }
            if(player == 4){
                if(controller.getItemList()!=null && controller.getItemList().size()>0){
                    listItemBlueTeamAdc = controller.getItemList();
                    this.preencheBuildPlayer(listItemBlueTeamAdc, imgItem1BlueTeamAdc, imgItem2BlueTeamAdc, imgItem3BlueTeamAdc, imgItem4BlueTeamAdc, imgItem5BlueTeamAdc, imgItem6BlueTeamAdc);
                }
            }
            if(player == 5){
                if(controller.getItemList()!=null && controller.getItemList().size()>0){
                    listItemBlueTeamSup = controller.getItemList();
                    this.preencheBuildPlayer(listItemBlueTeamSup, imgItem1BlueTeamSup, imgItem2BlueTeamSup, imgItem3BlueTeamSup, imgItem4BlueTeamSup, imgItem5BlueTeamSup, imgItem6BlueTeamSup);
                }
            }
        }
        if(tab == 1){
            this.limpaListaBuild(tab, player);
            if(player == 1){
                if(controller.getItemList()!=null && controller.getItemList().size()>0){
                    listItemRedTeamTop = controller.getItemList();
                    this.preencheBuildPlayer(listItemRedTeamTop, imgItem1RedTeamTop, imgItem2RedTeamTop, imgItem3RedTeamTop, imgItem4RedTeamTop, imgItem5RedTeamTop, imgItem6RedTeamTop);
                }
            }
            if(player == 2){
                if(controller.getItemList()!=null && controller.getItemList().size()>0){
                    listItemRedTeamJng = controller.getItemList();
                    this.preencheBuildPlayer(listItemRedTeamJng, imgItem1RedTeamJng, imgItem2RedTeamJng, imgItem3RedTeamJng, imgItem4RedTeamJng, imgItem5RedTeamJng, imgItem6RedTeamJng);
                }
            }
            if(player == 3){
                if(controller.getItemList()!=null && controller.getItemList().size()>0){
                    listItemRedTeamMid = controller.getItemList();
                    this.preencheBuildPlayer(listItemRedTeamMid, imgItem1RedTeamMid, imgItem2RedTeamMid, imgItem3RedTeamMid, imgItem4RedTeamMid, imgItem5RedTeamMid, imgItem6RedTeamMid);
                }
            }
            if(player == 4){
                if(controller.getItemList()!=null && controller.getItemList().size()>0){
                    listItemRedTeamAdc = controller.getItemList();
                    this.preencheBuildPlayer(listItemRedTeamAdc, imgItem1RedTeamAdc, imgItem2RedTeamAdc, imgItem3RedTeamAdc, imgItem4RedTeamAdc, imgItem5RedTeamAdc, imgItem6RedTeamAdc);
                }
            }
            if(player == 5){
                if(controller.getItemList()!=null && controller.getItemList().size()>0){
                    listItemRedTeamSup = controller.getItemList();
                    this.preencheBuildPlayer(listItemRedTeamSup, imgItem1RedTeamSup, imgItem2RedTeamSup, imgItem3RedTeamSup, imgItem4RedTeamSup, imgItem5RedTeamSup, imgItem6RedTeamSup);
                }
            }
        }
    }
    
    public void preencheBuildPlayer(List<Item> list, ImageView img1, ImageView img2, ImageView img3, ImageView img4, ImageView img5, ImageView img6){
        
        if(list.size()>0){
            img1.setImage(new Image(getClass().getResourceAsStream("pics/items/" + list.get(0).getNome().toLowerCase() + ".png")));
        }
        if(list.size()>1){
            img2.setImage(new Image(getClass().getResourceAsStream("pics/items/" + list.get(1).getNome().toLowerCase() + ".png")));
        }
        if(list.size()>2){
            img3.setImage(new Image(getClass().getResourceAsStream("pics/items/" + list.get(2).getNome().toLowerCase() + ".png")));
        }
        if(list.size()>3){
            img4.setImage(new Image(getClass().getResourceAsStream("pics/items/" + list.get(3).getNome().toLowerCase() + ".png")));
        }
        if(list.size()>4){
            img5.setImage(new Image(getClass().getResourceAsStream("pics/items/" + list.get(4).getNome().toLowerCase() + ".png")));
        }
        if(list.size()>5){
            img6.setImage(new Image(getClass().getResourceAsStream("pics/items/" + list.get(5).getNome().toLowerCase() + ".png")));
        }
        
    }
    
    public void preencheSpellsPlayer(List<Spell> list, ImageView img1, ImageView img2){
        if(list.size()>0){
            img1.setImage(new Image(getClass().getResourceAsStream("pics/spells/" + list.get(0).getNome().toLowerCase() + ".png")));
        }
        if(list.size()>1){
            img2.setImage(new Image(getClass().getResourceAsStream("pics/spells/" + list.get(1).getNome().toLowerCase() + ".png")));
        }
    }
    
    public void preencheChampionsPlayer(Champion champ, ImageView img){
        img.setImage(new Image(getClass().getResourceAsStream("pics/champs/" + champ.getNome().toLowerCase() + ".png")));
    }
    public void preencheRunaPlayer(Runa runa, ImageView img){
        img.setImage(new Image(getClass().getResourceAsStream("pics/runes/" + runa.getNome().toLowerCase() + ".png")));
    }
        
    @FXML
    public void createSpellAction(MouseEvent event, int tab, int player) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLSpellSelection.fxml"));
        Parent root = loader.load();
        FXMLSpellSelectionController controller = loader.getController();
        controller.preencheGridSpells();
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
        if(tab == 0){
            this.limpaListaSpells(tab, player);
            if(player == 1){
                if(controller.getSpellsList()!=null && controller.getSpellsList().size()>0){
                    listSpellBlueTeamTop = controller.getSpellsList();
                    this.preencheSpellsPlayer(listSpellBlueTeamTop, imgSpell1BlueTeamTop, imgSpell2BlueTeamTop);
                }
            }
            if(player == 2){
                if(controller.getSpellsList()!=null && controller.getSpellsList().size()>0){
                    listSpellBlueTeamJng = controller.getSpellsList();
                    this.preencheSpellsPlayer(listSpellBlueTeamJng, imgSpell1BlueTeamJng, imgSpell2BlueTeamJng);
                }
            }
            if(player == 3){
                if(controller.getSpellsList()!=null && controller.getSpellsList().size()>0){
                    listSpellBlueTeamMid = controller.getSpellsList();
                    this.preencheSpellsPlayer(listSpellBlueTeamMid, imgSpell1BlueTeamMid, imgSpell2BlueTeamMid);
                }
            }
            if(player == 4){
                if(controller.getSpellsList()!=null && controller.getSpellsList().size()>0){
                    listSpellBlueTeamAdc = controller.getSpellsList();
                    this.preencheSpellsPlayer(listSpellBlueTeamAdc, imgSpell1BlueTeamAdc, imgSpell2BlueTeamAdc);
                }
            }
            if(player == 5){
                if(controller.getSpellsList()!=null && controller.getSpellsList().size()>0){
                    listSpellBlueTeamSup = controller.getSpellsList();
                    this.preencheSpellsPlayer(listSpellBlueTeamSup, imgSpell1BlueTeamSup, imgSpell2BlueTeamSup);
                }
            }
        }
        
        if(tab == 1){
            this.limpaListaSpells(tab, player);
            if(player == 1){
                if(controller.getSpellsList()!=null && controller.getSpellsList().size()>0){
                    listSpellRedTeamTop = controller.getSpellsList();
                    this.preencheSpellsPlayer(listSpellRedTeamTop, imgSpell1RedTeamTop, imgSpell2RedTeamTop);
                }
            }
            if(player == 2){
                if(controller.getSpellsList()!=null && controller.getSpellsList().size()>0){
                    listSpellRedTeamJng = controller.getSpellsList();
                    this.preencheSpellsPlayer(listSpellRedTeamJng, imgSpell1RedTeamJng, imgSpell2RedTeamJng);
                }
            }
            if(player == 3){
                if(controller.getSpellsList()!=null && controller.getSpellsList().size()>0){
                    listSpellRedTeamMid = controller.getSpellsList();
                    this.preencheSpellsPlayer(listSpellRedTeamMid, imgSpell1RedTeamMid, imgSpell2RedTeamMid);
                }
            }
            if(player == 4){
                if(controller.getSpellsList()!=null && controller.getSpellsList().size()>0){
                    listSpellRedTeamAdc = controller.getSpellsList();
                    this.preencheSpellsPlayer(listSpellRedTeamAdc, imgSpell1RedTeamAdc, imgSpell2RedTeamAdc);
                }
            }
            if(player == 5){
                if(controller.getSpellsList()!=null && controller.getSpellsList().size()>0){
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
        if(tab == 0){
            if(player == 1){
                if(!champsSelecionados.contains(controller.getChampionSelected())){
                    if(championBlueTeamTop == null){
                        championBlueTeamTop = controller.getChampionSelected();
                        champsSelecionados.add(controller.getChampionSelected());
                        this.preencheChampionsPlayer(controller.getChampionSelected(), imgChampBlueTeamTop);
                    }else{
                        champsSelecionados.remove(championBlueTeamTop);
                        championBlueTeamTop = controller.getChampionSelected();
                        champsSelecionados.add(controller.getChampionSelected());
                        this.preencheChampionsPlayer(controller.getChampionSelected(), imgChampBlueTeamTop);
                    }
                }else{
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText("Something went wrong.");
                    alert.setContentText("The champion " + controller.getChampionSelected() + " is already selected by another player.");
                    alert.showAndWait();
                }
            }
            if(player == 2){
                if(!champsSelecionados.contains(controller.getChampionSelected())){
                    if(championBlueTeamJng == null){
                        championBlueTeamJng = controller.getChampionSelected();
                        champsSelecionados.add(controller.getChampionSelected());
                        this.preencheChampionsPlayer(controller.getChampionSelected(), imgChampBlueTeamJng);
                    }else{
                        champsSelecionados.remove(championBlueTeamJng);
                        championBlueTeamJng = controller.getChampionSelected();
                        champsSelecionados.add(controller.getChampionSelected());
                        this.preencheChampionsPlayer(controller.getChampionSelected(), imgChampBlueTeamJng);
                    }
                }else{
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText("Something went wrong.");
                    alert.setContentText("The champion " + controller.getChampionSelected() + " is already selected by another player.");
                    alert.showAndWait();
                }
            }
            if(player == 3){
                if(!champsSelecionados.contains(controller.getChampionSelected())){
                    if(championBlueTeamMid == null){
                        championBlueTeamMid = controller.getChampionSelected();
                        champsSelecionados.add(controller.getChampionSelected());
                        this.preencheChampionsPlayer(controller.getChampionSelected(), imgChampBlueTeamMid);
                    }else{
                        champsSelecionados.remove(championBlueTeamMid);
                        championBlueTeamMid = controller.getChampionSelected();
                        champsSelecionados.add(controller.getChampionSelected());
                        this.preencheChampionsPlayer(controller.getChampionSelected(), imgChampBlueTeamMid);
                    }
                }else{
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText("Something went wrong.");
                    alert.setContentText("The champion " + controller.getChampionSelected() + " is already selected by another player.");
                    alert.showAndWait();
                }
            }
            if(player == 4){
                if(!champsSelecionados.contains(controller.getChampionSelected())){
                    if(championBlueTeamAdc == null){
                        championBlueTeamAdc = controller.getChampionSelected();
                        champsSelecionados.add(controller.getChampionSelected());
                        this.preencheChampionsPlayer(controller.getChampionSelected(), imgChampBlueTeamAdc);
                    }else{
                        champsSelecionados.remove(championBlueTeamAdc);
                        championBlueTeamAdc = controller.getChampionSelected();
                        champsSelecionados.add(controller.getChampionSelected());
                        this.preencheChampionsPlayer(controller.getChampionSelected(), imgChampBlueTeamAdc);
                    }
                }else{
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText("Something went wrong.");
                    alert.setContentText("The champion " + controller.getChampionSelected() + " is already selected by another player.");
                    alert.showAndWait();
                }
            }
            if(player == 5){
                if(!champsSelecionados.contains(controller.getChampionSelected())){
                    if(championBlueTeamSup == null){
                        championBlueTeamSup = controller.getChampionSelected();
                        champsSelecionados.add(controller.getChampionSelected());
                        this.preencheChampionsPlayer(controller.getChampionSelected(), imgChampBlueTeamSup);
                    }else{
                        champsSelecionados.remove(championBlueTeamSup);
                        championBlueTeamSup = controller.getChampionSelected();
                        champsSelecionados.add(controller.getChampionSelected());
                        this.preencheChampionsPlayer(controller.getChampionSelected(), imgChampBlueTeamSup);
                    }
                }else{
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText("Something went wrong.");
                    alert.setContentText("The champion " + controller.getChampionSelected() + " is already selected by another player.");
                    alert.showAndWait();
                }
            }
        }
        if(tab == 1){
            if(player == 1){
                if(!champsSelecionados.contains(controller.getChampionSelected())){
                    if(championRedTeamTop == null){
                        championRedTeamTop = controller.getChampionSelected();
                        champsSelecionados.add(controller.getChampionSelected());
                        this.preencheChampionsPlayer(controller.getChampionSelected(), imgChampRedTeamTop);
                    }else{
                        champsSelecionados.remove(championRedTeamTop);
                        championRedTeamTop = controller.getChampionSelected();
                        champsSelecionados.add(controller.getChampionSelected());
                        this.preencheChampionsPlayer(controller.getChampionSelected(), imgChampRedTeamTop);
                    }
                }else{
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText("Something went wrong.");
                    alert.setContentText("The champion " + controller.getChampionSelected() + " is already selected by another player.");
                    alert.showAndWait();
                }
            }
            if(player == 2){
                if(!champsSelecionados.contains(controller.getChampionSelected())){
                    if(championRedTeamJng == null){
                        championRedTeamJng = controller.getChampionSelected();
                        champsSelecionados.add(controller.getChampionSelected());
                        this.preencheChampionsPlayer(controller.getChampionSelected(), imgChampRedTeamJng);
                    }else{
                        champsSelecionados.remove(championRedTeamJng);
                        championRedTeamJng = controller.getChampionSelected();
                        champsSelecionados.add(controller.getChampionSelected());
                        this.preencheChampionsPlayer(controller.getChampionSelected(), imgChampRedTeamJng);
                    }
                }else{
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText("Something went wrong.");
                    alert.setContentText("The champion " + controller.getChampionSelected() + " is already selected by another player.");
                    alert.showAndWait();
                }
            }
            if(player == 3){
                if(!champsSelecionados.contains(controller.getChampionSelected())){
                    if(championRedTeamMid == null){
                        championRedTeamMid = controller.getChampionSelected();
                        champsSelecionados.add(controller.getChampionSelected());
                        this.preencheChampionsPlayer(controller.getChampionSelected(), imgChampRedTeamMid);
                    }else{
                        champsSelecionados.remove(championRedTeamMid);
                        championRedTeamMid = controller.getChampionSelected();
                        champsSelecionados.add(controller.getChampionSelected());
                        this.preencheChampionsPlayer(controller.getChampionSelected(), imgChampRedTeamMid);
                    }
                }else{
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText("Something went wrong.");
                    alert.setContentText("The champion " + controller.getChampionSelected() + " is already selected by another player.");
                    alert.showAndWait();
                }
            }
            if(player == 4){
                if(!champsSelecionados.contains(controller.getChampionSelected())){
                    if(championRedTeamAdc == null){
                        championRedTeamAdc = controller.getChampionSelected();
                        champsSelecionados.add(controller.getChampionSelected());
                        this.preencheChampionsPlayer(controller.getChampionSelected(), imgChampRedTeamAdc);
                    }else{
                        champsSelecionados.remove(championRedTeamAdc);
                        championRedTeamAdc = controller.getChampionSelected();
                        champsSelecionados.add(controller.getChampionSelected());
                        this.preencheChampionsPlayer(controller.getChampionSelected(), imgChampRedTeamAdc);
                    }
                }else{
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText("Something went wrong.");
                    alert.setContentText("The champion " + controller.getChampionSelected() + " is already selected by another player.");
                    alert.showAndWait();
                }
            }
            if(player == 5){
                if(!champsSelecionados.contains(controller.getChampionSelected())){
                    if(championRedTeamSup == null){
                        championRedTeamSup = controller.getChampionSelected();
                        champsSelecionados.add(controller.getChampionSelected());
                        this.preencheChampionsPlayer(controller.getChampionSelected(), imgChampRedTeamSup);
                    }else{
                        champsSelecionados.remove(championRedTeamSup);
                        championRedTeamSup = controller.getChampionSelected();
                        champsSelecionados.add(controller.getChampionSelected());
                        this.preencheChampionsPlayer(controller.getChampionSelected(), imgChampRedTeamSup);
                    }
                }else{
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText("Something went wrong.");
                    alert.setContentText("The champion " + controller.getChampionSelected() + " is already selected by another player.");
                    alert.showAndWait();
                }
            }
        }
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
        if(tab == 0){
            if(player == 1){
                runaBlueTeamTop = controller.getRunaSelected();
                this.preencheRunaPlayer(controller.getRunaSelected(), imgRunaBlueTeamTop);
            }
            if(player == 2){
                runaBlueTeamJng = controller.getRunaSelected();
                this.preencheRunaPlayer(controller.getRunaSelected(), imgRunaBlueTeamJng);
            }
            if(player == 3){
                runaBlueTeamMid = controller.getRunaSelected();
                this.preencheRunaPlayer(controller.getRunaSelected(), imgRunaBlueTeamMid);
            }
            if(player == 4){
                runaBlueTeamAdc = controller.getRunaSelected();
                this.preencheRunaPlayer(controller.getRunaSelected(), imgRunaBlueTeamAdc);
            }
            if(player == 5){
                runaBlueTeamSup = controller.getRunaSelected();
                this.preencheRunaPlayer(controller.getRunaSelected(), imgRunaBlueTeamSup);
            }
        }
        if(tab == 1){
            if(player == 1){
                runaRedTeamTop = controller.getRunaSelected();
                this.preencheRunaPlayer(controller.getRunaSelected(), imgRunaRedTeamTop);
            }
            if(player == 2){
                runaRedTeamJng = controller.getRunaSelected();
                this.preencheRunaPlayer(controller.getRunaSelected(), imgRunaRedTeamJng);
            }
            if(player == 3){
                runaRedTeamMid = controller.getRunaSelected();
                this.preencheRunaPlayer(controller.getRunaSelected(), imgRunaRedTeamMid);
            }
            if(player == 4){
                runaRedTeamAdc = controller.getRunaSelected();
                this.preencheRunaPlayer(controller.getRunaSelected(), imgRunaRedTeamAdc);
            }
            if(player == 5){
                runaRedTeamSup = controller.getRunaSelected();
                this.preencheRunaPlayer(controller.getRunaSelected(), imgRunaRedTeamSup);
            }
        }
        
    }
    
    public void prepareActionsGrid(){
        //Tab Blue team selected int tab = 0, Tab Red team selected int tab = 1
        for(Node node: gridPaneBlueTeam.getChildren()){
            System.out.println(GridPane.getColumnIndex(node));
            if(GridPane.getColumnIndex(node)!=null && GridPane.getRowIndex(node)!=null && GridPane.getColumnIndex(node)==4 && GridPane.getRowIndex(node)>0){
                node.setCursor(Cursor.HAND);
                node.setOnMouseClicked((event) -> {
                    try {
                        if(tabBlueTeam.isSelected()){
                            if(GridPane.getRowIndex(node)==1){
                                this.createItemAction(event, 0, 1);
                            }
                            if(GridPane.getRowIndex(node)==2){
                                this.createItemAction(event,0, 2);
                            }
                            if(GridPane.getRowIndex(node)==3){
                                this.createItemAction(event,0, 3);
                            }
                            if(GridPane.getRowIndex(node)==4){
                                this.createItemAction(event,0, 4);
                            }
                            if(GridPane.getRowIndex(node)==5){
                                this.createItemAction(event,0, 5);
                            }
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLMatchGameTournamentController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            }
            if(GridPane.getColumnIndex(node)!=null && GridPane.getRowIndex(node)!=null && GridPane.getColumnIndex(node)==6 && GridPane.getRowIndex(node)>0){
                node.setCursor(Cursor.HAND);
                node.setOnMouseClicked((event) -> {
                    try {
                        if(tabBlueTeam.isSelected()){
                            if(GridPane.getRowIndex(node)==1){
                                this.createSpellAction(event, 0, 1);
                            }
                            if(GridPane.getRowIndex(node)==2){
                                this.createSpellAction(event,0, 2);
                            }
                            if(GridPane.getRowIndex(node)==3){
                                this.createSpellAction(event,0, 3);
                            }
                            if(GridPane.getRowIndex(node)==4){
                                this.createSpellAction(event,0, 4);
                            }
                            if(GridPane.getRowIndex(node)==5){
                                this.createSpellAction(event,0, 5);
                            }
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLMatchGameTournamentController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            }
            if(GridPane.getColumnIndex(node)!=null && GridPane.getRowIndex(node)!=null && GridPane.getColumnIndex(node)==7 && GridPane.getRowIndex(node)>0){
                node.setCursor(Cursor.HAND);
                node.setOnMouseClicked((event) -> {
                    try {
                        if(tabBlueTeam.isSelected()){
                            if(GridPane.getRowIndex(node)==1){
                                this.createChampionAction(event, 0, 1);
                            }
                            if(GridPane.getRowIndex(node)==2){
                                this.createChampionAction(event,0, 2);
                            }
                            if(GridPane.getRowIndex(node)==3){
                                this.createChampionAction(event,0, 3);
                            }
                            if(GridPane.getRowIndex(node)==4){
                                this.createChampionAction(event,0, 4);
                            }
                            if(GridPane.getRowIndex(node)==5){
                                this.createChampionAction(event,0, 5);
                            }
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLMatchGameTournamentController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            }
            if(GridPane.getColumnIndex(node)!=null && GridPane.getRowIndex(node)!=null && GridPane.getColumnIndex(node)==8 && GridPane.getRowIndex(node)>0){
                node.setCursor(Cursor.HAND);
                node.setOnMouseClicked((event) -> {
                    try {
                        if(tabBlueTeam.isSelected()){
                            if(GridPane.getRowIndex(node)==1){
                                this.createRunaAction(event, 0, 1);
                            }
                            if(GridPane.getRowIndex(node)==2){
                                this.createRunaAction(event,0, 2);
                            }
                            if(GridPane.getRowIndex(node)==3){
                                this.createRunaAction(event,0, 3);
                            }
                            if(GridPane.getRowIndex(node)==4){
                                this.createRunaAction(event,0, 4);
                            }
                            if(GridPane.getRowIndex(node)==5){
                                this.createRunaAction(event,0, 5);
                            }
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLMatchGameTournamentController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            }
        }
        
        for(Node node: gridPaneRedTeam.getChildren()){
            System.out.println(GridPane.getColumnIndex(node));
            if(GridPane.getColumnIndex(node)!=null && GridPane.getRowIndex(node)!=null && GridPane.getColumnIndex(node)==4 && GridPane.getRowIndex(node)>0){
                node.setCursor(Cursor.HAND);
                node.setOnMouseClicked((event) -> {
                    try {
                        if(tabRedTeam.isSelected()){
                            if(GridPane.getRowIndex(node)==1){
                                this.createItemAction(event,1, 1);
                            }
                            if(GridPane.getRowIndex(node)==2){
                                this.createItemAction(event,1, 2);
                            }
                            if(GridPane.getRowIndex(node)==3){
                                this.createItemAction(event,1, 3);
                            }
                            if(GridPane.getRowIndex(node)==4){
                                this.createItemAction(event,1, 4);
                            }
                            if(GridPane.getRowIndex(node)==5){
                                this.createItemAction(event,1, 5);
                            }
                        }
                        
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLMatchGameTournamentController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            }
            if(GridPane.getColumnIndex(node)!=null && GridPane.getRowIndex(node)!=null && GridPane.getColumnIndex(node)==6 && GridPane.getRowIndex(node)>0){
                node.setCursor(Cursor.HAND);
                node.setOnMouseClicked((event) -> {
                    try {
                        if(tabRedTeam.isSelected()){
                            if(GridPane.getRowIndex(node)==1){
                                this.createSpellAction(event,1, 1);
                            }
                            if(GridPane.getRowIndex(node)==2){
                                this.createSpellAction(event,1, 2);
                            }
                            if(GridPane.getRowIndex(node)==3){
                                this.createSpellAction(event,1, 3);
                            }
                            if(GridPane.getRowIndex(node)==4){
                                this.createSpellAction(event,1, 4);
                            }
                            if(GridPane.getRowIndex(node)==5){
                                this.createSpellAction(event,1, 5);
                            }
                        }
                        
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLMatchGameTournamentController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            }
            if(GridPane.getColumnIndex(node)!=null && GridPane.getRowIndex(node)!=null && GridPane.getColumnIndex(node)==7 && GridPane.getRowIndex(node)>0){
                node.setCursor(Cursor.HAND);
                node.setOnMouseClicked((event) -> {
                    try {
                        if(tabRedTeam.isSelected()){
                            if(GridPane.getRowIndex(node)==1){
                                this.createChampionAction(event,1, 1);
                            }
                            if(GridPane.getRowIndex(node)==2){
                                this.createChampionAction(event,1, 2);
                            }
                            if(GridPane.getRowIndex(node)==3){
                                this.createChampionAction(event,1, 3);
                            }
                            if(GridPane.getRowIndex(node)==4){
                                this.createChampionAction(event,1, 4);
                            }
                            if(GridPane.getRowIndex(node)==5){
                                this.createChampionAction(event,1, 5);
                            }
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLMatchGameTournamentController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            }
            if(GridPane.getColumnIndex(node)!=null && GridPane.getRowIndex(node)!=null && GridPane.getColumnIndex(node)==8 && GridPane.getRowIndex(node)>0){
                node.setCursor(Cursor.HAND);
                node.setOnMouseClicked((event) -> {
                    try {
                        if(tabRedTeam.isSelected()){
                            if(GridPane.getRowIndex(node)==1){
                                this.createRunaAction(event,1, 1);
                            }
                            if(GridPane.getRowIndex(node)==2){
                                this.createRunaAction(event,1, 2);
                            }
                            if(GridPane.getRowIndex(node)==3){
                                this.createRunaAction(event,1, 3);
                            }
                            if(GridPane.getRowIndex(node)==4){
                                this.createRunaAction(event,1, 4);
                            }
                            if(GridPane.getRowIndex(node)==5){
                                this.createRunaAction(event,1, 5);
                            }
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLMatchGameTournamentController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            }
        }
    }
    
    public void limpaListaSpells(int tab, int player){
        if(tab == 0){
            if(player == 1){
                imgSpell1BlueTeamTop.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgSpell2BlueTeamTop.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
            }
            if(player == 2){
                imgSpell1BlueTeamJng.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgSpell2BlueTeamJng.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
            }
            if(player == 3){
                imgSpell1BlueTeamMid.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgSpell2BlueTeamMid.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
            }
            if(player == 4){
                imgSpell1BlueTeamAdc.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgSpell2BlueTeamAdc.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
            }
            if(player == 5){
                imgSpell1BlueTeamAdc.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgSpell2BlueTeamAdc.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
            }
        }
        if(tab == 1){
            if(player == 1){
                imgSpell1RedTeamTop.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgSpell2RedTeamTop.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
            }
            if(player == 2){
                imgSpell1RedTeamJng.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgSpell2RedTeamJng.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
            }
            if(player == 3){
                imgSpell1RedTeamMid.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgSpell2RedTeamMid.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
            }
            if(player == 4){
                imgSpell1RedTeamAdc.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgSpell2RedTeamAdc.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
            }
            if(player == 5){
                imgSpell1RedTeamAdc.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgSpell2RedTeamAdc.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
            }
        }
    }
    
    public void limpaListaBuild(int tab, int player){
        if(tab == 0){
            if(player == 1){
                imgItem1BlueTeamTop.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem2BlueTeamTop.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem3BlueTeamTop.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem4BlueTeamTop.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem5BlueTeamTop.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem6BlueTeamTop.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
            }
            if(player == 2){
                imgItem1BlueTeamJng.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem2BlueTeamJng.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem3BlueTeamJng.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem4BlueTeamJng.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem5BlueTeamJng.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem6BlueTeamJng.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
            }
            if(player == 3){
                imgItem1BlueTeamMid.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem2BlueTeamMid.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem3BlueTeamMid.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem4BlueTeamMid.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem5BlueTeamMid.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem6BlueTeamMid.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
            }
            if(player == 4){
                imgItem1BlueTeamAdc.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem2BlueTeamAdc.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem3BlueTeamAdc.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem4BlueTeamAdc.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem5BlueTeamAdc.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem6BlueTeamAdc.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
            }
            if(player == 5){
                imgItem1BlueTeamSup.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem2BlueTeamSup.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem3BlueTeamSup.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem4BlueTeamSup.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem5BlueTeamSup.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem6BlueTeamSup.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
            }
        }
        if(tab == 1){
            if(player == 1){
                imgItem1RedTeamTop.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem2RedTeamTop.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem3RedTeamTop.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem4RedTeamTop.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem5RedTeamTop.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem6RedTeamTop.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
            }
            if(player == 2){
                imgItem1RedTeamJng.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem2RedTeamJng.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem3RedTeamJng.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem4RedTeamJng.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem5RedTeamJng.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem6RedTeamJng.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
            }
            if(player == 3){
                imgItem1RedTeamMid.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem2RedTeamMid.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem3RedTeamMid.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem4RedTeamMid.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem5RedTeamMid.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem6RedTeamMid.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
            }
            if(player == 4){
                imgItem1RedTeamAdc.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem2RedTeamAdc.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem3RedTeamAdc.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem4RedTeamAdc.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem5RedTeamAdc.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem6RedTeamAdc.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
            }
            if(player == 5){
                imgItem1RedTeamSup.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem2RedTeamSup.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem3RedTeamSup.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem4RedTeamSup.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem5RedTeamSup.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
                imgItem6RedTeamSup.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
            }
        }
    }
    
    public void preencheTabsLabelsComboboxesEquipas(Encontro en){
        tabBlueTeam.setText(en.getEquipaByEquipa1().getNome());
        tabRedTeam.setText(en.getEquipaByEquipa2().getNome());
        this.setTeamMemberNames(en.getEquipaByEquipa1(), en.getEquipaByEquipa2());
        this.setComboBoxes(en.getEquipaByEquipa1(), en.getEquipaByEquipa2());
        
    }
    
    public void setTeamMemberNames(Equipa eq1, Equipa eq2){
        for(Membroequipa m: (Collection<Membroequipa>) eq1.getMembroequipas()){
            if(m.getCargo().equals("player") && m.getPosicao().toString().equals("TOP")){
                lblBlueTeamTop.setText(m.getNome());
            }
            if(m.getCargo().equals("player") && m.getPosicao().toString().equals("JNG")){
                lblBlueTeamJng.setText(m.getNome());
            }
            if(m.getCargo().equals("player") && m.getPosicao().toString().equals("MID")){
                lblBlueTeamMid.setText(m.getNome());
            }
            if(m.getCargo().equals("player") && m.getPosicao().toString().equals("ADC")){
                lblBlueTeamAdc.setText(m.getNome());
            }
            if(m.getCargo().equals("player") && m.getPosicao().toString().equals("SUP")){
                lblBlueTeamSup.setText(m.getNome());
            }
        }
        
        for(Membroequipa m: (Collection<Membroequipa>) eq2.getMembroequipas()){
            if(m.getCargo().equals("player") && m.getPosicao().toString().equals("TOP")){
                lblRedTeamTop.setText(m.getNome());
            }
            if(m.getCargo().equals("player") && m.getPosicao().toString().equals("JNG")){
                lblRedTeamJng.setText(m.getNome());
            }
            if(m.getCargo().equals("player") && m.getPosicao().toString().equals("MID")){
                lblRedTeamMid.setText(m.getNome());
            }
            if(m.getCargo().equals("player") && m.getPosicao().toString().equals("ADC")){
                lblRedTeamAdc.setText(m.getNome());
            }
            if(m.getCargo().equals("player") && m.getPosicao().toString().equals("SUP")){
                lblRedTeamSup.setText(m.getNome());
            }
        }
        
    }
    
    public void setComboBoxes(Equipa eq1, Equipa eq2){
        cbWinner.getItems().addAll(eq1.getNome(), eq2.getNome());
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
    
}
