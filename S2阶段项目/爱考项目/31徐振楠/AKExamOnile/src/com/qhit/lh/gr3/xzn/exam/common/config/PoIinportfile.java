package com.qhit.lh.gr3.cyh.exam.common.config;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.qhit.lh.gr3.cyh.exam.question.bean.TCourse;
import com.qhit.lh.gr3.cyh.exam.question.bean.TWrittenTest;
import com.qhit.lh.gr3.cyh.exam.question.service.BaseService;
import com.qhit.lh.gr3.cyh.exam.question.service.impl.BaseServiceImpl;


public class PoIinportfile {
	private BaseService bs = new BaseServiceImpl();

	@SuppressWarnings("resource")
	public List<TWrittenTest> InportExcel(FileInputStream is, TCourse course) {
		List<TWrittenTest> Writtenlist = new ArrayList<TWrittenTest>();
		// 创建工作簿对象
		Workbook workbook = null;
		try {
			// HSSF类只支持07年以前的Excel(文件扩展名为.xls)
			workbook = new HSSFWorkbook(is);
		} catch (Exception e) {
			// TODO: handle exception
			try {
				// XSSF类支持07年以后得Excel(文件扩展名为.xlsx)
				workbook = new XSSFWorkbook(is);
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		// 通过工作簿对象获得工作表对象,默认读取第一个工作表
		Sheet sheet = workbook.getSheetAt(0);
		// 第一行,从第一行开始读,工作表下标是从0开始,但第一行是表头,无用,知道就好
		int fristRow = 1;
		//获得最后一个有值的行(总共6行,下标为0~5)
		int lastRow = sheet.getPhysicalNumberOfRows();
		System.out.println("lastRow:"+lastRow);	
		//for循环,执行条件  i 到6就停止了,不再执行循环体了(由于第一格是表头,没用,所以就是由1~5);
		//for(int i=1;i<6;i++){}
		for (int i = fristRow; i < lastRow; i++) {
			System.out.println("i行:"+i);
			// 获得每一行的行对象
			Row row = sheet.getRow(i);
			// 从第一个单元格开始读
			int fristCell = 0;
			// 获得最后一个有值的单元格索引(总共9格,下标为0~8)
			int lastCell = row.getLastCellNum();
			System.out.println("lastCell:"+lastCell);
			// 创建笔试题对象
			TWrittenTest written = new TWrittenTest();
			// 如果第一行就是空值的话,就没必要接着读了,结束这次for循环
			if (row.getCell(0).getStringCellValue() == null || "".equals(row.getCell(0).getStringCellValue())) {
				break;
			}
			//for循环 j 到9停止了,下标就应该是0到8
			//for(int j=0;j<9;j++){}
			for (int j = fristCell; j < lastCell; j++) {
				System.out.println("j列:"+j);
				// 获得第一个单元格对象
				Cell cell = row.getCell(j);
				// 通过setCellType将单元格类型设置为字符串
				cell.setCellType(Cell.CELL_TYPE_STRING);
				// 通过switch根据下标判断,第几个单元格内容就是第几个笔试题对象的属性
				switch (j) {
				case 0:
					written.setQtype(cell.getStringCellValue());
					break;
				case 1:
					written.setQtitle(cell.getStringCellValue());
					break;
				case 2:
					written.setOptionA(cell.getStringCellValue());
					break;
				case 3:
					written.setOptionB(cell.getStringCellValue());
					break;
				case 4:
					written.setOptionC(cell.getStringCellValue());
					break;
				case 5:
					written.setOptionD(cell.getStringCellValue());
					break;
				case 6:
					written.setAnswer(cell.getStringCellValue());
					break;
				case 7:
					written.setDegree(cell.getStringCellValue());
					break;
				case 8:
					written.setChapter(cell.getStringCellValue());
					break;
				default:
					break;
				}
			}
			// TCourse course1=(TCourse) bs.getObject(TWrittenTest.class, course.getCsId());
			// 通过科目表赋值csId
			written.setCourse(course);
			// 把此笔试题对象放入笔试题集合中
			Writtenlist.add(written);
			System.out.println("试题集合添加完毕");
		}
		// 返回出去
		return Writtenlist;
	}
}
