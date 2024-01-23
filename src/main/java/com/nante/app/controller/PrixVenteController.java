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
import com.nante.app.model.MeubleCostView;
import com.nante.app.model.PrixVente;
import com.nante.app.service.PrixVenteService;
import com.nante.app.service.StyleService;
import com.nante.app.service.CategorieService;
import com.nante.app.service.VolumeService;
import com.nante.app.service.MateriauService;
import com.nante.app.service.MeubleCostViewService;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.Integer;
import java.time.LocalDateTime;
import java.lang.Double;

@Controller
public class PrixVenteController {
    @Autowired
    private PrixVenteService prixventeservice;

    @Autowired
    private CategorieService categorieservice;

    @Autowired
    private StyleService styleservice;

    @Autowired
    private VolumeService volumeservice;

    @GetMapping("/newprixvente")
    public String pagenewprixvente(Model model)
    {
        model.addAttribute("categories", categorieservice.getAllCategories());
        model.addAttribute("styles", styleservice.getAllStyles());
        model.addAttribute("volumes", volumeservice.getAllVolumes());
        return "insertion-prixvente";
    }

    @PostMapping("/insertnewprixvente")
    public String insertnewprixvente(@RequestParam(name="idCategorie", required=true) String idCategorie, @RequestParam(name="idStyle", required=true) String idStyle, @RequestParam(name="idVolume", required=true) String idVolume, @RequestParam(name="prix", required=true) String prix, Model model)
    {
        PrixVente newprixvente = new PrixVente(categorieservice.getById(Integer.parseInt(idCategorie)), styleservice.getById(Integer.parseInt(idStyle)), volumeservice.getById(Integer.parseInt(idVolume)), Double.parseDouble(prix), LocalDateTime.now());
        try {
            prixventeservice.save(newprixvente);
            model.addAttribute("categories", categorieservice.getAllCategories());
            model.addAttribute("styles", styleservice.getAllStyles());
            model.addAttribute("volumes", volumeservice.getAllVolumes());
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        
        return "insertion-prixvente";
    }
}
