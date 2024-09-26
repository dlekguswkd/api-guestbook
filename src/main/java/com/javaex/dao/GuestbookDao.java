package com.javaex.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestVo;

@Repository
public class GuestbookDao {

	@Autowired
	private SqlSession sqlSession;

	/* 리스트 (등록폼) */
	public List<GuestVo> getGuestList() {

		System.out.println("GuestbookDao.getGuestList()");

		List<GuestVo> guestList = sqlSession.selectList("guestbook.selectList");

		return guestList;

	}

	/* 등록 */
	public int insertGuest(GuestVo guestVo) {
		System.out.println("GuestbookDao.insertGuest()");

		int count = sqlSession.insert("guestbook.insert", guestVo);
		return count;
	}

	
	/* 삭제 */
	public int deleteGuest(int no, String password) {
		System.out.println("GuestbookDao.deleteGuest()");

		GuestVo guestVo = new GuestVo(no, password);
		int count = sqlSession.delete("guestbook.delete", guestVo);

		return count;
	}
	
	
	
	
}
