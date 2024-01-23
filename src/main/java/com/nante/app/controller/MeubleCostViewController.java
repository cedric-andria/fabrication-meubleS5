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
import com.nante.app.service.Formule_meubleService;
import com.nante.app.service.StyleService;
import com.nante.app.service.CategorieService;
import com.nante.app.service.VolumeService;
import com.nante.app.service.MateriauService;
import com.nante.app.service.MeubleCostViewService;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.Integer;
import java.lang.Double;

@Controller
public class MeubleCostViewController {
  
    @Autowired
    private MeubleCostViewService MeubleCostViewService;

    @GetMapping("/pageprixminmaxmeuble")
    public String pageprixminmaxmeuble()
    {
        return "selection-prix-meuble";
    }

    @PostMapping("/getmeublesfilteredbyprix")
    public String submitprixminmax(@RequestParam(name="prixmin", required=true) String prixmin, 
    @RequestParam(name="prixmax", required=true) String prixmax, Model model)
    {
        double prixminimum = 0;
        double prixmaximum = 0;
        try {
            prixminimum = Double.parseDouble(prixmin);
            prixmaximum = Double.parseDouble(prixmax);

            System.out.println("prixmin : " + prixminimum);
            System.out.println("prixmax : " + prixmaximum);

            List<MeubleCostView> meubles = MeubleCostViewService.getMeublesFilteredByPrix(prixminimum, prixmaximum);
           
            MeubleCostViewService.hydrate_all_fields(meubles);
            model.addAttribute("meubles", meubles);
            
            return "liste-meubles-filtered-by-prix";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        
    }

}
