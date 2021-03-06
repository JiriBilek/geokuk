/**
 *
 */
package cz.geokuk.core.coord;

import java.awt.Point;

import cz.geokuk.core.coordinates.Mou;
import cz.geokuk.core.coordinates.Mouable;
import cz.geokuk.framework.Event0;

/**
 * @author Martin Veverka
 *
 */
public class ZmenaSouradnicMysiEvent extends Event0<PoziceModel> {
	public final Mou moucur;
	public final Point pointcur;
	public final Mouable upravenaMys;

	public ZmenaSouradnicMysiEvent(final Point pointcur, final Mou moucur, final Mouable upravenaMys) {
		super();
		this.pointcur = pointcur;
		this.moucur = moucur;
		this.upravenaMys = upravenaMys;
	}

}
