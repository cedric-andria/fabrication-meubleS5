package com.nante.app.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nante.app.model.AchatMateriau;
import com.nante.app.model.StockMateriau;
import com.nante.app.model.StockMeuble;
import com.nante.app.repository.StockMeubleRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service

public class StockMeubleService {
    @Autowired
    private StockMeubleRepository stockMeubleRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void save(StockMeuble stockMeuble)
    {
        stockMeubleRepository.save(stockMeuble);

    }

    public List<StockMeuble> findLastRowByCatStyleVol(int idCategorie, int idStyle, int idVolume)
    {
        // return stockMateriauRepository.findTopByMateriauIdOrderByDate_stockDesc(idMateriau);
        String nativeQuery = "SELECT * FROM StockMeuble where idCategorie = :idCategorie and idStyle = :idStyle and idVolume = :idVolume order by id desc limit 1";
        jakarta.persistence.Query query = entityManager.createNativeQuery(nativeQuery, StockMeuble.class);
        query.setParameter("idCategorie", idCategorie);
        query.setParameter("idStyle", idStyle);
        query.setParameter("idVolume", idVolume);


        @SuppressWarnings("unchecked")
        List<StockMeuble> stockMeuble = query.getResultList();

        return stockMeuble;
    }

}
