package callingcard.service;

import callingcard.utils.CardUtils;
import callingcard.utils.FileUtils;

public class CardMgr {
    private final static String FILE_NAME = "D:/My_JAVA/card_account/card.txt";

    public static void backup() {
        System.out.println("*****************备份名片界面*****************");
        String p = CardUtils.input("请为备份文件命名:");
        String path = "D:/My_JAVA/card_account/" + p + "txt";
        boolean yseOrNo = CardUtils.yesOrNO("是否备份(Y/N)");
        if (!yseOrNo) {
            System.out.println("已取消备份");
            return;
        }
        boolean isCopied = FileUtils.copy(FILE_NAME, path);
        if (isCopied) {
            System.out.println("文件备份成功,备份路径为[" + path + "]");
        } else System.out.println("文件备份失败");
    }

    public static void recovery() {
        System.out.println("*****************恢复名片界面*****************");
        String path = CardUtils.input("请输入恢复文件路径");
        boolean yOn = CardUtils.yesOrNO("是否恢复(Y/N)");
        if (!yOn) {
            System.out.println("已取消恢复");
            return;
        }
        boolean isCopied = FileUtils.copy(FILE_NAME, path);
        if (isCopied) {
            System.out.println("文件恢复成功,恢复路径为[" + path + "]");
        } else System.out.println("文件恢复失败");
    }
}
