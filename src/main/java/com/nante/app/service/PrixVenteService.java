package com.nante.app.service;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

// import com.nante.app.crud.service.GenericService;
import com.nante.app.model.Materiau;
import com.nante.app.model.PrixVente;
import com.nante.app.repository.PrixVenteRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.EntityManager;


@Service
public class PrixVenteService {
    @Autowired
    private PrixVenteRepository prixventeRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public List<PrixVente> getAllPrixVentes() {
        return prixventeRepository.findAll();
    }

    // public Categorie getById(int id)
    // {
    //     return prixventeRepository.getById(id);
    // }
    public void save(PrixVente prixvente)
    {
        prixventeRepository.save(prixvente);
    }

    public List<PrixVente> getByCategorieIdAndStyleIdAndVolumeId(int idCategorie, int idStyle, int idVolume)
    {
        // return stockMateriauRepository.findTopByMateriauIdOrderByDate_stockDesc(idMateriau);
        String nativeQuery = "SELECT DISTINCT ON (idCategorie, idStyle, idVolume) id, idCategorie, idStyle, idVolume, prix, submit_date FROM PrixVente where idCategorie = :idCategorie and idStyle = :idStyle and idVolume = :idVolume ORDER BY idCategorie, idStyle, idVolume, submit_date desc limit 1";
        jakarta.persistence.Query query = entityManager.createNativeQuery(nativeQuery, PrixVente.class);
        
        query.setParameter("idCategorie", idCategorie);
        query.setParameter("idStyle", idStyle);
        query.setParameter("idVolume", idVolume);

        @SuppressWarnings("unchecked")
        List<PrixVente> prixventes = query.getResultList();

        return prixventes;
    }
}
