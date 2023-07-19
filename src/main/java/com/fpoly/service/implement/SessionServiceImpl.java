package com.fpoly.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpoly.model.TaiKhoan;
import com.fpoly.service.SessionService;

import jakarta.servlet.http.HttpSession;

@Service
public class SessionServiceImpl implements SessionService{
	@Autowired
	HttpSession session;

	@Override
	public void set(String name, Object value) {
		session.setMaxInactiveInterval(18000);
		session.setAttribute(name, value);
	}

	@Override
	public TaiKhoan get(String name) {
		return (TaiKhoan) session.getAttribute(name);
	}

	@Override
	public void remove(String name) {
		session.removeAttribute(name);
		
	}
	
	

}
