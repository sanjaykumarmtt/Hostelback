package com.Hostel_management.Hostel.DTO;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;

public class Aten_dance_DTO {
	
	
	private String hostel_id;
	private String Student_name;
	private String Year;
	private String Gender;
	private String Branch;
	private String Attendance;

	
	@CreationTimestamp
	@Column(updatable=false)
	private LocalDate crentdate;
	
	public Aten_dance_DTO() {
		super();
	}

	public Aten_dance_DTO(String hostel_id, String student_name, String year, String gender, String branch,
			String attendance,LocalDate crentdate) {
		super();
		
		this.hostel_id = hostel_id;
		Student_name = student_name;
		Year = year;
		Gender = gender;
		Branch = branch;
		Attendance = attendance;
		this.crentdate = crentdate;
	}
	public String getHostel_id() {
		return hostel_id;
	}

	public void setHostel_id(String hostel_id) {
		this.hostel_id = hostel_id;
	}

	public String getStudent_name() {
		return Student_name;
	}

	public void setStudent_name(String student_name) {
		Student_name = student_name;
	}

	public String getYear() {
		return Year;
	}

	public void setYear(String year) {
		Year = year;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public String getBranch() {
		return Branch;
	}

	public void setBranch(String branch) {
		Branch = branch;
	}

	public String getAttendance() {
		return Attendance;
	}

	public void setAttendance(String attendance) {
		Attendance = attendance;
	}

	public LocalDate getCrentdate() {
		return crentdate;
	}

	public void setCrentdate(LocalDate crentdate) {
		this.crentdate = crentdate;
	}
	
}
