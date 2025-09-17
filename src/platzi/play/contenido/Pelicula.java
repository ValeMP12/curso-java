package platzi.play.contenido;

import java.time.LocalDate;

public class Pelicula {
    private String titulo;
    private String descripcion;
    private int duracion;
    private String genero;
    private LocalDate fechaEstreno;
    private double calificacion;
    private boolean disponible;

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

    //Getter

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getDuracion() {
        return duracion;
    }

    public String getGenero() {
        return genero;
    }

    public LocalDate getFechaEstreno() {
        return fechaEstreno;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public void setFechaEstreno(LocalDate fechaEstreno) {
        this.fechaEstreno = fechaEstreno;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}
