//package com.jaloway.jalowayheroku;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//  @Override
//  protected void configure(HttpSecurity http) throws Exception {
////	  http.authorizeRequests().anyRequest().permitAll();
//	  System.out.println(http.toString());
////	  http.requiresChannel().requestMatchers(r -> r.getHeader("X-Forwarded-Proto") != null).requiresSecure();
////	  http.requiresChannel().anyRequest().requiresInsecure();
////	  http.sessionManagement().sessionFixation().none();
//  }
//}
//
