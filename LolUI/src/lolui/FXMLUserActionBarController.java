/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lolui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Line;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import loldal.model.Admin;

/**
 * FXML Controller class
 *
 * @author joaoc
 */
public class FXMLUserActionBarController implements Initializable {

    /**
     * Initializes the controller class.
     */
    //Id's das imagens
    @FXML
    private ImageView imgTournament;

    @FXML
    private ImageView imgTeams;

    @FXML
    private ImageView imgPlayer;

    @FXML
    private ImageView imgAdmin;

    //Textos das imagens
    @FXML
    private Label lblTournament;

    @FXML
    private Label lblTeams;

    @FXML
    private Label lblPlayer;

    @FXML
    private Label lblAdmin;

    @FXML
    private Line line;

    @FXML
    private BorderPane mainPane;

    @FXML
    private GridPane currentGrid;

    @FXML
    private MenuButton accountMenu;
    
    @FXML MenuItem itemChangePassword;
    @FXML MenuItem itemCreateNewAdmin;
    @FXML MenuItem itemLogOut;
//    private Label labelLogOut;
//    private Label labelchangePass;
//    private Label labelCreateAdmin;

    private static boolean loggedIn;
    private static Admin currentAdmin;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        this.labelCreateAdmin = new Label();
//        this.labelLogOut = new Label();
//        this.labelchangePass = new Label();
        ScaleTransition st = new ScaleTransition();
        
        System.out.println(loggedIn);

        fadeInAnimation();

