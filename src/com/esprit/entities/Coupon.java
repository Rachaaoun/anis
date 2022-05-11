/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entities;

/**
 *
 * @author raoun
 */
public class Coupon {
    
    private int id ; 
    private String  code ; 
    private String pourcentageReduction ; 

    public Coupon() {
    }

    public Coupon(String code, String pourcentageReduction) {
        this.code = code;
        this.pourcentageReduction = pourcentageReduction;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPourcentageReduction() {
        return pourcentageReduction;
    }

    public void setPourcentageReduction(String pourcentageReduction) {
        this.pourcentageReduction = pourcentageReduction;
    }
    
    
}
