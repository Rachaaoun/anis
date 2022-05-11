/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.esprit.entities.Abonnement;
import com.esprit.services.AbonnementService;

/**
 *
 * @author Asus
 */
public class ModifierAbonnement extends BaseForm {
    
    Form current;
    public ModifierAbonnement(Resources res ,Abonnement r) {
         super("Newsfeed",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
    
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Modifier Abonnement");
        getContentPane().setScrollVisible(false);
        
        
        super.addSideMenu(res);
        
       TextField duree = new TextField(r.getDuree(), "duree" , 20 , TextField.ANY);
        TextField prix = new TextField(r.getPrix(), "prix " , 20 , TextField.NUMERIC);
            
        
        
        
        
        duree.setUIID("NewsTopLine");
        prix.setUIID("NewsTopLine");
       
        
       
        duree.setSingleLineTextArea(true);
         prix.setSingleLineTextArea(true);
        
        Button Add = new Button("Add");
       Add.setUIID("Button");
       
       //Event onclick btnModifer
       
       Add.addPointerPressedListener(l ->   { 
          r.setDuree(duree.getText());
          r.setPrix(prix.getText());
           
      
       
       //appel fonction modfier reclamation men service
       
       if(AbonnementService.getInstance().modifierAbonnement(r)) { // if true
           new ListAbonnement(res).show();
       }
        });
       Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(e -> {
           new ListCoupon(res).show();
       });
       
       
       Label l2 = new Label("");
       
       Label l3 = new Label("");
       
       Label l4 = new Label("");
       
       Label l5 = new Label("");
       
        Label l1 = new Label();
        
         Button lactiver = new Button("Activer");
        
                lactiver.requestFocus();
        lactiver.addActionListener(e ->{
           // CarteService.getInstance().activate(r);
            
        } );
        
        Container content = BoxLayout.encloseY(
                l1, l2, 
                new FloatingHint(duree),
                createLineSeparator(),
                new FloatingHint(prix),
                createLineSeparator(),
               
              
                createLineSeparator(),//ligne de séparation
           
                Add,
                btnAnnuler
                
               
        );
        
        add(content);
        show();
        
        
    }
}