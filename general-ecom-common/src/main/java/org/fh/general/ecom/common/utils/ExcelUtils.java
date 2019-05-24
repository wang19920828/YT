package org.fh.general.ecom.common.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.*;

import javax.imageio.ImageIO;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.POIXMLDocumentPart;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFPicture;
import org.apache.poi.xssf.usermodel.XSSFPictureData;
import org.apache.poi.xssf.usermodel.XSSFShape;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.fh.general.ecom.common.comm.ExportEntity;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTMarker;
@Slf4j
public class ExcelUtils {

	/**
	 * 解析excel
	 *
	 * @param in
	 * @param fileName
	 *            文件名
	 * @param className
	 *            实体类的全路径
	 * @param colList
	 *            需要导入的列名
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List parseExcel(InputStream in, String fileName, String className, ArrayList<String> colList)
			throws Exception {
		List list = new ArrayList();
		try {
			Workbook wb = null;
			if (fileName.matches("^.+\\.(?i)(xls)$")) {
				// Excel 2003
				wb = new HSSFWorkbook(in);
			} else if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
				// Excel 2007
				wb = new XSSFWorkbook(in);
			}
			Sheet sheet = wb.getSheetAt(0);
			int intRowNum = sheet.getPhysicalNumberOfRows();
			for (int i = 1; i < intRowNum; i++) {
				Row row = sheet.getRow(i);
				if (row != null) {
					String fieldName = ExcelUtils.readValue(row.getCell(0));
					if (!StringUtils.isEmpty(fieldName)) {
						Class<?> forName = Class.forName(className);
						Object newInstance = forName.newInstance();
						String name = className.substring(className.lastIndexOf(".") + 1, className.length())
								.toLowerCase();
						Field f = forName.getDeclaredField(name + "Id");
						f.setAccessible(true);
						f.set(newInstance, Long.valueOf(i));
						for (int j = 0; j < colList.size(); j++) {
							Field field = forName.getDeclaredField(colList.get(j));
							field.setAccessible(true);
							Class cls = field.getType();
							if (BigDecimal.class == cls) {
								field.set(newInstance, new BigDecimal(readValue(row.getCell(j))));
							} else {
								field.set(newInstance, ExcelUtils.readValue(row.getCell(j)));
							}
						}
						list.add(newInstance);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("导入失败，错误信息：" + e.getMessage());
		}
		return list;
	}

	/*
	 * 读取单元格
	 */
	private static String readValue(Cell cell) {
		String cellValue = "";
		if (cell == null) {
			return "";
		}
		int cellType = cell.getCellType();
		if (cellType == Cell.CELL_TYPE_STRING) {
			cellValue = cell.getStringCellValue();
		} else if (cellType == Cell.CELL_TYPE_NUMERIC) {
			DecimalFormat df = new DecimalFormat("#");
			cellValue = df.format(cell.getNumericCellValue());
		}
		return cellValue;
	}

	/**
	 * 导出Excel
	 *
	 * param label
	 *            列头数组定义
	 * param fileName
	 *            生成Excel的文件名
	 * param dataList
	 *            需要导入的数据
	 * throws Exception
	 */
	@SuppressWarnings("deprecation")
	public static HSSFWorkbook export(ExportEntity entity, float high, short width, String ossPath) throws Exception {
		HSSFWorkbook workbook = null;
		HSSFSheet sheet = null;
		try {
			workbook = new HSSFWorkbook(); // 创建工作簿对象
			FileOutputStream fos = new FileOutputStream("test.xls"); // 创建.xls文件
			sheet = workbook.createSheet(); // 创建工作表

			sheet.setDefaultColumnWidth(width); // 设置工作表列宽
			// sheet.setDefaultRowHeight((short)20); // 设置工作表行高

			// sheet样式定义
			HSSFCellStyle columnTopStyle = ExcelUtils.getColumnTopStyle(workbook);// 获取列头样式对象
			HSSFCellStyle style = ExcelUtils.getStyle(workbook); // 单元格样式对象

			// 设置列头
			HSSFRow row1 = sheet.createRow((short) 0); // 在索引0的位置创建行(最顶端的行)
			HSSFCell cell1 = null; // 在索引0的位置创建单元格(左上端)

			if (null != entity && entity.getTitles().size() > 0) {
				// 定义所需列数
				// int columnNum = entity.getTitles().size();
				int n = 0;
				// for (String key : entity.getTitles().keySet()) {
				// cell1 = row1.createCell((short)(n)); //创建列头对应个数的单元格
				// cell1.setCellType(HSSFCell.CELL_TYPE_STRING); //设置列头单元格的数据类型
				// cell1.setCellValue(entity.getTitles().get(key)); //设置列头单元格的值
				// //设置列头单元格样式
				// cell1.setCellStyle(columnTopStyle);
				// n++;
				// }

				for (int t = 0; t < entity.getTitles().size(); t++) {
					Map<String, Object> data = entity.getTitles().get(t);
					cell1 = row1.createCell((short) (n)); // 创建列头对应个数的单元格
					cell1.setCellType(HSSFCell.CELL_TYPE_STRING); // 设置列头单元格的数据类型
					cell1.setCellValue((String) data.get("cnName")); // 设置列头单元格的值
					// 设置列头单元格样式
					cell1.setCellStyle(columnTopStyle);
					n++;
				}

			}

			for (int i = 0; i < entity.getDatas().size(); i++) {
				Map<String, Object> data = entity.getDatas().get(i);// 遍历每个对象
				HSSFRow row = sheet.createRow(i + 1); // 创建所需的行数
				row.setHeightInPoints(80);
				int j = 0;
				for (int t = 0; t < entity.getTitles().size(); t++) {
					Map<String, Object> title = entity.getTitles().get(t);
					int cellType = (Integer) title.get("type");
					createCell(workbook, sheet, row, cellType, i, j, data.get(title.get("enName")), style, high,
							ossPath);
					j++;
				}

			}

			/*
			 * //将数据设置到sheet对应的单元格中 for(int i=0; i < entity.getDatas().size();
			 * i++){ Map<String, Object> data =
			 * entity.getDatas().get(i);//遍历每个对象 HSSFRow row =
			 * sheet.createRow(i+1); //创建所需的行数 row.setHeightInPoints(80); int j
			 * = 0; for(String key : entity.getTitles().keySet()){ int cellType
			 * = entity.getDataTypes().get(key);
			 * createCell(workbook,sheet,row,cellType
			 * ,i,j,data.get(key),style,ossPath); j++; }
			 *
			 * }
			 */
			workbook.write(fos);// 将workbook对象输出到文件test.xls
			fos.flush(); // 缓冲
			fos.close(); // 关闭流

		} catch (Exception e) {
			e.printStackTrace();
		}
		return workbook;
	}

