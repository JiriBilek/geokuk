package cz.geokuk.plugins.mapy.kachle;

import java.util.EnumSet;

public class JKachlovnikDoRohu extends JKachlovnik {

	private static final long serialVersionUID = -7897332661428146095L;

	public JKachlovnikDoRohu() {
		super("Levý dolní roh - kachlovník", Priority.KACHLE);
	}

	@Override
	public void setKachloTypes(final KaSet aKachloSet) {
//			super.setKachloTypes(new KaSet(EnumSet.of(EKaType.OPHOTO_M)));
		
		// Vyloučí všechno, co nejsou vybrané ortofotomapy. To, co zbyde, předá jako podklad.
		// Když nic nezbyde, nic nedělá.
		// Takto se zajistí, aby kachlovník do rohu se přepínal podle posledně nastavené ortofotomapy.
		// Povolují se jenom ortofotomapy MapyCZ (aktuální) a google
		EnumSet<EKaType> kts = aKachloSet.getKts();
		kts.retainAll(EnumSet.of(EKaType.OPHOTO_M, EKaType.OPHOTO_GOOGLE));
		if (! kts.isEmpty())
			super.setKachloTypes(new KaSet(kts));
	}

}
