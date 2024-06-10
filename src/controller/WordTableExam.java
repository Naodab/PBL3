package controller;

import org.apache.poi.xwpf.usermodel.*;

import dao.BoardingClassDAO;
import dao.BoardingFeeDAO;
import dao.GlobalDAO;
import dao.InvoiceDAO;
import dao.StudentDAO;
import model.BoardingClass;
import model.BoardingFee;
import model.Invoice;
import model.Student;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class WordTableExam {
	public static void makeTableInvoiceForClass(XWPFDocument doc, BoardingFee bfee, int boardingClass_id) {
		BoardingClass bclass = BoardingClassDAO.getInstance().selectById(boardingClass_id);

		XWPFParagraph title = doc.createParagraph();
		XWPFRun runTitle = title.createRun();
		runTitle.setBold(true);
		runTitle.setText("\nLớp " + bclass.getName() + ":");
		runTitle.setFontFamily("Times New Roman");
		runTitle.setFontSize(16);
		title.setAlignment(ParagraphAlignment.LEFT);

		String[] columnName = { "STT", "Mã HS", "Họ và tên", "Chi phí dư", "Buổi chính", "Buổi phụ", "Số ngày học",
				"Số tiền nộp" };
		int row = bclass.getStudent_ids().size() + 1;
		int column = columnName.length;
		XWPFTable table = doc.createTable(row, column);
		table.setWidth(10000);
		XWPFTableRow headerTable = table.getRow(0);
		for (int i = 0; i < column; i++) {
			XWPFTableCell cell = headerTable.getCell(i);
			XWPFParagraph paragraph = cell.getParagraphs().get(0);
			XWPFRun run = paragraph.createRun();
			run.setText(columnName[i]);
			run.setFontFamily("Times New Roman");
			run.setFontSize(12);
			run.setBold(true);
			paragraph.setAlignment(ParagraphAlignment.CENTER);
			paragraph.setVerticalAlignment(TextAlignment.CENTER);
		}

		List<Invoice> invoices = new ArrayList<Invoice>();
		for (int id : bclass.getStudent_ids()) {
			Invoice iv = InvoiceDAO.getInstance().selectByBoardingFeeIdandStudentID(bfee.getBoardingFee_id(), id);
			if (iv != null)
				invoices.add(iv);
		}
		int count = 1;
		long mainCosts = bfee.getMainCosts();
		long subCosts = bfee.getSubCosts();
		int numberDays = bfee.getNumberDays();

		for (int i = 0; i < invoices.size(); i++) {
			Invoice iv = invoices.get(i);
			Student std = StudentDAO.getInstance().selectById(iv.getStudent_id());
			long subCost = (std.isSubMeal()) ? subCosts : 0;
			String[] currentRow = { (count++) + " ", iv.getStudent_id() + " ", " " + std.getName(), iv.getReturnMoney() + " ",
					mainCosts + " ", subCost + " ", numberDays + " ", iv.getMoney() + " " };
			XWPFTableRow r = table.getRow(i + 1);
			for (int j = 0; j < column; j++) {
				XWPFTableCell cell = r.getCell(j);
				XWPFParagraph paragraph = cell.getParagraphs().get(0);
				XWPFRun run = paragraph.createRun();
				run.setText(currentRow[j]);
				run.setFontFamily("Times New Roman");
				run.setFontSize(12);

				if (j != 2)
					paragraph.setAlignment(ParagraphAlignment.RIGHT);
				paragraph.setVerticalAlignment(TextAlignment.AUTO);
			}
		}
	}

	public static void makeListClass(int boardingFee_id, String path) {
		BoardingFee bfee = BoardingFeeDAO.getInstance().selectById(boardingFee_id);
		XWPFDocument doc = new XWPFDocument();

		LocalDate start = bfee.getStart_day().toLocalDate();
		XWPFParagraph title = doc.createParagraph();
		XWPFRun runTitle = title.createRun();
		runTitle.setBold(true);
		runTitle.setText("ĐỢT THU TIỀN THÁNG " + start.getMonthValue() + " NĂM " + start.getYear());
		runTitle.setFontFamily("Times New Roman");
		runTitle.setFontSize(22);
		title.setAlignment(ParagraphAlignment.CENTER);

		List<String> class_ids = GlobalDAO.getInstance().search("boardingClass", "boardingClass_id",
				"boardingClass_id<>0", 0);
		for (String id : class_ids) {
			makeTableInvoiceForClass(doc, bfee, Integer.parseInt(id));
		}
		try (FileOutputStream out = new FileOutputStream(path + "\\" + "DTT Tháng " + start.getMonthValue() + ".docx")) {
			doc.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		
	}
}