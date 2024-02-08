package com.nante.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

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
import com.nante.app.service.Volume_Nb_OuvrierService;
import com.nante.app.service.MateriauService;
import com.nante.app.service.MeubleCostViewService;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.Integer;
import java.lang.Double;

@Controller
public class MeubleCostViewController {
  
    @Autowired
    private MeubleCostViewService MeubleCostViewService;

    @Autowired
    private Formule_meubleService formule_meubleService;

    @Autowired
    private Volume_Nb_OuvrierService volume_Nb_OuvrierService;

    @GetMapping("/pageprixminmaxmeuble")
    public String pageprixminmaxmeuble(Model model)
    {
        model.addAttribute("menu_list", "<div class=\"row\" style=\"padding-top: 60px; background-color:cadetblue;\"></div>\r\n" + //
                "    <div class=\"row\">\r\n" + //
                "        <div class=\"list-group col-md-3\" style=\"background-color: cadetblue;\">\r\n" + //
                "            <a href=\"/newcategorie\" class=\"list-group-item\">Insertion categorie</a>\r\n" + //
                "            <a href=\"/newmateriau\" class=\"list-group-item\">Insertion materiau</a>\r\n" + //
                "            <a href=\"/newvolume\" class=\"list-group-item\">Insertion volume</a>\r\n" + //
                "            <a href=\"/newgenre\" class=\"list-group-item\">Insertion genre</a>\r\n" + //
                "            <a href=\"/newstyle\" class=\"list-group-item\">Insertion style</a>\r\n" + //
                "            <a href=\"/newmateriaustyle\" class=\"list-group-item\">Assignation style materiau</a>\r\n" + //
                "            <a href=\"/newprofil\" class=\"list-group-item\">Insertion profil</a>\r\n" + //
                "            <a href=\"/newouvrier\" class=\"list-group-item\">Insertion Ouvrier</a>\r\n" + "            <a href=\"/pageselectionfiltreouvrier\" class=\"list-group-item\">Voir infos ouvrier</a>\r\n" + // //
                "            <a href=\"/new_volume_nbouvrier\" class=\"list-group-item\">Assignation nombre ouvrier à type volume</a>\r\n" + //
                "            <a href=\"/newparamformule\" class=\"list-group-item\">Definition formule d'un meuble</a>\r\n" + //
                "            <a href=\"/newpumateriau\" class=\"list-group-item\">Insertion Prix Unitaire materiau</a>\r\n" + //
                "            <a href=\"/newprixvente\" class=\"list-group-item\">Insertion Prix vente d'un meuble</a>\r\n" + //
                "            <a href=\"/newachatmateriau\" class=\"list-group-item\">Acheter materiau</a>\r\n" + //
                "            <a href=\"/voirrestestock\" class=\"list-group-item\">Voir reste stock materiau profil</a>\r\n" + "            <a href=\"/pageselectionfiltrestatvente\" class=\"list-group-item\">Voir stat vente</a>\r\n" + //  //
                "            <a href=\"/newvente\" class=\"list-group-item\">Vendre meuble</a>\r\n" + //
                "            <a href=\"/fabrication\" class=\"list-group-item\">Fabriquer meuble</a>\r\n" + //
                "        </div>\r\n" + //
                "        <div class=\"col-md-8\">\r\n" + //
                "            <h2 style=\"margin-left: 200px;\"></h2>\r\n" + //
                "        </div>\r\n" + //
                "    </div>");
        return "selection-prix-meuble";
    }

    // @PostMapping("/getmeublesfilteredbyprix")
    // public String submitprixminmax(@RequestParam(name="prixmin", required=true) String prixmin, 
    // @RequestParam(name="prixmax", required=true) String prixmax, Model model)
    // {
    //     double prixminimum = 0;
    //     double prixmaximum = 0;
    //     try {
    //         prixminimum = Double.parseDouble(prixmin);
    //         prixmaximum = Double.parseDouble(prixmax);

    //         System.out.println("prixmin : " + prixminimum);
    //         System.out.println("prixmax : " + prixmaximum);

    //         List<MeubleCostView> meubles = MeubleCostViewService.getMeublesFilteredByPrix(prixminimum, prixmaximum);
           
    //         MeubleCostViewService.hydrate_all_fields(meubles);
    //         model.addAttribute("meubles", meubles);
            
