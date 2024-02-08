package com.nante.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import com.nante.app.model.Categorie;
import com.nante.app.model.Formule_meuble;
import com.nante.app.model.Materiau;
import com.nante.app.model.Style;
import com.nante.app.model.Volume;
import com.nante.app.model.Ouvrier;

import com.nante.app.service.Formule_meubleService;
import com.nante.app.service.StyleService;
import com.nante.app.service.OuvrierService;

import com.nante.app.service.CategorieService;
import com.nante.app.service.VolumeService;

import com.nante.app.service.MateriauService;
import com.nante.app.service.Style_MateriauService;
import org.springframework.beans.factory.annotation.Autowired;


import java.lang.Integer;

@Controller
public class Formule_meubleController {
    
    @Autowired
    private CategorieService categorieservice;

    @Autowired
    private VolumeService volumeservice;
    
    @Autowired
    private StyleService styleservice;

    @Autowired
    private MateriauService materiauservice;

    @Autowired
    private OuvrierService ouvrierservice;

    @Autowired
    private Formule_meubleService formule_meubleservice;


    @GetMapping("/newparamformule")
    public String pagenewparamformule(Model model)
    {
        model.addAttribute("categories", categorieservice.getAllCategories());
        model.addAttribute("styles", styleservice.getAllStyles());
        model.addAttribute("volumes", volumeservice.getAllVolumes());

        // model.addAttribute("materiaux", materiauservice.getAllMateriaux());
        model.addAttribute("imports", StaticImportController.head_imports);
        model.addAttribute("sidebar", StaticImportController.sidebar);
        model.addAttribute("settings", StaticImportController.settings);
        model.addAttribute("footer", StaticImportController.footer);
        model.addAttribute("foot_imports", StaticImportController.foot_imports);
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
        return "parametre-quantite-materiaux";
    }

    @PostMapping("/submitparamformule")
    public String submitparamformule(@RequestParam(name="idCategorie", required=true) String idCategorie, 
    @RequestParam(name="idStyle", required=true) String idStyle, 
    @RequestParam(name="idVolume", required=true) String idVolume, Model model)
    {
        model.addAttribute("idCategorie", idCategorie);
        model.addAttribute("idStyle", idStyle);
        model.addAttribute("idVolume", idVolume);
        model.addAttribute("materiaux", styleservice.getById(Integer.parseInt(idStyle)).getMateriaux());
        model.addAttribute("ouvriers", ouvrierservice.getAllOuvriers());
        model.addAttribute("imports", StaticImportController.head_imports);
        model.addAttribute("sidebar", StaticImportController.sidebar);
        model.addAttribute("settings", StaticImportController.settings);
        model.addAttribute("footer", StaticImportController.footer);
        model.addAttribute("foot_imports", StaticImportController.foot_imports);


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
        return "insertion-quantite-materiaux";
    }

    @PostMapping("/insertnewformule")
    public String insertnewformule(@RequestParam(name="idCategorie", required=true) String idCategorie, 
    @RequestParam(name="idStyle", required=true) String idStyle, 
    @RequestParam(name="idVolume", required=true) String idVolume, @RequestParam(name="idMateriau", required=true) String idMateriau, 
    @RequestParam(name="quantite", required=true) String quantite, @RequestParam(name="duree_travail", required=true) String duree_travail, @RequestParam(name="idOuvrier", required=true) String idOuvrier, Model model)
    {
        Formule_meuble formule_meuble = null;
        
        try {
            formule_meuble = new Formule_meuble(new Categorie(Integer.parseInt(idCategorie)), new Style(Integer.parseInt(idStyle)), new Materiau(Integer.parseInt(idMateriau)), new Volume(Integer.parseInt(idVolume)), Double.parseDouble(quantite), Double.parseDouble(duree_travail), new Ouvrier(Integer.parseInt(idOuvrier)));
            formule_meubleservice.save(formule_meuble);

            model.addAttribute("categories", categorieservice.getAllCategories());
            model.addAttribute("styles", styleservice.getAllStyles());
            model.addAttribute("volumes", volumeservice.getAllVolumes());

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("erreur", e.getMessage());
            return "error";
        }

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
        model.addAttribute("categories", categorieservice.getAllCategories());
        model.addAttribute("styles", styleservice.getAllStyles());
        model.addAttribute("volumes", volumeservice.getAllVolumes());

        // model.addAttribute("materiaux", materiauservice.getAllMateriaux());
        model.addAttribute("imports", StaticImportController.head_imports);
        model.addAttribute("sidebar", StaticImportController.sidebar);
        model.addAttribute("settings", StaticImportController.settings);
        model.addAttribute("footer", StaticImportController.footer);
        model.addAttribute("foot_imports", StaticImportController.foot_imports);
        return "parametre-quantite-materiaux";
    }

    @GetMapping("/pageselectionmateriau")
    public String pageselectionmateriau(Model model)
    {
        model.addAttribute("materiaux", materiauservice.getAllMateriaux());
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
        return "selection-materiau";
    }

    @PostMapping("/selectmateriau_to_list_meuble")
    public String selectmateriau_to_list_meuble(@RequestParam(name="idMateriau", required=true) String idMateriau, Model model)
    {
        
        List<Formule_meuble> liste_meubles = formule_meubleservice.getFormule_meubles_by_materiauId(Integer.parseInt(idMateriau));

        model.addAttribute("liste_meubles", liste_meubles);
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
        model.addAttribute("imports", StaticImportController.head_imports);
        model.addAttribute("sidebar", StaticImportController.sidebar);
        model.addAttribute("settings", StaticImportController.settings);
        model.addAttribute("footer", StaticImportController.footer);
        model.addAttribute("foot_imports", StaticImportController.foot_imports);
        return "liste-meubles-by-materiau";
    }
}
