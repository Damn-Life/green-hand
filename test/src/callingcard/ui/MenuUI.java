package callingcard.ui;

import callingcard.utils.CardUtils;

public class MenuUI {
    public static void loadingMenu() {
        String[] subtitles = {"This ", "is ", "my ", "first ", "achievement", "!", "!", "!\n"};
        String[] barrages = {"Now ", "check ", "it ", "and ", "give ", "me ", "some ", "suggestions\n"};
        for (String subtitle : subtitles) {
            System.out.print(subtitle);
            try {
                Thread.sleep(400);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for (String barrage : barrages) {
            System.out.print(barrage);
            try {
                Thread.sleep(350);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void mainMenuUI() {
        System.out.println("************欢迎使用名片管理系统************");
        System.out.println("------------  1.Register  --------------");
        System.out.println("------------  2.Login     --------------");
        System.out.println("------------  3.Exit      --------------");
        System.out.println("****************************************");
        System.out.print("Please enter the action you selected:");
    }

    public static int optMenu() {
        System.out.println("****************菜单界面******************");
        System.out.println("------------   1.新增名片   --------------");
        System.out.println("------------   2.修改名片   --------------");
        System.out.println("------------   3.删除名片   --------------");
        System.out.println("------------   4.展示名片   --------------");
        System.out.println("------------   5.合并名片   --------------");
        System.out.println("------------   6.备份名片   --------------");
        System.out.println("------------   7.恢复名片   --------------");
        System.out.println("------------   8.加密名片   --------------");
        System.out.println("------------   9.退出登录   --------------");
        System.out.println("****************************************");
        return CardUtils.inputInt("Please enter the action you selected:");
    }

    public static int chooseMessage(String Case) {
        System.out.println("------------   1.姓名      ");
        System.out.println("------------   2.职务      ");
        System.out.println("------------   3.手机号码   ");
        System.out.println("------------   4.公司名称   ");
        System.out.println("------------   5.公司地址   ");
        return CardUtils.inputInt("请选择您要" + Case + "的信息:");
    }

    public static int chooseM() {
        System.out.println("------------   1.展示所有名片      ");
        System.out.println("------------   2.搜索目标名片      ");
        return CardUtils.inputInt("请选择接下来的操作:");
    }
}
