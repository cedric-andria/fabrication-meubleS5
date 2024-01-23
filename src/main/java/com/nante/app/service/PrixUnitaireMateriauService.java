package com.nante.app.service;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

// import com.nante.app.crud.service.GenericService;
import com.nante.app.model.Formule_meuble;
import com.nante.app.model.PrixUnitaireMateriau;
import com.nante.app.model.Style;
import com.nante.app.repository.PrixUnitaireMateriauRepository;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;

@Service
public class PrixUnitaireMateriauService 
{
    @Autowired
    private PrixUnitaireMateriauRepository pum_Repository;

    public void save(PrixUnitaireMateriau style)
    {
        pum_Repository.save(style);
    }
}
