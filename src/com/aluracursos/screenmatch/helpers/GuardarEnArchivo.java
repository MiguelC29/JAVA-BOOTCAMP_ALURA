package com.aluracursos.screenmatch.helpers;

import com.aluracursos.screenmatch.modelos.Titulo;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class GuardarEnArchivo {

    private FileWriter escritura;

    public GuardarEnArchivo(Gson gson, List<Titulo> titulos) throws IOException {
        this.escritura = new FileWriter("titulos.json");
        escritura.write(gson.toJson(titulos));
        escritura.close();
    }
}
