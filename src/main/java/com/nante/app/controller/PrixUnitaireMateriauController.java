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
import com.nante.app.model.PrixUnitaireMateriau;
import com.nante.app.model.Style;
import com.nante.app.model.Volume;
import com.nante.app.service.Formule_meubleService;
import com.nante.app.service.StyleService;
import com.nante.app.service.CategorieService;
import com.nante.app.service.VolumeService;
import com.nante.app.service.MateriauService;
import com.nante.app.service.Style_MateriauService;
import com.nante.app.service.PrixUnitaireMateriauService;
import org.springframework.beans.factory.annotation.Autowired;


@Controller
public class PrixUnitaireMateriauController {
    @Autowired
    private PrixUnitaireMateriauService pumService;

    @Autowired
    private CategorieService categorieService;

    @Autowired
    private MateriauService materiauService;

    @GetMapping("/newpumateriau")
    public String pagenewpumateriau(Model model)
    {
        model.addAttribute("materiaux", materiauService.getAllMateriaux());
        return "insertion_prix";
    }

    @PostMapping("/insertpumateriau")
    public String insertpumateriau(@RequestParam(name="idMateriau", required=true) String idMateriau, @RequestParam(name="montant", required=true) String montant, Model model)
    {
        PrixUnitaireMateriau pumateriau = null;
        
        try {
            pumateriau = new PrixUnitaireMateriau(new Materiau(Integer.parseInt(idMateriau)), Double.parseDouble(montant));
            pumService.save(pumateriau);

            model.addAttribute("materiaux", materiauService.getAllMateriaux());
            return "insertion_prix";

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("erreur", e.getMessage());
            return "error";
        }
    }
    
}
