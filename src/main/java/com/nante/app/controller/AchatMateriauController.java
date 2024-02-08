package com.nante.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import com.nante.app.service.StyleService;
import com.nante.app.service.MateriauService;
import com.nante.app.service.StockMateriauService;
import com.nante.app.service.Style_MateriauService;
import com.nante.app.model.Style;
import com.nante.app.model.Materiau;
import com.nante.app.model.AchatMateriau;
import com.nante.app.model.Categorie;
import com.nante.app.model.StockMateriau;
import com.nante.app.service.AchatMateriauService;
import com.nante.app.service.Formule_meubleService;

import java.lang.Integer;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;


import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class AchatMateriauController {
    @Autowired
    private AchatMateriauService achatMateriauService;

    @Autowired
    private Formule_meubleService formuleMeubleService;

    @Autowired
    private MateriauService materiauService;

    @Autowired
    private StockMateriauService stockMateriauService;

    @GetMapping("/newachatmateriau")
    public String pagenewachatmateriau(Model model)
    {   
        model.addAttribute("materiaux", materiauService.getAllMateriaux());
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
        return "insertion-materiaux";
    }    

    @PostMapping("/insertnewachatmateriau")
    public String insertnewachatmateriau(@RequestParam(name="idMateriau", required=true) String idMateriau, 
    @RequestParam(name="quantite", required=true) String quantite, Model model)
    {
        AchatMateriau achatmateriau = null;
        StockMateriau stockmateriau = null;
        try {
            Materiau materiau = materiauService.getById(Integer.parseInt(idMateriau));
            achatmateriau = new AchatMateriau(materiau, Integer.parseInt(quantite), LocalDateTime.now());

            achatMateriauService.save(achatmateriau);
            
            model.addAttribute("materiaux", materiauService.getAllMateriaux());
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
        model.addAttribute("imports", StaticImportController.head_imports);
        model.addAttribute("sidebar", StaticImportController.sidebar);
        model.addAttribute("settings", StaticImportController.settings);
        model.addAttribute("footer", StaticImportController.footer);
        model.addAttribute("foot_imports", StaticImportController.foot_imports);
        return "insertion-materiaux";
    }
}
