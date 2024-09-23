package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestVo;

@Service
public class GuestbookService {
	
	@Autowired
	private GuestbookDao guestbookDao;
	
	
	/*  리스트 (등록폼) */
	public List<GuestVo> exeGetGuestList() {
		System.out.println("GuestbookService.exeGetGuestList()");
		
		List<GuestVo> guestList = guestbookDao.getGuestList();
		
		return guestList;
		
	}
	
	/* 등록 */
	public int exeInsertGuest(GuestVo guestVo) {		
		System.out.println("GuestbookService.exeInsertGuest()");

		int count = guestbookDao.insertGuest(guestVo);
		return count;
		
	}
	
	/* 삭제폼 1명 정보 가져오기 */
	public GuestVo exeGetGuestOne (int no) {	
		System.out.println("GuestbookService.exeGetGuestOne()");
		
		GuestVo guestVo = guestbookDao.getGuestOne(no);
		
		return guestVo;
	}
	
	/* 삭제 */
	public int exeGuestDelete(int no, String password) {
		
		System.out.println("GuestbookService.exeGuestDelete()");
		
		int count = guestbookDao.deleteGuest(no, password);
		
		return count;
	}

}
