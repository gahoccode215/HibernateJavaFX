package com.gahoccode.repository;

import com.gahoccode.dao.AccountDAO;
import com.gahoccode.pojo.Account;

public class AccountRepository implements IAccountRepository{
	private AccountDAO accountDAO = null;
	
	public AccountRepository(String fileConfig) {
		accountDAO = new AccountDAO(fileConfig);
	}

	@Override
	public Account findByUserName(String userName) {
		return accountDAO.findByUserName(userName);
	}
	
	
}
