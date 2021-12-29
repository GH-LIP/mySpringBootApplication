package com.test.springAOP;

public interface IAccountService {

    /**
     * 模拟保存账号（无返回值无参）
     */
    public void saveAccount();

    /**
     * 模拟更新账号（有返回值无参）
     */
    public int updateAccount();

    /**
     * 模拟删除账号（无返回值有参）
     */
    public void deleteAccount(int i);

}
