package capturaPantalla;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Capturador {

	private Robot robot;
	private static final String FILE_PATH = "Capturas/";
	private static final String NOMBRE_BASE = "cap.png";
	private static final Dimension TAMAÑO_PANTALLA = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

	private String contador;

	public Capturador() {
		try {
			this.robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		this.contador = "";
		configurarDireccion();
	}

	/**
	 * Crea la/s direcciones de almacenamiento para las capturas.
	 */
	private void configurarDireccion() {
		File f = new File(FILE_PATH);
		if (!f.isDirectory())
			f.mkdirs();
	}

	public void capturarPantalla() {

		// creo un rectangulo con la dimension de la pantalla con contenido
		// basura, luego creo la captura y la guardo en BufferedImage imagen.
		BufferedImage imagen = this.robot.createScreenCapture(new Rectangle(TAMAÑO_PANTALLA));
		try {
			ImageIO.write(imagen, "png", new File(FILE_PATH + NOMBRE_BASE + this.contador));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
