package com.nante.app.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

// import com.nante.app.crud.service.GenericService;
import com.nante.app.model.Materiau;
import com.nante.app.model.StockMateriau;
import com.nante.app.model.StockMeuble;
import com.nante.app.model.Fabrication;
import com.nante.app.repository.FabricationRepository;
import com.nante.app.repository.StockMeubleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Service;

@Service
public class FabricationService {
    @Autowired
    private FabricationRepository fabricationRepository;

    @Autowired
    private StockMeubleRepository stockMeubleRepository;

    @Autowired
    private StockMeubleService stockMeubleService;
    // public List<Categorie> getAllCategories() {
    //     return fabricationRepository.findAll();
    // }

    // public Categorie getById(int id)
    // {
    //     return fabricationRepository.getById(id);
    // }
    
    @Transactional
    public void save(Fabrication fabrication) throws Exception
    {
        StockMeuble stockMeuble = null;
        //alaina ny dernier stock dia analana aminy quantite de meuble vendus
        List<StockMeuble> laststock = stockMeubleService.findLastRowByCatStyleVol(fabrication.getCategorie().getId(), fabrication.getStyle().getId(), fabrication.getVolume().getId());
        if (laststock.size() != 0) {
            stockMeuble = new StockMeuble(fabrication.getCategorie(), fabrication.getStyle(), fabrication.getVolume(), (laststock.get(0).getQuantite() + fabrication.getQuantite()), LocalDateTime.now());
            stockMeubleRepository.save(stockMeuble);
        }
        else
        {
            stockMeuble = new StockMeuble(fabrication.getCategorie(), fabrication.getStyle(), fabrication.getVolume(), fabrication.getQuantite(), LocalDateTime.now());
            stockMeubleRepository.save(stockMeuble);
        }

        fabricationRepository.save(fabrication);
    }
}
