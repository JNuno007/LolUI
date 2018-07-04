/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lolui;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lolbll.ImagesTeamServices;
import loldal.model.Build;
import loldal.model.Encontro;
import loldal.model.Equipa;
import loldal.model.Estatisticasmembrojogo;
import loldal.model.Jogo;
import loldal.model.Membroequipa;
import loldal.model.Runaescolhida;
import loldal.model.Spellescolhido;

/**
 * FXML Controller class
 *
 * @author joaoc
 */
public class FXMLPopUpTeamController implements Initializable {

    // -- LOGOS DAS EQUIPAS
    @FXML
    private ImageView imgLogoEquipa1;
    @FXML
    private ImageView imgLogoEquipa2;
    
    // -- ImageViews das Equipas que ganharam os jogos
    
    @FXML private ImageView imgWinnerGame1;
    @FXML private ImageView imgWinnerGame2;
    @FXML private ImageView imgWinnerGame3;
    @FXML private ImageView imgWinnerGame4;
    @FXML private ImageView imgWinnerGame5;

    // -- Resultados das Equipas no Encontro
    @FXML
    private Label lblWinEquipa1;
    @FXML
    private Label lblWinEquipa2;

    // -- Siglas das Equipas
    @FXML
    private Label lblSiglaEquipa1;
    @FXML
    private Label lblSiglaEquipa2;

    // -- Nomes dos membros de equipa e posição
    //--Equipa 1
    @FXML
    private Label lblEquipa1NomeTop;
    @FXML
    private Label lblEquipa1NomeJungler;
    @FXML
    private Label lblEquipa1NomeMid;
    @FXML
    private Label lblEquipa1NomeAdc;
    @FXML
    private Label lblEquipa1NomeSup;
    //--Equipa 2
    @FXML
    private Label lblEquipa2NomeTop;
    @FXML
    private Label lblEquipa2NomeJungler;
    @FXML
    private Label lblEquipa2NomeMid;
    @FXML
    private Label lblEquipa2NomeAdc;
    @FXML
    private Label lblEquipa2NomeSup;

    // -- Champions dos membros de equipa 1
    @FXML
    private ImageView imgEquipa1TopChamp;
    @FXML
    private ImageView imgEquipa1JunglerChamp;
    @FXML
    private ImageView imgEquipa1MidChamp;
    @FXML
    private ImageView imgEquipa1AdcChamp;
    @FXML
    private ImageView imgEquipa1SupChamp;

    // -- Champions dos membros de equipa 2
    @FXML
    private ImageView imgEquipa2TopChamp;
    @FXML
    private ImageView imgEquipa2JunglerChamp;
    @FXML
    private ImageView imgEquipa2MidChamp;
    @FXML
    private ImageView imgEquipa2AdcChamp;
    @FXML
    private ImageView imgEquipa2SupChamp;

    // -- CS dos membros da equipa 1
    @FXML
    private Label lblCSEquipa1Top;
    @FXML
    private Label lblCSEquipa1Jungler;
    @FXML
    private Label lblCSEquipa1Mid;
    @FXML
    private Label lblCSEquipa1Adc;
    @FXML
    private Label lblCSEquipa1Sup;

    // -- CS dos membros da equipa 2
    @FXML
    private Label lblCSEquipa2Top;
    @FXML
    private Label lblCSEquipa2Jungler;
    @FXML
    private Label lblCSEquipa2Mid;
    @FXML
    private Label lblCSEquipa2Adc;
    @FXML
    private Label lblCSEquipa2Sup;

    // -- KDA dos membros da equipa 1
    @FXML
    private Label lblKDAEquipa1Top;
    @FXML
    private Label lblKDAEquipa1Jungler;
    @FXML
    private Label lblKDAEquipa1Mid;
    @FXML
    private Label lblKDAEquipa1Adc;
    @FXML
    private Label lblKDAEquipa1Sup;

    // -- KDA dos membros da equipa 2
    @FXML
    private Label lblKDAEquipa2Top;
    @FXML
    private Label lblKDAEquipa2Jungler;
    @FXML
    private Label lblKDAEquipa2Mid;
    @FXML
    private Label lblKDAEquipa2Adc;
    @FXML
    private Label lblKDAEquipa2Sup;

