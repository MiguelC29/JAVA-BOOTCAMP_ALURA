package com.aluracursos.screenmatch.principal;

import com.aluracursos.screenmatch.excepcion.ErrorEnConversionDeDuracionException;
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

public class PrincipalConBusqueda {
    public static void main(String[] args) throws IOException, InterruptedException {
        // CODIGO ALURA: https://github.com/alura-es-cursos/2047-screenmatch4/releases/tag/Aula4-Fin

        Scanner teclado = new Scanner(System.in);
        List<Titulo> titulos = new ArrayList<>();
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting() //para que se vea en un formato mas bonito y no todo en una linea
                .create(); //para mantener nuestras variable del record en minuscula, con esto el hace la conversion de la primera letra a mayuscula

        while (true) {
            System.out.println("Escribe el nombre de la pelicula:");
            var busqueda = teclado.nextLine();

            if (busqueda.equalsIgnoreCase("salir")) {
                break;
            }

            String apiKey = "9c051dd9";
            //String url = String.format("https://www.omdbapi.com/?apikey=%s&t=%s", apiKey, busqueda);
            String url = String.format("https://www.omdbapi.com/?apikey=%s&t=%s", apiKey, busqueda.replace(" ", "+")); // si el nombre de la pelicula tiene espacios se reemplaza por un +
            // URL ENCODER

            try {
                // PONEMOS TODO EN EL BLOQUE TRY YA QUE QUEREMOS EXPRESAR UNA DEPENDENCIA, SI DA ERROR NUESTRA URI O REQUEST, NO TIENE SENTIDO SEGUIR, INTENTANDO CONVERTIR MI JSON A UNA CLASE.
                // Arquitectura cliente-servidor
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder() // patron de diseño builder
                        .uri(URI.create(url)) // Se establece la URI (Uniform Resource Identifier) de la solicitud, que en este caso es la URL de la API de OMDb.
                        .build();
                HttpResponse<String> response = client
                        .send(request, HttpResponse.BodyHandlers.ofString()); //hacer import para el send de las exceptions

                String json = response.body();

                System.out.println(json); // Imprimimos nuestros datos o cuerpo de la solicitud

                //Vamos a convertir JSON a objetos/clases java para eso usaremos GSON - vamos a mvn repository y buscamos GSON y seleccionamos la de google y descargamos el JAR
                // Para usarlo vamos a file -> project structure -> modules -> dependencies -> y le damos al + para agregar el JAR que descargamos y listo

                //Gson gson = new Gson(); //creamos un objeto de tipo gson
                // Creamos un objeto de tipo Titulo, y le asignamos el convertidor de json a class
                //Titulo miTitulo = gson.fromJson(json, Titulo.class); //primer parametro el json a convertir, y segundo parametro la clase que queremos convertir

                /*
                System.out.println(miTitulo.getNombre());
                esto en un principio retornara null, ya que el json nos devuelve un title y un year, y nosotros en la clase tenemos nombre y fechaDeLanzamiento
                entonces tenemos que modificar nuestra clase Titulo
                 */

                //System.out.println(miTitulo);

                // DTO - DATA TRANSFER OBJECT
                TituloOmdb miTituloOmdb = gson.fromJson(json, TituloOmdb.class);
                System.out.println(miTituloOmdb); // ctrl + shift + flecha arriba/abajo para bajar o subir lineas

                // Identar todo el codigo ctrl + a y luego ctrl + alt + i

                // MANEJO DE EXCEPCIONES

                Titulo miTitulo = new Titulo(miTituloOmdb);
                System.out.println("Título ya convertido: " + miTitulo);
                // encoding

                /*
                FileWriter escritura = new FileWriter("peliculas.txt"); // del paquete java.io que es inputOutput / con ctrl + p dentro de los parentesis de FileWriter podemos ver las diferentes formas de crear este obj
                escritura.write(miTitulo.toString());
                escritura.close(); // es importante cerrar la escritura para evitar inconvenientes futuros, lentitud, etc
                 */
                titulos.add(miTitulo);

            } catch (NumberFormatException e) {
                System.out.println("Ocurrió un error: ");
                System.out.println(e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Error en la URI, verifique la dirección.");
            } catch (ErrorEnConversionDeDuracionException e) {
                System.out.println(e.getMessage());
                // En la vida laboral no es comun solo mostrar un mensaje, sino tambien tener un archivo log, donde yo almacene todas las exceptions que se produzcan
            }

            /*catch (Exception e) { // no es muy recomendable tener una exception general, perdemos el control de nuestra app y los posibles errores
                // ERROR: Algo que nosotros no podemos controlar, por ejemplo, que se acabe la memoria de la computadora.
                // EXCEPTION: Algo que nosotros podemos prevenir, comprender y modificar algo para poder tratar el problema.
                System.out.println("Ocurrió un error inesperado.");
            }*/
        }
        System.out.println(titulos);

        FileWriter escritura = new FileWriter("titulos.json");
        escritura.write(gson.toJson(titulos));
        escritura.close();

        System.out.println("Finalizó la ejecución del programa!");
    }
}
