package com.wdk.controller;

import com.alibaba.excel.converters.string.StringErrorConverter;
import com.wdk.pojo.Account;
import com.wdk.pojo.CookBook;
import com.wdk.pojo.RecipeStatus;
import com.wdk.service.CookBookService;
import com.wdk.service.impl.AccountServiceImpl;
import com.wdk.service.impl.CookBookServiceImpl;
import com.wdk.util.CheckInput;
import com.wdk.util.MenuModule;
import com.wdk.util.StringAndMapConverter;

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
        System.out.println(MenuModule.DELIMITER_3);
        System.out.println("\t\t\t\t【所有菜谱】");
        List<CookBook> recipeList = cookBookService.getRecipe();
        for (CookBook cookBook : recipeList) {
            System.out.println(String.format("菜谱名称: %-20s 菜谱作者: %-15s 点赞人数: %-5s 更新时间: %s", cookBook.getTitle(), cookBook.getAuthorName(),cookBook.getLikes(), dateFormat.format(cookBook.getUpdateTime())));
        }
        System.out.println(MenuModule.DELIMITER_3);
        //  TODO:   后续优化分页查询的功能
        System.out.println("请输入您要查看的菜谱【按 0 退出查看】：");
        int i_Recipe = checkInput.check_Value_Input_int(scanner.nextLine(), recipeList.size());
        if (i_Recipe == 0) return;
        showRecipe(recipeList.get(i_Recipe - 1));

    }

    private void showRecipe(CookBook cookBook) {
        System.out.println("查看详细的菜谱信息！");
        System.out.println(MenuModule.DELIMITER_3);
        System.out.println("\t\t\t\t【菜谱信息】");
        System.out.println("菜谱名称: " + cookBook.getTitle());
        System.out.println("菜谱作者: " + cookBook.getAuthorName());
        System.out.println("作者邮箱: " + cookBook.getUserEmail());
        System.out.println(MenuModule.DELIMITER_1);
        System.out.println("\t\t\t\t【所需材料】");
        Map<String, List<String>> materialsMap = cookBook.getMaterials();
        List<String> main_Ingredients = (List<String>) materialsMap.get("主料");
        List<String> auxiliary_Ingredients = (List<String>) materialsMap.get("辅料");
        System.out.println("\t\t\t\t【主料】");
        for (int i = 0; i < main_Ingredients.size(); i++) {
            String[] split = main_Ingredients.get(i).split("\\|");
            System.out.println("\t\t" + split[0] + "\t\t\t\t" + split[1]);
        }
        System.out.println("\t\t\t\t【辅料】");
        for (int i = 0; i < auxiliary_Ingredients.size(); i++) {
            String[] split = auxiliary_Ingredients.get(i).split("\\|");
            System.out.println("\t\t" + split[0] + "\t\t\t\t" + split[1]);
        }
        System.out.println(MenuModule.DELIMITER_1);
        System.out.println("\t\t\t\t【制作步骤】");
        Map<Integer, String> recipeDescription = cookBook.getDescription();
        for (int i = 1; i <= recipeDescription.size(); i++) {
            System.out.println("\t\t\t\t【步骤 - " + i + " - 】");
            System.out.println(recipeDescription.get(i));
        }
        System.out.println(MenuModule.DELIMITER_3);
    }

    public void showAllCookBook_Admin() {
        System.out.println(MenuModule.DELIMITER_3);
        System.out.println("\t\t\t\t【所有菜谱】");
        List<CookBook> recipeList = cookBookService.getRecipe_Admin();
        for (CookBook cookBook : recipeList) {
            System.out.println(String.format("菜谱名称: %-20s " +
                    "菜谱作者: %-15s " +
                    "发布时间: %-15s " +
                    "更新时间: %-15s " +
                    "文章状态: %-15s",
                    cookBook.getTitle(),
                    cookBook.getAuthorRealName(),
                    dateFormat.format(cookBook.getCreateTime()),
                    dateFormat.format(cookBook.getUpdateTime()),
                    cookBook.getStatus().getDescription()));
        }
        System.out.println(MenuModule.DELIMITER_3);

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
            cookBookInfoStatus.add(dateFormat.format(cookBook.getUpdateTime()));
            cookBookMaps.put(cid, cookBookInfoStatus);
        }
        account.setCookBookIDs(cookBoook_IDs);
        account.setCookBooks(cookBookList);
        account.setCookBooksMap(cookBookMaps);
        account.setIsFindCookBook(false);
        return account;
    }

    public Account reviseRecipe(Account account) throws InterruptedException {
        int recipes = account.getCookBookIDs().size();
        outputRecipe(account);
        //  TODO:   后续优化分页查询的功能
        System.out.println("请输入您要修改第几个菜谱【按 0 退出修改】：");
        int i_Recipe = checkInput.check_Value_Input_int(scanner.nextLine(), recipes);
        if (i_Recipe == 0) return account;
        account = reviseRecipe_Info(i_Recipe, account);
        return account;
    }

    private Account reviseRecipe_Info(int i_Recipe, Account account) throws InterruptedException {
        i_Recipe--;
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
                    // TODO: 后续优化
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
                    return account;
                }
            }
        }

    }

    private Account modifyRecipe(int iRecipe, Account account) {
        account.getCookBooksMap().get(account.getCookBookIDs().get(iRecipe)).set(1, RecipeStatus.PENDING.getDescription());
        account.getCookBooksMap().get(account.getCookBookIDs().get(iRecipe)).set(2, dateFormat.format(new Date()));
        return account;
    }

    private Account modifyMaterials(int iRecipe, Account account) throws InterruptedException {
        boolean isModify = true;
        String tirle = account.getCookBooks().get(iRecipe - 1).getTitle();
        System.out.println("您正在修改菜谱【 " + tirle + " 】的材料");
        showRecipeMaterials(account.getCookBooks().get(iRecipe).getMaterials());
        List<String> main_Ingredients = (List<String>) account.getCookBooks().get(iRecipe).getMaterials().get("主料");
        List<String> auxiliary_Ingredients = (List<String>) account.getCookBooks().get(iRecipe).getMaterials().get("辅料");
        while (true) {
            menuModule.waitToMenu("菜谱材料修改");
            menuModule.reviseRecipeMaterials_Menu();
            System.out.print("请输入您的选择：");
            int i = checkInput.check_Value_Input(scanner.nextLine(), '2');
            switch (i) {
                case 1: {
                    main_Ingredients = reviceMain_ingredients(main_Ingredients);
                    System.out.println("修改菜谱主料【Over】");
                }
                break;
                case 2: {
                    auxiliary_Ingredients = reviceAuxiliary_ingredients(auxiliary_Ingredients);
                    System.out.println("修改菜谱辅料【Over】");
                }
                break;
                case 0: {
                    cookBookService.updateMaterials(account.getCookBookIDs().get(iRecipe), main_Ingredients, auxiliary_Ingredients);
                    return account;
                }
            }
        }
    }

    private List<String> reviceAuxiliary_ingredients(List<String> auxiliary_Ingredients) {
        while (true) {
            showIngredients(auxiliary_Ingredients, "辅料");
            menuModule.reviseIngredients_Menu("辅料");
            System.out.print("请输入您的选择：");
            int i = checkInput.check_Value_Input(scanner.nextLine(), '3');
            switch (i) {
                case 1:
                    auxiliary_Ingredients = reviceIngredients_Info(auxiliary_Ingredients,"辅料");
                    break;
                case 2:
                    auxiliary_Ingredients = addIngredients_Info(auxiliary_Ingredients,"辅料");
                    break;
                case 3:
                    auxiliary_Ingredients = deleteIngredients_Info(auxiliary_Ingredients,"辅料");
                    break;
                case 0:
                    return auxiliary_Ingredients;
            }
        }

    }

    private List<String> deleteIngredients_Info(List<String> ingredients, String Ingredients_name) {
        List<String> auxiliary_Names = new ArrayList<>();
        for (String auxiliaryName : ingredients) {
            auxiliary_Names.add(auxiliaryName.split("\\|")[0]);
        }
        System.out.println("请输入您要删除的"+Ingredients_name+"名称：");
        String name = scanner.nextLine();
        while (!auxiliary_Names.contains(name)) {
            System.out.println("您输入的"+Ingredients_name+"名称不存在，请重新输入：");
            name = scanner.nextLine();
        }
        int index = 0;
        for (String s : ingredients) {
            if (s.equals(name)) {
                break;
            }
            index++;
        }
        System.out.println(MenuModule.DELIMITER_1);
        System.out.println("\t\t\t\t【您要删除的信息】");
        System.out.println(ingredients.get(index));
        System.out.print("您确定要删除吗？【Y/N】");
        if("N".equals(scanner.nextLine())){
            return ingredients;
        }
        ingredients.remove(index);
        return ingredients;
    }

    private List<String> addIngredients_Info(List<String> ingredients,String Ingredients_name) {
        List<String> auxiliary_Names = new ArrayList<>();
        for (String auxiliaryName : ingredients) {
            auxiliary_Names.add(auxiliaryName.split("\\|")[0]);
        }
        System.out.println("请输入"+Ingredients_name+"名称：");
        String ingredientsName = scanner.nextLine();
        while (auxiliary_Names.contains(ingredientsName)) {
            System.out.println("您输入的"+Ingredients_name+"名称已存在，请重新输入：");
            ingredientsName = scanner.nextLine();
        }
        System.out.println("请输入"+Ingredients_name+"计量：");
        String ingredientsAmount = scanner.nextLine();
        System.out.println(MenuModule.DELIMITER_1);
        System.out.println("\t\t\t\t【您添加的信息】");
        System.out.println(ingredientsName + " | " + ingredientsAmount);
        System.out.print("您确定要添加吗？【Y/N】");
        if("N".equals(scanner.nextLine())){
            return ingredients;
        }
        ingredients.add(ingredientsName + "|" + ingredientsAmount);
        return ingredients;
    }

    private List<String> reviceIngredients_Info(List<String> ingredients,String Ingredients_name) {
        List<String> auxiliary_Names = new ArrayList<>();
        List<String> auxiliary_Amount = new ArrayList<>();
        for (String auxiliaryName : ingredients) {
            auxiliary_Names.add(auxiliaryName.split("\\|")[0]);
            auxiliary_Amount.add(auxiliaryName.split("\\|")[1]);
        }

        System.out.println("请输入您要修改的"+Ingredients_name+"名称：");
        String name = scanner.nextLine();
        while (!auxiliary_Names.contains(name)) {
            System.out.println("您输入的"+Ingredients_name+"名称不存在，请重新输入：");
            name = scanner.nextLine();
        }
        int index = 0;
        for (String s : ingredients) {
            if (s.equals(name)) {
                break;
            }
            index++;
        }
        System.out.print("请输入"+Ingredients_name+"名称：");
        String auxiliaryName = scanner.nextLine();
        System.out.println("请输入"+Ingredients_name+"计量：");
        String auxiliaryAmount = scanner.nextLine();
        System.out.println(MenuModule.DELIMITER_1);
        System.out.println("\t\t\t\t【修改前】");
        System.out.println(ingredients.get(index));
        System.out.println("\t\t\t\t【修改后】");
        System.out.println(auxiliaryName + " | " + auxiliaryAmount);
        System.out.print("您确定要修改吗？【Y/N】");
        if("N".equals(scanner.nextLine())){
            return ingredients;
        }
        String newAuxiliary = auxiliaryName + "|" + auxiliaryAmount;
        ingredients.set(index,newAuxiliary);
        return ingredients;
    }


    private List<String> reviceMain_ingredients(List<String> main_Ingredients) {
        while (true) {
            showIngredients(main_Ingredients, "主料");
            menuModule.reviseIngredients_Menu("主料");
            System.out.print("请输入您的选择：");
            int i = checkInput.check_Value_Input(scanner.nextLine(), '3');
            switch (i) {
                case 1:
                    main_Ingredients = reviceIngredients_Info(main_Ingredients,"主料");
                    break;
                case 0:
                    return main_Ingredients;
            }
        }
    }

    private void showIngredients(List<String> ingredients, String name) {
        System.out.println(MenuModule.DELIMITER_3);
        System.out.println("\t\t\t\t【" + name + "】");
        for (int i = 0; i < ingredients.size(); i++) {
            String[] split = ingredients.get(i).split("\\|");
            System.out.println("\t\t" + split[0] + "\t\t\t\t" + split[1]);
        }
        System.out.println(MenuModule.DELIMITER_3);
    }

    private void showRecipeMaterials(Map<String, List<String>> materialsMap) {
        List<String> test01 = materialsMap.get("主料");
        List<String> test02 = materialsMap.get("辅料");
        System.out.println(MenuModule.DELIMITER_3);
        System.out.println("\t\t\t\t【主料】");
        for (int i = 0; i < test01.size(); i++) {
            String[] split = test01.get(i).split("\\|");
            System.out.println("\t\t" + split[0] + "\t\t\t\t" + split[1]);
        }
        System.out.println("\t\t\t\t【辅料】");
        for (int i = 0; i < test02.size(); i++) {
            String[] split = test02.get(i).split("\\|");
            System.out.println("\t\t" + split[0] + "\t\t\t\t" + split[1]);
        }
        System.out.println(MenuModule.DELIMITER_3);
    }

    private Account modifyTitle(int iRecipe, Account account) {
        System.out.println("请输入您要修改的标题：");
        String title = scanner.nextLine();
        cookBookService.modifyTitle(account.getCookBookIDs().get(iRecipe), title);
        account.getCookBooksMap().get(account.getCookBookIDs().get(iRecipe)).set(0, title);
        account = modifyRecipe(iRecipe, account);
        return account;
    }

    private Account modifyDescription(int iRecipe, Account account) {
        //  TODO:  后续优化 菜谱内容的修改 --> 操作步骤的修改
        System.out.println("请输入您要修改的内容：");
        String description = scanner.nextLine();
        cookBookService.modifyDescription(account.getCookBookIDs().get(iRecipe), description);
        //  TODO:   修改菜谱描述


        account = modifyRecipe(iRecipe, account);
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
        account.getCookBooks().remove(i_Recipe);
        account.getCookBooksMap().remove(i_Recipe);
        return account;
    }
}
