package com.aluracursos.screenmatch.principal;

import com.aluracursos.screenmatch.excepcion.ErrorEnConversionDeDuracionException;
import com.aluracursos.screenmatch.helpers.CosultaAPI;
import com.aluracursos.screenmatch.helpers.DTOTitulos;
import com.aluracursos.screenmatch.helpers.GuardarEnArchivo;
import com.aluracursos.screenmatch.modelos.Titulo;
import com.aluracursos.screenmatch.modelos.TituloOmdb;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrincipalConBusquedaReestructurado {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner teclado = new Scanner(System.in);
        List<Titulo> titulos = new ArrayList<>();
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();

        while (true) {
            System.out.println("Escribe el nombre de la pelicula:");
            var busqueda = teclado.nextLine();

            if (busqueda.equalsIgnoreCase("salir")) break;

            try {
                CosultaAPI consulta = new CosultaAPI(busqueda);
                consulta.consultarTitulos();
                System.out.println(consulta.getJson());

                DTOTitulos dtoTitulos = new DTOTitulos(consulta.getJson(), gson);
                titulos.add(dtoTitulos.getMiTitulo());
            } catch (NumberFormatException e) {
                System.out.println("Ocurrió un error: ");
                System.out.println(e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Error en la URI, verifique la dirección.");
            } catch (ErrorEnConversionDeDuracionException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Lista de titulos consultados: " + titulos);
        GuardarEnArchivo guardarArchivo = new GuardarEnArchivo(gson, titulos);
        System.out.println("Finalizó la ejecución del programa!");
    }
}
