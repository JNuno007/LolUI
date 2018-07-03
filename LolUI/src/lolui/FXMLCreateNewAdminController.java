/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lolui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import lolbll.AdminServices;
import lolbll.HibernateBLL;
import loldal.model.Admin;

/**
 * FXML Controller class
 *
 * @author vaaz
 */
public class FXMLCreateNewAdminController implements Initializable {

    
    @FXML private ImageView imgBack;
    @FXML private ImageView imgWarning;
    @FXML private TextField newAdminNameTxt;
    @FXML private PasswordField newPassword;
    @FXML private PasswordField confirmationPassword;
    @FXML private Button btnCreateNewAdmin;
    @FXML private Line lineAdminName;
    @FXML private Line linePassword;
    @FXML private Line lineConfirmationPassword;
    @FXML private Label lblWarning;
    private List<Admin> admins; 
    private Admin newAdmin;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.admins = AdminServices.listaAdmins();
        this.newAdmin = new Admin();
    }
    
    @FXML
    public void closePopUp() {
        Stage stage = (Stage) this.imgBack.getScene().getWindow();
        stage.close();
    }
    
    public boolean verificaDadosNovoAdmin(){
        boolean valid = false;
        if(newAdminNameTxt.getText().isEmpty()){
            lblWarning.setText("Username is empty.");
            lineAdminName.setStyle("-fx-stroke: red;");
            linePassword.setStyle("-fx-stroke: orange;");
            lineConfirmationPassword.setStyle("-fx-stroke: orange;");
        }else{
            if(newAdminNameTxt.getText().length() > 40){
                lblWarning.setText("Username have more than 40 characters.");
                lineAdminName.setStyle("-fx-stroke: red;");
                linePassword.setStyle("-fx-stroke: orange;");
                lineConfirmationPassword.setStyle("-fx-stroke: orange;");
            }else{
                if(existeAdmin()){
                    lblWarning.setText("This Admin already exists.");
                    lineAdminName.setStyle("-fx-stroke: red;");
                    linePassword.setStyle("-fx-stroke: orange;");
                    lineConfirmationPassword.setStyle("-fx-stroke: orange;");
                }else{
                    if(newPassword.getText().isEmpty()){
                        lblWarning.setText("New Password is empty.");
                        lineAdminName.setStyle("-fx-stroke: orange;");
                        linePassword.setStyle("-fx-stroke: red;");
                        lineConfirmationPassword.setStyle("-fx-stroke: orange;");
                    }else{
                        if(newPassword.getText().length() > 40){
                            lblWarning.setText("Password have more than 40 characters.");
                            lineAdminName.setStyle("-fx-stroke: orange;");
                            linePassword.setStyle("-fx-stroke: red;");
                            lineConfirmationPassword.setStyle("-fx-stroke: orange;");
                        }else{
                            if(confirmationPassword.getText().isEmpty()){
                                lblWarning.setText("Confirmation Password is empty.");
                                lineAdminName.setStyle("-fx-stroke: orange;");
                                linePassword.setStyle("-fx-stroke: orange;");
                                lineConfirmationPassword.setStyle("-fx-stroke: red;");
                            }else{
                                if(confirmationPassword.getText().length() > 40){
                                    lblWarning.setText("Password have more than 40 characters.");
                                    lineAdminName.setStyle("-fx-stroke: orange;");
                                    linePassword.setStyle("-fx-stroke: orange;");
                                    lineConfirmationPassword.setStyle("-fx-stroke: red;");
                                }else{
                                    if(!newPassword.getText().equals(confirmationPassword.getText())){
                                        lblWarning.setText("Passwords do not match.");
                                        lineAdminName.setStyle("-fx-stroke: orange;");
                                        linePassword.setStyle("-fx-stroke: red;");
                                        lineConfirmationPassword.setStyle("-fx-stroke: red;");
                                    }else{
                                        valid =  true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        
        if(valid){
            lblWarning.setVisible(false);
            imgWarning.setVisible(false);
            lineAdminName.setStyle("-fx-stroke: orange;");
            linePassword.setStyle("-fx-stroke: orange;");
            lineConfirmationPassword.setStyle("-fx-stroke: orange;");
        }else{
            lblWarning.setVisible(true);
            imgWarning.setVisible(true);
        }
        return valid;
    }
    
    public boolean existeAdmin(){
        boolean existe = false;
        for(Admin admin: admins){
            if(admin.getUsername().equals(newAdminNameTxt.getText())){
                existe = true;
            }
        }
        return existe;
    }
    
    @FXML
    public void saveNewAdmin(){
        if(verificaDadosNovoAdmin()){
            newAdmin.setUsername(newAdminNameTxt.getText());
            newAdmin.setPassword(newPassword.getText());
            AdminServices.saveAdmin(newAdmin);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Operation Successfull");
            alert.setContentText("The new Admin " + newAdmin.getUsername() + " has been created!");
            alert.showAndWait();
            HibernateBLL.clearCache();
            closePopUp();
        }
    }
}
