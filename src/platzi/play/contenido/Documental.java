package platzi.play.contenido;

public class Documental extends Contenido implements Promocionable {
    private String narrador;
    public Documental(String titulo, int duracion, Genero genero) {
        super(titulo, duracion, genero);
    }
    public Documental(String titulo, int duracion, Genero genero, double calificacion, String narrador) {
        super(titulo, duracion, genero, calificacion);
        this.narrador = narrador;
    }

    @Override
    public void reproducir() {
        System.out.println("Reproduciendo documental: " + getTitulo() + ", narrado por: " + narrador);
    }

    @Override
    public String promocionar() {
        return "😁 Descubre el fascinante documental: " + getTitulo() + ", narrado por " + narrador + ". ¡No te lo pierdas!";
    }
    public String getNarrador() {
        return narrador;
    }
}
