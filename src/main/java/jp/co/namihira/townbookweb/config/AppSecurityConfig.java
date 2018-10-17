package jp.co.namihira.townbookweb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import jp.co.namihira.townbookweb.handler.AuthFailureHandler;
import jp.co.namihira.townbookweb.handler.AuthSuccessHandler;
import jp.co.namihira.townbookweb.service.SecurityService;
import jp.co.namihira.townbookweb.service.UserAuthService;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserAuthService userAuthService;
	
	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private AuthSuccessHandler authSuccessHandler;
	
	@Autowired
	private AuthFailureHandler authFailureHandler;
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/api/**", "/view/**", "/js/**", "/css/**", "/error").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginProcessingUrl("/api/login")
                .loginPage("/view/login")
                .usernameParameter("userId")
                .passwordParameter("password")
                .successHandler(authSuccessHandler)
                .failureHandler(authFailureHandler)
                .and()
            .logout()
	            .logoutRequestMatcher(new AntPathRequestMatcher("/api/logout"))
	            .logoutSuccessUrl("/view/welcome")
	            .deleteCookies("JSESSIONID")
	            .invalidateHttpSession(true)
	            .permitAll();
        
        http.csrf().disable();
    }

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	    auth.userDetailsService(userAuthService)
	        .passwordEncoder(securityService.getPasswordEncoder());
	}    

}
