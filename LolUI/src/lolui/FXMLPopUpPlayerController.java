/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lolui;

import hibernate.HibernateGenericLib;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import loldal.model.Champion;
import loldal.model.Equipa;
import loldal.model.Estatisticasmembrojogo;
import loldal.model.Membroequipa;

/**
 * FXML Controller class
 *
 * @author vaaz
 */
public class FXMLPopUpPlayerController implements Initializable {

    //Imagens do PopUp
    @FXML
    private ImageView imgBack;
    @FXML
    private ImageView imgPlayer;
    @FXML
    private ImageView imgPais;
    @FXML
    private ImageView imgPosition;
    @FXML
    private ImageView imgTeam;
    @FXML
    private ImageView imgInfoKKDDAA;
    @FXML
    private ImageView imgInfoKDA;
    @FXML
    private ImageView imgTopChamp1;
    @FXML
    private ImageView imgTopChamp2;
    @FXML
    private ImageView imgTopChamp3;

    //Labels do PopUp
    @FXML
    private Label lblNomePlayer;
    @FXML
    private Label lblIdade;
    @FXML
    private Label lblKKDDAA;
    @FXML
    private Label lblKDA;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }

    @FXML
    public void closePopUp(MouseEvent event) {
        Stage stage = (Stage) this.imgBack.getScene().getWindow();
        stage.close();
    }

    public void setPlayerImage(Equipa eq, String posicao) {
        for (Membroequipa me : (Collection<Membroequipa>) eq.getMembroequipas()) {
            if (me.getCargo().equals("player") && me.getPosicao().toString().equals(posicao)) {
                if (FXMLPlayersMainController.class.getResourceAsStream("pics/players/" + me.getNome() + ".png") != null) {
                    this.imgPlayer.setImage(new Image(FXMLPlayersMainController.class.getResourceAsStream("pics/players/" + me.getNome() + ".png")));
                } else {
                    this.imgPlayer.setImage(new Image(FXMLPlayersMainController.class.getResourceAsStream("pics/players/unknown.png")));
                }
            }
        }
    }

    public void setCountryImage(Equipa eq, String posicao) {
        for (Membroequipa me : (Collection<Membroequipa>) eq.getMembroequipas()) {
            if (me.getCargo().equals("player") && me.getPosicao().toString().equals(posicao)) {
                if (FXMLPlayersMainController.class.getResourceAsStream("pics/countries/" + me.getPais().getSigla().toLowerCase() + ".png") != null) {
                    this.imgPais.setImage(new Image(FXMLPlayersMainController.class.getResourceAsStream("pics/countries/" + me.getPais().getSigla().toLowerCase() + ".png")));
                } else {
                    this.imgPais.setImage(new Image(FXMLPlayersMainController.class.getResourceAsStream("pics/countries/unknown.png")));
                }
            }
        }
    }

    public void setTeamImage(Equipa eq, String posicao) {
        for (Membroequipa me : (Collection<Membroequipa>) eq.getMembroequipas()) {
            if (FXMLPlayersMainController.class.getResourceAsStream("pics/teams/" + me.getEquipa().getSigla().toLowerCase() + ".png") != null) {
                this.imgTeam.setImage(new Image(FXMLPlayersMainController.class.getResourceAsStream("pics/teams/" + me.getEquipa().getSigla().toLowerCase() + ".png")));
            } else {
                this.imgTeam.setImage(new Image(FXMLPlayersMainController.class.getResourceAsStream("pics/teams/unknown.png")));
            }
        }
    }

    public void setPositionImage(Equipa eq, String posicao) {
        for (Membroequipa me : (Collection<Membroequipa>) eq.getMembroequipas()) {
            this.imgPosition.setImage(new Image(FXMLPlayersMainController.class.getResourceAsStream("pics/positions/" + posicao.toLowerCase() + ".png")));
        }
    }

    public void setPlayerName(Equipa eq, String posicao) {
        for (Membroequipa me : (Collection<Membroequipa>) eq.getMembroequipas()) {
            if (me.getCargo().equals("player") && me.getPosicao().toString().equals(posicao)) {
                this.lblNomePlayer.setText(me.getNome());
            }
        }
    }

    public void setPlayerAge(Equipa eq, String posicao) {
        for (Membroequipa me : (Collection<Membroequipa>) eq.getMembroequipas()) {
            if (me.getCargo().equals("player") && me.getPosicao().toString().equals(posicao)) {
                this.lblIdade.setText(me.getIdade() + " years old");
            }
        }
    }

    void setTop3Champs(Equipa eq, String posicao) {

        int n = 3;
        Membroequipa me = new Membroequipa();

        for (Membroequipa membro : (Collection<Membroequipa>) eq.getMembroequipas()) {
            if (membro.getCargo().equals("player") && membro.getPosicao().toString().equals(posicao)) {
                me = membro;
            }
        }

        Map<Champion, Integer> mapa = new HashMap<>();
        List<Estatisticasmembrojogo> top3 = HibernateGenericLib.executeHQLQuery(" from Estatisticasmembrojogo where membroequipa=" + me.getId());
        for (Estatisticasmembrojogo c : top3) {
            if (!mapa.containsKey(c.getChampion())) {
                mapa.put(c.getChampion(), 1);
            } else {
                mapa.put(c.getChampion(), mapa.get(c.getChampion()) + 1);
            }
        }

        List<Map.Entry<Champion, Integer>> greatest = findGreatest(mapa, n);
        List<Champion> top3Champs = new ArrayList<>();

        for (Map.Entry<Champion, Integer> entry : greatest) {
            top3Champs.add(entry.getKey());
        }

        if (top3Champs.size() > 2 && FXMLPlayersMainController.class.getResourceAsStream("pics/champs/" + top3Champs.get(2).getNome().toLowerCase() + ".png") != null) {
            this.imgTopChamp1.setImage(new Image(FXMLPlayersMainController.class.getResourceAsStream("pics/champs/" + top3Champs.get(2).getNome().toLowerCase() + ".png")));
            Tooltip.install(this.imgTopChamp1, new Tooltip(top3Champs.get(2).getNome()));
        } else {
            this.imgTopChamp1.setImage(new Image(FXMLPlayersMainController.class.getResourceAsStream("pics/players/unknown.png")));
        }

        if (top3Champs.size() > 1 && FXMLPlayersMainController.class.getResourceAsStream("pics/champs/" + top3Champs.get(1).getNome().toLowerCase() + ".png") != null) {
            this.imgTopChamp2.setImage(new Image(FXMLPlayersMainController.class.getResourceAsStream("pics/champs/" + top3Champs.get(1).getNome().toLowerCase() + ".png")));
            Tooltip.install(this.imgTopChamp2, new Tooltip(top3Champs.get(1).getNome()));
        } else {
            this.imgTopChamp2.setImage(new Image(FXMLPlayersMainController.class.getResourceAsStream("pics/players/unknown.png")));
        }

        if (top3Champs.size() > 0 && FXMLPlayersMainController.class.getResourceAsStream("pics/champs/" + top3Champs.get(0).getNome().toLowerCase() + ".png") != null) {
            this.imgTopChamp3.setImage(new Image(FXMLPlayersMainController.class.getResourceAsStream("pics/champs/" + top3Champs.get(0).getNome().toLowerCase() + ".png")));
            Tooltip.install(this.imgTopChamp3, new Tooltip(top3Champs.get(0).getNome()));
        } else {
            this.imgTopChamp3.setImage(new Image(FXMLPlayersMainController.class.getResourceAsStream("pics/players/unknown.png")));
        }
    }

    public void setKDA(Equipa eq, String posicao) {
        //Formula do KDA = (sumKills + sumAssists) / sumDeaths

        Membroequipa me = new Membroequipa();
        int totKills = 0;
        int totAssists = 0;
        int totDeaths = 0;
        float kdaRatio = 0;
        DecimalFormat df2 = new DecimalFormat(".##");

        for (Membroequipa membro : (Collection<Membroequipa>) eq.getMembroequipas()) {
            if (membro.getCargo().equals("player") && membro.getPosicao().toString().equals(posicao)) {
                me = membro;
            }
        }

        //listas de jogos onde o Membroequipa participou
        List<Estatisticasmembrojogo> listaJogosDoPlayer = HibernateGenericLib.executeHQLQuery(" from Estatisticasmembrojogo where membroequipa=" + me.getId());

        for (Estatisticasmembrojogo stat : listaJogosDoPlayer) {
            totKills += stat.getKills().intValue();
            totAssists += stat.getAssists().intValue();
            totDeaths += stat.getDeaths().intValue();
        }

        kdaRatio = (float) (totKills + totAssists) / totDeaths;

        this.lblKDA.setText(df2.format(kdaRatio));
        this.lblKKDDAA.setText(totKills + "/" + totDeaths + "/" + totAssists);
    }

    private static <K, V extends Comparable<? super V>> List<Map.Entry<K, V>>
            findGreatest(Map<K, V> map, int n) {
        Comparator<? super Map.Entry<K, V>> comparator
                = new Comparator<Map.Entry<K, V>>() {
            @Override
            public int compare(Map.Entry<K, V> e0, Map.Entry<K, V> e1) {
                V v0 = e0.getValue();
                V v1 = e1.getValue();
                return v0.compareTo(v1);
            }
        };
        PriorityQueue<Map.Entry<K, V>> highest
                = new PriorityQueue<Map.Entry<K, V>>(n, comparator);
        for (Map.Entry<K, V> entry : map.entrySet()) {
            highest.offer(entry);
            while (highest.size() > n) {
                highest.poll();
            }
        }

        List<Map.Entry<K, V>> result = new ArrayList<Map.Entry<K, V>>();
        while (highest.size() > 0) {
            result.add(highest.poll());
        }
        return result;
    }

    @FXML
    public void setInfoKDA() {
        Tooltip.install(this.imgInfoKDA, new Tooltip("KDA\n(Kills + Assists / Deaths)"));
    }

    @FXML
    public void setInfoKKDDAA() {
        Tooltip.install(this.imgInfoKKDDAA, new Tooltip("KK/DD/AA\n(Total Kills / Total Deaths / Total Assists)"));
    }
}
