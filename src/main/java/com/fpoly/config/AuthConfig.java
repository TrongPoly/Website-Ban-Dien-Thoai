package com.fpoly.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.fpoly.model.TaiKhoan;
import com.fpoly.service.AccountService;
import com.fpoly.service.SessionService;
//import com.fpoly.service.UserInforService;

@Configuration
@EnableWebSecurity
public class AuthConfig {
	@Autowired
	AccountService accountService;
	@Autowired
	SessionService sessionService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	// authentication
	public UserDetailsService userDetailsService(PasswordEncoder pe) {
		return new UserDetailsService() {

			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				TaiKhoan userInfo = accountService.findByEmail(username);
				String password = userInfo.getMatKhau();
				String roles = userInfo.getPhanQuyen().getId().toString();
				sessionService.set("user", userInfo);
				boolean active = userInfo.getTrangThai();
				return User.withUsername(username).password(pe.encode(password)).roles(roles).accountExpired(!active).build();
			}
		};
	}
	@Bean
	public AuthenticationFailureHandler customAuthenticationFailureHandler() {
	    return new CustomAuthenticationFailureHandler();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf().disable().authorizeHttpRequests().requestMatchers("/admin/**").hasRole("1").and()
				.authorizeHttpRequests().requestMatchers("/cart/**","/invoice/**","/address/**").authenticated().and()
				.authorizeHttpRequests().anyRequest().permitAll().and().exceptionHandling()
				.accessDeniedPage("/auth/access/denied").and().formLogin().loginPage("/auth/login/form")
				.loginProcessingUrl("/login").defaultSuccessUrl("/auth/login/success", true).failureHandler(customAuthenticationFailureHandler()).and().logout().logoutUrl("/logoff").logoutSuccessUrl("/auth/logoff/success").and().build();
	}
	

}
