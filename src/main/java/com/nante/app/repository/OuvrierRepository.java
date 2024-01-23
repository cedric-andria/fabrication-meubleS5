
package com.nante.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nante.app.model.Ouvrier;
import com.nante.app.model.Style;
import java.lang.Integer;
import java.util.List;
public interface OuvrierRepository extends JpaRepository<Ouvrier, Integer> { 
}
