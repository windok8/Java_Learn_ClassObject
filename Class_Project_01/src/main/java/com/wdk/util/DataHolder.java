package com.wdk.util;

import com.wdk.pojo.Account;
import com.wdk.pojo.Commit;
import com.wdk.pojo.CookBook;
import com.wdk.pojo.User;
import lombok.Data;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author : Windok
 * @date: 2023-10-03
 * @Description:
 * @version: 1.0
 */
@Data
public class DataHolder {

    //  存储所有账户信息
    private static List<Account> accountList;
    //  存储所有用户信息
    private static List<User> userList;
    //  存储所有菜谱信息
    private static List<CookBook> cookBookList;
    //  存储所有评论信息
    private static List<Commit> commitList;


    public void setAccountList(List<Account> accountList) {
        Comparator<Account> ageComparator = (account1, account2) -> Integer.compare(account1.getId(), account2.getId());
        Collections.sort(accountList, ageComparator);
        this.accountList = accountList;
    }

    public void setCookBookList(List<CookBook> cookBookList) {
        Comparator<CookBook> ageComparator = (cookBook1, cookBook2) -> Integer.compare(cookBook1.getCookID(), cookBook2.getCookID());
        Collections.sort(cookBookList, ageComparator);
        this.cookBookList = cookBookList;
    }

    public void setCommitList(List<Commit> commitList) {
        Comparator<Commit> ageComparator = (commit1, commit2) -> Integer.compare(commit1.getCid(), commit2.getCid());
        Collections.sort(commitList, ageComparator);
        this.commitList = commitList;
    }


}
