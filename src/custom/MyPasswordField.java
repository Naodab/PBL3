package custom;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

public class MyPasswordField extends JPasswordField{
	private static final long serialVersionUID = 1L;
	
	public MyPasswordField() {
		setBorder(new EmptyBorder(0, 0, getWidth(), getHeight()));
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				over = true;
			}

			@Override
			public void mouseExited(MouseEvent e) {
				over = false;
			}
		});
	}

	public Color getOverColor() {
		return overColor;
	}

	public void setOverColor(Color overColor) {
		this.overColor = overColor;
	}

	public Color getLineColor() {
		return lineColor;
	}

	public void setLineColor(Color lineColor) {
		this.lineColor = lineColor;
	}

	private boolean over = false;
	private Color overColor = Color.white;
	private Color lineColor = Color.BLACK;
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);

		if (over) {
			g2d.setColor(overColor);
		} else {
			g2d.setColor(lineColor);
		}

		g2d.fillRect(4, getHeight() - 2, getWidth() - 8, 2);
		g2d.dispose();
	}
}
