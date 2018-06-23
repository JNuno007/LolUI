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
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import loldal.model.Champion;
import loldal.model.Estatisticasmembrojogo;
import loldal.model.Membroequipa;
//import model.Champion;
//import model.Estatisticasmembrojogo;
//import model.Membroequipa;


/**
 * FXML Controller class
 *
 * @author vaaz
 */



public class FXMLPlayersMainController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    //Panes
    @FXML private BorderPane borderRight;
    
    //ImageViews
    @FXML private ImageView imgTop;
    @FXML private ImageView imgTeamLogo;
    @FXML private ImageView imgCountry;
    @FXML private ImageView imgChamp1;
    @FXML private ImageView imgChamp2;
    @FXML private ImageView imgChamp3;
    @FXML private ImageView imgPosition;
    
    //Labels
    @FXML private Label lblName;
    @FXML private Label lblAge;
    @FXML private Label lblKDA;
    @FXML private Label lblKKDDAA;
    
    //ListView
    @FXML private ListView listaJogadores;
    
    //TextField
    @FXML private TextField searchBar;
    
    //Icons top3 Champs
    @FXML private ImageView topOne;
    @FXML private ImageView topTwo;
    @FXML private ImageView topThree;
    
    
    
    private ObservableList<Membroequipa> membrosEquipaObs;
    private List<Membroequipa> listaPesquisa;
    private Membroequipa me;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.preencherListaJogadores();
        this.pesquisarNaLista();
        this.atribuirElementos();
        
    }
    
     public void preencherListaJogadores(){
       listaPesquisa = HibernateGenericLib.executeHQLQuery(" from Membroequipa where cargo = 'player'");
       Comparator<Membroequipa> comparator = Comparator.comparing(m -> m.getEquipa().toString());
       comparator = comparator.thenComparing(Comparator.comparing(m -> m.getNome()));
       Stream<Membroequipa> membroStream = listaPesquisa.stream().sorted(comparator);
       List<Membroequipa> listaOrdenada = membroStream.collect(Collectors.toList());
       membrosEquipaObs = FXCollections.observableArrayList(listaOrdenada);
       this.listaJogadores.setItems(membrosEquipaObs);
   }
    
    //Obter Player selecionado
    //Arranjar o nick do player para ir buscar a imagem
    
    public void atribuirImagemPlayer(Membroequipa me){
        if(FXMLPlayersMainController.class.getResourceAsStream("pics/players/" + me.getNome() + ".png")!=null){
            imgTop.setImage(new Image(FXMLPlayersMainController.class.getResourceAsStream("pics/players/" + me.getNome() + ".png"))); 
         }else{
            imgTop.setImage(new Image(FXMLPlayersMainController.class.getResourceAsStream("pics/players/unknown.png")));
        }
    }
    
    public void atribuirPaisPlayer(Membroequipa me){
        if(FXMLPlayersMainController.class.getResourceAsStream("pics/countries/" + me.getPais().getSigla().toLowerCase() + ".png")!=null){
            imgCountry.setImage(new Image(FXMLPlayersMainController.class.getResourceAsStream("pics/countries/" + me.getPais().getSigla().toLowerCase() + ".png"))); 
        }else{
            imgCountry.setImage(new Image(FXMLPlayersMainController.class.getResourceAsStream("pics/countries/unknown.png")));
        }
    }
    
    public void atribuirTeamPlayer(Membroequipa me){
        if(FXMLPlayersMainController.class.getResourceAsStream("pics/teams/" + me.getEquipa().getSigla().toLowerCase() + ".png")!=null){
            this.imgTeamLogo.setImage(new Image(FXMLPlayersMainController.class.getResourceAsStream("pics/teams/" + me.getEquipa().getSigla().toLowerCase() + ".png"))); 
        }else{
            this.imgTeamLogo.setImage(new Image(FXMLPlayersMainController.class.getResourceAsStream("pics/teams/unknown.png")));
        }
    }
    
    public void atribuirNomePlayer(Membroequipa me){
        lblName.setText(me.getNome());
    }
    
    public void atribuirIdadePlayer(Membroequipa me){
        this.lblAge.setText(me.getIdade() + " years old ");
    }
    
    public void atribuirImagemPosicao(Membroequipa me){
        this.imgPosition.setImage(new Image(FXMLPlayersMainController.class.getResourceAsStream("pics/positions/" + me.getPosicao().toString().toLowerCase() + ".png"))); 
    }
    
    public void atribuirElementos(){
        this.listaJogadores.getSelectionModel().selectedItemProperty().addListener((observable) -> {
            me = (Membroequipa) listaJogadores.getSelectionModel().getSelectedItem();
            if(me!=null){
               this.atribuirImagemPlayer(me);
                this.atribuirPaisPlayer(me);
                this.atribuirNomePlayer(me);
                this.atribuirIdadePlayer(me);
                this.atribuirTeamPlayer(me);
                this.atribuirImagemPosicao(me);
                this.atribuirKDA(me);
                this.top3Champs(me);
                this.borderRight.setVisible(true);
            }
        });        
    }
    
    public void atribuirKDA(Membroequipa me){
        //Formula do KDA = (sumKills + sumAssists) / sumDeaths
        
        int totKills = 0;
        int totAssists = 0;
        int totDeaths = 0;
        float kdaRatio = 0;
        DecimalFormat df2 = new DecimalFormat(".##");
        
        //listas de jogos onde o Membroequipa participou
        List<Estatisticasmembrojogo> listaJogosDoPlayer = HibernateGenericLib.executeHQLQuery(" from Estatisticasmembrojogo where membroequipa=" + me.getId());
        
        for(Estatisticasmembrojogo jogo : listaJogosDoPlayer){
            totKills += jogo.getKills().intValue();
            totAssists += jogo.getAssists().intValue();
            totDeaths += jogo.getDeaths().intValue();
        }
        
        kdaRatio = (float)(totKills + totAssists) / totDeaths;
        
        this.lblKDA.setText(df2.format(kdaRatio));
        this.lblKKDDAA.setText(totKills + "/" + totDeaths + "/" + totAssists);
    }
    
    public void pesquisarNaLista(){
        searchBar.setOnKeyReleased((event) -> {
           List<Membroequipa> temp = new ArrayList<>();
           String texto = searchBar.getText();
           if(texto.isEmpty()){
               membrosEquipaObs = FXCollections.observableArrayList(listaPesquisa);
               this.listaJogadores.setItems(membrosEquipaObs);
               temp.clear();
           }else{
               for(Membroequipa me: listaPesquisa){
                   if(me.getNome().toLowerCase().contains(texto.toLowerCase())){
                       temp.add(me);
                   }
               }
               membrosEquipaObs = FXCollections.observableArrayList(temp);
               this.listaJogadores.setItems(membrosEquipaObs);
           }
           
        });
    }
    
    
    
    public void top3Champs(Membroequipa me){
        //Criar Mapa Jogador -> mapa Champs(key), Numero de vezes que jogou inteiro
        //Select da BD para sacar o top 3 dos Champs
        //select Champion from (select CHAMPION, count(Champion) from ESTATISTICASMEMBROJOGO where JOGADOR = 2 group by CHAMPION order by COUNT(CHAMPION) desc) where rownum <=3;
        //where membroequipa = " + me.getId() + " group by Champion
    
        int n = 3;
        Map<Champion, Integer> mapa = new HashMap<>();
        
        if(me!=null){
            List<Estatisticasmembrojogo> top3 = HibernateGenericLib.executeHQLQuery(" from Estatisticasmembrojogo where membroequipa=" + me.getId());
            for(Estatisticasmembrojogo c: top3){
                if(!mapa.containsKey(c.getChampion())){
                    mapa.put(c.getChampion(), 1);
                }else{
                    mapa.put(c.getChampion(), mapa.get(c.getChampion()) + 1);
                }
            }
         
            List<Entry<Champion, Integer>> greatest = findGreatest(mapa, n);
            List<Champion> top3Champs = new ArrayList<>();
            
            for (Entry<Champion, Integer> entry : greatest){
                top3Champs.add(entry.getKey());
            }

            if(top3Champs.size()>2 && FXMLPlayersMainController.class.getResourceAsStream("pics/champs/" + top3Champs.get(2).getNome().toLowerCase() + ".png")!=null){
                topOne.setImage(new Image(FXMLPlayersMainController.class.getResourceAsStream("pics/champs/" + top3Champs.get(2).getNome().toLowerCase() + ".png"))); 
             }else{
                topOne.setImage(new Image(FXMLPlayersMainController.class.getResourceAsStream("pics/players/unknown.png")));
            }

            if(top3Champs.size()>1 && FXMLPlayersMainController.class.getResourceAsStream("pics/champs/" + top3Champs.get(1).getNome().toLowerCase() + ".png")!=null ){
                topTwo.setImage(new Image(FXMLPlayersMainController.class.getResourceAsStream("pics/champs/" + top3Champs.get(1).getNome().toLowerCase() + ".png"))); 
             }else{
                topTwo.setImage(new Image(FXMLPlayersMainController.class.getResourceAsStream("pics/players/unknown.png")));
            }

            if(top3Champs.size()>0 && FXMLPlayersMainController.class.getResourceAsStream("pics/champs/" + top3Champs.get(0).getNome().toLowerCase() + ".png")!=null){
                topThree.setImage(new Image(FXMLPlayersMainController.class.getResourceAsStream("pics/champs/" + top3Champs.get(0).getNome().toLowerCase() + ".png"))); 
             }else{
                topThree.setImage(new Image(FXMLPlayersMainController.class.getResourceAsStream("pics/players/unknown.png")));
            }
        }
    }
    
    private static <K, V extends Comparable<? super V>> List<Entry<K, V>> 
        findGreatest(Map<K, V> map, int n)
    {
        Comparator<? super Entry<K, V>> comparator = 
            new Comparator<Entry<K, V>>()
        {
            @Override
            public int compare(Entry<K, V> e0, Entry<K, V> e1)
            {
                V v0 = e0.getValue();
                V v1 = e1.getValue();
                return v0.compareTo(v1);
            }
        };
        PriorityQueue<Entry<K, V>> highest = 
            new PriorityQueue<Entry<K,V>>(n, comparator);
        for (Entry<K, V> entry : map.entrySet())
        {
            highest.offer(entry);
            while (highest.size() > n)
            {
                highest.poll();
            }
        }

        List<Entry<K, V>> result = new ArrayList<Map.Entry<K,V>>();
        while (highest.size() > 0)
        {
            result.add(highest.poll());
        }
        return result;
    }
    
    
}
