package com.aluracursos.screenmatch.principal;

import com.aluracursos.screenmatch.calculos.CalculadoraDeTiempo;
import com.aluracursos.screenmatch.calculos.FiltroRecomendacion;
import com.aluracursos.screenmatch.modelos.Episodio;
import com.aluracursos.screenmatch.modelos.Pelicula;
import com.aluracursos.screenmatch.modelos.Serie;

import java.util.ArrayList;

public class Principal {
    public static void main(String[] args) {
        Pelicula miPelicula = new Pelicula("Encanto", 2021);
        //miPelicula.setNombre("Encanto");
        //miPelicula.setFechaDeLanzamiento(2021);
        miPelicula.setDuracionEnMinutos(120);
        miPelicula.setIncluidoEnElPlan(true);

        miPelicula.muestraFichaTecnica();

        miPelicula.evalua(10);
        miPelicula.evalua(10);
        miPelicula.evalua(7.8);

        System.out.println(miPelicula.getTotalDeLasEvaluaciones());
        System.out.println("La media de evaluaciones de la película es: " + miPelicula.calculaMedia());

        Serie casaDragon = new Serie("La casa del dragón", 2022);
        //casaDragon.setNombre("La casa del dragón");
        //casaDragon.setFechaDeLanzamiento(2022);
        casaDragon.setTemporadas(1);
        casaDragon.setMinutosPorEpisodio(50);
        casaDragon.setEpisodiosPorTemporada(10);
        casaDragon.muestraFichaTecnica();
        System.out.println(casaDragon.getDuracionEnMinutos());

        Pelicula otraPelicula = new Pelicula("Matrix", 1998);
        otraPelicula.setDuracionEnMinutos(180);

        CalculadoraDeTiempo calculadora = new CalculadoraDeTiempo();
        calculadora.incluye(miPelicula);
        calculadora.incluye(casaDragon);
        calculadora.incluye(otraPelicula);
        System.out.println(String.format("El tiempo necesario para ver tus títulos favoritos estas vacaciones es de %d minutos", calculadora.getTiempoTotal()));

        Episodio episodio = new Episodio();
        episodio.setNumero(1);
        episodio.setNombre("La casa Targaryen");
        episodio.setSerie(casaDragon);
        episodio.setTotalVisualizaciones(50);

        FiltroRecomendacion filtroRecomendacion = new FiltroRecomendacion();
        filtroRecomendacion.filtra(miPelicula);
        filtroRecomendacion.filtra(episodio);

        var peliculaDeBruno = new Pelicula("El señor de los Anillos", 180); //peliculaDeBruno -> variable de referencia | var -> hace una inferencia del tipo de dato
        peliculaDeBruno.setFechaDeLanzamiento(2001);

        ArrayList<Pelicula> listaDePeliculas = new ArrayList<>(); // ctrl + shift + enter -> pone el punto y coma al final
        listaDePeliculas.add(peliculaDeBruno);
        listaDePeliculas.add(miPelicula);
        listaDePeliculas.add(otraPelicula);

        System.out.println("Tamaño de la lista: " + listaDePeliculas.size());
        System.out.println("La primera película es: " + listaDePeliculas.get(0).getNombre()); // ctrl + Q sobre algun metodo, para ver la mini documentacion (poniendo el cursor sobre tambien sirve)

        System.out.println(listaDePeliculas);
        System.out.println("toString de la película: " + listaDePeliculas.get(0));

        // CLEAN CODE - A Handbook of Agile Software Craftsmanship. libro de buenas practicas, tambien esta SOLID
    }
}