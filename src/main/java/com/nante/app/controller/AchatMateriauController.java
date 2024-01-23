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
        return "achat_matiere";
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
        
        return "achat_matiere";
    }
}
