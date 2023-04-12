package co.edu.upb.Bounsic;

public class Cancion {
    private String nombre;
    private String letra;
    private String genero;
    private String artista;

    public Cancion(String nombre, String letra, String genero, String artista) {
        this.nombre = nombre;
        this.letra = letra;
        this.genero = genero;
        this.artista = artista;
    }

    public String getNombre() {
        return nombre;
    }

    public String getLetra() {
        return letra;
    }

    public String getGenero() {
        return genero;
    }

    public String getArtista() {
        return artista;
    }
}