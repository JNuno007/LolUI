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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import lolbll.CountryServices;
import loldal.model.Pais;

/**
 * FXML Controller class
 *
 * @author joaoc
 */
public class FXMLCountrySelectionController implements Initializable {

    @FXML private BorderPane mainPane;
    
    @FXML private GridPane gridPane;
    
    @FXML private ImageView imgBack;
    
    @FXML private TextField searchBar;
    
    private List<Pais> paises;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        mainPane.setCenter(gridPane);
        this.pesquisarNaLista();
        System.out.println("INITIALIZE DEPOIS DE SET CENTER");
    }

    // -- Criar GridPane de Paises
    // Criar CountryServices para ir buscar a Lista de Paises à BD
    // Vamos à BLL buscar a lista de Paises, ordenada por nome
    // Tendo a lista de paises , adicionamos a cada célula da grid uma ImageView
    // 5 ou 6 colunas.
    
    public void preencheGridPaises(){
        paises = CountryServices.getListaPaises();
        
        int column = 0;
        int row = 0;
        
        for(Pais p: paises){
            
            if(FXMLCountrySelectionController.class.getResourceAsStream("pics/countries/" + p.getSigla().toLowerCase() + ".png")!=null){
                ImageView image = new ImageView();
                image.setImage(new Image(LolUI.class.getResourceAsStream("pics/countries/"+ p.getSigla().toLowerCase() +".png")));
            
                image.setFitHeight(50);
                image.setFitWidth(75);

                gridPane.add(image, column, row);
                column++;
                
                if(column == 6){
                    column = 0;
                    row++;
                }
            }
        }
        System.out.println("DEPOIS DO FOR");
    }
    
    public void pesquisarNaLista(){
       searchBar.setOnKeyReleased((event) -> {
           List<Pais> temp = new ArrayList<>();
           String texto = searchBar.getText();
           if(texto.isEmpty()){
               this.limpaGrid();
               this.preencheGridPaises();
           }else{
               this.limpaGrid();
               for(Pais p: paises){
                   if(p.getNome().toLowerCase().contains(texto.toLowerCase())){
                       temp.add(p);
                   }
               }
               this.preencheGridPaisesFiltrada(temp);
           }
        });
    }
    
    public void preencheGridPaisesFiltrada(List<Pais> list){
        int column = 0;
        int row = 0;
        
        this.limpaGrid();
        
        for(Pais p: list){
            
            if(FXMLCountrySelectionController.class.getResourceAsStream("pics/countries/" + p.getSigla().toLowerCase() + ".png")!=null){
                ImageView image = new ImageView();
                image.setImage(new Image(LolUI.class.getResourceAsStream("pics/countries/"+ p.getSigla().toLowerCase() +".png")));
            
                image.setFitHeight(50);
                image.setFitWidth(75);

                gridPane.add(image, column, row);
                column++;
                
                if(column == 6){
                    column = 0;
                    row++;
                }
            }
        }
        System.out.println("DEPOIS DO FOR");
    }
    
    public void limpaGrid(){
       gridPane.getChildren().removeIf(node -> Objects.equals(GridPane.getRowIndex(node), GridPane.getRowIndex(node)));
    }
    
    @FXML public void closePopUp(){
        Stage stage = (Stage) this.imgBack.getScene().getWindow();
        stage.close();
    }
    
}
