package com.nante.app.service;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.nante.app.crud.service.GenericService;
import com.nante.app.model.Materiau;
import com.nante.app.model.Style;

public class StyleService extends GenericService<Style> {
    public List<Materiau> findMatieresOf(int id) throws NotFoundException {
        Style style = this.find(id);
        return style.getMatieres();
    }
}
