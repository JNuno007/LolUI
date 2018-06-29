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
import lolbll.RunesServices;
import loldal.model.Runa;

/**
 * FXML Controller class
 *
 * @author joaoc
 */
public class FXMLRunaSelectionController implements Initializable {

    @FXML
    private BorderPane mainPane;

    @FXML
    private GridPane gridPane;

    @FXML
    private ImageView imgBack;

    @FXML
    private TextField searchBar;

    private ImageView runaSelected;
    
    private List<Runa> runas;

    private List<Runa> listaFiltrada;

    private Runa runa;

    private int pos;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mainPane.setCenter(gridPane);
        this.pesquisarNaLista();
    }    
    
     public void preencheGridRunas() {
        runas = RunesServices.getListRuna();

        int column = 0;
        int row = 0;

        for (Runa r : runas) {

            if (FXMLRunaSelectionController.class.getResourceAsStream("pics/runes/" + r.getNome().toLowerCase() + ".png") != null) {
                ImageView image = new ImageView();
                image.setImage(new Image(LolUI.class.getResourceAsStream("pics/runes/" + r.getNome().toLowerCase() + ".png")));

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
        listaFiltrada = runas;
        this.selecionaRuna();
    }
     
     public void pesquisarNaLista() {
        searchBar.setOnKeyReleased((event) -> {
            List<Runa> temp = new ArrayList<>();
            String texto = searchBar.getText();
            if (texto.isEmpty()) {
                this.limpaGrid();
                this.preencheGridRunas();
                System.out.println(listaFiltrada.size());
            } else {
                this.limpaGrid();
                for (Runa r : runas) {
                    if (r.getNome().toLowerCase().contains(texto.toLowerCase())) {
                        temp.add(r);
                    }
                }
                this.preencheGridRunasFiltrada(temp);
            }
        });
    }
     
     public void preencheGridRunasFiltrada(List<Runa> list) {
        int column = 0;
        int row = 0;

        this.limpaGrid();

        for (Runa r : list) {

            if (FXMLRunaSelectionController.class.getResourceAsStream("pics/runes/" + r.getNome().toLowerCase() + ".png") != null) {
                ImageView image = new ImageView();
                image.setImage(new Image(LolUI.class.getResourceAsStream("pics/runes/" + r.getNome().toLowerCase() + ".png")));

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
        this.selecionaRuna();
    }

    public void limpaGrid() {
        gridPane.getChildren().removeIf(node -> Objects.equals(GridPane.getRowIndex(node), GridPane.getRowIndex(node)));
    }

    @FXML
    public void closePopUp() {
        Stage stage = (Stage) this.imgBack.getScene().getWindow();
        stage.close();
    }
    
    public void selecionaRuna() {
        for (Node node : gridPane.getChildren()) {
            node.setCursor(Cursor.HAND);
            node.setOnMouseClicked((event) -> {
                runaSelected = (ImageView) node;
                int column = GridPane.getColumnIndex(node);
                int row = GridPane.getRowIndex(node) + 1;
                if (row > 1) {
                    pos = 6 * (row - 1) + column;
                    runa = listaFiltrada.get(pos);
                    this.closePopUp();
                } else {
                    pos = column;
                    runa = listaFiltrada.get(pos);
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

    public Image getRunaImageSelected() {
        return runaSelected.getImage();
    }

    public Runa getRunaSelected() {
        return runa;
    }
    
}
