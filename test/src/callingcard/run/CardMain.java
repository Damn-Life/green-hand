package callingcard.run;


import callingcard.service.Account;
import callingcard.service.CardMaintain;
import callingcard.service.CardMgr;
import callingcard.service.CardSecurity;
import callingcard.ui.MenuUI;

import java.util.Scanner;

/**
 * 卡片管理主体
 *
 * @author DamnLife
 * @date 2022/08/31
 */
public class CardMain {
    public static void main(String[] args) {
        MenuUI.loadingMenu();
        while (true) {
            boolean flag1 = true;
            MenuUI.mainMenuUI();
            Scanner sc = new Scanner(System.in);
            while (flag1) {
                int choice1 = sc.nextInt();
                switch (choice1) {
                    case 1:
                        Account.reg();
                        flag1 = false;
                        break;
                    case 2:
                        boolean login = Account.log();
                        if (login) {
                            while (loginJudOpt()) {
                                System.out.println();
                            }
                        }
                        flag1 = false;
                        break;
                    case 3:
                        System.out.println("\n您已退出,感谢您的使用");
                        System.exit(0);
                    default:
                        System.out.print("无该选项,请重新输入:");
                }
            }
        }
    }

    public static boolean loginJudOpt() {
        int choice2 = MenuUI.optMenu();
        switch (choice2) {
            case 1:
                CardMaintain.add();
                break;
            case 2:
                CardMaintain.change();
                break;
            case 3:
                CardMaintain.delete();
                break;
            case 4:
                CardMaintain.lookup();
                break;
            case 5:
                CardMaintain.merge();
                break;
            case 6:
                CardMgr.backup();
                break;
            case 7:
                CardMgr.recovery();
                break;
            case 8:
                CardSecurity.will_encryption();
                break;
            case 9:
                System.out.println("您已退出登录\n");
                return false;
        }
        return true;
    }

}
