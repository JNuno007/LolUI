/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lolui;

import lolui.exceptions.InsertMembroEquipaDBException;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.imageio.ImageIO;
import lolbll.EquipaServices;
import lolbll.HibernateBLL;
import lolbll.ImagesMemberServices;
import lolbll.MembroEquipaServices;
import lolbll.PosicaoServices;
import loldal.model.Equipa;
import loldal.model.Membroequipa;
import loldal.model.Pais;
import loldal.model.Posicao;

/**
 * FXML Controller class
 *
 * @author vaaz
 */
public class FXMLCreateNewMemberController implements Initializable {

    @FXML
    private ImageView imgBack;

    @FXML
    private BorderPane parentBorderPane;

    @FXML
    private ImageView countrySelected;

    @FXML
    private ImageView playerPicSelected;

    @FXML
    private ImageView info;
    
    @FXML
    private ImageView imgNewTeamLogoPlayer;

    @FXML
    private Spinner<Integer> spinnerAge;

    @FXML
    private TextField txtUsername;

    @FXML
    private Button btnSelectImage;
    
    @FXML
    private Button btnChooseTeam;

    private FileChooser fileChooser;

    private File fileImagem;

    //variaveis para o Membroequipa
    private String username;

    private Pais pais;

    private String cargo; //se escolher posiçoes é "player", caso contrario é "coach"

    private int age; // converter para BigDecimal

    private Equipa equipa;

    private Posicao posicao;

    //RadioButtons cargo
    @FXML
    RadioButton rbTop;
    @FXML
    RadioButton rbJungler;
    @FXML
    RadioButton rbMid;
    @FXML
    RadioButton rbAdc;
    @FXML
    RadioButton rbSup;
    @FXML
    RadioButton rbCoach;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        parentBorderPane.getStyleClass().add("borderPane");
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(15, 99, 15);
        spinnerAge.setValueFactory(valueFactory);

