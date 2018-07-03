/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lolui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import lolbll.AdminServices;
import loldal.model.Admin;

/**
 * FXML Controller class
 *
 * @author vaaz
 */
public class FXMLLoginController implements Initializable {

    @FXML
    private ImageView imgBack;

    @FXML
    private BorderPane mainPane;

    @FXML
    private GridPane currentGrid;

    @FXML
    private Button btnLogin;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Label lblWarning;

    @FXML
    private ImageView imgWarning;

    @FXML
    private Line lineUser;

    @FXML
    private Line linePass;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        password.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                if (ke.getCode().equals(KeyCode.ENTER)) {
                    try {
                        loginSuccessefull();
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }

    @FXML
    public void closePopUp() {
        Stage stage = (Stage) this.imgBack.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void loginSuccessefull() throws IOException {
//       if(checkLoginAccount()){
//        if (loginSTUB()) {
//            FXMLUserActionBarController.setLoggedIn(true);
//            this.closePopUp();
//        }
        
        if(checkLoginAccount()){
            FXMLUserActionBarController.setLoggedIn(true);
            this.closePopUp();
        }
    }

    public boolean checkLoginAccount() {
        boolean loggedUser = false;
        boolean checkUser = false;
        String user = username.getText();
        String pass = password.getText();

        List<Admin> admins = AdminServices.listaAdmins();

        for (Admin a : admins) {
            if (a.getUsername().equals(user)) {
                checkUser = true;
                linePass.setStyle("-fx-stroke: red;");
            }
        }

        if (checkUser) {
            for (Admin a : admins) {
                if (a.getUsername().equals(user) && a.getPassword().equals(pass)) {
                    loggedUser = true;
                    lineUser.setStyle(null);
                    lblWarning.setVisible(false);
                    imgWarning.setVisible(false);
                    FXMLUserActionBarController.setCurrentAdmin(a);
                }
            }

            if (!loggedUser) {
                lblWarning.setVisible(true);
                imgWarning.setVisible(true);
                lineUser.setStyle("-fx-stroke: orange;");
                linePass.setStyle("-fx-stroke: red;");
                lblWarning.setText("Your password is incorrect!");
            }
        } else {
            lblWarning.setVisible(true);
            imgWarning.setVisible(true);
            lineUser.setStyle("-fx-stroke: red;");
            lblWarning.setText("Oops! We can't find that account.");
        }

        return loggedUser;
    }

    public boolean loginSTUB() {
        return true;
    }

}