    // -- Spells dos membros da equipa 1
    @FXML
    private ImageView img1SpellEquipa1Top;
    @FXML
    private ImageView img2SpellEquipa1Top;
    @FXML
    private ImageView img1SpellEquipa1Jungler;
    @FXML
    private ImageView img2SpellEquipa1Jungler;
    @FXML
    private ImageView img1SpellEquipa1Mid;
    @FXML
    private ImageView img2SpellEquipa1Mid;
    @FXML
    private ImageView img1SpellEquipa1Adc;
    @FXML
    private ImageView img2SpellEquipa1Adc;
    @FXML
    private ImageView img1SpellEquipa1Sup;
    @FXML
    private ImageView img2SpellEquipa1Sup;

    // -- Spells dos membros da equipa 2
    @FXML
    private ImageView img1SpellEquipa2Top;
    @FXML
    private ImageView img2SpellEquipa2Top;
    @FXML
    private ImageView img1SpellEquipa2Jungler;
    @FXML
    private ImageView img2SpellEquipa2Jungler;
    @FXML
    private ImageView img1SpellEquipa2Mid;
    @FXML
    private ImageView img2SpellEquipa2Mid;
    @FXML
    private ImageView img1SpellEquipa2Adc;
    @FXML
    private ImageView img2SpellEquipa2Adc;
    @FXML
    private ImageView img1SpellEquipa2Sup;
    @FXML
    private ImageView img2SpellEquipa2Sup;

    // -- Builds dos membros da equipa 1
    @FXML
    private ImageView imgEquipa1TopItem1;
    @FXML
    private ImageView imgEquipa1TopItem2;
    @FXML
    private ImageView imgEquipa1TopItem3;
    @FXML
    private ImageView imgEquipa1TopItem4;
    @FXML
    private ImageView imgEquipa1TopItem5;
    @FXML
    private ImageView imgEquipa1TopItem6;
    // ----
    @FXML
    private ImageView imgEquipa1JunglerItem1;
    @FXML
    private ImageView imgEquipa1JunglerItem2;
    @FXML
    private ImageView imgEquipa1JunglerItem3;
    @FXML
    private ImageView imgEquipa1JunglerItem4;
    @FXML
    private ImageView imgEquipa1JunglerItem5;
    @FXML
    private ImageView imgEquipa1JunglerItem6;
    // ----
    @FXML
    private ImageView imgEquipa1MidItem1;
    @FXML
    private ImageView imgEquipa1MidItem2;
    @FXML
    private ImageView imgEquipa1MidItem3;
    @FXML
    private ImageView imgEquipa1MidItem4;
    @FXML
    private ImageView imgEquipa1MidItem5;
    @FXML
    private ImageView imgEquipa1MidItem6;
    // ----
    @FXML
    private ImageView imgEquipa1AdcItem1;
    @FXML
    private ImageView imgEquipa1AdcItem2;
    @FXML
    private ImageView imgEquipa1AdcItem3;
    @FXML
    private ImageView imgEquipa1AdcItem4;
    @FXML
    private ImageView imgEquipa1AdcItem5;
    @FXML
    private ImageView imgEquipa1AdcItem6;
    // ----
    @FXML
    private ImageView imgEquipa1SupItem1;
    @FXML
    private ImageView imgEquipa1SupItem2;
    @FXML
    private ImageView imgEquipa1SupItem3;
    @FXML
    private ImageView imgEquipa1SupItem4;
    @FXML
    private ImageView imgEquipa1SupItem5;
    @FXML
    private ImageView imgEquipa1SupItem6;

