package platzi.play;

import platzi.play.contenido.*;
import platzi.play.excepcion.PeliculaExistenteException;
import platzi.play.plataforma.Plataforma;
import platzi.play.util.FileUtils;
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
    public static final int REPRODUCIR_CONTENIDO = 6;
    public static final int BUSCAR_POR_TIPO = 7;
    public static final int ELIMINAR_CONTENIDO = 8;
    public static final int SALIR = 9;

    public static void main(String[] args) {
        Plataforma plataforma = new Plataforma(NOMBRE_APP);
        System.out.println(NOMBRE_APP + " v" + VERSION);
        cargarPeliculas(plataforma);
        System.out.println("Mas de " + plataforma.getDuracionTotal() + " minutos de entretenimiento! \n");
        plataforma.getPromocionables().forEach(promocionable -> System.out.println(promocionable.promocionar()));
        System.out.println("\n");

        while (true) {
            int opcionElegida = ScannerUtils.capturarNumero("""
                    Menu de opciones:
                    1. Agregar contenido
                    2. Mostrar contenido
                    3. Buscar por titulo
                    4. Buscar por genero
                    5. Ver populares
                    6. Reproducir contenido
                    7. Buscar por tipo
                    8. Eliminar contenido
                    9. Salir
                    Ingresa una opcion (1-8):
                    """);
            System.out.println("-------------------------");
            System.out.println("Opcion elegida: " + opcionElegida);
            System.out.println("-------------------------");
            switch (opcionElegida) {
                case AGREGAR_CONTENIDO -> {
                    int tipoDeContenido = ScannerUtils.capturarNumero("""
                            ¿Qué tipo de contenido deseas agregar?
                            1. Película
                            2. Documental
                            Ingresa una opción (1-2):
                            """);
                    String nombre = ScannerUtils.capturarTexto("¿Cual es el nombre del contenido?");
                    Genero genero = ScannerUtils.capturarGenero("¿Cual es el genero del contenido? \n");
                    int duracion = ScannerUtils.capturarNumero("¿Cual es la duracion del contenido?");
                    double calificacion = ScannerUtils.capturarDecimal("¿Cual es la calificacion del contenido?");
                    try {
                        if(tipoDeContenido == 1) {
                            Contenido contenido = new Pelicula(nombre, duracion, genero, calificacion);
                            plataforma.agregar(contenido);
                            System.out.println("Contenido agregada exitosamente.");
                            System.out.println("----------------------------------");
                        }else {
                            String narrador = ScannerUtils.capturarTexto("¿Quien es el narrador del documental?");
                            Contenido contenido = new Documental(nombre, duracion, genero, calificacion, narrador);
                            plataforma.agregar(contenido);
                            System.out.println("Contenido agregada exitosamente.");
                            System.out.println("----------------------------------");
                        }

                    } catch (PeliculaExistenteException e) {
                        System.out.println(e.getMessage());
                        System.out.println("No se pudo agregar el contenido.");
                        System.out.println("----------------------------------");
                    }
                }
                case MOSTRAR_CONTENIDO -> {
                    plataforma.getContenido().clear();
                    plataforma.getContenido().addAll(FileUtils.leerContenido());
                    List<ResumenContenido> contenidosResumidos = plataforma.getResumenes();
                    contenidosResumidos.forEach(resumenContenido -> System.out.println(resumenContenido.toString()));
                    System.out.println("----------------------------------");
                }
                case BUSCAR_POR_TITULO -> {
                    String tituloBuscado  = ScannerUtils.capturarTexto("Ingresa el título de la película que deseas buscar");
                    Contenido peliculaEncontrada = plataforma.busacarPorTitulo(tituloBuscado);
                    if (peliculaEncontrada != null) {
                        System.out.println("Contenido encontrada:");
                        System.out.println(peliculaEncontrada.obtenerFichaTecnica());
                    } else {
                        System.out.println("La película con título '" + tituloBuscado + "' no se encontró en la plataforma.");
                    }
                    System.out.println("----------------------------------");
                }
                case BUSCAR_POR_GENERO -> {
                    Genero generoBuscado = ScannerUtils.capturarGenero("Ingresa el género de la película que deseas buscar \n");
                    List<Contenido> contenidoPorGenero = plataforma.buscarPorGenero(generoBuscado);
                    System.out.println(contenidoPorGenero.size() + " películas encontradas en el género '" + generoBuscado + "':");
                    contenidoPorGenero.forEach(contenido -> System.out.println(contenido.obtenerFichaTecnica() + "\n"));
                    System.out.println("----------------------------------");
                }
                case VER_POPULARES -> {
                    int cantidad = ScannerUtils.capturarNumero("¿Cuántos contenidos populares deseas ver?");
                    List<Contenido> peliculasPopulares = plataforma.getPopulares(cantidad);
                    System.out.println("Películas populares: \n");
                    peliculasPopulares.forEach(contenido -> System.out.println(contenido.obtenerFichaTecnica() + "\n"));
                    System.out.println("----------------------------------");
                }
                case REPRODUCIR_CONTENIDO -> {
                    String tituloAReproducir = ScannerUtils.capturarTexto("Ingresa el título del contenido que deseas reproducir");
                    Contenido peliculaAReproducir = plataforma.busacarPorTitulo(tituloAReproducir);
                    if (peliculaAReproducir != null) {
                        plataforma.reproducir(peliculaAReproducir);
                    } else {
                        System.out.println("El contenido con título '" + tituloAReproducir + "' no se encontró en la plataforma.");
                    }
                    System.out.println("----------------------------------");
                }
                case BUSCAR_POR_TIPO -> {
                    int tipoDeContenido = ScannerUtils.capturarNumero("""
                            ¿Qué tipo de contenido deseas buscar?
                            1. Película
                            2. Documental
                            Ingresa una opción (1-2):
                            """);
                    if (tipoDeContenido == 1) {
                        List<Pelicula> peliculas = plataforma.getPeliculas();
                        System.out.println("Películas encontradas: \n");
                        peliculas.forEach(pelicula -> System.out.println(pelicula.obtenerFichaTecnica() + "\n"));
                    } else if (tipoDeContenido == 2) {
                        List<Documental> documentales = plataforma.getDocumentales();
                        System.out.println("Documentales encontrados: \n");
                        documentales.forEach(documental -> System.out.println(documental.obtenerFichaTecnica() + "\n"));
                    } else {
                        System.out.println("Opción inválida. Por favor, ingresa 1 o 2.");
                    }
                    System.out.println("----------------------------------");
                }
                case ELIMINAR_CONTENIDO -> {
                    String tituloAEliminar = ScannerUtils.capturarTexto("Ingresa el título de la película que deseas eliminar");
                    Contenido peliculaAEliminar = null;
                    for (Contenido pelicula : plataforma.getContenido()) {
                        if (pelicula.getTitulo().equalsIgnoreCase(tituloAEliminar)) {
                            peliculaAEliminar = pelicula;
                            break;
                        }
                    }
                    if (peliculaAEliminar != null) {
                        plataforma.eliminar(peliculaAEliminar);
                        System.out.println("Contenido eliminada exitosamente.");
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
        plataforma.getContenido().addAll(FileUtils.leerContenido());
    }
}
