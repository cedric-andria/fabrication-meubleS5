package com.nante.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import com.nante.app.service.StyleService;
import com.nante.app.service.CategorieService;
import com.nante.app.service.GenreService;
import com.nante.app.service.MateriauService;
import com.nante.app.service.Style_MateriauService;
import com.nante.app.service.VenteService;
import com.nante.app.service.VolumeService;
import com.nante.app.model.Style;
import com.nante.app.model.Vente;
import com.nante.app.model.Volume;
import com.nante.app.model.Categorie;
import com.nante.app.model.CategorieStyleVolume;
import com.nante.app.model.Genre;
import com.nante.app.model.Ouvrier;
import com.nante.app.model.Profil;
import com.nante.app.model.StatVente_Genre;
import com.nante.app.service.OuvrierService;
import com.nante.app.service.ProfilService;

import java.time.temporal.ChronoUnit;

import java.time.Instant;
import java.lang.Integer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;


@Controller
public class StatVente_GenreController {

    @Autowired
    private CategorieService categorieService;

    @Autowired
    private StyleService styleService;

    @Autowired
    private VolumeService volumeService;

    @Autowired
    private GenreService genreService;

    @Autowired
    private VenteService venteService;

    @GetMapping("/pageselectionfiltrestatvente")
    public String pageselectionfiltre_statvente(Model model)
    {
        model.addAttribute("categories", categorieService.getAllCategories());
        model.addAttribute("styles", styleService.getAllStyles());
        model.addAttribute("volumes", volumeService.getAllVolumes());
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
                "    </div>");
        return "selection_filtre_statvente";
    }

    @PostMapping("/statistique_vente")
    public String submitfiltrestatvente(@RequestParam(name="idCategorie", required=false) String idCategorie, @RequestParam(name="idStyle", required=false) String idStyle, @RequestParam(name="idVolume", required=false) String idVolume, @RequestParam(name="all_filtre", required=false) String all_filtre, Model model)
    {
        List<Vente> allVentes = venteService.getAllventes();
        double somme_nb = 0;
        for (Vente vente : allVentes) {
            somme_nb += vente.getQuantite();
        }
        List<Genre> all_genres = genreService.getAllgenres();
        List<StatVente_Genre> list_stat_genre_to_pass = new ArrayList<>();

        //test autre methode de donnees stat using HashMap
        HashMap<String, List<StatVente_Genre>> hashMap_genre_stat = new HashMap<>();
        HashMap<String, Integer> hashmap_total_par_csv = new HashMap<>();
        List<CategorieStyleVolume> cat_style_volume = new ArrayList<>();


        if (all_filtre.equals("oui")) 
        {
           
            List<Vente> ventedistinct_cat_style_volume = venteService.getdistinctCatStyleVol();
            for (Vente vente : ventedistinct_cat_style_volume) {
                cat_style_volume.add(new CategorieStyleVolume(vente.getCategorie(), vente.getStyle(), vente.getVolume()));
            }
            
            //angalana ny total par csv ho affichena
            for (CategorieStyleVolume csv : cat_style_volume) {
                int total_vente_one_csv = 0;
                List<Vente> list_vente_one_csv = venteService.getAllStatWithoutGenre(csv.getCategorie().getId(), csv.getStyle().getId(), csv.getVolume().getId());
                
                //maka ny total vente pour un csv
                for (Vente vente_one_csv : list_vente_one_csv) {
                    total_vente_one_csv += vente_one_csv.getQuantite();
                    //azo ny total isaky ny karazany
                }
                hashmap_total_par_csv.put(csv.getCategorie().getNom() + "-" + csv.getStyle().getNom() + "-" + csv.getVolume().getNom(), total_vente_one_csv);
            }
            
            for (Genre one_genre : all_genres) {
                //filtrena par genre ny total
                list_stat_genre_to_pass.clear();
                for (CategorieStyleVolume csv : cat_style_volume) {
                    //isaky ny karazana meuble dia alaina stat par genre
                    int total_vente_one_csv = 0;
                    List<Vente> list_vente_one_csv = venteService.getAllStatWithoutGenre(csv.getCategorie().getId(), csv.getStyle().getId(), csv.getVolume().getId());
                    
                    //maka ny total vente pour un csv
                    for (Vente vente_one_csv : list_vente_one_csv) {
                        total_vente_one_csv += vente_one_csv.getQuantite();
                        //azo ny total isaky ny karazany
                    }
                    System.out.println("total vente one csv : " + total_vente_one_csv);
                    ///maka ny total vente pour un csv

                    List<Vente> stats_one_genre = venteService.getStatFromFiltre(csv.getCategorie().getId(), csv.getStyle().getId(), csv.getVolume().getId(), one_genre.getId());
                    double nb_one_genre = 0;
                    for (Vente vente_one_genre : stats_one_genre) {
                        nb_one_genre += vente_one_genre.getQuantite();
                    }
                    System.out.println("nb one genre : " + nb_one_genre);
                    //azo daolo ny stat ho an'ny genre anakiray isaky ny karazany (rehefa mi boucle dia atao par genre, efa milahatra par genre sisa)
                    //vita ny genre iray zay vao mandeha ny genre manaraka
                    StatVente_Genre stat_one_genre = new StatVente_Genre(csv.getCategorie(), csv.getStyle(), csv.getVolume(), one_genre, (nb_one_genre/total_vente_one_csv)*100);
                    
                    list_stat_genre_to_pass.add(stat_one_genre);
                }
                hashMap_genre_stat.put(one_genre.getDescription(), new ArrayList<StatVente_Genre>(list_stat_genre_to_pass));
            }

            model.addAttribute("stats_par_genre", list_stat_genre_to_pass);
            model.addAttribute("karazana_meuble", cat_style_volume);
            model.addAttribute("genres", all_genres);
            model.addAttribute("nb_genre", all_genres.size());
            model.addAttribute("hashmap_genre_stat", hashMap_genre_stat);
            model.addAttribute("hashmap_total_par_csv", hashmap_total_par_csv);

            for (StatVente_Genre stat: hashMap_genre_stat.get("femme")) {
                System.out.println("hashmap Femme : " + stat.getStat());
            } 
            // System.out.println(hashMap_genre_stat.size());
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
            return "statistique_vente_all_filter";
        }


        //raha misy filtre
        CategorieStyleVolume csv_filtre = new CategorieStyleVolume(categorieService.getById(Integer.parseInt(idCategorie)), styleService.getById(Integer.parseInt(idStyle)), volumeService.getById(Integer.parseInt(idVolume)));
        cat_style_volume.add(csv_filtre);
        int total_vente_one_csv = 0;

        List<Vente> list_vente_one_csv = venteService.getAllStatWithoutGenre(csv_filtre.getCategorie().getId(), csv_filtre.getStyle().getId(), csv_filtre.getVolume().getId());
        
        //maka ny total vente pour un csv_filtre
        for (Vente vente_one_csv : list_vente_one_csv) {
            total_vente_one_csv += vente_one_csv.getQuantite();
            //azo ny total isaky ny karazany
        }
        hashmap_total_par_csv.put(csv_filtre.getCategorie().getNom() + "-" + csv_filtre.getStyle().getNom() + "-" + csv_filtre.getVolume().getNom(), total_vente_one_csv);

        for (Genre one_genre : all_genres) {
            //filtrena par genre ny total
            list_stat_genre_to_pass.clear();
                
            System.out.println("total vente one csv : " + total_vente_one_csv);
            ///maka ny total vente pour un csv

            List<Vente> stats_one_genre = venteService.getStatFromFiltre(csv_filtre.getCategorie().getId(), csv_filtre.getStyle().getId(), csv_filtre.getVolume().getId(), one_genre.getId());
            double nb_one_genre = 0;
            for (Vente vente_one_genre : stats_one_genre) {
                nb_one_genre += vente_one_genre.getQuantite();
            }
            System.out.println("nb one genre : " + nb_one_genre);
            //azo daolo ny stat ho an'ny genre anakiray isaky ny karazany (rehefa mi boucle dia atao par genre, efa milahatra par genre sisa)
            //vita ny genre iray zay vao mandeha ny genre manaraka
            StatVente_Genre stat_one_genre = new StatVente_Genre(csv_filtre.getCategorie(), csv_filtre.getStyle(), csv_filtre.getVolume(), one_genre, (nb_one_genre/total_vente_one_csv)*100);
            
            list_stat_genre_to_pass.add(stat_one_genre);
        
            hashMap_genre_stat.put(one_genre.getDescription(), new ArrayList<StatVente_Genre>(list_stat_genre_to_pass));
        }
        model.addAttribute("stats_par_genre", list_stat_genre_to_pass);
        model.addAttribute("karazana_meuble", cat_style_volume);
        model.addAttribute("genres", all_genres);
        model.addAttribute("nb_genre", all_genres.size());
        model.addAttribute("hashmap_genre_stat", hashMap_genre_stat);
        model.addAttribute("hashmap_total_par_csv", hashmap_total_par_csv);

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
        return "statistique_vente_wt_filter";

    }

}
