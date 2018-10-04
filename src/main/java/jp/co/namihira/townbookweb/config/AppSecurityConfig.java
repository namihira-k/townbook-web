package jp.co.namihira.townbookweb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import jp.co.namihira.townbookweb.handler.AuthFailureHandler;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthFailureHandler authFailureHandler;
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/api/**", "/view/**", "/js/**", "/error").permitAll()
//                .antMatchers("/view/eventadd").hasRole("USER")
                .and()
            .formLogin()
                .loginProcessingUrl("/api/login")
                .loginPage("/view/login")
                .failureUrl("/view/login?error")
                .failureHandler(authFailureHandler)
                .and()
            .logout()
                .permitAll();
        
        http.csrf().disable();
    }

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.inMemoryAuthentication()
				.withUser("user").password("password").roles("USER");
	}    

}
