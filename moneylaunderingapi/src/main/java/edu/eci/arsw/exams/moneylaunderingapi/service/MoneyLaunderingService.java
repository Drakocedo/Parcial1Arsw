package edu.eci.arsw.exams.moneylaunderingapi.service;

import edu.eci.arsw.exams.moneylaunderingapi.model.SuspectAccount;

import java.util.List;

public interface MoneyLaunderingService {
    void updateAccountStatus(String accountId, SuspectAccount suspectAccount);
    SuspectAccount getAccountStatus(String accountId);
    List<SuspectAccount> getSuspectAccounts();
	void postSuspectAccounts(SuspectAccount cuenta);
}
