package platzi.play;

import platzi.play.contenido.Pelicula;
import platzi.play.plataforma.Plataforma;
import platzi.play.plataforma.Usuario;
import platzi.play.util.ScannerUtils;


public class Main {
    //Constantes inmutables con static final
    public static final String NOMBRE_APP = "Platzi Play 🍿";
    public static final String VERSION = "1.0.1";
    public static final int AGREGAR_CONTENIDO = 1;
    public static final int MOSTRAR_CONTENIDO = 2;
    public static final int BUSCAR_POR_TITULO = 3;
    public static final int ELIMINAR_CONTENIDO = 4;
    public static final int SALIR = 5;
    public static void main(String[] args) {
        Plataforma plataforma = new Plataforma(NOMBRE_APP);
        System.out.println(NOMBRE_APP + " v" + VERSION);
        //1. Agregar contenido
        //2. Mostrar contenido
        //3. Buscar por titulo
        //4. Eliminar contenido
        //5. Salir

        while (true) {
            int opcionElegida = ScannerUtils.capturarNumero("""
                    Ingresa una de las siguientes opciones:
                    1. Agregar contenido
                    2. Mostrar contenido
                    3. Buscar por titulo
                    4. Eliminar contenido
                    5. Salir
                    """);
            System.out.println("-------------------------");
            System.out.println("Opcion elegida: " + opcionElegida);
            System.out.println("-------------------------");
            if (opcionElegida == AGREGAR_CONTENIDO) {
                String nombre = ScannerUtils.capturarTexto("¿Cual es el nombre de la pelicula?");
                String genero = ScannerUtils.capturarTexto("¿Cual es el genero de la pelicula?");
                int duracion = ScannerUtils.capturarNumero("¿Cual es la duracion de la pelicula?");
                double calificacion = ScannerUtils.capturarDecimal("¿Cual es la calificacion de la pelicula?");
                Pelicula pelicula = new Pelicula(nombre, duracion, genero, calificacion);
                plataforma.agregar(pelicula);
                System.out.println("Pelicula agregada exitosamente.");
            } else if (opcionElegida == MOSTRAR_CONTENIDO) {
                System.out.println("Contenido disponible:");
                plataforma.mostrarTitulo();
            } else if (opcionElegida == BUSCAR_POR_TITULO) {
                String tituloBuscado = ScannerUtils.capturarTexto("Ingresa el título de la película que deseas buscar");
                boolean encontrado = false;
                for (Pelicula pelicula : plataforma.getContenido()) {
                    if (pelicula.getTitulo().equalsIgnoreCase(tituloBuscado)) {
                        System.out.println("Pelicula encontrada:");
                        System.out.println(pelicula.obtenerFichaTecnica());
                        encontrado = true;
                        break;
                    }
                }
                if (!encontrado) {
                    System.out.println("La película con título '" + tituloBuscado + "' no se encontró en la plataforma.");
                }
            } else if (opcionElegida == ELIMINAR_CONTENIDO) {
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
            } else
            if (opcionElegida == SALIR) {
                System.out.println("Gracias por usar " + NOMBRE_APP + ". ¡Hasta luego!");
                System.exit(0);
            }
        }

        //Parte para realizar la captura de datos por consola
//        String nombre = ScannerUtils.capturarTexto("¿Cual es el nombre de la pelicula?");
//        String genero = ScannerUtils.capturarTexto("¿Cual es el genero de la pelicula?");
//        int duracion = ScannerUtils.capturarNumero("¿Cual es la duracion de la pelicula?");
//        double calificacion = ScannerUtils.capturarDecimal("¿Cual es la calificacion de la pelicula?");
//        System.out.println("-------------------------");
//
//        Pelicula pelicula = new Pelicula(nombre, duracion, genero, calificacion);
//        Pelicula pelicula1 = new Pelicula("Titanic", 195, "Romance", 9.5);
//
//        plataforma.agregar(pelicula);
//        plataforma.agregar(pelicula1);
//        System.out.println("Número de elementos en la plataforma: " + plataforma.getContenido().size());
//        System.out.println("Es popular: " + (pelicula.esPopular() ? "Sí" : "No"));
//        System.out.println("-------------------------");
//        pelicula.reproducir();
//        plataforma.eliminar(pelicula1);
//
//        plataforma.mostrarTitulo();
//        System.out.println("-------------------------");
//
//        Usuario usuario = new Usuario("Juan", "juan.pepito@gmail.com");
//        usuario.ver(pelicula);
    }
}
