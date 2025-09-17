package platzi.play.contenido;

import java.time.LocalDate;

public class Pelicula {
    public String titulo;
    public String descripcion;
    public int duracion;
    public String genero;
    public LocalDate fechaEstreno;
    public double calificacion;
    public boolean disponible;

    public Pelicula(String titulo, int duracion, String genero) {
        this.titulo = titulo;
        this.duracion = duracion;
        this.genero = genero;
        this.fechaEstreno = fechaEstreno;
        this.disponible = true;
    }

    public Pelicula(String titulo, int duracion, String genero, double calificacion) {
        this(titulo, duracion, genero);
        this.calificacion(calificacion);
    }

    public void reproducir() {
        System.out.println(titulo);
    }
    public String obtenerFichaTecnica() {
        return titulo + " (" + fechaEstreno.getYear() + ") \n" +
                "Género: " + genero + "\n" +
                "Duracion: " + duracion +" min \n" +
                "Fecha de estreno: " + fechaEstreno + "\n" +
                "Calificacion: " + calificacion + "/10";
    }
    public void calificacion(double calificacion) {
        if (calificacion >= 0 && calificacion <= 10) {
            this.calificacion = calificacion;
        } else {
            System.out.println("Calificacion invalida. Debe estar entre 0 y 10.");
        }
    }
    public boolean esPopular() {
        return calificacion >= 7.0;
    }
}
