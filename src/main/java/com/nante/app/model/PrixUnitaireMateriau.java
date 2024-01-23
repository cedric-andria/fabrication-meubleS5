package com.nante.app.model;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;

import java.sql.Date;
import java.time.LocalDateTime;
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
    private LocalDateTime submit_date;

    public PrixUnitaireMateriau(int id) {
        this.id = id;
    }

    public PrixUnitaireMateriau(Materiau materiau, double montant) {
        this.materiau = materiau;
        this.montant = montant;
    }

    public PrixUnitaireMateriau(Materiau materiau, double montant, LocalDateTime submit_date) {
        this.materiau = materiau;
        this.montant = montant;
        this.submit_date = submit_date;
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
