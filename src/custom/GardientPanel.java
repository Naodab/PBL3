package custom;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class GardientPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private final Color UP = new Color(214, 47, 128);
	private final Color DOWN = new Color(209, 174, 191);
	
	public GardientPanel() {
		setOpaque(false);
		colorUp = UP;
		colorDown = DOWN;
	}
	
	public Color getColorUp() {
		return colorUp;
	}

	public void setColorUp(Color colorUp) {
		this.colorUp = colorUp;
	}

	public Color getColorDown() {
		return colorDown;
	}

	public void setColorDown(Color colorDown) {
		this.colorDown = colorDown;
	}

	private Color colorUp;
	private Color colorDown;
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setPaint(new GradientPaint(getWidth() / 2, 0, colorUp, getWidth() / 2, getHeight(), colorDown));
		g2d.fillRect(0, 0, getWidth(), getHeight());
		super.paintComponent(g);
	}
}
