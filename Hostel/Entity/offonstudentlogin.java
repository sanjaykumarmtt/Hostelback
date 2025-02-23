package com.Hostel_management.Hostel.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Student_login_on_off")
public class offonstudentlogin {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	Boolean conds;
	public offonstudentlogin() {
		super();
	}
	public offonstudentlogin(int id, Boolean conds) {
		super();
		this.id = id;
		this.conds = conds;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Boolean getConds() {
		return conds;
	}
	public void setConds(Boolean conds) {
		this.conds = conds;
	}

}
