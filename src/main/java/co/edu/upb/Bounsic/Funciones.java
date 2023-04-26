package co.edu.upb.Bounsic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

public class Funciones {

    static Scanner sc = new Scanner(System.in);

    public static boolean reproducirCancion(Cancion cancion) {
        String[] cancionSeparada = cancion.getLetra().split("\r\n|\r|\n");
        System.out.println("Reproduciendo canción. Presione la letra 'p' para pausar y 'r' para reanudar.");
        AtomicBoolean pausado = new AtomicBoolean(false); // para llevar registro de si la canción está pausada o no
        CountDownLatch latch = new CountDownLatch(1); // para esperar a que termine la reproducción de la canción

        // Hilo para reproducir la canción
        Thread hiloReproduccion = new Thread(() -> {
            for (String linea : cancionSeparada) {
                synchronized (pausado) {
                    while (pausado.get()) {
                        try {
                            pausado.wait(); // Esperar hasta que la canción se reanude
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
                System.out.println(linea); // mostrar la línea actual
                try {
                    Thread.sleep(1000); // esperar 1 segundo antes de mostrar la siguiente línea
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            System.out.println("LA CANCION HA ACABADO.");
            latch.countDown(); // reducir el contador para indicar que la canción ha terminado
        });

        // Hilo para monitorear la entrada del usuario
        Thread hiloEntrada = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) { // Detectar si se ingresó una línea por teclado
                String input = scanner.nextLine();
                if (input.equals("p")) { // Si se ingresó la letra "p", pausar la canción
                    System.out.println("Canción pausada. Presione la letra 'r' para reanudar.");
                    pausado.set(true); // Marcar la canción como pausada
                } else if (input.equals("r")) { // Si se ingresó la letra "r", reanudar la canción
                    System.out.println("Reanudando canción...");
                    pausado.set(false); // Marcar la canción como no pausada
                    synchronized (pausado) {
                        pausado.notifyAll(); // Despertar el hilo de reproducción
                    }
                } else if (input.equals("q")) { // Si se ingresó la letra "q", salir del programa
                    System.out.println("Saliendo del programa...");
                    latch.countDown(); // reducir el contador para indicar que la canción ha terminado
                    scanner.close(); // Cerrar el objeto Scanner
                    return; // salir del método
                }
            }
            scanner.close(); // Cerrar el objeto Scanner al final del hilo
        });

        hiloReproduccion.start(); // Iniciar el hilo de reproducción
        hiloEntrada.start(); // Iniciar el hilo de entrada del usuario
        try {
            latch.await(); // Esperar a que la canción
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        return true;
    }

    public static List<Cancion> buscarPorNombre(BibliotecaCanciones biblioteca, String nombre) {
        System.out.println("Canciones con el nombre \"" + nombre + "\":");
        System.out.println("------------------------");

        // Obtener la lista de canciones que contienen el nombre buscado
        List<Cancion> canciones = biblioteca.getCanciones();
        List<Cancion> cancionesEncontradas = new ArrayList<>();
        int index = 0;
        for (Cancion cancion : canciones) {
            if (cancion.getNombre().toLowerCase().contains(nombre.toLowerCase())) {
                cancionesEncontradas.add(cancion);
                System.out.println((index++) + ": " + cancion.getNombre() + " - " + cancion.getArtista());
            }
        }

        if (cancionesEncontradas.isEmpty()) {
            System.out.println("No se encontraron canciones con el nombre \"" + nombre + "\"");
            return null;
        }

        return cancionesEncontradas;
    }

    public static Cancion seleccionarCancionBusqueda(List<Cancion> cancionesEncontradas) {
        System.out.println();
        System.out.print("Ingrese el número de la canción que desea reproducir: ");
        int seleccion = sc.nextInt();

        if (seleccion < 0 || seleccion >= cancionesEncontradas.size()) {
            System.out.println("Número de canción inválido");
            return null;
        }

        System.out.println("Reproduciendo \"" + cancionesEncontradas.get(seleccion).getNombre() + "\" por "
                + cancionesEncontradas.get(seleccion).getArtista());
        reproducirCancion(cancionesEncontradas.get(seleccion));

        return cancionesEncontradas.get(seleccion);
    }

    public static List<Cancion> buscarPorGenero(BibliotecaCanciones biblioteca, String genero) {
        System.out.println("Canciones de " + genero + ":");
        System.out.println("------------------------");

        // Obtener la lista de canciones del género seleccionado
        List<Cancion> canciones = biblioteca.getCanciones();
        List<Cancion> cancionesEncontradas = new ArrayList<>();
        int index = 0;
        for (Cancion cancion : canciones) {
            if (cancion.getGenero().equalsIgnoreCase(genero)) {
                cancionesEncontradas.add(cancion);
                System.out.println((index++) + ": " + cancion.getNombre() + " - " + cancion.getArtista());
            }
        }

        if (cancionesEncontradas.isEmpty()) {
            System.out.println("No se encontraron canciones de " + genero);
            return null;
        }
        return cancionesEncontradas;
    }

    public static Cancion buscarCancionAleatoria(BibliotecaCanciones biblioteca, Scanner sc) {
        Random random = new Random();
        int randomIndex = random.nextInt(biblioteca.size());
        Cancion cancionAleatoria = biblioteca.get(randomIndex);
        return cancionAleatoria;
    }

    public static List<Cancion> generarListaAleatoria(BibliotecaCanciones biblioteca, Cancion cancionActual, int numCanciones) {
        List<Cancion> listaAleatoria = new ArrayList<>();
        List<Cancion> todasLasCanciones = biblioteca.getCanciones();
        Random random = new Random();

        // Agregar la canción actual a la lista aleatoria
        listaAleatoria.add(cancionActual);

        // Generar una lista aleatoria de canciones diferentes a la actual
        while (listaAleatoria.size() < numCanciones) {
            int index = random.nextInt(todasLasCanciones.size());
            Cancion cancionAleatoria = todasLasCanciones.get(index);
            if (!listaAleatoria.contains(cancionAleatoria) && !cancionAleatoria.equals(cancionActual)) {
                listaAleatoria.add(cancionAleatoria);
            }
        }

        return listaAleatoria;
    }


}
