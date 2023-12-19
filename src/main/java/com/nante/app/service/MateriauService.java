package com.nante.app.service;

// import com.nante.app.crud.service.GenericService;
import com.nante.app.model.Materiau;
import com.nante.app.model.Style;
import com.nante.app.repository.MateriauRepository;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;



@Service
public class MateriauService {
    @Autowired
    private MateriauRepository materiauRepository;

    public List<Materiau> getAllMateriaux() {
        return materiauRepository.findAll();
    }

    public Materiau getById(int id)
    {
        return materiauRepository.getById(id);
    }

    public void save(Materiau materiau)
    {
        materiauRepository.save(materiau);
    }
}
