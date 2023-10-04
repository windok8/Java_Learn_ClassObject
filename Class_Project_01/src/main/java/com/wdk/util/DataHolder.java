package com.wdk.util;

import com.wdk.pojo.Account;
import com.wdk.pojo.Commit;
import com.wdk.pojo.CookBook;
import com.wdk.pojo.User;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author : Windok
 * @date: 2023-10-03
 * @Description:
 * @version: 1.0
 */
public class DataHolder {

    //  存储所有账户信息
    private static List<Account> accountList;
    //  存储所有用户信息
    private static List<User> userList;
    //  存储所有菜谱信息
    private static List<CookBook> cookBookList;
    //  存储所有评论信息
    private static List<Commit> commitList;
    //  存储所有 userName
    private static List<String> userNameList;
    //  存储所有 uid
    private static List<Integer> uidList;
    //  存储所有 cookID
    private static List<Integer> cookIDList;
    //  存储所有 cid
    private static List<Integer> cidList;

    public static List<String> getUserNameList() {
        return userNameList;
    }

    public static List<Integer> getUidList() {
        return uidList;
    }

    public static List<Integer> getCookIDList() {
        return cookIDList;
    }

    public static List<Integer> getCidList() {
        return cidList;
    }

    public static List<Account> getAccountList() {
        return accountList;
    }


    public void setAccountList(List<Account> accountList) {
        List<String> userDataNameList = new ArrayList<>();
        List<Integer> uidDataList = new ArrayList<>();
        Comparator<Account> ageComparator = (account1, account2) -> Integer.compare(account1.getId(), account2.getId());
        Collections.sort(accountList, ageComparator);
        if(DataHolder.userNameList != null) userDataNameList = DataHolder.userNameList;
        if (DataHolder.uidList != null) uidDataList = DataHolder.uidList;
        userDataNameList.clear();
        //  取出所有用户名
        for (Account account : accountList) {
            userDataNameList.add(account.getUsername());
        }
        DataHolder.userNameList = userDataNameList;
        uidDataList.clear();
        //  取出所有用户ID
        for (Account account : accountList) {
            uidDataList.add(account.getId());
        }
        DataHolder.uidList = uidDataList;
        this.accountList = accountList;
    }

    public void setCookBookList(List<CookBook> cookBookList) {
        Comparator<CookBook> ageComparator = (cookBook1, cookBook2) -> Integer.compare(cookBook1.getCookID(), cookBook2.getCookID());
        Collections.sort(cookBookList, ageComparator);
        List<Integer> cookIDList = DataHolder.getCookIDList();
        //  取出所有菜谱ID
        if (cookIDList != null) cookIDList.clear();
        for (CookBook cookBook : cookBookList) {
            cookIDList.add(cookBook.getCookID());
        }
        DataHolder.cookIDList = cookIDList;
        this.cookBookList = cookBookList;
    }

    public void setCommitList(List<Commit> commitList) {
        Comparator<Commit> ageComparator = (commit1, commit2) -> Integer.compare(commit1.getCid(), commit2.getCid());
        Collections.sort(commitList, ageComparator);
        List<Integer> cidList = DataHolder.getCidList();
        //  取出所有评论ID
        if (cidList != null) cidList.clear();
        for (Commit commit : commitList) {
            cidList.add(commit.getCid());
        }
        DataHolder.cidList = cidList;
        this.commitList = commitList;
    }


}
