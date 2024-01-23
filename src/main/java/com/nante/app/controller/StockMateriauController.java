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
import com.nante.app.model.AchatMateriau;
import com.nante.app.model.Categorie;
import com.nante.app.model.Fabrication;
import com.nante.app.service.FabricationService;
import com.nante.app.service.Formule_meubleService;

import java.lang.Integer;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class StockMateriauController {
    @Autowired
    private StockMateriauService stockMateriauService;

    @GetMapping("/voirrestestock")
    public String restestock(Model model)
    {
        model.addAttribute("stocks", stockMateriauService.getreststock());
        return "reste_stock";
    }

}
