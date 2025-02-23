package com.Hostel_management.Hostel.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Hostel_management.Hostel.Entity.Overall_dataEnt;

@Repository
public interface Overall_Repository extends JpaRepository<Overall_dataEnt,Long>{

	@Query(value="SELECT *FROM overall_data_ent WHERE student_name= :name AND hostel_id= :hostel_id",nativeQuery=true)
	public Optional<Overall_dataEnt> getondata(@Param("name") String name,@Param("hostel_id") String hostel_id);
}
