/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lolui;

import java.io.File;
import lolui.exceptions.InsertEquipaDBException;
import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.imageio.ImageIO;
import lolbll.EquipaServices;
import loldal.model.Equipa;
import loldal.model.Membroequipa;
import loldal.model.Pais;

/**
 * FXML Controller class
 *
 * @author joaoc
 */
public class FXMLCreateTeamController implements Initializable {

    @FXML
    private ImageView imgBack;

    @FXML
    private BorderPane parentBorderPane;

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtInitials;

    @FXML
    private ImageView countrySelected;

    @FXML
    private ImageView teamLogo;

    //FXML da grelha direita
    @FXML
    private Button btnTop;

    @FXML
    private Button btnJng;

    @FXML
    private Button btnMid;

    @FXML
    private Button btnAdc;

    @FXML
    private Button btnSup;

    @FXML
    private Button btnCoach;

    @FXML
    private ImageView imgTop;

    @FXML
    private ImageView imgJng;

    @FXML
    private ImageView imgMid;

    @FXML
    private ImageView imgAdc;

    @FXML
    private ImageView imgSup;

    @FXML
    private ImageView imgCoach;

    @FXML
    private Button btnSelectImage;

    private FileChooser fileChooser;

    private File fileImagem;

    private Pais pais;

    private Set<Membroequipa> listaMembroEquipa;

    private Membroequipa membro;

    private String teamName;

    private String initials;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        listaMembroEquipa = new HashSet<>();
        parentBorderPane.getStyleClass().add("borderPane");

