package custom;

import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

import controller.TableActionEvent;

public class TableActionTcCellEditor extends DefaultCellEditor{
	private static final long serialVersionUID = 1L;
	private TableActionEvent event;
	
	public TableActionTcCellEditor (TableActionEvent event) {
		super(new JCheckBox());
		this.event = event;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		PanelActionTeacher action = new PanelActionTeacher();
		action.initEvent(event, row);
		action.setBackground(table.getSelectionBackground());
		return action;
	}
}
