package com.Hostel_management.Hostel.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Hostel_management.Hostel.Entity.offonstudentlogin;

@Repository
public interface Loginonoffcon extends JpaRepository<offonstudentlogin,Integer>{

}
