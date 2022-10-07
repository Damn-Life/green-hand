package callingcard.service;

import callingcard.ui.MenuUI;
import callingcard.utils.CardUtils;
import callingcard.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;

public class CardMaintain {

    private final static String FILE_NAME = "D:/My_JAVA/card_account/card.txt";
    private final static String FILE_NAME2 = "D:/My_JAVA/card_account/card2.txt";

    public static void add() {
        System.out.println("*****************新增名片界面*****************");
        String name = CardUtils.input("请输入姓名----->>");
        String job = CardUtils.input("请输入职务----->>");
        String phone;
        while (true) {
            phone = CardUtils.input("请输入手机号码----->>");
            if (phone.length() != 11 || (phone.charAt(0) != '1')) {
                System.out.println("手机号码输入疑似错误,请重新输入");
            } else {
                break;
            }
        }
        String company = CardUtils.input("请输入公司名称----->>");
        String address = CardUtils.input("请输入公司地址----->>");
        boolean saver = CardUtils.yesOrNO("是否保存(Y/N):");
        if (!saver) {
            System.out.println("数据保存失败");
            return;
        }
        String data = name + "-" + job + "-" + phone + "-" + company + "-" + address + "0" + "\n";

        boolean save = FileUtils.save(FILE_NAME, data);
        if (save) {
            System.out.println("名片数据保存成功！");
        } else {
            System.out.println("名片数据保存失败！");
        }
    }

    public static void change() {
        String findDate = "", arr = "";
        System.out.println("*****************修改名片界面*****************");
        while (true) {
            String name = CardUtils.input("请输入要修改名片的姓名(按’0‘退出该操作):");
            if (name.equals("0")) {
                System.out.println("已退出修改名片操作");
                break;
            }
            List<String> list = FileUtils.obtain(FILE_NAME);
            if (list == null) {
                System.out.println("系统异常,请重新试试");
                return;
            }

            for (String str : list) {
                //初级
//                String[] split = str.split("-");
//                String account = split[0];
//                if(account.equals(name)){
//                    findDate = str;
//                    break;
//                }
                //进阶
                if (str.startsWith(name + "-")) {
                    findDate = str;
                    break;
                }
            }
            if (findDate.equals("")) {
                System.out.println("未找到名片,请重新查找");
                continue;
            } else {
                System.out.println("您要修改的名片是:" + findDate);
            }
            int chooseMessage = MenuUI.chooseMessage("修改");
            for (String str : list) {
                if (str.equals(findDate)) {
                    String[] split = str.split("-");
                    split[chooseMessage - 1] = CardUtils.input("请输入您要修改的内容:");
                    arr = String.join("-", split);
                    break;
                }
            }
            boolean saver = CardUtils.yesOrNO("是否保存修改(Y/N)");
            if (!saver) {
                System.out.println("数据修改失败");
                return;
            }
            StringBuilder saveDate = new StringBuilder();
            boolean isChanged = false;
            for (String str : list) {
                if (str.equals(findDate) && !isChanged) {
                    saveDate.append(arr).append("\n");
                    isChanged = true;
                } else {
                    saveDate.append(str).append("\n");
                }
            }
            boolean save = FileUtils.save(FILE_NAME, saveDate.toString(), false);
            if (save) {
                System.out.println("名片数据修改成功！");
            } else {
                System.out.println("名片数据修改失败！");
            }
            break;
        }
    }

