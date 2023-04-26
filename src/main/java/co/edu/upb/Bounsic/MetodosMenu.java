package co.edu.upb.Bounsic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class MetodosMenu {

    public static boolean buscarCacion(BibliotecaCanciones biblioteca, Scanner sc) {
        List<Cancion> cancionesEncontradas = new ArrayList<>();
        boolean opcionValida = false;
        while (!opcionValida) {
            System.out.println("¿Cómo quieres buscar la canción? (por nombre o por género)");
            String opcionBusq = sc.nextLine();

            if (opcionBusq.equalsIgnoreCase("nombre")) {
                System.out.println("Introduce el nombre de la canción:");
                String nombre = sc.nextLine();
                cancionesEncontradas = Funciones.buscarPorNombre(biblioteca, nombre); //Busca canciones
                Funciones.seleccionarCancionBusqueda(cancionesEncontradas);		      //Selecciona apartir del resultado de busqueda
                return true;
            } else if (opcionBusq.equalsIgnoreCase("genero")) {
                System.out.println("¿Qué género de canciones quieres ver? (rock, pop, hip hop, electronica)");
                String genero = sc.nextLine();
                cancionesEncontradas = Funciones.buscarPorGenero(biblioteca, genero);
                Funciones.seleccionarCancionBusqueda(cancionesEncontradas);
                return true;
            } else {
                System.out.println("Opción no válida. Intentelo de nuevo.");
            }
        }
        return false;
    }

    public static boolean verBibliotecaCanciones(BibliotecaCanciones biblioteca, Scanner sc) {
        biblioteca.getCanciones();
        System.out.println("\nCanciones disponibles:\n");

        int index = 0;
        for (Cancion cancion : biblioteca.getCanciones()) {
            System.out.println((index++) + ". " + cancion.getNombre());
        }
        Funciones.seleccionarCancionBusqueda(biblioteca.getCanciones());
        return true;
    }

    public static boolean cancionAleatoria(BibliotecaCanciones biblioteca, Scanner sc) {
        Cancion cancionAleatoria = Funciones.buscarCancionAleatoria(biblioteca, sc);

        System.out.println("Canción aleatoria seleccionada:");
        System.out.println("Nombre: " + cancionAleatoria.getNombre());
        System.out.println("Género: " + cancionAleatoria.getGenero());
        System.out.println("Artista: " + cancionAleatoria.getArtista());
        Funciones.reproducirCancion(cancionAleatoria);
        return true;
    }
