package com.nante.app.service;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

// import com.nante.app.crud.service.GenericService;
import com.nante.app.model.Materiau;
import com.nante.app.model.StockMateriau;
import com.nante.app.model.Volume_Nb_Ouvrier;

import com.nante.app.repository.Volume_Nb_OuvrierRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.EntityManager;


@Service
public class Volume_Nb_OuvrierService {
    @Autowired
    private Volume_Nb_OuvrierRepository volume_Nb_OuvrierRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public List<Volume_Nb_Ouvrier> getAllStyle_Nb_Ouvriers() {
        return volume_Nb_OuvrierRepository.findAll();
    }

    public Volume_Nb_Ouvrier getById(int id)
    {
        return volume_Nb_OuvrierRepository.getById(id);
    }

    @Transactional
    public void save(Volume_Nb_Ouvrier volume_nb_ouvrier)
    {
        volume_Nb_OuvrierRepository.save(volume_nb_ouvrier);
    }

    public List<Volume_Nb_Ouvrier> findLastRowByIdVolume(int idVolume)
    {
        // return stockMateriauRepository.findTopByMateriauIdOrderByDate_stockDesc(idMateriau);
        String nativeQuery = "SELECT * FROM Volume_Nb_Ouvrier where idVolume = :idVolume order by id desc limit 1";
        jakarta.persistence.Query query = entityManager.createNativeQuery(nativeQuery, Volume_Nb_Ouvrier.class);
        query.setParameter("idVolume", idVolume);

        @SuppressWarnings("unchecked")
        List<Volume_Nb_Ouvrier> volme_nb_ouvrier = query.getResultList();

        return volme_nb_ouvrier;
    }

}
