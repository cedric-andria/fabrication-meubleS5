package com.nante.app.model;

import com.nante.app.crud.model.GenericModel;

public class StyleMateriau extends GenericModel {
    int id ;
    int idStyle ;
    int idMateriau ;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getIdStyle() {
        return idStyle;
    }
    public void setIdStyle(int idStyle) {
        this.idStyle = idStyle;
    }
    public int getIdMateriau() {
        return idMateriau;
    }
    public void setIdMateriau(int idMateriau) {
        this.idMateriau = idMateriau;
    }

}
