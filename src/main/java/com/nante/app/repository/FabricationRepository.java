package com.nante.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nante.app.model.Fabrication;
import java.lang.Integer;
import java.util.List;

public interface FabricationRepository extends JpaRepository<Fabrication, Integer>  {
    
}
