package com.nante.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nante.app.model.AchatMateriau;
import java.lang.Integer;
import java.util.List;

public interface AchatMateriauRepository extends JpaRepository<AchatMateriau, Integer> {
    
}
