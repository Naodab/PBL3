package controller;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import dao.InvoiceDAO;
import dao.StudentDAO;
import model.Invoice;
import model.Student;
import view.InvoiceView;
import view.PersonDetail;

public class TableTeacherCTL implements TableActionEvent {
	private String object;
	private DefaultTableModel model;
	
	
	public TableTeacherCTL(String object, DefaultTableModel model) {
		this.object = object;
		this.model = model;
	}

	//teacher cannot edit anything
	@Override
	public void onEdit(int row) {
		
	}

	//teacher cannot delete anything
	@Override
	public void onDelete(int row) {
		
	}

	//teacher can view detail information of an object
	@Override
	public void onView(int row) {
		int id = Integer.parseInt(model.getValueAt(row, 1) + "");
		if (object.equals("Invoice")) {
			Invoice iv = InvoiceDAO.getInstance().selectById(id);
			if (iv.getStatusPayment() == 1) {
				JOptionPane.showMessageDialog(null, "Hiện chưa nộp học phí!");
			} else if (iv.getStatusPayment() == 2) {
				JOptionPane.showMessageDialog(null, "Hiện chưa in hóa đơn!");
			} else {
				new InvoiceView(id);
			}
		} else {
			if (object.equals("Student")) {
				new PersonDetail(id, "Student", false);
			}
			else if (object.equals("Parents")) {
				Student std = StudentDAO.getInstance().selectById(id);
				new PersonDetail(std.getParents_id(), "Parents", false);
			}
		}
	}

}
