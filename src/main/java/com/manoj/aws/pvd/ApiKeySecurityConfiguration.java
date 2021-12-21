package com.manoj.aws.pvd;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.manoj.aws.pvd.exceptions.ApiClientNotFoundException;
import com.manoj.aws.pvd.model.ApiClient;
import com.manoj.aws.pvd.service.ApiClientService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApiKeySecurityConfiguration extends WebSecurityConfigurerAdapter {

	private static final String apiKeyHeaderName = "x-api-key";

	private ApiKeyAuthenticationManager authenticationManager;

	private final ApiClientService apiClientService;

	/**
	 * @param apiClientService
	 */
	@Autowired
	public ApiKeySecurityConfiguration(ApiClientService apiClientService) {
		this.apiClientService = apiClientService;
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {

		ApiKeyHeaderAuthorizationFilter apiKeyAuthorizationFilter = new ApiKeyHeaderAuthorizationFilter();

		httpSecurity.antMatcher("/**").csrf().disable().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().addFilter(apiKeyAuthorizationFilter)
				.authorizeRequests().anyRequest().authenticated();
	}

	public class ApiKeyHeaderAuthorizationFilter extends UsernamePasswordAuthenticationFilter {

		public ApiKeyHeaderAuthorizationFilter() {
			authenticationManager = new ApiKeyAuthenticationManager();
		}

		@Override
		public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
				throws IOException, ServletException {

			final HttpServletRequest request = (HttpServletRequest) req;
			final HttpServletResponse response = (HttpServletResponse) res;

			String apikey = request.getHeader(apiKeyHeaderName);

			if (apikey == null) {
				chain.doFilter(request, response);
				return;
			}

			try {
				UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(apikey,
						apikey);
				authRequest.setDetails(request);
				Authentication authResult = authenticationManager.authenticate(authRequest);
				SecurityContextHolder.getContext().setAuthentication(authResult);

			} catch (AuthenticationException ae) {
				SecurityContextHolder.clearContext();
				AuthenticationEntryPoint authenticationEntryPoint = new RestAuthenticationEntryPoint();
				authenticationEntryPoint.commence(request, response, ae);
				return;
			}

			chain.doFilter(request, response);

		}

	}

	public class ApiKeyAuthenticationManager implements AuthenticationManager {

		@Override
		public Authentication authenticate(Authentication authentication) throws AuthenticationException {
			String credentials = (String) authentication.getCredentials();
			ApiClient client;
			try {
				client = apiClientService.find(credentials);
			} catch (ApiClientNotFoundException e) {
				throw new BadCredentialsException("Access Denied");
			}

			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(client.getName(),
					client.getApiKey(), getClientAuthorities(client));
			return authToken;
		}

		public List<SimpleGrantedAuthority> getClientAuthorities(ApiClient client) {
			List<SimpleGrantedAuthority> authorities = new ArrayList<>();
			for (String role : client.getAuthorities()) {
				authorities.add(new SimpleGrantedAuthority(role));
			}
			return authorities;
		}

	}

	public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

		@Override
		public void commence(final HttpServletRequest request, final HttpServletResponse response,
				final AuthenticationException authException) throws IOException {
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			response.setCharacterEncoding(StandardCharsets.UTF_8.name());
			response.getWriter().write("{\"error\": \"Access denied\"}");
		}

	}

}
