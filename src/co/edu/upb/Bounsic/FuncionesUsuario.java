package co.edu.upb.Bounsic;

import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class FuncionesUsuario {
	static Scanner sc = new Scanner(System.in);
	
	public static boolean verificarCadena(String cadena) { //Funcion para verificar usuarios y contraseñas
		if ((cadena.length() >= 5) && (!cadena.contains("@"))
				&& (!cadena.contains(" ")) && (!cadena.contains("!")) && (!cadena.contains("?")) 
				&& (!cadena.contains("¿")) && (!cadena.contains("¡")) ){
			return true;
		}
		return false;
	}
	
	public static boolean verificarCorreo(String correo) {
		if ((correo.endsWith("@hotmail.com")) || (correo.endsWith("@gmail.com"))) {
			return true;
		}
		return false;
	}
	
	public static boolean verificarNumeroTarjeta(String numeroTarjeta) {		
		if (numeroTarjeta.matches("\\d{16}")) {
			return true;
		}
		return false;
	}
	
	public static boolean verificarFechaDeVencimiento(Date fechaDeVencimiento) {
		Calendar hoy = Calendar.getInstance();
		Calendar fechaVencimiento = Calendar.getInstance();
		fechaVencimiento.setTime(fechaDeVencimiento);
		if (!fechaVencimiento.before(hoy)) {
			return true;
		}
		return false;
	}
	
	public static boolean verificarCodigoSeguridad(int codigoSeguridad) {
		if ((String.valueOf(codigoSeguridad).length() == 3) || (String.valueOf(codigoSeguridad).length() == 4)) {
			return true;
		}
		return false;
	}
}
