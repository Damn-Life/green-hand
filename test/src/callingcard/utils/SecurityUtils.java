package callingcard.utils;


import java.util.List;
import java.util.Random;

public class SecurityUtils {
    public static boolean judge(String password) {
        //判断密码是否过于简单

        double[] arr = new double[password.length()];
        int count1 = 0;
        for (int i = 0; i < password.length(); i++) {
            arr[i] = password.charAt(i);
        }
        for (int i = 1; i < arr.length - 1; i++) {
            if ((arr[i] == (arr[i - 1] + arr[i + 1]) / 2) || ((arr[i] == arr[i + 1]) && (arr[i - 1] == arr[i]))) {
                count1 = 1;
                break;
            }
        }
        return count1 == 1;
    }

    public static boolean search(String filename, String account) {
        List<String> mess = FileUtils.obtain(filename);
        if (mess == null) {
            System.out.println("请先注册一个账号");
        } else {
            for (String s : mess) {
                if (s.startsWith(account + "-")) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void code() {
        Random r = new Random();
        int random = r.nextInt(10000) + 1000;
        System.out.println("验证码----->" + random);
        int code = CardUtils.inputInt("请正确输入验证码:");
        while (true) {
            if (code == random) {
                break;
            } else {
                code = CardUtils.inputInt("请重新输入验证码:");
            }
        }
    }

    public static String kaisa(String data) {
        char[] str = new char[11];
        for (int i = 0; i < data.length(); i++) {
            char c = data.charAt(i);
            switch (c) {
                case '0':
                    c = '!';
                    break;
                case '1':
                    c = '@';
                    break;
                case '2':
                    c = '#';
                    break;
                case '3':
                    c = '$';
                    break;
                case '4':
                    c = '%';
                    break;
                case '5':
                    c = '^';
                    break;
                case '6':
                    c = '&';
                    break;
                case '7':
                    c = '*';
                    break;
                case '8':
                    c = '(';
                    break;
                case '9':
                    c = ')';
                    break;
            }
            str[i] = c;
        }
        data = String.valueOf(str);
        return data;
    }
}















