package com.nante.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import com.nante.app.service.StyleService;
import com.nante.app.service.MateriauService;
import com.nante.app.service.Style_MateriauService;
import com.nante.app.model.Volume;
import com.nante.app.model.Style;
import com.nante.app.service.VolumeService;
import java.lang.Integer;

@Controller
public class VolumeController {
    @Autowired
    private VolumeService volumeservice;

    @GetMapping("/newvolume")
    public String pagenewvolume()
    {
        return "insertion-type-volume";
    }

    @PostMapping("/insertnewvolume")
    public String insertnewvolume(@RequestParam(name="nom", required=true) String nom)
    {
        Volume newvolume = new Volume(nom);
        try {
            volumeservice.save(newvolume);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        
        return "insertion-type-volume";
    }

}
