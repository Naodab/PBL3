package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import dao.BoardingClassDAO;
import dao.GlobalDAO;
import dao.StudentDAO;
import model.BoardingClass;
import model.Student;
import view.DetailBoardingClass;

public class DetailClassCTL implements ActionListener {
	private DetailBoardingClass dbc;

	public DetailClassCTL(DetailBoardingClass dbc) {
		this.dbc = dbc;
	}

	public BoardingClass selectBoardingClassById(int boardingClass_id) {
		return BoardingClassDAO.getInstance().selectById(boardingClass_id);
	}

	public List<Student> selectStudentsByBoaringClassId(int boardingClass_id) {
		List<Student> result = new ArrayList<Student>();
		List<Integer> ids = StudentDAO.getInstance().selectByBoardingClass_id(boardingClass_id);
		for (int id : ids) {
			result.add(StudentDAO.getInstance().selectById(id));
		}
		return result;
	}

	public List<String> selecltTeacherIdNonClass() {
		return GlobalDAO.getInstance().search("Teacher", "Teacher_id", "boardingClass_id=0", 0);
	}

	public int getNewIdBoardingClass() {
		return GlobalDAO.getInstance().getAuto_IncrementOf("BoardingClass");
	}

	public String getNameParents(int parents_id) {
		List<String> names = GlobalDAO.getInstance().search("parents", "name", "parents_id = \'" + parents_id + "\'", 1);
		String result = "Không có";
		if (names.size() != 0) {
			result = names.get(0);
		}
		return result;
	}
	
	public String getNameTeacher(int teacher_id) {
		List<String> names = GlobalDAO.getInstance().search("teacher", "name", "teacher_id = \'" + teacher_id + "\'", 1);
		String result = "Không có";
		if (names.size() != 0) {
			result = names.get(0);
		}
		return result;
	}
	
	public int getLastIdBoardingClass() {
		return GlobalDAO.getInstance().getLastIDOf("boardingClass");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String src = e.getActionCommand();
		if (src.equals("Đóng")) {
			dbc.dispose();
		} else if (src.equals("Lưu")) {
			if (this.dbc.checkBeforeSave()) {
				BoardingClass bc = this.dbc.getBoardingClass();
				if (this.dbc.getIsNew()) {
					BoardingClassDAO.getInstance().insert(bc);
					JOptionPane.showMessageDialog(this.dbc, "Tạo thành công!");
				} else {
					BoardingClassDAO.getInstance().update(bc);
					JOptionPane.showMessageDialog(this.dbc, "Cập nhật thành công!");
				}
				this.dbc.dispose();
			}
		} else if (e.getSource() == this.dbc.comboBox) {
			String selection = this.dbc.comboBox.getSelectedItem() + "";
			if (!selection.equals("Không có")) {
				this.dbc.tfTeacher.setText(GlobalDAO.getInstance()
						.search("teacher", "name", "teacher_id = \'" + selection + "\'", 1).get(0));
			} else {
				this.dbc.tfTeacher.setText("");
			}
		}
	}
}
