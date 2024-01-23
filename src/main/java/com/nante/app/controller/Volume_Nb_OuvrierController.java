package com.nante.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import com.nante.app.service.StyleService;
import com.nante.app.service.CategorieService;
import com.nante.app.service.MateriauService;
import com.nante.app.service.Style_MateriauService;
import com.nante.app.service.VolumeService;
import com.nante.app.model.Volume_Nb_Ouvrier;
import com.nante.app.model.Volume;

import com.nante.app.service.Volume_Nb_OuvrierService;
import java.lang.Integer;
import org.springframework.beans.factory.annotation.Autowired;


@Controller
public class Volume_Nb_OuvrierController {
    @Autowired
    private Volume_Nb_OuvrierService volume_nb_ouvrierService;

    @Autowired
    private VolumeService volumeService;

    @Autowired
    private StyleService styleService;

    @Autowired
    private CategorieService categorieService;

    @GetMapping("/new_volume_nbouvrier")
    public String pagenewvolume_nbouvrier(Model model)
    {
        model.addAttribute("volumes", volumeService.getAllVolumes());
        return "insertion-nombre-personne";
    }

    @PostMapping("/insertnew_volume_nb_ouvrier")
    public String insertnewvolume_nbouvrier(@RequestParam(name="idVolume", required=true) String idVolume, @RequestParam(name="nb_personnes", required=true) String nb_personnes, Model model)
    {
        Volume_Nb_Ouvrier volume_nb_ouvrier = new Volume_Nb_Ouvrier(volumeService.getById(Integer.parseInt(idVolume)), Integer.parseInt(nb_personnes));
        try {
            volume_nb_ouvrierService.save(volume_nb_ouvrier);
            model.addAttribute("categories", categorieService.getAllCategories());
            model.addAttribute("styles", styleService.getAllStyles());
            model.addAttribute("volumes", volumeService.getAllVolumes());

        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        
        return "insertion_parametre-duree";
    }
}
