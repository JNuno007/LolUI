/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lolui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import lolbll.SpellServices;
import loldal.model.Spell;

/**
 * FXML Controller class
 *
 * @author joaoc
 */
public class FXMLSpellSelectionController implements Initializable {

    @FXML
    private BorderPane mainPane;

    @FXML
    private GridPane gridPane;

    @FXML
    private ImageView imgBack;

    @FXML
    private TextField searchBar;
    
    //Items selecionados
    @FXML private ImageView imgSpell1;
    @FXML private ImageView imgSpell2;
    
    private Spell spell1;
    private Spell spell2;

    private ImageView spellSelected;
    
    private List<Spell> spells;

    private List<Spell> listaFiltrada;
    
    private List<Spell> spellsSelecionados;

    private Spell spell;

    private int pos;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mainPane.setCenter(gridPane);
        this.pesquisarNaLista();
        spellsSelecionados = new ArrayList<>();
    }    
    
    public void preencheGridSpells() {
        spells = SpellServices.getListaSpells();

        int column = 0;
        int row = 0;

        for (Spell s : spells) {

            if (FXMLSpellSelectionController.class.getResourceAsStream("pics/spells/" + s.getNome().toLowerCase() + ".png") != null) {
                ImageView image = new ImageView();
                image.setImage(new Image(LolUI.class.getResourceAsStream("pics/spells/" + s.getNome().toLowerCase() + ".png")));

                image.setFitHeight(50);
                image.setFitWidth(75);

                gridPane.add(image, column, row);
                column++;

                if (column == 6) {
                    column = 0;
                    row++;
                }
            }
        }
        listaFiltrada = spells;
        this.selecionaSpell();
    }
    
    public void preencheSpellSelecionadosInicio(List<Spell> list){
        if(list.size()>0){
            spell1 = list.get(0);
            imgSpell1.setImage(new Image(getClass().getResourceAsStream("pics/spells/" + list.get(0).getNome().toLowerCase() + ".png")));
        }
        
        if(list.size()>1){
            spell2 = list.get(1);
            imgSpell2.setImage(new Image(getClass().getResourceAsStream("pics/spells/" + list.get(1).getNome().toLowerCase() + ".png")));
        }
    }
    
    public void pesquisarNaLista() {
        searchBar.setOnKeyReleased((event) -> {
            List<Spell> temp = new ArrayList<>();
            String texto = searchBar.getText();
            if (texto.isEmpty()) {
                this.limpaGrid();
                this.preencheGridSpells();
                System.out.println(listaFiltrada.size());
            } else {
                this.limpaGrid();
                for (Spell s : spells) {
                    if (s.getNome().toLowerCase().contains(texto.toLowerCase())) {
                        temp.add(s);
                    }
                }
                this.preencheGridSpellsFiltrada(temp);
            }
        });
    }
    
    public void preencheGridSpellsFiltrada(List<Spell> list) {
        int column = 0;
        int row = 0;

        this.limpaGrid();

        for (Spell s : list) {

            if (FXMLSpellSelectionController.class.getResourceAsStream("pics/spells/" + s.getNome().toLowerCase() + ".png") != null) {
                ImageView image = new ImageView();
                image.setImage(new Image(LolUI.class.getResourceAsStream("pics/spells/" + s.getNome().toLowerCase() + ".png")));

                image.setFitHeight(50);
                image.setFitWidth(75);

                gridPane.add(image, column, row);
                column++;

                if (column == 6) {
                    column = 0;
                    row++;
                }
            }
        }
        listaFiltrada = list;
        this.selecionaSpell();
    }
    
    public void limpaGrid() {
        gridPane.getChildren().removeIf(node -> Objects.equals(GridPane.getRowIndex(node), GridPane.getRowIndex(node)));
    }

    @FXML
    public void closePopUp() {
        Stage stage = (Stage) this.imgBack.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    public void closePopUpAndClear() {
        spellsSelecionados = null;
        Stage stage = (Stage) this.imgBack.getScene().getWindow();
        stage.close();
    }
    
    public void selecionaSpell() {
        for (Node node : gridPane.getChildren()) {
            node.setCursor(Cursor.HAND);
            node.setOnMouseClicked((event) -> {
                spellSelected = (ImageView) node;
                int column = GridPane.getColumnIndex(node);
                int row = GridPane.getRowIndex(node) + 1;
                if (row > 1) {
                    pos = 6 * (row - 1) + column;
                    spell = listaFiltrada.get(pos);
                    //this.closePopUp();
                    this.preencheSpellsSelecionados(spell, spellSelected);
                } else {
                    pos = column;
                    spell = listaFiltrada.get(pos);
                    //this.closePopUp();
                    this.preencheSpellsSelecionados(spell, spellSelected);
                }
            });
            node.setOnMouseEntered((event) -> {
                int column = GridPane.getColumnIndex(node);
                int row = GridPane.getRowIndex(node) + 1;
                if (row > 1) {
                    pos = 6 * (row - 1) + column;
                } else {
                    pos = column;
                }
                Tooltip.install(node, new Tooltip(listaFiltrada.get(pos).getNome()));
            });
        }
    }
    
    public void preencheSpellsSelecionados(Spell spell, ImageView spellSelected){
        if(spell1 == null){
            spell1 = spell;
            imgSpell1.setImage(spellSelected.getImage());
        }else{
            if(spell2 == null){
                spell2 = spell;
                imgSpell2.setImage(spellSelected.getImage());
            }
        }
    }
    
    public void eliminaItemSelecionado(MouseEvent event){
        if(event.getSource() == imgSpell1){
            spell1 = null;
            imgSpell1.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
        }
        if(event.getSource() == imgSpell2){
            spell2 = null;
            imgSpell2.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
        }
    }
    
    public void preencheLista(){
        if(spell1 !=null && spell1!=spell2){
            spellsSelecionados.add(spell1);
        }
        
        if(spell2 != null && spell1!=spell2){
            spellsSelecionados.add(spell2);
        }
    }
    
    public void confirmClick(){
        if(spell1 == spell2 && spell1!=null && spell2!=null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Something went wrong.");
            alert.setContentText("Please choose 2 different spells");
            alert.showAndWait();
        }else{
            if((spell1!=null && spell2==null) || (spell1==null && spell2!=null)){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("Something went wrong.");
                alert.setContentText("Please choose 2 spells");
                alert.showAndWait();
            }else{
                spellsSelecionados.clear();
                this.preencheLista();
                this.closePopUp();
            }
        }
    }
    
    public List<Spell> getSpellsList(){
        return spellsSelecionados;
    }
    
    
}
