package web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
			
		}else if("view".equals(uri)) {
			path+= "user-view.jsp";
			String uiNum = request.getParameter("uiNum");  // 파라미터에서 uiNum값을 요청함
			Map<String,String> userInfo = uiRepo.selectUserInfo(uiNum); //uiNum값을 가진 생성자를 Map에 넣음
			request.setAttribute("userInfo",userInfo); //특성을 요청하여 uiNum값을 가진 userInfo를 value로 설정
			
		}else if("insert".equals(uri)) {
			path+= "user-insert.jsp";
			
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(path); //requestDispatcher를 이용하여 요청받은 정보 초기화
		rd.forward(request, response); //정보를 받은걸 저장하고 반환
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();
		int idx = uri.lastIndexOf("/");
		uri = uri.substring(idx+1);
		String path = "/WEB-INF/views/msg/msg.jsp";
		if("insert".equals(uri)) {
			Map<String,String> param = new HashMap<>();
			
			param.put("uiName", request.getParameter("uiName"));
			param.put("uiId", request.getParameter("uiId"));
			param.put("uiPwd", request.getParameter("uiPwd"));
			int result = uiRepo.insertUserInfo(param);
			request.setAttribute("msg","회원등록 실패입니다." );
			request.setAttribute("uri", "/user-info/insert");
			if(result == 1) {
				request.setAttribute("msg","회원등록 성공." );
				request.setAttribute("uri", "/user-info/list");
			}
		}
		RequestDispatcher rd = request.getRequestDispatcher(path); // msg출력을 위해서 사용
		rd.forward(request, response);
	}

}
