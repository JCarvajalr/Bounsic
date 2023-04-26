package co.edu.upb.Bounsic;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;
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

    public static boolean cancionAleatoria(BibliotecaCanciones biblioteca) {
        Cancion cancionAleatoria = Funciones.buscarCancionAleatoria(biblioteca);

        System.out.println("Canción aleatoria seleccionada:");
        System.out.println("Nombre: " + cancionAleatoria.getNombre());
        System.out.println("Género: " + cancionAleatoria.getGenero());
        System.out.println("Artista: " + cancionAleatoria.getArtista());
        Funciones.reproducirCancion(cancionAleatoria);
        return true;
    }
    public static boolean listaReproduccionHits (BibliotecaCanciones biblioteca,Scanner sc) {
        System.out.println("\nPlaylist Recomendada.\n");
        System.out.println("Reproduciendo Playlist...\n");
        List<Cancion> canciones = biblioteca.getCanciones();
        int[] indices = {1, 5, 7, 8, 11, 14, 16};
        for (int i = 0; i < indices.length; i++) {
            Cancion cancion = canciones.get(indices[i]);
            System.out.println(cancion.getNombre() + "\n");
            Funciones.reproducirCancion(cancion);
            System.out.println("\n");
        }
        return true;

    }
    public static boolean crearListaCancionesAleatorias(BibliotecaCanciones biblioteca, Scanner sc) {
        List<Cancion> cancionesEncontradas = new ArrayList<>();
        Random random = new Random();
        List<Cancion> listaCancionesAleatorias = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            int index = random.nextInt(biblioteca.getCanciones().size());
            Cancion cancionAleatoria = biblioteca.getCanciones().get(index);
            listaCancionesAleatorias.add(cancionAleatoria);
        }

        System.out.println("Lista de 7 canciones aleatorias:");
        int numCancion = 1;
        for (Cancion cancion : listaCancionesAleatorias) {
            System.out.println(numCancion + ". " + cancion.getNombre());
            numCancion++;
        }

        int opcion;
        int cancionActual = 0;
        do {
            System.out.println("Selecciona una opción:");
            System.out.println("1. Reproducir canción actual");
            System.out.println("2. Saltar canción actual");
            System.out.println("3. Volver al menú principal");

            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opción no válida. Intentelo");
                continue;
            }

            switch (opcion) {
                case 1:
                    System.out.println("Reproduciendo: " + listaCancionesAleatorias.get(cancionActual).getNombre());
                    Funciones.reproducirCancion(listaCancionesAleatorias.get(cancionActual));
                    cancionActual++;
                    break;
                case 2:
                    System.out.println("Saltando canción: " + listaCancionesAleatorias.get(cancionActual).getNombre());
                    cancionActual++;
                    break;
                case 3:
                    System.out.println("Volviendo al menú principal.");
                    return false;
                default:
                    System.out.println("Opción no válida. Intentelo de nuevo.");
                    break;
            }
        } while (cancionActual < listaCancionesAleatorias.size());

        System.out.println("Lista de canciones aleatorias finalizada.");
        return true;
    }

}
