package com.Hostel_management.Hostel.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Hostel_management.Hostel.DTO.Aten_dance_DTO;
import com.Hostel_management.Hostel.DTO.Month_fess_mess_DTO;
import com.Hostel_management.Hostel.Entity.Atten_danceEnt;
import com.Hostel_management.Hostel.Entity.Overall_dataEnt;
import com.Hostel_management.Hostel.Entity.St_ima_da;
import com.Hostel_management.Hostel.Entity.mess_fees_month;
import com.Hostel_management.Hostel.Entity.offonstudentlogin;
import com.Hostel_management.Hostel.ExceptionHan.HostelException;
import com.Hostel_management.Hostel.Repository.AttendenceRepository;
import com.Hostel_management.Hostel.Repository.Imag_Repository;
import com.Hostel_management.Hostel.Repository.Loginonoffcon;
import com.Hostel_management.Hostel.Repository.Overall_Repository;
import com.Hostel_management.Hostel.Repository.mess_fees_month_Reopsitory;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class StudService {

	@Autowired
	Overall_Repository Overall_Repository;
	@Autowired
	Imag_Repository Imag_Repository;
	@Autowired
	AttendenceRepository AttendenceRepository;
	@Autowired
	mess_fees_month_Reopsitory mess_fees_month_Reopsitory;
	@Autowired
	Loginonoffcon Loginonoffcon;

	// Sror over_data and imag over Entit
	public void Saveoveralldata(Overall_dataEnt Overall_dataEnt, MultipartFile fail) throws IOException ,HostelException{
		Optional<Overall_dataEnt> Overall=Overall_Repository.getondata(Overall_dataEnt.getStudent_name(), Overall_dataEnt.getHostel_id());
		Optional<St_ima_da> image=Imag_Repository.getimgedata(Overall_dataEnt.getStudent_name(), Overall_dataEnt.getHostel_id());
		if(Overall.isEmpty() && image.isEmpty()) {
			St_ima_da St_ima_da = new St_ima_da();

			St_ima_da.setStudent_name(Overall_dataEnt.getStudent_name());
			St_ima_da.setHostel_id(Overall_dataEnt.getHostel_id());
			St_ima_da.setYear(Overall_dataEnt.getYear());

			St_ima_da.setImgname(fail.getOriginalFilename());
			St_ima_da.setBytdata(fail.getBytes());
			Overall_Repository.save(Overall_dataEnt);
			Imag_Repository.save(St_ima_da);
		}else {
			throw new HostelException("This Student Data is Already Stored "+Overall_dataEnt.getStudent_name());
		}
		
		
	}

	// get one Student data voerallEntit
	public Optional<Overall_dataEnt> getonedata(String name, String hostel_id) {
		return Overall_Repository.getondata(name, hostel_id);
	}

	// get one Student Image St_imag
	public List<Map<String, Object>> getImagdata(String name, String hostel_id) {
		return Imag_Repository.getimgedata(name, hostel_id).stream().map(image -> {
			Map<String, Object> imgdata = new HashMap<>();
			imgdata.put("id", image.getId());
			imgdata.put("hostel_id", image.getHostel_id());
			imgdata.put("Student_name", image.getStudent_name());
			imgdata.put("year", image.getYear());
			imgdata.put("imgname", image.getImgname());
			imgdata.put("data", Base64.getEncoder().encodeToString(image.getBytdata()));
			return imgdata;
		}).toList();
	}
						
    //get All data in voerallEntit
	public List<Overall_dataEnt> getAlldata() {
		return Overall_Repository.findAll();
	}

	// get All Student Image St_imag
	public List<Map<String, Object>> getAllImag() throws IOException {
		return Imag_Repository.findAll().stream().map(image -> {
			Map<String, Object> imgdata = new HashMap<>();
			imgdata.put("id", image.getId());
			imgdata.put("hostel_id", image.getHostel_id());
			imgdata.put("Student_name", image.getStudent_name());
			imgdata.put("year", image.getYear());
			imgdata.put("imgname", image.getImgname());
			imgdata.put("data", Base64.getEncoder().encodeToString(image.getBytdata()));
			return imgdata;
		}).toList();
	}
	//update Imag And overAll data
	public void Updatedata(Overall_dataEnt Overall_dataEnt) throws IOException {
		Optional<Overall_dataEnt> Overall_dataEnt1=Overall_Repository.findById(Overall_dataEnt.getId());
		Optional<St_ima_da> St_ima_da=Imag_Repository.getimgedata(Overall_dataEnt1.get().getStudent_name(), Overall_dataEnt1.get().getHostel_id());
		
		if(Overall_dataEnt1.get().getStudent_name().equalsIgnoreCase(St_ima_da.get().getStudent_name()) &&
		   Overall_dataEnt1.get().getHostel_id().equalsIgnoreCase(St_ima_da.get().getHostel_id())) {
			Overall_Repository.save(Overall_dataEnt);
			St_ima_da St_ima_da1=new St_ima_da();
			St_ima_da1.setId(St_ima_da.get().getId());
			St_ima_da1.setStudent_name(Overall_dataEnt.getStudent_name());
			St_ima_da1.setHostel_id(Overall_dataEnt.getHostel_id());
			St_ima_da1.setYear(Overall_dataEnt.getYear());
			St_ima_da1.setImgname(St_ima_da.get().getImgname());
			St_ima_da1.setBytdata(St_ima_da.get().getBytdata());
			
			Imag_Repository.save(St_ima_da1);
		}
		
		
	}
	//delet imageid and overid  
	public void Delectbyidimag_da_and_overal(Long idimg,Long idover) {
		Imag_Repository.deleteById(idimg);
		Overall_Repository.deleteById(idover);
	}
	                 //Attendence codes
	public void Attendencsave(Aten_dance_DTO Aten_dance_DTO) {
	
			Atten_danceEnt Atten_danceEnt=new Atten_danceEnt();
			Atten_danceEnt.setStudent_name(Aten_dance_DTO.getStudent_name());
			Atten_danceEnt.setHostel_id(Aten_dance_DTO.getHostel_id());
			Atten_danceEnt.setBranch(Aten_dance_DTO.getBranch());
			Atten_danceEnt.setGender(Aten_dance_DTO.getGender());
			Atten_danceEnt.setYear(Aten_dance_DTO.getYear());
			Atten_danceEnt.setAttendance(Aten_dance_DTO.getAttendance());
			AttendenceRepository.save(Atten_danceEnt);
	}
	 //get All Atinase Attendence codes
	public List<Atten_danceEnt> getallAttendence() {
		return AttendenceRepository.findAll();
	}
	
	//get Attan Selct *one data
	public List<Atten_danceEnt> Getonedataselect(String name,String hostel_id){
		return AttendenceRepository.getAttedata(name, hostel_id);	
	}
	//delect by id Atinase Attendence codes
	public void DelectbyidAttendence(Long id) {
		Optional<Atten_danceEnt> Atten=AttendenceRepository.findById(id);
		
		if(Atten.isPresent()) {
			 AttendenceRepository.deleteById(id);
		}else {
			throw new HostelException("This Id not Available In Attendance Table = "+id);
		}
	}
	    //mess fess  month stste in post data   mess_fees_month
	public void Mess_fess_month(Month_fess_mess_DTO Month_fess_mess_DTO) {
		
		try {
			mess_fees_month mess_fees_month=new mess_fees_month();
			
			mess_fees_month.setHostel_id(Month_fess_mess_DTO.getHostel_id());
			mess_fees_month.setStudent_name(Month_fess_mess_DTO.getStudent_name());
			mess_fees_month.setYear(Month_fess_mess_DTO.getYear());
			mess_fees_month.setGender(Month_fess_mess_DTO.getGender());
			mess_fees_month.setBranch(Month_fess_mess_DTO.getBranch());
			mess_fees_month.setTotal_mess_balance(Month_fess_mess_DTO.getTotal_mess_balance());
			mess_fees_month.setMess_fess_collected_fom_Student(Month_fess_mess_DTO.getMess_fess_collected_fom_Student());
			mess_fees_month.setNo_of_Attendence(Month_fess_mess_DTO.getNo_of_Attendence());
			mess_fees_month.setMess_fee_Rs_per_day(Month_fess_mess_DTO.getMess_fee_Rs_per_day());
			mess_fees_month.setMess_staff_salay_per_Student(Month_fess_mess_DTO.getMess_staff_salay_per_Student());
			mess_fees_month.setMess_fee_to_pay_l_m(Month_fess_mess_DTO.getMess_fee_to_pay_l_m());
			mess_fees_month.setBalance_Avalabile_g_h_i__n(Month_fess_mess_DTO.getBalance_Avalabile_g_h_i__n());
			mess_fees_month.setMonth(Month_fess_mess_DTO.getMonth());
		mess_fees_month_Reopsitory.save(mess_fees_month);
		}catch(Exception e) {
			throw new HostelException("Error "+e);
		}
	}
	//mess fess  month stste in Delecyt by id data 
	public void DelectbyMess_fess_monthdence(Long id) {
		Optional<mess_fees_month> Atten=mess_fees_month_Reopsitory.findById(id);
		
		if(Atten.isPresent()) {
			mess_fees_month_Reopsitory.deleteById(id);
		}else {
			throw new HostelException("This Id not Available In Attendanc = "+id);
		}
	}
	//update  data for Mess_fess_monthUpdate
	public void Mess_fess_monthUpdate(Month_fess_mess_DTO Month_fess_mess_DTO) {	
		try {
			mess_fees_month mess_fees_month=new mess_fees_month();
			
			mess_fees_month.setId(Month_fess_mess_DTO.getId());
			mess_fees_month.setHostel_id(Month_fess_mess_DTO.getHostel_id());
			mess_fees_month.setStudent_name(Month_fess_mess_DTO.getStudent_name());
			mess_fees_month.setYear(Month_fess_mess_DTO.getYear());
			mess_fees_month.setGender(Month_fess_mess_DTO.getGender());
			mess_fees_month.setBranch(Month_fess_mess_DTO.getBranch());
			mess_fees_month.setTotal_mess_balance(Month_fess_mess_DTO.getTotal_mess_balance());
			mess_fees_month.setMess_fess_collected_fom_Student(Month_fess_mess_DTO.getMess_fess_collected_fom_Student());
			mess_fees_month.setNo_of_Attendence(Month_fess_mess_DTO.getNo_of_Attendence());
			mess_fees_month.setMess_fee_Rs_per_day(Month_fess_mess_DTO.getMess_fee_Rs_per_day());
			mess_fees_month.setMess_staff_salay_per_Student(Month_fess_mess_DTO.getMess_staff_salay_per_Student());
			mess_fees_month.setMess_fee_to_pay_l_m(Month_fess_mess_DTO.getMess_fee_to_pay_l_m());
			mess_fees_month.setBalance_Avalabile_g_h_i__n(Month_fess_mess_DTO.getBalance_Avalabile_g_h_i__n());
			mess_fees_month.setMonth(Month_fess_mess_DTO.getMonth());
		
			mess_fees_month_Reopsitory.save(mess_fees_month);
		}catch(Exception e) {
			throw new HostelException("Error "+e);
		}
		
//		mess_fees_month_Reopsitory.save(mess_fees_month);
	}
	// for Mess_fess_monthUpdate getone data
    public Optional<mess_fees_month> GetbyIdMessfess(Long id){
		return mess_fees_month_Reopsitory.findById(id);	
    }
    
	//get Fess_mont Selct *one data
	public List<mess_fees_month> Getonedata_Mess_fess_select(String name,String hostel_id){
		return mess_fees_month_Reopsitory.get_fess_Selectdata(name, hostel_id);	
	}
	
	public List<mess_fees_month> GetAllmont_fess(){
		return mess_fees_month_Reopsitory.findAll();
		
	}
	
	//get select month mess mont pdf
	public byte[]  month_mess_fess_pdfdownote(String month) throws DocumentException,IOException{
		List<mess_fees_month> mess_fees_month=mess_fees_month_Reopsitory.get_fess_Selectdatapdf(month);
		
		if(!mess_fees_month.isEmpty()){
		Document document=new Document();
		ByteArrayOutputStream out=new ByteArrayOutputStream();
		PdfWriter.getInstance(document, out);
		document.open();
		
		
		Font heat=new Font(Font.FontFamily.TIMES_ROMAN,14,Font.BOLD);
	    Paragraph parahead=new Paragraph("UCEA HOSTELS",heat);
	    parahead.setAlignment(Element.ALIGN_CENTER);
	    document.add(parahead);
	    
	    Font subhade=new Font(Font.FontFamily.TIMES_ROMAN,10,Font.BOLD);
	    Paragraph parahsub=new Paragraph("UNIVERSITY COLLEGE OF ENGINEERING ARNI,THATCHUR",subhade);
	    parahsub.setAlignment(Element.ALIGN_CENTER);
	    document.add(parahsub);
	    
	    Paragraph parahsub2=new Paragraph("MESS FEES FOR THE MONTH OF "+month,subhade);
	    parahsub2.setAlignment(Element.ALIGN_CENTER);
	    document.add(parahsub2);
	    document.add(new Paragraph(" "));
	    
	    PdfPTable table=new PdfPTable(13);
	    table.setWidthPercentage(110);
	    table.setHorizontalAlignment(Element.ALIGN_CENTER);
	    
	    Font tbfont=new Font(Font.FontFamily.HELVETICA,8,Font.BOLD);
	    table.addCell(new Paragraph("SL.NO",tbfont));
	    table.addCell(new Paragraph("HOSTEL ID",tbfont));
	    table.addCell(new Paragraph("Student Name",tbfont));
	    table.addCell(new Paragraph("Year",tbfont));
	    table.addCell(new Paragraph("Gender",tbfont));
	    table.addCell(new Paragraph("Branch",tbfont));
	    table.addCell(new Paragraph("Total Mess Balance",tbfont));
	    table.addCell(new Paragraph("Mess Fees Collected from STUDENT",tbfont));
	    table.addCell(new Paragraph("No.of Attendance",tbfont));
	    table.addCell(new Paragraph("Mess Fee @RS/Per day",tbfont));
	    table.addCell(new Paragraph("Mess Staff Salary Per Student",tbfont));
	    table.addCell(new Paragraph("Mess Fess to Pay [I+m]",tbfont));
	    table.addCell(new Paragraph("Balance Avalabile (g+h+i-n)",tbfont));
	    
	    table.addCell(new Paragraph("(a)",tbfont));
	    table.addCell(new Paragraph("(b)",tbfont));
	    table.addCell(new Paragraph("(c)",tbfont));
	    table.addCell(new Paragraph("(d)",tbfont));
	    table.addCell(new Paragraph("(e)",tbfont));
	    table.addCell(new Paragraph("(f)",tbfont));
	    table.addCell(new Paragraph("(g)",tbfont));
	    table.addCell(new Paragraph("(h)",tbfont));
	    table.addCell(new Paragraph("(i)",tbfont));
	    table.addCell(new Paragraph("(j)",tbfont));
	    table.addCell(new Paragraph("(k)",tbfont));
	    table.addCell(new Paragraph("(l)",tbfont));
	    table.addCell(new Paragraph("(m)",tbfont));
	    
	    Font tbfcon=new Font(Font.FontFamily.HELVETICA,7,Font.BOLD);
	    int i=0;
      for(mess_fees_month mess:mess_fees_month) {
    	  i++;
    	  table.addCell(new Paragraph(""+i,tbfcon));
    	  table.addCell(new Paragraph(mess.getHostel_id(),tbfcon));
    	  table.addCell(new Paragraph(mess.getStudent_name(),tbfcon));
    	  table.addCell(new Paragraph(mess.getYear(),tbfcon));
    	  table.addCell(new Paragraph(mess.getGender(),tbfcon));
    	  table.addCell(new Paragraph(mess.getBranch(),tbfcon));
    	  table.addCell(new Paragraph(""+mess.getTotal_mess_balance(),tbfcon));
    	  table.addCell(new Paragraph(""+mess.getMess_fess_collected_fom_Student(),tbfcon));
    	  table.addCell(new Paragraph(""+mess.getNo_of_Attendence(),tbfcon));
    	  table.addCell(new Paragraph(""+mess.getMess_fee_Rs_per_day(),tbfcon));
    	  table.addCell(new Paragraph(""+mess.getMess_staff_salay_per_Student(),tbfcon));
    	  table.addCell(new Paragraph(""+mess.getMess_fee_to_pay_l_m(),tbfcon));
    	  table.addCell(new Paragraph(""+mess.getBalance_Avalabile_g_h_i__n(),tbfcon));    	  
		}
      document.add(table);
      document.close();
		return out.toByteArray();
		}else {
			throw new HostelException("This Month is Not Available in DataBase ="+month);
		}

	}
	//mess fess_mont_All_pdf
	public byte[]  All_mess_fess_pdfdownote() throws DocumentException,IOException{
		List<mess_fees_month> mess_fees_month=mess_fees_month_Reopsitory.findAll();
		
		Document document=new Document();
		ByteArrayOutputStream out=new ByteArrayOutputStream();
		PdfWriter.getInstance(document, out);
		document.open();
		
		
		Font heat=new Font(Font.FontFamily.TIMES_ROMAN,14,Font.BOLD);
	    Paragraph parahead=new Paragraph("UCEA HOSTELS",heat);
	    parahead.setAlignment(Element.ALIGN_CENTER);
	    document.add(parahead);
	    
	    Font subhade=new Font(Font.FontFamily.TIMES_ROMAN,10,Font.BOLD);
	    Paragraph parahsub=new Paragraph("UNIVERSITY COLLEGE OF ENGINEERING ARNI,THATCHUR",subhade);
	    parahsub.setAlignment(Element.ALIGN_CENTER);
	    document.add(parahsub);
	    
	    Paragraph parahsub2=new Paragraph("MESS FEES FOR THE ALL DAtA",subhade);
	    parahsub2.setAlignment(Element.ALIGN_CENTER);
	    document.add(parahsub2);
	    document.add(new Paragraph(" "));
	    
	    PdfPTable table=new PdfPTable(13);
	    table.setWidthPercentage(110);
	    table.setHorizontalAlignment(Element.ALIGN_CENTER);
	    
	    Font tbfont=new Font(Font.FontFamily.HELVETICA,8,Font.BOLD);
	    table.addCell(new Paragraph("SL.NO",tbfont));
	    table.addCell(new Paragraph("HOSTEL ID",tbfont));
	    table.addCell(new Paragraph("Student Name",tbfont));
	    table.addCell(new Paragraph("Year",tbfont));
	    table.addCell(new Paragraph("Gender",tbfont));
	    table.addCell(new Paragraph("Branch",tbfont));
	    table.addCell(new Paragraph("Total Mess Balance",tbfont));
	    table.addCell(new Paragraph("Mess Fees Collected from STUDENT",tbfont));
	    table.addCell(new Paragraph("No.of Attendance",tbfont));
	    table.addCell(new Paragraph("Mess Fee @RS/Per day",tbfont));
	    table.addCell(new Paragraph("Mess Staff Salary Per Student",tbfont));
	    table.addCell(new Paragraph("Mess Fess to Pay [I+m]",tbfont));
	    table.addCell(new Paragraph("Balance Avalabile (g+h+i-n)",tbfont));
	    
	    table.addCell(new Paragraph("(a)",tbfont));
	    table.addCell(new Paragraph("(b)",tbfont));
	    table.addCell(new Paragraph("(c)",tbfont));
	    table.addCell(new Paragraph("(d)",tbfont));
	    table.addCell(new Paragraph("(e)",tbfont));
	    table.addCell(new Paragraph("(f)",tbfont));
	    table.addCell(new Paragraph("(g)",tbfont));
	    table.addCell(new Paragraph("(h)",tbfont));
	    table.addCell(new Paragraph("(i)",tbfont));
	    table.addCell(new Paragraph("(j)",tbfont));
	    table.addCell(new Paragraph("(k)",tbfont));
	    table.addCell(new Paragraph("(l)",tbfont));
	    table.addCell(new Paragraph("(m)",tbfont));
	    
	    Font tbfcon=new Font(Font.FontFamily.HELVETICA,7,Font.BOLD);
	    int i=0;
      for(mess_fees_month mess:mess_fees_month) {
    	  i++;
    	  table.addCell(new Paragraph(""+i,tbfcon));
    	  table.addCell(new Paragraph(mess.getHostel_id(),tbfcon));
    	  table.addCell(new Paragraph(mess.getStudent_name(),tbfcon));
    	  table.addCell(new Paragraph(mess.getYear(),tbfcon));
    	  table.addCell(new Paragraph(mess.getGender(),tbfcon));
    	  table.addCell(new Paragraph(mess.getBranch(),tbfcon));
    	  table.addCell(new Paragraph(""+mess.getTotal_mess_balance(),tbfcon));
    	  table.addCell(new Paragraph(""+mess.getMess_fess_collected_fom_Student(),tbfcon));
    	  table.addCell(new Paragraph(""+mess.getNo_of_Attendence(),tbfcon));
    	  table.addCell(new Paragraph(""+mess.getMess_fee_Rs_per_day(),tbfcon));
    	  table.addCell(new Paragraph(""+mess.getMess_staff_salay_per_Student(),tbfcon));
    	  table.addCell(new Paragraph(""+mess.getMess_fee_to_pay_l_m(),tbfcon));
    	  table.addCell(new Paragraph(""+mess.getBalance_Avalabile_g_h_i__n(),tbfcon));    	  
		}
      document.add(table);
      document.close();
		return out.toByteArray();
  }
	//All_Attendence_stat_to_end_pdfdownote
	public byte[]  All_Attendence_stat_to_end_pdfdownote(LocalDate Start ,LocalDate End) throws DocumentException,IOException{
		List<Atten_danceEnt> Atten_danceEnt=AttendenceRepository.getdataStart_to_ent_pdf(Start, End);
		
		if(!Atten_danceEnt.isEmpty()) {
			Document document=new Document();
			ByteArrayOutputStream out=new ByteArrayOutputStream();
			PdfWriter.getInstance(document, out);
			document.open();
			
			
			Font heat=new Font(Font.FontFamily.TIMES_ROMAN,12,Font.BOLD);
		    Paragraph parahead=new Paragraph("Last Month Attendance",heat);
		    parahead.setAlignment(Element.ALIGN_CENTER);
		    document.add(parahead);
		    document.add(new Paragraph(" "));
		    
		    PdfPTable table=new PdfPTable(9);
		    table.setWidthPercentage(110);
		    table.setHorizontalAlignment(Element.ALIGN_CENTER);
		    
		    Font tbfont=new Font(Font.FontFamily.HELVETICA,8,Font.BOLD);
		    table.addCell(new Paragraph("SL.NO.",tbfont));
		    table.addCell(new Paragraph("HOSTEL ID",tbfont));
		    table.addCell(new Paragraph("Student Name",tbfont));
		    table.addCell(new Paragraph("Year",tbfont));
		    table.addCell(new Paragraph("Gender",tbfont));
		    table.addCell(new Paragraph("Branch",tbfont));
		    table.addCell(new Paragraph("Attendance",tbfont));
		    table.addCell(new Paragraph("Date",tbfont));
		    table.addCell(new Paragraph("Time",tbfont));
		    
		    int n=0;
		    for(Atten_danceEnt Atten:Atten_danceEnt) {
		    	n++;
		    	table.addCell(new Paragraph(""+n,tbfont));
		  	    table.addCell(new Paragraph(Atten.getHostel_id(),tbfont));
		  	    table.addCell(new Paragraph(Atten.getStudent_name(),tbfont));
		  	    table.addCell(new Paragraph(Atten.getYear(),tbfont));
		  	    table.addCell(new Paragraph(Atten.getGender(),tbfont));
		  	    table.addCell(new Paragraph(Atten.getBranch(),tbfont));
		  	    table.addCell(new Paragraph(Atten.getAttendance(),tbfont));
		  	    table.addCell(new Paragraph(""+Atten.getCrentdate(),tbfont));
		  	    table.addCell(new Paragraph(""+Atten.getCreatetime(),tbfont));
		    }
		    document.add(table);
		      document.close();
				return out.toByteArray();

		}else {
			throw new HostelException("This Date is Not Available in DataBase ="+Start+" to "+End);
		}
			   
}
	//All_Attendencepdfdownote
	public byte[]  All_Attendencepdfdownote() throws DocumentException,IOException{
		List<Atten_danceEnt> Atten_danceEnt=AttendenceRepository.findAll();
		
		if(!Atten_danceEnt.isEmpty()) {
			Document document=new Document();
			ByteArrayOutputStream out=new ByteArrayOutputStream();
			PdfWriter.getInstance(document, out);
			document.open();
			
			
			Font heat=new Font(Font.FontFamily.TIMES_ROMAN,12,Font.BOLD);
		    Paragraph parahead=new Paragraph("All Attendance Data",heat);
		    parahead.setAlignment(Element.ALIGN_CENTER);
		    document.add(parahead);
		    document.add(new Paragraph(" "));
		    
		    PdfPTable table=new PdfPTable(9);
		    table.setWidthPercentage(110);
		    table.setHorizontalAlignment(Element.ALIGN_CENTER);
		    
		    Font tbfont=new Font(Font.FontFamily.HELVETICA,8,Font.BOLD);
		    table.addCell(new Paragraph("SL.NO.",tbfont));
		    table.addCell(new Paragraph("HOSTEL ID",tbfont));
		    table.addCell(new Paragraph("Student Name",tbfont));
		    table.addCell(new Paragraph("Year",tbfont));
		    table.addCell(new Paragraph("Gender",tbfont));
		    table.addCell(new Paragraph("Branch",tbfont));
		    table.addCell(new Paragraph("Attendance",tbfont));
		    table.addCell(new Paragraph("Date",tbfont));
		    table.addCell(new Paragraph("Time",tbfont));
		    
		    int n=0;
		    for(Atten_danceEnt Atten:Atten_danceEnt) {
		    	n++;
		    	table.addCell(new Paragraph(""+n,tbfont));
		  	    table.addCell(new Paragraph(Atten.getHostel_id(),tbfont));
		  	    table.addCell(new Paragraph(Atten.getStudent_name(),tbfont));
		  	    table.addCell(new Paragraph(Atten.getYear(),tbfont));
		  	    table.addCell(new Paragraph(Atten.getGender(),tbfont));
		  	    table.addCell(new Paragraph(Atten.getBranch(),tbfont));
		  	    table.addCell(new Paragraph(Atten.getAttendance(),tbfont));
		  	    table.addCell(new Paragraph(""+Atten.getCrentdate(),tbfont));
		  	    table.addCell(new Paragraph(""+Atten.getCreatetime(),tbfont));
		    }
		    document.add(table);
		      document.close();
				return out.toByteArray();

		}else {
			throw new HostelException("This Date is Not Available in DataBase");
		}		   
}
	
	public byte[]  Studentdowpdf(String name,String hostel_id) throws DocumentException,IOException{
		Optional<Overall_dataEnt> Overall_dataEnt=Overall_Repository.getondata(name, hostel_id);
		List<mess_fees_month> messfeesmonth=mess_fees_month_Reopsitory.get_fess_Selectdata(name, hostel_id);
		List<Atten_danceEnt> Atten_danceEnt=AttendenceRepository.getAttedata(name, hostel_id);
		
		Document document=new Document();
		ByteArrayOutputStream out=new ByteArrayOutputStream();
		PdfWriter.getInstance(document, out);
		document.open();
		
		Font heat=new Font(Font.FontFamily.TIMES_ROMAN,13,Font.BOLD);
	    Paragraph parahead=new Paragraph("Hostel Report",heat);
	    parahead.setAlignment(Element.ALIGN_CENTER);
	    document.add(parahead);
	    document.add(new Paragraph(" "));
	    
	    Font fontname=new Font(Font.FontFamily.TIMES_ROMAN,10,Font.BOLD);
	    Paragraph pname=new Paragraph("NAME  :  "+Overall_dataEnt.get().getStudent_name(),fontname);
	    pname.setAlignment(Element.ALIGN_LEFT);
	    document.add(pname);
		
		Paragraph Histelid=new Paragraph("Hostel ID : "+Overall_dataEnt.get().getHostel_id(),fontname);
	   	document.add(Histelid);
	   	
	   	Paragraph Branch=new Paragraph("Branch : "+Overall_dataEnt.get().getBranch(),fontname);
	   	document.add(Branch);
	   	
		Paragraph Year=new Paragraph("Year : "+Overall_dataEnt.get().getYear(),fontname);
	   	document.add(Year);
	    document.add(new Paragraph(" "));
	   	
		Font Attheat=new Font(Font.FontFamily.TIMES_ROMAN,11,Font.BOLD);
	    Paragraph Attparahead=new Paragraph("Attendance",Attheat);
	    Attparahead.setAlignment(Element.ALIGN_CENTER);
	    document.add(Attparahead);
	    document.add(new Paragraph(" "));
	   	
	    PdfPTable table=new PdfPTable(4);
	    table.setWidthPercentage(90);
	    
	    Font tbfont=new Font(Font.FontFamily.HELVETICA,9,Font.BOLD);
	    table.addCell(new Paragraph("SL.NO.",tbfont));
	    table.addCell(new Paragraph("Crentdate",tbfont));
	    table.addCell(new Paragraph("createtime",tbfont));
	    table.addCell(new Paragraph("Attendance",tbfont));
	   
	    
	    Font tbfcon=new Font(Font.FontFamily.HELVETICA,7,Font.BOLD);
	    
	    int n=0;
		for(Atten_danceEnt Atten:Atten_danceEnt) {
				n++;
				table.addCell(new Paragraph(""+n,tbfcon));
				table.addCell(new Paragraph(""+Atten.getCrentdate(),tbfcon));
				table.addCell(new Paragraph(""+Atten.getCreatetime(),tbfcon));
				table.addCell(new Paragraph(""+Atten.getAttendance(),tbfcon));
	    }
		n=0;
		document.add(table);
		document.add(new Paragraph(" "));
		
		Font mess_fees=new Font(Font.FontFamily.TIMES_ROMAN,11,Font.BOLD);
	    Paragraph messparahead=new Paragraph("Mess Report",mess_fees);
	    messparahead.setAlignment(Element.ALIGN_CENTER);
	    document.add(messparahead);
	    document.add(new Paragraph(" "));
			
	    PdfPTable messtable=new PdfPTable(9);
	    messtable.setWidthPercentage(110);
	    
	    messtable.addCell(new Paragraph("SL.NO.",tbfont));
	    messtable.addCell(new Paragraph("Month",tbfont));
	    messtable.addCell(new Paragraph("Total Mess Balance",tbfont));
	    messtable.addCell(new Paragraph("Mess Fees Collected forom Student",tbfont));
	    messtable.addCell(new Paragraph("No.of Attendance",tbfont));
	    messtable.addCell(new Paragraph("Mess Fee@Rs/per day",tbfont));
	    messtable.addCell(new Paragraph("Mess Staff Salary per Stusent",tbfont));
	    messtable.addCell(new Paragraph("Mess fee to pay",tbfont));
	    messtable.addCell(new Paragraph("Balance Avalabile",tbfont));
		   
		for(mess_fees_month mess_feer:messfeesmonth) {
		n++;
		    messtable.addCell(new Paragraph(""+n,tbfcon));
			messtable.addCell(new Paragraph(""+mess_feer.getMonth(),tbfcon));
			messtable.addCell(new Paragraph(""+mess_feer.getTotal_mess_balance(),tbfcon));
			messtable.addCell(new Paragraph(""+mess_feer.getMess_fess_collected_fom_Student(),tbfcon));
			messtable.addCell(new Paragraph(""+mess_feer.getNo_of_Attendence(),tbfcon));
			messtable.addCell(new Paragraph(""+mess_feer.getMess_fee_Rs_per_day(),tbfcon));
			messtable.addCell(new Paragraph(""+mess_feer.getMess_staff_salay_per_Student(),tbfcon));
			messtable.addCell(new Paragraph(""+mess_feer.getMess_fee_to_pay_l_m(),tbfcon));
			messtable.addCell(new Paragraph(""+mess_feer.getBalance_Avalabile_g_h_i__n(),tbfcon));
    }
		document.add(messtable);
		document.close();
		return out.toByteArray();
	}
	
	public Optional<offonstudentlogin> offonlogin(){
		return Loginonoffcon.findById(1);
	}
	public void offonstor(offonstudentlogin offonstudentlogin) {
		Loginonoffcon.save(offonstudentlogin);
	}
		
}
