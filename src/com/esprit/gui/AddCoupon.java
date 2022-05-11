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
import com.esprit.entities.Coupon;
import com.esprit.services.CouponService;

/**
 *
 * @author Lenovo
 */
public class AddCoupon extends BaseForm {
    
    Form current;
    public AddCoupon(Resources res ) {
         super("Newsfeed",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
    
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajout Coupon");
        getContentPane().setScrollVisible(false);
        
        
        super.addSideMenu(res);
        
        TextField code = new TextField("", "Code" , 20 , TextField.ANY);
        TextField pourcentageReduction = new TextField("", "pourcentageReduction " , 20 , TextField.ANY);
             //  TextField dateexpiration =new TextField(String.valueOf(r.getDateexpiration()), "Date expiration " , 20 , TextField.ANY);
 
        
        
        
        
        code.setUIID("NewsTopLine");
        pourcentageReduction.setUIID("NewsTopLine");
       
        
        code.setSingleLineTextArea(true);
        pourcentageReduction.setSingleLineTextArea(true);
       
        Button Add = new Button("Add");
       Add.setUIID("Button");
       
       //Event onclick btnModifer
       
       Add.addPointerPressedListener(l ->   { 
           Coupon r = new Coupon();
           r.setCode(code.getText());
           r.setPourcentageReduction(pourcentageReduction.getText());
        
           
     //  r.setDateexpiration(r.getDateexpiration());
      
       
       //appel fonction modfier reclamation men service
       
       if(CouponService.getInstance().addCoupon(r)) { // if true
           new ListCoupon(res).show();
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
                new FloatingHint(code),
                createLineSeparator(),
                new FloatingHint(pourcentageReduction),
                createLineSeparator(),
              
           
                Add,
                btnAnnuler
                
               
        );
        
        add(content);
        show();
        
        
    }
}