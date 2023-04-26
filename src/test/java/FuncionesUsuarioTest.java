import co.edu.upb.Bounsic.FuncionesUsuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


class FuncionesUsuarioTest {
    private  FuncionesUsuario funcionesUsuario;
    @BeforeEach

    //PRUEBA DE INTENTO DE INGRESO DE USUARIO DE MANERA CORRECTA
    void setUp(){
        funcionesUsuario = new FuncionesUsuario();
    }
    @Test
    void verificarCadenaTest(){
        //Prueba con nombre mayor de 5 digitos en LowerCase
        Assertions.assertEquals(true,  funcionesUsuario.verificarCadena("patolin"));

    }

    @Test
    void verificarCorreoTest(){
        //Prueba de correo con @gmail.com
        Assertions.assertEquals(true , funcionesUsuario.verificarCorreo("pato123@gmail.com"));
    }

    @Test

    void verificarNumeroTarjetaTest(){
        //Prueba numero tarjeta 16 dijitos

        Assertions.assertEquals(true,funcionesUsuario.verificarNumeroTarjeta("8745690312579862"));

    }

    @Test

    void verificarFechaDeVencimientoTest() throws ParseException {
        //Fecha de Vencimiento mayor al dia actual
        String fechaStr = "25/04/2025"; // Fecha en formato dd/MM/yyyy
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        // Parsear la fecha en formato String a un objeto java.util.Date
        Date fecha = sdf.parse(fechaStr);
        //Date fecha = new Date(2024/5/11);
        Assertions.assertEquals(true,funcionesUsuario.verificarFechaDeVencimiento(fecha));

    }
    @Test

    void verificarCodigoSeguridadTest(){
        //Numero de seguridad = 4 digitos

        Assertions.assertEquals(true,funcionesUsuario.verificarCodigoSeguridad(1127));
    }

}
