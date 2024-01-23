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
import com.nante.app.model.Style;
import com.nante.app.model.Categorie;

import com.nante.app.service.CategorieService;
import java.lang.Integer;
import org.springframework.beans.factory.annotation.Autowired;


@Controller
public class CategorieController {
    @Autowired
    private CategorieService categorieservice;

    @GetMapping("/newcategorie")
    public String pagenewcategorie()
    {
        return "insertion-categorie";
    }

    @PostMapping("/insertnewcategorie")
    public String insertnewcategorie(@RequestParam(name="nom", required=true) String nom)
    {
        Categorie newcategorie = new Categorie(nom);
        try {
            categorieservice.save(newcategorie);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        
        return "insertion-categorie";
    }

}
