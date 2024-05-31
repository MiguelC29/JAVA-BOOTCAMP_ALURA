package com.aluracursos.screenmatch.helpers;

import com.aluracursos.screenmatch.modelos.Titulo;
import com.aluracursos.screenmatch.modelos.TituloOmdb;
import com.google.gson.Gson;

public class DTOTitulos {

    // DTO - DATA TRANSFER OBJECT
    private TituloOmdb miTituloOmdb;
    private Titulo miTitulo;

    public DTOTitulos(String json, Gson gson) {
        this.miTituloOmdb = gson.fromJson(json, TituloOmdb.class);
        this.miTitulo = new Titulo(miTituloOmdb);
    }

    public Titulo getMiTitulo() {
        return miTitulo;
    }
}
