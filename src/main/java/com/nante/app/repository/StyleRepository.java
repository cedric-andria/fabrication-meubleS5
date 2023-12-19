package com.nante.app.repository;

// import com.nante.app.crud.repository.GenericRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nante.app.model.Materiau;
import com.nante.app.model.Style;
import java.lang.Integer;
import java.util.List;

public interface StyleRepository extends JpaRepository<Style, Integer>{
    // List<Materiau> findMateriauxByIdStyle(@Param("idstyle") int idstyle);
    // void insertnewmateriaux(@Param("materiaux") List<Materiau> materiaux);
}
