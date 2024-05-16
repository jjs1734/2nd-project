package com.wizian.web.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wizian.web.dto.BoardDTO;
import com.wizian.web.service.BoardService;

@Controller
public class IndiController {
	
	
	@Autowired
	private BoardService boardService;
	
	
	
	@GetMapping("/indicoun")
	public String indiCoun() {
		return "indicoun";
		
	}
	@GetMapping("/indicoun_apply")
	public String indiCounApply() {
		return "indicoun_apply";
		
	}
	
	
	@GetMapping("/indiboard")
	public String indiboard(@RequestParam(value="boardNo", defaultValue = "1") int boardNo,  Model model) {
	    List<BoardDTO> board = boardService.board(boardNo);
	    model.addAttribute("board", board);
	    return "indiboard";
	}

	//20240307 안드로이드 앱 프로그래밍 psd 
	// 상세보기 -> no 잡기 -> 확인
	// detail.html
	// 값 -> DB에 물어보기 어떤 형태로?  BoardDTO
	@GetMapping("/detail")
	public String detail(@RequestParam("no") int no, Model model) {
	    BoardDTO detail = boardService.detail(no);
	    model.addAttribute("detail", detail);
	    return "detail";
	}

	@GetMapping("/notice")
	public String notice(@RequestParam(value="boardNo", defaultValue = "2") int boardNo,  Model model) {
	    List<BoardDTO> board = boardService.board(boardNo);
	    model.addAttribute("board", board);
	    return "notice";
	}

	@GetMapping("/write")
	public String write() {
	    return "write";
	}

	@PostMapping("/write")
	public String write(@RequestParam Map<String, Object> map) {
	    //System.out.println(map); 172.30.1.75
	    int result = boardService.write(map);
	    System.out.println(result);
	    return "write";
	}

	@PostMapping("/postDel")
	public String postDel(@RequestParam("no") int no) {
	    System.out.println(no);
	    return "redirect:/board";
	}
			
		
		
		
		
	}
		
