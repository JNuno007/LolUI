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
import lolbll.RegiaoServices;
import loldal.model.Regiao;

/**
 * FXML Controller class
 *
 * @author joaoc
 */
public class FXMLRegionSelectionController implements Initializable {

    
    @FXML private ImageView imgBack;
    
    @FXML
    private BorderPane mainPane;

    @FXML
    private GridPane gridPane;

    @FXML
    private TextField searchBar;

    private ImageView regionSelected;

    private List<Regiao> regioes;

    private List<Regiao> listaFiltrada;

    private Regiao regiao;

    private int pos;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        mainPane.setCenter(gridPane);
        this.pesquisarNaLista();
    } 
    
    public void preencheGridRegioes() {
        regioes = RegiaoServices.getRegioes();

        int column = 0;
        int row = 0;

        for (Regiao r : regioes) {

            if (FXMLRegionSelectionController.class.getResourceAsStream("pics/regions/" + r.getSigla().toLowerCase() + ".png") != null) {
                ImageView image = new ImageView();
                if (getClass().getResourceAsStream("pics/regions/" + r.getSigla().toLowerCase() + ".png") != null) {
                    image.setImage(new Image(getClass().getResourceAsStream("pics/regions/" + r.getSigla().toLowerCase() + ".png")));
                }else{
                    image.setImage(new Image(getClass().getResourceAsStream("pics/regions/nd.png")));
                } 

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
        listaFiltrada = regioes;
        this.selecionaRegiao();
    }
    
    public void pesquisarNaLista() {
        searchBar.setOnKeyReleased((event) -> {
            List<Regiao> temp = new ArrayList<>();
            String texto = searchBar.getText();
            if (texto.isEmpty()) {
                this.limpaGrid();
                this.preencheGridRegioes();
                System.out.println(listaFiltrada.size());
            } else {
                this.limpaGrid();
                for (Regiao r : regioes) {
                    if (r.getSigla().toLowerCase().contains(texto.toLowerCase())) {
                        temp.add(r);
                    }
                }
                this.preencheGridRegioesFiltrada(temp);
            }
        });
    }
    
    public void limpaGrid() {
        gridPane.getChildren().removeIf(node -> Objects.equals(GridPane.getRowIndex(node), GridPane.getRowIndex(node)));
    }
    
    public void preencheGridRegioesFiltrada(List<Regiao> list) {
        int column = 0;
        int row = 0;

        this.limpaGrid();

        for (Regiao r : list) {
            ImageView image = new ImageView();
            if (getClass().getResourceAsStream("pics/regions/" + r.getSigla().toLowerCase() + ".png") != null) {
                image.setImage(new Image(getClass().getResourceAsStream("pics/regions/" + r.getSigla().toLowerCase() + ".png")));
            }else{
                image.setImage(new Image(getClass().getResourceAsStream("pics/regions/nd.png")));
            }        
            image.setFitHeight(50);
            image.setFitWidth(75);

            gridPane.add(image, column, row);
            column++;

            if (column == 6) {
                column = 0;
                row++;
            }
        }
        listaFiltrada = list;
        this.selecionaRegiao();
    }
    
    public void selecionaRegiao() {
        for (Node node : gridPane.getChildren()) {
            node.setCursor(Cursor.HAND);
            node.setOnMouseClicked((event) -> {
                regionSelected = (ImageView) node;
                int column = GridPane.getColumnIndex(node);
                int row = GridPane.getRowIndex(node) + 1;
                if (row > 1) {
                    pos = 6 * (row - 1) + column;
                    regiao = listaFiltrada.get(pos);
                    this.closePopUp();
                } else {
                    pos = column;
                    regiao = listaFiltrada.get(pos);
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
                Tooltip.install(node, new Tooltip(listaFiltrada.get(pos).getDescricao()));
            });
        }
    }
    
    @FXML
    public void closePopUp() {
        Stage stage = (Stage) this.imgBack.getScene().getWindow();
        stage.close();
    }
    
    public Image getRegionImageSelected() {
        return regionSelected.getImage();
    }

    public Regiao getRegiaoSelected() {
        return regiao;
    }
    
}
