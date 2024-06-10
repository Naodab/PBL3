package custom;

import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.TableActionEvent;

public class TableActionCellEditor extends DefaultCellEditor{

	private TableActionEvent event;
	public TableActionCellEditor(TableActionEvent event) {
		super(new JCheckBox());
		this.event = event;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		if (row >= ((DefaultTableModel) table.getModel()).getRowCount()) {
			row -= 1;
		}
		PanelAction action = new PanelAction();
		action.initEvent(event, row);
		action.setBackground(table.getSelectionBackground());
		return action;
	}
}
