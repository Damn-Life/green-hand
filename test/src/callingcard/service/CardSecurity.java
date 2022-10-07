package callingcard.service;

import callingcard.utils.CardUtils;
import callingcard.utils.FileUtils;
import callingcard.utils.SecurityUtils;

import java.util.List;

public class CardSecurity {
    private final static String FILE_NAME = "D:/My_JAVA/card_account/card.txt";

    public static void will_encryption() {
        System.out.println("*****************用户加密*****************");
        find();

    }

    public static void find() {
        List<String> list = FileUtils.obtain(FILE_NAME);
        while (true) {
            String findData = "";
            String name = CardUtils.input("请输入要加密名片的姓名(按’0‘退出该操作):");
            if (name.equals("0")) {
                break;
            }
            if (list == null) {
                System.out.println("无该名片信息");
                return;
            }
            for (String str : list) {
                if (str.startsWith(name + "-")) {
                    findData = str + "、";
                }
            }
            if (findData.equals("")) {
                System.out.println("未找到该名片,请重新查找");
                continue;
            }
            findData = findData.substring(0, findData.length() - 1);
            System.out.println("您要加密的名片是:" + findData);
            encryption(findData);
            boolean saver = CardUtils.yesOrNO("是否继续加密(Y/N)");
            if (!saver) {
                System.out.println("已退出加密");
                break;
            }
        }
    }

    public static void encryption(String data) {
        String[] arr = data.split("-");
        data = SecurityUtils.kaisa(arr[2]);
        arr[5] = "1";
    }
}
