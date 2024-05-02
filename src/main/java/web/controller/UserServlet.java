package web.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.repository.UserInfoRepository;

@WebServlet("/user-info")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserInfoRepository uiRepo = new UserInfoRepository(); // 의존성 투입
       
  

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uri = request.getRequestURI(); // uri요청 초기화
		int idx = uri.lastIndexOf("/"); // 끝에서 시작하는 "/"부터 자름
		uri = uri.substring(idx+1); //만약 +1을 안해준다면 ex) .../user-infouser-list.jsp되어버림
		
		String path = "/WEB-INF/views/user-info/";  // 기본 path를 정해두고
		if("list".equals(uri)) { // 만약 uri에 list가 있는지 확인
			path+= "user-list.jsp"; // 맞다면 +=하여 이동
			request.setAttribute("userInfoList", uiRepo.selectUserInfoList()); //특성을 요청하여 userInfoList란 이름을 value로 설정
		}else if("insert".equals(uri)) {
			path+= "user-insert.jsp";
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(path); //requestDispatcher를 이용하여 요청받은 정보 초기화
		rd.forward(request, response); //정보를 받은걸 저장하고 반환
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
