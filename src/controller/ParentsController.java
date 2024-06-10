package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import dao.BoardingFeeDAO;
import dao.EatingHistoryDAO;
import dao.GlobalDAO;
import dao.InvoiceDAO;
import dao.ParentsDAO;
import dao.StudentDAO;
import dao.TeacherDAO;
import model.BoardingFee;
import model.CBBItem;
import model.EatingHistory;
import model.Invoice;
import model.Parents;
import model.Student;
import model.Teacher;
import view.ChangePassword;
import view.ParentsScreen;
import view.PersonDetail;

public class ParentsController implements ActionListener{
	private ParentsScreen psc;
	
	public ParentsController(ParentsScreen psc) {
		this.psc = psc;
	}
	
	public List<EatingHistory> getMenuEatingHistory(LocalDate start, int days) {
		List<EatingHistory> result = new ArrayList<EatingHistory>();
		int daysOfweek = EatingHistory.getDaysOfWeek(start.getDayOfWeek()) - 1;
		for (int i = 0; i < daysOfweek; i++) {
			result.add(new EatingHistory(0, 0, null, 0));
		}
		for (int i = 0; i <= days; i++) {
			String condition = "eating_day = \'" + start.toString() + "\'";
			List<String> searches = GlobalDAO.getInstance().search("eatingHistory", "eatingHistory_id", condition, 1);
			if (searches.size() == 0)
				result.add(new EatingHistory(0, 0, null, 0));
			else
				result.add(EatingHistoryDAO.getInstance().selectById(Integer.parseInt(searches.get(0))));
			start = start.plusDays(1);
		}
		start = start.plusDays(-1);
		daysOfweek = EatingHistory.getDaysOfWeek(start.getDayOfWeek());
		for (int i = 0; i < 5 - daysOfweek; i++) {
			result.add(new EatingHistory(0, 0, null, 0));
		}
		return result;
	}
	
	public Student selectStudentById(int student_id) {
		return StudentDAO.getInstance().selectById(student_id);
	}
	
	public List<Invoice> getInvoiceByStudentId(int student_id) {
		List<Invoice> result = new ArrayList<Invoice>();
		List<Integer> ids = InvoiceDAO.getInstance().selectByStudentId(student_id);
		for (int invoice_id : ids) {
			result.add(InvoiceDAO.getInstance().selectById(invoice_id));
		}
		return result;
	}
	
	public BoardingFee selectBoardingFeeById(int boardingFee_id) {
		return BoardingFeeDAO.getInstance().selectById(boardingFee_id);
	}
	
	public int selectTeacherIdByBoardingClassId(int boardingClass_id) {
		return TeacherDAO.getInstance().selectByBoardingClass_id(boardingClass_id);
	}
	
	public Teacher selectTeacherById(int teacher_id) {
		return TeacherDAO.getInstance().selectById(teacher_id);
	}
	
	public Parents selectParentsById(int parents_id) {
		return ParentsDAO.getInstance().selectById(parents_id);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == psc.button1) {
			psc.setCardRight("detailParents");
		} else if (e.getSource() == psc.button2) {
			List<CBBItem> listWeeks = EatingHistory.getListWeeks();
			psc.setCBBEhis(listWeeks);
			psc.setCardRight("eatingHistory");
		} else if (e.getSource() == psc.button3) {
			psc.setCardRight("invoice1");
		} else if(e.getSource() == psc.logOut) {
			int returnal = JOptionPane.showConfirmDialog(psc, "Bạn muốn đăng xuất không?");
			if (returnal == JOptionPane.YES_OPTION)
				psc.dispose();
		} else if(e.getSource() == psc.changePw) {
			new ChangePassword(psc.getUserName());
		} else if (e.getSource() == psc.comboBoxStudent) {
			int index = psc.comboBoxStudent.getSelectedIndex();
			if (psc.teacher_ids.size() > 0) {
				int teacher_id = psc.teacher_ids.get(index);
				if (teacher_id != 0) {
					Teacher tc = TeacherDAO.getInstance().selectById(teacher_id);
					psc.textTeacher.setText(tc.getName());
				}
			}
		} else if (e.getSource() == psc.btnTTCTHS) {
			int id = ((CBBItem) psc.comboBoxStudent.getSelectedItem()).getValue();
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				new PersonDetail(id, "Student", false);
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
					| UnsupportedLookAndFeelException e1) {
				e1.printStackTrace();
			}
		} else if (e.getSource() == psc.btnTTCTGV) {
			int index = psc.comboBoxStudent.getSelectedIndex();
			if (psc.teacher_ids.size() > 0) {
				int teacher_id = psc.teacher_ids.get(index);
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					new PersonDetail(teacher_id, "Teacher", false);
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException e1) {
					e1.printStackTrace();
				}
			}
		} else if (e.getSource() == psc.comboBox) {
			int index = psc.comboBox.getSelectedIndex();
			CBBItem selected = (CBBItem) psc.comboBox.getSelectedItem();
			int value = selected.getValue();
			String text = selected.getText();
			
			int pos = text.indexOf('(');
			int year = Integer.parseInt(text.substring(pos + 1, pos + 5));
			int month = Integer.parseInt(text.substring(pos + 6, pos + 8));
			int day = Integer.parseInt(text.substring(pos + 9, pos + 11));
			LocalDate date = LocalDate.of(year, month, day);
			int numberDays = 0;
			
			if (value == 1) {
				int daysOfFirstWeek = EatingHistory.getDaysOfWeek(date.getDayOfWeek());
				numberDays = 5 - daysOfFirstWeek;
			} else if (index == psc.comboBox.getItemCount() - 1) {
				int daysOfLastWeek = EatingHistory.getDaysOfWeek(date.getDayOfWeek());
				numberDays = daysOfLastWeek;
			} else {
				numberDays = 4;
			}
			List<EatingHistory> ehises = this.getMenuEatingHistory(date, numberDays);
			psc.setMenuEatingHistory(ehises);
		}
	}
}
