package com.wdk.test;

import com.wdk.util.MenuModule;
import com.wdk.util.StringAndMapConverter;
import com.wdk.util.UniqueIDGenerator;

import java.util.*;

/**
 * @author : Windok
 * @date: 2023-10-17
 * @Description:
 * @version: 1.0
 */
public class Test01 {

    public static void main(String[] args) {
        Map<String, List<String>> testMap = new HashMap<>();
        testMap.put("辅料", new ArrayList<>(Arrays.asList("冰糖|40克", "酱油|15毫升", "料酒|15毫升")));
        testMap.put("主料", new ArrayList<>(Arrays.asList("排骨|600克")));
        List<String> test01 = testMap.get("主料");
        List<String> test02 = testMap.get("辅料");
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

        System.out.println(MenuModule.DELIMITER_2);
        Map<Integer,String> recipeDescription = new HashMap<>();
        recipeDescription.put(1,"食材洗净、备好。");
        recipeDescription.put(2,"锅内放入花椒、姜片、葱段，注入半锅水烧至温热，即锅底冒小泡，但未沸腾。");
        recipeDescription.put(3,"放入排骨焯烫至水沸，捞出控干水分。");
        recipeDescription.put(4,"炒锅内放入少许油，放冰糖，小火炒至熔化。");
        recipeDescription.put(5,"糖色变浅黄褐色并冒细泡时，迅速放入排骨，炒至均匀上糖色。");
        recipeDescription.put(6,"继续加入酱油、料酒炒匀，加入热水没过排骨1寸，大火烧开，转小火炖40分钟左右。");
        recipeDescription.put(7,"加盐，继续炖至排骨熟烂入味，大火收干汤汁即可。");
        recipeDescription.put(8,"出锅，摆盘。");
        System.out.println(recipeDescription);
        System.out.println(MenuModule.DELIMITER_2);
        String test = recipeDescription.toString();
        Map<Integer,String> testMap01 = StringAndMapConverter.parseRecipeDescription(test);
        System.out.println(testMap01);

    }
}
