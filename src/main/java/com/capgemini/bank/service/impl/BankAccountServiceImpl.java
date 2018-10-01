package com.capgemini.bank.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.capgemini.bank.exceptions.AccountIdNotFoundException;
import com.capgemini.bank.repository.BankAccountRepository;
import com.capgemini.bank.service.BankAccountService;

@Service
public class BankAccountServiceImpl implements BankAccountService {

	@Autowired
	private BankAccountRepository bankAccountRepository;

	@Override
	public double getBalance(long accountId) throws AccountIdNotFoundException {
		try {
			return bankAccountRepository.getBalance(accountId);

		} catch (DataAccessException e) {
			// TODO: handle exception
			AccountIdNotFoundException accountIdNotFoundException = new AccountIdNotFoundException(
					"account id not found for bank account");
			accountIdNotFoundException.initCause(e);
			throw accountIdNotFoundException;
		}

	}

	@Override
	public double withdraw(long accountId, double amount) throws AccountIdNotFoundException {

		double balance = bankAccountRepository.getBalance(accountId);
		if (balance + amount >= 0) {
			if (bankAccountRepository.updateBalance(accountId, -1 * amount))
				return bankAccountRepository.getBalance(accountId);
		}
		return bankAccountRepository.getBalance(accountId);

	}

	@Override
	public double deposit(long accountId, double amount) {
		if (bankAccountRepository.updateBalance(accountId, amount))
			return bankAccountRepository.getBalance(accountId);
		return bankAccountRepository.getBalance(accountId);
	}

	@Override
	public boolean fundTransfer(long fromAcc, long toAcc, double amount) {

		if (bankAccountRepository.getBalance(toAcc) < 0) {
			return false;
		}

		else if (bankAccountRepository.updateBalance(fromAcc, -1 * amount)) {
			if (bankAccountRepository.updateBalance(toAcc, amount)) {
				return true;
			}
		}
		return false;
	}
}
