package controlador;

/**
 * @deprecated No se utiliza.
 * @author nahuel
 *
 */
public class HiloMain extends Thread {

	boolean terminarHilo;

	public HiloMain() {
		this.terminarHilo = false;
		this.start();
	}

	@Override
	public void run() {
		while (!terminarHilo) {

		}
	}

}
