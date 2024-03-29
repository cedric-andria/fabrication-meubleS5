package com.nante.app.repository;

// import com.nante.app.crud.repository.GenericRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nante.app.model.Materiau;
import com.nante.app.model.Style;
import com.nante.app.model.PrixUnitaireMateriau;
import java.lang.Integer;
import java.util.List;



public interface PrixUnitaireMateriauRepository extends JpaRepository<PrixUnitaireMateriau, Integer> {
    
}
