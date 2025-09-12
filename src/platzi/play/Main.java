package platzi.play;

import platzi.play.contenido.Pelicula;
import platzi.play.plataforma.Usuario;
import platzi.play.util.ScannerUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Platzi Play 🍿");
        //Parte para realizar la captura de datos por consola
        String nombre = ScannerUtils.capturarTexto("¿Cual es el nombre de la pelicula?");
        String genero = ScannerUtils.capturarTexto("¿Cual es el genero de la pelicula?");
        int duracion = ScannerUtils.capturarNumero("¿Cual es la duracion de la pelicula?");
        double calificacion = ScannerUtils.capturarDecimal("¿Cual es la calificacion de la pelicula?");
        LocalDate fechaEstreno = LocalDate.parse(ScannerUtils.capturarFecha("¿Cual es la fecha de estreno de la pelicula?"));
        System.out.println("-------------------------");



        Pelicula pelicula = new Pelicula();
        pelicula.titulo = nombre;
        pelicula.fechaEstreno = fechaEstreno;
        pelicula.genero = genero;
        pelicula.duracion = duracion;
        pelicula.calificacion(calificacion);

        System.out.println(pelicula.obtenerFichaTecnica());

        Usuario usuario = new Usuario();
        usuario.nombre = "Juan";
        usuario.fechaRegistro = LocalDateTime.of(1999, 5, 20, 10, 30);
        usuario.ver(pelicula);
    }
}
