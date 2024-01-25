package com.nante.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nante.app.model.Profil;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.EntityManager;
import com.nante.app.repository.ProfilRepository;


@Service
public class ProfilService 
{
    @Autowired
    private ProfilRepository profilRepository;

    @PersistenceContext
    private EntityManager entityManager;


    public List<Profil> getAllProfils() {
        return profilRepository.findAll();
    }

    public Profil getById(int id)
    {
        return profilRepository.getById(id);
    }

    public List<Profil> findByExperience(int exprience)
    {
        return profilRepository.findByExperience(exprience);
    }

    public void save(Profil profil)
    {
        profilRepository.save(profil);
    }
}
