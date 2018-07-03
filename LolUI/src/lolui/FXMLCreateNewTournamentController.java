/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lolui;

import lolui.exceptions.InsertTournamentDBException;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lolbll.EncontroServices;
import lolbll.EquipaServices;
import lolbll.EquipaTorneioServices;
import lolbll.HibernateBLL;
import lolbll.RegiaoServices;
import lolbll.RondaServices;
import lolbll.TorneioServices;
import loldal.model.Encontro;
import loldal.model.Equipa;
import loldal.model.Equipatorneio;
import loldal.model.Pais;
import loldal.model.Regiao;
import loldal.model.Ronda;
import loldal.model.Torneio;

/**
 * FXML Controller class
 *
 * @author vaaz
 */
public class FXMLCreateNewTournamentController implements Initializable {

    @FXML
    private ImageView imgBack;

    @FXML
    private BorderPane parentBorderPane;

    @FXML
    private ComboBox comboType;

    @FXML
    private Button btnCountry;

    @FXML
    private Button btnRegion;

    @FXML
    private Spinner<Integer> spinPrize;

    @FXML
    private RadioButton rb8Teams;

    @FXML
    private RadioButton rb16Teams;

    @FXML
    private GridPane grid8Teams;

    @FXML
    private GridPane grid16Teams;

    @FXML
    private ImageView imgCountryRegionSelected;

    @FXML
    private TextField txtFullName;

    @FXML
    private TextField txtInitials;

    @FXML
    private DatePicker dataInicio;

    private Pais pais;

    private Regiao regiao;

    private List<Equipa> listaFiltrada;

    private Set<Equipa> listaEquipasSelecionadas;

    //Grid Pane 8 Equipas
    @FXML
    private Button btnT8Equipa1;
    @FXML
    private Button btnT8Equipa2;
    @FXML
    private Button btnT8Equipa3;
    @FXML
    private Button btnT8Equipa4;
    @FXML
    private Button btnT8Equipa5;
    @FXML
    private Button btnT8Equipa6;
    @FXML
    private Button btnT8Equipa7;
    @FXML
    private Button btnT8Equipa8;

    //Grid Pane 16 Equipas
    @FXML
    private Button btnT16Equipa1;
    @FXML
    private Button btnT16Equipa2;
    @FXML
    private Button btnT16Equipa3;
    @FXML
    private Button btnT16Equipa4;
    @FXML
    private Button btnT16Equipa5;
    @FXML
    private Button btnT16Equipa6;
    @FXML
    private Button btnT16Equipa7;
    @FXML
    private Button btnT16Equipa8;
    @FXML
    private Button btnT16Equipa9;
    @FXML
    private Button btnT16Equipa10;
    @FXML
    private Button btnT16Equipa11;
    @FXML
    private Button btnT16Equipa12;
    @FXML
    private Button btnT16Equipa13;
    @FXML
    private Button btnT16Equipa14;
    @FXML
    private Button btnT16Equipa15;
    @FXML
    private Button btnT16Equipa16;

    private Equipa equipa;

