/**
 *
 */
package cz.geokuk.core.coord;

import cz.geokuk.framework.Event0;

/**
 * @author Martin Veverka
 *
 */
public class PoziceChangedEvent extends Event0<PoziceModel> {

	public final Poziceq poziceq;

	/**
	 * @param aPozice
	 * @param aMeloBySeCentrovat
	 */
	PoziceChangedEvent(final Poziceq aPoziceq) {
		assert aPoziceq != null;
		poziceq = aPoziceq;
	}
}