    // -- Builds dos membros da equipa 2
    @FXML
    private ImageView imgEquipa2TopItem1;
    @FXML
    private ImageView imgEquipa2TopItem2;
    @FXML
    private ImageView imgEquipa2TopItem3;
    @FXML
    private ImageView imgEquipa2TopItem4;
    @FXML
    private ImageView imgEquipa2TopItem5;
    @FXML
    private ImageView imgEquipa2TopItem6;
    // ----
    @FXML
    private ImageView imgEquipa2JunglerItem1;
    @FXML
    private ImageView imgEquipa2JunglerItem2;
    @FXML
    private ImageView imgEquipa2JunglerItem3;
    @FXML
    private ImageView imgEquipa2JunglerItem4;
    @FXML
    private ImageView imgEquipa2JunglerItem5;
    @FXML
    private ImageView imgEquipa2JunglerItem6;
    // ----
    @FXML
    private ImageView imgEquipa2MidItem1;
    @FXML
    private ImageView imgEquipa2MidItem2;
    @FXML
    private ImageView imgEquipa2MidItem3;
    @FXML
    private ImageView imgEquipa2MidItem4;
    @FXML
    private ImageView imgEquipa2MidItem5;
    @FXML
    private ImageView imgEquipa2MidItem6;
    // ----
    @FXML
    private ImageView imgEquipa2AdcItem1;
    @FXML
    private ImageView imgEquipa2AdcItem2;
    @FXML
    private ImageView imgEquipa2AdcItem3;
    @FXML
    private ImageView imgEquipa2AdcItem4;
    @FXML
    private ImageView imgEquipa2AdcItem5;
    @FXML
    private ImageView imgEquipa2AdcItem6;
    // ----
    @FXML
    private ImageView imgEquipa2SupItem1;
    @FXML
    private ImageView imgEquipa2SupItem2;
    @FXML
    private ImageView imgEquipa2SupItem3;
    @FXML
    private ImageView imgEquipa2SupItem4;
    @FXML
    private ImageView imgEquipa2SupItem5;
    @FXML
    private ImageView imgEquipa2SupItem6;

    // -- Botões dos Jogos
    @FXML
    private Button btnJogo1;
    @FXML
    private Button btnJogo2;
    @FXML
    private Button btnJogo3;
    @FXML
    private Button btnJogo4;
    @FXML
    private Button btnJogo5;

    // -- Runas da equipa 1
    @FXML
    private ImageView imgRunaEquipa1Top;
    @FXML
    private ImageView imgRunaEquipa1Jungler;
    @FXML
    private ImageView imgRunaEquipa1Mid;
    @FXML
    private ImageView imgRunaEquipa1Adc;
    @FXML
    private ImageView imgRunaEquipa1Sup;

    // --Runas da equipa 2
    @FXML
    private ImageView imgRunaEquipa2Top;
    @FXML
    private ImageView imgRunaEquipa2Jungler;
    @FXML
    private ImageView imgRunaEquipa2Mid;
    @FXML
    private ImageView imgRunaEquipa2Adc;
    @FXML
    private ImageView imgRunaEquipa2Sup;
    
    //Labels Kills Deaths Assists
    @FXML private Label lblKDABlueTeam;
    @FXML private Label lblKDARedTeam;

    @FXML
    private ImageView imgBack;

    private Encontro encontro;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void closePopUp(MouseEvent event) {
        Stage stage = (Stage) this.imgBack.getScene().getWindow();
        stage.close();
    }

    public void setLogoTeams(Equipa eq1, Equipa eq2) {
        
        if(ImagesTeamServices.existsOnMap(eq1.getNome())){
            this.imgLogoEquipa1.setImage(new Image(ImagesTeamServices.getOriginalPath(eq1.getNome())));
        }else{
            if (FXMLPopUpTeamController.class.getResourceAsStream("pics/teams/" + eq1.getSigla().toLowerCase() + ".png") != null) {
                this.imgLogoEquipa1.setImage(new Image(FXMLPopUpTeamController.class.getResourceAsStream("pics/teams/" + eq1.getSigla().toLowerCase() + ".png")));
            } else {
                this.imgLogoEquipa1.setImage(new Image(FXMLPopUpTeamController.class.getResourceAsStream("pics/teams/unknown.png")));
            }
        }
        
        if(ImagesTeamServices.existsOnMap(eq2.getNome())){
            this.imgLogoEquipa2.setImage(new Image(ImagesTeamServices.getOriginalPath(eq2.getNome())));
        }else{
            if (FXMLPopUpTeamController.class.getResourceAsStream("pics/teams/" + eq2.getSigla().toLowerCase() + ".png") != null) {
                this.imgLogoEquipa2.setImage(new Image(FXMLPopUpTeamController.class.getResourceAsStream("pics/teams/" + eq2.getSigla().toLowerCase() + ".png")));
            } else {
                this.imgLogoEquipa2.setImage(new Image(FXMLPopUpTeamController.class.getResourceAsStream("pics/teams/unknown.png")));
            }
        }
    }

