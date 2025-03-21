package com.example.java.util;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class DimenUtils {
//    private final static String rootPath = "C:/Users/Administrator/Desktop/layoutroot/values-{0}x{1}/"; //注意将Administrator替换为实际用户名
    private final static String rootPath = "/Users/xiaoman/Documents/aihandbook/ai/value/values-{0}x{1}/"; //注意将Administrator替换为实际用户名
    private final static float dw = 2560f;   //屏幕横向分为多少等份
    private final static float dh = 1600f;   //屏幕纵向分为多少等份
    private final static String WTemplate = "<dimen name=\"x{0}\">{1}px</dimen>\n"; //xml中横向条目的内容：<dimen name="x1">2.4px</dimen>
    private final static String HTemplate = "<dimen name=\"y{0}\">{1}px</dimen>\n"; //xml中纵向条目的内容：<dimen name="y1">2.56px</dimen>

    public static void main(String[] args) {    //AndroidStudio运行失败的话选择 Run 'DimenUtils.main()' with Coverage
        //720P
        makeString(2560, 1600);
//        makeString(720, 1520);
//        makeString(720, 1600);
//        makeString(720, 1650);
        //1080P
//        makeString(1080, 1920);
//        makeString(1080, 2160);
//        makeString(1080, 2220);
//        makeString(1080, 2340);
//        makeString(1080, 2376);
//        makeString(1080, 2400);
//        makeString(1080, 2412);
        //1.5K
//        makeString(1220, 2712);
//        makeString(1240, 2772);
//        makeString(1260, 2800);
        //2K
//        makeString(1440, 2560);
//        makeString(1440, 2960);
//        makeString(1440, 3040);
//        makeString(1440, 3200);
    }

    public static void makeString(int w, int h) {
        StringBuffer sb = new StringBuffer();
        sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
        sb.append("<resources>");
        float cellw = w / dw;
        for (int i = 1; i < dw; i++) {
            sb.append(WTemplate.replace("{0}", i + "").replace("{1}",
                    change(cellw * i) + ""));
        }
        sb.append(WTemplate.replace("{0}", "300").replace("{1}", w + ""));
        sb.append("</resources>");

        StringBuffer sb2 = new StringBuffer();
        sb2.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
        sb2.append("<resources>");
        float cellh = h / dh;
        for (int i = 1; i < dh; i++) {
            sb2.append(HTemplate.replace("{0}", i + "").replace("{1}",
                    change(cellh * i) + ""));
        }
        sb2.append(HTemplate.replace("{0}", "500").replace("{1}", h + ""));
        sb2.append("</resources>");

        String path = rootPath.replace("{0}", w + "").replace("{1}", h + "");
        File rootFile = new File(path);
        if (!rootFile.exists()) {
            rootFile.mkdirs();
        }
        File layxFile = new File(path + "lay_x.xml");
        File layyFile = new File(path + "lay_y.xml");
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(layxFile));
            pw.print(sb.toString());
            pw.close();
            pw = new PrintWriter(new FileOutputStream(layyFile));
            pw.print(sb2.toString());
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static float change(float a) {
        int temp = (int) (a * 100);
        return temp / 100f;
    }
}