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
import javafx.scene.input.MouseEvent;
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

    //Items selecionados
    @FXML
    private ImageView imgItem1;
    @FXML
    private ImageView imgItem2;
    @FXML
    private ImageView imgItem3;
    @FXML
    private ImageView imgItem4;
    @FXML
    private ImageView imgItem5;
    @FXML
    private ImageView imgItem6;

    private Item item1;
    private Item item2;
    private Item item3;
    private Item item4;
    private Item item5;
    private Item item6;

    private ImageView itemSelected;

    private List<Item> items;

    private List<Item> listaFiltrada;

    private Item item;

    private int pos;

    private List<Item> itemsSelecionados;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mainPane.setCenter(gridPane);
        this.pesquisarNaLista();
        //itemsSelecionados = new ArrayList<>();
        itemsSelecionados = new ArrayList<>();
    }

    @FXML
    public void closePopUp() {
        Stage stage = (Stage) this.imgBack.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void closePopUpAndClear() {
        itemsSelecionados = null;
        this.closePopUp();
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
                    this.preencheItemsSelecionados(item, itemSelected);
                } else {
                    pos = column;
                    item = listaFiltrada.get(pos);
                    this.preencheItemsSelecionados(item, itemSelected);
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

    public void preencheItemSelecionadosInicio(List<Item> list) {

        if (list.size()>0) {
            item1 = list.get(0);
            imgItem1.setImage(new Image(getClass().getResourceAsStream("pics/items/" + list.get(0).getNome().toLowerCase() + ".png")));
        }
        if (list.size()>1) {
            item2 = list.get(1);
            imgItem2.setImage(new Image(getClass().getResourceAsStream("pics/items/" + list.get(1).getNome().toLowerCase() + ".png")));
        }
        if (list.size()>2) {
            item3 = list.get(2);
            imgItem3.setImage(new Image(getClass().getResourceAsStream("pics/items/" + list.get(2).getNome().toLowerCase() + ".png")));

        }
        if (list.size()>3) {
            item4 = list.get(3);
            imgItem4.setImage(new Image(getClass().getResourceAsStream("pics/items/" + list.get(3).getNome().toLowerCase() + ".png")));
        }
        if (list.size()>4) {
            item5 = list.get(4);
            imgItem5.setImage(new Image(getClass().getResourceAsStream("pics/items/" + list.get(4).getNome().toLowerCase() + ".png")));
        }
        if (list.size()>5) {
            item6 = list.get(5);
            imgItem6.setImage(new Image(getClass().getResourceAsStream("pics/items/" + list.get(5).getNome().toLowerCase() + ".png")));
        }

//this.preencheLista();
    }

    public void preencheItemsSelecionados(Item item, ImageView itemSelected) {

        if (item1 == null) {
            item1 = item;
            imgItem1.setImage(itemSelected.getImage());
        } else {
            if (item2 == null) {
                item2 = item;
                imgItem2.setImage(itemSelected.getImage());
            } else {
                if (item3 == null) {
                    item3 = item;
                    imgItem3.setImage(itemSelected.getImage());
                } else {
                    if (item4 == null) {
                        item4 = item;
                        imgItem4.setImage(itemSelected.getImage());
                    } else {
                        if (item5 == null) {
                            item5 = item;
                            imgItem5.setImage(itemSelected.getImage());
                        } else {
                            if (item6 == null) {
                                item6 = item;
                                imgItem6.setImage(itemSelected.getImage());
                            }
                        }
                    }
                }
            }
        }
    }

    public void eliminaItemListaTemp(MouseEvent event) {
        if (event.getSource() == imgItem1) {
            item1 = null;
            imgItem1.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
            System.out.println(item1);
        }
        if (event.getSource() == imgItem2) {
            item2 = null;
            imgItem2.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
            System.out.println(item1);
        }
        if (event.getSource() == imgItem3) {
            item3 = null;
            imgItem3.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
            System.out.println(item1);
        }
        if (event.getSource() == imgItem4) {
            item4 = null;
            imgItem4.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
            System.out.println(item1);
        }
        if (event.getSource() == imgItem5) {
            item5 = null;
            imgItem5.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
            System.out.println(item1);
        }
        if (event.getSource() == imgItem6) {
            item6 = null;
            imgItem6.setImage(new Image(getClass().getResourceAsStream("pics/item.png")));
            System.out.println(item1);
        }

    }

    public void preencheLista() {
        if (item1 != null) {
            itemsSelecionados.add(item1);
        }
        if (item2 != null) {
            itemsSelecionados.add(item2);
        }
        if (item3 != null) {
            itemsSelecionados.add(item3);
        }
        if (item4 != null) {
            itemsSelecionados.add(item4);
        }
        if (item5 != null) {
            itemsSelecionados.add(item5);
        }
        if (item6 != null) {
            itemsSelecionados.add(item6);
        }
        
        if(item1 == null && item2 == null && item3 == null && item4 == null && item5 == null && item6 == null){
            itemsSelecionados.clear();
        }
    }

    public void confirmClick() {
        this.preencheLista();
        this.closePopUp();
    }

    public List<Item> getItemList() {
        return itemsSelecionados;
    }

    public void setItemList(List<Item> items) {
        itemsSelecionados = items;
    }

}
