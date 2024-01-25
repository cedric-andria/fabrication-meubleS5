package com.nante.app.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
@Entity
public class Profil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String description;
    private int experience;
    private int coeff_taux_ouvrier;
    
    public Profil(String description, int experience, int coeff_taux_ouvrier) {
        this.description = description;
        this.experience = experience;
        this.coeff_taux_ouvrier = coeff_taux_ouvrier;
    }
    public Profil(int id, String description, int experience, int coeff_taux_ouvrier) {
        this.id = id;
        this.description = description;
        this.experience = experience;
        this.coeff_taux_ouvrier = coeff_taux_ouvrier;
    }
    public Profil() {
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getExperience() {
        return experience;
    }
    public void setExperience(int experience) {
        this.experience = experience;
    }
    public int getCoeff_taux_ouvrier() {
        return coeff_taux_ouvrier;
    }
    public void setCoeff_taux_ouvrier(int coeff_taux_ouvrier) {
        this.coeff_taux_ouvrier = coeff_taux_ouvrier;
    }
    
}
