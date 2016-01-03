package nucleo;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import capturaPantalla.Capturador;

/**
 * Es el Listener que re escrive los metodos de NativeKeyListener
 * 
 * @author nahuel
 *
 */
public class ScannerNativeHook implements NativeKeyListener {

	private static final int LETRA_ACTIVACION_SALIDA = NativeKeyEvent.VC_ESCAPE;
	private static final int LETRA_ACTIVACION_CAPTURA = NativeKeyEvent.VC_ALT_R;

	private static final int[] COMBO_SALIR = { NativeKeyEvent.VC_S };

	private boolean letraSalidaActivada;
	private boolean letraCapturaActivada;
	private Capturador capturador;
	private int indiceVerificacion;

	public ScannerNativeHook() {
		this.indiceVerificacion = 0;
		this.letraSalidaActivada = false;
		this.letraCapturaActivada = false;
		this.capturador = new Capturador();
	}

	@Override
	public void nativeKeyPressed(NativeKeyEvent e) {
		if (e.getKeyCode() == LETRA_ACTIVACION_SALIDA)
			this.letraSalidaActivada = true;
		if (e.getKeyCode() == LETRA_ACTIVACION_CAPTURA)
			this.letraCapturaActivada = true;

		if (this.letraSalidaActivada)
			secuenciaDetener(e);
		if ((this.letraCapturaActivada) && (e.getKeyCode() == NativeKeyEvent.VC_C))
			this.capturador.capturarPantalla();
	}

	/**
	 * Determina si es la secuencia para salir, y si lo es detiene la
	 * aplicación.
	 * 
	 * @param e
	 *            El evento de la tecla que se presiono recientemente.
	 */
	private void secuenciaDetener(NativeKeyEvent e) {
		if (e.getKeyCode() == COMBO_SALIR[this.indiceVerificacion]) {
			this.indiceVerificacion++;
			if (this.indiceVerificacion == COMBO_SALIR.length) {
				try {
					GlobalScreen.unregisterNativeHook();
				} catch (NativeHookException e1) {
					e1.printStackTrace();
				}
			}
		} else
			this.indiceVerificacion = 0;
	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent e) {
		if (e.getKeyCode() == LETRA_ACTIVACION_SALIDA)
			this.letraSalidaActivada = false;
		if (e.getKeyCode() == LETRA_ACTIVACION_CAPTURA)
			this.letraCapturaActivada = false;
	}

	@Override
	public void nativeKeyTyped(NativeKeyEvent e) {
	}

}
