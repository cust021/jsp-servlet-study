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
       
  
	//doGet 으로 조회를 목적
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
		}else if("update".equals(uri)) {
			path+= "user-update.jsp";
			String uiNum = request.getParameter("uiNum");
			Map<String,String> userInfo = uiRepo.selectUserInfo(uiNum); // 들어갔을 때 수정 전 데이터 출력 목적으로 추가
			request.setAttribute("userInfo", userInfo);	
		}else if("delete".equals(uri)) {
			path+= "user-info/user-delete.jsp";
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(path); //requestDispatcher를 이용하여 요청받은 정보 초기화
		rd.forward(request, response); //정보를 받은걸 저장하고 반환
	}

	//doPost 생성,수정,삭제가 목적
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();
		int idx = uri.lastIndexOf("/");
		uri = uri.substring(idx+1);
		String path = "/WEB-INF/views/msg/msg.jsp";
		
		if("insert".equals(uri)) {
			Map<String,String> param = new HashMap<>();
			
			param.put("uiName", request.getParameter("uiName")); //jsp안에 파라미터 값을 조회
			param.put("uiId", request.getParameter("uiId"));
			param.put("uiPwd", request.getParameter("uiPwd"));
			int result = uiRepo.insertUserInfo(param); //조회한 파라미터 값을 uiRepo에 등록된어있는 insertUserInfo에서 sql쿼리문 실행
			request.setAttribute("msg","회원등록 실패입니다." );  //결과가 실패하면 메세지와 uri 요청
			request.setAttribute("uri", "/user-info/insert"); 
			if(result == 1) { // 결과값이 1 즉 성공 하면 아래 메세지와 uri 요청
				request.setAttribute("msg","회원등록 성공." );
				request.setAttribute("uri", "/user-info/list");
			}
		}else if("update".equals(uri)) {
			Map<String,String> param = new HashMap<>();
			
			param.put("uiName", request.getParameter("uiName"));
			param.put("uiId", request.getParameter("uiId"));
			param.put("uiPwd", request.getParameter("uiPwd"));
			param.put("uiNum", request.getParameter("uiNum"));
			
			int result = uiRepo.updateUserInfo(param);
			request.setAttribute("msg", "회원수정 실패");
			request.setAttribute("uri", "/user-info/update?uiNum=" + request.getParameter("uiNum"));
			if(result == 1) {
				request.setAttribute("msg", "회원수정 성공!");
				request.setAttribute("uri", "/user-info/list");
			}
		}else if ("delete".equals(uri)) {
			String uiNum = request.getParameter("uiNum"); // input값이든 uiNum을 가진 인자값 찾기
			int result = uiRepo.deleteUserInfo(uiNum);
			request.setAttribute("msg", "삭제실패");
			request.setAttribute("uri", "/user-info/view?uiNum="+request.getParameter("uiNum"));
			if(result == 1) {
				request.setAttribute("msg", "삭제성공");
				request.setAttribute("uri", "/user-info/list");
			}
		}
		RequestDispatcher rd = request.getRequestDispatcher(path); // msg출력을 위해서 사용
		rd.forward(request, response);
	}
	
}
