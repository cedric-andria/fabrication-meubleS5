package com.nante.app.service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

// import com.nante.app.crud.service.GenericService;
import com.nante.app.model.Materiau;
import com.nante.app.model.StockMateriau;
import com.nante.app.model.AchatMateriau;
import com.nante.app.repository.AchatMateriauRepository;

import com.nante.app.repository.StockMateriauRepository;
import com.nante.app.service.StockMateriauService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Service;

@Service
public class AchatMateriauService {
    @Autowired
    private AchatMateriauRepository achatMateriauRepository;

    @Autowired
    private StockMateriauRepository stockMateriauRepository;

    @Autowired
    private StockMateriauService stockMateriauService;

    // public List<Categorie> getAllCategories() {
    //     return AchatMateriauRepository.findAll();
    // }

    // public Categorie getById(int id)
    // {
    //     return AchatMateriauRepository.getById(id);
    // }
    @Transactional
    public void save(AchatMateriau achatMateriau)
    {
        // stockMateriauRepository.save(stockmateriau);
        // achatMateriauRepository.save(achatMateriau);
        StockMateriau stockmateriau = null;

        List<StockMateriau> laststock = null;
        laststock = stockMateriauService.findLastRowByIdMateriau(achatMateriau.getMateriau().getId());
        if (laststock.size() != 0) {
            stockmateriau = new StockMateriau(achatMateriau.getMateriau(), achatMateriau.getQuantite() + laststock.get(0).getStock(), LocalDateTime.now());
            stockMateriauRepository.save(stockmateriau);
        }
        else
        {
            stockmateriau = new StockMateriau(achatMateriau.getMateriau(), achatMateriau.getQuantite(), LocalDateTime.now());
            stockMateriauRepository.save(stockmateriau);
        }
        achatMateriauRepository.save(achatMateriau);

    }
}
