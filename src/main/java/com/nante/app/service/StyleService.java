package com.nante.app.service;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

// import com.nante.app.crud.service.GenericService;
import com.nante.app.model.Materiau;
import com.nante.app.model.Style;
import com.nante.app.repository.StyleRepository;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;

@Service
public class StyleService {

    @Autowired
    private StyleRepository styleRepository;

    public List<Style> getAllStyles() {
        return styleRepository.findAll();
    }

    public Style getById(int id)
    {
        return styleRepository.getById(id);
    }

    public void save(Style style)
    {
        styleRepository.save(style);
    }

    // public void insertnewMateriaux(Style style, List<Materiau> materiaux) throws Exception
    // {
    //     try {
    //         style.getMateriaux().addAll(materiaux);
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //         throw e;
    //     }
    // }
    // public List<Materiau> findMatieresOf(int id) throws NotFoundException {
    //     Style style = this.find(id);
    //     return style.getMatieres();
    // }
}
