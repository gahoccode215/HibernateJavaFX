package com.gahoccode.repository;

import com.gahoccode.pojo.Account;

public interface IAccountRepository {
	public Account findByUserName(String userName);
}