	/**
	 * 导出订单
	 *
	 *  param entity
	 *  param high
	 *  param width
	 *  param ossPath
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static HSSFWorkbook exportOrder(List<Map<String, Object>> list) throws Exception {
		HSSFWorkbook workbook = null;
		HSSFSheet sheet = null;
		try {
			workbook = new HSSFWorkbook(); // 创建工作簿对象
			FileOutputStream fos = new FileOutputStream("test.xls"); // 创建.xls文件
			sheet = workbook.createSheet(); // 创建工作表
			sheet.setColumnWidth(0, 6500);
			sheet.setColumnWidth(1, 3500);
			sheet.setColumnWidth(2, 3500);
			sheet.setColumnWidth(3, 3000);
			sheet.setColumnWidth(4, 6500);
			sheet.setColumnWidth(5, 3000);
			sheet.setColumnWidth(6, 2000);
			sheet.setColumnWidth(7, 4000);
			sheet.setColumnWidth(8, 4000);

			// Sheet样式
		    HSSFCellStyle sheetStyle = workbook.createCellStyle();
		    // 背景色的设定
		    sheetStyle.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);
		    // 前景色的设定
		    sheetStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		    // 填充模式
		    sheetStyle.setFillPattern(HSSFCellStyle.FINE_DOTS);
		    // 设置列的样式
		    for (int i = 0; i <= 14; i++) {
		      sheet.setDefaultColumnStyle((short) i, sheetStyle);
		    }

			// 设置表头字体
		    HSSFFont headfont = workbook.createFont();
		    headfont.setFontName("黑体");
		    headfont.setFontHeightInPoints((short) 14);// 字体大小
		    headfont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
		    // 表头样式
		    HSSFCellStyle headstyle = workbook.createCellStyle();
		    headstyle.setFont(headfont);
		    headstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
		    headstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
		    headstyle.setLocked(true);
		    headstyle.setWrapText(true);// 自动换行
		    // 另一个字体样式
		    HSSFFont columnHeadFont = workbook.createFont();
		    columnHeadFont.setFontName("宋体");
		    columnHeadFont.setFontHeightInPoints((short) 10);
		    columnHeadFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		    // 列头的样式
		    HSSFCellStyle columnHeadStyle = workbook.createCellStyle();
		    columnHeadStyle.setFont(columnHeadFont);
		    columnHeadStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);// 左右居中
		    columnHeadStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
		    columnHeadStyle.setLocked(true);
		    columnHeadStyle.setWrapText(true);
		    columnHeadStyle.setLeftBorderColor(HSSFColor.BLACK.index);// 左边框的颜色
		    columnHeadStyle.setBorderLeft((short) 1);// 边框的大小
		    columnHeadStyle.setRightBorderColor(HSSFColor.BLACK.index);// 右边框的颜色
		    columnHeadStyle.setBorderRight((short) 1);// 边框的大小
		    columnHeadStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 设置单元格的边框为粗体
		    columnHeadStyle.setBottomBorderColor(HSSFColor.BLACK.index); // 设置单元格的边框颜色
		    // 设置单元格的背景颜色（单元格的样式会覆盖列或行的样式）
		    columnHeadStyle.setFillForegroundColor(HSSFColor.WHITE.index);

		    HSSFFont font = workbook.createFont();
		    font.setFontName("宋体");
		    font.setFontHeightInPoints((short) 10);
		    // 另一个样式
		    HSSFCellStyle centerstyle = workbook.createCellStyle();
		    centerstyle.setFont(font);
		    centerstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
		    centerstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
		    centerstyle.setWrapText(true);
		    centerstyle.setLeftBorderColor(HSSFColor.BLACK.index);
		    centerstyle.setBorderLeft((short) 1);
		    centerstyle.setRightBorderColor(HSSFColor.BLACK.index);
		    centerstyle.setBorderRight((short) 1);
		    centerstyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 设置单元格的边框为粗体
		    centerstyle.setBottomBorderColor(HSSFColor.BLACK.index); // 设置单元格的边框颜色．
		    centerstyle.setFillForegroundColor(HSSFColor.WHITE.index);// 设置单元格的背景颜色

			//设置表头
			HSSFRow header = sheet.createRow(0);
			// 设置行高
			header.setHeight((short) 900);
			// 创建第一列
			HSSFCell cell = header.createCell(0);
			cell.setCellValue(new HSSFRichTextString(""));
			cell.setCellStyle(headstyle);
			HSSFCell cellType = header.createCell(1);
			cellType.setCellValue(new HSSFRichTextString("类型"));
			cellType.setCellStyle(headstyle);
			HSSFCell cellPrice = header.createCell(2);
			cellPrice.setCellValue(new HSSFRichTextString("单价"));
			cellPrice.setCellStyle(headstyle);
			HSSFCell cellNum = header.createCell(3);
			cellNum.setCellValue(new HSSFRichTextString("数量"));
			cellNum.setCellStyle(headstyle);
			HSSFCell cellName = header.createCell(4);
			cellName.setCellValue(new HSSFRichTextString("用户"));
			cellName.setCellStyle(headstyle);
			HSSFCell cellDate = header.createCell(5);
			cellDate.setCellValue(new HSSFRichTextString("入住日期"));
			cellDate.setCellStyle(headstyle);
			HSSFCell cellDay = header.createCell(6);
			cellDay.setCellValue(new HSSFRichTextString("天数"));
			cellDay.setCellStyle(headstyle);
			HSSFCell cellSum = header.createCell(7);
			cellSum.setCellValue(new HSSFRichTextString("金额"));
			cellSum.setCellStyle(headstyle);
			HSSFCell cellStatus = header.createCell(8);
			cellStatus.setCellValue(new HSSFRichTextString("订单状态"));
			cellStatus.setCellStyle(headstyle);

			CellRangeAddress range = null;
			if (list != null && list.size() > 0) {
				int row = 1;
				for(Map<String, Object> map : list){
					String orderNo = map.get("orderNo").toString();
					String createTime = map.get("createTimeStr").toString();

					range = new CellRangeAddress(row, row, 0, 8);//起始行，终止行，起始列，终止列
					sheet.addMergedRegion(range);

					HSSFRow rowNo = sheet.createRow(row);
					rowNo.setHeightInPoints(20);
					HSSFCell cellNo = rowNo.createCell(0);
					cellNo.setCellValue(new HSSFRichTextString("订单号："+orderNo+"       预定日期："+createTime));
					cellNo.setCellStyle(columnHeadStyle);
					row++;

					List<Map<String, Object>> listItem = (List<Map<String, Object>>) map.get("items");
					int itemRow =0;
					for(Map<String, Object> item : listItem){
						HSSFRow rowItem = sheet.createRow(row+itemRow);
						rowItem.setHeightInPoints(20);
						HSSFCell cellItemName = rowItem.createCell(1);
						cellItemName.setCellValue(new HSSFRichTextString(item.get("itemName").toString()));
						cellItemName.setCellStyle(centerstyle);

						HSSFCell cellItemPrice = rowItem.createCell(2);
						cellItemPrice.setCellValue(new HSSFRichTextString(item.get("itemPrice").toString()));
						cellItemPrice.setCellStyle(centerstyle);

						HSSFCell cellItemNum = rowItem.createCell(3);
						cellItemNum.setCellValue(new HSSFRichTextString(item.get("itemNum").toString()));
						cellItemNum.setCellStyle(centerstyle);

						range = new CellRangeAddress(row, row+listItem.size()-1, 0, 0);//起始行，终止行，起始列，终止列
						sheet.addMergedRegion(range);
						HSSFCell cellHotel = rowItem.createCell(0);
						cellHotel.setCellValue(new HSSFRichTextString(map.get("hotelName").toString()));
						cellHotel.setCellStyle(centerstyle);

						range = new CellRangeAddress(row, row+listItem.size()-1, 4, 4);//起始行，终止行，起始列，终止列
						sheet.addMergedRegion(range);
						HSSFCell cellCheckinName = rowItem.createCell(4);
						cellCheckinName.setCellValue(new HSSFRichTextString(map.get("checkinName").toString()));
						cellCheckinName.setCellStyle(centerstyle);

						range = new CellRangeAddress(row, row+listItem.size()-1, 5, 5);//起始行，终止行，起始列，终止列
						sheet.addMergedRegion(range);
						HSSFCell cellTimeStr = rowItem.createCell(5);
						cellTimeStr.setCellValue(new HSSFRichTextString(map.get("checkinTimeStr").toString()));
						cellTimeStr.setCellStyle(centerstyle);

						range = new CellRangeAddress(row, row+listItem.size()-1, 6, 6);//起始行，终止行，起始列，终止列
						sheet.addMergedRegion(range);
						HSSFCell cellDays = rowItem.createCell(6);
						cellDays.setCellValue(new HSSFRichTextString(map.get("checkinDays").toString()));
						cellDays.setCellStyle(centerstyle);

						range = new CellRangeAddress(row, row+listItem.size()-1, 7, 7);//起始行，终止行，起始列，终止列
						sheet.addMergedRegion(range);
						HSSFCell cellNeedSum = rowItem.createCell(7);
						cellNeedSum.setCellValue(new HSSFRichTextString(map.get("needSum").toString()));
						cellNeedSum.setCellStyle(centerstyle);

						range = new CellRangeAddress(row, row+listItem.size()-1, 8, 8);//起始行，终止行，起始列，终止列
						sheet.addMergedRegion(range);
						HSSFCell cellOrder = rowItem.createCell(8);
						cellOrder.setCellValue(new HSSFRichTextString(map.get("orderStatus").toString()));
						cellOrder.setCellStyle(centerstyle);

						itemRow++;
					}

					row = row +itemRow;
				}
			}

			workbook.write(fos);// 将workbook对象输出到文件test.xls
			fos.flush(); // 缓冲
			fos.close(); // 关闭流

		} catch (Exception e) {
			e.printStackTrace();
		}
		return workbook;
	}

	/**
	 * 下载预订信
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public static HSSFWorkbook exportReserve(Map<String, Object> map) throws Exception {
		HSSFWorkbook workbook = null;
		HSSFSheet sheet = null;
		try {
			workbook = new HSSFWorkbook(); // 创建工作簿对象
			FileOutputStream fos = new FileOutputStream("test.xls"); // 创建.xls文件
			sheet = workbook.createSheet(); // 创建工作表
			sheet.setColumnWidth(0, 6500);
			sheet.setColumnWidth(1, 3500);
			sheet.setColumnWidth(2, 3500);
			sheet.setColumnWidth(3, 2000);

			// Sheet样式
		    HSSFCellStyle sheetStyle = workbook.createCellStyle();
		    // 背景色的设定
		    sheetStyle.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);
		    // 前景色的设定
		    sheetStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		    // 填充模式
		    sheetStyle.setFillPattern(HSSFCellStyle.FINE_DOTS);
		    // 设置列的样式
		    for (int i = 0; i <= 14; i++) {
		      sheet.setDefaultColumnStyle((short) i, sheetStyle);
		    }

			// 设置表头字体
		    HSSFFont headfont = workbook.createFont();
		    headfont.setFontName("黑体");
		    headfont.setFontHeightInPoints((short) 14);// 字体大小
		    headfont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
		    // 表头样式
		    HSSFCellStyle headstyle = workbook.createCellStyle();
		    headstyle.setFont(headfont);
		    headstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
		    headstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
		    headstyle.setLocked(true);
		    headstyle.setWrapText(true);// 自动换行
		    // 另一个字体样式
		    HSSFFont columnHeadFont = workbook.createFont();
		    columnHeadFont.setFontName("宋体");
		    columnHeadFont.setFontHeightInPoints((short) 10);
		    columnHeadFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		    // 列头的样式
		    HSSFCellStyle columnHeadStyle = workbook.createCellStyle();
		    columnHeadStyle.setFont(columnHeadFont);
		    columnHeadStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);// 左右居中
		    columnHeadStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
		    columnHeadStyle.setLocked(true);
		    columnHeadStyle.setWrapText(true);
		    columnHeadStyle.setLeftBorderColor(HSSFColor.BLACK.index);// 左边框的颜色
		    columnHeadStyle.setBorderLeft((short) 1);// 边框的大小
		    columnHeadStyle.setRightBorderColor(HSSFColor.BLACK.index);// 右边框的颜色
		    columnHeadStyle.setBorderRight((short) 1);// 边框的大小
		    columnHeadStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 设置单元格的边框为粗体
		    columnHeadStyle.setBottomBorderColor(HSSFColor.BLACK.index); // 设置单元格的边框颜色
		    // 设置单元格的背景颜色（单元格的样式会覆盖列或行的样式）
		    columnHeadStyle.setFillForegroundColor(HSSFColor.WHITE.index);

		    HSSFFont font = workbook.createFont();
		    font.setFontName("宋体");
		    font.setFontHeightInPoints((short) 10);
		    // 另一个样式
		    HSSFCellStyle centerstyle = workbook.createCellStyle();
		    centerstyle.setFont(font);
		    centerstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
		    centerstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
		    centerstyle.setWrapText(true);
		    centerstyle.setLeftBorderColor(HSSFColor.BLACK.index);
		    centerstyle.setBorderLeft((short) 1);
		    centerstyle.setRightBorderColor(HSSFColor.BLACK.index);
		    centerstyle.setBorderRight((short) 1);
		    centerstyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 设置单元格的边框为粗体
		    centerstyle.setBottomBorderColor(HSSFColor.BLACK.index); // 设置单元格的边框颜色．
		    centerstyle.setFillForegroundColor(HSSFColor.WHITE.index);// 设置单元格的背景颜色

			//设置表头
			HSSFRow header = sheet.createRow(0);
			// 设置行高
			header.setHeight((short) 900);
			// 创建第一列
			HSSFCell cell = header.createCell(0);
			cell.setCellValue(new HSSFRichTextString(""));
			cell.setCellStyle(headstyle);
			HSSFCell cellType = header.createCell(1);
			cellType.setCellValue(new HSSFRichTextString("类型"));
			cellType.setCellStyle(headstyle);
			HSSFCell cellPrice = header.createCell(2);
			cellPrice.setCellValue(new HSSFRichTextString("单价"));
			cellPrice.setCellStyle(headstyle);
			HSSFCell cellNum = header.createCell(3);
			cellNum.setCellValue(new HSSFRichTextString("数量"));
			cellNum.setCellStyle(headstyle);
			HSSFCell cellName = header.createCell(4);
			cellName.setCellValue(new HSSFRichTextString("用户"));
			cellName.setCellStyle(headstyle);
			HSSFCell cellDate = header.createCell(5);
			cellDate.setCellValue(new HSSFRichTextString("入住日期"));
			cellDate.setCellStyle(headstyle);
			HSSFCell cellDay = header.createCell(6);
			cellDay.setCellValue(new HSSFRichTextString("天数"));
			cellDay.setCellStyle(headstyle);
			HSSFCell cellSum = header.createCell(7);
			cellSum.setCellValue(new HSSFRichTextString("金额"));
			cellSum.setCellStyle(headstyle);
			HSSFCell cellStatus = header.createCell(8);
			cellStatus.setCellValue(new HSSFRichTextString("订单状态"));
			cellStatus.setCellStyle(headstyle);

//			CellRangeAddress range = null;
//			if (list != null && list.size() > 0) {
//				int row = 1;
//				for(Map<String, Object> map : list){
//					String orderNo = map.get("orderNo").toString();
//					String createTime = map.get("createTimeStr").toString();
//
//					range = new CellRangeAddress(row, row, 0, 8);//起始行，终止行，起始列，终止列
//					sheet.addMergedRegion(range);
//
//					HSSFRow rowNo = sheet.createRow(row);
//					rowNo.setHeightInPoints(20);
//					HSSFCell cellNo = rowNo.createCell(0);
//					cellNo.setCellValue(new HSSFRichTextString("订单号："+orderNo+"       预定日期："+createTime));
//					cellNo.setCellStyle(columnHeadStyle);
//					row++;
//
//					List<Map<String, Object>> listItem = (List<Map<String, Object>>) map.get("items");
//					int itemRow =0;
//					for(Map<String, Object> item : listItem){
//						HSSFRow rowItem = sheet.createRow(row+itemRow);
//						rowItem.setHeightInPoints(20);
//						HSSFCell cellItemName = rowItem.createCell(1);
//						cellItemName.setCellValue(new HSSFRichTextString(item.get("itemName").toString()));
//						cellItemName.setCellStyle(centerstyle);
//
//						HSSFCell cellItemPrice = rowItem.createCell(2);
//						cellItemPrice.setCellValue(new HSSFRichTextString(item.get("itemPrice").toString()));
//						cellItemPrice.setCellStyle(centerstyle);
//
//						HSSFCell cellItemNum = rowItem.createCell(3);
//						cellItemNum.setCellValue(new HSSFRichTextString(item.get("itemNum").toString()));
//						cellItemNum.setCellStyle(centerstyle);
//
//						range = new CellRangeAddress(row, row+listItem.size()-1, 0, 0);//起始行，终止行，起始列，终止列
//						sheet.addMergedRegion(range);
//						HSSFCell cellHotel = rowItem.createCell(0);
//						cellHotel.setCellValue(new HSSFRichTextString(map.get("hotelName").toString()));
//						cellHotel.setCellStyle(centerstyle);
//
//						range = new CellRangeAddress(row, row+listItem.size()-1, 4, 4);//起始行，终止行，起始列，终止列
//						sheet.addMergedRegion(range);
//						HSSFCell cellCheckinName = rowItem.createCell(4);
//						cellCheckinName.setCellValue(new HSSFRichTextString(map.get("checkinName").toString()));
//						cellCheckinName.setCellStyle(centerstyle);
//
//						range = new CellRangeAddress(row, row+listItem.size()-1, 5, 5);//起始行，终止行，起始列，终止列
//						sheet.addMergedRegion(range);
//						HSSFCell cellTimeStr = rowItem.createCell(5);
//						cellTimeStr.setCellValue(new HSSFRichTextString(map.get("checkinTimeStr").toString()));
//						cellTimeStr.setCellStyle(centerstyle);
//
//						range = new CellRangeAddress(row, row+listItem.size()-1, 6, 6);//起始行，终止行，起始列，终止列
//						sheet.addMergedRegion(range);
//						HSSFCell cellDays = rowItem.createCell(6);
//						cellDays.setCellValue(new HSSFRichTextString(map.get("checkinDays").toString()));
//						cellDays.setCellStyle(centerstyle);
//
//						range = new CellRangeAddress(row, row+listItem.size()-1, 7, 7);//起始行，终止行，起始列，终止列
//						sheet.addMergedRegion(range);
//						HSSFCell cellNeedSum = rowItem.createCell(7);
//						cellNeedSum.setCellValue(new HSSFRichTextString(map.get("needSum").toString()));
//						cellNeedSum.setCellStyle(centerstyle);
//
//						range = new CellRangeAddress(row, row+listItem.size()-1, 8, 8);//起始行，终止行，起始列，终止列
//						sheet.addMergedRegion(range);
//						HSSFCell cellOrder = rowItem.createCell(8);
//						cellOrder.setCellValue(new HSSFRichTextString(map.get("orderStatus").toString()));
//						cellOrder.setCellStyle(centerstyle);
//
//						itemRow++;
//					}
//
//					row = row +itemRow;
//				}
//			}

			workbook.write(fos);// 将workbook对象输出到文件test.xls
			fos.flush(); // 缓冲
			fos.close(); // 关闭流

		} catch (Exception e) {
			e.printStackTrace();
		}
		return workbook;
	}

	/**
	 * @param row
	 * @param cellType
	 * @param object
	 * @param style
	 * @return
	 */
	private static void createCell(HSSFWorkbook workbook, HSSFSheet sheet, HSSFRow row, int cellType, int rowNmuber,
			int colNumber, Object object, HSSFCellStyle style, float high, String ossPath) {
		HSSFPatriarch patriarch = null;
		HSSFCell cell = null;
		switch (cellType) {
		case 0:
			// 设置普通文本
			cell = row.createCell(colNumber, HSSFCell.CELL_TYPE_STRING);// 设置单元格的数据类型
			String sv = object == null ? "" : object.toString();
			cell.setCellValue(sv);
			cell.setCellStyle(style);
			cell.getRow().setHeightInPoints(high);
			break;
		case 1:
			// 设置为形状
			patriarch = sheet.createDrawingPatriarch();
			HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 1023, 255, (short) colNumber, rowNmuber,
					(short) colNumber, rowNmuber);
			HSSFSimpleShape shape = patriarch.createSimpleShape(anchor);
			// 这里可以设置形状的样式
			shape.setShapeType(HSSFSimpleShape.OBJECT_TYPE_OVAL);

			break;
		case 2:
			// 设置金额
			cell = row.createCell(colNumber, HSSFCell.CELL_TYPE_STRING);// 设置单元格的数据类型
			double dv = object == null ? 0.00 : new Double(object.toString());
			cell.setCellValue(dv);
			break;
		case 3:
			// 设置为图片]
			try {

				if (null == object || "".equals(object)) {
					// 设置普通文本
					cell = row.createCell(colNumber, HSSFCell.CELL_TYPE_STRING);// 设置单元格的数据类型
					cell.setCellValue("");
				} else {
					patriarch = sheet.createDrawingPatriarch();
					ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
					URL url = new URL(ossPath + object.toString());
					BufferedImage bufferImg = ImageIO.read(url); // 打开一个图片文件
					ImageIO.write(bufferImg, "jpg", byteArrayOut);
					HSSFClientAnchor iAnchor = new HSSFClientAnchor(80, 30, 0, 0, (short) colNumber, rowNmuber + 1,
							(short) (colNumber + 1), (rowNmuber + 2));
					patriarch.createPicture(iAnchor,
							workbook.addPicture(byteArrayOut.toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case 4:
			// 生成条形码
			try {
				if (null == object || "".equals(object)) {
					// 设置普通文本
					cell = row.createCell(colNumber, HSSFCell.CELL_TYPE_STRING);// 设置单元格的数据类型
					cell.setCellValue("");
				} else {
					patriarch = sheet.createDrawingPatriarch();
					ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
					BufferedImage bufferImg = BarCodeUtil.doGet(object.toString()); // 打开一个图片文件
					if (null == bufferImg) {
						cell = row.createCell(colNumber, HSSFCell.CELL_TYPE_STRING);// 设置单元格的数据类型
						cell.setCellValue("");
					} else {
						ImageIO.write(bufferImg, "jpg", byteArrayOut);
						HSSFClientAnchor iAnchor = new HSSFClientAnchor(80, 30, 0, 0, (short) colNumber, rowNmuber + 1,
								(short) (colNumber + 1), (rowNmuber + 2));
						HSSFPicture print = patriarch.createPicture(iAnchor,
								workbook.addPicture(byteArrayOut.toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));
						print.resize(1.0);
						HSSFClientAnchor anchor1 = (HSSFClientAnchor) print.getAnchor();
						anchor1.setDx1(20);
						anchor1.setDy1(20);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case 5:
			// 生成二维码
			try {
				if (null == object || "".equals(object)) {
					// 设置普通文本
					cell = row.createCell(colNumber, HSSFCell.CELL_TYPE_STRING);// 设置单元格的数据类型
					cell.setCellValue("");
				} else {
					patriarch = sheet.createDrawingPatriarch();
					ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
					BufferedImage bufferImg = QRCodeUtil.createImage(object.toString(), null, true); // 打开一个图片文件
					if (null == bufferImg) {
						cell = row.createCell(colNumber, HSSFCell.CELL_TYPE_STRING);// 设置单元格的数据类型
						cell.setCellValue("");
					} else {
						ImageIO.write(bufferImg, "jpg", byteArrayOut);
						HSSFClientAnchor iAnchor = new HSSFClientAnchor(80, 30, 0, 0, (short) colNumber, rowNmuber + 1,
								(short) (colNumber + 1), (rowNmuber + 2));
						HSSFPicture print = patriarch.createPicture(iAnchor,
								workbook.addPicture(byteArrayOut.toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));
						print.resize(1.0);
						HSSFClientAnchor anchor1 = (HSSFClientAnchor) print.getAnchor();
						anchor1.setDx1(20);
						anchor1.setDy1(20);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
	}

	/*
	 * 列头单元格样式
	 */
	private static HSSFCellStyle getColumnTopStyle(HSSFWorkbook workbook) {
		// 设置字体
		HSSFFont font = workbook.createFont();
		// 设置字体名字
		font.setFontName("Courier New");
		// 设置样式;
		HSSFCellStyle style = workbook.createCellStyle();
		// 设置底边框;
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		// 设置底边框颜色;
		style.setBottomBorderColor(HSSFColor.BLACK.index);
		// 设置左边框;
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		// 设置左边框颜色;
		style.setLeftBorderColor(HSSFColor.BLACK.index);
		// 设置右边框;
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		// 设置右边框颜色;
		style.setRightBorderColor(HSSFColor.BLACK.index);
		// 设置顶边框;
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		// 设置顶边框颜色;
		style.setTopBorderColor(HSSFColor.BLACK.index);
		// 在样式用应用设置的字体;
		style.setFont(font);
		// 设置自动换行;
		style.setWrapText(true);
		// 设置水平对齐的样式为居中对齐;
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 设置垂直对齐的样式为居中对齐;
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		return style;

	}

	/*
	 * 列数据信息单元格样式
	 */
	private static HSSFCellStyle getStyle(HSSFWorkbook workbook) {
		// 设置字体
		HSSFFont font = workbook.createFont();
		// 设置字体名字
		font.setFontName("Ebrima");
		// 设置样式;
		HSSFCellStyle style = workbook.createCellStyle();
		// 设置底边框;
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		// 设置底边框颜色;
		style.setBottomBorderColor(HSSFColor.BLACK.index);
		// 设置左边框;
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		// 设置左边框颜色;
		style.setLeftBorderColor(HSSFColor.BLACK.index);
		// 设置右边框;
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		// 设置右边框颜色;
		style.setRightBorderColor(HSSFColor.BLACK.index);
		// 设置顶边框;
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		// 设置顶边框颜色;
		style.setTopBorderColor(HSSFColor.BLACK.index);
		// 在样式用应用设置的字体;
		style.setFont(font);
		// 设置自动换行;
		style.setWrapText(true);
		// 设置水平对齐的样式为居中对齐;
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 设置垂直对齐的样式为居中对齐;
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		return style;
	}

	/**
	 * 读取Excel数据内容
	 *
	 * param InputStream
	 * return Map 包含单元格数据内容的Map对象
	 * author zengwendong
	 */
	public static Map<Integer, Map<Integer, Object>> readExcelContent(String fileType, InputStream is,
			String sheetName) {
		Workbook wb = null;
		Map<Integer, Map<Integer, Object>> content = new HashMap<Integer, Map<Integer, Object>>();

		try {
			if ("xls".equals(fileType)) {
				wb = new HSSFWorkbook(is);
			} else if ("xlsx".equals(fileType)) {
				wb = new XSSFWorkbook(is);
			} else {
				return content;
			}

			Sheet sheet = wb.getSheet(sheetName);
			// 得到总行数
			int rowNum = sheet.getLastRowNum();
			Row row = sheet.getRow(0);
			int colNum = row.getPhysicalNumberOfCells();
			// 正文内容应该从第二行开始,第一行为表头的标题
			for (int i = 0; i <= rowNum; i++) {
				row = sheet.getRow(i);
				int j = 0;
				Map<Integer, Object> cellValue = new HashMap<Integer, Object>();
				while (j < colNum) {
					Object obj = getCellFormatValue(row.getCell(j));
					cellValue.put(j, obj);
					j++;
				}
				content.put(i, cellValue);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content;
	}

	/**
	 *
	 * 根据Cell类型设置数据
	 *
	 * @param cell
	 * @return
	 * @author zengwendong
	 */
	private static Object getCellFormatValue(Cell cell) {
		Object cellvalue = "";
		if (cell != null) {
			// 判断当前Cell的Type
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_NUMERIC:// 如果当前Cell的Type为NUMERIC
			case Cell.CELL_TYPE_FORMULA: {
				// 判断当前的cell是否为Date
				if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell)) {
					// 如果是Date类型则，转化为Data格式
					// data格式是带时分秒的：2013-7-10 0:00:00
					// cellvalue = cell.getDateCellValue().toLocaleString();
					// data格式是不带带时分秒的：2013-7-10
					Date date = cell.getDateCellValue();
					cellvalue = date;
				} else {// 如果是纯数字

					// 取得当前Cell的数值
					cellvalue = String.valueOf(cell.getNumericCellValue());
				}
				break;
			}
			case Cell.CELL_TYPE_STRING:// 如果当前Cell的Type为STRING
				// 取得当前的Cell字符串
				cellvalue = cell.getRichStringCellValue().getString();
				break;
			default:// 默认的Cell值
				cellvalue = "";
			}
		} else {
			cellvalue = "";
		}
		return cellvalue;
	}

	/**
	 *
	 * @param sheet
	 * @throws IOException
	 */
	public static Map<String, Object> getXSSFprocess(XSSFSheet sheet) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		for (POIXMLDocumentPart dr : sheet.getRelations()) {
			if (dr instanceof XSSFDrawing) {
				XSSFDrawing drawing = (XSSFDrawing) dr;
				List<XSSFShape> shapes = drawing.getShapes();
				for (XSSFShape shape : shapes) {
					XSSFPicture pic = (XSSFPicture) shape;
					XSSFClientAnchor anchor = pic.getPreferredSize();
					CTMarker ctMarker = anchor.getFrom();
					XSSFPictureData picData = pic.getPictureData();
					String fileName = picData.suggestFileExtension();
					map.put(fileName, picData.getData());
					System.out.println("ext:" + ctMarker + "文件名称" + fileName + ";图片类型：" + picData.getMimeType());

				}
			}
		}
		return map;
	}

	public static Map<String, Object> getHSSFprocess(HSSFWorkbook workbook, HSSFSheet sheet) throws Exception {
		List<HSSFPictureData> pictures = workbook.getAllPictures();
		Map<String, Object> map = new HashMap<String, Object>();
		for (HSSFShape shape : sheet.getDrawingPatriarch().getChildren()) {
			// HSSFClientAnchor anchor = (HSSFClientAnchor) shape.getAnchor();
			if (shape instanceof HSSFPicture) {
				HSSFPicture pic = (HSSFPicture) shape;
				// int row = anchor.getRow1();
				int pictureIndex = pic.getPictureIndex() - 1;
				HSSFPictureData picData = pictures.get(pictureIndex);
				String ext = picData.suggestFileExtension();
				String fileName = pic.getFileName();
				System.out.println("ext:" + ext + "文件名称" + pic.getFileName() + ";图片类型：" + picData.getMimeType());
				map.put(fileName, picData.getData());
			}
		}
		return map;
	}

	public static Map<String, Object> getFileByte(InputStream is, String sheetName) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Workbook wb = WorkbookFactory.create(is);
			if (wb instanceof HSSFWorkbook) {
				HSSFWorkbook workbook = (HSSFWorkbook) wb;
				HSSFSheet sheet = workbook.getSheet(sheetName);
				if (sheet == null) {
					return map;
				}
				return getHSSFprocess(workbook, sheet);
			} else if (wb instanceof XSSFWorkbook) {
				XSSFWorkbook xSSFWorkbook = (XSSFWorkbook) wb;
				XSSFSheet sheet = xSSFWorkbook.getSheet(sheetName);
				if (sheet == null) {
					return map;
				}
				return getXSSFprocess(sheet);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return map;
	}

	public static String getMergeCellValue(Cell c) {
		String value = "";
		boolean isMerge = isMergedRegion(c);
		System.out.println("isMerge=" + isMerge);
		// 判断是否具有合并单元格
		if (isMerge) {
			value = getMergedRegionValue(c);
		} else {
			value = c.getRichStringCellValue() + "";
		}
		return value;
	}

	public static String getMergedRegionValue(Cell cell) {
		int row = cell.getRow().getRowNum();
		int column = cell.getColumnIndex();
		Sheet sheet = cell.getRow().getSheet();
		int sheetMergeCount = sheet.getNumMergedRegions();

		for (int i = 0; i < sheetMergeCount; i++) {
			CellRangeAddress ca = sheet.getMergedRegion(i);
			int firstColumn = ca.getFirstColumn();
			int lastColumn = ca.getLastColumn();
			int firstRow = ca.getFirstRow();
			int lastRow = ca.getLastRow();

			if (row >= firstRow && row <= lastRow) {

				if (column >= firstColumn && column <= lastColumn) {
					Row fRow = sheet.getRow(firstRow);
					Cell fCell = fRow.getCell(firstColumn);
					return getCellValue(fCell);
				}
			}
		}

		return null;
	}

	/**
	 * 判断指定的单元格是否是合并单元格
	 *
	 * param sheet
	 * param row
	 *            行下标
	 * param column
	 *            列下标
	 * @return
	 */
	private static boolean isMergedRegion(Cell cell) {
		int row = cell.getRow().getRowNum();
		int column = cell.getColumnIndex();
		Sheet sheet = cell.getRow().getSheet();
		int sheetMergeCount = sheet.getNumMergedRegions();
		for (int i = 0; i < sheetMergeCount; i++) {

			CellRangeAddress range = sheet.getMergedRegion(i);
			int firstColumn = range.getFirstColumn();
			int lastColumn = range.getLastColumn();
			int firstRow = range.getFirstRow();
			int lastRow = range.getLastRow();
			if (row >= firstRow && row <= lastRow) {
				if (column >= firstColumn && column <= lastColumn) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 获取单元格的值
	 *
	 * @param cell
	 * @return
	 */
	public static String getCellValue(Cell cell) {
		if (cell == null)
			return "";

		if (cell.getCellType() == Cell.CELL_TYPE_STRING) {

			return cell.getStringCellValue();

		} else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {

			return String.valueOf(cell.getBooleanCellValue());

		} else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {

			return cell.getCellFormula();

		} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {

			return String.valueOf(cell.getNumericCellValue());

		}
		return "";
	}

}