    public void setTeamWins(String eq1, String eq2) {
        this.lblWinEquipa1.setText(eq1);
        this.lblWinEquipa2.setText(eq2);
    }

    public void setTeamSigla(String eq1, String eq2) {
        this.lblSiglaEquipa1.setText(eq1);
        this.lblSiglaEquipa2.setText(eq2);
    }

    public void setTeamMemberNames(Equipa eq1, Equipa eq2) {

        for (Membroequipa me : (Collection<Membroequipa>) eq1.getMembroequipas()) {
            if (me.getCargo().equals("player") && me.getPosicao().toString().equals("TOP")) {
                this.lblEquipa1NomeTop.setText(me.getNome());
            }

            if (me.getCargo().equals("player") && me.getPosicao().toString().equals("JNG")) {
                this.lblEquipa1NomeJungler.setText(me.getNome());
            }

            if (me.getCargo().equals("player") && me.getPosicao().toString().equals("MID")) {
                this.lblEquipa1NomeMid.setText(me.getNome());
            }

            if (me.getCargo().equals("player") && me.getPosicao().toString().equals("ADC")) {
                this.lblEquipa1NomeAdc.setText(me.getNome());
            }

            if (me.getCargo().equals("player") && me.getPosicao().toString().equals("SUP")) {
                this.lblEquipa1NomeSup.setText(me.getNome());
            }
        }

        for (Membroequipa me : (Collection<Membroequipa>) eq2.getMembroequipas()) {
            if (me.getCargo().equals("player") && me.getPosicao().toString().equals("TOP")) {
                this.lblEquipa2NomeTop.setText(me.getNome());
            }

            if (me.getCargo().equals("player") && me.getPosicao().toString().equals("JNG")) {
                this.lblEquipa2NomeJungler.setText(me.getNome());
            }

            if (me.getCargo().equals("player") && me.getPosicao().toString().equals("MID")) {
                this.lblEquipa2NomeMid.setText(me.getNome());
            }

            if (me.getCargo().equals("player") && me.getPosicao().toString().equals("ADC")) {
                this.lblEquipa2NomeAdc.setText(me.getNome());
            }

            if (me.getCargo().equals("player") && me.getPosicao().toString().equals("SUP")) {
                this.lblEquipa2NomeSup.setText(me.getNome());
            }
        }

    }

