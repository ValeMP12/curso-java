package platzi.play.plataforma;
import platzi.play.contenido.Pelicula;
import java.util.ArrayList;
import java.util.List;

public class Plataforma {
    private String nombre;
    private List<Pelicula> contenido;

    public Plataforma(String nombre){
        this.nombre = nombre;
        this.contenido = new ArrayList<>();
    }
    public void agregar(Pelicula elemento) {
        contenido.add(elemento);
    }
    public void mostrarTitulo() {
//        for (Pelicula pelicula : contenido) {
//            System.out.println(pelicula.getTitulo());
//        }
        contenido.forEach(pelicula -> System.out.println(pelicula.getTitulo()));
    }
    public Pelicula busacarPorTitulo(String titulo) {
       return contenido.stream()
                .filter(contenido -> contenido.getTitulo().equalsIgnoreCase(titulo))
                .findFirst()
                .orElse(null);
    }
    public List<Pelicula> buscarPorGenero(String genero) {
        return contenido.stream()
                .filter(contenido -> contenido.getGenero().equalsIgnoreCase(genero))
                .toList();
    }
    public void eliminar(Pelicula elemento) {
        this.contenido.remove(elemento);
    }

    public String getNombre() {
        return nombre;
    }

    public List<Pelicula> getContenido() {
        return contenido;
    }
}