        st.setNode(line);
        st.setDuration(Duration.seconds(1));
        st.setToX(-1500);
        st.play();
        
        
    }

    public static Admin getCurrentAdmin() {
        return currentAdmin;
    }

    public static void setCurrentAdmin(Admin currentAdmin) {
        FXMLUserActionBarController.currentAdmin = currentAdmin;
    }
    
    

    public void fadeInAnimation() {
        FadeTransition ft = new FadeTransition(Duration.millis(1000), imgTournament);
        ft.setFromValue(0.1);
        ft.setToValue(1.0);
        ft.play();

        ft = new FadeTransition(Duration.millis(1000), imgTeams);
        ft.setFromValue(0.1);
        ft.setToValue(1.0);
        ft.play();

        ft = new FadeTransition(Duration.millis(1000), imgPlayer);
        ft.setFromValue(0.1);
        ft.setToValue(1.0);
        ft.play();

        ft = new FadeTransition(Duration.millis(1000), imgAdmin);
        ft.setFromValue(0.1);
        ft.setToValue(1.0);
        ft.play();

        ft = new FadeTransition(Duration.millis(1000), lblTournament);
        ft.setFromValue(0.1);
        ft.setToValue(1.0);
        ft.play();

        ft = new FadeTransition(Duration.millis(1000), lblTeams);
        ft.setFromValue(0.1);
        ft.setToValue(1.0);
        ft.play();

        ft = new FadeTransition(Duration.millis(1000), lblPlayer);
        ft.setFromValue(0.1);
        ft.setToValue(1.0);
        ft.play();

        ft = new FadeTransition(Duration.millis(1000), lblAdmin);
        ft.setFromValue(0.1);
        ft.setToValue(1.0);
        ft.play();

        ft.setOnFinished((event) -> {
            try {
                lblTournament.setStyle("-fx-border-color: orange; -fx-border-width: 1px; -fx-border-radius: 5px;-fx-font-weight: bold");
                currentGrid = FXMLLoader.load(getClass().getResource("FXMLTournamentMain.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(FXMLUserActionBarController.class.getName()).log(Level.SEVERE, null, ex);
            }
            mainPane.setCenter(currentGrid);
        });
    }

    public void btnTeamsClicked() {
        try {
            lblTournament.setStyle("-fx-border-color: orange; -fx-border-width: 0px; -fx-border-radius: 5px;-fx-font-weight: bold");
            lblTeams.setStyle("-fx-border-color: orange; -fx-border-width: 1px; -fx-border-radius: 5px;-fx-font-weight: bold");
            lblPlayer.setStyle("-fx-border-color: orange; -fx-border-width: 0px; -fx-border-radius: 5px;-fx-font-weight: bold");
            lblAdmin.setStyle("-fx-border-color: orange; -fx-border-width: 0px; -fx-border-radius: 5px;-fx-font-weight: bold");
            currentGrid = FXMLLoader.load(getClass().getResource("FXMLTeamsMain.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLUserActionBarController.class.getName()).log(Level.SEVERE, null, ex);
        }
        mainPane.setCenter(currentGrid);
    }

    public void btnPlayersClicked() {
        try {
            lblTournament.setStyle("-fx-border-color: orange; -fx-border-width: 0px; -fx-border-radius: 5px;-fx-font-weight: bold");
            lblTeams.setStyle("-fx-border-color: orange; -fx-border-width: 0px; -fx-border-radius: 5px;-fx-font-weight: bold");
            lblPlayer.setStyle("-fx-border-color: orange; -fx-border-width: 1px; -fx-border-radius: 5px;-fx-font-weight: bold");
            lblAdmin.setStyle("-fx-border-color: orange; -fx-border-width: 0px; -fx-border-radius: 5px;-fx-font-weight: bold");
            currentGrid = FXMLLoader.load(getClass().getResource("FXMLPlayersMain.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLUserActionBarController.class.getName()).log(Level.SEVERE, null, ex);
        }
        mainPane.setCenter(currentGrid);
    }

//    public void btnAdminClicked(){
//        this.imgAdmin.setOnMouseClicked((event) -> {
//            try {
//                currentGrid = FXMLLoader.load(getClass().getResource("FXMLAdminMain.fxml"));
//            } catch (IOException ex) {
//                Logger.getLogger(FXMLUserActionBarController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            mainPane.setCenter(currentGrid);
//        });
//    }
    public void btnTournamentsClicked() {
        try {
            lblTournament.setStyle("-fx-border-color: orange; -fx-border-width: 1px; -fx-border-radius: 5px;-fx-font-weight: bold");
            lblTeams.setStyle("-fx-border-color: orange; -fx-border-width: 0px; -fx-border-radius: 5px;-fx-font-weight: bold");
            lblPlayer.setStyle("-fx-border-color: orange; -fx-border-width: 0px; -fx-border-radius: 5px;-fx-font-weight: bold");
            lblAdmin.setStyle("-fx-border-color: orange; -fx-border-width: 0px; -fx-border-radius: 5px;-fx-font-weight: bold");
            currentGrid = FXMLLoader.load(getClass().getResource("FXMLTournamentMain.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLUserActionBarController.class.getName()).log(Level.SEVERE, null, ex);
        }
        mainPane.setCenter(currentGrid);
    }

    @FXML
    public void handleLoginAction() throws IOException {
        lblTournament.setStyle("-fx-border-color: orange; -fx-border-width: 0px; -fx-border-radius: 5px;-fx-font-weight: bold");
        lblTeams.setStyle("-fx-border-color: orange; -fx-border-width: 0px; -fx-border-radius: 5px;-fx-font-weight: bold");
        lblPlayer.setStyle("-fx-border-color: orange; -fx-border-width: 0px; -fx-border-radius: 5px;-fx-font-weight: bold");
        lblAdmin.setStyle("-fx-border-color: orange; -fx-border-width: 1px; -fx-border-radius: 5px;-fx-font-weight: bold");

        if (!loggedIn) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLLogin.fxml"));
            Parent root = loader.load();
            FXMLLoginController controller = loader.getController();
            this.prepareStage(root);
        } else {
            accountMenu.setVisible(true);
            this.carregaPainelAdmin();
        }
        
    }

    public void prepareStage(Parent root) {
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.setY(350);
        stage.setX(700);
        stage.getIcons().add(new Image(LolUI.class.getResourceAsStream("pics/lol.png")));
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(this.imgAdmin.getScene().getWindow());
        stage.addEventHandler(KeyEvent.KEY_RELEASED, (KeyEvent event) -> {
            if (KeyCode.ESCAPE == event.getCode()) {
                stage.close();
            }
        });
        stage.showAndWait();

        if (loggedIn) {
            accountMenu.setVisible(true);
            this.accountMenu.setText("Welcome, " + currentAdmin.getUsername());
//            this.labelLogOut.setText("Log Out");
//            this.labelLogOut.prefWidthProperty().bind(this.accountMenu.widthProperty().subtract(43));
//            this.itemLogOut.setGraphic(labelLogOut);
//            
//            
//            this.labelCreateAdmin.setText("Create New Admin");
//            this.labelCreateAdmin.prefWidthProperty().bind(this.accountMenu.widthProperty().subtract(43));
//            this.itemCreateNewAdmin.setGraphic(labelCreateAdmin);
//            
//            Image troca = new Image(LolUI.class.getResourceAsStream("pics/exchange.png"));
//            ImageView c = new ImageView(troca);
//            c.fitWidthProperty().setValue(10);
//            c.fitHeightProperty().setValue(10);
//            this.labelchangePass.prefWidthProperty().bind(this.accountMenu.widthProperty().subtract(43));
//            this.itemChangePassword.setGraphic(c);
            this.carregaPainelAdmin();
        }
    }

    //Método Logout
    @FXML
    public void logout() {
        this.btnTournamentsClicked();
        loggedIn = false;
        accountMenu.setVisible(false);
        currentAdmin = null;
        
    }
    
    public void carregaPainelAdmin() {
        System.out.println("NO METODO carregaPainelAdmin");
        try {
            currentGrid = FXMLLoader.load(getClass().getResource("FXMLAdminMain.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLUserActionBarController.class.getName()).log(Level.SEVERE, null, ex);
        }

        mainPane.setCenter(currentGrid);
    }

    public static void setLoggedIn(boolean bool) {
        loggedIn = bool;
    }
    
    @FXML
    public void handleChangePasswordAction() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLChangeAdminPassword.fxml"));
        Parent root = loader.load();
        FXMLChangeAdminPasswordController controller = loader.getController();
        this.prepareStage(root);
    }
    
    public void handleCreateNewAdmindAction() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLCreateNewAdmin.fxml"));
        Parent root = loader.load();
        FXMLCreateNewAdminController controller = loader.getController();
        this.prepareStage(root);
    }
}
