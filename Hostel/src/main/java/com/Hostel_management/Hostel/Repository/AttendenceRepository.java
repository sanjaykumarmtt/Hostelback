package com.Hostel_management.Hostel.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Hostel_management.Hostel.Entity.Atten_danceEnt;

@Repository
public interface AttendenceRepository extends JpaRepository<Atten_danceEnt,Long>{

	@Query(value="SELECT *FROM atten_dance_ent WHERE student_name= :name AND hostel_id= :hostel_id",nativeQuery=true)
	public List<Atten_danceEnt> getAttedata(@Param("name") String name,@Param("hostel_id") String hostel_id);
	
	@Query(value="SELECT *FROM atten_dance_ent WHERE crentdate BETWEEN :Stat AND End",nativeQuery=true)
	public List<Atten_danceEnt> getdataStart_to_ent_pdf(@Param("Stat") LocalDate Stat,@Param("End") LocalDate End);

}
//select *from atten_dance_ent WHERE student_name="sanjay" aND hostel_id="A01586" and crentdate="2025-02-09";