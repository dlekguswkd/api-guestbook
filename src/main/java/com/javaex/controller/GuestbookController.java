package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestVo;

@RestController
public class GuestbookController {

	@Autowired
	private GuestbookService guestbookService;


	// http://localhost:8888/guestbook3/addlist
	/* 리스트 (등록폼)*/
	@RequestMapping(value="/addlist", method= {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		System.out.println("PhonebookController.addList()");
		
		List<GuestVo> guestList = guestbookService.exeGetGuestList();
		
		// 한다리 건너서 넣기 (데이터 보내기)
		model.addAttribute("guestList", guestList);
		
		return "addList";
		
	}
	
	// http://localhost:8888/guestbook3/insert?name=~&password=~&content=~
	/* 등록 */
	@RequestMapping(value="/insert", method= {RequestMethod.GET, RequestMethod.POST})
	public String insert(@ModelAttribute GuestVo guestVo) {
		
		System.out.println("guestbookController.insert()");
		
		guestbookService.exeInsertGuest(guestVo);

		//리스트로 리다이렉트
		return "redirect:/addlist";
		
	}
	
	// ---------------------------------------------------------------------------------
	// http://localhost:8888/guestbook3/deleteform?no=~
	/* 삭제폼 */
	@RequestMapping(value="deleteform", method= {RequestMethod.GET, RequestMethod.POST})
	public String deleteForm(@RequestParam(value="no") int no , Model model) {
		
		System.out.println("guestbookController.deleteForm()");
	
		GuestVo guestVo = guestbookService.exeGetGuestOne(no);

		model.addAttribute("guestVo", guestVo);
		
		return "deleteForm";
		
	}
	
	// http://localhost:8888/guestbook3/delete?
	/* 삭제 */
	@RequestMapping(value="delete", method= {RequestMethod.GET, RequestMethod.POST})
	public String delete(@RequestParam(value="no") int no , 
						 @RequestParam(value="password") String password) {
		
		System.out.println("guestbookController.delete()");
		
		int count = guestbookService.exeGuestDelete(no, password);
		System.out.println(count);

		
		return "redirect:/addlist";
	} 
	
	
	
	
}
