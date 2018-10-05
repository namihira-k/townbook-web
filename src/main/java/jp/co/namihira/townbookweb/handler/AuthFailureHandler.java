package jp.co.namihira.townbookweb.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import jp.co.namihira.townbookweb.service.UrlService;

@Component
public class AuthFailureHandler extends SimpleUrlAuthenticationFailureHandler{

	@Autowired
	private UrlService urlService;
	    
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {
    	urlService.sendRedirect(request, response, "/view/login?error");
    }
}