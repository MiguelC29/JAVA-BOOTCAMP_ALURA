package com.aluracursos.screenmatch.principal;

import com.aluracursos.screenmatch.modelos.Pelicula;
import com.aluracursos.screenmatch.modelos.Serie;
import com.aluracursos.screenmatch.modelos.Titulo;

import java.util.*;

public class PrincipalConListas {
    public static void main(String[] args) {
        Pelicula miPelicula = new Pelicula("Encanto", 2021);
        miPelicula.evalua(9);
        Pelicula otraPelicula = new Pelicula("Matrix", 1998);
        otraPelicula.evalua(6);
        var peliculaDeBruno = new Pelicula("El señor de los Anillos", 180);
        peliculaDeBruno.evalua(10);

        Serie casaDragon = new Serie("La casa del dragón", 2022);

        List<Titulo> lista = new LinkedList<>(); // shift + f6 sobre la variable para cambiar el nombre de la variable donde se este llamando
        //Set -> no hay repeticion de datos //hashset         // LinkedList
        //Map //hashmap
        lista.add(miPelicula);
        lista.add(otraPelicula);
        lista.add(peliculaDeBruno);
        lista.add(casaDragon);

        for (Titulo item : lista ) {
            System.out.println("Nombre: " + item.getNombre());

            if (item instanceof Pelicula pelicula && pelicula.getClasificacion() > 2) { // instanceof -> la instancia de item es del tipo Pelicula
                // a partir de java 14 aparece la posibilidad de desde de Pelicula agregar una variable de referencia- en resumen hace el casting por lo que podemos quitar la linea 31
                //Pelicula pelicula = (Pelicula) item;
                // a partir de java 17 aparece la posibilidad de agregar algunas operaciones o condiciones como se ve en el if de la linea 29 despues del &&
                System.out.println("Clasificación: " + pelicula.getClasificacion());
            }
        }

        List<String> listaDeArtistas = new ArrayList<>();
        listaDeArtistas.add("Penélope Cruz"); // con ctrl + D -> copiamos automaticamente lo de la línea actual a la siguiente línea
        listaDeArtistas.add("Antonio Banderas");
        listaDeArtistas.add("Ricardo Darín");

        System.out.println("Lista de artistas NO ordenada: " + listaDeArtistas);

        Collections.sort(listaDeArtistas); //Organiza nuestra lista
        System.out.println("Lista de artistas ordenada: " + listaDeArtistas);

        Collections.sort(lista);
        System.out.println("Lista de titulos ordenados: " + lista);

        // Esto lo hacemos para no modificar el orden natural de ordenamiento por nombre del titulo
        lista.sort(Comparator.comparing(Titulo::getFechaDeLanzamiento)); // :: lambda basicamente compara el campo fecha
        // (Titulo t) -> t.getFechaDeLanzamiento().
    }
}
