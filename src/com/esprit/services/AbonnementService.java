/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.esprit.entities.Abonnement;
import com.esprit.entities.Coupon;
import static com.esprit.services.CouponService.resultOk;
import com.esprit.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author raoun
 */
public class AbonnementService {
          public ArrayList<Abonnement> abonnements;
    public static AbonnementService instance = null;
    private ConnectionRequest req;
     Resources res;
      public static boolean resultOk = true;
    public static AbonnementService getInstance() {
        if (instance == null) {
            instance = new AbonnementService();
        }
        return instance;

    }

    public AbonnementService() {
        req = new ConnectionRequest();
    }
    
        public ArrayList<Abonnement> parseTasks(String jsonText){
        try {
            abonnements=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            /*
                On doit convertir notre réponse texte en CharArray à fin de
            permettre au JSONParser de la lire et la manipuler d'ou vient 
            l'utilité de new CharArrayReader(json.toCharArray())
            
            La méthode parse json retourne une MAP<String,Object> ou String est 
            la clé principale de notre résultat.
            Dans notre cas la clé principale n'est pas définie cela ne veux pas
            dire qu'elle est manquante mais plutôt gardée à la valeur par defaut
            qui est root.
            En fait c'est la clé de l'objet qui englobe la totalité des objets 
                    c'est la clé définissant le tableau de tâches.
            */
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
              /* Ici on récupère l'objet contenant notre liste dans une liste 
            d'objets json List<MAP<String,Object>> ou chaque Map est une tâche.               
            
            Le format Json impose que l'objet soit définit sous forme
            de clé valeur avec la valeur elle même peut être un objet Json.
            Pour cela on utilise la structure Map comme elle est la structure la
            plus adéquate en Java pour stocker des couples Key/Value.
            
            Pour le cas d'un tableau (Json Array) contenant plusieurs objets
            sa valeur est une liste d'objets Json, donc une liste de Map
            */
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
     
            
            
        } catch (IOException ex) {
            
        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        return abonnements;
    }
        
        
         public ArrayList<Abonnement> getAllAbonnements(){
        String url = Statics.BASE_URL+"/abonnement/list";
       
        
       ArrayList<Abonnement> result = new ArrayList<>();
        
        
        req.setUrl(url);
        req.setPost(false);
        
        
         req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();

                try {

                    Map<String, Object> mapReclamations = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapReclamations.get("abonnement");
                   System.out.println("lissst"+mapReclamations);
                    for(Map<String, Object> obj : listOfMaps) {
                        Abonnement h = new Abonnement();

                        String duree = obj.get("duree").toString();
                        String prix =obj.get("prix").toString();
                        String dateDebut =obj.get("dateDebut").toString();
                        
                        String dateFin =obj.get("dateFin").toString();
                             
                              float id = Float.parseFloat(obj.get("id").toString());
                     h.setId((int)id);
                    h.setDateDebut(dateDebut);
                    h.setDateFin(dateFin);
                    h.setDuree(duree);
                    h.setPrix(prix);
                    
                     result.add(h);
                System.out.println("data "+obj.get("duree").toString());
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                
             }

        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        
        
        return result;
    }
         
         
    public boolean deleteAbonnement(int id ) {
        String url = Statics.BASE_URL +"/abonnement/supprimer?id="+id;
        
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  resultOk;
    }
    
     public boolean addAbonnement(Abonnement h) {
     String url = Statics.BASE_URL +"/abonnement/ajouter/?duree="+h.getDuree()+"&prix="+h.getPrix();
    
         
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200 ;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
    return resultOk;
        
   
    }
           
      public boolean modifierAbonnement(Abonnement h) {
        String url = Statics.BASE_URL +"/abonnement/modifier/"+h.getId()+"?duree="+h.getDuree()+"&prix="+h.getPrix();
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200 ;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
    return resultOk;
        
    }
}
