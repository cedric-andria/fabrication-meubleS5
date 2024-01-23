package com.nante.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nante.app.model.StockMateriau;
import java.lang.Integer;
import java.util.List;

public interface StockMateriauRepository extends JpaRepository<StockMateriau, Integer>  {
    
    // public List<StockMateriau> findTopByMateriauIdOrderByIdStockMateriaus();
    public List<StockMateriau> findTopByMateriauIdOrderById(Integer materiauId);

}
