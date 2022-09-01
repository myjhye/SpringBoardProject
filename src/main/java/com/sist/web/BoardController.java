package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

// request에 값을 담아서 JSP로 전송 
import java.util.*;
import com.sist.dao.*;

@Controller
public class BoardController {

	@Autowired
	private BoardDAO dao;
	
	////////////////////////////
	
	// 게시글 목록
	@GetMapping("board/list.do")
	public String board_list(String page, Model model)
	{
		if(page == null)
			page = "1";
		
		int curpage = Integer.parseInt(page);
		
		Map map = new HashMap();
		map.put("start", (curpage*10)-9);
		map.put("end", curpage*10);
		
		List<BoardVO> list = dao.boardListData(map);
		int totalpage = dao.boardTotalPage();
		
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("list", list);
		
		return "board/list";
	}
	
	////////////////////////////////////////
	
	// 상세보기
	@GetMapping("board/detail.do")
	public String board_detail(int no, Model model)
	{
		BoardVO vo = dao.boardDetailData(no);
		model.addAttribute("vo", vo);
		
		return "board/detail";
	}
	
	////////////////////////////////////////
	
	// 게시글 작성 => 입력 화면 보여주기
	@GetMapping("board/insert.do")
	public String board_insert()
	{
		return "board/insert";
	}
	
	// 게시글 작성 작업 처리
	@PostMapping("board/insert_ok.do")
	public String board_insert_ok(BoardVO vo)
	{
		dao.boardInsert(vo); // 게시글 작성하고
		
		return "redirect: list.do"; // 게시글 목록으로 돌아온다
	}
	
	
	
	////////////////////////////////////////
	
	// 게시글 수정
	@GetMapping("board/update.do")
	public String board_update(int no, Model model)
	{
		BoardVO vo = dao.boardUpdateData(no);
		model.addAttribute("vo", vo);
		
		return "board/update";
	}
	
	////////////////////////////////////////
	
	
	// 게시글 삭제
	@GetMapping("board/delete.do")
	public String board_delete(int no, Model model)
	{
		BoardVO vo = dao.boardDeleteData(no);
		model.addAttribute("vo", vo);
		
		return "board/delete";
	}
	
	
	
	////////////////////////////////////////
	
	// 검색
	@PostMapping("board/find.do")
	public String board_find(String[] fd, String ss, Model model)
	{
		Map map = new HashMap();
		map.put("fsArr", fd);
		map.put("ss", ss);
		
		List<BoardVO> list = dao.boardFindData(map);
		model.addAttribute("list", list);
		
		return "board/find";
	}
	
}







