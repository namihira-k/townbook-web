package jp.co.namihira.townbookweb.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Service;

@Service
public class UrlService {

	private String protocol = "http://";
	
	@Value("${app.domain}")
	private String appDomain = "";

	@Value("${server.servlet.contextPath}")	
	private String contextPath = "";
	
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    public void sendRedirect(HttpServletRequest request, HttpServletResponse response, String relativeUrl) throws IOException {
    	redirectStrategy.sendRedirect(request, response, protocol + appDomain + request.getContextPath() + relativeUrl);
    }
    
    public String getBaseUrl() {
    	return protocol + appDomain + contextPath;
    }
    
    public String getContextPath() {
    	return contextPath;
    }

}
