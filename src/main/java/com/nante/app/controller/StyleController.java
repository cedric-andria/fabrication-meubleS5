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
import java.lang.Integer;

import com.nante.app.model.Materiau;
// import com.nante.app.crud.service.GenericService;
import com.nante.app.model.Style;
import com.nante.app.model.Style_Materiau;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class StyleController {
    
    private static final Logger logger = LoggerFactory.getLogger(StyleController.class);

    @Autowired
    private StyleService styleservice;

    @Autowired
    private MateriauService materiauservice;

    @Autowired
    private Style_MateriauService style_materiauservice;

    @GetMapping("/getallstyle")
	public String getallstyle(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);
        List<Style> listestyles = styleservice.getAllStyles();
        model.addAttribute("name", name);
        model.addAttribute("listestyles", listestyles);

		return "simple-style-view";
	}

    @GetMapping("/getmateriau/style")
	public String getallstyle(@RequestParam(name="idstyle", required=false, defaultValue="1") int idstyle, Model model) {
        Style styleselectionne = styleservice.getById(idstyle);
        model.addAttribute("styleselectionne", styleselectionne);

		return "liste-materiaux";
	}

    @GetMapping("/newstyle")
    public String pagenewstyle()
    {
        System.out.println("Mandeha tsara");
        return "insertion-style";
    }

    @PostMapping("/insertnewstyle")
    public String insertnewstyle(@RequestParam(name="nom", required=true) String nom)
    {
        Style newstyle = new Style(nom);
        try {
            styleservice.save(newstyle);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        
        return "insertion-style";
    }

    @GetMapping("/newmateriaustyle")
    public String pagenewmateriaustyle(Model model)
    {
        List<Style> listestyles = styleservice.getAllStyles();
        List<Materiau> listemateriaux = materiauservice.getAllMateriaux();

        model.addAttribute("listestyles", listestyles);
        model.addAttribute("listemateriaux", listemateriaux);

        return "insertion-materiaux-style";
    }

    @PostMapping("/insertnewmateriaustyle")
    public String insertnewmateriaustyle(@RequestParam(name="idmateriaux[]", required=true) List<String> idmateriaux, @RequestParam(name="idstyle", required=true) String idstyle, Model model)
    {
        try {
            for (String idmateriau : idmateriaux) 
            {
                style_materiauservice.save(new Style_Materiau(Integer.parseInt(idstyle), Integer.parseInt(idmateriau)));
            }
        } catch (Exception e) {
            model.addAttribute("erreur", e.getMessage());
            logger.error("An error occured", e.getMessage());
            return "error";
        }

        List<Style> listestyles = styleservice.getAllStyles();
        List<Materiau> listemateriaux = materiauservice.getAllMateriaux();

        model.addAttribute("listestyles", listestyles);
        model.addAttribute("listemateriaux", listemateriaux);

        return "insertion-materiaux-style";
    }

}
