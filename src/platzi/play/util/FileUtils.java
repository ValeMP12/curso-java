package platzi.play.util;

import platzi.play.contenido.Contenido;
import platzi.play.contenido.Genero;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    private static final String NOMBRE_ARCHIVO = "contenido.txt";
    private static final String SEPARADOR = "|";
    public static void escribirContenido(Contenido contenido) {
        String linea = String.join(SEPARADOR, contenido.getTitulo(),
                String.valueOf(contenido.getDuracion()),
                contenido.getGenero().name(),
                String.valueOf(contenido.getCalificacion()),
                contenido.getFechaEstreno().toString()
        );
        try {
            Files.writeString(Paths.get(NOMBRE_ARCHIVO), linea + System.lineSeparator(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo de contenido: " + e.getMessage());
        }
    }

    public static List<Contenido> leerContenido() {
        List<Contenido> contenidoDesdeArchivo = new ArrayList<>();
        try {
            List<String> lineas = Files.readAllLines(Paths.get(NOMBRE_ARCHIVO));
            lineas.forEach(linea -> {
                String[] datos = linea.split(SEPARADOR);
                if (datos.length == 6) {
                    String titulo = datos[0];
                    int duracion = Integer.parseInt(datos[1]);
                    Genero genero = Genero.valueOf(datos[2].toUpperCase());
                    double calificacion = datos[3].isBlank() ? 0 : Double.parseDouble(datos[3]);
                    LocalDate fechaEstreno = LocalDate.parse(datos[4]);
                    String narrador = datos[5].isBlank() ? "N/A" : datos[5];

                    Contenido contenido = new Contenido(titulo, duracion, genero, calificacion);
                    contenido.setFechaEstreno(fechaEstreno);
                    contenidoDesdeArchivo.add(contenido);
                }
                System.out.println(linea);
            });
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de contenido: " + e.getMessage());
        }
        return contenidoDesdeArchivo;
    }
}
