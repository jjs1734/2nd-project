package com.wizian.web.controller;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wizian.web.dto.BoardDTO;
import com.wizian.web.dto.UserDTO;
import com.wizian.web.service.BoardService;
import com.wizian.web.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class IndiController {

    @Autowired
    private BoardService boardService;
    @Autowired
    private UserService userService;

    @GetMapping("/indicoun")
    public String indiCoun() {
        return "indicoun";
    }
	/*
	 * @GetMapping("/indicoun_apply") public String indiCounApply() { return
	 * "indicoun_apply"; }
	 */

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

    @GetMapping("/indiboard_detail")
    public String detail(@RequestParam("no") int no, Model model) {
        BoardDTO detail = boardService.detail(no);
        model.addAttribute("detail", detail);
        return "indiboard_detail";
    }

    @GetMapping("/notice")
    public String notice(@RequestParam(value = "boardNo", defaultValue = "2") int boardNo, Model model) {
        List<BoardDTO> board = boardService.board(boardNo);
        model.addAttribute("board", board);
        return "notice";
    }

    @GetMapping("/indiboard_apply")
    public String showConsultationForm(@RequestParam(name = "counselor", required = false) String counselor, Model model, HttpSession session) {
    	
    	String userId = (String) session.getAttribute("userId");
    	if (userId == null) return "redirect:/login";
    	
    	UserDTO userInfo = userService.userInfo(userId);
    	model.addAttribute("userInfo", userInfo);
    	System.out.println("인디 컨트롤러" + userInfo);
        List<String> counselors = Arrays.asList("김을용", "리키마틴", "세이버", "홍길동");
        model.addAttribute("counselors", counselors);
        if (counselor != null) {
            model.addAttribute("selectedCounselor", counselor);
        }
        return "indiboard_apply";
    }

    @PostMapping("/indiboard_apply")
    public String write(@RequestParam Map<String, Object> map) {
    	
    	 map.put("pstg_ymd", LocalDate.now().toString()); // 현재 날짜를 게시일로 설정
    	 map.put("pst_comp", "미완료"); // 기본값을 '미완료'로 설정
    	 map.put("writer", "임의작가"); // 작성자를 임의의 값으로 설정 (필요에 따라 변경 가능)
    	
        int result = boardService.write(map);
        System.out.println(result);
        return "redirect:/indiboard";
    }

    @PostMapping("/postDel")
    public String postDel(@RequestParam("no") int no) {
        System.out.println(no);
        return "redirect:/board";
    }
}
