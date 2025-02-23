package com.Hostel_management.Hostel.Controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Hostel_management.Hostel.DTO.Aten_dance_DTO;
import com.Hostel_management.Hostel.DTO.Month_fess_mess_DTO;
import com.Hostel_management.Hostel.Entity.Atten_danceEnt;
import com.Hostel_management.Hostel.Entity.Overall_dataEnt;
import com.Hostel_management.Hostel.Entity.mess_fees_month;
import com.Hostel_management.Hostel.Entity.offonstudentlogin;
import com.Hostel_management.Hostel.Service.StudService;
import com.itextpdf.text.DocumentException;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class hoController {

	@Autowired
	StudService StudService;

	@PostMapping("/post") // stor ov-data and image Overall_dataEnt
	public ResponseEntity<String> savedata(@RequestPart("overall") Overall_dataEnt overall,
			@RequestPart("image") MultipartFile fail) throws IOException {
		StudService.Saveoveralldata(overall, fail);
		return ResponseEntity.ok("Save Successful Data name " + overall.getStudent_name());
	}

	@GetMapping("/getonedata") // get One student data Overall_dataEnt
	public ResponseEntity<Optional<Overall_dataEnt>> getonedata(@RequestParam String name,
			@RequestParam String hostel_id) {
		return ResponseEntity.ok().body(StudService.getonedata(name, hostel_id));
	}

	@GetMapping("/getoneimage") // get one student Image
	public ResponseEntity<List<Map<String, Object>>> getImagdata(@RequestParam String name,
			@RequestParam String hostel_id) {
		return ResponseEntity.ok(StudService.getImagdata(name, hostel_id));
	}

	@GetMapping("/getAlldata") // get All data Overall_dataEnt
	public ResponseEntity<List<Overall_dataEnt>> getAlldata() {
		return ResponseEntity.ok().body(StudService.getAlldata());
	}

	@GetMapping("/getAllimag") // get All Images Overall_dataEnt
	public ResponseEntity<List<Map<String, Object>>> update() throws IOException {
		return ResponseEntity.ok(StudService.getAllImag());
	}

	@PutMapping("/Putmap") // Update ov-data
	public ResponseEntity<String> Updatedata(@RequestBody Overall_dataEnt overall) throws IOException {
		StudService.Updatedata(overall);
		return ResponseEntity.ok("Save Succ essful Data name " + overall.getStudent_name());
	}

	@DeleteMapping("/delect")
	public ResponseEntity<String> Delectim_ov_id(@RequestParam Long imgid, @RequestParam Long vorid) {
		StudService.Delectbyidimag_da_and_overal(imgid, vorid);
		return ResponseEntity.ok("Save Successful Deletede");
	}

	// Atendence codes post data
	@PostMapping("/Attrndance_save")
	public ResponseEntity<String> Attendancesave(Aten_dance_DTO Aten_dance_DTO) {
		StudService.Attendencsave(Aten_dance_DTO);
		return ResponseEntity.ok().body("Attrndance marked SuccessFully = " + Aten_dance_DTO.getStudent_name());
	}

	// Atendence Gel All data
	@GetMapping("/GetAlldataAttendance")
	public ResponseEntity<List<Atten_danceEnt>> GetAlldataAttendance() {
		return ResponseEntity.ok(StudService.getallAttendence());
	}

	// Select one data Atten
	@GetMapping("/Selcet_Att_onedata")
	public ResponseEntity<List<Atten_danceEnt>> Selcetonedatatt(@RequestParam String name,
			@RequestParam String hostel_id) {
		return ResponseEntity.ok().body(StudService.Getonedataselect(name, hostel_id));
	}

	// Atendence Delect one data
	@DeleteMapping("/delectbyidAttendence/{id}")
	public ResponseEntity<String> Deletebyid(@PathVariable Long id) {
		StudService.DelectbyidAttendence(id);
		return ResponseEntity.ok().body("Delete SuccessFully");
	}

	// Start Mess fees month new data
	@PostMapping("/Mess_fess_month_save")
	public ResponseEntity<String> Mess_fess_month(@RequestBody Month_fess_mess_DTO Month_fess_mess_DTO) {
		StudService.Mess_fess_month(Month_fess_mess_DTO);

		return ResponseEntity.ok().body("Successful Store Miss Billw Data");
	}

	// Delect one data fsee mont
	@DeleteMapping("/delec_fess_mot by id/{id}")
	public ResponseEntity<String> Deletebyid_fsee_mont(@PathVariable Long id) {
		StudService.DelectbyMess_fess_monthdence(id);
		return ResponseEntity.ok().body("Delete SuccessFully");
	}

	// Start Mess fees month Update
	@PutMapping("/Update_Mess_fess_month")
	public ResponseEntity<String> Mess_fess_monthUpdate(@RequestBody Month_fess_mess_DTO Month_fess_mess_DTO) {
		StudService.Mess_fess_monthUpdate(Month_fess_mess_DTO);
		return ResponseEntity.ok().body("Successful Update data");
	}

	@GetMapping("/mess_fess_getbyid/{id}")
	public ResponseEntity<Optional<mess_fees_month>> Mess_fessgetbyid(@PathVariable Long id) {
		return ResponseEntity.ok().body(StudService.GetbyIdMessfess(id));
	}

	// Select* one data mess fess month
	@GetMapping("/Selcet_messFess_month_onedata")
	public ResponseEntity<List<mess_fees_month>> Selcetonedatat_Mess_fess(@RequestParam String name,
			@RequestParam String hostel_id) {
		return ResponseEntity.ok().body(StudService.Getonedata_Mess_fess_select(name, hostel_id));
	}
	
	//get All data mess fess month
		@GetMapping("/Get_All_messFess_month_data")
		public ResponseEntity<List<mess_fees_month>> GetAlldata_mont_fess_mess(){
		    return ResponseEntity.ok().body(StudService.GetAllmont_fess());
		}

	// Selct one month_mess_fees bod pdf
	@GetMapping("/month_mess_fees_select_pdf/{month}")
	public ResponseEntity<byte[]> Mess_fess_getdatapfd(@PathVariable("month") String month)
			throws DocumentException, IOException {
		byte[] pdfbyt = StudService.month_mess_fess_pdfdownote(month);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_PDF);
		headers.setContentDispositionFormData("attachment", "Attendanceonemonth.pdf");

		return ResponseEntity.ok().headers(headers).body(pdfbyt);
	}

	// All pdf mess_fess
	@GetMapping("/All_data_mess_fees_select_pdf")
	public ResponseEntity<byte[]> All_mess_fess_getdatapdf() throws DocumentException, IOException {
		byte[] pdfbyt = StudService.All_mess_fess_pdfdownote();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_PDF);
		headers.setContentDispositionFormData("attachment", "AttendanceAlldata.pdf");

		return ResponseEntity.ok().headers(headers).body(pdfbyt);
	}

	// All pdf START OT END Attendance dow
	@GetMapping("/_Attendance_start_to_end_select_pdf")
	public ResponseEntity<byte[]> _Attendance_start_to_end_select_pdf(@RequestParam LocalDate Start,
			@RequestParam LocalDate End) throws DocumentException, IOException {
		byte[] pdfbyt = StudService.All_Attendence_stat_to_end_pdfdownote(Start, End);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_PDF);
		headers.setContentDispositionFormData("attachment", "data.pdf");

		return ResponseEntity.ok().headers(headers).body(pdfbyt);
	}

	// All pdf Attendance dow
	@GetMapping("/All_Attendance_pdf")
	public ResponseEntity<byte[]> Alldata_Attendance_pdf() throws DocumentException, IOException {
		byte[] pdfbyt = StudService.All_Attendencepdfdownote();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_PDF);
		headers.setContentDispositionFormData("attachment", "data.pdf");

		return ResponseEntity.ok().headers(headers).body(pdfbyt);
	}
	
	
	// Student mess pdfdow
	@GetMapping("/Student_mess_pdf")
	public ResponseEntity<byte[]> Student_mess_pdf(@RequestParam String name,
			@RequestParam String hostel_id) throws DocumentException, IOException {
		byte[] pdfbyt = StudService.Studentdowpdf(name, hostel_id);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_PDF);
		headers.setContentDispositionFormData("attachment", "Student.pdf");
		return ResponseEntity.ok().headers(headers).body(pdfbyt);
	}
	
	@GetMapping("/true _or_false")
	public Optional<offonstudentlogin> Teran_of_one_stloging() {
		return StudService.offonlogin();
	}
	
	@PutMapping("/setstudent_login_off_on")
	public ResponseEntity<String> storoffonn(@RequestBody offonstudentlogin offonstudentlogin){
		StudService.offonstor(offonstudentlogin);
		if(offonstudentlogin.getConds()) {
			return ResponseEntity.ok("Student Login page turn On");
		}else {
			return ResponseEntity.ok("Student Login page turn Off");
		}
		
	}
}
//System.out.println(overall.getStudent_name());
//System.out.println(overall.getHostel_id());
//System.out.println(overall.getBranch());
//System.out.println(overall.getGender());
//System.out.println(overall.getYear());

//System.out.println(Aten_dance_DTO.getStudent_name());
//System.out.println(Aten_dance_DTO.getHostel_id());
//System.out.println(Aten_dance_DTO.getBranch());
//System.out.println(Aten_dance_DTO.getGender());
//System.out.println(Aten_dance_DTO.getYear());
//System.out.println(Aten_dance_DTO.getAttendance());