    public void setTeamsStats(Encontro en, int njogo) {
        // -- Guardar um encontro numa variavel da classe
        encontro = en;

        List<Jogo> jogos = new ArrayList<>();
        jogos.addAll(en.getJogos());
        jogos.sort(Comparator.comparing((jogo) -> jogo.getId()));

        if (jogos.size() > njogo) {
            Jogo jg = jogos.get(njogo);

            System.out.println(jg.getId().toString());

            Equipa eq1 = jg.getEquipaByEquipa1();
            Equipa eq2 = jg.getEquipaByEquipa2();
            
            // -- Labels de KILLS DEATHS E ASSISTS
            
            lblKDABlueTeam.setText(jg.getKillsequipa1().toString()+"/"+jg.getDeathsequipa1().toString()+"/"+jg.getAssistsequipa1().toString());
            lblKDARedTeam.setText(jg.getKillsequipa2().toString()+"/"+jg.getDeathequipa2().toString()+"/"+jg.getAssistsequipa2().toString());

            // -- Receber as estatisticas do jogo correto
            List<Estatisticasmembrojogo> estatisticas = new ArrayList<>();
            estatisticas.addAll(jg.getEstatisticasmembrojogos());

            // --Se a estatistica pertencer ao membro da equipa x e tiver a posiçao y, vamos buscar o champion, KDA, CS, Items, SPELLS e Runa
            for (Estatisticasmembrojogo est : estatisticas) {
                // -- Equipa 1
                // -- Top
                if (est.getMembroequipa().getEquipa().equals(eq1) && est.getMembroequipa().getPosicao().toString().equals("TOP")) {
                    this.setTeamChamp(est, imgEquipa1TopChamp);
                    this.setTeamKDA(est, lblKDAEquipa1Top);
                    this.setTeamCS(est, lblCSEquipa1Top);
                    this.setTeamSpell(est, img1SpellEquipa1Top, img2SpellEquipa1Top);
                    this.setTeamBuild(est, imgEquipa1TopItem1, imgEquipa1TopItem2, imgEquipa1TopItem3, imgEquipa1TopItem4, imgEquipa1TopItem5, imgEquipa1TopItem6);
                    this.setTeamRunes(est, imgRunaEquipa1Top);
                }
                // -- Jungler
                if (est.getMembroequipa().getEquipa().equals(eq1) && est.getMembroequipa().getPosicao().toString().equals("JNG")) {
                    this.setTeamChamp(est, imgEquipa1JunglerChamp);
                    this.setTeamKDA(est, lblKDAEquipa1Jungler);
                    this.setTeamCS(est, lblCSEquipa1Jungler);
                    this.setTeamSpell(est, img1SpellEquipa1Jungler, img2SpellEquipa1Jungler);
                    this.setTeamBuild(est, imgEquipa1JunglerItem1, imgEquipa1JunglerItem2, imgEquipa1JunglerItem3, imgEquipa1JunglerItem4, imgEquipa1JunglerItem5, imgEquipa1JunglerItem6);
                    this.setTeamRunes(est, imgRunaEquipa1Jungler);
                }
                // -- Mid
                if (est.getMembroequipa().getEquipa().equals(eq1) && est.getMembroequipa().getPosicao().toString().equals("MID")) {
                    this.setTeamChamp(est, imgEquipa1MidChamp);
                    this.setTeamKDA(est, lblKDAEquipa1Mid);
                    this.setTeamCS(est, lblCSEquipa1Mid);
                    this.setTeamSpell(est, img1SpellEquipa1Mid, img2SpellEquipa1Mid);
                    this.setTeamBuild(est, imgEquipa1MidItem1, imgEquipa1MidItem2, imgEquipa1MidItem3, imgEquipa1MidItem4, imgEquipa1MidItem5, imgEquipa1MidItem6);
                    this.setTeamRunes(est, imgRunaEquipa1Mid);
                }
                // -- Adc
                if (est.getMembroequipa().getEquipa().equals(eq1) && est.getMembroequipa().getPosicao().toString().equals("ADC")) {
                    this.setTeamChamp(est, imgEquipa1AdcChamp);
                    this.setTeamKDA(est, lblKDAEquipa1Adc);
                    this.setTeamCS(est, lblCSEquipa1Adc);
                    this.setTeamSpell(est, img1SpellEquipa1Adc, img2SpellEquipa1Adc);
                    this.setTeamBuild(est, imgEquipa1AdcItem1, imgEquipa1AdcItem2, imgEquipa1AdcItem3, imgEquipa1AdcItem4, imgEquipa1AdcItem5, imgEquipa1AdcItem6);
                    this.setTeamRunes(est, imgRunaEquipa1Adc);
                }
                // -- Sup
                if (est.getMembroequipa().getEquipa().equals(eq1) && est.getMembroequipa().getPosicao().toString().equals("SUP")) {
                    this.setTeamChamp(est, imgEquipa1SupChamp);
                    this.setTeamKDA(est, lblKDAEquipa1Sup);
                    this.setTeamCS(est, lblCSEquipa1Sup);
                    this.setTeamSpell(est, img1SpellEquipa1Sup, img2SpellEquipa1Sup);
                    this.setTeamBuild(est, imgEquipa1SupItem1, imgEquipa1SupItem2, imgEquipa1SupItem3, imgEquipa1SupItem4, imgEquipa1SupItem5, imgEquipa1SupItem6);
                    this.setTeamRunes(est, imgRunaEquipa1Sup);
                }

                // -- Equipa 2
                // -- Top
                if (est.getMembroequipa().getEquipa().equals(eq2) && est.getMembroequipa().getPosicao().toString().equals("TOP")) {
                    this.setTeamChamp(est, imgEquipa2TopChamp);
                    this.setTeamKDA(est, lblKDAEquipa2Top);
                    this.setTeamCS(est, lblCSEquipa2Top);
                    this.setTeamSpell(est, img1SpellEquipa2Top, img2SpellEquipa2Top);
                    this.setTeamBuild(est, imgEquipa2TopItem1, imgEquipa2TopItem2, imgEquipa2TopItem3, imgEquipa2TopItem4, imgEquipa2TopItem5, imgEquipa2TopItem6);
                    this.setTeamRunes(est, imgRunaEquipa2Top);
                }
                // -- Jungler
                if (est.getMembroequipa().getEquipa().equals(eq2) && est.getMembroequipa().getPosicao().toString().equals("JNG")) {
                    this.setTeamChamp(est, imgEquipa2JunglerChamp);
                    this.setTeamKDA(est, lblKDAEquipa2Jungler);
                    this.setTeamCS(est, lblCSEquipa2Jungler);
                    this.setTeamSpell(est, img1SpellEquipa2Jungler, img2SpellEquipa2Jungler);
                    this.setTeamBuild(est, imgEquipa2JunglerItem1, imgEquipa2JunglerItem2, imgEquipa2JunglerItem3, imgEquipa2JunglerItem4, imgEquipa2JunglerItem5, imgEquipa2JunglerItem6);
                    this.setTeamRunes(est, imgRunaEquipa2Jungler);
                }
                // -- Mid
                if (est.getMembroequipa().getEquipa().equals(eq2) && est.getMembroequipa().getPosicao().toString().equals("MID")) {
                    this.setTeamChamp(est, imgEquipa2MidChamp);
                    this.setTeamKDA(est, lblKDAEquipa2Mid);
                    this.setTeamCS(est, lblCSEquipa2Mid);
                    this.setTeamSpell(est, img1SpellEquipa2Mid, img2SpellEquipa2Mid);
                    this.setTeamBuild(est, imgEquipa2MidItem1, imgEquipa2MidItem2, imgEquipa2MidItem3, imgEquipa2MidItem4, imgEquipa2MidItem5, imgEquipa2MidItem6);
                    this.setTeamRunes(est, imgRunaEquipa2Mid);
                }
                // -- Adc
                if (est.getMembroequipa().getEquipa().equals(eq2) && est.getMembroequipa().getPosicao().toString().equals("ADC")) {
                    this.setTeamChamp(est, imgEquipa2AdcChamp);
                    this.setTeamKDA(est, lblKDAEquipa2Adc);
                    this.setTeamCS(est, lblCSEquipa2Adc);
                    this.setTeamSpell(est, img1SpellEquipa2Adc, img2SpellEquipa2Adc);
                    this.setTeamBuild(est, imgEquipa2AdcItem1, imgEquipa2AdcItem2, imgEquipa2AdcItem3, imgEquipa2AdcItem4, imgEquipa2AdcItem5, imgEquipa2AdcItem6);
                    this.setTeamRunes(est, imgRunaEquipa2Adc);
                }
                // -- Sup
                if (est.getMembroequipa().getEquipa().equals(eq2) && est.getMembroequipa().getPosicao().toString().equals("SUP")) {
                    this.setTeamChamp(est, imgEquipa2SupChamp);
                    this.setTeamKDA(est, lblKDAEquipa2Sup);
                    this.setTeamCS(est, lblCSEquipa2Sup);
                    this.setTeamSpell(est, img1SpellEquipa2Sup, img2SpellEquipa2Sup);
                    this.setTeamBuild(est, imgEquipa2SupItem1, imgEquipa2SupItem2, imgEquipa2SupItem3, imgEquipa2SupItem4, imgEquipa2SupItem5, imgEquipa2SupItem6);
                    this.setTeamRunes(est, imgRunaEquipa2Sup);
                }
            }
        }
    }

