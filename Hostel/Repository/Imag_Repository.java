package com.Hostel_management.Hostel.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Hostel_management.Hostel.Entity.St_ima_da;

@Repository
public interface Imag_Repository extends JpaRepository<St_ima_da,Long>{
	
	@Query(value="SELECT *FROM st_ima_da WHERE student_name= :name AND hostel_id= :hostel_id",nativeQuery=true)
	public Optional<St_ima_da> getimgedata(@Param("name") String name,@Param("hostel_id") String hostel_id);

}