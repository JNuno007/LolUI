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
import lolbll.EquipaServices;
import lolbll.ImagesTeamServices;
import loldal.model.Equipa;
import loldal.model.Membroequipa;

/**
 * FXML Controller class
 *
 * @author joaoc
 */
public class FXMLTeamSelectionController implements Initializable {

    @FXML
    private BorderPane mainPane;

    @FXML
    private GridPane gridPane;

    @FXML
    private ImageView imgBack;

    @FXML
    private TextField searchBar;

    private ImageView teamSelected;
    
    private int pos;
    
    private Equipa equipa;
    
    private List<Equipa> equipas;
    
    private List<Equipa> listaFiltrada;
    
    private String posicao;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        mainPane.setCenter(gridPane);
        this.pesquisarNaLista();
    }
    
    public void selecionaEquipas() {
        for (Node node : gridPane.getChildren()) {
            node.setCursor(Cursor.HAND);
            node.setOnMouseClicked((event) -> {
                teamSelected = (ImageView) node;
                int column = GridPane.getColumnIndex(node);
                int row = GridPane.getRowIndex(node) + 1;
                if (row > 1) {
                    pos = 6 * (row - 1) + column;
                    equipa = listaFiltrada.get(pos);
                    this.closePopUp();
                } else {
                    pos = column;
                    equipa = listaFiltrada.get(pos);
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
            List<Equipa> temp = new ArrayList<>();
            String texto = searchBar.getText();
            if (texto.isEmpty()) {
                this.limpaGrid();
                this.preencheGridEquipas();
                System.out.println(listaFiltrada.size());
            } else {
                this.limpaGrid();
                for (Equipa eq : equipas) {
                    if (eq.getNome().toLowerCase().contains(texto.toLowerCase())) {
                        temp.add(eq);
                    }
                }
                this.preencheGridEquipasFiltrada(temp);
            }
        });
    }
    
    public void preencheGridEquipas() {
        equipas = listaFiltrada;

        int column = 0;
        int row = 0;

        for (Equipa eq : equipas) {
            
            if(ImagesTeamServices.existsOnMap(eq.getNome())){
                ImageView image = new ImageView();
                image.setImage(new Image(ImagesTeamServices.getOriginalPath(eq.getNome())));

                image.setFitHeight(50);
                image.setFitWidth(75);

                gridPane.add(image, column, row);
                column++;

                if (column == 6) {
                    column = 0;
                    row++;
                }
            }else{
                if (FXMLCountrySelectionController.class.getResourceAsStream("pics/teams/" + eq.getSigla().toLowerCase() + ".png") != null) {
                    ImageView image = new ImageView();
                    image.setImage(new Image(LolUI.class.getResourceAsStream("pics/teams/" + eq.getSigla().toLowerCase() + ".png")));

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
        listaFiltrada = equipas;
        this.selecionaEquipas();
    }
    
    public void equipasDisponiveis(String pos){
        String posicao = pos;
        List<Equipa> temp = new ArrayList<>();
        if(pos == null){
            System.out.println("Entrou no NULL");
            for(Equipa eq: EquipaServices.listaEquipas()){
                int cont = 0;
                List<Membroequipa> membrosList = new ArrayList<>();
                membrosList.addAll(eq.getMembroequipas());
                //System.out.println(eq.getNome());
                for(Membroequipa me : membrosList){
                    if(me.getPosicao()==null){
                        cont++;
                    }
                }
                System.out.println(cont);
                if(cont == 0 || membrosList.isEmpty()){
                    temp.add(eq);
                }
            }
        }else{
            System.out.println("ENTROU NO POS");
            for(Equipa eq: EquipaServices.listaEquipas()){
                int cont = 0;
                List<Membroequipa> membrosList = new ArrayList<>();
                membrosList.addAll(eq.getMembroequipas());
                System.out.println(eq.getNome());
                for(Membroequipa me : membrosList){
                    if(me.getPosicao()!= null && me.getPosicao().getSigla().equals(pos)){
                        cont++;
                    }
                }
                System.out.println(cont);
                if((cont == 0 && membrosList.size() > 0) || membrosList.isEmpty()){
                    temp.add(eq);
                }
            }
        }
        listaFiltrada = temp;
        this.preencheGridEquipas();
    }
    
    public void preencheGridEquipasFiltrada(List<Equipa> list) {
        int column = 0;
        int row = 0;

        this.limpaGrid();

        for (Equipa eq : list) {

            if(ImagesTeamServices.existsOnMap(eq.getNome())){
                ImageView image = new ImageView();
                image.setImage(new Image(ImagesTeamServices.getOriginalPath(eq.getNome())));

                image.setFitHeight(50);
                image.setFitWidth(75);

                gridPane.add(image, column, row);
                column++;

                if (column == 6) {
                    column = 0;
                    row++;
                }
            }else{
                if (FXMLCountrySelectionController.class.getResourceAsStream("pics/teams/" + eq.getSigla().toLowerCase() + ".png") != null) {
                    ImageView image = new ImageView();
                    image.setImage(new Image(LolUI.class.getResourceAsStream("pics/teams/" + eq.getSigla().toLowerCase() + ".png")));

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
        this.selecionaEquipas();
    }
    
    public void limpaGrid() {
        gridPane.getChildren().removeIf(node -> Objects.equals(GridPane.getRowIndex(node), GridPane.getRowIndex(node)));
    }
    
     public Image getTeamImageSelected() {
        return teamSelected.getImage();
    }

    @FXML
    public void closePopUp() {
        Stage stage = (Stage) this.imgBack.getScene().getWindow();
        stage.close();
    }
    
    public Equipa getEquipaSelected() {
        return equipa;
    }
    
}
