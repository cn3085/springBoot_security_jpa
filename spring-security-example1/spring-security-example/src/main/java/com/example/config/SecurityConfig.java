package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
//애너테이션을 사용하겠다는 설정
@EnableGlobalMethodSecurity(prePostEnabled= true)
//ACCESS_OVERRIDE_ORDER 우리의 설정을 먼저 적용시키겠다. 근데 없음.
@Order(SecurityProperties.DEFAULT_FILTER_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService);
		
		//하드코딩. inMemory기 때문에 서버를 껐다 킬때마다 새로 된다
		//auth.inMemoryAuthentication().withUser("test").password("{noop}1111").roles("ADMIN");
	}
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Bean
	//passwordEncoder를 사용하지 않는 설정
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/user/**")
		//이 권한 중에 하나는 있어야 접근할 수 있다
		.access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_SUPER')")
		.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_SUPER')")
		.antMatchers("/super/**").access("hasRole('ROLE_SUPER')")
		.and()
		//로그인 폼, 아웃은 누구나 접근할 수 있다
		.formLogin().permitAll()
		.and()
		//로그아웃하고 나서는 루트로 가라
		.logout().permitAll().logoutSuccessUrl("/");
	}
}
