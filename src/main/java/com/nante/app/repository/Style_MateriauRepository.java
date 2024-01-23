package com.nante.app.repository;
// import com.nante.app.crud.repository.GenericRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nante.app.model.Style_Materiau;
import com.nante.app.model.Style;
import java.lang.Integer;
import java.util.List;

public interface Style_MateriauRepository extends JpaRepository <Style_Materiau, Integer>
{
    
}
