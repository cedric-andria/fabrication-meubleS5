package com.nante.app.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import com.nante.app.service.VenteService;
import com.nante.app.service.VolumeService;
import com.nante.app.service.MateriauService;
import com.nante.app.service.StyleService;
import com.nante.app.service.Style_MateriauService;
import com.nante.app.model.Vente;
import com.nante.app.model.Categorie;

import com.nante.app.service.CategorieService;
import com.nante.app.service.GenreService;

import java.lang.Integer;
import java.text.NumberFormat.Style;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;


@Controller
public class VenteController {
    @Autowired
    private VenteService venteservice;

    @Autowired
    private CategorieService categorieservice;

    @Autowired
    private StyleService styleservice;

    @Autowired
    private VolumeService volumeservice;

    @Autowired
    private GenreService genreservice;

    @GetMapping("/newvente")
    public String pagenewvente(Model model)
    {
        model.addAttribute("categories", categorieservice.getAllCategories());
        model.addAttribute("styles", styleservice.getAllStyles());
        model.addAttribute("volumes", volumeservice.getAllVolumes());
        model.addAttribute("genres", genreservice.getAllgenres());

        model.addAttribute("menu_list", "<div class=\"row\" style=\"padding-top: 60px; background-color:cadetblue;\"></div>\r\n" + //
        "    <div class=\"row\">\r\n" + //
        "        <div class=\"list-group col-md-3\" style=\"background-color: cadetblue;\">\r\n" + //
        "            <a href=\"/newcategorie\" class=\"list-group-item\">Insertion categorie</a>\r\n" + //
        "            <a href=\"/newmateriau\" class=\"list-group-item\">Insertion materiau</a>\r\n" + //
        "            <a href=\"/newvolume\" class=\"list-group-item\">Insertion volume</a>\r\n" + //
        "            <a href=\"/newgenre\" class=\"list-group-item\">Insertion genre</a>\r\n" + //
        "            <a href=\"/newstyle\" class=\"list-group-item\">Insertion style</a>\r\n" + //
        "            <a href=\"/newmateriaustyle\" class=\"list-group-item\">Assignation style materiau</a>\r\n" + //
        "            <a href=\"/newprofil\" class=\"list-group-item\">Insertion profil</a>\r\n" + //
        "            <a href=\"/newouvrier\" class=\"list-group-item\">Insertion Ouvrier</a>\r\n" + "            <a href=\"/pageselectionfiltreouvrier\" class=\"list-group-item\">Voir infos ouvrier</a>\r\n" + // //
        "            <a href=\"/new_volume_nbouvrier\" class=\"list-group-item\">Assignation nombre ouvrier à type volume</a>\r\n" + //
        "            <a href=\"/newparamformule\" class=\"list-group-item\">Definition formule d'un meuble</a>\r\n" + //
        "            <a href=\"/newpumateriau\" class=\"list-group-item\">Insertion Prix Unitaire materiau</a>\r\n" + //
        "            <a href=\"/newprixvente\" class=\"list-group-item\">Insertion Prix vente d'un meuble</a>\r\n" + //
        "            <a href=\"/newachatmateriau\" class=\"list-group-item\">Acheter materiau</a>\r\n" + //
        "            <a href=\"/voirrestestock\" class=\"list-group-item\">Voir reste stock materiau profil</a>\r\n" + "            <a href=\"/pageselectionfiltrestatvente\" class=\"list-group-item\">Voir stat vente</a>\r\n" + //  //
        "            <a href=\"/newvente\" class=\"list-group-item\">Vendre meuble</a>\r\n" + //
        "            <a href=\"/fabrication\" class=\"list-group-item\">Fabriquer meuble</a>\r\n" + //
        "        </div>\r\n" + //
        "        <div class=\"col-md-8\">\r\n" + //
        "            <h2 style=\"margin-left: 200px;\"></h2>\r\n" + //
        "        </div>\r\n" + //
        "    </div>");
        return "insertion_vente";
    }

