package custom;

import javax.swing.JPanel;

import controller.TableActionEvent;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import java.awt.FlowLayout;

public class PanelAction extends JPanel {
	private static final int SIZE_BUTTON = 20;
	private static final long serialVersionUID = 1L;
	private ActionButton edit;
	private ActionButton delete;
	private ActionButton detail;

	public PanelAction() {
		initComponent();
	}

	private void initComponent() {
		setLayout(new FlowLayout(FlowLayout.CENTER, 1, 1));

		edit = new ActionButton();
		edit.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(PanelAction.class.getResource("icon/pencil.png"))
						.getScaledInstance(SIZE_BUTTON, SIZE_BUTTON, Image.SCALE_SMOOTH)));
		add(edit);

		delete = new ActionButton();
		delete.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(PanelAction.class.getResource("icon/remove.png"))
						.getScaledInstance(SIZE_BUTTON, SIZE_BUTTON, Image.SCALE_SMOOTH)));
		add(delete);

		detail = new ActionButton();
		detail.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(PanelAction.class.getResource("icon/search.png"))
						.getScaledInstance(SIZE_BUTTON, SIZE_BUTTON, Image.SCALE_SMOOTH)));
		add(detail);
	}

	public void initEvent(TableActionEvent event, int row) {
		edit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				event.onEdit(row);
			}
		});

		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				event.onDelete(row);
			}
		});

		detail.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				event.onView(row);
			}
		});
	}
}
