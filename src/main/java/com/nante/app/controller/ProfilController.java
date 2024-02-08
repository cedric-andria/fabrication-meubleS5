package com.nante.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nante.app.model.Categorie;
import com.nante.app.model.Profil;
import com.nante.app.service.CategorieService;
import com.nante.app.service.ProfilService;

@Controller
public class ProfilController {
    @Autowired
    private ProfilService profilservice;

    @GetMapping("/newprofil")
    public String pagenewprofil(Model model)
    {
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
        model.addAttribute("profils", profilservice.getAllProfils());
        model.addAttribute("imports", StaticImportController.head_imports);
        model.addAttribute("sidebar", StaticImportController.sidebar);
        model.addAttribute("settings", StaticImportController.settings);
        model.addAttribute("footer", StaticImportController.footer);
        model.addAttribute("foot_imports", StaticImportController.foot_imports);
        return "insertion-profil";
    }

    @PostMapping("/insertnewprofil")
    public String insertnewprofil(@RequestParam(name="description", required=true) String description, @RequestParam(name="experience", required=true) String experience, @RequestParam(name="coefficient", required=true) String coefficient, Model model)
    {
        Profil newprofil = new Profil(description, Integer.parseInt(experience), Integer.parseInt(coefficient));
        try {
            profilservice.save(newprofil);
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
        model.addAttribute("profils", profilservice.getAllProfils());
        model.addAttribute("imports", StaticImportController.head_imports);
        model.addAttribute("sidebar", StaticImportController.sidebar);
        model.addAttribute("settings", StaticImportController.settings);
        model.addAttribute("footer", StaticImportController.footer);
        model.addAttribute("foot_imports", StaticImportController.foot_imports);
        return "insertion-profil";
    }
}
