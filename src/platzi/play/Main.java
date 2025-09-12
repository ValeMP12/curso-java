package platzi.play;

import platzi.play.contenido.Pelicula;
import platzi.play.plataforma.Usuario;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Platzi Play 🍿");
        Pelicula pelicula = new Pelicula();
        pelicula.titulo = "The Matrix";
        pelicula.fechaEstreno = LocalDate.of(1999, 3, 31);
        pelicula.genero = "Ciencia Ficcion";
        pelicula.duracion = 136;
        pelicula.calificacion(8.7);

        long duracionLong = pelicula.duracion;
        int calificacionInt = (int) pelicula.calificacion;
        long numeroDePremios = Long.parseLong("25");

        System.out.println("La duracion en long es: " + duracionLong);
        System.out.println("La calificacion en int es: " + calificacionInt);
        System.out.println("El numero de premios es: " + numeroDePremios);

        Usuario usuario = new Usuario();
        usuario.nombre = "Juan";
        usuario.fechaRegistro = LocalDateTime.of(1999, 5, 20, 10, 30);
        usuario.ver(pelicula);
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Cual es tu nombre?");
//        String nombre = scanner.nextLine();
//        System.out.println("Hola " + nombre + ", esto es Platzi Play!");
//        System.out.println(nombre + " ¿cuantos años tienes?");
//        int edad = scanner.nextInt();
//        System.out.println(nombre + " puedes ver contenido +" + edad);

    }
}
