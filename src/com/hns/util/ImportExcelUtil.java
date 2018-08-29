package com.hns.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ImportExcelUtil {

	private static final String EXCEL_XLS = "xls";
	private static final String EXCEL_XLSX = "xlsx";

	/**
	 * �ж�Excel�İ汾,��ȡWorkbook
	 * 
	 * @param in
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public static Workbook getWorkbok(InputStream in, File file)
			throws IOException {
		Workbook wb = null;
		if (file.getName().endsWith(EXCEL_XLS)) { // Excel 2003
			wb = new HSSFWorkbook(in);
		} else if (file.getName().endsWith(EXCEL_XLSX)) { // Excel 2007/2010
			wb = new XSSFWorkbook(in);
		}
		return wb;
	}

	/**
	 * �ж��ļ��Ƿ���excel
	 * 
	 * @throws Exception
	 */
	public static void checkExcelVaild(File file) throws Exception {
		if (!file.exists()) {
			throw new Exception("�ļ�������");
		}
		if (!(file.isFile() && (file.getName().endsWith(EXCEL_XLS) || file
				.getName().endsWith(EXCEL_XLSX)))) {
			throw new Exception("�ļ�����Excel");
		}
	}

	/**
	 * ��ȡExcel���ԣ����� Excel 2003/2007/2010
	 * 
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			// ͬʱ֧��Excel 2003��2007
			File excelFile = new File("G:/test/poi/bl.xlsx"); // �����ļ�����
			FileInputStream in = new FileInputStream(excelFile); // �ļ���
			checkExcelVaild(excelFile);
			Workbook workbook = getWorkbok(in, excelFile);
			// Workbook workbook = WorkbookFactory.create(is); // ���ַ�ʽ
			// Excel2003/2007/2010���ǿ��Դ����

			int sheetCount = workbook.getNumberOfSheets(); // Sheet������

			/**
			 * ���õ�ǰexcel��sheet���±꣺0��ʼ
			 */
			Sheet sheet = workbook.getSheetAt(0); // ������һ��Sheet
			// Sheet sheet = workbook.getSheetAt(2); // ����������Sheet

			// ��ȡ������
			// System.out.println(sheet.getLastRowNum());

			int count = 0;
			for (Row row : sheet) {
				try {
					// ������һ�͵ڶ��е�Ŀ¼
					if (count < 2) {
						count++;
						continue;
					}

					// �����ǰ��û�����ݣ�����ѭ��
					if (row.getCell(0).toString().equals("")) {
						return;
					}

					// ��ȡ������(�ո�Ĳ�����)
					// int columnTotalNum = row.getPhysicalNumberOfCells();
					// System.out.println("��������" + columnTotalNum);
					// System.out.println("���������" + row.getLastCellNum());

					int end = row.getLastCellNum();

					for (int i = 0; i < end; i++) {
						Cell cell = row.getCell(i);
						if (cell == null) {
							System.out.print("null" + "\t");
							continue;
						}

						Object obj = getValue(cell);
						System.out.print(obj + "\t");
					}
					System.out.println();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static Object getValue(Cell cell) {
		Object obj = null;
		switch (cell.getCellType()) {
		case 4:
			obj = cell.getBooleanCellValue();
			break;
		case 5:
			obj = cell.getErrorCellValue();
			break;
		case 0:
			obj = cell.getNumericCellValue();
			break;
		case 1:
			obj = cell.getStringCellValue();
			break;
		default:
			break;
		}
		return obj;
	}

	public <T_T> List<T_T> readBean(InputStream inputStream,
			Map<String, Object> headerMapper, Class<T_T> tClass)
			throws Exception {

		List<T_T> list = new ArrayList<>();

		ArrayList<ArrayList<String>> rows = new ArrayList<>();

		for (int k = 1; k < rows.size(); k++) {
			T_T t = tClass.newInstance();
			for (int num = 0; num < rows.get(0).size(); num++) {

			}
			list.add(t);
		}

		return list;
	}

}
