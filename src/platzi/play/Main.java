package platzi.play;

import platzi.play.contenido.Genero;
import platzi.play.contenido.Pelicula;
import platzi.play.excepcion.PeliculaExistenteException;
import platzi.play.plataforma.Plataforma;
import platzi.play.plataforma.Usuario;
import platzi.play.util.ScannerUtils;

import java.util.List;


public class Main {
    //Constantes inmutables con static final
    public static final String NOMBRE_APP = "Platzi Play 🍿";
    public static final String VERSION = "1.0.1";
    public static final int AGREGAR_CONTENIDO = 1;
    public static final int MOSTRAR_CONTENIDO = 2;
    public static final int BUSCAR_POR_TITULO = 3;
    public static final int BUSCAR_POR_GENERO = 4;
    public static final int VER_POPULARES = 5;
    public static final int ELIMINAR_CONTENIDO = 6;
    public static final int SALIR = 7;

    public static void main(String[] args) {
        Plataforma plataforma = new Plataforma(NOMBRE_APP);
        System.out.println(NOMBRE_APP + " v" + VERSION);
        //1. Agregar contenido
        //2. Mostrar contenido
        //3. Buscar por titulo
        //4. Eliminar contenido
        //5. Salir
        cargarPeliculas(plataforma);
        System.out.println("Mas de " + plataforma.getDuracionTotal() + " minutos de entretenimiento! \n");

        while (true) {
            int opcionElegida = ScannerUtils.capturarNumero("""
                    Menu de opciones:
                    1. Agregar contenido
                    2. Mostrar contenido
                    3. Buscar por titulo
                    4. Buscar por genero
                    5. Ver populares
                    6. Eliminar contenido
                    7. Salir
                    Ingresa una opcion (1-6):
                    """);
            System.out.println("-------------------------");
            System.out.println("Opcion elegida: " + opcionElegida);
            System.out.println("-------------------------");
            switch (opcionElegida) {
                case AGREGAR_CONTENIDO -> {
                    String nombre = ScannerUtils.capturarTexto("¿Cual es el nombre de la pelicula?");
                    Genero genero = ScannerUtils.capturarGenero("¿Cual es el genero de la pelicula? \n");
                    int duracion = ScannerUtils.capturarNumero("¿Cual es la duracion de la pelicula?");
                    double calificacion = ScannerUtils.capturarDecimal("¿Cual es la calificacion de la pelicula?");
                    Pelicula pelicula = new Pelicula(nombre, duracion, genero, calificacion);
                    try {
                        plataforma.agregar(pelicula);
                        System.out.println("Pelicula agregada exitosamente.");
                        System.out.println("----------------------------------");
                    } catch (PeliculaExistenteException e) {
                        System.out.println(e.getMessage());
                        System.out.println("No se pudo agregar la pelicula.");
                        System.out.println("----------------------------------");
                    }
                }
                case MOSTRAR_CONTENIDO -> {
                    List<String> titulos = plataforma.getTitulos();
                    titulos.forEach(System.out::println);
                    System.out.println("----------------------------------");
                }
                case BUSCAR_POR_TITULO -> {
                    String tituloBuscado  = ScannerUtils.capturarTexto("Ingresa el título de la película que deseas buscar");
                    Pelicula peliculaEncontrada = plataforma.busacarPorTitulo(tituloBuscado);
                    if (peliculaEncontrada != null) {
                        System.out.println("Pelicula encontrada:");
                        System.out.println(peliculaEncontrada.obtenerFichaTecnica());
                    } else {
                        System.out.println("La película con título '" + tituloBuscado + "' no se encontró en la plataforma.");
                    }
                    System.out.println("----------------------------------");
                }
                case BUSCAR_POR_GENERO -> {
                    Genero generoBuscado = ScannerUtils.capturarGenero("Ingresa el género de la película que deseas buscar \n");
                    List<Pelicula> contenidoPorGenero = plataforma.buscarPorGenero(generoBuscado);
                    System.out.println(contenidoPorGenero.size() + " películas encontradas en el género '" + generoBuscado + "':");
                    contenidoPorGenero.forEach(contenido -> System.out.println(contenido.obtenerFichaTecnica() + "\n"));
                    System.out.println("----------------------------------");
                }
                case VER_POPULARES -> {
                    int cantidad = ScannerUtils.capturarNumero("¿Cuántas películas populares deseas ver?");
                    List<Pelicula> peliculasPopulares = plataforma.getPopulares(cantidad);
                    System.out.println("Películas populares: \n");
                    peliculasPopulares.forEach(contenido -> System.out.println(contenido.obtenerFichaTecnica() + "\n"));
                    System.out.println("----------------------------------");
                }
                case ELIMINAR_CONTENIDO -> {
                    String tituloAEliminar = ScannerUtils.capturarTexto("Ingresa el título de la película que deseas eliminar");
                    Pelicula peliculaAEliminar = null;
                    for (Pelicula pelicula : plataforma.getContenido()) {
                        if (pelicula.getTitulo().equalsIgnoreCase(tituloAEliminar)) {
                            peliculaAEliminar = pelicula;
                            break;
                        }
                    }
                    if (peliculaAEliminar != null) {
                        plataforma.eliminar(peliculaAEliminar);
                        System.out.println("Pelicula eliminada exitosamente.");
                    } else {
                        System.out.println("La película con título '" + tituloAEliminar + "' no se encontró en la plataforma.");
                    }
                    System.out.println("----------------------------------");
                }
                case SALIR -> {
                    System.out.println("Gracias por usar " + NOMBRE_APP + ". ¡Hasta luego!");
                    System.exit(0);
                }

            }
        }
    }
    private static void cargarPeliculas(Plataforma plataforma) {
        plataforma.agregar(new Pelicula("Shrek", 120, Genero.COMEDIA, 8.5));
        plataforma.agregar(new Pelicula("El Origen", 150, Genero.CIENCIA_FICCION, 9.0));
        plataforma.agregar(new Pelicula("Batman", 130, Genero.ACCION, 8.7));
        plataforma.agregar(new Pelicula("Titanic", 180, Genero.ROMANCE, 7.5));
        plataforma.agregar(new Pelicula("Avengers: Endgame", 180, Genero.ACCION, 8.8));
        plataforma.agregar(new Pelicula("El Rey León", 90, Genero.ANIMACION, 8.3));
    }
}
