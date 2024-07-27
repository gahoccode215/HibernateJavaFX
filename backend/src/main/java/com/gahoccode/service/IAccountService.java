package com.gahoccode.service;

import com.gahoccode.pojo.Account;

public interface IAccountService {
	public Account findByUserName(String userName);
}
