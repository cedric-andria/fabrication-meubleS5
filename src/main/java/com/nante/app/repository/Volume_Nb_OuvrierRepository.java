package com.nante.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nante.app.model.Volume_Nb_Ouvrier;
import com.nante.app.model.Style;
import java.lang.Integer;
import java.util.List;
public interface Volume_Nb_OuvrierRepository extends JpaRepository<Volume_Nb_Ouvrier, Integer> { 
    
}
