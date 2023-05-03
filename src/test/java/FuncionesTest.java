import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import co.edu.upb.Bounsic.Funciones;

import java.util.concurrent.atomic.AtomicBoolean;

public class FuncionesTest {
  Funciones funciones;
  AtomicBoolean atomicBoolean;

  @BeforeEach

  void setUp() {
    funciones = new Funciones();
  }

  @Test

  void pausarCancionTest() {
    atomicBoolean = new AtomicBoolean();
    atomicBoolean.set(false);
    assertEquals(true, funciones.pausarCancion(atomicBoolean));

  }

  @Test

  void reanudarCancionTest() {
    atomicBoolean = new AtomicBoolean();
    atomicBoolean.set(false);
    assertEquals(true, funciones.reanudarCancion(atomicBoolean));
  }

  @Test

  void terminarCancionTest() {
    atomicBoolean = new AtomicBoolean();
    atomicBoolean.set(false);
    assertEquals(true, funciones.terminarCancion(atomicBoolean));

  }
}
