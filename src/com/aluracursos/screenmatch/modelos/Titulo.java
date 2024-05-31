package com.aluracursos.screenmatch.modelos;

import com.aluracursos.screenmatch.excepcion.ErrorEnConversionDeDuracionException;
import com.google.gson.annotations.SerializedName;

public class Titulo implements Comparable<Titulo> {

    // Para usar el GSON tenemos que asociar lo que nos retorna json con nuestros campos de la clase, para eso usaremos anotaciones
    // Vamos a usar la anotacion Serializedname

    //@SerializedName("Title") // con eso asociamos nuestro atributo nombre, con la clave Title que retorna el json
    private String nombre;

    //@SerializedName("Year") // sin embargo esta no es la mejor forma, ya que nos limita a usar una sola api y si cambiamos tendriamos que cambiar todos estos nombres
    private int fechaDeLanzamiento;
    private int duracionEnMinutos;
    private boolean incluidoEnElPlan;
    private double sumaDeLasEvaluaciones;
    private int totalDeLasEvaluaciones;

    public Titulo(TituloOmdb tituloOmdb) {
        this.nombre = tituloOmdb.title(); // los record no tienen el nombre get.. sino que es el nombre del atributo y parentesis
        this.fechaDeLanzamiento = Integer.valueOf(tituloOmdb.year());

        // CREANDO NUESTRA PROPIA EXCEPTION
        if (tituloOmdb.runtime().contains("N/A")) {
            throw new ErrorEnConversionDeDuracionException(
                    "No pude convertir la duración, porque contiene un N/A"
            ); // si la pelicula no cuenta con una duración y nos da esto N/A, retornamos una exception
        }

        this.duracionEnMinutos = Integer.valueOf(
                tituloOmdb.runtime().substring(0, 3).replace(" ", "")); // tenemos que hacer un substring porque la api nos devuelve ej: 60 min, solo necesitamos el numero
    }

    public Titulo(String nombre, int fechaDeLanzamiento) {
        this.nombre = nombre;
        this.fechaDeLanzamiento = fechaDeLanzamiento;
    }

    public String getNombre() {
        return nombre;
    }

    public int getFechaDeLanzamiento() {
        return fechaDeLanzamiento;
    }

    public int getDuracionEnMinutos() {
        return duracionEnMinutos;
    }

    public boolean isIncluidoEnElPlan() {
        return incluidoEnElPlan;
    }

    public int getTotalDeLasEvaluaciones() {
        return totalDeLasEvaluaciones;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFechaDeLanzamiento(int fechaDeLanzamiento) {
        this.fechaDeLanzamiento = fechaDeLanzamiento;
    }

    public void setDuracionEnMinutos(int duracionEnMinutos) {
        this.duracionEnMinutos = duracionEnMinutos;
    }

    public void setIncluidoEnElPlan(boolean incluidoEnElPlan) {
        this.incluidoEnElPlan = incluidoEnElPlan;
    }

    public void muestraFichaTecnica() {
        System.out.println("El nombre del título es: " + nombre);
        System.out.println("Su fecha de lazamiento es: " + fechaDeLanzamiento);
        System.out.println("Duración en minutos: " + getDuracionEnMinutos());
        System.out.println("Incluido en el plan: " + (incluidoEnElPlan ? "Sí" : "No"));
    }

    public void evalua(double nota) {
        sumaDeLasEvaluaciones += nota;
        totalDeLasEvaluaciones++;
    }

    public double calculaMedia() {
        return sumaDeLasEvaluaciones / totalDeLasEvaluaciones;
    }

    @Override
    public int compareTo(Titulo otroTitulo) {
        return this.getNombre().compareTo(otroTitulo.getNombre());
    }

    @Override
    public String toString() {
        return "(nombre=" + nombre +
                ", fechaDeLanzamiento=" + fechaDeLanzamiento +
                ", duración=" + duracionEnMinutos + ")";
    }
}