package edu.kh.jsp.student.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static edu.kh.jsp.common.JDBCTemplate.*;
import edu.kh.jsp.student.model.dto.Student;

public class StudentDAO {
	
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Properties prop;
	
	public StudentDAO() {
		try {
			String filePath 
					= StudentDAO.class.getResource("/edu/kh/jsp/sql/student-sql.xml").getPath();
					
			prop = new Properties();
			prop.loadFromXML( new FileInputStream(filePath) );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Student> selectAll(Connection conn) throws Exception{
		// 1. 결과 저장용 변수 선언
		List<Student> stdList = new ArrayList<Student>();
		
		try {
			// 2. sql 작성
			String sql = prop.getProperty("selectAll");
			
			// 3. Statement 객체 생성
			stmt = conn.createStatement();
			
			// 4. sql 수행 후 결과 반환받기
			rs = stmt.executeQuery(sql);
			
			// 5. ResultSet 1행씩 접근하면서 List에 옯겨 담기
			while(rs.next()) {
				String studentNo = rs.getString("STUDENT_NO");
				String studentName = rs.getString("STUDENT_NAME");
				String studetnAddress = rs.getString("STUDENT_ADDRESS");
				String departmentName = rs.getString("DEPARTMENT_NAME");
				
				Student student = new Student(studentNo, studentName, studetnAddress, departmentName);
				
				stdList.add(student);
			}
			
			
		} finally {
			close(rs);
			close(stmt);
		}
		return stdList;
	}

	public List<Student> selectArch(Connection conn) throws Exception{
		List<Student> stdList = new ArrayList<Student>();
		
		try {
			String sql = prop.getProperty("selectArch");
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				String studentNo = rs.getString("STUDENT_NO");
				String studentName = rs.getString("STUDENT_NAME");
				String studetnAddress = rs.getString("STUDENT_ADDRESS");
				String departmentName = rs.getString("DEPARTMENT_NAME");
				
				Student student = new Student(studentNo, studentName, studetnAddress, departmentName);
				
				stdList.add(student);
			}
			
		} finally {
			close(rs);
			close(stmt);
		}
		
		return stdList;
	}

	public List<Student> selectOne(Connection conn, String deptName) throws Exception{
		List<Student> stdList = new ArrayList<Student>();
		
		try {
			String sql = prop.getProperty("selectOne");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, deptName);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				String studentNo = rs.getString("STUDENT_NO");
				String studentName = rs.getString("STUDENT_NAME");
				String studetnAddress = rs.getString("STUDENT_ADDRESS");
				String departmentName = rs.getString("DEPARTMENT_NAME");
				
				Student student = new Student(studentNo, studentName, studetnAddress, departmentName);
				
				stdList.add(student);
			}
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return stdList;
	}
}
