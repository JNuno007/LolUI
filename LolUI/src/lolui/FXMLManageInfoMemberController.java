/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lolui;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
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
import lolbll.HibernateBLL;
import lolbll.ImagesMemberServices;
import lolbll.ImagesTeamServices;
import lolbll.MembroEquipaServices;
import lolbll.PosicaoServices;
import loldal.model.Equipa;
import loldal.model.Membroequipa;
import loldal.model.Pais;
import loldal.model.Posicao;
import lolui.exceptions.InsertMembroEquipaDBException;

/**
 * FXML Controller class
 *
 * @author vaaz
 */
public class FXMLManageInfoMemberController implements Initializable {

    @FXML
    private ImageView imgBack;

    @FXML
    private BorderPane parentBorderPane;
    
    @FXML
    private ListView listaMembros;
    
    @FXML
    private ComboBox comboCargo;
    
    @FXML
    private ComboBox comboComSemEquipa;
    
    @FXML
    private TextField searchBar;
    
    //Current Information
    
    @FXML private ImageView imgCurrentPicPlayer;
    @FXML private ImageView imgCurrentCountryPlayer;
    @FXML private ImageView imgCurrentPositionPlayer;
    @FXML private ImageView imgCurrentTeamLogoPlayer;
    @FXML private Label lblCurrentNamePlayer;
    @FXML private Label lblCurrentAgePlayer;
    
    //New Information
    @FXML private ImageView imgNewCountryPlayer;
    @FXML private ImageView imgNewPicPlayer;
    @FXML private ImageView imgNewTeamLogoPlayer;
    @FXML private TextField txtNewNamePlayer;
    @FXML private Spinner<Integer> spinnerNewAge;
    
    //Variáveis para o Membro
    
    private Pais pais;
    private Equipa equipa;
    private String nome;
    private BigDecimal age;
    private String cargo;
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
    
    private List<Membroequipa> listaFiltrada;
    
    private ObservableList<Membroequipa> membrosEquipaObs;
    
    private Membroequipa me;
    
    @FXML
    private Button btnSelectImage;

    private FileChooser fileChooser;

    private File fileImagem;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(15, 99, 15);
        spinnerNewAge.setValueFactory(valueFactory);
        parentBorderPane.getStyleClass().add("borderPane");
        this.preencherListView();
        this.preencherComboBoxes();
        this.atribuirElementos();
        this.pesquisarNaLista();
        
