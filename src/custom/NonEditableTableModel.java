package custom;

import javax.swing.table.DefaultTableModel;

public class NonEditableTableModel extends DefaultTableModel {
	private static final long serialVersionUID = 1L;
	private boolean[] editableColumns;

	public NonEditableTableModel(Object[][] data, Object[] columnNames, boolean[] editableColumns) {
		super(data, columnNames);
		this.editableColumns = editableColumns;
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return editableColumns[column];
	}
}