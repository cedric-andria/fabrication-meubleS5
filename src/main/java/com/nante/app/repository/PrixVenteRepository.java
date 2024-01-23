package com.nante.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nante.app.model.PrixVente;
import java.lang.Integer;
import java.util.List;
public interface PrixVenteRepository extends JpaRepository<PrixVente, Integer>   {
    
}
