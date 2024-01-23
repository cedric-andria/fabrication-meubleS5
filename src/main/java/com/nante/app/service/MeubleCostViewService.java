package com.nante.app.service;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

// import com.nante.app.crud.service.GenericService;
import com.nante.app.model.Formule_meuble;
import com.nante.app.model.MeubleCostView;
import com.nante.app.model.Style;
import com.nante.app.model.Categorie;
import com.nante.app.model.Volume;
import com.nante.app.repository.MeubleCostViewRepository;
import com.nante.app.repository.CategorieRepository;
import com.nante.app.repository.StyleRepository;
import com.nante.app.repository.VolumeRepository;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;

@Service
public class MeubleCostViewService {
    @Autowired
    private MeubleCostViewRepository MeubleCostViewRepository;

    @Autowired
    private CategorieRepository CategorieRepository;

    @Autowired
    private StyleRepository StyleRepository;

    @Autowired
    private VolumeRepository VolumeRepository;


    // public List<MeubleCostView> getMeubleCostView() {
    //     return MeubleCostViewRepository.getMeubleCostView();
    // }

    public List<MeubleCostView> getMeublesFilteredByPrix(double prixmin, double prixmax) {
        return MeubleCostViewRepository.getMeubleCostViewFilteredByPrix(prixmin, prixmax);
    }

    public List<MeubleCostView> getByCategorieIdAndStyleIdAndVolumeId(int idCategorie, int idStyle, int idVolume)
    {
        return MeubleCostViewRepository.findByCategorieIdAndStyleIdAndVolumeId(idCategorie, idStyle, idVolume);
    }

    public void hydrate_all_fields(List<MeubleCostView> meubles)
    {
        for (MeubleCostView meuble : meubles) {
            meuble.setCategorie(CategorieRepository.getById(meuble.getIdCategorie()));
            meuble.setStyle(StyleRepository.getById(meuble.getIdStyle()));
            meuble.setVolume(VolumeRepository.getById(meuble.getIdVolume()));
        }
    }
}
