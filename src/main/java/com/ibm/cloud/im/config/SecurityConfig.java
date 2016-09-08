//created by zhang jian xin 2016 @IBM
package com.ibm.cloud.im.config;
/*
import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.GenericFilterBean;


@Configuration
//@EnableWebMvcSecurity
//@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	public class ApplicationSecurityTokenFilter extends GenericFilterBean {

	    private final static String AUTHENTICATION_PARAMETER = "authentication";

	    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse/*, FilterChain filterChain) throws IOException, ServletException {
	        if (servletRequest instanceof HttpServletRequest) {
	            // check to see if already authenticated before trying again
	            Authentication existingAuth = SecurityContextHolder.getContext().getAuthentication();
	            if ((existingAuth == null) || !existingAuth.isAuthenticated()) {
	                HttpServletRequest request = (HttpServletRequest)servletRequest;
	                UsernamePasswordAuthenticationToken token = extractToken(request);
	                // dump token into security context (for authentication-provider to pick up)
	                if (token != null) {  // if it exists
	                    SecurityContextHolder.getContext().setAuthentication(token);
	                }
	            }
	        }
	        //filterChain.doFilter(servletRequest,servletResponse);
	    }

	    private UsernamePasswordAuthenticationToken extractToken( HttpServletRequest request ) {
	        UsernamePasswordAuthenticationToken authenticationToken = null;
	        // do what you need to extract the information for a token
	        // in this example we assume a query string that has an authenticate
	        // parameter with a "user:password" string.  A new UsernamePasswordAuthenticationToken
	        // is created and then normal authentication happens using this info.
	        // This is just a sample and I am sure there are more secure ways to do this.
	        if (request.getQueryString() != null) {
	            String[] pairs = request.getQueryString().split("&");
	            for (String pair : pairs) {
	                String[] pairTokens = pair.split("=");
	                if (pairTokens.length == 2) {
	                    if (AUTHENTICATION_PARAMETER.equals(pairTokens[0])) {
	                        String[] tokens = pairTokens[1].split(":");
	                        if (tokens.length == 2) {
	                            authenticationToken = new UsernamePasswordAuthenticationToken(tokens[0], tokens[1]);
	                        }
	                    }
	                }
	            }
	        }
	        return authenticationToken;
	    }

		@Override
		public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
				FilterChain filterChain) throws IOException, ServletException {
			// TODO Auto-generated method stub
			doFilter(servletRequest,servletResponse);
			filterChain.doFilter(servletRequest,servletResponse);
		}
	}
    @Override
    protected void configure(HttpSecurity http) throws Exception {
		http
			//.requestMatchers().antMatchers("/myEndPoint**","/myEndPoint/**").and()
		//.addFilterBefore(new ApplicationSecurityTokenFilter(), UsernamePasswordAuthenticationFilter.class)
			// Configures url based authorization
			//.authorizeRequests()
			//.anyRequest().authenticated()
				// Anyone can access the urls
				//.antMatchers("/index.html").permitAll().
				// The rest of the our application is protected.
				//.antMatchers("/**").authenticated()
			//and()
			.httpBasic()// leave this if you want non web browser clients to connect and add an auth header
			//.and().csrf().disable()
			;
		//TODO 在， IMChannelInterceptor里做认证， 这里开放。 
			
    }

    @Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.inMemoryAuthentication()
				.withUser("testuser").password("testpass").roles("USER").and()
				.withUser("adminuser").password("adminpass").roles("ADMIN","USER");
	}

}
*/