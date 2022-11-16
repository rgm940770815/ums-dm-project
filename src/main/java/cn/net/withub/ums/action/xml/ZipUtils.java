package cn.net.withub.ums.action.xml;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Enumeration;

/**
 * 功能：
 * 1 、实现把指定文件夹下的所有文件压缩为指定文件夹下指定 zip 文件
 * 2 、实现把指定文件夹下的 zip 文件解压到指定目录下
 */

public class ZipUtils {


    /**
     * 功能：把 sourceDir 目录下的所有文件进行 zip 格式的压缩，保存为指定 zip 文件
     *
     * @param sourceDir
     * @param zipFile
     */

    public static void zip(String sourceDir, String zipFile) {

        OutputStream os;

        try {

            os = new FileOutputStream(zipFile);

            BufferedOutputStream bos = new BufferedOutputStream(os);

            ZipOutputStream zos = new ZipOutputStream(bos);

            zos.setEncoding("GBK");

            zos.setMethod(ZipOutputStream.DEFLATED);

            File file = new File(sourceDir);

            String basePath = null;

            if (file.isDirectory()) {

                basePath = file.getPath();

            } else {//直接压缩单个文件时，取父目录

                basePath = file.getParent();

            }

            zipFile(file, basePath, zos);

            zos.closeEntry();

            zos.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    /**
     * 功能：执行文件压缩成zip文件
     *
     * @param source
     * @param basePath 待压缩文件根目录
     * @param zos
     */

    private static void zipFile(File source, String basePath,

                                ZipOutputStream zos) {

        File[] files = new File[0];

        if (source.isDirectory()) {

            files = source.listFiles();

        } else {

            files = new File[1];

            files[0] = source;

        }

        String pathName;//存相对路径(相对于待压缩的根目录)

        byte[] buf = new byte[1024];

        int length = 0;

        try {

            for (File file : files) {

                if (file.isDirectory()) {

                    pathName = file.getPath().substring(basePath.length() + 1)

                            + "/";

                    zos.putNextEntry(new ZipEntry(pathName));

                    zipFile(file, basePath, zos);

                } else {

                    pathName = file.getPath().substring(basePath.length() + 1);

                    InputStream is = new FileInputStream(file);

                    BufferedInputStream bis = new BufferedInputStream(is);

                    zos.putNextEntry(new ZipEntry(pathName));

                    while ((length = bis.read(buf)) > 0) {

                        zos.write(buf, 0, length);

                    }

                    is.close();

                }

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    /**
     * 功能：解压 zip 文件，只能解压 zip 文件
     *
     * @param zipfile
     * @param destDir
     */

    public static void unZip(String zipfile, String destDir) {

        destDir = destDir.endsWith("\\") ? destDir : destDir + "\\";

        byte b[] = new byte[1024];

        int length;

        ZipFile zipFile;

        try {

            zipFile = new ZipFile(new File(zipfile));

            Enumeration enumeration = zipFile.getEntries();

            ZipEntry zipEntry = null;

            while (enumeration.hasMoreElements()) {

                zipEntry = (ZipEntry) enumeration.nextElement();

                File loadFile = new File(destDir + zipEntry.getName());

                if (zipEntry.isDirectory()) {

                    loadFile.mkdirs();

                } else {

                    if (!loadFile.getParentFile().exists()) {

                        loadFile.getParentFile().mkdirs();

                    }

                    OutputStream outputStream = new FileOutputStream(loadFile);

                    InputStream inputStream = zipFile.getInputStream(zipEntry);

                    while ((length = inputStream.read(b)) > 0)

                        outputStream.write(b, 0, length);

                }

            }

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    //压缩图片设置大小
    public static byte[] zipImageFile( float quality, BufferedImage image) {

        // 写死了 宽 4 高 5的压缩比例
        if (image == null) {
            return null;
        }

        ByteArrayOutputStream byteArrayOutputStream = null;
        byte[] bytes = null;
        try {

            // 得到源图宽
            int old_w = image.getWidth();
            // 得到源图长
            int old_h = image.getHeight();
            // 新图的宽
            int new_w = 0;
            // 新图的长
            int new_h = 0;
            // 根据图片尺寸压缩比得到新图的尺寸
            if (old_w > old_h) {
                // 图片要缩放的比例
                new_w = old_w;
                new_h =  Math.round(new_w * ((float) 5 / 4));
            } else {
                new_h = old_h;
                new_w =  Math.round(new_h * ((float) 4 / 5));
            }

            /** 宽,高设定 */
            BufferedImage tag = new BufferedImage(new_w, new_h,
                    BufferedImage.TYPE_INT_RGB);
            tag.getGraphics().drawImage(image, 0, 0, new_w, new_h, null);

            // 开始开始，写入byte[]
            byteArrayOutputStream = new ByteArrayOutputStream(); // 取得内存输出流

            // 设置编码器
            JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(tag);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(
                    byteArrayOutputStream, jep);

            /** 压缩质量 */
            jep.setQuality(quality, false);
            encoder.encode(tag);

            bytes = byteArrayOutputStream.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bytes;
    }


//    if (image == null) {
//        return null;
//    }
//    byte[] bytes = null;
//    // 开始开始，写入byte[]
//    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(); // 取得内存输出流
//    //        image.set
//    // 设置压缩参数
//    JPEGEncodeParam param = JPEGCodec.getDefaultJPEGEncodeParam(image);
////        param.set
//    param.setQuality(quality, false);
//    // 设置编码器
//    JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(
//            byteArrayOutputStream, param);
//    encoder.encode(image);
//
//    bytes = byteArrayOutputStream.toByteArray();
//
//    if (byteArrayOutputStream != null) {
//        try {
//            byteArrayOutputStream.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

}
