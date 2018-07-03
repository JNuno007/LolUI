/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lolui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import lolbll.AdminServices;
import loldal.model.Admin;

/**
 * FXML Controller class
 *
 * @author vaaz
 */
public class FXMLChangeAdminPasswordController implements Initializable {

    @FXML private ImageView imgBack;
    @FXML private ImageView imgWarning;
    @FXML private PasswordField currentPass;
    @FXML private PasswordField newPass;
    @FXML private PasswordField confirmationPass;
    @FXML private Button btnChangePassword;
    @FXML private Label lblWarning;
    @FXML private Line lineCurrentPass;
    @FXML private Line lineNewPass;
    @FXML private Line lineConfirmationPass;
    
    
    private Admin adminLogged;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.adminLogged = FXMLUserActionBarController.getCurrentAdmin();
    }    
 
    @FXML
    public void closePopUp() {
        Stage stage = (Stage) this.imgBack.getScene().getWindow();
        stage.close();
    }
    
    
    @FXML
    public void changePassword(){
        if(verificaPassword()){
            saveNewPassword();
        }
    }
    
    public boolean verificaPassword(){
        boolean valid = false;
        if(currentPass.getText().isEmpty()){
            lblWarning.setText("Current Password is empty.");
            lineCurrentPass.setStyle("-fx-stroke: red;");
            lineNewPass.setStyle("-fx-stroke: orange;");
            lineConfirmationPass.setStyle("-fx-stroke: orange;");
        }else{
            if(!currentPass.getText().equals(adminLogged.getPassword())){
                lblWarning.setText("Current Password is not correct.");
                lineCurrentPass.setStyle("-fx-stroke: red;");
                lineNewPass.setStyle("-fx-stroke: orange;");
                lineConfirmationPass.setStyle("-fx-stroke: orange;");
            }else{
                if(newPass.getText().isEmpty()){
                    lblWarning.setText("New Password is empty.");
                    lineCurrentPass.setStyle("-fx-stroke: orange;");
                    lineNewPass.setStyle("-fx-stroke: red;");
                    lineConfirmationPass.setStyle("-fx-stroke: orange;");
                }else{
                    if(newPass.getText().length() > 40){
                        lblWarning.setText("Password have more than 40 characters.");
                        lineCurrentPass.setStyle("-fx-stroke: orange;");
                        lineNewPass.setStyle("-fx-stroke: red;");
                        lineConfirmationPass.setStyle("-fx-stroke: orange;");
                    }else{
                        if(confirmationPass.getText().isEmpty()){
                            lblWarning.setText("Confirmation Password is empty.");
                            lineCurrentPass.setStyle("-fx-stroke: orange;");
                            lineNewPass.setStyle("-fx-stroke: orange;");
                            lineConfirmationPass.setStyle("-fx-stroke: red;");
                        }else{
                            if(confirmationPass.getText().length() > 40){
                                lblWarning.setText("Password have more than 40 characters.");
                                lineCurrentPass.setStyle("-fx-stroke: orange;");
                                lineNewPass.setStyle("-fx-stroke: orange;");
                                lineConfirmationPass.setStyle("-fx-stroke: red;");
                            }else{
                                if(!newPass.getText().equals(confirmationPass.getText())){
                                    lblWarning.setText("Passwords do not match.");
                                    lineCurrentPass.setStyle("-fx-stroke: orange;");
                                    lineNewPass.setStyle("-fx-stroke: red;");
                                    lineConfirmationPass.setStyle("-fx-stroke: red;");
                                }else{
                                    valid =  true;
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
            lineCurrentPass.setStyle("-fx-stroke: orange;");
            lineNewPass.setStyle("-fx-stroke: orange;");
            lineConfirmationPass.setStyle("-fx-stroke: orange;");
        }else{
            lblWarning.setVisible(true);
            imgWarning.setVisible(true);
        }
        
        return valid;
    }
    
    public void saveNewPassword(){
        adminLogged.setPassword(newPass.getText());
        AdminServices.saveAdmin(adminLogged);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Operation Successfull");
        alert.setContentText("Your password has been changed!");
        alert.showAndWait();
        this.closePopUp();
    }
}
