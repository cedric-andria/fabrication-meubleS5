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
import com.nante.app.model.Ouvrier;

import com.nante.app.service.OuvrierService;
import java.lang.Integer;
import org.springframework.beans.factory.annotation.Autowired;


@Controller
public class OuvrierController {
    @Autowired
    private OuvrierService ouvrierService;

    @GetMapping("/newouvrier")
    public String pagenewouvrier()
    {
        return "insertion-ouvrier";
    }

    @PostMapping("/insertnewouvrier")
    public String insertnewouvrier(@RequestParam(name="description", required=true) String description, @RequestParam(name="taux_horaire", required=true) String taux_horaire)
    {
        Ouvrier newouvrier = new Ouvrier(description, Integer.parseInt(taux_horaire));
        try {
            ouvrierService.save(newouvrier);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        
        return "insertion-ouvrier";
    }
}
