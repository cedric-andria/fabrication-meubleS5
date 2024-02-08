package com.nante.app.service;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;

import com.nante.app.model.Genre;
import com.nante.app.model.StatVente_Genre;
import com.nante.app.model.StockMateriau;
import com.nante.app.model.StockMeuble;
import com.nante.app.model.Vente;
import com.nante.app.repository.StockMeubleRepository;
import com.nante.app.repository.VenteRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class VenteService {
    @Autowired
    private VenteRepository venteRepository;

    @Autowired
    private AchatMateriauService achatMateriauService;

    @Autowired
    private StockMeubleService stockMeubleService;

    @Autowired
    private StockMeubleRepository stockMeubleRepository;

    @PersistenceContext
    EntityManager entityManager;

    public List<Vente> getAllventes() {
        return venteRepository.findAll();
    }

    public Vente getById(int id)
    {
        return venteRepository.getById(id);
    }

    //asiana transaction par rapport am stock ny vente
    @Transactional
    public void save(Vente vente) throws Exception {
        StockMeuble stockMeuble = null;
        //alaina ny dernier stock dia analana aminy quantite de meuble vendus
        List<StockMeuble> laststock = stockMeubleService.findLastRowByCatStyleVol(vente.getCategorie().getId(), vente.getStyle().getId(), vente.getVolume().getId());
        if (laststock.size() != 0) {
            stockMeuble = new StockMeuble(vente.getCategorie(), vente.getStyle(), vente.getVolume(), (laststock.get(0).getQuantite() - vente.getQuantite()), LocalDateTime.now());
            stockMeubleRepository.save(stockMeuble);
        }
        else
        {
            try {
                stockMeuble = new StockMeuble(vente.getCategorie(), vente.getStyle(), vente.getVolume(), vente.getQuantite(), LocalDateTime.now());
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }
            stockMeubleRepository.save(stockMeuble);
        }
    
        venteRepository.save(vente);
    }

    public List<Vente> getStatFromFiltre(int idCategorie, int idStyle, int idVolume, int idGenre)
    {
        // return stockMateriauRepository.findTopByMateriauIdOrderByDate_stockDesc(idMateriau);
        String nativeQuery = "SELECT * FROM Vente where idCategorie = :idCategorie and idStyle = :idStyle and idVolume = :idVolume and idGenre = :idGenre";
        jakarta.persistence.Query query = entityManager.createNativeQuery(nativeQuery, Vente.class);
        query.setParameter("idCategorie", idCategorie);
        query.setParameter("idStyle", idStyle);
        query.setParameter("idVolume", idVolume);
        query.setParameter("idGenre", idGenre);

        @SuppressWarnings("unchecked")
        List<Vente> statvente = query.getResultList();

        return statvente;
    }

    public List<Vente> getAllStatFromOneGenre(int idGenre)
    {
        String nativeQuery = "SELECT * FROM Vente where idGenre = :idGenre";
        jakarta.persistence.Query query = entityManager.createNativeQuery(nativeQuery, Vente.class);
        query.setParameter("idGenre", idGenre);

        @SuppressWarnings("unchecked")
        List<Vente> statvente = query.getResultList();

        return statvente;
    }

    public List<Vente> getAllStatWithoutGenre(int idCategorie, int idStyle, int idVolume)
    {
        String nativeQuery = "SELECT * FROM Vente where idCategorie = :idCategorie and idStyle = :idStyle and idVolume = :idVolume";
        jakarta.persistence.Query query = entityManager.createNativeQuery(nativeQuery, Vente.class);
        query.setParameter("idCategorie", idCategorie);
        query.setParameter("idStyle", idStyle);
        query.setParameter("idVolume", idVolume);


        @SuppressWarnings("unchecked")
        List<Vente> statvente = query.getResultList();

        return statvente;
    }

    public List<Vente> getdistinctCatStyleVol()
    {
        String nativeQuery = "SELECT distinct on (idCategorie, idStyle, idVolume) id, idCategorie, idStyle, idVolume, idGenre, quantite, date_vente FROM Vente";
        jakarta.persistence.Query query = entityManager.createNativeQuery(nativeQuery, Vente.class);

        @SuppressWarnings("unchecked")
        List<Vente> statvente = query.getResultList();

        return statvente;
    }
}