    public void setTeamChamp(Estatisticasmembrojogo est, ImageView img) {
        if (FXMLPopUpTeamController.class.getResourceAsStream("pics/champs/" + est.getChampion().getNome().toLowerCase() + ".png") != null) {
            img.setImage(new Image(FXMLPopUpTeamController.class.getResourceAsStream("pics/champs/" + est.getChampion().getNome().toLowerCase() + ".png")));
        } else {
            img.setImage(new Image(FXMLPopUpTeamController.class.getResourceAsStream("pics/players/unknown.png")));
        }
    }

    public void setTeamKDA(Estatisticasmembrojogo est, Label lbl) {
        lbl.setText(est.getKills().toString() + "/" + est.getDeaths().toString() + "/" + est.getAssists().toString());
    }

    public void setTeamCS(Estatisticasmembrojogo est, Label lbl) {
        lbl.setText(est.getCs().toString());
    }

    public void setTeamSpell(Estatisticasmembrojogo est, ImageView img1, ImageView img2) {
        List<Spellescolhido> spells = new ArrayList<>();
        spells.addAll(est.getSpellescolhidos());

        if (FXMLPopUpTeamController.class.getResourceAsStream("pics/spells/" + spells.get(0).getSpell().getNome().toLowerCase() + ".png") != null) {
            img1.setImage(new Image(FXMLPopUpTeamController.class.getResourceAsStream("pics/spells/" + spells.get(0).getSpell().getNome().toLowerCase() + ".png")));
        } else {
            img1.setImage(new Image(FXMLPopUpTeamController.class.getResourceAsStream("pics/players/unknown.png")));
        }

        if (FXMLPopUpTeamController.class.getResourceAsStream("pics/spells/" + spells.get(1).getSpell().getNome().toLowerCase() + ".png") != null) {
            img2.setImage(new Image(FXMLPopUpTeamController.class.getResourceAsStream("pics/spells/" + spells.get(1).getSpell().getNome().toLowerCase() + ".png")));
        } else {
            img2.setImage(new Image(FXMLPopUpTeamController.class.getResourceAsStream("pics/players/unknown.png")));
        }
    }

