package com.Hostel_management.Hostel.Entity;

import java.time.LocalDate;
import java.time.LocalTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Atten_danceEnt {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String hostel_id;
	private String Student_name;
	private String Year;
	private String Gender;
	private String Branch;
	private String Attendance;

//	@UpdateTimestamp
//	private LocalTime createdTime;
	
	@CreationTimestamp
	@Column(updatable=false)
	private LocalTime createtime;
	
	@CreationTimestamp
	@Column(updatable=false)
	private LocalDate crentdate;
	
	public Atten_danceEnt() {
		super();
	}

	public Atten_danceEnt(Long id, String hostel_id, String student_name, String year, String gender, String branch,
			String attendance,LocalTime createtime, LocalDate crentdate) {
		super();
		this.id = id;
		this.hostel_id = hostel_id;
		Student_name = student_name;
		Year = year;
		Gender = gender;
		Branch = branch;
		Attendance = attendance;
		this.createtime = createtime;
		this.crentdate = crentdate;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public LocalTime getCreatetime() {
		return createtime;
	}

	public void setCreatetime(LocalTime createtime) {
		this.createtime = createtime;
	}

	public LocalDate getCrentdate() {
		return crentdate;
	}

	public void setCrentdate(LocalDate crentdate) {
		this.crentdate = crentdate;
	}
}
