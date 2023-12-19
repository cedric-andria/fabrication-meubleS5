package com.nante.app.repository;

// import com.nante.app.crud.repository.GenericRepository;
import com.nante.app.model.Materiau;
import com.nante.app.model.Style;
import java.lang.Integer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MateriauRepository extends JpaRepository<Materiau, Integer> {
    
}
