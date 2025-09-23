package platzi.play.util;

import platzi.play.contenido.Contenido;
import platzi.play.contenido.Documental;
import platzi.play.contenido.Genero;
import platzi.play.contenido.Pelicula;

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
        String lineaFinal;
        if (contenido instanceof Documental documental) {
            lineaFinal = "DOCUMENTAL" + SEPARADOR + linea + SEPARADOR + documental.getNarrador();
        }else {
            lineaFinal = "PELICULA" + SEPARADOR + linea;
        }
        try {
            Files.writeString(Paths.get(NOMBRE_ARCHIVO), lineaFinal + System.lineSeparator(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo de contenido: " + e.getMessage());
        }
    }

    public static List<Contenido> leerContenido() {
        List<Contenido> contenidoDesdeArchivo = new ArrayList<>();
        try {
            List<String> lineas = Files.readAllLines(Paths.get(NOMBRE_ARCHIVO));
            for (String linea : lineas) {
                linea = linea.trim();
                if (linea.isBlank()) continue;
                String[] datos = linea.split("\\|");
                if (datos.length == 0) continue;
                String tipoDeContenido = datos[0].trim();
                if (("PELICULA".equalsIgnoreCase(tipoDeContenido) && datos.length == 6) ||
                        ("DOCUMENTAL".equalsIgnoreCase(tipoDeContenido) && datos.length == 7)) {
                    try {
                        String titulo = datos[1].trim();
                        int duracion = Integer.parseInt(datos[2].trim());
                        Genero genero = Genero.valueOf(datos[3].trim().toUpperCase());
                        double calificacion = datos[4].trim().isBlank() ? 0 : Double.parseDouble(datos[4].trim());
                        LocalDate fechaEstreno = LocalDate.parse(datos[5].trim());

                        Contenido contenido;
                        if ("PELICULA".equalsIgnoreCase(tipoDeContenido)) {
                            contenido = new Pelicula(titulo, duracion, genero, calificacion);
                        } else {
                            String narrador = datos[6].trim();
                            contenido = new Documental(titulo, duracion, genero, calificacion, narrador);
                        }
                        contenido.setFechaEstreno(fechaEstreno);
                        contenidoDesdeArchivo.add(contenido);
                    } catch (Exception e) {
                        System.out.println("❌ Error procesando línea: " + linea);
                        System.out.println("   → " + e.getMessage());
                    }
                } else {
                    System.out.println("⚠️ Línea inválida o con cantidad de campos incorrecta: " + linea);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de contenido: " + e.getMessage());
        }
        return contenidoDesdeArchivo;
    }

}
