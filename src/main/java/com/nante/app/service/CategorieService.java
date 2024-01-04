package com.nante.app.service;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

// import com.nante.app.crud.service.GenericService;
import com.nante.app.model.Materiau;
import com.nante.app.model.Categorie;
import com.nante.app.repository.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;

@Service
public class CategorieService {
    @Autowired
    private CategorieRepository categorieRepository;

    public List<Categorie> getAllCategories() {
        return categorieRepository.findAll();
    }

    public Categorie getById(int id)
    {
        return categorieRepository.getById(id);
    }

    public void save(Categorie style)
    {
        categorieRepository.save(style);
    }

}
