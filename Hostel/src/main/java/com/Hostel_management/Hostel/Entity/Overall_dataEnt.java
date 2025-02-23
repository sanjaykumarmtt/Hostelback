package com.Hostel_management.Hostel.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Overall_dataEnt {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String hostel_id;
	private String Student_name;
	private String Year;
	private String Gender;
	private String Branch;
	
	public Overall_dataEnt() {
		super();
	}
	public Overall_dataEnt(Long id, String hostel_id, String student_name, String year, String gender, String branch) {
		super();
		this.id = id;
		this.hostel_id = hostel_id;
		Student_name = student_name;
		Year = year;
		Gender = gender;
		Branch = branch;
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
}
