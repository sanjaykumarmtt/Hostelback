package com.Hostel_management.Hostel.Repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Hostel_management.Hostel.Entity.St_ima_da;
import com.Hostel_management.Hostel.Entity.mess_fees_month;

@Repository
public interface mess_fees_month_Reopsitory extends JpaRepository<mess_fees_month,Long>{
	
	@Query(value="SELECT *FROM mess_fees_month WHERE student_name= :name AND hostel_id= :hostel_id",nativeQuery=true)
	public List<mess_fees_month> get_fess_Selectdata(@Param("name") String name,@Param("hostel_id") String hostel_id);

	@Query(value="SELECT *FROM mess_fees_month WHERE month= :monthle",nativeQuery=true)
	public List<mess_fees_month> get_fess_Selectdatapdf(@Param("monthle") String monthle);

}
