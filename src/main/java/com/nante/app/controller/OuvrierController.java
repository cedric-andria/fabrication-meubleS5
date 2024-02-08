package com.nante.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.nante.app.service.StyleService;
import com.nante.app.service.MateriauService;
import com.nante.app.service.Style_MateriauService;
import com.nante.app.model.Style;
import com.nante.app.model.Ouvrier;
import com.nante.app.model.Profil;
import com.nante.app.service.OuvrierService;
import com.nante.app.service.ProfilService;
import java.time.temporal.ChronoUnit;

import java.time.Instant;
import java.lang.Integer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;


@Controller
public class OuvrierController {
    @Autowired
    private OuvrierService ouvrierService;

    @Autowired
    private ProfilService profilService;

    @GetMapping("/newouvrier")
    public String pagenewouvrier(Model model)
    {
        model.addAttribute("profils", profilService.getAllProfils());
        // model.addAttribute("menu_list", "<div class=\"row\" style=\"padding-top: 60px; background-color:cadetblue;\"></div>\r\n" + //
        //         "    <div class=\"row\">\r\n" + //
        //         "        <div class=\"list-group col-md-3\" style=\"background-color: cadetblue;\">\r\n" + //
        //         "            <a href=\"/newcategorie\" class=\"list-group-item\">Insertion categorie</a>\r\n" + //
        //         "            <a href=\"/newmateriau\" class=\"list-group-item\">Insertion materiau</a>\r\n" + //
        //         "            <a href=\"/newvolume\" class=\"list-group-item\">Insertion volume</a>\r\n" + //
        //         "            <a href=\"/newgenre\" class=\"list-group-item\">Insertion genre</a>\r\n" + //
        //         "            <a href=\"/newstyle\" class=\"list-group-item\">Insertion style</a>\r\n" + //
        //         "            <a href=\"/newmateriaustyle\" class=\"list-group-item\">Assignation style materiau</a>\r\n" + //
        //         "            <a href=\"/newprofil\" class=\"list-group-item\">Insertion profil</a>\r\n" + //
        //         "            <a href=\"/newouvrier\" class=\"list-group-item\">Insertion Ouvrier</a>\r\n" + "            <a href=\"/pageselectionfiltreouvrier\" class=\"list-group-item\">Voir infos ouvrier</a>\r\n" + // //
        //         "            <a href=\"/new_volume_nbouvrier\" class=\"list-group-item\">Assignation nombre ouvrier à type volume</a>\r\n" + //
        //         "            <a href=\"/newparamformule\" class=\"list-group-item\">Definition formule d'un meuble</a>\r\n" + //
        //         "            <a href=\"/newpumateriau\" class=\"list-group-item\">Insertion Prix Unitaire materiau</a>\r\n" + //
        //         "            <a href=\"/newprixvente\" class=\"list-group-item\">Insertion Prix vente d'un meuble</a>\r\n" + //
        //         "            <a href=\"/newachatmateriau\" class=\"list-group-item\">Acheter materiau</a>\r\n" + //
        //         "            <a href=\"/voirrestestock\" class=\"list-group-item\">Voir reste stock materiau profil</a>\r\n" + "            <a href=\"/pageselectionfiltrestatvente\" class=\"list-group-item\">Voir stat vente</a>\r\n" + //  //
        //         "            <a href=\"/newvente\" class=\"list-group-item\">Vendre meuble</a>\r\n" + //
        //         "            <a href=\"/fabrication\" class=\"list-group-item\">Fabriquer meuble</a>\r\n" + //
        //         "        </div>\r\n" + //
        //         "        <div class=\"col-md-8\">\r\n" + //
        //         "            <h2 style=\"margin-left: 200px;\"></h2>\r\n" + //
        //         "        </div>\r\n" + //
        //         "    </div>");
        model.addAttribute("ouvriers", ouvrierService.getAllOuvriers());
        model.addAttribute("imports", StaticImportController.head_imports);
        model.addAttribute("sidebar", StaticImportController.sidebar);
        model.addAttribute("settings", StaticImportController.settings);
        model.addAttribute("footer", StaticImportController.footer);
        model.addAttribute("foot_imports", StaticImportController.foot_imports);
        return "insertion_ouvrier";
    }

