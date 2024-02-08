package com.nante.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nante.app.model.Vente;

public interface VenteRepository extends JpaRepository<Vente, Integer>{
    
}
