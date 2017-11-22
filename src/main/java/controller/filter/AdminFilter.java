package controller.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import javax.servlet.http.*;

import controller.bean.LoginBean;
import model.User;
import model.utils.IndexPages;


public class AdminFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {		
		HttpSession sesion = ((HttpServletRequest)request).getSession(true);
		if ((sesion.getAttribute("loginBean") != null)){
			User user = ((LoginBean)sesion.getAttribute("loginBean")).getUser();
			if (user != null && user.isAdmin()){
				chain.doFilter(request, response);					
			}else {
				((HttpServletResponse)response).sendRedirect(IndexPages.INDEX);
			}							
		}else {
			((HttpServletResponse)response).sendRedirect(IndexPages.INDEX);
		}
	}
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}
	@Override
	public void destroy() {
	}
}
