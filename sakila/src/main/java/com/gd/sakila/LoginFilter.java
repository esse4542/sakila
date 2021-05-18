package com.gd.sakila;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebFilter("/admin/*")
public class LoginFilter implements Filter {

   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
      
	   //요청전
      log.debug("filter◆◆◆◆◆◆ LoginFilter 요청 전");
      
      HttpSession session = null;
      if(request instanceof HttpServletRequest) {
         session = ((HttpServletRequest)request).getSession();
      }
      /*
      // 로그인 필터 개발모드에서는 주석처리
      if(session.getAttribute("loginStaff") == null) {
         if(response instanceof HttpServletResponse) {
            ((HttpServletResponse)response).sendRedirect("/");
         }
         return;
      }
      */
      
      //요청후
      chain.doFilter(request, response);
      log.debug("filter◆◆◆◆◆◆ LoginFilter 요청 후");
      
   }
   
}