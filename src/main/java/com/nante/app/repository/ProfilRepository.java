package com.nante.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nante.app.model.Profil;
import java.util.List;


public interface ProfilRepository extends JpaRepository<Profil, Integer>
{
    List<Profil> findByExperience(int experience);
}
