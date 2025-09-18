package platzi.play;

import platzi.play.contenido.Pelicula;
import platzi.play.plataforma.Plataforma;
import platzi.play.plataforma.Usuario;
import platzi.play.util.ScannerUtils;


public class Main {
    //Constantes inmutables con static final
    public static final String NOMBRE_APP = "Platzi Play 🍿";
    public static final String VERSION = "1.0.0";
    public static void main(String[] args) {
        Plataforma plataforma = new Plataforma(NOMBRE_APP);
        System.out.println(NOMBRE_APP + " v" + VERSION);
        //Parte para realizar la captura de datos por consola
        String nombre = ScannerUtils.capturarTexto("¿Cual es el nombre de la pelicula?");
        String genero = ScannerUtils.capturarTexto("¿Cual es el genero de la pelicula?");
        int duracion = ScannerUtils.capturarNumero("¿Cual es la duracion de la pelicula?");
        double calificacion = ScannerUtils.capturarDecimal("¿Cual es la calificacion de la pelicula?");
        System.out.println("-------------------------");

        Pelicula pelicula = new Pelicula(nombre, duracion, genero, calificacion);
        Pelicula pelicula1 = new Pelicula("Titanic", 195, "Romance", 9.5);

        plataforma.agregar(pelicula);
        plataforma.agregar(pelicula1);
        System.out.println("Número de elementos en la plataforma: " + plataforma.getContenido().size());
        System.out.println("Es popular: " + (pelicula.esPopular() ? "Sí" : "No"));
        System.out.println("-------------------------");
//        pelicula.reproducir();
        plataforma.eliminar(pelicula1);

        plataforma.mostrarTitulo();
        System.out.println("-------------------------");

        Usuario usuario = new Usuario("Juan", "juan.pepito@gmail.com");
        usuario.ver(pelicula);
    }
}
