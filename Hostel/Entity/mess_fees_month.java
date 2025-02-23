package com.Hostel_management.Hostel.Entity;

import java.time.LocalDate;
import java.time.LocalTime;

import org.hibernate.annotations.CreationTimestamp;

import com.itextpdf.text.Paragraph;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class mess_fees_month {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String hostel_id;
	private String Student_name;
	private String Year;
	private String Gender;
	private String Branch;
	private double Total_mess_balance;
	private double Mess_fess_collected_fom_Student;
	private long No_of_Attendence;
	private double Mess_fee_Rs_per_day;
	private double mess_staff_salay_per_Student;
	private double Mess_fee_to_pay_l_m;
	private double Balance_Avalabile_g_h_i__n;
	private String Month;
	
	@CreationTimestamp
	@Column(updatable=false)
	private LocalTime createtime;
	
	@CreationTimestamp
	@Column(updatable=false)
	private LocalDate crentdate;
	
	public mess_fees_month() {
		super();
	}

	public mess_fees_month(String month,Long id, String hostel_id, String student_name, String year, String gender, String branch,
			double total_mess_balance, double mess_fess_collected_fom_Student, long no_of_Attendence,
			double mess_fee_Rs_per_day, double mess_staff_salay_per_Student, double mess_fee_to_pay_l_m,
			double balance_Avalabile_g_h_i__n, LocalTime createtime, LocalDate crentdate) {
		super();
		this.id = id;
		this.hostel_id = hostel_id;
		Student_name = student_name;
		Year = year;
		Gender = gender;
		Branch = branch;
		Total_mess_balance = total_mess_balance;
		Mess_fess_collected_fom_Student = mess_fess_collected_fom_Student;
		No_of_Attendence = no_of_Attendence;
		Mess_fee_Rs_per_day = mess_fee_Rs_per_day;
		this.mess_staff_salay_per_Student = mess_staff_salay_per_Student;
		Mess_fee_to_pay_l_m = mess_fee_to_pay_l_m;
		Balance_Avalabile_g_h_i__n = balance_Avalabile_g_h_i__n;
		this.createtime = createtime;
		this.crentdate = crentdate;
		Month = month;
	}

	public String getMonth() {
		return Month;
	}

	public void setMonth(String month) {
		Month = month;
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

	public double getTotal_mess_balance() {
		return Total_mess_balance;
	}

	public void setTotal_mess_balance(double total_mess_balance) {
		Total_mess_balance = total_mess_balance;
	}

	public double getMess_fess_collected_fom_Student() {
		return Mess_fess_collected_fom_Student;
	}

	public void setMess_fess_collected_fom_Student(double mess_fess_collected_fom_Student) {
		Mess_fess_collected_fom_Student = mess_fess_collected_fom_Student;
	}

	public long getNo_of_Attendence() {
		return No_of_Attendence;
	}

	public void setNo_of_Attendence(long no_of_Attendence) {
		No_of_Attendence = no_of_Attendence;
	}

	public double getMess_fee_Rs_per_day() {
		return Mess_fee_Rs_per_day;
	}

	public void setMess_fee_Rs_per_day(double mess_fee_Rs_per_day) {
		Mess_fee_Rs_per_day = mess_fee_Rs_per_day;
	}

	public double getMess_staff_salay_per_Student() {
		return mess_staff_salay_per_Student;
	}

	public void setMess_staff_salay_per_Student(double mess_staff_salay_per_Student) {
		this.mess_staff_salay_per_Student = mess_staff_salay_per_Student;
	}

	public double getMess_fee_to_pay_l_m() {
		return Mess_fee_to_pay_l_m;
	}

	public void setMess_fee_to_pay_l_m(double mess_fee_to_pay_l_m) {
		Mess_fee_to_pay_l_m = mess_fee_to_pay_l_m;
	}

	public double getBalance_Avalabile_g_h_i__n() {
		return Balance_Avalabile_g_h_i__n;
	}

	public void setBalance_Avalabile_g_h_i__n(double balance_Avalabile_g_h_i__n) {
		Balance_Avalabile_g_h_i__n = balance_Avalabile_g_h_i__n;
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
