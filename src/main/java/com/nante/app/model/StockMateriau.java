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
public class StockMateriau {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name="idMateriau")
    private Materiau materiau;
    private int stock;
    private LocalDateTime date_stock;
    public StockMateriau(Materiau materiau, int stock, LocalDateTime date_stock) {
        this.materiau = materiau;
        this.stock = stock;
        this.date_stock = date_stock;
    }
    public StockMateriau() {
    }
    public StockMateriau(int id, Materiau materiau, int stock, LocalDateTime date_stock) {
        this.id = id;
        this.materiau = materiau;
        this.stock = stock;
        this.date_stock = date_stock;
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
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
    public LocalDateTime getDate_stock() {
        return date_stock;
    }
    public void setDate_stock(LocalDateTime date_stock) {
        this.date_stock = date_stock;
    }
    
}
