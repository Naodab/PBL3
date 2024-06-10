//package test;
//
//import java.io.FileOutputStream;
//import java.io.IOException;
//import org.apache.poi.*;
//
//public class Testhui {
//    public static void main(String[] args) {
//        try {
//            // Tạo một tài liệu Word mới
//            Document document = new XWPFDocument();
//
//            // Tạo một đoạn văn bản
//            XWPFParagraph paragraph = document.createParagraph();
//
//            // Tạo một đoạn văn bản
//            XWPFRun run = paragraph.createRun();
//
//            // Thêm nội dung vào đoạn văn bản
//            run.setText("Đây là một tài liệu Word được tạo bằng Java.");
//
//            // Lưu tài liệu vào tệp
//            FileOutputStream out = new FileOutputStream("output.docx");
//            document.write(out);
//            out.close();
//
//            System.out.println("Tạo tệp Word thành công!");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
package test;

import dao.GlobalDAO;
import dao.InvoiceDAO;

public class Testhui {
	public static void main(String[] args) {
//		String date = "(1002-22-22)";
//		int pos = date.indexOf('(');
//		int year = Integer.parseInt(date.substring(pos + 1, pos + 5));
//		int month = Integer.parseInt(date.substring(pos + 6, pos + 8));
//		int day = Integer.parseInt(date.substring(pos + 9, pos + 11));
//		System.out.println(year + " " + month + " " + day);
		System.out.println(InvoiceDAO.getInstance().getTotalMoneyOfBoardingFee(1));
	}
}