package callingcard.service;

import callingcard.utils.CardUtils;
import callingcard.utils.FileUtils;
import callingcard.utils.SecurityUtils;

import java.util.List;


public class Account {

    private final static String FILE_NAME = "D:/My_JAVA/card_account/user.txt";

    public static void reg() {
        //界面
        System.out.println("*****************用户注册*****************");
        String password1, password2, account;
        while (true) {
            account = CardUtils.input("请您命名用户名----->>");
            boolean search = SecurityUtils.search(FILE_NAME, account);
            if (search) {
                break;
            } else {
                System.out.println("该用户名已存在,请重新命名");
            }
        }
        while (true) {
            password1 = CardUtils.input("请设置登录密码----->>");
            password2 = CardUtils.input("请再次输入密码----->>");
            boolean judge = SecurityUtils.judgePw(password1);
            if (password1.length() < 6 || judge) {
                System.out.println("密码太简单,请重新输入");
            } else if (password1.equals(password2)) {
                break;
            } else {
                System.out.println("两次密码输入不一致,请重新输入");
            }
        }
        //是否保存成功
        String data = account + '-' + '-' + '-' + password2 + '\n';
        boolean save = FileUtils.save(FILE_NAME, data);
        if (save) {
            System.out.println("注册成功！！！");
        } else {
            System.out.println("注册失败！！！");
        }
    }

    public static boolean log() {
        System.out.println("*****************用户登录*****************");
        String account, password;
        boolean flag = true;
        while (flag) {
            int count = 1;
            account = CardUtils.input("请输入用户名----->>");
            password = CardUtils.input("请输入密码(输入'0000'可修改密码) ----->>");
            if (password.equals("0000")) {
                SecurityUtils.code();
                CardUtils.changeP(FILE_NAME, account);
                return false;
            }
            String s = account + '-' + '-' + '-' + password;
            List<String> list = FileUtils.obtain(FILE_NAME);
            if (list == null) {
                System.out.println("登录失败！");
                return false;
            }
            for (String data : list) {
                if (data.equals(s)) {
                    System.out.println("登录成功！！");
                    flag = false;
                    break;
                } else {
                    count++;
                }
            }
            if (count > list.size()) {
                System.out.println("*用户名或密码出错*");
            }
        }
        return true;
    }

}

