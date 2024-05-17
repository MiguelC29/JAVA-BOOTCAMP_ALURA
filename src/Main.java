public class Main {
    public static void main(String[] args) {
        System.out.println("Bienvenido(a) a Screen Match");
        System.out.println("Película: Matrix");

        int fechaDeLanzamiento = 1999;
        boolean incluidoEnElPlan = true;
        double notaDeLaPelicula = 8.2;

        double media = (8.2 + 6.0 + 9.0) / 3;
        System.out.println(media);

        /*
        String sinopsis = """
                Matrix es una paradoja
                La mejor película del fin del milenio
                Fué lanzada en:
                """ + fechaDeLanzamiento;
         */

        String sinopsis = """
                Matrix es una paradoja
                La mejor película del fin del milenio
                Fué lanzada en: %d
                Tiene una Nota de: %.1f
                """.formatted(fechaDeLanzamiento, notaDeLaPelicula);
        System.out.println(sinopsis);

        int calificacion = (int) (media / 2);
        System.out.println(calificacion);
    }
}