        btnSelectImage.setOnAction(
                new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent e) {
                fileChooser = new FileChooser();
                setExtFilters(fileChooser);
                fileImagem = fileChooser.showOpenDialog((Stage) txtUsername.getScene().getWindow());
                if (fileImagem != null) {
                    teamLogo.setImage(new Image(fileImagem.toURI().toString()));
                }
            }
        });
    }

    private void setExtFilters(FileChooser chooser) {
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
    }

    @FXML
    public void closePopUp() {
        Stage stage = (Stage) this.imgBack.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void createCountryAction(MouseEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLCountrySelection.fxml"));
        Parent root = loader.load();
        FXMLCountrySelectionController controller = loader.getController();
        controller.preencheGridPaises();
        //this.preparePopUpElements(event, controller);
        //Metodo para preencher a Janela de PopUp
        this.prepareCountryStage(root, controller);
    }

    public void prepareCountryStage(Parent root, FXMLCountrySelectionController controller) {
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.setY(350);
        stage.setX(650);
        stage.getIcons().add(new Image(LolUI.class.getResourceAsStream("pics/lol.png")));
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(this.imgBack.getScene().getWindow());
        stage.showAndWait();
        if (controller.getPaisSelected() != null) {
            countrySelected.setImage(controller.getCountryImageSelected());
            this.pais = controller.getPaisSelected();
            System.out.println(this.pais.getNome());
        }

    }

    @FXML
    public void selectMemberAction(MouseEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLPlayerSelection.fxml"));
        Parent root = loader.load();
        FXMLPlayerSelectionController controller = loader.getController();
        if (event.getSource() == btnTop) {
            controller.preencheGridPane("TOP");
            controller.setPosicao("TOP");
        }
        if (event.getSource() == btnJng) {
            controller.preencheGridPane("JNG");
            controller.setPosicao("JNG");
        }
        if (event.getSource() == btnMid) {
            controller.preencheGridPane("MID");
            controller.setPosicao("MID");
        }
        if (event.getSource() == btnAdc) {
            controller.preencheGridPane("ADC");
            controller.setPosicao("ADC");
        }
        if (event.getSource() == btnSup) {
            controller.preencheGridPane("SUP");
            controller.setPosicao("SUP");
        }
        if (event.getSource() == btnCoach) {
            controller.preencheGridPane("null");
            controller.setPosicao("null");
        }
        //Metodo para preencher a Janela de PopUp
        this.prepareSelectMemberStage(root, controller);
    }

    public void prepareSelectMemberStage(Parent root, FXMLPlayerSelectionController controller) {
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.setY(350);
        stage.setX(650);
        stage.getIcons().add(new Image(LolUI.class.getResourceAsStream("pics/lol.png")));
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(this.imgBack.getScene().getWindow());
        stage.showAndWait();
        //TODO
        if (controller.getMembroSelected() != null) {
            membro = controller.getMembroSelected();
            listaMembroEquipa.add(membro);
            this.setImagefromMemberSelected(membro, controller);
        }
    }

    public void setImagefromMemberSelected(Membroequipa m, FXMLPlayerSelectionController controller) {
        if (m.getPosicao() != null && m.getPosicao().getSigla().equals("TOP")) {
            imgTop.setImage(controller.getMemberImageSelected());
            btnTop.setText("Edit");
        }
        if (m.getPosicao() != null && m.getPosicao().getSigla().equals("JNG")) {
            imgJng.setImage(controller.getMemberImageSelected());
            btnJng.setText("Edit");
        }
        if (m.getPosicao() != null && m.getPosicao().getSigla().equals("MID")) {
            imgMid.setImage(controller.getMemberImageSelected());
            btnMid.setText("Edit");
        }
        if (m.getPosicao() != null && m.getPosicao().getSigla().equals("ADC")) {
            imgAdc.setImage(controller.getMemberImageSelected());
            btnAdc.setText("Edit");
        }
        if (m.getPosicao() != null && m.getPosicao().getSigla().equals("SUP")) {
            imgSup.setImage(controller.getMemberImageSelected());
            btnSup.setText("Edit");
        }
        if (m.getPosicao() == null) {
            imgCoach.setImage(controller.getMemberImageSelected());
            btnCoach.setText("Edit");
        }
    }

    @FXML
    public void saveOnClick() {
        try {
            this.verificaEquipa();
            this.getUserInput();
            this.gravarEquipa();
            this.gravarImagemDir();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Operation Successfull");
            alert.setContentText("Your new team was created!");
            alert.showAndWait();
        } catch (InsertEquipaDBException ex) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Something went wrong.");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Database internal error.");
            alert.setContentText("Please contact support");
            alert.showAndWait();
        }
    }

    private void gravarImagemDir() {
        try {
            File file = new File(".\\src\\lolui\\pics\\teams\\" + initials.toLowerCase() + ".png");
            ImageIO.write(SwingFXUtils.fromFXImage(teamLogo.getImage(), null), "png", file);
        } catch (IOException ex) {
            Logger.getLogger(FXMLCreateNewMemberController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void gravarEquipa() {
        int contPlayers = 0;
        //criar Equipa e inserir os dados da equipa
        Equipa equipa = new Equipa();
        equipa.setNome(teamName);
        equipa.setSigla(initials);
        equipa.setPais(pais);

        if (listaMembroEquipa.size() > 0) {
            equipa.setMembroequipas(listaMembroEquipa);

            for (Membroequipa m : listaMembroEquipa) {
                m.setEquipa(equipa);
                if (m.getPosicao() != null) {
                    contPlayers++;
                }
            }

            if (contPlayers == 5) {
                equipa.setAtivo(true);
            } else {
                equipa.setAtivo(false);
            }
        }
        //gravar equipa na DB
        EquipaServices.saveEquipa(equipa);
    }

    public void getUserInput() {
        //Recebemos o nome da equipa
        teamName = txtUsername.getText();
        //Recebemos as initials da equipa
        initials = txtInitials.getText();
    }

    public void verificaEquipa() throws InsertEquipaDBException {
        List<Equipa> lista = EquipaServices.listaEquipas();
        //Verificar parametros nome da equipa

        if (txtUsername.getText().isEmpty()) {
            throw new InsertEquipaDBException("The team name is empty");
        }

        if (txtUsername.getText().length() > 20) {
            throw new InsertEquipaDBException("The team name is too long, maximum 20 characters");
        }

        for (Equipa e : lista) {
            if (e.getNome().toLowerCase().equals(txtUsername.getText().toLowerCase())) {
                throw new InsertEquipaDBException("The team name is already in use");
            }
        }

        //Verificar parametros initials da equipa
        if (txtInitials.getText().isEmpty()) {
            throw new InsertEquipaDBException("The team initials are empty");
        }

        if (txtInitials.getText().length() > 4 || txtInitials.getText().contains(" ")) {
            throw new InsertEquipaDBException("The team initials cannot contain spaces and must have a maximum of 4 characters");
        }

        for (Equipa e : lista) {
            if (e.getSigla().toLowerCase().equals(txtInitials.getText().toLowerCase())) {
                throw new InsertEquipaDBException("The team initials are already in use");
            }
        }

        //Verificar se pais existe
        if (pais == null) {
            throw new InsertEquipaDBException("Choose the team's country");
        }
    }

}
