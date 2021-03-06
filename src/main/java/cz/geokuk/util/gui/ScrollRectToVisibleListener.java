package cz.geokuk.util.gui;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JComponent;

/**
 * @author Martin Veverka
 *
 */
final class ScrollRectToVisibleListener extends MouseMotionAdapter {
	@Override
	public void mouseDragged(final MouseEvent e) {
		final Rectangle r = new Rectangle(e.getX(), e.getY(), 1, 1);
		((JComponent) e.getSource()).scrollRectToVisible(r);
	}
}