package com.nante.app.service;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

// import com.nante.app.crud.service.GenericService;
import com.nante.app.model.Materiau;
import com.nante.app.model.Ouvrier;
import com.nante.app.repository.OuvrierRepository;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;

@Service
public class OuvrierService {
    @Autowired
    private OuvrierRepository ouvrierRepository;

    public List<Ouvrier> getAllOuvriers() {
        return ouvrierRepository.findAll();
    }

    public Ouvrier getById(int id)
    {
        return ouvrierRepository.getById(id);
    }

    public void save(Ouvrier ouvrier)
    {
        ouvrierRepository.save(ouvrier);
    }
}