    @PostMapping("/insertnewouvrier")
    public String insertnewouvrier(@RequestParam(name="description", required=true) String description, @RequestParam(name="taux_horaire", required=true) String taux_horaire, @RequestParam(name="idProfil", required=true) String idProfil, @RequestParam(name="date_embauche", required=true) String date_embauche, Model model)
    {
        // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = dateFormat.parse(date_embauche);
        } catch (ParseException e) {
            
            e.printStackTrace();
        }
        // Convert Date to Instant
        Instant instant = date.toInstant();

        // Specify a time zone (you can choose the appropriate time zone for your application)
        ZoneId zoneId = ZoneId.systemDefault();

        Ouvrier newouvrier = new Ouvrier(description, Integer.parseInt(taux_horaire), instant.atZone(zoneId).toLocalDateTime(), profilService.getById(Integer.parseInt(idProfil)));
        try {
            ouvrierService.save(newouvrier);
            model.addAttribute("profils", profilService.getAllProfils());

        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
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
        return "insertion_ouvrier";
    }

    @GetMapping("/pageselectionfiltreouvrier")
    public String pageselectionfiltreouvrier(Model model)
    {
        model.addAttribute("profils", profilService.getAllProfils());
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
        return "filtre_liste_travailleurs";
    }

    @PostMapping("/listeouvriersfiltree")
    public String submitfiltreouvrier(@RequestParam(name="idProfil", required=true) String idProfil, Model model)
    {
        List<Ouvrier> ouvriers_native = ouvrierService.getAllOuvriers();
        List<Profil> profils_native = profilService.getAllProfils();
        List<Ouvrier> ouvriers_taux_calculated = new ArrayList<>();

        LocalDateTime date_embauche_ouvrier = null;
        Profil profilCorrespondant = null;
        Profil profil_teo_aloha = null;

        int difference_annee = 0;
        int taux_actuel = 0;
        for (Ouvrier ouvrier : ouvriers_native) {
            date_embauche_ouvrier = ouvrier.getDate_embauche();
            difference_annee = (int)ChronoUnit.YEARS.between(date_embauche_ouvrier, LocalDateTime.now());
            System.out.println("Difference annee : " + difference_annee);
            int count = 0;

            for (Profil profil : profils_native) {
                if (difference_annee > profil.getExperience()) {
                    if(count == (profils_native.size()-1))
                    {
                        profilCorrespondant = profil;
                    }
                }
                else if (difference_annee < profil.getExperience())
                {
                    if (count == 0) {
                        profilCorrespondant = profil;
                    }
                    profilCorrespondant = profil_teo_aloha;
                }
                else if (difference_annee == profil.getExperience())
                {
                    profilCorrespondant = profil;
                }
                profil_teo_aloha = profil;
                count ++;
            }
            taux_actuel = profilCorrespondant.getCoeff_taux_ouvrier() * ouvrier.getTaux_horaire();
            ouvrier.setTaux_horaire_actuel(taux_actuel);
            ouvrier.setProfil(profilCorrespondant);
            ouvriers_taux_calculated.add(ouvrier);
        }
        System.out.println("ouvriers taux calculated size :" + ouvriers_taux_calculated.size());

        //raha tsy misy filtre
        if (idProfil.equalsIgnoreCase("all")) 
        {
            model.addAttribute("ouvrier", ouvriers_taux_calculated);   
            return "liste-travailleurs";
        }

        List<Ouvrier> ouvriers_filtres = new ArrayList<>();
        Profil profil_filtre = profilService.getById(Integer.parseInt(idProfil));
        System.out.println("profil filtre " + profil_filtre.getDescription());
        for (Ouvrier ouvrier : ouvriers_taux_calculated) {
            System.out.println("profil filtre id : " + profil_filtre.getId());
            System.out.println("ouvrier.getProfil().getID() : " + ouvrier.getProfil().getId());

            if (profil_filtre.getId() == ouvrier.getProfil().getId()) {
                ouvriers_filtres.add(ouvrier);
            }
        }
        model.addAttribute("ouvriers", ouvriers_filtres);
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
        return "liste-travailleurs";

    }
}
