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
import lolbll.ImagesMemberServices;
import lolbll.MembroEquipaServices;
import loldal.model.Membroequipa;

/**
 * FXML Controller class
 *
 * @author joaoc
 */
public class FXMLPlayerSelectionController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private BorderPane mainPane;

    @FXML
    private GridPane gridPane;

    @FXML
    private ImageView imgBack;

    @FXML
    private TextField searchBar;

    private ImageView memberSelected;

    private Membroequipa me;

    private List<Membroequipa> lista;

    private List<Membroequipa> listaFiltrada;

    private int pos;

    private String posicao;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mainPane.setCenter(gridPane);
        this.preencheListaMembrosDisponiveis();
        this.pesquisarNaLista();
    }

    public void preencheListaMembrosDisponiveis() {
        lista = MembroEquipaServices.getAllMembrosDisponiveis();
        System.out.println(lista.size());
    }

    @FXML
    public void closePopUp() {
        Stage stage = (Stage) this.imgBack.getScene().getWindow();
        stage.close();
    }

    public void preencheGridPane(String posicao) {
        List<Membroequipa> listaGrid = new ArrayList<>();

        if (posicao.equals("null")) {
            for (Membroequipa me : lista) {
                if (me.getPosicao() == null) {
                    listaGrid.add(me);
                }
            }
        } else {
            for (Membroequipa me : lista) {
                if (me.getPosicao() != null && me.getPosicao().getSigla().equals(posicao)) {
                    listaGrid.add(me);
                }
            }
        }

        int column = 0;
        int row = 0;

        for (Membroequipa m : listaGrid) {

            if(ImagesMemberServices.existsOnMap(m.getNome())){
                ImageView image = new ImageView();
                image.setImage(new Image(ImagesMemberServices.getOriginalPath(m.getNome())));

                image.setFitHeight(50);
                image.setFitWidth(75);

                gridPane.add(image, column, row);
                column++;

                if (column == 6) {
                    column = 0;
                    row++;
                }
            }else{
                if (FXMLPlayerSelectionController.class.getResourceAsStream("pics/players/" + m.getNome() + ".png") != null) {
                    ImageView image = new ImageView();
                    image.setImage(new Image(LolUI.class.getResourceAsStream("pics/players/" + m.getNome() + ".png")));

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
        }
        listaFiltrada = listaGrid;
        this.selecionaMembro();
    }
    
    public void preencheGridPaneComMembroExra(String posicao, Membroequipa membro) {
        List<Membroequipa> listaGrid = new ArrayList<>();

        if (posicao.equals("null")) {
            for (Membroequipa me : lista) {
                if (me.getPosicao() == null) {
                    listaGrid.add(me);
                }
            }
        } else {
            for (Membroequipa me : lista) {
                if (me.getPosicao() != null && me.getPosicao().getSigla().equals(posicao)) {
                    listaGrid.add(me);
                }
            }
        }
        if(membro != null){
            listaGrid.add(membro);
        }

        int column = 0;
        int row = 0;

        for (Membroequipa m : listaGrid) {
            
            if(ImagesMemberServices.existsOnMap(m.getNome())){
                ImageView image = new ImageView();
                image.setImage(new Image(ImagesMemberServices.getOriginalPath(m.getNome())));

                image.setFitHeight(50);
                image.setFitWidth(75);

                gridPane.add(image, column, row);
                column++;

                if (column == 6) {
                    column = 0;
                    row++;
                }
            }else{
                if (FXMLPlayerSelectionController.class.getResourceAsStream("pics/players/" + m.getNome() + ".png") != null) {
                    ImageView image = new ImageView();
                    image.setImage(new Image(LolUI.class.getResourceAsStream("pics/players/" + m.getNome() + ".png")));

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
        }
        listaFiltrada = listaGrid;
        this.selecionaMembro();
    }

    public void selecionaMembro() {
        for (Node node : gridPane.getChildren()) {
            node.setCursor(Cursor.HAND);
            node.setOnMouseClicked((event) -> {
                memberSelected = (ImageView) node;
                int column = GridPane.getColumnIndex(node);
                int row = GridPane.getRowIndex(node) + 1;
                if (row > 1) {
                    pos = 6 * (row - 1) + column;
                    me = listaFiltrada.get(pos);
                    this.closePopUp();
                } else {
                    pos = column;
                    me = listaFiltrada.get(pos);
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

    public void pesquisarNaLista() {
        searchBar.setOnKeyReleased((event) -> {
            List<Membroequipa> temp = new ArrayList<>();
            String texto = searchBar.getText();
            if (texto.isEmpty()) {
                this.limpaGrid();
                this.preencheGridPane(posicao);
                System.out.println(listaFiltrada.size());
            } else {
                this.limpaGrid();
                if (posicao.equals("null")) {
                    for (Membroequipa me : lista) {
                        if (me.getPosicao() == null && me.getNome().toLowerCase().contains(texto.toLowerCase())) {
                            temp.add(me);
                        }
                    }
                } else {
                    for (Membroequipa me : lista) {
                        if (me.getPosicao() != null && me.getPosicao().getSigla().equals(posicao) && me.getNome().toLowerCase().contains(texto.toLowerCase())) {
                            temp.add(me);
                        }
                    }
                }
                this.preencheGridMembrosFiltrada(temp);
            }
        });
    }

    public void preencheGridMembrosFiltrada(List<Membroequipa> list) {
        int column = 0;
        int row = 0;

        this.limpaGrid();

        for (Membroequipa me : list) {
            
            if(ImagesMemberServices.existsOnMap(me.getNome())){
                ImageView image = new ImageView();
                image.setImage(new Image(ImagesMemberServices.getOriginalPath(me.getNome())));

                image.setFitHeight(50);
                image.setFitWidth(75);

                gridPane.add(image, column, row);
                column++;

                if (column == 6) {
                    column = 0;
                    row++;
                }
            }else{
                if (FXMLPlayerSelectionController.class.getResourceAsStream("pics/players/" + me.getNome() + ".png") != null) {
                    ImageView image = new ImageView();
                    image.setImage(new Image(LolUI.class.getResourceAsStream("pics/players/" + me.getNome() + ".png")));

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
        }
        listaFiltrada = list;
        this.selecionaMembro();
    }

    public void limpaGrid() {
        gridPane.getChildren().removeIf(node -> Objects.equals(GridPane.getRowIndex(node), GridPane.getRowIndex(node)));
    }

    public Image getMemberImageSelected() {
        return memberSelected.getImage();
    }

    public Membroequipa getMembroSelected() {
        return me;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

}
