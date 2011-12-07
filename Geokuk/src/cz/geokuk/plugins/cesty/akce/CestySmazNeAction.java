package cz.geokuk.plugins.cesty.akce;


import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import cz.geokuk.framework.Dlg;


public class CestySmazNeAction extends CestyAction0 {

  private static final long serialVersionUID = -7547868179813232769L;

  public CestySmazNeAction() {
    super("Žádné neignoruji");
    putValue(SHORT_DESCRIPTION, "Odstraní příznak ignorace u všech keší.");
    putValue(MNEMONIC_KEY, KeyEvent.VK_N);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (Dlg.anone("Opravdu odstranit z výletu " + cestyModel.getPocetIgnorovanychKesoidu() + " keší, které chcete ignorovat?")) {
      cestyModel.clearIgnoreList();
    }
  }

  @Override
  protected void vyletChanged() {
    super.vyletChanged();
    setEnabled(cestyModel.getPocetIgnorovanychKesoidu() > 0);
  }

}