package callingcard.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    //默认append为true
    public static boolean save(String path, String data) {
        return save(path, data, true);
    }

    public static boolean save(String path, String data, boolean append) {
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        try {
            fos = new FileOutputStream(path, append);
            bos = new BufferedOutputStream(fos);
            bos.write(data.getBytes());
            bos.close();
            fos.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    public static List<String> obtain(String path) {
        FileReader fr = null;
        BufferedReader br = null;
        try {
            List<String> dataList = new ArrayList<>();
            fr = new FileReader(path);
            br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                if ("".equals(line)) {
                    continue;
                }
                dataList.add(line);
            }
            return dataList;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (fr != null) {
                try {
                    fr.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static boolean copy(String filename, String path) {
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        try {
            fis = new FileInputStream(filename);
            bis = new BufferedInputStream(fis);
            fos = new FileOutputStream(path);
            bos = new BufferedOutputStream(fos);
            byte[] b = new byte[1024];
            int num;
            while ((num = bis.read(b)) != -1) {
                bos.write(b, 0, num);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("文件异常");
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (bis != null) {
                try {
                    bis.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
}
