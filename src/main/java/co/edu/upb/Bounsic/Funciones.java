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
        System.out.println(
                "Reproduciendo canción. Presione la letra 'p' para pausar, 'r' para reanudar y 'e' para terminar.");
        AtomicBoolean pausado = new AtomicBoolean(false);
        CountDownLatch latch = new CountDownLatch(1);
        AtomicBoolean seguirReproduciendo = new AtomicBoolean(true);

        Thread hiloReproduccion = new Thread(() -> {
            for (String linea : cancionSeparada) {
                synchronized (pausado) {
                    while (pausado.get()) {
                        try {
                            pausado.wait();
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
                if (!seguirReproduciendo.get()) {
                    break;
                }
                System.out.println(linea);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            seguirReproduciendo.set(false);
            latch.countDown();
        });

        Thread hiloEntrada = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (seguirReproduciendo.get()) {
                String input = scanner.nextLine();
                if (input.equals("p")) {
                    System.out.println("Canción pausada. Presione la letra 'r' para reanudar.");
                    pausado.set(true);
                } else if (input.equals("r")) {
                    System.out.println("Reanudando canción...");
                    pausado.set(false);
                    synchronized (pausado) {
                        pausado.notifyAll();
                    }
                } else if (input.equals("e")) {
                    System.out.println("Terminando la canción...");
                    seguirReproduciendo.set(false);
                    break;
                }
            }
            // scanner.close();
            // latch.countDown();
        });

        hiloReproduccion.start();
        hiloEntrada.start();
        try {
            latch.await();
            System.out.println("La canción ha terminado.");
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        return true;
    }

    public static List<Cancion> buscarPorNombre(BibliotecaCanciones biblioteca, String nombre)	{
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

    public static Cancion buscarCancionAleatoria(BibliotecaCanciones biblioteca) {
        Random random = new Random();
        int randomIndex = random.nextInt(biblioteca.getsize());
        Cancion cancionAleatoria = biblioteca.getCancion(randomIndex);
        return cancionAleatoria;
    }

}