package com.yjh.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yjh.entity.Department;
import com.yjh.entity.Duty;
import com.yjh.entity.Employee;
import com.yjh.service.DepartmentService;
import com.yjh.service.DutyService;
import com.yjh.service.impl.DepartmentServiceImpl;
import com.yjh.service.impl.DutyServiceImpl;


public class DutyServlet extends BaseServlet {
	
	//创建Duty业务层对象
	private DutyService ds = new DutyServiceImpl();
	//创建Gson对象
	private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
	//创建日期转化对象
	private static DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	//导出xls
	public void exportXls(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取查询条件
		String empid = request.getParameter("empid");
		String sdeptno = request.getParameter("deptno");
		String sdtdate = request.getParameter("dtdate");
		//增强代码健壮性
		int deptno = 0;
		try{//把deptno转换成int类型,因为sdeptno有可能为null或"",这样可以防止转换出错
			deptno = Integer.parseInt(sdeptno);
		}catch(NumberFormatException e){
			e.printStackTrace();
		}
		java.sql.Date dtdate = null;
		try{//调用sql Date的静态方法获取时间
			dtdate = java.sql.Date.valueOf(sdtdate);
		}catch(IllegalArgumentException e){//该异常表示参数不合法
			e.printStackTrace();
		}
		//调用service层方法
		List<Duty> list = ds.findDuty(empid,deptno,dtdate);
		//获取outputStream
		createExcel(list,response);
	}
	
	/**
	 * 创建excel表
	 * @param list
	 */
    private static void createExcel(List<Duty> list,HttpServletResponse response) {
        // 创建一个Excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 创建一个工作表
        HSSFSheet sheet = workbook.createSheet("签到表1");
        //CellRangeAddress	单元格范围地址
        CellRangeAddress region = new CellRangeAddress(0, // first row
                0, // last row
                0, // first column
                2 // last column
        );
        //addMergedRegion  添加合并区域	
        sheet.addMergedRegion(region);
        
        //创建第一行
        HSSFRow hssfRow = sheet.createRow(0);
        //在行内创建第一个单元格
        HSSFCell headCell = hssfRow.createCell(0);
        //在单元格里面写入值
        headCell.setCellValue("尚学堂员工签到表");
        
        // 设置单元格格式居中
        HSSFCellStyle cellStyle = workbook.createCellStyle();
    	cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        /*
       
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
       // cellStyle.setFillBackgroundColor(HSSFColor.GREEN.index);
        cellStyle.setFillForegroundColor(HSSFColor.GREEN.index);
       
        HSSFFont font = workbook.createFont();
        font.setFontName("楷体"); //字体
        font.setFontHeightInPoints((short)30); //字体大小
        font.setColor(HSSFColor.RED.index);//颜色
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//加粗
        font.setItalic(true); //倾斜
        cellStyle.setFont(font);
        */
        headCell.setCellStyle(cellStyle);
        
        
        // 添加表头行
        hssfRow = sheet.createRow(1);
        // 添加表头内容
        headCell = hssfRow.createCell(0);
        headCell.setCellValue("用户名");
        headCell.setCellStyle(cellStyle);

        headCell = hssfRow.createCell(1);
        headCell.setCellValue("真实姓名");
        headCell.setCellStyle(cellStyle);

        headCell = hssfRow.createCell(2);
        headCell.setCellValue("所属部门");
        headCell.setCellStyle(cellStyle);
        
        headCell = hssfRow.createCell(3);
        headCell.setCellValue("出勤日期");
        headCell.setCellStyle(cellStyle);

        headCell = hssfRow.createCell(4);
        headCell.setCellValue("签到时间");
        headCell.setCellStyle(cellStyle);

        headCell = hssfRow.createCell(5);
        headCell.setCellValue("签退时间");
        headCell.setCellStyle(cellStyle);

        // 添加数据内容
        for (int i = 0; i < list.size(); i++) {
            hssfRow = sheet.createRow((int) i + 2);
            Duty duty = list.get(i);

            // 创建单元格，并设置值
            HSSFCell cell = hssfRow.createCell(0);
            cell.setCellValue(duty.getEmp().getEmpId());
            cell.setCellStyle(cellStyle);

            cell = hssfRow.createCell(1);
            cell.setCellValue(duty.getEmp().getRealName());
            cell.setCellStyle(cellStyle);

            cell = hssfRow.createCell(2);
            cell.setCellValue(duty.getEmp().getDept().getDeptName());
            cell.setCellStyle(cellStyle);
            
            cell = hssfRow.createCell(3);
            //注意：静态方法不能调用非静态属性
            cell.setCellValue(df.format(duty.getDtDate()));
            cell.setCellStyle(cellStyle);
            
            cell = hssfRow.createCell(4);
            cell.setCellValue(duty.getSignInTime());
            cell.setCellStyle(cellStyle);
            
            cell = hssfRow.createCell(5);
            cell.setCellValue(duty.getSignOutTime());
            cell.setCellStyle(cellStyle);
        }

        // 保存Excel文件
        try {
        	//这里的输出流是保存到本地（服务器端）
            //OutputStream outputStream = new FileOutputStream("D:/students.xls");
        	//设置响应格式为application/vnd.ms-excel
        	response.setContentType("application/vnd.ms-excel");
        	//附件形式下载，文件名为duty.xls
        	response.setHeader("Content-disposition", "attachment;filename=duty.xls");
        	//保存到客户端
        	OutputStream outputStream = response.getOutputStream();
        	workbook.write(outputStream);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	
	//获取查询结果
	public void findDuty(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取查询条件
		String empid = request.getParameter("empid");
		String sdeptno = request.getParameter("deptno");
		String sdtdate = request.getParameter("dtdate");
		//增强代码健壮性
		int deptno = 0;
		try{//把deptno转换成int类型,因为sdeptno有可能为null或"",这样可以防止转换出错
			deptno = Integer.parseInt(sdeptno);
		}catch(NumberFormatException e){
			e.printStackTrace();
		}
		java.sql.Date dtdate = null;
		try{//调用sql Date的静态方法获取时间
			dtdate = java.sql.Date.valueOf(sdtdate);
		}catch(IllegalArgumentException e){//该异常表示参数不合法
			e.printStackTrace();
		}
		//调用service层方法
		List<Duty> list = ds.findDuty(empid,deptno,dtdate);
		//响应
		response.getWriter().write(gson.toJson(list));
		
	}	
	
	//获取部门信息
	public void findAllDept(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取部门信息
		DepartmentService dept = new DepartmentServiceImpl();
		List<Department> list = dept.findAll();
		//响应
		response.getWriter().write(gson.toJson(list));
	}
	
	//签退
	public void signout(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取session中的用户对象
		Employee emp = (Employee) request.getSession().getAttribute("emp");
		
		//调用业务层方法，由于签到签退需要知道是哪个用户签到，所以需要获取用户id
		int n = ds.signout(emp.getEmpId());//0	签到失败	1 签到成功	2 已经签到
		PrintWriter writer = response.getWriter();
		//响应
		if(n==1){//签退成功
			writer.println("签退成功");
		}else if(n==0){
			writer.println("签退失败");
		}else{
			writer.println("尚未签到");
		}
		
	}
	

	//签到
	public void signin(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取session中的用户对象
		Employee emp = (Employee) request.getSession().getAttribute("emp");
		
		//调用业务层方法，由于签到签退需要知道是哪个用户签到，所以需要获取用户id
		int n = ds.signin(emp.getEmpId());//0	签退失败	1 签退成功	2 尚未签到
		
		//把数据响应回浏览器
		response.getWriter().println(n);
	}
	
}
