package com.nante.app.service;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

// import com.nante.app.crud.service.GenericService;
import com.nante.app.model.Materiau;
import com.nante.app.model.StockMateriau;
import com.nante.app.repository.StockMateriauRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.EntityManager;


@Service
public class StockMateriauService {
    @Autowired
    private StockMateriauRepository stockMateriauRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public List<StockMateriau> getAllStockMateriaux() {
        return stockMateriauRepository.findAll();
    }

    // public Categorie getById(int id)
    // {
    //     return stockMateriauRepository.getById(id);
    // }
    @Transactional
    public void save(StockMateriau stockmateriau)
    {
        stockMateriauRepository.save(stockmateriau);
    }

    public List<StockMateriau> findLastRowByIdMateriau(int idMateriau)
    {
        // return stockMateriauRepository.findTopByMateriauIdOrderByDate_stockDesc(idMateriau);
        String nativeQuery = "SELECT * FROM StockMateriau where idMateriau = :idMateriau order by id desc limit 1";
        jakarta.persistence.Query query = entityManager.createNativeQuery(nativeQuery, StockMateriau.class);
        query.setParameter("idMateriau", idMateriau);

        @SuppressWarnings("unchecked")
        List<StockMateriau> stockmateriau = query.getResultList();

        return stockmateriau;
    }

    public List<StockMateriau> getreststock()
    {
        // return stockMateriauRepository.findTopByMateriauIdOrderByDate_stockDesc(idMateriau);
        String nativeQuery = "SELECT DISTINCT ON (idMateriau) id, idMateriau, stock, date_stock FROM StockMateriau ORDER BY idMateriau, date_stock desc";
        jakarta.persistence.Query query = entityManager.createNativeQuery(nativeQuery, StockMateriau.class);

        @SuppressWarnings("unchecked")
        List<StockMateriau> stockmateriau = query.getResultList();

        return stockmateriau;
    }
}
