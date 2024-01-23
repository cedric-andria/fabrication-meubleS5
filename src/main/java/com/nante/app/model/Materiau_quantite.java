package com.nante.app.model;

import com.nante.app.model.Materiau;

public class Materiau_quantite {
    private Materiau materiau;
    private int quantite;


    public Materiau_quantite(Materiau materiau, int quantite) {
        this.materiau = materiau;
        this.quantite = quantite;
    }
    public Materiau_quantite() {
    }
    public Materiau getMateriau() {
        return materiau;
    }
    public void setMateriau(Materiau materiau) {
        this.materiau = materiau;
    }
    public int getQuantite() {
        return quantite;
    }
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
    
}
