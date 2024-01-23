package com.nante.app.service;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

// import com.nante.app.crud.service.GenericService;
import com.nante.app.model.Materiau;
import com.nante.app.model.Fabrication;
import com.nante.app.repository.FabricationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Service;

@Service
public class FabricationService {
    @Autowired
    private FabricationRepository fabricationRepository;

    // public List<Categorie> getAllCategories() {
    //     return fabricationRepository.findAll();
    // }

    // public Categorie getById(int id)
    // {
    //     return fabricationRepository.getById(id);
    // }
    
    @Transactional
    public void save(Fabrication fabrication)
    {
        fabricationRepository.save(fabrication);
    }
}
