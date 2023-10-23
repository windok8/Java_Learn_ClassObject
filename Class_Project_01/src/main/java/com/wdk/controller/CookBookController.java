package com.wdk.controller;

import com.wdk.pojo.Account;
import com.wdk.pojo.CookBook;
import com.wdk.pojo.RecipeStatus;
import com.wdk.service.CookBookService;
import com.wdk.service.impl.AccountServiceImpl;
import com.wdk.service.impl.CookBookServiceImpl;
import com.wdk.util.CheckInput;
import com.wdk.util.MenuModule;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author : Windok
 * @date: 2023-10-15
 * @Description:
 * @version: 1.0
 */
public class CookBookController {

    AccountServiceImpl accountService = new AccountServiceImpl();
    CookBookServiceImpl cookBookService = new CookBookServiceImpl();
    CheckInput checkInput = new CheckInput();
    MenuModule menuModule = new MenuModule();
    Scanner scanner = new Scanner(System.in);
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");

    public Account showMyCookBook(Account account) {
        if (account.getCookBookIDs() == null) {
            //  通过accountID获取自己的所有菜谱ID
            account = getSelfCookBookIDs(account);
            if (account.getIsFindCookBook()) {
                System.out.println(MenuModule.DELIMITER_3);
                System.out.println("\t\t\t您还没有发布过菜谱哦！");
                System.out.println(MenuModule.DELIMITER_3);
                return null;
            }
        }
        outputRecipe(account);
        return account;
    }

    private void outputRecipe(Account account) {
        //  TODO:   后续优化分页查询的功能
        List<Integer> cookBookIDs = account.getCookBookIDs();
        System.out.println(MenuModule.DELIMITER_3);
        System.out.println("\t\t尊敬的【" + account.getUser().getUserName() + "】您有" + cookBookIDs.size() + "个菜谱");
        for (int i = 0; i < cookBookIDs.size(); i++) {
            System.out.println(
                    recipeToString(account.getCookBooksMap().get(cookBookIDs.get(i)).get(0),
                            account.getCookBooksMap().get(cookBookIDs.get(i)).get(1),
                            account.getCookBooksMap().get(cookBookIDs.get(i)).get(2)));
        }
        System.out.println(MenuModule.DELIMITER_3);
    }

    public void showSelfRecipe(Account account) {
        outputRecipe(account);
    }

    private String recipeToString(String title, String status, String creationDate) {
        // 使用格式化字符串确保对齐
        return String.format("菜谱名称: %-20s 菜谱状态: %-15s 创作时间: %s", title, status, creationDate);
    }

    public void showAllCookBook() {

    }

    public void showAllCookBook_Admin() {

    }

    public void writeCookBook(Integer id) {

    }

    public Account getSelfCookBookIDs(Account account) {
        //  TODO: 优化仅需一条SQL查询语句 就获取全部信息
        Map<Integer, List<String>> cookBookMaps = new HashMap<>();
        Map<Integer, RecipeStatus> cookBookMap = cookBookService.getAccountAndRecipeByuID(account.getId());
        if (cookBookMap == null) {
            account.setIsFindCookBook(true);
            return account;
        }
        List<Integer> cookBoook_IDs = new ArrayList<>();
        List<CookBook> cookBookList = new ArrayList<>();
        Set<Integer> cookBookIDs = cookBookMap.keySet();
        for (Integer cid : cookBookIDs) {
            cookBoook_IDs.add(cid);
            CookBook cookBook = cookBookService.getcookBookById(cid);
            cookBookList.add(cookBook);
            List<String> cookBookInfoStatus = new ArrayList<>();
            cookBookInfoStatus.add(cookBook.getTitle());
            cookBookInfoStatus.add(cookBookMap.get(cid).getDescription());
            cookBookInfoStatus.add(dateFormat.format(cookBook.getCreateTime()));
            cookBookMaps.put(cid, cookBookInfoStatus);
        }
        account.setCookBookIDs(cookBoook_IDs);
        account.setCookBooks(cookBookList);
        account.setCookBooksMap(cookBookMaps);
        account.setIsFindCookBook(true);
        return account;
    }

    public Account reviseRecipe(Account account) throws InterruptedException {
        int recipes = account.getCookBookIDs().size();
        outputRecipe(account);
        //  TODO:   后续优化分页查询的功能
        System.out.println("请输入您要修改第几个菜谱【按 0 退出修改】：");
        int i_Recipe = checkInput.check_Value_Input(scanner.nextLine(), (char) recipes);
        if (i_Recipe == 0) return account;
        reviseRecipe_Info(i_Recipe, account);
        return account;
    }

    private void reviseRecipe_Info(int i_Recipe, Account account) throws InterruptedException {
        while (true) {
            menuModule.waitToMenu("菜谱信息修改");
            menuModule.reviseRecipe_Menu();
            System.out.print("请输入您的选择：");
            int i = checkInput.check_Value_Input(scanner.nextLine(), '3');
            switch (i) {
                case 1: {
                    account = modifyTitle(i_Recipe, account);
                    System.out.println("修改菜谱标题【Over】");
                }
                break;
                case 2: {
                    account = modifyDescription(i_Recipe, account);
                    System.out.println("修改菜谱描述【Over】");
                }
                break;
                case 3: {
                    account = modifyMaterials(i_Recipe, account);
                    System.out.println("修改菜谱材料【Over】");
                }
                break;
                case 0: {
                    return;
                }
            }
        }

    }

    private Account modifyMaterials(int iRecipe, Account account) {
        return null;
    }

    private Account modifyTitle(int iRecipe, Account account) {
        System.out.println("请输入您要修改的标题：");
        String title = scanner.nextLine();
        cookBookService.modifyTitle(account.getCookBookIDs().get(iRecipe), title);

        return account;
    }

    private Account modifyDescription(int iRecipe, Account account) {
        System.out.println("请输入您要修改的内容：");
        String description = scanner.nextLine();
        cookBookService.modifyDescription(account.getCookBookIDs().get(iRecipe), description);
        return account;
    }

    public Account deleteRecipe(Account account) {
        int recipes = account.getCookBookIDs().size();
        outputRecipe(account);
        System.out.println("请输入您要删除第几个菜谱【按 0 退出修改】：");
        int i_Recipe = checkInput.check_Value_Input(scanner.nextLine(), (char) recipes);
        if (i_Recipe == 0) return account;
        cookBookService.deleteRecipe(account.getCookBookIDs().get(i_Recipe));
        account.getCookBookIDs().remove(i_Recipe);
        return account;
    }
}
