package custom;

import javax.swing.JPanel;

import controller.TableActionEvent;

import javax.swing.ImageIcon;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelActionTeacher extends JPanel {
	private static final int SIZE_BUTTON = 20;
	private static final long serialVersionUID = 1L;
	private ActionButton cmdView;

	/**
	 * Create the panel.
	 */
	public PanelActionTeacher() {
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		cmdView = new ActionButton();
		cmdView.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(PanelAction.class.getResource("icon/search.png"))
						.getScaledInstance(SIZE_BUTTON, SIZE_BUTTON, Image.SCALE_SMOOTH)));
		add(cmdView);
	}

	public void initEvent(TableActionEvent event, int row) {
		cmdView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				event.onView(row);
			}
		});
	}
}