    @PostMapping("/insertnewvente")
    public String insertnewvente(@RequestParam(name="idCategorie", required=true) String idCategorie, @RequestParam(name="idStyle", required=true) String idStyle, @RequestParam(name="idVolume", required=true) String idVolume, @RequestParam(name="idGenre", required=true) String idGenre, @RequestParam(name="quantite", required=true) String quantite, @RequestParam(name="date_vente", required=true) String date_vente, Model model)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = dateFormat.parse(date_vente);
        } catch (ParseException e) {
            
            e.printStackTrace();
        }

        // Convert Date to Instant
        Instant instant = date.toInstant();

        // Specify a time zone (you can choose the appropriate time zone for your application)
        ZoneId zoneId = ZoneId.systemDefault();


        //gestion stock meuble


        Vente newvente = new Vente(categorieservice.getById(Integer.parseInt(idCategorie)), styleservice.getById(Integer.parseInt(idStyle)), volumeservice.getById(Integer.parseInt(idVolume)), genreservice.getById(Integer.parseInt(idGenre)), Integer.parseInt(quantite), instant.atZone(zoneId).toLocalDateTime());
        try {
            venteservice.save(newvente);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("erreur", e.getMessage());
            return "error";
        }
        model.addAttribute("categories", categorieservice.getAllCategories());
        model.addAttribute("styles", styleservice.getAllStyles());
        model.addAttribute("volumes", volumeservice.getAllVolumes());
        model.addAttribute("genres", genreservice.getAllgenres());

        model.addAttribute("menu_list", "<div class=\"row\" style=\"padding-top: 60px; background-color:cadetblue;\"></div>\r\n" + //
                "    <div class=\"row\">\r\n" + //
                "        <div class=\"list-group col-md-3\" style=\"background-color: cadetblue;\">\r\n" + //
                "            <a href=\"/newcategorie\" class=\"list-group-item\">Insertion categorie</a>\r\n" + //
                "            <a href=\"/newmateriau\" class=\"list-group-item\">Insertion materiau</a>\r\n" + //
                "            <a href=\"/newvolume\" class=\"list-group-item\">Insertion volume</a>\r\n" + //
                "            <a href=\"/newgenre\" class=\"list-group-item\">Insertion genre</a>\r\n" + //
                "            <a href=\"/newstyle\" class=\"list-group-item\">Insertion style</a>\r\n" + //
                "            <a href=\"/newmateriaustyle\" class=\"list-group-item\">Assignation style materiau</a>\r\n" + //
                "            <a href=\"/newprofil\" class=\"list-group-item\">Insertion profil</a>\r\n" + //
                "            <a href=\"/newouvrier\" class=\"list-group-item\">Insertion Ouvrier</a>\r\n" + "            <a href=\"/pageselectionfiltreouvrier\" class=\"list-group-item\">Voir infos ouvrier</a>\r\n" + // //
                "            <a href=\"/new_volume_nbouvrier\" class=\"list-group-item\">Assignation nombre ouvrier à type volume</a>\r\n" + //
                "            <a href=\"/newparamformule\" class=\"list-group-item\">Definition formule d'un meuble</a>\r\n" + //
                "            <a href=\"/newpumateriau\" class=\"list-group-item\">Insertion Prix Unitaire materiau</a>\r\n" + //
                "            <a href=\"/newprixvente\" class=\"list-group-item\">Insertion Prix vente d'un meuble</a>\r\n" + //
                "            <a href=\"/newachatmateriau\" class=\"list-group-item\">Acheter materiau</a>\r\n" + //
                "            <a href=\"/voirrestestock\" class=\"list-group-item\">Voir reste stock materiau profil</a>\r\n" + "            <a href=\"/pageselectionfiltrestatvente\" class=\"list-group-item\">Voir stat vente</a>\r\n" + //  //
                "            <a href=\"/newvente\" class=\"list-group-item\">Vendre meuble</a>\r\n" + //
                "            <a href=\"/fabrication\" class=\"list-group-item\">Fabriquer meuble</a>\r\n" + //
                "        </div>\r\n" + //
                "        <div class=\"col-md-8\">\r\n" + //
                "            <h2 style=\"margin-left: 200px;\"></h2>\r\n" + //
                "        </div>\r\n" + //
                "    </div>");
        return "insertion_vente";
    }
}