    //         model.addAttribute("menu_list", "<div class=\"row\" style=\"padding-top: 60px; background-color:cadetblue;\"></div>\r\n" + //
    //             "    <div class=\"row\">\r\n" + //
    //             "        <div class=\"list-group col-md-3\" style=\"background-color: cadetblue;\">\r\n" + //
    //             "            <a href=\"/newcategorie\" class=\"list-group-item\">Insertion categorie</a>\r\n" + //
    //             "            <a href=\"/newmateriau\" class=\"list-group-item\">Insertion materiau</a>\r\n" + //
    //             "            <a href=\"/newvolume\" class=\"list-group-item\">Insertion volume</a>\r\n" + //
    //             "            <a href=\"/newgenre\" class=\"list-group-item\">Insertion genre</a>\r\n" + //
    //             "            <a href=\"/newstyle\" class=\"list-group-item\">Insertion style</a>\r\n" + //
    //             "            <a href=\"/newmateriaustyle\" class=\"list-group-item\">Assignation style materiau</a>\r\n" + //
    //             "            <a href=\"/newprofil\" class=\"list-group-item\">Insertion profil</a>\r\n" + //
    //             "            <a href=\"/newouvrier\" class=\"list-group-item\">Insertion Ouvrier</a>\r\n" + "            <a href=\"/pageselectionfiltreouvrier\" class=\"list-group-item\">Voir infos ouvrier</a>\r\n" + // //
    //             "            <a href=\"/new_volume_nbouvrier\" class=\"list-group-item\">Assignation nombre ouvrier à type volume</a>\r\n" + //
    //             "            <a href=\"/newparamformule\" class=\"list-group-item\">Definition formule d'un meuble</a>\r\n" + //
    //             "            <a href=\"/newpumateriau\" class=\"list-group-item\">Insertion Prix Unitaire materiau</a>\r\n" + //
    //             "            <a href=\"/newprixvente\" class=\"list-group-item\">Insertion Prix vente d'un meuble</a>\r\n" + //
    //             "            <a href=\"/newachatmateriau\" class=\"list-group-item\">Acheter materiau</a>\r\n" + //
    //             "            <a href=\"/voirrestestock\" class=\"list-group-item\">Voir reste stock materiau profil</a>\r\n" + "            <a href=\"/pageselectionfiltrestatvente\" class=\"list-group-item\">Voir stat vente</a>\r\n" + //  //
    //             "            <a href=\"/newvente\" class=\"list-group-item\">Vendre meuble</a>\r\n" + //
    //             "            <a href=\"/fabrication\" class=\"list-group-item\">Fabriquer meuble</a>\r\n" + //
    //             "        </div>\r\n" + //
    //             "        <div class=\"col-md-8\">\r\n" + //
    //             "            <h2 style=\"margin-left: 200px;\"></h2>\r\n" + //
    //             "        </div>\r\n" + //
    //             "    </div>");
    //         return "liste-meubles-filtered-by-prix";
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //         return "error";
    //     }
        
    // }