    //Torneio
    private String fullName;
    private String initials;
    private Date dataInicioTorneio;
    private int prizepool;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        parentBorderPane.getStyleClass().add("borderPane");
        comboType.getItems().addAll("Country", "Region", "All");
        comboType.getSelectionModel().selectLast();
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(20000, 25000000, 20000, 20000);
        spinPrize.setValueFactory(valueFactory);
        grid8Teams.setVisible(false);
        grid16Teams.setVisible(false);
        listaEquipasSelecionadas = new HashSet<>();
    }

    @FXML
    public void closePopUp() {
        Stage stage = (Stage) this.imgBack.getScene().getWindow();
        stage.close();
    }

    public void filtraButtons() {
        if (comboType.getValue() != null) {
            if (comboType.getValue().equals("Country")) {
                btnCountry.setVisible(true);
                btnRegion.setVisible(false);
                imgCountryRegionSelected.setVisible(true);
                listaEquipasSelecionadas.clear();
                this.resetButtonTexts();
            }
            if (comboType.getValue().equals("Region")) {
                btnCountry.setVisible(false);
                btnRegion.setVisible(true);
                imgCountryRegionSelected.setVisible(true);
                listaEquipasSelecionadas.clear();
                this.resetButtonTexts();
            }
            if (comboType.getValue().equals("All")) {
                btnCountry.setVisible(false);
                btnRegion.setVisible(false);
                imgCountryRegionSelected.setVisible(false);
                listaFiltrada = EquipaServices.listaEquipasAtivas();
            }
        } else {
            listaFiltrada = EquipaServices.listaEquipasAtivas();
        }
    }

    public void filtraGridPaneSelecaoEquipas() {
        if (rb8Teams.isSelected()) {
            grid8Teams.setVisible(true);
            grid16Teams.setVisible(false);
            listaEquipasSelecionadas.clear();
            this.resetButtonTexts();
            this.filtraButtons();
        } else {
            if (rb16Teams.isSelected()) {
                grid8Teams.setVisible(false);
                grid16Teams.setVisible(true);
                listaEquipasSelecionadas.clear();
                this.resetButtonTexts();
                this.filtraButtons();
            }
        }
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
            imgCountryRegionSelected.setImage(controller.getCountryImageSelected());
            regiao = null;
            pais = controller.getPaisSelected();
            listaEquipasSelecionadas.clear();
            this.resetButtonTexts();
            listaFiltrada = EquipaServices.listaEquipasPais(pais.getSigla());
            //this.pais = controller.getPaisSelected();
            //System.out.println(this.pais.getNome());
        }
    }

    @FXML
    public void createRegionAction(MouseEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLRegionSelection.fxml"));
        Parent root = loader.load();
        FXMLRegionSelectionController controller = loader.getController();
        controller.preencheGridRegioes();
        //this.preparePopUpElements(event, controller);
        //Metodo para preencher a Janela de PopUp
        this.prepareRegionStage(root, controller);
    }

    public void prepareRegionStage(Parent root, FXMLRegionSelectionController controller) {
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
        if (controller.getRegiaoSelected() != null) {
            imgCountryRegionSelected.setImage(controller.getRegionImageSelected());
            pais = null;
            regiao = controller.getRegiaoSelected();
            listaEquipasSelecionadas.clear();
            this.resetButtonTexts();
            listaFiltrada = EquipaServices.listaEquipasRegiao(regiao.getSigla());
            //this.pais = controller.getPaisSelected();
            System.out.println(this.regiao.getDescricao());
        }
    }

    @FXML
    public void createTeamAction(MouseEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLTeamSelection.fxml"));
        Parent root = loader.load();
        FXMLTeamSelectionController controller = loader.getController();

        controller.preencheGridEquipasFiltrada(listaFiltrada);
        //Metodo para preencher a Janela de PopUp
        this.prepareTeamStage(event, root, controller);
    }

    public void prepareTeamStage(MouseEvent event, Parent root, FXMLTeamSelectionController controller) {
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
        if (controller.getEquipaSelected() != null) {
//            imgNewTeamLogoPlayer.setImage(controller.getTeamImageSelected());
            this.equipa = controller.getEquipaSelected();
            this.insereListaSelecionada(event, equipa);
//            System.out.println(this.equipa.getNome());
        }
    }

    public void insereListaSelecionada(MouseEvent event, Equipa equipa) {
        System.out.println(event.getSource());
        if (rb8Teams.isSelected()) {
            if (event.getSource() == btnT8Equipa1) {
                if (btnT8Equipa1.getText().contains("Match")) {
                    if (!listaEquipasSelecionadas.contains(equipa)) {
                        btnT8Equipa1.setText(equipa.getNome());
                        listaEquipasSelecionadas.add(equipa);
                    }
                } else {
                    if (!listaEquipasSelecionadas.contains(equipa)) {
                        removeMembroEquipaSelecionada(btnT8Equipa1.getText());
                        btnT8Equipa1.setText(equipa.getNome());
                        listaEquipasSelecionadas.add(equipa);
                    }
                }

            } else {
                if (event.getSource() == btnT8Equipa2) {
                    if (btnT8Equipa2.getText().contains("Match")) {
                        if (!listaEquipasSelecionadas.contains(equipa)) {
                            btnT8Equipa2.setText(equipa.getNome());
                            listaEquipasSelecionadas.add(equipa);
                        }
                    } else {
                        if (!listaEquipasSelecionadas.contains(equipa)) {
                            removeMembroEquipaSelecionada(btnT8Equipa2.getText());
                            btnT8Equipa2.setText(equipa.getNome());
                            listaEquipasSelecionadas.add(equipa);
                        }

                    }
                } else {
                    if (event.getSource() == btnT8Equipa3) {
                        if (btnT8Equipa3.getText().contains("Match")) {
                            if (!listaEquipasSelecionadas.contains(equipa)) {
                                btnT8Equipa3.setText(equipa.getNome());
                                listaEquipasSelecionadas.add(equipa);
                            }

                        } else {
                            if (!listaEquipasSelecionadas.contains(equipa)) {
                                removeMembroEquipaSelecionada(btnT8Equipa3.getText());
                                btnT8Equipa3.setText(equipa.getNome());
                                listaEquipasSelecionadas.add(equipa);
                            }

                        }
                    } else {
                        if (event.getSource() == btnT8Equipa4) {
                            if (btnT8Equipa4.getText().contains("Match")) {
                                if (!listaEquipasSelecionadas.contains(equipa)) {
                                    btnT8Equipa4.setText(equipa.getNome());
                                    listaEquipasSelecionadas.add(equipa);
                                }

                            } else {
                                if (!listaEquipasSelecionadas.contains(equipa)) {
                                    removeMembroEquipaSelecionada(btnT8Equipa4.getText());
                                    btnT8Equipa4.setText(equipa.getNome());
                                    listaEquipasSelecionadas.add(equipa);
                                }

                            }
                        } else {
                            if (event.getSource() == btnT8Equipa5) {
                                if (btnT8Equipa5.getText().contains("Match")) {
                                    if (!listaEquipasSelecionadas.contains(equipa)) {
                                        btnT8Equipa5.setText(equipa.getNome());
                                        listaEquipasSelecionadas.add(equipa);
                                    }

                                } else {
                                    if (!listaEquipasSelecionadas.contains(equipa)) {
                                        removeMembroEquipaSelecionada(btnT8Equipa5.getText());
                                        btnT8Equipa5.setText(equipa.getNome());
                                        listaEquipasSelecionadas.add(equipa);
                                    }

                                }
                            } else {
                                if (event.getSource() == btnT8Equipa6) {
                                    if (btnT8Equipa6.getText().contains("Match")) {
                                        if (!listaEquipasSelecionadas.contains(equipa)) {
                                            btnT8Equipa6.setText(equipa.getNome());
                                            listaEquipasSelecionadas.add(equipa);
                                        }

                                    } else {
                                        if (!listaEquipasSelecionadas.contains(equipa)) {
                                            removeMembroEquipaSelecionada(btnT8Equipa6.getText());
                                            btnT8Equipa6.setText(equipa.getNome());
                                            listaEquipasSelecionadas.add(equipa);
                                        }

                                    }
                                } else {
                                    if (event.getSource() == btnT8Equipa7) {
                                        if (btnT8Equipa7.getText().contains("Match")) {
                                            if (!listaEquipasSelecionadas.contains(equipa)) {
                                                btnT8Equipa7.setText(equipa.getNome());
                                                listaEquipasSelecionadas.add(equipa);
                                            }

                                        } else {
                                            if (!listaEquipasSelecionadas.contains(equipa)) {
                                                removeMembroEquipaSelecionada(btnT8Equipa7.getText());
                                                btnT8Equipa7.setText(equipa.getNome());
                                                listaEquipasSelecionadas.add(equipa);
                                            }

                                        }
                                    } else {
                                        if (event.getSource() == btnT8Equipa8) {
                                            if (btnT8Equipa8.getText().contains("Match")) {
                                                if (!listaEquipasSelecionadas.contains(equipa)) {
                                                    btnT8Equipa8.setText(equipa.getNome());
                                                    listaEquipasSelecionadas.add(equipa);
                                                }

                                            } else {
                                                if (!listaEquipasSelecionadas.contains(equipa)) {
                                                    removeMembroEquipaSelecionada(btnT8Equipa8.getText());
                                                    btnT8Equipa8.setText(equipa.getNome());
                                                    listaEquipasSelecionadas.add(equipa);
                                                }

                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        if (rb16Teams.isSelected()) {
            if (event.getSource() == btnT16Equipa1) {
                if (btnT16Equipa1.getText().contains("Match")) {
                    if (!listaEquipasSelecionadas.contains(equipa)) {
                        btnT16Equipa1.setText(equipa.getNome());
                        listaEquipasSelecionadas.add(equipa);
                    }
                } else {
                    if (!listaEquipasSelecionadas.contains(equipa)) {
                        removeMembroEquipaSelecionada(btnT16Equipa1.getText());
                        btnT16Equipa1.setText(equipa.getNome());
                        listaEquipasSelecionadas.add(equipa);
                    }
                }

            } else {
                if (event.getSource() == btnT16Equipa2) {
                    if (btnT16Equipa2.getText().contains("Match")) {
                        if (!listaEquipasSelecionadas.contains(equipa)) {
                            btnT16Equipa2.setText(equipa.getNome());
                            listaEquipasSelecionadas.add(equipa);
                        }
                    } else {
                        if (!listaEquipasSelecionadas.contains(equipa)) {
                            removeMembroEquipaSelecionada(btnT16Equipa2.getText());
                            btnT16Equipa2.setText(equipa.getNome());
                            listaEquipasSelecionadas.add(equipa);
                        }

                    }
                } else {
                    if (event.getSource() == btnT16Equipa3) {
                        if (btnT16Equipa3.getText().contains("Match")) {
                            if (!listaEquipasSelecionadas.contains(equipa)) {
                                btnT16Equipa3.setText(equipa.getNome());
                                listaEquipasSelecionadas.add(equipa);
                            }

                        } else {
                            if (!listaEquipasSelecionadas.contains(equipa)) {
                                removeMembroEquipaSelecionada(btnT16Equipa3.getText());
                                btnT16Equipa3.setText(equipa.getNome());
                                listaEquipasSelecionadas.add(equipa);
                            }

                        }
                    } else {
                        if (event.getSource() == btnT16Equipa4) {
                            if (btnT16Equipa4.getText().contains("Match")) {
                                if (!listaEquipasSelecionadas.contains(equipa)) {
                                    btnT16Equipa4.setText(equipa.getNome());
                                    listaEquipasSelecionadas.add(equipa);
                                }

                            } else {
                                if (!listaEquipasSelecionadas.contains(equipa)) {
                                    removeMembroEquipaSelecionada(btnT16Equipa4.getText());
                                    btnT16Equipa4.setText(equipa.getNome());
                                    listaEquipasSelecionadas.add(equipa);
                                }

                            }
                        } else {
                            if (event.getSource() == btnT16Equipa5) {
                                if (btnT16Equipa5.getText().contains("Match")) {
                                    if (!listaEquipasSelecionadas.contains(equipa)) {
                                        btnT16Equipa5.setText(equipa.getNome());
                                        listaEquipasSelecionadas.add(equipa);
                                    }

                                } else {
                                    if (!listaEquipasSelecionadas.contains(equipa)) {
                                        removeMembroEquipaSelecionada(btnT16Equipa5.getText());
                                        btnT16Equipa5.setText(equipa.getNome());
                                        listaEquipasSelecionadas.add(equipa);
                                    }

                                }
                            } else {
                                if (event.getSource() == btnT16Equipa6) {
                                    if (btnT16Equipa6.getText().contains("Match")) {
                                        if (!listaEquipasSelecionadas.contains(equipa)) {
                                            btnT16Equipa6.setText(equipa.getNome());
                                            listaEquipasSelecionadas.add(equipa);
                                        }

                                    } else {
                                        if (!listaEquipasSelecionadas.contains(equipa)) {
                                            removeMembroEquipaSelecionada(btnT16Equipa6.getText());
                                            btnT16Equipa6.setText(equipa.getNome());
                                            listaEquipasSelecionadas.add(equipa);
                                        }

                                    }
                                } else {
                                    if (event.getSource() == btnT16Equipa7) {
                                        if (btnT16Equipa7.getText().contains("Match")) {
                                            if (!listaEquipasSelecionadas.contains(equipa)) {
                                                btnT16Equipa7.setText(equipa.getNome());
                                                listaEquipasSelecionadas.add(equipa);
                                            }

                                        } else {
                                            if (!listaEquipasSelecionadas.contains(equipa)) {
                                                removeMembroEquipaSelecionada(btnT16Equipa7.getText());
                                                btnT16Equipa7.setText(equipa.getNome());
                                                listaEquipasSelecionadas.add(equipa);
                                            }

                                        }
                                    } else {
                                        if (event.getSource() == btnT16Equipa8) {
                                            if (btnT16Equipa8.getText().contains("Match")) {
                                                if (!listaEquipasSelecionadas.contains(equipa)) {
                                                    btnT16Equipa8.setText(equipa.getNome());
                                                    listaEquipasSelecionadas.add(equipa);
                                                }

                                            } else {
                                                if (!listaEquipasSelecionadas.contains(equipa)) {
                                                    removeMembroEquipaSelecionada(btnT16Equipa8.getText());
                                                    btnT16Equipa8.setText(equipa.getNome());
                                                    listaEquipasSelecionadas.add(equipa);
                                                }

                                            }
                                        } else {
                                            if (event.getSource() == btnT16Equipa9) {
                                                if (btnT16Equipa9.getText().contains("Match")) {
                                                    if (!listaEquipasSelecionadas.contains(equipa)) {
                                                        btnT16Equipa9.setText(equipa.getNome());
                                                        listaEquipasSelecionadas.add(equipa);
                                                    }

                                                } else {
                                                    if (!listaEquipasSelecionadas.contains(equipa)) {
                                                        removeMembroEquipaSelecionada(btnT16Equipa9.getText());
                                                        btnT16Equipa9.setText(equipa.getNome());
                                                        listaEquipasSelecionadas.add(equipa);
                                                    }

                                                }
                                            } else {
                                                if (event.getSource() == btnT16Equipa10) {
                                                    if (btnT16Equipa10.getText().contains("Match")) {
                                                        if (!listaEquipasSelecionadas.contains(equipa)) {
                                                            btnT16Equipa10.setText(equipa.getNome());
                                                            listaEquipasSelecionadas.add(equipa);
                                                        }

                                                    } else {
                                                        if (!listaEquipasSelecionadas.contains(equipa)) {
                                                            removeMembroEquipaSelecionada(btnT16Equipa10.getText());
                                                            btnT16Equipa10.setText(equipa.getNome());
                                                            listaEquipasSelecionadas.add(equipa);
                                                        }

                                                    }
                                                } else {
                                                    if (event.getSource() == btnT16Equipa11) {
                                                        if (btnT16Equipa11.getText().contains("Match")) {
                                                            if (!listaEquipasSelecionadas.contains(equipa)) {
                                                                btnT16Equipa11.setText(equipa.getNome());
                                                                listaEquipasSelecionadas.add(equipa);
                                                            }

                                                        } else {
                                                            if (!listaEquipasSelecionadas.contains(equipa)) {
                                                                removeMembroEquipaSelecionada(btnT16Equipa11.getText());
                                                                btnT16Equipa11.setText(equipa.getNome());
                                                                listaEquipasSelecionadas.add(equipa);
                                                            }

                                                        }
                                                    } else {
                                                        if (event.getSource() == btnT16Equipa12) {
                                                            if (btnT16Equipa12.getText().contains("Match")) {
                                                                if (!listaEquipasSelecionadas.contains(equipa)) {
                                                                    btnT16Equipa12.setText(equipa.getNome());
                                                                    listaEquipasSelecionadas.add(equipa);
                                                                }

                                                            } else {
                                                                if (!listaEquipasSelecionadas.contains(equipa)) {
                                                                    removeMembroEquipaSelecionada(btnT16Equipa12.getText());
                                                                    btnT16Equipa12.setText(equipa.getNome());
                                                                    listaEquipasSelecionadas.add(equipa);
                                                                }

                                                            }
                                                        } else {
                                                            if (event.getSource() == btnT16Equipa13) {
                                                                if (btnT16Equipa13.getText().contains("Match")) {
                                                                    if (!listaEquipasSelecionadas.contains(equipa)) {
                                                                        btnT16Equipa13.setText(equipa.getNome());
                                                                        listaEquipasSelecionadas.add(equipa);
                                                                    }

                                                                } else {
                                                                    if (!listaEquipasSelecionadas.contains(equipa)) {
                                                                        removeMembroEquipaSelecionada(btnT16Equipa13.getText());
                                                                        btnT16Equipa13.setText(equipa.getNome());
                                                                        listaEquipasSelecionadas.add(equipa);
                                                                    }

                                                                }
                                                            } else {
                                                                if (event.getSource() == btnT16Equipa14) {
                                                                    if (btnT16Equipa14.getText().contains("Match")) {
                                                                        if (!listaEquipasSelecionadas.contains(equipa)) {
                                                                            btnT16Equipa14.setText(equipa.getNome());
                                                                            listaEquipasSelecionadas.add(equipa);
                                                                        }

                                                                    } else {
                                                                        if (!listaEquipasSelecionadas.contains(equipa)) {
                                                                            removeMembroEquipaSelecionada(btnT16Equipa14.getText());
                                                                            btnT16Equipa14.setText(equipa.getNome());
                                                                            listaEquipasSelecionadas.add(equipa);
                                                                        }

                                                                    }
                                                                } else {
                                                                    if (event.getSource() == btnT16Equipa15) {
                                                                        if (btnT16Equipa15.getText().contains("Match")) {
                                                                            if (!listaEquipasSelecionadas.contains(equipa)) {
                                                                                btnT16Equipa15.setText(equipa.getNome());
                                                                                listaEquipasSelecionadas.add(equipa);
                                                                            }

                                                                        } else {
                                                                            if (!listaEquipasSelecionadas.contains(equipa)) {
                                                                                removeMembroEquipaSelecionada(btnT16Equipa15.getText());
                                                                                btnT16Equipa15.setText(equipa.getNome());
                                                                                listaEquipasSelecionadas.add(equipa);
                                                                            }

                                                                        }
                                                                    } else {
                                                                        if (event.getSource() == btnT16Equipa16) {
                                                                            if (btnT16Equipa16.getText().contains("Match")) {
                                                                                if (!listaEquipasSelecionadas.contains(equipa)) {
                                                                                    btnT16Equipa16.setText(equipa.getNome());
                                                                                    listaEquipasSelecionadas.add(equipa);
                                                                                }

                                                                            } else {
                                                                                if (!listaEquipasSelecionadas.contains(equipa)) {
                                                                                    removeMembroEquipaSelecionada(btnT16Equipa16.getText());
                                                                                    btnT16Equipa16.setText(equipa.getNome());
                                                                                    listaEquipasSelecionadas.add(equipa);
                                                                                }

                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        System.out.println(listaEquipasSelecionadas);
    }

    private void removeMembroEquipaSelecionada(String text) {
        Equipa eq = new Equipa();

        for (Equipa e : listaEquipasSelecionadas) {
            if (e.getNome().equals(text)) {
                eq = e;
            }
        }
        listaEquipasSelecionadas.remove(eq);
    }

    public void resetButtonTexts() {
        btnT8Equipa1.setText("Match 1 Blue Team");
        btnT8Equipa2.setText("Match 1 Red Team");
        btnT8Equipa3.setText("Match 2 Blue Team");
        btnT8Equipa4.setText("Match 2 Red Team");
        btnT8Equipa5.setText("Match 3 Blue Team");
        btnT8Equipa6.setText("Match 3 Red Team");
        btnT8Equipa7.setText("Match 4 Blue Team");
        btnT8Equipa8.setText("Match 4 Red Team");

        btnT16Equipa1.setText("Match 1 Blue Team");
        btnT16Equipa2.setText("Match 1 Red Team");
        btnT16Equipa3.setText("Match 2 Blue Team");
        btnT16Equipa4.setText("Match 2 Red Team");
        btnT16Equipa5.setText("Match 3 Blue Team");
        btnT16Equipa6.setText("Match 3 Red Team");
        btnT16Equipa7.setText("Match 4 Blue Team");
        btnT16Equipa8.setText("Match 4 Red Team");
        btnT16Equipa9.setText("Match 5 Blue Team");
        btnT16Equipa10.setText("Match 5 Red Team");
        btnT16Equipa11.setText("Match 6 Blue Team");
        btnT16Equipa12.setText("Match 6 Red Team");
        btnT16Equipa13.setText("Match 7 Blue Team");
        btnT16Equipa14.setText("Match 7 Red Team");
        btnT16Equipa15.setText("Match 8 Blue Team");
        btnT16Equipa16.setText("Match 8 Red Team");
    }

    public void verificaTorneio() throws InsertTournamentDBException {
        //verificar nome do torneio (se já existe e tamanho)
        //descrição igual ao nome
        //verificar initials (tamanho)
        //verificar se a lista com equipas selecionadas contêm 8 ou 16 elementos consoante o RadioButton
        //verificar se a data nao esta vazia

        if (txtFullName.getText().isEmpty()) {
            throw new InsertTournamentDBException("Please add Tournament name");
        }

        if (txtFullName.getText().length() > 50) {
            throw new InsertTournamentDBException("Tournament Name too long, maximum 50 characters");
        }

        for (Torneio t : TorneioServices.listaTorneios()) {
            if (t.getNome().equals(txtFullName.getText())) {
                throw new InsertTournamentDBException("Tournament Name already exists");
            }
        }

        if (txtInitials.getText().isEmpty()) {
            throw new InsertTournamentDBException("Please add Tournament initials");
        }

        if (txtInitials.getText().length() > 3 || txtInitials.getText().contains(" ")) {
            throw new InsertTournamentDBException("Tournament initials can't have spaces and/or are too long, maximum 3 characters");
        }

        if (rb8Teams.isSelected() && listaEquipasSelecionadas.size() != 8) {
            throw new InsertTournamentDBException("Please fill all the team's list");
        }

        if (rb16Teams.isSelected() && listaEquipasSelecionadas.size() != 16) {
            throw new InsertTournamentDBException("Please fill all the team's list");
        }

        if (dataInicio.getValue() == null) {
            throw new InsertTournamentDBException("Please add a Date");
        }
        
        if (!rb8Teams.isSelected() && !rb16Teams.isSelected()){
            throw new InsertTournamentDBException("Please select the number of teams");
        }
        
        if(listaEquipasSelecionadas.isEmpty()){
            throw new InsertTournamentDBException("Please select the number of teams");
        }
    }

    public void getUserInput() {
        fullName = txtFullName.getText();
        initials = txtInitials.getText();
        dataInicioTorneio = java.sql.Date.valueOf(dataInicio.getValue());
        prizepool = spinPrize.getValue();
    }

    public void gravarTorneio() {
        //adicionar tudo para o Torneio
        Torneio t = new Torneio();
        t.setNome(fullName);
        t.setDescricao(fullName);
        t.setSigla(initials);
        t.setDatafim(dataInicioTorneio);
        t.setPremio(new BigDecimal(prizepool));
        t.setEstado("CRIADO");
        if (pais != null) {
            t.setRegiao(pais.getRegiao());
            t.setTipo(new BigDecimal(3));
            pais.getRegiao().getTorneios().add(t);
            RegiaoServices.saveRegiao(pais.getRegiao());
        }
        if (regiao != null) {
            t.setRegiao(regiao);
            t.setTipo(new BigDecimal(2));
            regiao.getTorneios().add(t);
            RegiaoServices.saveRegiao(regiao);
        }
        if(regiao==null && pais == null){
            t.setTipo(BigDecimal.ONE);
        }
        
        //criar a ronda
        Ronda r = new Ronda();
        r.setTorneio(t);
        r.setEstado("CRIADO");
        r.setNronda(BigDecimal.ONE);
        

        if (rb8Teams.isSelected()) {
            r.setNumequipas(new BigDecimal(8));
            r.setDescricao("Quarter finals");
            t.setNumrondas(new BigDecimal(3));
            t.setRondaatual(BigDecimal.ONE);
            //Preparar os encontros iniciais e adicionar à ronda?
            Encontro encontro1 = new Encontro();
            Encontro encontro2 = new Encontro();
            Encontro encontro3 = new Encontro();
            Encontro encontro4 = new Encontro();

            encontro1.setRonda(r);
            encontro2.setRonda(r);
            encontro3.setRonda(r);
            encontro4.setRonda(r);

            encontro1.setEquipaByEquipa1(this.getEquipaButton(btnT8Equipa1));
            encontro1.setEquipaByEquipa2(this.getEquipaButton(btnT8Equipa2));
            encontro2.setEquipaByEquipa1(this.getEquipaButton(btnT8Equipa3));
            encontro2.setEquipaByEquipa2(this.getEquipaButton(btnT8Equipa4));
            encontro3.setEquipaByEquipa1(this.getEquipaButton(btnT8Equipa5));
            encontro3.setEquipaByEquipa2(this.getEquipaButton(btnT8Equipa6));
            encontro4.setEquipaByEquipa1(this.getEquipaButton(btnT8Equipa7));
            encontro4.setEquipaByEquipa2(this.getEquipaButton(btnT8Equipa8));
            
            //Criar valores default
            encontro1.setData(dataInicioTorneio);
            encontro1.setEstado("CRIADO");
            encontro1.setTotaljogos(new BigDecimal(5));
            encontro1.setVitoriaequipa1(BigDecimal.ZERO);
            encontro1.setVitoriaequipa2(BigDecimal.ZERO);
            encontro2.setData(dataInicioTorneio);
            encontro2.setEstado("CRIADO");
            encontro2.setTotaljogos(new BigDecimal(5));
            encontro2.setVitoriaequipa1(BigDecimal.ZERO);
            encontro2.setVitoriaequipa2(BigDecimal.ZERO);
            encontro3.setData(dataInicioTorneio);
            encontro3.setEstado("CRIADO");
            encontro3.setTotaljogos(new BigDecimal(5));
            encontro3.setVitoriaequipa1(BigDecimal.ZERO);
            encontro3.setVitoriaequipa2(BigDecimal.ZERO);
            encontro4.setData(dataInicioTorneio);
            encontro4.setEstado("CRIADO");
            encontro4.setTotaljogos(new BigDecimal(5));
            encontro4.setVitoriaequipa1(BigDecimal.ZERO);
            encontro4.setVitoriaequipa2(BigDecimal.ZERO);
            
            r.getEncontros().add(encontro1);
            r.getEncontros().add(encontro2);
            r.getEncontros().add(encontro3);
            r.getEncontros().add(encontro4);

            t.getRondas().add(r);

            //gravar encontros, rondas e torneio
            TorneioServices.saveTorneio(t);
            RondaServices.saveRonda(r);
            EncontroServices.saveEncontro(encontro1);
            EncontroServices.saveEncontro(encontro2);
            EncontroServices.saveEncontro(encontro3);
            EncontroServices.saveEncontro(encontro4);
        }

        if (rb16Teams.isSelected()) {
            r.setNumequipas(new BigDecimal(16));
            t.setNumrondas(new BigDecimal(4));
            r.setDescricao("Round of 16");
            t.setRondaatual(BigDecimal.ONE);
            //Preparar os encontros iniciais e adicionar à ronda?
            Encontro encontro1 = new Encontro();
            Encontro encontro2 = new Encontro();
            Encontro encontro3 = new Encontro();
            Encontro encontro4 = new Encontro();
            Encontro encontro5 = new Encontro();
            Encontro encontro6 = new Encontro();
            Encontro encontro7 = new Encontro();
            Encontro encontro8 = new Encontro();

            encontro1.setRonda(r);
            encontro2.setRonda(r);
            encontro3.setRonda(r);
            encontro4.setRonda(r);
            encontro5.setRonda(r);
            encontro6.setRonda(r);
            encontro7.setRonda(r);
            encontro8.setRonda(r);

            encontro1.setEquipaByEquipa1(this.getEquipaButton(btnT16Equipa1));
            encontro1.setEquipaByEquipa2(this.getEquipaButton(btnT16Equipa2));
            encontro2.setEquipaByEquipa1(this.getEquipaButton(btnT16Equipa3));
            encontro2.setEquipaByEquipa2(this.getEquipaButton(btnT16Equipa4));
            encontro3.setEquipaByEquipa1(this.getEquipaButton(btnT16Equipa5));
            encontro3.setEquipaByEquipa2(this.getEquipaButton(btnT16Equipa6));
            encontro4.setEquipaByEquipa1(this.getEquipaButton(btnT16Equipa7));
            encontro4.setEquipaByEquipa2(this.getEquipaButton(btnT16Equipa8));
            encontro5.setEquipaByEquipa1(this.getEquipaButton(btnT16Equipa9));
            encontro5.setEquipaByEquipa2(this.getEquipaButton(btnT16Equipa10));
            encontro6.setEquipaByEquipa1(this.getEquipaButton(btnT16Equipa11));
            encontro6.setEquipaByEquipa2(this.getEquipaButton(btnT16Equipa12));
            encontro7.setEquipaByEquipa1(this.getEquipaButton(btnT16Equipa13));
            encontro7.setEquipaByEquipa2(this.getEquipaButton(btnT16Equipa14));
            encontro8.setEquipaByEquipa1(this.getEquipaButton(btnT16Equipa15));
            encontro8.setEquipaByEquipa2(this.getEquipaButton(btnT16Equipa16));
            
            //Criar valores default
            encontro1.setData(dataInicioTorneio);
            encontro1.setEstado("CRIADO");
            encontro1.setTotaljogos(new BigDecimal(5));
            encontro1.setVitoriaequipa1(BigDecimal.ZERO);
            encontro1.setVitoriaequipa2(BigDecimal.ZERO);
            encontro2.setData(dataInicioTorneio);
            encontro2.setEstado("CRIADO");
            encontro2.setTotaljogos(new BigDecimal(5));
            encontro2.setVitoriaequipa1(BigDecimal.ZERO);
            encontro2.setVitoriaequipa2(BigDecimal.ZERO);
            encontro3.setData(dataInicioTorneio);
            encontro3.setEstado("CRIADO");
            encontro3.setTotaljogos(new BigDecimal(5));
            encontro3.setVitoriaequipa1(BigDecimal.ZERO);
            encontro3.setVitoriaequipa2(BigDecimal.ZERO);
            encontro4.setData(dataInicioTorneio);
            encontro4.setEstado("CRIADO");
            encontro4.setTotaljogos(new BigDecimal(5));
            encontro4.setVitoriaequipa1(BigDecimal.ZERO);
            encontro4.setVitoriaequipa2(BigDecimal.ZERO);
            encontro5.setData(dataInicioTorneio);
            encontro5.setEstado("CRIADO");
            encontro5.setTotaljogos(new BigDecimal(5));
            encontro5.setVitoriaequipa1(BigDecimal.ZERO);
            encontro5.setVitoriaequipa2(BigDecimal.ZERO);
            encontro6.setData(dataInicioTorneio);
            encontro6.setEstado("CRIADO");
            encontro6.setTotaljogos(new BigDecimal(5));
            encontro6.setVitoriaequipa1(BigDecimal.ZERO);
            encontro6.setVitoriaequipa2(BigDecimal.ZERO);
            encontro7.setData(dataInicioTorneio);
            encontro7.setEstado("CRIADO");
            encontro7.setTotaljogos(new BigDecimal(5));
            encontro7.setVitoriaequipa1(BigDecimal.ZERO);
            encontro7.setVitoriaequipa2(BigDecimal.ZERO);
            encontro8.setData(dataInicioTorneio);
            encontro8.setEstado("CRIADO");
            encontro8.setTotaljogos(new BigDecimal(5));
            encontro8.setVitoriaequipa1(BigDecimal.ZERO);
            encontro8.setVitoriaequipa2(BigDecimal.ZERO);

            r.getEncontros().add(encontro1);
            r.getEncontros().add(encontro2);
            r.getEncontros().add(encontro3);
            r.getEncontros().add(encontro4);
            r.getEncontros().add(encontro5);
            r.getEncontros().add(encontro6);
            r.getEncontros().add(encontro7);
            r.getEncontros().add(encontro8);

            t.getRondas().add(r);

            //gravar encontros, rondas e torneio
            TorneioServices.saveTorneio(t);
            RondaServices.saveRonda(r);
            EncontroServices.saveEncontro(encontro1);
            EncontroServices.saveEncontro(encontro2);
            EncontroServices.saveEncontro(encontro3);
            EncontroServices.saveEncontro(encontro4);
            EncontroServices.saveEncontro(encontro5);
            EncontroServices.saveEncontro(encontro6);
            EncontroServices.saveEncontro(encontro7);
            EncontroServices.saveEncontro(encontro8);
        }
            //criar EquipasTorneio e adicionar ao Torneio e à Equipa esse equipatorneio
            for (Equipa eq : listaEquipasSelecionadas) {
                Equipatorneio et = new Equipatorneio();
                et.setEquipa(eq);
                et.setTorneio(t);
                eq.getEquipatorneios().add(et);
                t.getEquipatorneios().add(et);
                eq.getTorneios().add(t);
                et.setClassificacao("Unassigned");
                EquipaTorneioServices.saveEquipaTorneio(et);
                EquipaServices.saveEquipa(eq);
            }

    }
    
    public void saveOnClick(){
        try {
            this.verificaTorneio();
            this.getUserInput();
            this.gravarTorneio();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Operation Successfull");
            alert.setContentText("Your new member was created!");
            alert.showAndWait();
            HibernateBLL.clearCache();
            this.closePopUp();
        } catch (InsertTournamentDBException ex) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Something went wrong.");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }

    public Equipa getEquipaButton(Button btn) {
        Equipa eq = new Equipa();
        for (Equipa e : listaEquipasSelecionadas) {
            if (e.getNome().equals(btn.getText())) {
                eq = e;
            }
        }
        return eq;
    }
}
