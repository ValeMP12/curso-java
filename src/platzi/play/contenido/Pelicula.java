package platzi.play.contenido;

public class Pelicula {
    public String titulo;
    public String descripcion;
    public int duracion;
    public String genero;
    public int anioEstreno;
    public double calificacion;
    public boolean disponible;

    public void reproducir() {
        System.out.println(titulo);
    }
    public String obtenerFichaTecnica() {
        return titulo + " (" + anioEstreno + ") \n" +
                "Género: " + genero + "\n" +
                "Duracion: " + duracion +" min \n" +
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