    public static void delete() {
        System.out.println("*****************删除名片界面*****************");
        while (true) {
            String findDate = "";
            String name = CardUtils.input("请输入要删除名片的姓名(按’0‘退出该操作):");
            if (name.equals("0")) {
                System.out.println("已退出删除名片操作");
                break;
            }
            List<String> list = FileUtils.obtain(FILE_NAME);
            if (list == null) {
                System.out.println("系统异常,请重新试试");
                return;
            }

            for (String str : list) {
                if (str.startsWith(name + "-")) {
                    findDate = str + "、";
                }
            }
            if (findDate.equals("")) {
                System.out.println("未找到名片,请重新查找");
                continue;
            }
            findDate = findDate.substring(0, findDate.length() - 1);
            System.out.println("您要删除的名片是:" + findDate);
            boolean saver = CardUtils.yesOrNO("是否删除(Y/N)");
            if (!saver) {
                System.out.println("数据删除失败");
                return;
            }
            String saveData = CardUtils.delByName(name, list);
            boolean save = FileUtils.save(FILE_NAME, saveData, false);
            if (save) {
                System.out.println("名片数据删除成功！");
            } else {
                System.out.println("名片数据删除失败！");
            }
            boolean isContinue = CardUtils.yesOrNO("是否继续删除(Y/N)");
            if (!isContinue) {
                break;
            }
        }

    }

    public static void lookup() {
        int choice = MenuUI.chooseM();
        switch (choice) {
            case 1:
                show();
                break;
            case 2:
                find();
                break;
        }
    }

    public static void merge() {
        System.out.println("*****************合并名片界面*****************");
        String[] arr = {"姓名", "职务", "手机号码", "公司名称", "公司地址"};
        while (true) {
            StringBuilder saveData = new StringBuilder();
            List<String> list = FileUtils.obtain(FILE_NAME);
            if (list == null) {
                System.out.println("无名片信息");
                return;
            }
            int choice = MenuUI.chooseMessage("合并");

            String name = CardUtils.input("请输入要合并的名片的" + arr[choice - 1] + "(按'0'退出该操作):");
            if (name.equals("0")) {
                System.out.println("已退出合并名片操作");
                return;
            }
            List<String> fdList = new ArrayList<>();
            for (String str : list) {
                String[] split = str.split("-");
                if (split[choice - 1].equals(name)) {
                    fdList.add(str);
                }
            }
            int findSize = fdList.size();
            if (findSize == 0) {
                System.out.println("未找到数据,请重新查找！");
            } else if (findSize == 1) {
                System.out.println("符合条件的数据仅有一条,无需进行合并");
            } else {
                System.out.println("符合条件的名片是:");
                for (String data : fdList) {
                    System.out.println(data);
                    saveData.append(data).append("\n");
                }
                boolean yesOrNO = CardUtils.yesOrNO("是否合并以上数据到另一文件(Y/N)");
                if (yesOrNO) {
                    boolean isSaved = FileUtils.save(FILE_NAME2, saveData.toString(), false);
                    if (isSaved) {
                        boolean yesOrNo = CardUtils.yesOrNO("是否删除原文件所合并的 名片数据(Y/N)");
                        if (yesOrNo) {
                            String delData = CardUtils.delByName(arr[choice - 1], list);
                            FileUtils.save(FILE_NAME, delData, false);
                        }
                        System.out.println("合并成功！！！");
                        break;
                    } else {
                        System.out.println("合并失败,请重新试试");
                    }
                } else {
                    System.out.println("已取消合并");
                    break;
                }
            }
        }
    }

    public static void show() {
        List<String> list = FileUtils.obtain(FILE_NAME);
        if (list == null) {
            System.out.println("无名片信息");
            return;
        }
        System.out.println("*****************名片信息如下*****************");
        for (String str : list) {
            System.out.println(str);
        }
    }

    public static void find() {
        List<String> list = FileUtils.obtain(FILE_NAME);
        while (true) {
            String findDate = "";
            String name = CardUtils.input("请输入要查找名片的姓名(按’0‘退出该操作):");
            if (name.equals("0")) {
                break;
            }
            if (list == null) {
                System.out.println("无名片信息");
                return;
            }
            for (String str : list) {
                if (str.startsWith(name + "-")) {
                    findDate = str + "、";
                }
            }
            if (findDate.equals("")) {
                System.out.println("未找到名片,请重新查找");
                continue;
            }
            findDate = findDate.substring(0, findDate.length() - 1);
            System.out.println("您要搜索的名片是:" + findDate);
            boolean saver = CardUtils.yesOrNO("是否继续搜索(Y/N)");
            if (!saver) {
                System.out.println("已退出搜索");
                break;
            }
        }
    }
}
