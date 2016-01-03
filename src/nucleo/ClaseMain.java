package nucleo;

import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.jnativehook.GlobalScreen;

public class ClaseMain {

	public static void main(String[] args) {

		// Clear previous logging configurations.
		LogManager.getLogManager().reset();

		// Get the logger for "org.jnativehook" and set the level to off.
		Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
		logger.setLevel(Level.OFF);

		// Se inicia la rutina de inicialización de los componentes de la
		// librería JNativeHook
		try {
			GlobalScreen.registerNativeHook();
		} catch (Exception e) {
			e.printStackTrace();
		}

		/*
		 * Es necesario decirle a la instancia de GlobalScreen que se agregará
		 * un Listener, ya que la clase Main implementa la interfaz del Listener
		 * sólo se crea una instancia de dicha clase para colocarla como
		 * argumento:
		 */
		GlobalScreen.addNativeKeyListener(new ScannerNativeHook());
	}

}
