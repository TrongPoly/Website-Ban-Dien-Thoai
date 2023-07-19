package com.fpoly.service;

import com.fpoly.model.TaiKhoan;

public interface SessionService {

	void set(String name, Object value);

	TaiKhoan get(String name);

	void remove(String name);

}
