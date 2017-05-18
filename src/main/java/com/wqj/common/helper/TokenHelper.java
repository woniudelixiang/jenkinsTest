package com.wqj.common.helper;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class TokenHelper {

	//	session中存放token的key
    public static final String TRANSACTION_TOKEN_KEY = "session_token";
    // 请求参数中token的key
    public static final String TOKEN_KEY = "request_token";
    public static String TOKEN_KEY_SUFFIX = "suffer";
	private static final Random RANDOM = new SecureRandom();

	public static String setToken(HttpSession session) {
		return setToken(session, TRANSACTION_TOKEN_KEY);
	}

	public static String setToken(HttpSession session, String tokenName) {
		String token = generateGUID();
		setSessionToken(session, tokenName, token);
		return token;
	}

	public static void setSessionToken(HttpSession session, String tokenName, String token) {
		if (session != null) {
			session.setAttribute(tokenName, token);
		}
	}

	public static String getToken(HttpServletRequest request) {
		return getToken(request, TRANSACTION_TOKEN_KEY);
	}

	public static String getToken(HttpServletRequest request, String tokenName) {
		if (tokenName == null) {
			return null;
		}
		return request.getParameter(tokenName);
	}

	public static boolean validToken(HttpServletRequest request) {
		String token = getToken(request, TOKEN_KEY);
		if (token == null) {
			return false;
		}

		HttpSession session = request.getSession(false);
		if (session == null) {
			return false;
		}

		String tokenSessionName = TRANSACTION_TOKEN_KEY;
		String sessionToken = (String) session.getAttribute(tokenSessionName);

		if (!token.equals(sessionToken)) {
			return false;
		}
		session.removeAttribute(tokenSessionName);
		return true;
	}

	public static String generateGUID() {
		return new BigInteger(165, RANDOM).toString(36).toUpperCase();
	}
}