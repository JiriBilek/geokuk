/**
 *
 */
package cz.geokuk.plugins.kesoid.mvc;

import cz.geokuk.framework.Event0;
import cz.geokuk.plugins.kesoid.KesBag;

/**
 * @author Martin Veverka
 *
 */
public class KeskyNactenyEvent extends Event0<KesoidModel> {
	private final KesBag vsechny;

	/**
	 * @return the informaceOZdrojich
	 */
	public KeskyNactenyEvent(final KesBag vsechny) {
		super();
		this.vsechny = vsechny;
	}

	public KesBag getVsechny() {
		return vsechny;
	}
}
