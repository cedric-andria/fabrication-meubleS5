package com.nante.app.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import com.nante.app.service.MateriauService;
// import com.nante.app.crud.service.GenericService;
import com.nante.app.model.Materiau;
import com.nante.app.model.Style;

import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class MateriauController {
    @Autowired
    private MateriauService materiauService;

    @GetMapping("/getallmateriaux")
	public String getallstyle(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);
        List<Materiau> listemateriaux = materiauService.getAllMateriaux();
        model.addAttribute("name", name);
        model.addAttribute("listemateriaux", listemateriaux);

		return "simple-style-view";
	}

    @GetMapping("/getmateriau")
	public String getallstyle(@RequestParam(name="idstyle", required=false, defaultValue="1") int idstyle, Model model) {
        Materiau styleselectionne = materiauService.getById(idstyle);
        model.addAttribute("styleselectionne", styleselectionne);

		return "liste-materiaux";
	}

    @GetMapping("/newmateriau")
    public String pagenewmateriau()
    {
        return "insertion-materiaux";
    }

    @PostMapping("/insertnewmateriau")
    public String insertnewmateriau(@RequestParam(name="nom", required=true) String nom)
    {
        Materiau newmateriau = new Materiau(nom);
        try {
            materiauService.save(newmateriau);
        } catch (Exception e) {
            e.printStackTrace();
            // return "error";
        }
        
        return "insertion-materiaux";
    }
}
