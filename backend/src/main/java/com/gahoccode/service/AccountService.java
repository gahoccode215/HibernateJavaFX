package com.gahoccode.service;

import com.gahoccode.pojo.Account;
import com.gahoccode.repository.AccountRepository;
import com.gahoccode.repository.IAccountRepository;

public class AccountService implements IAccountService {
	
	private IAccountRepository iAccountRepo = null;
	
	public AccountService(String fileName) {
		iAccountRepo = new AccountRepository(fileName);
	}
	
	@Override
	public Account findByUserName(String userName) {
		return iAccountRepo.findByUserName(userName);
	}

}