    public void setTeamBuild(Estatisticasmembrojogo est, ImageView img1, ImageView img2, ImageView img3, ImageView img4, ImageView img5, ImageView img6) {
        List<Build> builds = new ArrayList<>();
        builds.addAll(est.getBuilds());
        builds.sort(Comparator.comparing((build) -> build.getId()));

        if (builds.size() > 0 && FXMLPopUpTeamController.class.getResourceAsStream("pics/items/" + builds.get(0).getItem().toString().toLowerCase() + ".png") != null) {
            img1.setImage(new Image(FXMLPopUpTeamController.class.getResourceAsStream("pics/items/" + builds.get(0).getItem().toString().toLowerCase() + ".png")));
        } else {
            img1.setImage(new Image(FXMLPopUpTeamController.class.getResourceAsStream("pics/players/unknown.png")));
        }

        if (builds.size() > 1 && FXMLPopUpTeamController.class.getResourceAsStream("pics/items/" + builds.get(1).getItem().toString().toLowerCase() + ".png") != null) {
            img2.setImage(new Image(FXMLPopUpTeamController.class.getResourceAsStream("pics/items/" + builds.get(1).getItem().toString().toLowerCase() + ".png")));
        } else {
            img2.setImage(new Image(FXMLPopUpTeamController.class.getResourceAsStream("pics/players/unknown.png")));
        }

        if (builds.size() > 2 && FXMLPopUpTeamController.class.getResourceAsStream("pics/items/" + builds.get(2).getItem().toString().toLowerCase() + ".png") != null) {
            img3.setImage(new Image(FXMLPopUpTeamController.class.getResourceAsStream("pics/items/" + builds.get(2).getItem().toString().toLowerCase() + ".png")));
        } else {
            img3.setImage(new Image(FXMLPopUpTeamController.class.getResourceAsStream("pics/players/unknown.png")));
        }

        if (builds.size() > 3 && FXMLPopUpTeamController.class.getResourceAsStream("pics/items/" + builds.get(3).getItem().toString().toLowerCase() + ".png") != null) {
            img4.setImage(new Image(FXMLPopUpTeamController.class.getResourceAsStream("pics/items/" + builds.get(3).getItem().toString().toLowerCase() + ".png")));
        } else {
            img4.setImage(new Image(FXMLPopUpTeamController.class.getResourceAsStream("pics/players/unknown.png")));
        }

        if (builds.size() > 4 && FXMLPopUpTeamController.class.getResourceAsStream("pics/items/" + builds.get(4).getItem().toString().toLowerCase() + ".png") != null) {
            img5.setImage(new Image(FXMLPopUpTeamController.class.getResourceAsStream("pics/items/" + builds.get(4).getItem().toString().toLowerCase() + ".png")));
        } else {
            img5.setImage(new Image(FXMLPopUpTeamController.class.getResourceAsStream("pics/players/unknown.png")));
        }

        if (builds.size() > 5 && FXMLPopUpTeamController.class.getResourceAsStream("pics/items/" + builds.get(5).getItem().toString().toLowerCase() + ".png") != null) {
            img6.setImage(new Image(FXMLPopUpTeamController.class.getResourceAsStream("pics/items/" + builds.get(5).getItem().toString().toLowerCase() + ".png")));
        } else {
            img6.setImage(new Image(FXMLPopUpTeamController.class.getResourceAsStream("pics/players/unknown.png")));
        }
    }
    
