/* Copyright 2006-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.codehaus.groovy.grails.plugins.springsecurity;

import java.io.IOException;
import java.lang.reflect.Field;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.AuthenticationException;
import org.springframework.security.BadCredentialsException;
import org.springframework.security.ui.ntlm.NtlmBaseException;
import org.springframework.security.ui.ntlm.NtlmBeginHandshakeException;
import org.springframework.security.ui.ntlm.NtlmProcessingFilter;
import org.springframework.security.ui.ntlm.NtlmProcessingFilterEntryPoint;
import org.springframework.util.ReflectionUtils;

/**
 * @author Martin Vlcek
 * @author <a href='mailto:burt@burtbeckwith.com'>Burt Beckwith</a>
 */
public class GrailsNtlmProcessingFilterEntryPoint extends NtlmProcessingFilterEntryPoint {

	private final String STATE_ATTR;
	private final Integer BEGIN;

	/**
	 * Default constructor.
	 * @throws IllegalAccessException
	 */
	public GrailsNtlmProcessingFilterEntryPoint() throws IllegalAccessException {
		STATE_ATTR = (String)getFieldValue("STATE_ATTR");
		BEGIN = (Integer)getFieldValue("BEGIN");
	}

	private Object getFieldValue(final String name) throws IllegalAccessException {
		Field field = ReflectionUtils.findField(NtlmProcessingFilter.class, name);
		field.setAccessible(true);
		return field.get(null);
	}

	/**
	 * {@inheritDoc}
	 * @see org.springframework.security.ui.ntlm.NtlmProcessingFilterEntryPoint#commence(
	 * 	javax.servlet.ServletRequest, javax.servlet.ServletResponse,
	 * 	org.springframework.security.AuthenticationException)
	 */
	@Override
	public void commence(final ServletRequest req, final ServletResponse res, final AuthenticationException authException) throws IOException, ServletException {

		// start authentication, if necessary and forceIdentification in NtlmProcessingFilter is false
		if (!(authException instanceof NtlmBaseException
				|| authException instanceof BadCredentialsException)) {

			HttpServletRequest request = (HttpServletRequest)req;
			request.getSession().setAttribute(STATE_ATTR, BEGIN);

			HttpServletResponse response = (HttpServletResponse)res;

			response.setHeader("WWW-Authenticate", new NtlmBeginHandshakeException().getMessage());
			response.setHeader("Connection", "Keep-Alive");
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.setContentLength(0);
			response.flushBuffer();
		}
		else {
			super.commence(req, res, authException);
		}
	}
}
