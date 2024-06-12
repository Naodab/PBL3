package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import dao.BoardingClassDAO;
import dao.BoardingFeeDAO;
import dao.EatingHistoryDAO;
import dao.FoodDAO;
import dao.GlobalDAO;
import dao.InvoiceDAO;
import dao.MenuDAO;
import dao.ParentsDAO;
import dao.StudentDAO;
import dao.TeacherDAO;
import dao.UserDAO;
import model.BoardingClass;
import model.BoardingFee;
import model.CBBItem;
import model.EatingHistory;
import model.Food;
import model.Invoice;
import model.Menu;
import model.Parents;
import model.Student;
import model.Teacher;
import model.User;
import view.AdminScreen;
import view.BoardingFeeDetail;
import view.ChangeDefaultPassword;
import view.ChangePassword;
import view.DetailBoardingClass;
import view.FoodDetail;
import view.ListInvoiceByBClass;
import view.MenuDetail;
import view.PersonDetail;

public class AdminController implements ActionListener {
	private AdminScreen adminScreen;
	private String object;
	private int amount;
	private BoardingFeeDetail bfd;

	public AdminController(AdminScreen adminScreen) {
		this.adminScreen = adminScreen;
		this.object = "Student";
		this.amount = 20;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public List<Food> getFoodsOfMenu(int menu_id) {
		List<Food> result = new ArrayList<Food>();
		Menu menu = MenuDAO.getInstance().selectById(menu_id);
		List<Integer> listFoods = menu.getFood_ids();
		for (int id : listFoods) {
			result.add(FoodDAO.getInstance().selectById(id));
		}
		return result;
	}

	public User getUserByUsername(String username) {
		return UserDAO.getInstance().selectByUserName(username);
	}

	public BoardingClass getBoardingClassById(int boardingClass_id) {
		return BoardingClassDAO.getInstance().selectById(boardingClass_id);
	}

	public EatingHistory getEatingHistoryById(int eatingHistory_id) {
		return EatingHistoryDAO.getInstance().selectById(eatingHistory_id);
	}

	public Food getFoodByFoodById(int food_id) {
		return FoodDAO.getInstance().selectById(food_id);
	}

	public Teacher getTeacherByFoodById(int teacher_id) {
		return TeacherDAO.getInstance().selectById(teacher_id);
	}

	public Student getStudentById(int student_id) {
		return StudentDAO.getInstance().selectById(student_id);
	}

	public Parents getParentsById(int parents_id) {
		return ParentsDAO.getInstance().selectById(parents_id);
	}

	public Menu getMenuById(int menu_id) {
		return MenuDAO.getInstance().selectById(menu_id);
	}

	public Invoice getInvoiceById(int invoice_id) {
		return InvoiceDAO.getInstance().selectById(invoice_id);
	}

	public long getTotalMoneyOfBoardingFeeId(int boardingFee_id) {
		return InvoiceDAO.getInstance().getTotalMoneyOfBoardingFee(boardingFee_id);
	}

	public long getPayedMoneyOfBoardingFeeId(int boardingFee_id) {
		return InvoiceDAO.getInstance().getPayedMoneyOfBoardingFee(boardingFee_id);
	}

	public int getPayedStudentOfBoardingFee(int boardingFee_id) {
		return InvoiceDAO.getInstance().getPayedStudentOfBoardingFee(boardingFee_id);
	}

	public int getSizeOfStudent() {
		return GlobalDAO.getInstance().getSizeOf("Student", "");
	}

	public int getSizeOfParents() {
		return GlobalDAO.getInstance().getSizeOf("Parents", "");
	}

	public int getSizeOfTeacher() {
		return GlobalDAO.getInstance().getSizeOf("Teacher", "");
	}

	public int getSizeOfBoardingClass() {
		return GlobalDAO.getInstance().getSizeOf("BoardingClass", "");
	}

	public int getSizeOfMenu() {
		return GlobalDAO.getInstance().getSizeOf("Menu", "");
	}

	public int getSizeOfFood() {
		return GlobalDAO.getInstance().getSizeOf("Food", "");
	}

	public int getSizeOfPaidInvoiceOfBoaringFee(int boardingFee_id) {
		return GlobalDAO.getInstance().getSizeOf("Invoice",
				"statusPayment=2 AND boardingFee_id=\'" + boardingFee_id + "\'");
	}

	public int getSizeOfUser() {
		return GlobalDAO.getInstance().getSizeOf("User", "");
	}

	public String getNameBoardingClass(int boardingClass_id) {
		String result = "Không có";
		List<String> ids = GlobalDAO.getInstance().search("boardingClass", "name",
				"boardingClass_id=\'" + boardingClass_id + "\'", 1);
		if (ids.size() != 0)
			result = ids.get(0);
		return result;
	}

	public String getNameTeacherById(int teacher_id) {
		String result = "Không có";
		List<String> ids = GlobalDAO.getInstance().search("teacher", "name", "teacher_id=\'" + teacher_id + "\'", 1);
		if (ids.size() != 0)
			result = ids.get(0);
		return result;
	}

	public String getNameUser(String position, String username) {
		String result = "Không có";
		List<String> ids = GlobalDAO.getInstance().search(position, "name", "phoneNumber=\'" + username + "\'", 1);
		if (ids.size() != 0)
			result = ids.get(0);
		return result;
	}

	public String getUserId(String position, String username) {
		String result = "Không có";
		List<String> ids = GlobalDAO.getInstance().search(position, position + "_id",
				"phoneNumber=\'" + username + "\'", 1);
		if (ids.size() != 0)
			result = ids.get(0);
		return result;
	}

	public static List<LocalDate> getListDateInMonth(int month, int year) {
		List<LocalDate> localDates = new ArrayList<LocalDate>();
		int[] dayOfMonth = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		for (int i = 1; i <= dayOfMonth[month]; i++) {
			LocalDate eatingDay = LocalDate.of(year, month, i);
			DayOfWeek dow = eatingDay.getDayOfWeek();
			if (dow != DayOfWeek.SATURDAY && dow != DayOfWeek.SUNDAY) {
				localDates.add(eatingDay);
			}
		}
		return localDates;
	}

	public String getCondition() {
		String condition = this.adminScreen.getTextSearchOf(object);
		if (condition.length() != 0) {
			String clSearch = this.adminScreen.getColumnSearch(object);
			if (clSearch.equals("LBT")) {
				List<String> cl_ids = GlobalDAO.getInstance().search("boardingClass", "boardingClass_id",
						" name = \'" + condition + "\'", 1);
				if (cl_ids.size() > 0) {
					clSearch = "boardingClass_id";
					condition = " " + clSearch + " LIKE \'%" + cl_ids.get(0) + "%\'";
				}
			} else {
				if (clSearch.equals("category")) {
					if (condition.toLowerCase().equals("chính"))
						condition = " " + clSearch + " IS TRUE";
					else if (condition.toLowerCase().equals("phụ"))
						condition = " " + clSearch + " IS FALSE";
				} else
					condition = " " + clSearch + " LIKE \'%" + condition + "%\'";
			}
		} else
			condition = "";
		return condition;
	}

	public List<String> doSearch() {
		String condition = getCondition();
		List<String> ids = null;
		if (object.equals("User")) {
			ids = GlobalDAO.getInstance().search(object, "username", condition, amount);
		} else
			ids = GlobalDAO.getInstance().search(object, object + "_id", condition, amount);
		return ids;
	}

	public List<String> doSort(byte direction) {
		List<String> ids = null;
		String desc = ((direction == 2) ? " DESC " : "");
		String column = this.adminScreen.getColumnSort(object);
		String condition = getCondition();
		if (column.equals("SHS")) {
			if (condition.length() != 0)
				condition = object + "." + condition;
			ids = GlobalDAO.getInstance().getSortNumberOfStudents(condition, amount, desc);
		} else if (column.equals("STDCM")) {
			if (condition.length() != 0)
				condition = "F." + condition;
			ids = GlobalDAO.getInstance().getSortNumberOfMenu(condition, amount, desc);
		} else {
			ids = GlobalDAO.getInstance().sort(object, column, condition, amount, desc);
		}
		return ids;
	}

	public void doAddMore() {
		List<String> addedRows = new ArrayList<String>();
		byte dr = this.adminScreen.getDirectionSortOf(object);
		if (dr != 3) {
			addedRows = doSort(dr);
		} else {
			addedRows = doSearch();
		}
		switch (object) {
		case "Student":
			this.adminScreen.addRowsStudent(addedRows, true);
			break;
		case "Parents":
			this.adminScreen.addRowsParents(addedRows, true);
			break;
		case "Teacher":
			this.adminScreen.addRowsTeacher(addedRows, true);
			break;
		case "BoardingClass":
			this.adminScreen.addRowsClass(addedRows, true);
			break;
		case "Food":
			this.adminScreen.addRowsFood(addedRows, true);
			break;
		case "Menu":
			this.adminScreen.addRowsMenu(addedRows, true);
			break;
		case "User":
			this.adminScreen.addRowsAccount(addedRows, true);
			break;
		}
	}

	public void doAddMoreInvoice() {
		int bFee_id = ((CBBItem) this.adminScreen.comboBoxBFee.getSelectedItem()).getValue();
		String condition = this.adminScreen.getTextSearchOf(object);
		if (condition.length() != 0) {
			String clSearch = this.adminScreen.getColumnSearch(object);
			if (clSearch.equals("THS")) {
				List<String> cl_ids = GlobalDAO.getInstance().search("student", "student_id",
						" name LIKE \'%" + condition + "%\'", 0);
				if (cl_ids.size() > 0) {
					clSearch = "student_id";
					condition = " " + clSearch + " IN (";
					for (String id : cl_ids) {
						condition += "\'" + id + "\',";
					}
					condition = condition.substring(0, condition.length() - 1) + ")";
				}
			} else if (clSearch.equals("LBT")) {
				List<String> cl_ids = GlobalDAO.getInstance().search("boardingClass", "boardingClass_id",
						" name = \'" + condition + "\'", 1);
				if (cl_ids.size() > 0) {
					clSearch = "boardingClass_id";
					condition = " " + clSearch + " LIKE \'%" + cl_ids.get(0) + "%\'";
				}
			} else if (clSearch.equals("statusPayment")) {
				condition = condition.toLowerCase();
				if (condition.equals("chưa nộp")) {
					condition = clSearch + "=1 ";
				} else if (condition.equals("chưa in")) {
					condition = clSearch + "=2 ";
				} else if (condition.equals("đã in")) {
					condition = clSearch + "=3";
				} else {
					JOptionPane.showMessageDialog(adminScreen,
							"Vui lòng nhập đúng tên trạng thái!\n 1. Chưa nộp.\n 2. Chưa in.\n 3. Đã in");
					return;
				}
			} else
				condition = " " + clSearch + " LIKE \'%" + condition + "%\'";
		}
		if (condition.length() == 0)
			condition += " boardingFee_id = \'" + bFee_id + "\'";
		else
			condition += " AND boardingFee_id = \'" + bFee_id + "\'";
		List<String> ids = GlobalDAO.getInstance().search("invoice", "invoice_id", condition, amount);
		List<Integer> result = new ArrayList<Integer>();
		for (String id : ids) {
			result.add(Integer.parseInt(id));
		}
		this.adminScreen.addRowsInvoice(result, true);
	}

	public void doAddMoreEHis() {
		int bFee_id = ((CBBItem) this.adminScreen.comboBoxEHis.getSelectedItem()).getValue();
		String condition = this.adminScreen.getTextSearchOf(object);

		String extraCondition = " boardingFee_id = \'" + bFee_id + "\'";
		if (condition.length() == 0) {
			condition = extraCondition;
		} else {
			String clSearch = this.adminScreen.getColumnSearch(object);
			condition = " " + clSearch + " LIKE \'%" + condition + "%\'";
			condition += " AND" + extraCondition;
		}
		List<String> ids = GlobalDAO.getInstance().search(object, object + "_id", condition, amount);
		this.adminScreen.addRowsEatingHistory(ids, true);
	}

	public int checkCritical() {
		int result = JOptionPane.YES_OPTION;
		if (this.adminScreen.isCritical()) {
			result = JOptionPane.showConfirmDialog(adminScreen,
					"Những thao tác của bạn có thể bị hủy. Bạn muốn tiếp tục chứ?");
			if (result == JOptionPane.YES_OPTION)
				this.adminScreen.setCritical(false);
		}
		return result;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String src = e.getActionCommand().trim();

		if (src.equals("Quản lý học sinh")) {
			if (checkCritical() == JOptionPane.NO_OPTION)
				return;
			this.object = "Student";
			this.amount = 20;
			this.adminScreen.setCardRight("listStudent");
			this.adminScreen.setNoneSortOf(object);
		} else if (src.equals("Quản lý phụ huynh")) {
			if (checkCritical() == JOptionPane.NO_OPTION)
				return;
			this.object = "Parents";
			this.amount = 20;
			this.adminScreen.setNoneSortOf(object);
			doAddMore();
			this.adminScreen.setCardRight("listParents");
		} else if (src.equals("Quản lý giáo viên")) {
			if (checkCritical() == JOptionPane.NO_OPTION)
				return;
			this.object = "Teacher";
			this.amount = 20;
			this.adminScreen.setNoneSortOf(object);
			doAddMore();
			this.adminScreen.setCardRight("listTeacher");
		} else if (src.equals("Quản lý lớp bán trú")) {
			if (checkCritical() == JOptionPane.NO_OPTION)
				return;
			this.object = "BoardingClass";
			this.amount = 20;
			this.adminScreen.setNoneSortOf(object);
			doAddMore();
			this.adminScreen.setCardRight("listClass");
		} else if (src.equals("Quản lý thực đơn")) {
			if (checkCritical() == JOptionPane.NO_OPTION)
				return;
			this.object = "Menu";
			this.amount = 20;
			doAddMore();
			this.adminScreen.setCardRight("listMenu");
		} else if (src.equals("Lịch sử ngày ăn")) {
			if (checkCritical() == JOptionPane.NO_OPTION)
				return;
			this.object = "EatingHistory";
			this.amount = 0;
			if (adminScreen.comboBoxEHis.getItemCount() > 0) {
				int bFee_id = ((CBBItem) this.adminScreen.comboBoxEHis.getSelectedItem()).getValue();
				BoardingFee bfee = BoardingFeeDAO.getInstance().selectById(bFee_id);
				if (bfee != null)
					doAddMoreEHis();
			}
			this.adminScreen.setCardRight("eatingHistory");
		} else if (src.equals("Quản lý thu tiền")) {
			if (checkCritical() == JOptionPane.NO_OPTION)
				return;
			this.object = "Invoice";
			this.amount = 20;
			if (adminScreen.comboBoxBFee.getItemCount() > 0) {
				int bFee_id = ((CBBItem) this.adminScreen.comboBoxBFee.getSelectedItem()).getValue();
				BoardingFee bfee = BoardingFeeDAO.getInstance().selectById(bFee_id);
				if (bfee != null) {
					this.adminScreen.setBoardingFee(BoardingFeeDAO.getInstance().selectById(bFee_id));
					doAddMoreInvoice();
				}
			}
			this.adminScreen.setCardRight("listBoardingFee");
		} else if (src.equals("Quản lý tài khoản")) {
			if (checkCritical() == JOptionPane.NO_OPTION)
				return;
			this.object = "User";
			this.amount = 20;
			this.adminScreen.setNoneSortOf(object);
			doAddMore();
			this.adminScreen.setCardRight("listAccount");
		} else if (src.equals("Quản lý món ăn")) {
			if (checkCritical() == JOptionPane.NO_OPTION)
				return;
			this.object = "Food";
			this.amount = 20;
			this.adminScreen.setNoneSortOf(object);
			doAddMore();
			this.adminScreen.setCardRight("listFood");
		} else if (src.equals("Đăng xuất")) {
			int returnal = JOptionPane.showConfirmDialog(adminScreen, "Bạn muốn đăng xuất không?");
			if (returnal == JOptionPane.YES_OPTION) {
				this.adminScreen.dispose();
			}
		} else if (src.equals("Thay đổi mật khẩu")) {
			String username = GlobalDAO.getInstance().search("User", "username", "position=\'Admin\'", 1).get(0);
			new ChangePassword(username);
		} else if (src.equals("Thêm học sinh") || src.equals("Thêm phụ huynh") || src.equals("Thêm giáo viên")) { // STUDENT
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				new PersonDetail(object);
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
					| UnsupportedLookAndFeelException ec) {
				ec.printStackTrace();
			}
			doAddMore();
		} else if (src.equals("Xem thêm")) {
			if (amount == 0) {
				JOptionPane.showMessageDialog(adminScreen, "Đã hiển thị tất cả!");
			} else {
				amount += 20;
				if (object.equals("Invoice")) {
					doAddMoreInvoice();
				}
				doAddMore();
			}
		} else if (src.equals("Xem tất cả")) {
			if (amount == 0) {
				JOptionPane.showMessageDialog(adminScreen, "Đã hiển thị tất cả!");
			} else {
				amount = 0;
				if (object.equals("Invoice")) {
					doAddMoreInvoice();
				}
				doAddMore();
			}
		} else if (src.equals("Tìm kiếm")) {
			String search = adminScreen.getTextSearchOf(object);
			if (search.length() == 0) {
				JOptionPane.showMessageDialog(adminScreen, "Vui lòng nhập thông tin tìm kiếm");
			} else {
				amount = 20;
				if (object.equals("Menu")) {
					String clSearch = this.adminScreen.getColumnSearch(object);
					if (clSearch.equals("MC")) {
						List<String> ids = GlobalDAO.getInstance()
								.searchMainFoodInMenu(this.adminScreen.getTextSearchOf(object));
						this.adminScreen.addRowsMenu(ids, true);
						return;
					} else if (clSearch.equals("MP")) {
						List<String> ids = GlobalDAO.getInstance()
								.searchSubFoodInMenu(this.adminScreen.getTextSearchOf(object));
						this.adminScreen.addRowsMenu(ids, true);
						return;
					}
				}
				if (object.equals("Invoice")) {
					doAddMoreInvoice();
				} else if (object.equals("EatingHistory")) {
					doAddMoreEHis();
				} else {
					this.adminScreen.setNoneSortOf(object);
					doAddMore();
				}
			}
		} else if (src.equals("Sắp xếp")) {
			amount = 20;
			doAddMore();
		} else if (src.equals("Thêm lớp bán trú")) {
			new DetailBoardingClass();
		} else if (src.equals("Thêm món ăn")) {
			new FoodDetail();
		} else if (src.equals("Thêm thực đơn")) {
			new MenuDetail();
		} else if (e.getSource() == this.adminScreen.comboBoxBFee) {
			int bFee_id = this.adminScreen.getBoardingFeeid();
			this.adminScreen.setBoardingFee(BoardingFeeDAO.getInstance().selectById(bFee_id));
			doAddMoreInvoice();
		} else if (src.equals("Tạo đợt thu tiền")) {
			bfd = new BoardingFeeDetail(adminScreen);
		} else if (src.equals("Chi tiết")) {
			int bFee_id = this.adminScreen.getBoardingFeeid();
			new BoardingFeeDetail(bFee_id, false);
		} else if (src.equals("In hóa đơn")) {
			String message = "";
			int invoices = adminScreen.getTfPrinted();
			if (invoices == 0) {
				message = "Hiện không có hóa đơn nào để in";
			} else {
				int bFee_id = this.adminScreen.getBoardingFeeid();
				BoardingFee bf = BoardingFeeDAO.getInstance().selectById(bFee_id);
				boolean check = false;
				for (int id : bf.getInvoice_ids()) {
					Invoice iv = InvoiceDAO.getInstance().selectById(id);
					if (iv.getStatusPayment() == 2) {
						iv.setStatusPayment((byte) 3);
						check = InvoiceDAO.getInstance().update(iv);
					}
				}
				if (check) {
					message = "In thành công, nhấn \"Xem thêm\" để làm mới.";
					this.adminScreen.setTfPrinted("0");
				} else
					message = "In không thành công!";
			}
			JOptionPane.showMessageDialog(adminScreen, message);
		} else if (src.equals("Phân thực đơn")) {
			List<EatingHistory> ehies = new ArrayList<EatingHistory>();
			int bFee_id = ((CBBItem) this.adminScreen.comboBoxEHis.getSelectedItem()).getValue();
			BoardingFee bf = bfd.getBoardingFee();
			LocalDate start = bf.getStart_day().toLocalDate();
			List<LocalDate> localDates = getListDateInMonth(start.getMonthValue(), start.getYear());
			List<String> menu_ids = GlobalDAO.getInstance().search("menu", "menu_id", "active is true", 0);
			Random rd = new Random();
			for (LocalDate localDate : localDates) {
				int ran = rd.nextInt(menu_ids.size());
				String menu_id = menu_ids.get(ran);
				menu_ids.remove(ran);
				if (menu_ids.size() == 0) {
					menu_ids = GlobalDAO.getInstance().search("menu", "menu_id", "active is true", 0);
				}
				ehies.add(new EatingHistory(0, Integer.parseInt(menu_id), Date.valueOf(localDate), bFee_id));
			}
			this.adminScreen.setEditEHis();
			this.adminScreen.addRowsCreateEHis(ehies);
			this.adminScreen.setCritical(true);
			this.adminScreen.setTextButtonEHis("Lưu", "Hủy");
			this.adminScreen.setEnableButtonEHis(true, true);
		} else if (src.equals("Lưu")) {
			int returnal = JOptionPane.showConfirmDialog(adminScreen,
					"Những thông tin sẽ không thể thay đổi, bạn chắc không?");
			if (returnal == JOptionPane.YES_NO_OPTION) {
				this.object = "EatingHistory";
				this.amount = 0;
				BoardingFee bfee = bfd.getBoardingFee();
				List<EatingHistory> ehies = this.adminScreen.getListEHis();
				int days = ehies.size();
				boolean isNew = bfd.getIsNew();
				if (isNew) {
					bfee.setNumberDays(days);
					BoardingFeeDAO.getInstance().insert(bfee);
				} else
					BoardingFeeDAO.getInstance().update(bfee);
				for (EatingHistory ehis : ehies) {
					EatingHistoryDAO.getInstance().insert(ehis);
				}
				List<Student> students = StudentDAO.getInstance().selectAll();
				for (Student std : students) {
					long returnMoney = 0;
					int boardingFee_idlast = bfee.getBoardingFee_id() - 1;
					if (boardingFee_idlast != 0) {
						int month = bfee.getStart_day().toLocalDate().getMonthValue() - 1;
						if (month == 0)
							month = 12;
						String condition = "student_id = \'" + std.getStudent_id() + "\'";
						if (month != 8)
							condition += " AND month(absence_day) = \'" + month + "\'";
						int countAbsence = GlobalDAO.getInstance().getSizeOf("absence", condition);
						BoardingFee bfLast = BoardingFeeDAO.getInstance().selectById(boardingFee_idlast);
						returnMoney += countAbsence * bfLast.getMainCosts();
						returnMoney += std.isSubMeal() ? countAbsence * bfLast.getSubCosts() : 0;
					}
					long money = days * bfee.getMainCosts();
					if (std.isSubMeal())
						money += days * bfee.getSubCosts();
					money -= returnMoney;
					Invoice invoice = new Invoice();
					invoice.setStudent_id(std.getStudent_id());
					invoice.setBoardingFee_id(bfee.getBoardingFee_id());
					invoice.setReturnMoney(returnMoney);
					invoice.setPayment_date(bfee.getStart_day());
					invoice.setStatusPayment((byte) 1);
					invoice.setMoney(money);
					InvoiceDAO.getInstance().insert(invoice);
				}
				JOptionPane.showMessageDialog(adminScreen, "Lưu thành công");
				this.adminScreen.setCritical(false);
				this.adminScreen.setEnableButtonEHis(false, false);
				this.adminScreen.setTextButtonEHis("Chỉnh sửa", "Phân thực đơn");
				this.adminScreen.setNonEditEHis();
				doAddMoreEHis();
			}
		} else if (src.equals("Hủy")) {
			int returnal = JOptionPane.showConfirmDialog(adminScreen, "Những thông tin sẽ không được lưu!");
			if (returnal == JOptionPane.YES_OPTION) {
				this.object = "EatingHistory";
				this.amount = 0;
				this.adminScreen.setNonEditEHis();
				this.adminScreen.comboBoxEHis.setSelectedIndex(0);
				this.adminScreen.comboBoxBFee.removeActionListener(this);
				DefaultComboBoxModel<CBBItem> model = (DefaultComboBoxModel<CBBItem>) this.adminScreen.comboBoxBFee
						.getModel();
				model.removeElement(new CBBItem(bfd.getBoardingFee().getBoardingFee_id(), ""));
				this.adminScreen.comboBoxBFee.addActionListener(this);
				this.adminScreen.comboBoxEHis.removeActionListener(this);
				DefaultComboBoxModel<CBBItem> model2 = (DefaultComboBoxModel<CBBItem>) this.adminScreen.comboBoxEHis
						.getModel();
				model2.removeElement(new CBBItem(bfd.getBoardingFee().getBoardingFee_id(), ""));
				this.adminScreen.comboBoxEHis.addActionListener(this);
				this.adminScreen.comboBoxBFee.revalidate();
				this.adminScreen.comboBoxEHis.revalidate();
				this.adminScreen.comboBoxBFee.repaint();
				this.adminScreen.comboBoxEHis.repaint();
				this.adminScreen.setCritical(false);
				this.adminScreen.setEnableButtonEHis(false, false);
				this.adminScreen.setTextButtonEHis("Chỉnh sửa", "Phân thực đơn");
				doAddMoreEHis();
			}
		} else if (e.getSource() == this.adminScreen.comboBoxEHis) {
			doAddMoreEHis();
			int bFee_id = ((CBBItem) this.adminScreen.comboBoxEHis.getSelectedItem()).getValue();
			BoardingFee bf = BoardingFeeDAO.getInstance().selectById(bFee_id);
			if (bf != null) {
				if (bf.getEatingHistory_ids().size() == 0) {
					this.adminScreen.setEnableButtonEHis(false, true);
				} else {
//					LocalDate now = LocalDate.now();
//					if (now.compareTo(bf.getEnd_day().toLocalDate()) < 0) {
//						this.adminScreen.setEnableButtonEHis(true, false);
//					} else {
//						this.adminScreen.setEnableButtonEHis(false, false);
//					}
					this.adminScreen.setEnableButtonEHis(false, false);
				}
			}
		} else if (src.equals("Danh sách theo lớp")) {
			int bFee_id = ((CBBItem) this.adminScreen.comboBoxBFee.getSelectedItem()).getValue();
			new ListInvoiceByBClass(bFee_id);
		} else if (src.equals("Thay đổi mật khẩu mặc định")) {
			new ChangeDefaultPassword();
		}
	}

}
