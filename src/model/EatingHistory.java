package model;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import dao.EatingHistoryDAO;
import dao.GlobalDAO;

public class EatingHistory {
	private int eatingHistory_id;
	private int menu_id;
	private Date eating_day;
	private int boardingFee_id;

	public EatingHistory() {
		super();
	}

	public EatingHistory(int eatingHistory_id, int menu_id, Date eating_day, int boardingFee_id) {
		super();
		this.eatingHistory_id = eatingHistory_id;
		this.menu_id = menu_id;
		this.eating_day = eating_day;
		this.boardingFee_id = boardingFee_id;
	}

	public int getEatingHistory_id() {
		return eatingHistory_id;
	}

	public void setEatingHistory_id(int eatingHistory_id) {
		this.eatingHistory_id = eatingHistory_id;
	}

	public int getMenu_id() {
		return menu_id;
	}

	public void setMenu_id(int menu_id) {
		this.menu_id = menu_id;
	}

	public Date getEating_day() {
		return eating_day;
	}

	public void setEating_day(Date eating_day) {
		this.eating_day = eating_day;
	}

	public int getBoardingFee_id() {
		return boardingFee_id;
	}

	public void setBoardingFee_id(int boardingFee_id) {
		this.boardingFee_id = boardingFee_id;
	}

	public static int getDaysOfWeek(DayOfWeek dow) {
		int result = 0;
		switch (dow) {
		case MONDAY:
			result = 1;
			break;
		case TUESDAY:
			result = 2;
			break;
		case WEDNESDAY:
			result = 3;
			break;
		case THURSDAY:
			result = 4;
			break;
		case FRIDAY:
			result = 5;
		default:
			break;
		}
		return result;
	}

	public static List<CBBItem> getListWeeks() {
		List<CBBItem> result = new ArrayList<CBBItem>();

		int eH_id = GlobalDAO.getInstance().getLastIDOf("eatingHistory");
		EatingHistory last_EH = EatingHistoryDAO.getInstance().selectById(eH_id);
		eH_id = GlobalDAO.getInstance().getFirstIDOf("eatingHistory");
		EatingHistory start_EH = EatingHistoryDAO.getInstance().selectById(eH_id);
		LocalDate last_day = last_EH.getEating_day().toLocalDate();
		LocalDate start_day = start_EH.getEating_day().toLocalDate();
		int daysOfFirstWeek = getDaysOfWeek(start_day.getDayOfWeek());
		int daysOfLastWeek = getDaysOfWeek(last_day.getDayOfWeek());
		long daysBetween = ChronoUnit.DAYS.between(start_day, last_day) - daysOfLastWeek - (7 - daysOfFirstWeek + 1) + 1;
		long weeks = daysBetween / 7 + 2;
		result.add(new CBBItem(1, "Tuần 1(" + start_day + " - " + start_day.plusDays(5 - daysOfFirstWeek) + ")"));
		start_day = start_day.plusDays(7 - daysOfFirstWeek + 1);
		for (int i = 2; i <= weeks - 1; i++) {
			result.add(new CBBItem(i, "Tuần " + i + "(" + start_day + " - " + start_day.plusDays(4) + ")"));
			start_day = start_day.plusDays(7);
		}
		result.add(new CBBItem((int) weeks, "Tuần " + weeks + "(" + start_day + " - " + last_day + ")"));
		return result;
	}
}