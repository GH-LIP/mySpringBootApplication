package com.springAOP;

import org.springframework.stereotype.Service;

@Service("accountService")
public class AccountServiceImpl implements IAccountService {

    public void saveAccount() {
//        int a = 1/0;
        System.out.println("保存账号");
    }

    public int updateAccount() {
        System.out.println("更新账号");
        return 1;
    }

    public void deleteAccount(int i) {
        System.out.println("删除账号");
    }

}
