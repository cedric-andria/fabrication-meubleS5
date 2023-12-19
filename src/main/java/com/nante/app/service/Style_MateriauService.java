package com.nante.app.service;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

// import com.nante.app.crud.service.GenericService;
import com.nante.app.model.Style_Materiau;
import com.nante.app.model.Style;
import com.nante.app.repository.Style_MateriauRepository;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;

@Service
public class Style_MateriauService {
    @Autowired
    private Style_MateriauRepository style_materiauRepository;

    public void save(Style_Materiau style_materiau) throws Exception
    {
        try {
            style_materiauRepository.save(style_materiau);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

}
