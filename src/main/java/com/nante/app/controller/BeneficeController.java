package com.nante.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.ArrayList;


import com.nante.app.model.Benefice;
import com.nante.app.model.Categorie;
import com.nante.app.model.Formule_meuble;
import com.nante.app.model.Materiau;
import com.nante.app.model.Style;
import com.nante.app.model.Volume_Nb_Ouvrier;
import com.nante.app.model.MeubleCostView;
import com.nante.app.model.PrixVente;
import com.nante.app.service.Formule_meubleService;
import com.nante.app.service.StyleService;
import com.nante.app.service.CategorieService;
import com.nante.app.service.VolumeService;

import com.nante.app.service.MateriauService;
import com.nante.app.service.PrixVenteService;

import com.nante.app.service.MeubleCostViewService;
import com.nante.app.service.Formule_meubleService;
import com.nante.app.service.Volume_Nb_OuvrierService;


import org.springframework.beans.factory.annotation.Autowired;

import java.lang.Integer;
import java.time.LocalDateTime;
import java.lang.Double;

@Controller
public class BeneficeController {
    @Autowired
    private MeubleCostViewService MeubleCostViewService;

    @Autowired
    private Formule_meubleService formule_meubleService;

    @Autowired
    private PrixVenteService prixventeService;

    @Autowired
    private Volume_Nb_OuvrierService volume_Nb_OuvrierService;

    @GetMapping("/pageprixminmaxmeublebenefice")
    public String pageprixminmaxmeuble()
    {
        return "parametre-filtre-benefice";
    }

    @PostMapping("/getmeublesfilteredbybenefice")
    public String submitprixminmax(@RequestParam(name="prixmin", required=true) String prixmin, 
    @RequestParam(name="prixmax", required=true) String prixmax, Model model)
    {

        List<Formule_meuble> formule_meubles = formule_meubleService.getFormule_meubles_native();
        List<Benefice> benefices_to_filter = new ArrayList<>();
        List<Benefice> benefices_to_return = new ArrayList<>();

        if (formule_meubles.size() == 0) {
            model.addAttribute("erreur", "Aucun formule_meuble");
            // model.addAttribute("categories", categorieService.getAllCategories());
            // model.addAttribute("styles", styleService.getAllStyles());
            // model.addAttribute("volumes", volumeService.getAllVolumes());
            return "error";
        }
        Volume_Nb_Ouvrier volume_nb_ouvrier = null;
        int taux_horaire = 0;
        int nb_ouvrier = 0;
        double duree_travail = 0;
        double cout_humain = 0;
        PrixVente prix_vente_correspondant = null;

        MeubleCostView meublecostview_correspondant = null;
        double benefice = 0;
        for (Formule_meuble formule_meuble : formule_meubles) {
            volume_nb_ouvrier = volume_Nb_OuvrierService.findLastRowByIdVolume(formule_meuble.getVolume().getId()).get(0);
            nb_ouvrier = volume_nb_ouvrier.getNb_ouvrier();
            duree_travail = formule_meuble.getDuree_travail();
            taux_horaire = formule_meuble.getOuvrier().getTaux_horaire();
            cout_humain = nb_ouvrier * duree_travail * taux_horaire;

            meublecostview_correspondant = MeubleCostViewService.getByCategorieIdAndStyleIdAndVolumeId(formule_meuble.getCategorie().getId(), formule_meuble.getStyle().getId(), formule_meuble.getVolume().getId()).get(0);
            prix_vente_correspondant = prixventeService.getByCategorieIdAndStyleIdAndVolumeId(formule_meuble.getCategorie().getId(), formule_meuble.getStyle().getId(), formule_meuble.getVolume().getId()).get(0);

            benefice =  prix_vente_correspondant.getPrix() - (meublecostview_correspondant.getCout() + cout_humain);
            
            Benefice objBenefice = new Benefice(formule_meuble.getCategorie(), formule_meuble.getStyle(), formule_meuble.getVolume(), benefice);
            benefices_to_filter.add(objBenefice);
        }
        //azo ny benefice rehetra

        double prixminimum = 0;
        double prixmaximum = 0;
        try {
            prixminimum = Double.parseDouble(prixmin);
            prixmaximum = Double.parseDouble(prixmax);

            System.out.println("prixmin : " + prixminimum);
            System.out.println("prixmax : " + prixmaximum);

            for (Benefice benefice_to_test : benefices_to_filter) {
                if ((benefice_to_test.getMontant() >= prixminimum) && (benefice_to_test.getMontant() <= prixmaximum)) {
                    benefices_to_return.add(benefice_to_test);
                }
            }

            model.addAttribute("benefices", benefices_to_return);
            model.addAttribute("test", "test");
            return "liste-meubles-filtered-by-benefice";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        
    }
}
