/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lolui;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lolbll.HibernateBLL;
import model.Torneio;   
//import loldal.model.Torneio;

/**
 *
 * @author joaoc
 */
public class LolUI extends Application {
    
     @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLMainScreen.fxml"));
        
        Scene scene = new Scene(root);
        
        //Configuração do stage
        stage.setTitle("LoL eSports");
        stage.getIcons().add(new Image(LolUI.class.getResourceAsStream("pics/lol.png")));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    
    @Override
    public void stop(){
        HibernateBLL.close();
        Platform.exit();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
        Torneio torneio = new Torneio();
    }
    
}
