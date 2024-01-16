package com.nante.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.ArrayList;
import java.sql.Date;
import java.time.LocalDateTime;

import com.nante.app.service.StyleService;
import com.nante.app.service.MateriauService;
import com.nante.app.service.StockMateriauService;
import com.nante.app.service.Style_MateriauService;
import com.nante.app.service.VolumeService;

import com.nante.app.service.FabricationService;

import com.nante.app.model.Style;
import com.nante.app.model.AchatMateriau;
import com.nante.app.model.Categorie;
import com.nante.app.model.Volume;
import com.nante.app.model.Fabrication;
import com.nante.app.model.Formule_meuble;
import com.nante.app.model.Materiau;
import com.nante.app.model.Materiau_quantite;
import com.nante.app.model.StockMateriau;
import com.nante.app.service.CategorieService;
import com.nante.app.service.FabricationService;
import com.nante.app.service.Formule_meubleService;

import java.lang.Integer;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class FabricationController {
    @Autowired
    private FabricationService fabricationService;

    @Autowired
    private Formule_meubleService formuleMeubleService;

    @Autowired
    private StockMateriauService stockMateriauService;

    @Autowired
    private MateriauService materiauService;

    @Autowired
    private CategorieService categorieService;
    
    @Autowired
    private StyleService styleService;
    
    @Autowired
    private VolumeService volumeService;

    @GetMapping("/fabrication")
    public String pagefabrication(Model model)
    {
        model.addAttribute("categories", categorieService.getAllCategories());
        model.addAttribute("styles", styleService.getAllStyles());
        model.addAttribute("volumes", volumeService.getAllVolumes());

        return "fabrication";
    }    

    @PostMapping("/insertnewfabrication")
    public String insertnewfabrication(@RequestParam(name="idCategorie", required=true) String idCategorie, 
    @RequestParam(name="idStyle", required=true) String idStyle, 
    @RequestParam(name="idVolume", required=true) String idVolume, 
    @RequestParam(name="quantite", required=true) String quantite, Model model)
    {
        Fabrication fabrication = null; 
        try {
            List<Materiau_quantite> materiau_quantites = new ArrayList<>();

            int quantite_meuble = Integer.parseInt(quantite);
            //maka anle liste de Formule_meubles
            List<Formule_meuble> formule_meubles_by_many_ids = formuleMeubleService.getFormule_meubles_by_categorie_style_volume(Integer.parseInt(idCategorie), Integer.parseInt(idStyle), Integer.parseInt(idVolume));
            //raha tsy misy ilay formule_meuble
            if (formule_meubles_by_many_ids.size() == 0) {
                model.addAttribute("form_meuble_null", "Cette formule de fabrication n'est pas encore disponible");
                model.addAttribute("categories", categorieService.getAllCategories());
                model.addAttribute("styles", styleService.getAllStyles());
                model.addAttribute("volumes", volumeService.getAllVolumes());
                return "fabrication";
            }
            List<StockMateriau> laststock = null;
            int stock_disponible = 0;
            int stock_ilaina = 0;
            int stock_restant = 0;
            
            List<StockMateriau> stock_to_save = new ArrayList<>();

            for (Formule_meuble formule_meuble : formule_meubles_by_many_ids) {
                laststock = stockMateriauService.findLastRowByIdMateriau(formule_meuble.getMateriau().getId());
                stock_disponible = laststock.get(0).getStock();
                stock_ilaina = (int)formule_meuble.getQuantite() * quantite_meuble;
                stock_restant = stock_disponible - stock_ilaina;
                if (stock_restant < 0) {
                    Materiau_quantite materiau_quantite = new Materiau_quantite(materiauService.getById(formule_meuble.getMateriau().getId()), (stock_restant * (-1)));
                    materiau_quantites.add(materiau_quantite);

                    model.addAttribute("materiau_quantites", materiau_quantites);

                    if (formule_meubles_by_many_ids.indexOf(formule_meuble) == (formule_meubles_by_many_ids.size() - 1)) {
                        return "stock_insuff_fabrication";
                    }
                }
                else
                {
                    stock_to_save.add(new StockMateriau(formule_meuble.getMateriau(), stock_disponible - stock_ilaina, LocalDateTime.now()));
                }
            }

            for (StockMateriau stockMateriau : stock_to_save) {
                stockMateriauService.save(stockMateriau);

            }

            fabrication = new Fabrication(new Categorie(Integer.parseInt(idCategorie)), new Style(Integer.parseInt(idStyle)), new Volume(Integer.parseInt(idVolume)), Integer.parseInt(quantite));
            fabricationService.save(fabrication);
            model.addAttribute("categories", categorieService.getAllCategories());
            model.addAttribute("styles", styleService.getAllStyles());
            model.addAttribute("volumes", volumeService.getAllVolumes());

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("erreur", e.getMessage());
            return "error";
        }
        
        return "fabrication";
    }
}
