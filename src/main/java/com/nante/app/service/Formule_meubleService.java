package com.nante.app.service;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

// import com.nante.app.crud.service.GenericService;
import com.nante.app.model.Formule_meuble;
import com.nante.app.model.Style;
import com.nante.app.repository.Formule_meubleRepository;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;

@Service
public class Formule_meubleService {
    @Autowired
    private Formule_meubleRepository formule_meubleRepository;

    public List<Formule_meuble> getAllFormule_meubles() {
        return formule_meubleRepository.findAll();
    }

    public Formule_meuble getById(int id)
    {
        return formule_meubleRepository.getById(id);
    }

    public void save(Formule_meuble style)
    {
        formule_meubleRepository.save(style);
    }

    public List<Formule_meuble> getFormule_meubles_by_materiauId(int idMateriau)
    {
        return formule_meubleRepository.findByMateriauId(idMateriau);
    }

    public List<Formule_meuble> getFormule_meubles_by_categorie_style_volume(int idCategorie, int idStyle, int idVolume)
    {
        return formule_meubleRepository.findByCategorieIdAndStyleIdAndVolumeId(idCategorie, idStyle, idVolume);
    }

}
