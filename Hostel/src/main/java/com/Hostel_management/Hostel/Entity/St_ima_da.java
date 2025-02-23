package com.Hostel_management.Hostel.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class St_ima_da {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String hostel_id;
	private String Student_name;
	private String Year;
	private String imgname;
	
	@Lob
	@Column(name="bytdata",columnDefinition="LONGBLOB")
	private byte[] bytdata;

	public St_ima_da() {
		super();
	}

	public St_ima_da(Long id, String hostel_id, String student_name, String year, String imgname, byte[] bytdata) {
		super();
		this.id = id;
		this.hostel_id = hostel_id;
		Student_name = student_name;
		Year = year;
		this.imgname = imgname;
		this.bytdata = bytdata;
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

	public String getImgname() {
		return imgname;
	}

	public void setImgname(String imgname) {
		this.imgname = imgname;
	}

	public byte[] getBytdata() {
		return bytdata;
	}

	public void setBytdata(byte[] bytdata) {
		this.bytdata = bytdata;
	}
}
