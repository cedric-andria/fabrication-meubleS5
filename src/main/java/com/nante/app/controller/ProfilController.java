package com.nante.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    public String pagenewprofil()
    {
        return "insertion-profil";
    }

    @PostMapping("/insertnewprofil")
    public String insertnewprofil(@RequestParam(name="description", required=true) String description, @RequestParam(name="experience", required=true) String experience, @RequestParam(name="coefficient", required=true) String coefficient)
    {
        Profil newprofil = new Profil(description, Integer.parseInt(experience), Integer.parseInt(coefficient));
        try {
            profilservice.save(newprofil);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        
        return "insertion-profil";
    }
}
