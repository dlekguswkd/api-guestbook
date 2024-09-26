package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.service.GuestbookService;
import com.javaex.uti.JsonResult;
import com.javaex.vo.GuestVo;

@RestController
public class GuestbookController {

	@Autowired
	private GuestbookService guestbookService;


	// http://localhost:9000/api/persons
	// http://localhost:3000/addlist
	/* 리스트 (등록폼)*/
	@GetMapping("/api/guests")
	public JsonResult addList() {
		System.out.println("GuestbookController.addList()");
		
		List<GuestVo> guestList = guestbookService.exeGetGuestList();

		return JsonResult.success(guestList);
	}
	
	// http://localhost:9000/api/persons
	/* 등록 */
	@PostMapping("/api/guests")
	public JsonResult insert(@RequestBody GuestVo guestVo) {
		
		System.out.println("guestbookController.insert()");
		
		int count = guestbookService.exeInsertGuest(guestVo);

		if(count != 1) {
			return JsonResult.fail("등록에 실패했습니다.");
		}else { //등록됨
			return JsonResult.success(count);
		}
		
	}
	
	// ---------------------------------------------------------------------------------
	
	// http://localhost:9000/api/persons/~
	/* 삭제 */
	@DeleteMapping("/api/guests/{no}")
	public JsonResult delete(@RequestBody GuestVo guestVo, @PathVariable(value = "no") int no) {
		
		System.out.println("guestbookController.delete()");
		
		int count = guestbookService.exeGuestDelete(no, guestVo.getPassword());
		
		if(count != 1) {	// 실패 (삭제안됨)
			return JsonResult.fail("해당번호가 없습니다.");
			
		}else {				// 성공 (삭제됨) 
			return JsonResult.success(count);
		}
		
	} 
	
	
	
	
}
