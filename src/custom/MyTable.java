package custom;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JTable;
public class MyTable extends JTable{
	private static final long serialVersionUID = 1L;
	private static final Color RIGHT_COLOR = new Color(209, 174, 191);
	private static final Color LEFT_COLOR = new Color(252, 219, 215);

	public MyTable() {
		setBackground(new Color(0, 0, 0, 0));
		color1 = RIGHT_COLOR;
		color2 = LEFT_COLOR;
	}
	
	private Color color1;
	private Color color2;
	
	
	public Color getColor1() {
		return color1;
	}


	public void setColor1(Color color1) {
		this.color1 = color1;
	}


	public Color getColor2() {
		return color2;
	}


	public void setColor2(Color color2) {
		this.color2 = color2;
	}


	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setPaint(new GradientPaint(0, getHeight(), color1, getWidth(), getHeight(), color2));
		g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 0, 0);
		super.paintComponent(g);
	}
}
