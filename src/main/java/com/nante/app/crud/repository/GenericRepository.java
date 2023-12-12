package com.nante.app.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nante.app.crud.model.GenericModel;

public interface GenericRepository<T extends GenericModel> extends JpaRepository<T, Integer> {

}
