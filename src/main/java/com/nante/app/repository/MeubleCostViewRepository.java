package com.nante.app.repository;

// import com.nante.app.crud.repository.GenericRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.nante.app.model.Formule_meuble;
import com.nante.app.model.Materiau;
import com.nante.app.model.Style;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.EntityManager;

import com.nante.app.model.MeubleCostView;
import org.springframework.stereotype.Repository;
import java.lang.Long;
import java.util.List;

@Repository
public class MeubleCostViewRepository {

    @PersistenceContext
    private EntityManager entityManager;

    // @Query(nativeQuery = true,
    //        value = "SELECT * from MeubleCostView")
    // List<MeubleCostView> getMeubleCostView();

    // @Query(nativeQuery = true,
    //        value = "SELECT * from MeubleCostView where cout between :prixmin and :prixmax")
    // List<MeubleCostView> getMeubleCostViewFilteredByPrix(@Param("prixmin") double prixmin, @Param("prixmax") double prixmax);

    public List<MeubleCostView> getMeubleCostViewFilteredByPrix(double prixmin, double prixmax) {
        String nativeQuery = "SELECT * FROM MeubleCostView where cout between :prixmin and :prixmax";
        jakarta.persistence.Query query = entityManager.createNativeQuery(nativeQuery, MeubleCostView.class);
        query.setParameter("prixmin", prixmin);
        query.setParameter("prixmax", prixmax);

        @SuppressWarnings("unchecked")
        List<MeubleCostView> meubles = query.getResultList();

        return meubles;
    }

    public List<MeubleCostView> findByCategorieIdAndStyleIdAndVolumeId(int categorieId, int styleId, int volumeId)
    {
        String nativeQuery = "SELECT * FROM MeubleCostView where idcategorie = :categorieId and idstyle = :styleId and idvolume = :volumeId";
        jakarta.persistence.Query query = entityManager.createNativeQuery(nativeQuery, MeubleCostView.class);
        query.setParameter("categorieId", categorieId);
        query.setParameter("styleId", styleId);
        query.setParameter("volumeId", volumeId);


        @SuppressWarnings("unchecked")
        List<MeubleCostView> meubles = query.getResultList();

        return meubles;
    }


}
