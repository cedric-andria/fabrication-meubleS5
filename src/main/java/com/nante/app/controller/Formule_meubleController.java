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
import com.nante.app.service.Formule_meubleService;
import com.nante.app.service.StyleService;
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
    private Formule_meubleService formule_meubleservice;


    @GetMapping("/newparamformule")
    public String pagenewparamformule(Model model)
    {
        model.addAttribute("categories", categorieservice.getAllCategories());
        model.addAttribute("styles", styleservice.getAllStyles());
        model.addAttribute("volumes", volumeservice.getAllVolumes());

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
        model.addAttribute("materiaux", materiauservice.getAllMateriaux());
        
        return "insertion-quantite-materiaux";
    }

    @PostMapping("/insertnewformule")
    public String insertnewformule(@RequestParam(name="idCategorie", required=true) String idCategorie, 
    @RequestParam(name="idStyle", required=true) String idStyle, 
    @RequestParam(name="idVolume", required=true) String idVolume, @RequestParam(name="idMateriau", required=true) String idMateriau, 
    @RequestParam(name="quantite", required=true) String quantite, Model model)
    {
        Formule_meuble formule_meuble = null;
        
        try {
            formule_meuble = new Formule_meuble(new Categorie(Integer.parseInt(idCategorie)), new Style(Integer.parseInt(idStyle)), new Materiau(Integer.parseInt(idMateriau)), new Volume(Integer.parseInt(idVolume)), Double.parseDouble(quantite));
            formule_meubleservice.save(formule_meuble);

            model.addAttribute("categories", categorieservice.getAllCategories());
            model.addAttribute("styles", styleservice.getAllStyles());
            model.addAttribute("volumes", volumeservice.getAllVolumes());

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("erreur", e.getMessage());
            return "error";
        }

        return "parametre-quantite-materiaux";
    }

    @GetMapping("/pageselectionmateriau")
    public String pageselectionmateriau(Model model)
    {
        model.addAttribute("materiaux", materiauservice.getAllMateriaux());

        return "selection-materiau";
    }

    @PostMapping("/selectmateriau_to_list_meuble")
    public String selectmateriau_to_list_meuble(@RequestParam(name="idMateriau", required=true) String idMateriau, Model model)
    {

        List<Formule_meuble> liste_meubles = formule_meubleservice.getFormule_meubles_by_materiauId(Integer.parseInt(idMateriau));

        model.addAttribute("liste_meubles", liste_meubles);
        return "liste-meubles-by-materiau";
    }
}
