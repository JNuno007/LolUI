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
import lolbll.ChampionServices;
import loldal.model.Champion;

/**
 * FXML Controller class
 *
 * @author joaoc
 */
public class FXMLChampionSelectionController implements Initializable {

    @FXML
    private BorderPane mainPane;

    @FXML
    private GridPane gridPane;

    @FXML
    private ImageView imgBack;

    @FXML
    private TextField searchBar;

    private ImageView championSelected;
    
    private List<Champion> champions;

    private List<Champion> listaFiltrada;

    private Champion champion;

    private int pos;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mainPane.setCenter(gridPane);
        this.pesquisarNaLista();
    }

    public void preencheGridChampions() {
        champions = ChampionServices.getListChampions();

        int column = 0;
        int row = 0;

        for (Champion c : champions) {

            if (FXMLChampionSelectionController.class.getResourceAsStream("pics/champs/" + c.getNome().toLowerCase() + ".png") != null) {
                ImageView image = new ImageView();
                image.setImage(new Image(LolUI.class.getResourceAsStream("pics/champs/" + c.getNome().toLowerCase() + ".png")));

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
        listaFiltrada = champions;
        this.selecionaChampion();
    }
    
    public void pesquisarNaLista() {
        searchBar.setOnKeyReleased((event) -> {
            List<Champion> temp = new ArrayList<>();
            String texto = searchBar.getText();
            if (texto.isEmpty()) {
                this.limpaGrid();
                this.preencheGridChampions();
                System.out.println(listaFiltrada.size());
            } else {
                this.limpaGrid();
                for (Champion c : champions) {
                    if (c.getNome().toLowerCase().contains(texto.toLowerCase())) {
                        temp.add(c);
                    }
                }
                this.preencheGridChampionsFiltrada(temp);
            }
        });
    }
    
    public void preencheGridChampionsFiltrada(List<Champion> list) {
        int column = 0;
        int row = 0;

        this.limpaGrid();

        for (Champion c : list) {

            if (FXMLChampionSelectionController.class.getResourceAsStream("pics/champs/" + c.getNome().toLowerCase() + ".png") != null) {
                ImageView image = new ImageView();
                image.setImage(new Image(LolUI.class.getResourceAsStream("pics/champs/" + c.getNome().toLowerCase() + ".png")));

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
        this.selecionaChampion();
    }
    
    public void limpaGrid() {
        gridPane.getChildren().removeIf(node -> Objects.equals(GridPane.getRowIndex(node), GridPane.getRowIndex(node)));
    }

    @FXML
    public void closePopUp() {
        Stage stage = (Stage) this.imgBack.getScene().getWindow();
        stage.close();
    }
    
    public void selecionaChampion() {
        for (Node node : gridPane.getChildren()) {
            node.setCursor(Cursor.HAND);
            node.setOnMouseClicked((event) -> {
                championSelected = (ImageView) node;
                int column = GridPane.getColumnIndex(node);
                int row = GridPane.getRowIndex(node) + 1;
                if (row > 1) {
                    pos = 6 * (row - 1) + column;
                    champion = listaFiltrada.get(pos);
                    this.closePopUp();
                } else {
                    pos = column;
                    champion = listaFiltrada.get(pos);
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

    public Image getChampionImageSelected() {
        return championSelected.getImage();
    }

    public Champion getChampionSelected() {
        return champion;
    }
}