        btnSelectImage.setOnAction(
                new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent e) {
                fileChooser = new FileChooser();
                setExtFilters(fileChooser);
                fileImagem = fileChooser.showOpenDialog((Stage) txtNewNamePlayer.getScene().getWindow());
                if (fileImagem != null) {
                    imgNewPicPlayer.setImage(new Image(fileImagem.toURI().toString()));
                }
            }
        });
    }
    
    private void setExtFilters(FileChooser chooser) {
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
    }
    
    public void pesquisarNaLista() {
        searchBar.setOnKeyReleased((event) -> {
            List<Membroequipa> temp = new ArrayList<>();
            String texto = searchBar.getText();
            if (texto.isEmpty()) {
                membrosEquipaObs = FXCollections.observableArrayList(listaFiltrada);
                membrosEquipaObs.sort(Comparator.comparing(m -> m.getNome().toLowerCase()));
                this.listaMembros.setItems(membrosEquipaObs);
                temp.clear();
            } else {
                for (Membroequipa me : listaFiltrada) {
                    if (me.getNome().toLowerCase().contains(texto.toLowerCase())) {
                        temp.add(me);
                    }
                }
                membrosEquipaObs = FXCollections.observableArrayList(temp);
                membrosEquipaObs.sort(Comparator.comparing(m -> m.getNome().toLowerCase()));
                this.listaMembros.setItems(membrosEquipaObs);
            }

        });
    }
    
    private void gravarImagemDir() {
        try {
            File file = new File(".\\src\\lolui\\pics\\players\\" + txtNewNamePlayer.getText() + ".png");
            ImageIO.write(SwingFXUtils.fromFXImage(imgNewPicPlayer.getImage(), null), "png", file);
            ImagesMemberServices.addToMap(me.getNome(), file.toURI().toURL().toString());
        } catch (IOException ex) {
            Logger.getLogger(FXMLCreateNewMemberController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
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
        stage.showAndWait();
        if (controller.getPaisSelected() != null) {
            imgNewCountryPlayer.setImage(controller.getCountryImageSelected());
            this.pais = controller.getPaisSelected();
            System.out.println(this.pais.getNome());
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
    
    public void preencherListView(){
        listaFiltrada = MembroEquipaServices.listaAll();
        Comparator<Membroequipa> comparator = Comparator.comparing(m -> m.getNome().toLowerCase());
        Stream<Membroequipa> membroStream = listaFiltrada.stream().sorted(comparator);
        List<Membroequipa> listaOrdenada = membroStream.collect(Collectors.toList());
        membrosEquipaObs = FXCollections.observableArrayList(listaOrdenada);
        this.listaMembros.setItems(membrosEquipaObs);
    }
    
    public void preencherComboBoxes(){
        comboCargo.getItems().addAll(
          "Player", "Coach", "All"      
        );
        
        comboComSemEquipa.getItems().addAll(
          "In a Team", "Free Agent", "All"
        );
    }
    
    @FXML
    public void filtrarListaComboBoxes(){
        
        if(comboCargo.getValue()!=null && comboComSemEquipa.getValue()==null){
            if(comboCargo.getValue().equals("Player") && comboComSemEquipa.getValue()==null){
                listaFiltrada = this.listPlayerAll();
                this.preencherListViewFiltrada();
            }
            if(comboCargo.getValue().equals("Coach") && comboComSemEquipa.getValue()==null){
                listaFiltrada = this.listCoachAll();
                this.preencherListViewFiltrada();
            }
            if(comboCargo.getValue().equals("All") && comboComSemEquipa.getValue()==null){
                this.preencherListView();
            }
        }else if(comboCargo.getValue()==null && comboComSemEquipa.getValue()!=null){
            if(comboCargo.getValue()==null && comboComSemEquipa.getValue().equals("In a Team")){
                listaFiltrada = this.listAllInaTeam();
                this.preencherListViewFiltrada();
            }
            if(comboCargo.getValue()==null && comboComSemEquipa.getValue().equals("Free Agent")){
                listaFiltrada = this.listAllFreeAgent();
                this.preencherListViewFiltrada();
            }
            if(comboCargo.getValue()==null && comboComSemEquipa.getValue().equals("All")){
                this.preencherListView();
            }
        }else{
            if(comboCargo.getValue().equals("Player") && comboComSemEquipa.getValue().equals("In a Team")){
                listaFiltrada = this.listPlayerInaTeam();
                this.preencherListViewFiltrada();
            }
            if(comboCargo.getValue().equals("Player") && comboComSemEquipa.getValue().equals("Free Agent")){
                listaFiltrada = this.listPlayerFreeAgent();
                this.preencherListViewFiltrada();
            }
            if(comboCargo.getValue().equals("Player") && comboComSemEquipa.getValue().equals("All")){
                listaFiltrada = this.listPlayerAll();
                this.preencherListViewFiltrada();
            }
            if(comboCargo.getValue().equals("Coach") && comboComSemEquipa.getValue().equals("In a Team")){
                listaFiltrada = this.listCoachInaTeam();
                this.preencherListViewFiltrada();
            }
            if(comboCargo.getValue().equals("Coach") && comboComSemEquipa.getValue().equals("Free Agent")){
                listaFiltrada = this.listCoachFreeAgent();
                this.preencherListViewFiltrada();
            }
            if(comboCargo.getValue().equals("Coach") && comboComSemEquipa.getValue().equals("All")){
                listaFiltrada = this.listCoachAll();
                this.preencherListViewFiltrada();
            }
            if(comboCargo.getValue().equals("All") && comboComSemEquipa.getValue().equals("In a Team")){
                listaFiltrada = this.listAllInaTeam();
                this.preencherListViewFiltrada();
            }
            if(comboCargo.getValue().equals("All") && comboComSemEquipa.getValue().equals("Free Agent")){
                listaFiltrada = this.listAllFreeAgent();
                this.preencherListViewFiltrada();
            }
            if(comboCargo.getValue().equals("All") && comboComSemEquipa.getValue().equals("All")){
                this.preencherListView();
            }
        }       
    }
    
    public void atribuirElementos(){
        this.listaMembros.getSelectionModel().selectedItemProperty().addListener((observable) -> {
            me = (Membroequipa) listaMembros.getSelectionModel().getSelectedItem();
            pais = null;
            equipa = null;
            if(me!=null){
                //métodos
                this.atribuirCurrentPic(me);
                this.atribuirCurrentPaisPlayer(me);
                this.atribuirCurrentTeamlogoPlayer(me);
                this.atribuirCurrentImagemPosicao(me);
                this.atribuirCurrentNomePlayer(me);
                this.atribuirCurrentAgePlayer(me);
                this.atribuirNewNomePlayer(me);
                this.atribuirNewAgePlayer(me);
                this.atribuirCurrentPosicaoPlayer(me);
            }
        });
    }
    
    public void atribuirCurrentPic(Membroequipa m){
        
        if(ImagesMemberServices.existsOnMap(m.getNome())){
            imgCurrentPicPlayer.setImage(new Image(ImagesMemberServices.getOriginalPath(m.getNome())));
            imgNewPicPlayer.setImage(new Image(ImagesMemberServices.getOriginalPath(m.getNome())));
        }else{
            if (getClass().getResourceAsStream("pics/players/" + m.getNome() + ".png") != null) {
                imgCurrentPicPlayer.setImage(new Image(getClass().getResourceAsStream("pics/players/" + m.getNome() + ".png")));
                imgNewPicPlayer.setImage(new Image(getClass().getResourceAsStream("pics/players/" + m.getNome() + ".png")));
            } else {
                imgCurrentPicPlayer.setImage(new Image(getClass().getResourceAsStream("pics/players/unknown.png")));
            }
        }
    }
    
    public void atribuirCurrentPaisPlayer(Membroequipa m) {
        if (getClass().getResourceAsStream("pics/countries/" + m.getPais().getSigla().toLowerCase() + ".png") != null) {
            imgCurrentCountryPlayer.setImage(new Image(getClass().getResourceAsStream("pics/countries/" + m.getPais().getSigla().toLowerCase() + ".png")));
            imgNewCountryPlayer.setImage(new Image(getClass().getResourceAsStream("pics/countries/" + m.getPais().getSigla().toLowerCase() + ".png")));
        } else {
            imgCurrentCountryPlayer.setImage(new Image(getClass().getResourceAsStream("pics/countries/unknown.png")));
        }
    }
    
    public void atribuirCurrentTeamlogoPlayer(Membroequipa m) {
        
        if(ImagesTeamServices.existsOnMap(m.getEquipa().getNome())){
            this.imgCurrentTeamLogoPlayer.setImage(new Image(ImagesTeamServices.getOriginalPath(m.getEquipa().getNome())));
            imgNewTeamLogoPlayer.setImage(new Image(ImagesTeamServices.getOriginalPath(m.getEquipa().getNome())));
        }else{
            if (m.getEquipa() != null && getClass().getResourceAsStream("pics/teams/" + m.getEquipa().getSigla().toLowerCase() + ".png") != null) {
                this.imgCurrentTeamLogoPlayer.setImage(new Image(getClass().getResourceAsStream("pics/teams/" + m.getEquipa().getSigla().toLowerCase() + ".png")));
                imgNewTeamLogoPlayer.setImage(new Image(getClass().getResourceAsStream("pics/teams/" + m.getEquipa().getSigla().toLowerCase() + ".png")));
            } else {
                this.imgCurrentTeamLogoPlayer.setImage(new Image(getClass().getResourceAsStream("pics/teams/unknown.png")));
            }
        }
    }
    
    public void atribuirCurrentPosicaoPlayer(Membroequipa m){
        
        if(m.getPosicao()==null){
            rbCoach.setSelected(true);
        }else{
            if(m.getPosicao().getSigla().equals("TOP")){
                rbTop.setSelected(true);
            }
            if(m.getPosicao().getSigla().equals("JNG")){
                rbJungler.setSelected(true);
            }
            if(m.getPosicao().getSigla().equals("MID")){
                rbMid.setSelected(true);
            }
            if(m.getPosicao().getSigla().equals("ADC")){
                rbAdc.setSelected(true);
            }
            if(m.getPosicao().getSigla().equals("SUP")){
                rbSup.setSelected(true);
            }
        }
    }
    
    public void atribuirCurrentImagemPosicao(Membroequipa m) {
        if(m.getPosicao()!=null){
            this.imgCurrentPositionPlayer.setImage(new Image(getClass().getResourceAsStream("pics/positions/" + m.getPosicao().toString().toLowerCase() + ".png")));
        }else{
            this.imgCurrentPositionPlayer.setImage(new Image(getClass().getResourceAsStream("pics/teams/unknown.png")));
        }
    }
    
    public void atribuirCurrentNomePlayer(Membroequipa me) {
        lblCurrentNamePlayer.setText(me.getNome());
    }

    public void atribuirCurrentAgePlayer(Membroequipa me) {
        this.lblCurrentAgePlayer.setText(me.getIdade() + " years old ");
    }
    
    public void atribuirNewNomePlayer(Membroequipa me){
        this.txtNewNamePlayer.setText(me.getNome());
    }
    
    public void atribuirNewAgePlayer(Membroequipa me){
        this.spinnerNewAge.getValueFactory().setValue(me.getIdade().intValue());
    }
    
    public void onRadioButtonClick(){
        equipa = null;
        this.imgNewTeamLogoPlayer.setImage(new Image(getClass().getResourceAsStream("pics/teams/unknown.png")));
    }
    
    public List<Membroequipa> listPlayerInaTeam(){
        List<Membroequipa> list = new ArrayList<>();
        
        for(Membroequipa me: MembroEquipaServices.listaAll()){
            if(me.getPosicao()!=null && me.getEquipa()!=null){
                list.add(me);
            }
        }
        return list;
    }
    
    public List<Membroequipa> listPlayerFreeAgent(){
        List<Membroequipa> list = new ArrayList<>();
        
        for(Membroequipa me: MembroEquipaServices.listaAll()){
            if(me.getPosicao()!=null && me.getEquipa()==null){
                list.add(me);
            }
        }
        return list;
    }
    
    public List<Membroequipa> listPlayerAll(){
        List<Membroequipa> list = new ArrayList<>();
        
        for(Membroequipa me: MembroEquipaServices.listaAll()){
            if(me.getPosicao()!=null){
                list.add(me);
            }
        }
        return list;
    }
    
    public List<Membroequipa> listCoachInaTeam(){
        List<Membroequipa> list = new ArrayList<>();
        
        for(Membroequipa me: MembroEquipaServices.listaAll()){
            if(me.getPosicao()==null && me.getEquipa()!=null){
                list.add(me);
            }
        }
        return list;
    }
    
    public List<Membroequipa> listCoachFreeAgent(){
        List<Membroequipa> list = new ArrayList<>();
        
        for(Membroequipa me: MembroEquipaServices.listaAll()){
            if(me.getPosicao()==null && me.getEquipa()==null){
                list.add(me);
            }
        }
        return list;
    }
    
    public List<Membroequipa> listCoachAll(){
        List<Membroequipa> list = new ArrayList<>();
        
        for(Membroequipa me: MembroEquipaServices.listaAll()){
            if(me.getPosicao()==null){
                list.add(me);
            }
        }
        return list;
    }
    
    public List<Membroequipa> listAllInaTeam(){
        List<Membroequipa> list = new ArrayList<>();
        
        for(Membroequipa me: MembroEquipaServices.listaAll()){
            if(me.getEquipa()!=null){
                list.add(me);
            }
        }
        return list;
    }
    
    public List<Membroequipa> listAllFreeAgent(){
        List<Membroequipa> list = new ArrayList<>();
        
        for(Membroequipa me: MembroEquipaServices.listaAll()){
            if(me.getEquipa()==null){
                list.add(me);
            }
        }
        return list;
    }
    
    public void preencherListViewFiltrada(){
        Comparator<Membroequipa> comparator = Comparator.comparing(m -> m.getNome());
        Stream<Membroequipa> membroStream = listaFiltrada.stream().sorted(comparator);
        List<Membroequipa> listaOrdenada = membroStream.collect(Collectors.toList());
        membrosEquipaObs = FXCollections.observableArrayList(listaOrdenada);
        this.listaMembros.setItems(membrosEquipaObs);
    }
    
    private void verificaMembroEquipa() throws InsertMembroEquipaDBException {
        List<Membroequipa> membros = MembroEquipaServices.listaAll();

        if (txtNewNamePlayer.getText().isEmpty() || txtNewNamePlayer.getText().contains(" ")) {
            throw new InsertMembroEquipaDBException("This username is empty or contain spaces");
        }

        //verificar se username já existe - se sim, throws
        for (Membroequipa m : membros) {
            if (m.getNome().equals(txtNewNamePlayer.getText()) && !m.equals(me)) {
                throw new InsertMembroEquipaDBException("This username already exists");
            }
        }
        
        //verificar se cargos estão selecionados
        if (!rbTop.isSelected() && !rbAdc.isSelected() && !rbMid.isSelected() && !rbJungler.isSelected() && !rbSup.isSelected() && !rbCoach.isSelected()) {
            throw new InsertMembroEquipaDBException("No role choosed");
        }
    }
    
    public void getUserInput() {
        nome = txtNewNamePlayer.getText();
        age = new BigDecimal(spinnerNewAge.getValue());
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
        if (pais == null) {
            pais = me.getPais();
        }
        
        if(equipa == null){
            if(me.getEquipa()!=null){
                 equipa = me.getEquipa();
            }
        }else{
            if(me.getEquipa()!=null){
                me.getEquipa().getMembroequipas().remove(me);
                EquipaServices.saveEquipa(me.getEquipa());
                equipa.getMembroequipas().add(me);
                EquipaServices.saveEquipa(equipa);
                EquipaServices.atualizarEstadoEquipa();
            }else{
                equipa.getMembroequipas().add(me);
                EquipaServices.saveEquipa(equipa);
                EquipaServices.atualizarEstadoEquipa();
            }
        }
    }
    
    public void gravarMembroEquipa() throws MalformedURLException, IOException {
        me.setPais(pais);
        me.setNome(nome);
        me.setIdade(age);
        me.setCargo(cargo);
        me.setPosicao(posicao);
        if (equipa != null) {
            me.setEquipa(equipa);
        }
        MembroEquipaServices.updateMembroEquipa(me);
    }
    
    @FXML
    public void saveOnClick() throws MalformedURLException, IOException {
        this.save();
        HibernateBLL.clearCache();
    }
    
    public void save() throws MalformedURLException, IOException {
        try {
            this.verificaMembroEquipa();
            this.getUserInput();
            this.gravarMembroEquipa();
            this.gravarImagemDir();
            EquipaServices.atualizarEstadoEquipa();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Operation Successfull");
            alert.setContentText("Your new member was created!");
            alert.showAndWait();
            this.closePopUp();
        } catch (InsertMembroEquipaDBException e) {
            //Dialog
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Something went wrong.");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
    
    
}
