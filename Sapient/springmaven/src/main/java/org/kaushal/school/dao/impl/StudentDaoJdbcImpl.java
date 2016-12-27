package org.kaushal.school.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.kaushal.school.dao.StudentDao;
import org.kaushal.school.entity.StudentEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDaoJdbcImpl implements StudentDao {
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public StudentEntity createStudent(StudentEntity studentEntity) {
		return null;
	}

	public boolean updateStudent(StudentEntity studentEntity) {
		return false;
	}

	public boolean deleteStudent(StudentEntity studentEntity) {
		return false;
	}

	public List<StudentEntity> getStudentList() {
		return jdbcTemplate.query("select * from student", new RowMapper<StudentEntity>() {

			public StudentEntity mapRow(ResultSet rs, int paramInt) throws SQLException {
				StudentEntity st = new StudentEntity();
				st.setId(rs.getInt("id"));
				st.setName(rs.getString("name"));
				st.setCity(rs.getString("city"));
				st.setDob(rs.getDate("dob"));
				return st;
			}
		});
		// return null;
	}

}
