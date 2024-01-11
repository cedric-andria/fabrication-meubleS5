package com.nante.app.model;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import java.util.List;

import com.nante.app.model.Materiau;

@Entity
public class PrixUnitaireMateriau {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name="idMateriau")
    private Materiau materiau;
    private double montant;

    public PrixUnitaireMateriau(int id) {
        this.id = id;
    }

    public PrixUnitaireMateriau(Materiau materiau, double montant) {
        this.materiau = materiau;
        this.montant = montant;
    }

    public PrixUnitaireMateriau() {
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Materiau getMateriau() {
        return materiau;
    }
    public void setMateriau(Materiau materiau) {
        this.materiau = materiau;
    }
    public double getMontant() {
        return montant;
    }
    public void setMontant(double montant) {
        this.montant = montant;
    }
    
}
