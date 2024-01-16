package com.nante.app.repository;

// import com.nante.app.crud.repository.GenericRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nante.app.model.Materiau;
import com.nante.app.model.Style;
import com.nante.app.model.Formule_meuble;
import java.lang.Integer;
import java.util.List;


public interface Formule_meubleRepository extends JpaRepository<Formule_meuble, Integer>
{
    List<Formule_meuble> findByMateriauId(Integer MateriauId);
    
    List<Formule_meuble> findByCategorieIdAndStyleIdAndVolumeId(Integer categorieId, Integer styleId, Integer volumeId);

}