        btnSelectImage.setOnAction(
                new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent e) {
                fileChooser = new FileChooser();
                setExtFilters(fileChooser);
                fileImagem = fileChooser.showOpenDialog((Stage) txtUsername.getScene().getWindow());
                if (fileImagem != null) {
                    playerPicSelected.setImage(new Image(fileImagem.toURI().toString()));
                }
            }
        });

        info.setOnMouseEntered((event) -> {
            Tooltip.install(info, new Tooltip("This field is optional"));
        });
    }

    private void setExtFilters(FileChooser chooser) {
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
    }

    private void gravarImagemDir() {
        try {
            File file = new File(".\\src\\lolui\\pics\\players\\" + txtUsername.getText() + ".png");
            ImageIO.write(SwingFXUtils.fromFXImage(playerPicSelected.getImage(), null), "png", file);
        } catch (IOException ex) {
            Logger.getLogger(FXMLCreateNewMemberController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void createTeamAction(MouseEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLTeamSelection.fxml"));
        Parent root = loader.load();
        FXMLTeamSelectionController controller = loader.getController();
        
        if(rbTop.isSelected()){
            controller.equipasDisponiveis("TOP");
        }
        if(rbJungler.isSelected()){
            controller.equipasDisponiveis("JNG");
        }
        if(rbMid.isSelected()){
            controller.equipasDisponiveis("MID");
        }
        if(rbAdc.isSelected()){
            controller.equipasDisponiveis("ADC");
        }
        if(rbSup.isSelected()){
            controller.equipasDisponiveis("SUP");
        }
        if(rbCoach.isSelected()){
            controller.equipasDisponiveis(null);
        }
        //Metodo para preencher a Janela de PopUp
        this.prepareTeamStage(root, controller);
    }

    public void prepareTeamStage(Parent root, FXMLTeamSelectionController controller) {
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
        stage.addEventHandler(KeyEvent.KEY_RELEASED, (KeyEvent event) -> {
            if (KeyCode.ESCAPE == event.getCode()) {
                stage.close();
            }
        });
        stage.showAndWait();
        if (controller.getEquipaSelected()!= null) {
            imgNewTeamLogoPlayer.setImage(controller.getTeamImageSelected());
            this.equipa = controller.getEquipaSelected();
            System.out.println(this.equipa.getNome());
        }
    }

    @FXML
    public void closePopUp() {
        Stage stage = (Stage) this.imgBack.getScene().getWindow();
        stage.close();
    }

    //CREATE NEW TEAM
    @FXML
    public void createCountryAction(MouseEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLCountrySelection.fxml"));
        Parent root = loader.load();
        FXMLCountrySelectionController controller = loader.getController();
        controller.preencheGridPaises();
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
        stage.addEventHandler(KeyEvent.KEY_RELEASED, (KeyEvent event) -> {
            if (KeyCode.ESCAPE == event.getCode()) {
                stage.close();
            }
        });
        stage.showAndWait();
        if (controller.getPaisSelected() != null) {
            countrySelected.setImage(controller.getCountryImageSelected());
            this.pais = controller.getPaisSelected();
            System.out.println(this.pais.getNome());
        }

    }
    public void save() throws MalformedURLException{
        try {
            this.verificaMembroEquipa();
            this.getUserInput();
            this.gravarMembroEquipa();
            this.gravarImagemDir();
            HibernateBLL.clearCache();
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Operation Successfull");
            alert.setContentText("Your new member was created!");
            alert.showAndWait();
            this.closePopUp();
        } catch (InsertMembroEquipaDBException e) {
            //Dialog
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Something went wrong.");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    public void saveOnClick() throws MalformedURLException {
        this.save();
    }

    public void getUserInput() {
        username = txtUsername.getText();
        age = spinnerAge.getValue();
        if (rbCoach.isSelected()) {
            cargo = "coach";
        } else {
            cargo = "player";

            if (rbTop.isSelected()) {
                posicao = PosicaoServices.getPosicao("TOP");
            }
            if (rbJungler.isSelected()) {
                posicao = PosicaoServices.getPosicao("JNG");
            }
            if (rbMid.isSelected()) {
                posicao = PosicaoServices.getPosicao("MID");
            }
            if (rbAdc.isSelected()) {
                posicao = PosicaoServices.getPosicao("ADC");
            }
            if (rbSup.isSelected()) {
                posicao = PosicaoServices.getPosicao("SUP");
            }
        }
    }

    public void gravarMembroEquipa() throws MalformedURLException {
        Membroequipa m = new Membroequipa();
        m.setPais(pais);
        m.setNome(txtUsername.getText());
        m.setIdade(new BigDecimal(age));
        m.setCargo(cargo);
        m.setPosicao(posicao);
        m.setTotalassists(new BigDecimal(0));
        m.setTotaldeaths(new BigDecimal(0));
        m.setTotaljogos(new BigDecimal(0));
        m.setTotalkills(new BigDecimal(0));
        if (equipa != null) {
            m.setEquipa(equipa);
            equipa.getMembroequipas().add(m);
            EquipaServices.atualizarEstadoEquipa();
        }
        ImagesMemberServices.addToMap(m.getNome(), fileImagem.toURI().toURL().toString());
        MembroEquipaServices.criarMembroEquipa(m);
    }

    private void verificaMembroEquipa() throws InsertMembroEquipaDBException {
        List<Membroequipa> membros = MembroEquipaServices.listaAll();

        if (txtUsername.getText().isEmpty() || txtUsername.getText().contains(" ")) {
            throw new InsertMembroEquipaDBException("This username is empty or contain spaces");
        }

        //verificar se username já existe - se sim, throws
        for (Membroequipa m : membros) {
            if (m.getNome().equals(txtUsername.getText())) {
                throw new InsertMembroEquipaDBException("This username already exists");
            }
        }
        //verificar se pais ou cargo não é null
        if (pais == null) {
            throw new InsertMembroEquipaDBException("No country choosed");
        }
        //verificar se cargos estão selecionados
        if (!rbTop.isSelected() && !rbAdc.isSelected() && !rbMid.isSelected() && !rbJungler.isSelected() && !rbSup.isSelected() && !rbCoach.isSelected()) {
            throw new InsertMembroEquipaDBException("No role choosed");
        }
    }
    
    @FXML
    public void mostraBotaoChooseTeam(){
        this.info.setVisible(true);
        this.btnChooseTeam.setVisible(true);
    }
}
