package com.nante.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import com.nante.app.service.StyleService;
import com.nante.app.service.MateriauService;
import com.nante.app.service.Style_MateriauService;
import java.lang.Integer;

import com.nante.app.model.Materiau;
// import com.nante.app.crud.service.GenericService;
import com.nante.app.model.Style;
import com.nante.app.model.Style_Materiau;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class StyleController {
    
    private static final Logger logger = LoggerFactory.getLogger(StyleController.class);

    @Autowired
    private StyleService styleservice;

    @Autowired
    private MateriauService materiauservice;

    @Autowired
    private Style_MateriauService style_materiauservice;

    @GetMapping("/getallstyle")
	public String getallstyle(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);
        List<Style> listestyles = styleservice.getAllStyles();
        model.addAttribute("name", name);
        model.addAttribute("listestyles", listestyles);

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
		return "simple-style-view";
	}

    @GetMapping("/getmateriau/style")
	public String getallstyle(@RequestParam(name="idstyle", required=false, defaultValue="1") int idstyle, Model model) {
        Style styleselectionne = styleservice.getById(idstyle);
        model.addAttribute("styleselectionne", styleselectionne);

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
		return "liste-materiaux";
	}

    @GetMapping("/newstyle")
    public String pagenewstyle(Model model)
    {
        System.out.println("Mandeha tsara");
        // model.addAttribute("menu_list", "<div class=\"row\" style=\"padding-top: 60px; background-color:cadetblue;\"></div>\r\n" + //
                // "    <div class=\"row\">\r\n" + //
                // "        <div class=\"list-group col-md-3\" style=\"background-color: cadetblue;\">\r\n" + //
                // "            <a href=\"/newcategorie\" class=\"list-group-item\">Insertion categorie</a>\r\n" + //
                // "            <a href=\"/newmateriau\" class=\"list-group-item\">Insertion materiau</a>\r\n" + //
                // "            <a href=\"/newvolume\" class=\"list-group-item\">Insertion volume</a>\r\n" + //
                // "            <a href=\"/newgenre\" class=\"list-group-item\">Insertion genre</a>\r\n" + //
                // "            <a href=\"/newstyle\" class=\"list-group-item\">Insertion style</a>\r\n" + //
                // "            <a href=\"/newmateriaustyle\" class=\"list-group-item\">Assignation style materiau</a>\r\n" + //
                // "            <a href=\"/newprofil\" class=\"list-group-item\">Insertion profil</a>\r\n" + //
                // "            <a href=\"/newouvrier\" class=\"list-group-item\">Insertion Ouvrier</a>\r\n" + "            <a href=\"/pageselectionfiltreouvrier\" class=\"list-group-item\">Voir infos ouvrier</a>\r\n" + // //
                // "            <a href=\"/new_volume_nbouvrier\" class=\"list-group-item\">Assignation nombre ouvrier à type volume</a>\r\n" + //
                // "            <a href=\"/newparamformule\" class=\"list-group-item\">Definition formule d'un meuble</a>\r\n" + //
                // "            <a href=\"/newpumateriau\" class=\"list-group-item\">Insertion Prix Unitaire materiau</a>\r\n" + //
                // "            <a href=\"/newprixvente\" class=\"list-group-item\">Insertion Prix vente d'un meuble</a>\r\n" + //
                // "            <a href=\"/newachatmateriau\" class=\"list-group-item\">Acheter materiau</a>\r\n" + //
                // "            <a href=\"/voirrestestock\" class=\"list-group-item\">Voir reste stock materiau profil</a>\r\n" + "            <a href=\"/pageselectionfiltrestatvente\" class=\"list-group-item\">Voir stat vente</a>\r\n" + //  //
                // "            <a href=\"/newvente\" class=\"list-group-item\">Vendre meuble</a>\r\n" + //
                // "            <a href=\"/fabrication\" class=\"list-group-item\">Fabriquer meuble</a>\r\n" + //
                // "        </div>\r\n" + //
                // "        <div class=\"col-md-8\">\r\n" + //
                // "            <h2 style=\"margin-left: 200px;\"></h2>\r\n" + //
                // "        </div>\r\n" + //
                // "    </div>");
        
        model.addAttribute("styles", styleservice.getAllStyles());
        model.addAttribute("imports", StaticImportController.head_imports);
        model.addAttribute("sidebar", StaticImportController.sidebar);
        model.addAttribute("settings", StaticImportController.settings);
        model.addAttribute("footer", StaticImportController.footer);
        model.addAttribute("foot_imports", StaticImportController.foot_imports);
        return "insertion-style";
    }

    @PostMapping("/insertnewstyle")
    public String insertnewstyle(@RequestParam(name="nom", required=true) String nom, Model model)
    {
        Style newstyle = new Style(nom);
        try {
            styleservice.save(newstyle);
        } catch (Exception e) {
            e.printStackTrace();
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
        model.addAttribute("styles", styleservice.getAllStyles());
        model.addAttribute("imports", StaticImportController.head_imports);
        model.addAttribute("sidebar", StaticImportController.sidebar);
        model.addAttribute("settings", StaticImportController.settings);
        model.addAttribute("footer", StaticImportController.footer);
        model.addAttribute("foot_imports", StaticImportController.foot_imports);
        return "insertion-style";
    }

    @GetMapping("/newmateriaustyle")
    public String pagenewmateriaustyle(@RequestParam(name = "idstyle_filtre", defaultValue = "1") String idstyle_filtre, Model model)
    {
        List<Style> listestyles = styleservice.getAllStyles();
        List<Materiau> listemateriaux = materiauservice.getAllMateriaux();

        model.addAttribute("listestyles", listestyles);
        model.addAttribute("listemateriaux", listemateriaux);

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
        try {
            model.addAttribute("styleselectionne", styleservice.getById(Integer.parseInt(idstyle_filtre)));
        } catch (Exception e) {
            e.printStackTrace();            
        }
        model.addAttribute("materiaux", materiauservice.getAllMateriaux());
        model.addAttribute("imports", StaticImportController.head_imports);
        model.addAttribute("sidebar", StaticImportController.sidebar);
        model.addAttribute("settings", StaticImportController.settings);
        model.addAttribute("footer", StaticImportController.footer);
        model.addAttribute("foot_imports", StaticImportController.foot_imports);
        return "insertion-materiaux-style";
    }

    @PostMapping("/insertnewmateriaustyle")
    public String insertnewmateriaustyle(@RequestParam(name="idmateriaux[]", required=true) List<String> idmateriaux, @RequestParam(name="idstyle", required=true) String idstyle, Model model)
    {
        try {
            for (String idmateriau : idmateriaux) 
            {
                style_materiauservice.save(new Style_Materiau(Integer.parseInt(idstyle), Integer.parseInt(idmateriau)));
            }
        } catch (Exception e) {
            model.addAttribute("erreur", e.getMessage());
            logger.error("An error occured", e.getMessage());
            return "error";
        }

        List<Style> listestyles = styleservice.getAllStyles();
        List<Materiau> listemateriaux = materiauservice.getAllMateriaux();

        model.addAttribute("listestyles", listestyles);
        model.addAttribute("listemateriaux", listemateriaux);

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
        try {
            model.addAttribute("styleselectionne", styleservice.getById(Integer.parseInt(idstyle)));
        } catch (Exception e) {
            e.printStackTrace();            
        }
        model.addAttribute("materiaux", materiauservice.getAllMateriaux());
        model.addAttribute("imports", StaticImportController.head_imports);
        model.addAttribute("sidebar", StaticImportController.sidebar);
        model.addAttribute("settings", StaticImportController.settings);
        model.addAttribute("footer", StaticImportController.footer);
        model.addAttribute("foot_imports", StaticImportController.foot_imports);
        return "insertion-materiaux-style";
    }

}
