package com.nante.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nante.app.model.StockMeuble;

public interface StockMeubleRepository extends JpaRepository<StockMeuble, Integer>{
    
}
