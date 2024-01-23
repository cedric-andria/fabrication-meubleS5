package com.nante.app.service;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

// import com.nante.app.crud.service.GenericService;
import com.nante.app.model.Materiau;
import com.nante.app.model.Style;
import com.nante.app.model.Volume;
import com.nante.app.repository.VolumeRepository;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;

@Service
public class VolumeService {
    @Autowired
    private VolumeRepository volumeRepository;
    
    public List<Volume> getAllVolumes() {
        return volumeRepository.findAll();
    }

    public Volume getById(int id)
    {
        return volumeRepository.getById(id);
    }

    public void save(Volume style)
    {
        volumeRepository.save(style);
    }

}
