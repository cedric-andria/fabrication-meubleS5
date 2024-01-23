package com.nante.app.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;

@Entity
public class Volume_Nb_Ouvrier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name="idVolume")
    private Volume volume;
    private int nb_ouvrier;
    
    public Volume_Nb_Ouvrier(Volume volume, int nb_ouvrier) {
        this.volume = volume;
        this.nb_ouvrier = nb_ouvrier;
    }
    public Volume_Nb_Ouvrier(int id) {
        this.id = id;
    }
    public Volume_Nb_Ouvrier() {
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
   
    public int getNb_ouvrier() {
        return nb_ouvrier;
    }
    public void setNb_ouvrier(int nb_ouvrier) {
        this.nb_ouvrier = nb_ouvrier;
    }
    public Volume getVolume() {
        return volume;
    }
    public void setVolume(Volume volume) {
        this.volume = volume;
    }
    
}
