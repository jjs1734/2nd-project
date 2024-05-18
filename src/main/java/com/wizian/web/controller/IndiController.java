package com.wizian.web.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String indiboard(@RequestParam(value = "page", defaultValue = "1") int page, Model model) {
	    try {
	        int pageSize = 10; // 페이지당 표시할 데이터 수
	        List<BoardDTO> board = boardService.getBoards(page, pageSize);
	        int totalItems = boardService.getTotalBoardCount(); // 전체 게시물 수
	        int totalPages = (int) Math.ceil((double) totalItems / pageSize);

	        model.addAttribute("board", board);
	        model.addAttribute("totalPages", totalPages);
	        model.addAttribute("currentPage", page);
	        
	        System.out.println("Total Pages: " + totalPages);
	        
	        
	    } catch (Exception e) {
	        model.addAttribute("error", "서버 오류 발생");
	        return "errorPage"; // 오류 페이지 뷰 이름
	    }
	    return "indiboard";
	}

	
	
	
	/*
	 * @GetMapping("/indiboard") public String
	 * indiboard(@RequestParam(value="boardNo", defaultValue = "1") int boardNo,
	 * Model model) { List<BoardDTO> board = boardService.board(boardNo);
	 * model.addAttribute("board", board); return "indiboard"; }
	 */
	//20240307 안드로이드 앱 프로그래밍 psd 
	// 상세보기 -> no 잡기 -> 확인
	// detail.html
	// 값 -> DB에 물어보기 어떤 형태로?  BoardDTO
	@GetMapping("/indiboard_detail")
	public String detail(@RequestParam("no") int no, Model model) {
	    BoardDTO detail = boardService.detail(no);
	    model.addAttribute("detail", detail);
	    return "indiboard_detail";
	}

	@GetMapping("/notice")
	public String notice(@RequestParam(value="boardNo", defaultValue = "2") int boardNo,  Model model) {
	    List<BoardDTO> board = boardService.board(boardNo);
	    model.addAttribute("board", board);
	    return "notice";
	}

	@GetMapping("/indiboard_apply")
	public String showConsultationForm(@RequestParam(name = "counselor", required = false) String counselor, Model model) {
	    List<String> counselors = Arrays.asList("김을용", "리키마틴", "세이버", "홍길동");
	    model.addAttribute("counselors", counselors);
	    if (counselor != null) {
	        model.addAttribute("selectedCounselor", counselor);
	    }
	    return "indiboard_apply";  // 해당 뷰 파일 이름이 맞는지 확인
	}


	    
	    
	    
	@PostMapping("/indiboard_apply")
	public String write(@RequestParam Map<String, Object> map) {
	    //System.out.println(map); 172.30.1.75
	    int result = boardService.write(map);
	    System.out.println(result);
	    return "redirect:/indiboard";
	}

	@PostMapping("/postDel")
	public String postDel(@RequestParam("no") int no) {
	    System.out.println(no);
	    return "redirect:/board";
	}
			
	@PostMapping("/submitReply")
    public String submitReply(@RequestParam Map<String,Object>map) {
        
            int result = boardService.submitReply(map);
            return "redirect:/indiboard_detail?no=" + map.get("originalPostId");  // Redirect back to the original post
        }
		
		
	
	@GetMapping("/api/replies")
	@ResponseBody
	public List<BoardDTO> getReplies(@RequestParam("postId") int postId) {
	    return boardService.getRepliesForPost(postId);
	}

	
}

		
