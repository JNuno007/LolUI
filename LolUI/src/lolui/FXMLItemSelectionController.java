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
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import lolbll.ItemServices;
import loldal.model.Item;

/**
 * FXML Controller class
 *
 * @author joaoc
 */
public class FXMLItemSelectionController implements Initializable {

    @FXML
    private BorderPane mainPane;

    @FXML
    private GridPane gridPane;

    @FXML
    private ImageView imgBack;

    @FXML
    private TextField searchBar;

    private ImageView itemSelected;
    
    private List<Item> items;

    private List<Item> listaFiltrada;

    private Item item;

    private int pos;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mainPane.setCenter(gridPane);
        this.pesquisarNaLista();
        
    }   
    
    @FXML
    public void closePopUp() {
        Stage stage = (Stage) this.imgBack.getScene().getWindow();
        stage.close();
    }
    
     public void preencheGridItems() {
        items = ItemServices.getListaItems();

        int column = 0;
        int row = 0;

        for (Item i : items) {

            if (FXMLItemSelectionController.class.getResourceAsStream("pics/items/" + i.getNome().toLowerCase() + ".png") != null) {
                ImageView image = new ImageView();
                image.setImage(new Image(LolUI.class.getResourceAsStream("pics/items/" + i.getNome().toLowerCase() + ".png")));

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
        listaFiltrada = items;
        this.selecionaItem();
    }
     
     public void pesquisarNaLista() {
        searchBar.setOnKeyReleased((event) -> {
            List<Item> temp = new ArrayList<>();
            String texto = searchBar.getText();
            if (texto.isEmpty()) {
                this.limpaGrid();
                this.preencheGridItems();
                System.out.println(listaFiltrada.size());
            } else {
                this.limpaGrid();
                for (Item i : items) {
                    if (i.getNome().toLowerCase().contains(texto.toLowerCase())) {
                        temp.add(i);
                    }
                }
                this.preencheGridPaisesFiltrada(temp);
            }
        });
    }
     
     public void preencheGridPaisesFiltrada(List<Item> list) {
        int column = 0;
        int row = 0;

        this.limpaGrid();

        for (Item i : list) {

            if (FXMLItemSelectionController.class.getResourceAsStream("pics/items/" + i.getNome().toLowerCase() + ".png") != null) {
                ImageView image = new ImageView();
                image.setImage(new Image(LolUI.class.getResourceAsStream("pics/items/" + i.getNome().toLowerCase() + ".png")));

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
        this.selecionaItem();
    }
     
     public void limpaGrid() {
        gridPane.getChildren().removeIf(node -> Objects.equals(GridPane.getRowIndex(node), GridPane.getRowIndex(node)));
    }
     
     public void selecionaItem() {
        for (Node node : gridPane.getChildren()) {
            node.setCursor(Cursor.HAND);
            node.setOnMouseClicked((event) -> {
                itemSelected = (ImageView) node;
                int column = GridPane.getColumnIndex(node);
                int row = GridPane.getRowIndex(node) + 1;
                if (row > 1) {
                    pos = 6 * (row - 1) + column;
                    item = listaFiltrada.get(pos);
                    this.closePopUp();
                } else {
                    pos = column;
                    item = listaFiltrada.get(pos);
                    this.closePopUp();
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
     
    public Image getItemImageSelected() {
        return itemSelected.getImage();
    }

    public Item getItemSelected() {
        return item;
    }
    
}
