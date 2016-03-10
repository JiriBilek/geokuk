/**
 *
 */
package cz.geokuk.plugins.mrizky;


import java.awt.Color;

import cz.geokuk.core.coord.JSingleSlide0;
import cz.geokuk.core.coordinates.Mou;
import cz.geokuk.core.coordinates.Utm;



/**
 * @author veverka
 *
 */
public class JMrizkaUtm extends JMrizka0 {

  protected static final double MINUTA = 1.0 / 60;

  private static final long serialVersionUID = 4558815639199835559L;

  /* (non-Javadoc)
   * @see mrizka.JMrizka0#convertToMou(double, double)
   */
  @Override
  public Mou convertToMou(final double aX, final double aY) {
    final Mou mou =  getUtmStredu().toUtmInTheSameZone(aX, aY).toMou();
    return mou;
  }

  private Utm getUtmStredu() {
    return super.getSoord().getMoustred().toWgs().toUtm();
  }

  /* (non-Javadoc)
   * @see mrizka.JMrizka0#convertToX(coordinates.Mou)
   */
  @Override
  public double convertToX(final Mou aMou) {
    final Utm utmStredu = getUtmStredu();
    return aMou.toWgs().toUtm().toSampePlaceInAnotherZone(utmStredu.polednikovaZona, utmStredu.rovnobezkovaZona).ux;
  }

  /* (non-Javadoc)
   * @see mrizka.JMrizka0#convertToY(coordinates.Mou)
   */
  @Override
  public double convertToY(final Mou aMou) {
    final Utm utmStredu = getUtmStredu();
    return aMou.toWgs().toUtm().toSampePlaceInAnotherZone(utmStredu.polednikovaZona, utmStredu.rovnobezkovaZona).uy;
  }


  @Override
  public String getTextY(final double y) {
    return (long)y + "";
  }

  @Override
  public String getTextX(final double x) {
    return (long)x + "";
  }

  /* (non-Javadoc)
   * @see mrizka.JMrizka0#initPainting(mrizka.JMrizka0.Vykreslovac)
   */
  @Override
  public void initPainting(final Vykreslovac v) {
    v.setColor(Color.BLUE);
    for (int rad =1; rad < 100000000; rad = rad * 10) {
      v.rastr(rad, 1);
      v.rastr(rad * 2, 5);
      v.rastr(rad * 5, 2);
    }
  }

  /* (non-Javadoc)
   * @see cz.geokuk.core.coord.JSingleSlide0#createRenderableSlide()
   */
  @Override
  public JSingleSlide0 createRenderableSlide() {
    return new JMrizkaUtm();
  }

  @Override
  public boolean smimVykreslovat() {
    final int polednikovaZonaVychod = getSoord().getMouV().toWgs().toUtm().polednikovaZona;
    final int polednikovaZonaZapad = getSoord().getMouZ().toWgs().toUtm().polednikovaZona;
    if (polednikovaZonaVychod == 60 && polednikovaZonaZapad == 1) {
      return true; // přechod kolem datumové čáry
    }
    if (Math.abs(polednikovaZonaZapad - polednikovaZonaVychod) <= 1) {
      return true;
    }
    // JE to moc daleko od sebe, tak nevykreslujeme
    return false;
  }



}
