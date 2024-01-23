package com.nante.app.model;

import java.sql.Date;
import java.time.LocalDateTime;

import com.nante.app.model.Materiau;

import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
@Entity
public class AchatMateriau {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name="idMateriau")
    private Materiau materiau;
    private int quantite;
    private LocalDateTime date_achat;


    public AchatMateriau(Materiau materiau, int quantite, LocalDateTime date_achat) {
        this.materiau = materiau;
        this.quantite = quantite;
        this.date_achat = date_achat;
    }
    public AchatMateriau(int id, Materiau materiau, int quantite, LocalDateTime date_achat) {
        this.id = id;
        this.materiau = materiau;
        this.quantite = quantite;
        this.date_achat = date_achat;
    }
    public AchatMateriau() {
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
    public int getQuantite() {
        return quantite;
    }
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
    public LocalDateTime getDate_achat() {
        return date_achat;
    }
    public void setDate_achat(LocalDateTime date_achat) {
        this.date_achat = date_achat;
    }

    
}
