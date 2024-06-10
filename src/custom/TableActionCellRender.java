package custom;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class TableActionCellRender extends DefaultTableCellRenderer {
	private static final long serialVersionUID = 1L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		if (row >= ((DefaultTableModel) table.getModel()).getRowCount()) {
			row -= 1;
		}
		Component com = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

		PanelAction action = new PanelAction();
		action.setBackground(com.getBackground());
		return action;
	}
}
