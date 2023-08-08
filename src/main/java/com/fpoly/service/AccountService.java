package com.fpoly.service;

import com.fpoly.model.TaiKhoan;

public interface AccountService {

	TaiKhoan findByEmail(String username);

}