    public void atribuirWinners(Encontro encontro){
        List<Jogo> jogos = new ArrayList<>();
        jogos.addAll(encontro.getJogos());
        jogos.sort(Comparator.comparing((jogo) -> jogo.getId()));
        
        if(jogos.size()>0){
            this.atribuirImageWinner(jogos.get(0), imgWinnerGame1);
        }else{
            imgWinnerGame1.setVisible(false);
        }
        if(jogos.size()>1){
            this.atribuirImageWinner(jogos.get(1), imgWinnerGame2);
        }else{
            imgWinnerGame2.setVisible(false);
        }
        if(jogos.size()>2){
            this.atribuirImageWinner(jogos.get(2), imgWinnerGame3);
        }else{
            imgWinnerGame3.setVisible(false);
        }
        if(jogos.size()>3){
            this.atribuirImageWinner(jogos.get(3), imgWinnerGame4);
        }else{
            imgWinnerGame4.setVisible(false);
        }
        if(jogos.size()>4){
            this.atribuirImageWinner(jogos.get(4), imgWinnerGame5);
        }else{
            imgWinnerGame5.setVisible(false);
        }
    }
    
    public void atribuirImageWinner(Jogo jogo, ImageView img){
        Equipa eq = jogo.getEquipaByVencedor();
        
        if(ImagesTeamServices.existsOnMap(eq.getNome())){
            img.setImage(new Image(ImagesTeamServices.getOriginalPath(eq.getNome())));
        }else{
            if (FXMLPopUpTeamController.class.getResourceAsStream("pics/teams/" + eq.getSigla().toLowerCase() + ".png") != null) {
                img.setImage(new Image(FXMLPopUpTeamController.class.getResourceAsStream("pics/teams/" + eq.getSigla().toLowerCase() + ".png")));
            } else {
                img.setImage(new Image(FXMLPopUpTeamController.class.getResourceAsStream("pics/teams/unknown.png")));
            }
        }
    }

    public void setTeamRunes(Estatisticasmembrojogo est, ImageView img) {
        List<Runaescolhida> runas = new ArrayList<>();
        runas.addAll(est.getRunaescolhidas());

        if (FXMLPopUpTeamController.class.getResourceAsStream("pics/runes/" + runas.get(0).getRuna().toString().toLowerCase() + ".png") != null) {
            img.setImage(new Image(FXMLPopUpTeamController.class.getResourceAsStream("pics/runes/" + runas.get(0).getRuna().toString().toLowerCase() + ".png")));
        } else {
            img.setImage(new Image(FXMLPopUpTeamController.class.getResourceAsStream("pics/players/unknown.png")));
        }
    }

    @FXML
    public void atualizaStats(MouseEvent event) {

        if (event.getSource() == btnJogo1) {
            this.setTeamsStats(encontro, 0);
        }
        if (event.getSource() == btnJogo2) {
            this.setTeamsStats(encontro, 1);
        }
        if (event.getSource() == btnJogo3) {
            this.setTeamsStats(encontro, 2);
        }
        if (event.getSource() == btnJogo4) {
            this.setTeamsStats(encontro, 3);
        }
        if (event.getSource() == btnJogo5) {
            this.setTeamsStats(encontro, 4);
        }
    }

}
