package com.bid.board.common;


import org.apache.commons.text.StringEscapeUtils;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("unused")
public class Util {
	
	// 파일명 변경 메소드
	public static String fileRename(String originFileName) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String date = sdf.format(new java.util.Date(System.currentTimeMillis()));

		int ranNum = (int) (Math.random() * 100000); // 5자리 랜덤 숫자 생성

		String str = "_" + String.format("%05d", ranNum);

		String ext = originFileName.substring(originFileName.lastIndexOf("."));

		return date + str + ext;
	}

	// 개행문자 처리
	public static String newLineHandling(String content) {
		return content.replaceAll("(\r\n|\r|\n|\n\r)", "<br>");
	}

	// 개행문자 해제
	public static String newLineClear(String content) {
		return content.replaceAll("<br>", "\n");
	}

	public static String removeQuotes(String input) {
		return input.replaceAll("[\"\\[\\]\\\\]", "").replaceAll("&quot;", "").replaceAll(",", ", ");
	}
	
	// 문자열 잘라서 쓰는 유틸
	// https://bigstupid.tistory.com/41
	public static class SessionUtil {
	    public static HttpSession getSession() {
	        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
	        return attr.getRequest().getSession();
	    }
	}
	
	// 경고창 관련 유틸
	 private static void init(HttpServletResponse response) {
	        response.setContentType("text/html; charset=euc-kr");
	        response.setCharacterEncoding("euc-kr");
	    }
	 
	 // 경고
	    public static void alert(HttpServletResponse response, String alertText) throws IOException {
	        init(response);
	        PrintWriter out = response.getWriter();
	        out.println("<script>alert('" + alertText + "');</script> ");
	        out.flush();
	    }
	 
	    // 경고 및 다음페이지 이동
	    public static void alertAndMovePage(HttpServletResponse response, String alertText, String nextPage)
	            throws IOException {
	        init(response);
	        PrintWriter out = response.getWriter();
	        out.println("<script>alert('" + alertText + "'); location.href='" + nextPage + "';</script> ");
	        out.flush();
	    }
	 
	    // 경고 및 이전 페이지로 이동
	    public static void alertAndBackPage(HttpServletResponse response, String alertText) throws IOException {
	        init(response);
	        PrintWriter out = response.getWriter();
	        out.println("<script>alert('" + alertText + "'); history.go(-1);</script>");
	        out.flush();
	    }
	
}


