package com.nante.app.service;
// import com.nante.app.crud.service.GenericService;
import com.nante.app.model.Materiau;
import com.nante.app.model.Categorie;
import com.nante.app.model.Genre;
import com.nante.app.repository.CategorieRepository;
import com.nante.app.repository.GenreRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;

@Service
public class GenreService {
    @Autowired
    private GenreRepository genreRepository;

    public List<Genre> getAllgenres() {
        return genreRepository.findAll();
    }

    public Genre getById(int id)
    {
        return genreRepository.getById(id);
    }

    public void save(Genre genre)
    {
        genreRepository.save(genre);
    }
}