    //sans cout humain ilay eo ambony
    @PostMapping("/getmeublesfilteredbyprix")
    public String submitprixminmax(@RequestParam(name="prixmin", required=true) String prixmin, 
    @RequestParam(name="prixmax", required=true) String prixmax, Model model)
    {

        List<Formule_meuble> formule_meubles = formule_meubleService.getFormule_meubles_native();
        List<MeubleCostView> meublescostview_to_filter = new ArrayList<>();
        List<MeubleCostView> meublescostview_to_return = new ArrayList<>();

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
        // PrixVente prix_vente_correspondant = null;

        MeubleCostView meublecostview_correspondant = null;
        // double benefice = 0;
        for (Formule_meuble formule_meuble : formule_meubles) {
            System.out.println("formule_meuble : " + formule_meuble.getVolume().getId());
            volume_nb_ouvrier = volume_Nb_OuvrierService.findLastRowByIdVolume(formule_meuble.getVolume().getId()).get(0);
            nb_ouvrier = volume_nb_ouvrier.getNb_ouvrier();
            duree_travail = formule_meuble.getDuree_travail();
            taux_horaire = formule_meuble.getOuvrier().getTaux_horaire();
            cout_humain = nb_ouvrier * duree_travail * taux_horaire;

            meublecostview_correspondant = MeubleCostViewService.getByCategorieIdAndStyleIdAndVolumeId(formule_meuble.getCategorie().getId(), formule_meuble.getStyle().getId(), formule_meuble.getVolume().getId()).get(0);
            meublecostview_correspondant.setCouthumain(cout_humain);
            // prix_vente_correspondant = prixventeService.getByCategorieIdAndStyleIdAndVolumeId(formule_meuble.getCategorie().getId(), formule_meuble.getStyle().getId(), formule_meuble.getVolume().getId()).get(0);

            // benefice =  prix_vente_correspondant.getPrix() - (meublecostview_correspondant.getCout() + cout_humain);
            
            // Benefice objBenefice = new Benefice(formule_meuble.getCategorie(), formule_meuble.getStyle(), formule_meuble.getVolume(), benefice);
            // benefices_to_filter.add(objBenefice);
            meublescostview_to_filter.add(meublecostview_correspondant);
        }
        // System.out.println("taille meublecostview to filter : " + meublescostview_to_filter.size());
        //azo ny benefice rehetra

        double prixminimum = 0;
        double prixmaximum = 0;
        try {
            prixminimum = Double.parseDouble(prixmin);
            prixmaximum = Double.parseDouble(prixmax);

            System.out.println("prixmin : " + prixminimum);
            System.out.println("prixmax : " + prixmaximum);

            for (MeubleCostView meublecostview_to_test : meublescostview_to_filter) {
                // System.out.println("cout total mcv : " + (meublecostview_to_test.getCout() + meublecostview_to_test.getCouthumain()));
                if (((meublecostview_to_test.getCout() + meublecostview_to_test.getCouthumain()) >= prixminimum) && ((meublecostview_to_test.getCout() + meublecostview_to_test.getCouthumain()) <= prixmaximum)) {
                    meublescostview_to_return.add(meublecostview_to_test);
                }
            }

            MeubleCostViewService.hydrate_all_fields(meublescostview_to_return);
            model.addAttribute("meubles", meublescostview_to_return);
            model.addAttribute("test", "test");
            model.addAttribute("menu_list", "<div class=\"row\" style=\"padding-top: 60px; background-color:cadetblue;\"></div>\r\n" + //
                "    <div class=\"row\">\r\n" + //
                "        <div class=\"list-group col-md-3\" style=\"background-color: cadetblue;\">\r\n" + //
                "            <a href=\"/newcategorie\" class=\"list-group-item\">Insertion categorie</a>\r\n" + //
                "            <a href=\"/newmateriau\" class=\"list-group-item\">Insertion materiau</a>\r\n" + //
                "            <a href=\"/newvolume\" class=\"list-group-item\">Insertion volume</a>\r\n" + //
                "            <a href=\"/newgenre\" class=\"list-group-item\">Insertion genre</a>\r\n" + //
                "            <a href=\"/newstyle\" class=\"list-group-item\">Insertion style</a>\r\n" + //
                "            <a href=\"/newmateriaustyle\" class=\"list-group-item\">Assignation style materiau</a>\r\n" + //
                "            <a href=\"/newprofil\" class=\"list-group-item\">Insertion profil</a>\r\n" + //
                "            <a href=\"/newouvrier\" class=\"list-group-item\">Insertion Ouvrier</a>\r\n" + "            <a href=\"/pageselectionfiltreouvrier\" class=\"list-group-item\">Voir infos ouvrier</a>\r\n" + // //
                "            <a href=\"/new_volume_nbouvrier\" class=\"list-group-item\">Assignation nombre ouvrier à type volume</a>\r\n" + //
                "            <a href=\"/newparamformule\" class=\"list-group-item\">Definition formule d'un meuble</a>\r\n" + //
                "            <a href=\"/newpumateriau\" class=\"list-group-item\">Insertion Prix Unitaire materiau</a>\r\n" + //
                "            <a href=\"/newprixvente\" class=\"list-group-item\">Insertion Prix vente d'un meuble</a>\r\n" + //
                "            <a href=\"/newachatmateriau\" class=\"list-group-item\">Acheter materiau</a>\r\n" + //
                "            <a href=\"/voirrestestock\" class=\"list-group-item\">Voir reste stock materiau profil</a>\r\n" + "            <a href=\"/pageselectionfiltrestatvente\" class=\"list-group-item\">Voir stat vente</a>\r\n" + //  //
                "            <a href=\"/newvente\" class=\"list-group-item\">Vendre meuble</a>\r\n" + //
                "            <a href=\"/fabrication\" class=\"list-group-item\">Fabriquer meuble</a>\r\n" + //
                "        </div>\r\n" + //
                "        <div class=\"col-md-8\">\r\n" + //
                "            <h2 style=\"margin-left: 200px;\"></h2>\r\n" + //
                "        </div>\r\n" + //
                "    </div>");
            return "liste-meubles-filtered-by-prix";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        
    }

}
