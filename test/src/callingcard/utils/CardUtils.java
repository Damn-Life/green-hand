package callingcard.utils;

import java.util.List;
import java.util.Scanner;

public class CardUtils {
    public static String input(String title) {
        Scanner s = new Scanner(System.in);
        System.out.print(title);
        return s.nextLine();
    }

    public static int inputInt(String demo) {
        Scanner s = new Scanner(System.in);
        System.out.print(demo);
        return s.nextInt();
    }

    public static boolean yesOrNO(String saver) {
        while (true) {
            String input = input(saver);
            if (input.equals("y") || input.equals("Y")) {
                return true;
            } else if (input.equals("n") || input.equals("N")) {
                return false;
            } else {
                System.out.println("输入错误,请重新输入");
            }
        }
    }

    public static void changeP(String filename, String account) {
        List<String> list = FileUtils.obtain(filename);
        if (list == null) {
            System.out.println("无信息,请联系管理员修改");
            return;
        }
        StringBuilder saveDate = new StringBuilder();
        String password = CardUtils.input("请输入修改密码(输入’exit‘退出):");
        if (password.equals("exit")) {
            return;
        }
        while (true) {
            boolean judge = SecurityUtils.judge(password);
            if (judge) {
                break;
            } else password = CardUtils.input("密码太简单,请重新输入:");
        }
        for (String str : list) {
            if (str.startsWith(account + "-")) {
                saveDate.append(account).append("---").append(password).append("\n");
            } else {
                saveDate.append(str).append("\n");
            }
        }
        boolean saver = CardUtils.yesOrNO("是否确认修改(Y/N)");
        if (!saver) {
            System.out.println("数据修改失败");
        }
        boolean save = FileUtils.save(filename, saveDate.toString(), false);
        if (save) {
            System.out.println("密码修改成功,请重新登录");
        } else {
            System.out.println("密码修改失败！");
        }

    }

    public static String delByName(String name, List<String> dataList) {
        StringBuilder saveDate = new StringBuilder();
        for (String str : dataList) {
            if (!str.startsWith(name + "-")) {
                saveDate.append(str).append("\n");
            }
        }
        return saveDate.toString();
    }
}
