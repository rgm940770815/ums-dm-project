package cn.net.withub.ums.action.xml;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.struts2.convention.annotation.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.*;

/**
 * Created by Administrator on 2016/1/15.
 */
@Controller
@ParentPackage("base")
@Scope("prototype")
@Namespace("/testc")
@Results({
        @Result(name = "json", type = "json", params = {"root", "data"})
})
public class TestcAction {

    ////
    static String url = "155.0.0.105";
    static String port = "22";
    static String userName = "gsxxsj";
    static String password = "Gs123456";

    static String targetPath = "/data/myapp/zxglapp-apache-tomcat-8.5.3/files/";
//    static String targetPath = "G:\\data";

//    static String remotePath = "2020";

//    static String url = "192.0.81.183";
//    static String port = "21";
//    static String userName = "update";
//    static String password = "update";

//    static String remotePath = "99-updateFiles";


    /**
     * 生成xml
     */
    @Action("test1")
    public void test1() {
        System.out.println("in ====== test");

        ftpTest(url, port, userName, password);

        System.out.println("out ====== test");

    }


    //通过Ftp服务器进行上传
    private boolean ftpTest(String url, String port, String username,
                            String password) {
        FTPClient ftpClient = new FTPClient();
        boolean result = false;
        try {
            System.out.println("");
            ftpClient.connect(url, Integer.parseInt(port));
            boolean loginResult = ftpClient.login(username, password);
            int returnCode = ftpClient.getReplyCode();
            if (loginResult && FTPReply.isPositiveCompletion(returnCode)) {// 如果登录成功
//                ftpClient.makeDirectory("2020");
// 设置上传目录

//                ftpClient.changeWorkingDirectory("2020");
//                ftpClient.changeWorkingDirectory("20200101");

//                ftpClient.changeWorkingDirectory("99-updateFiles");

                ftpClient.setBufferSize(1024);
                ftpClient.setControlEncoding("UTF-8");
                ftpClient.enterLocalPassiveMode();
                // 设置文件类型（二进制）
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                ftpClient.setDataTimeout(60 * 60 * 1000);
                ftpClient.setDefaultTimeout(60 * 60 * 1000);
                ftpClient.setConnectTimeout(60 * 60 * 1000);

//                FTPFile[] ftpFiles = ftpClient.listFiles();

                doDownload(ftpClient,"2020/20200101");

                // 2020/20200101"
                cax(ftpClient);

            }

        } catch (Exception e) {
            System.out.println("test 出现错误  ");
            e.printStackTrace();
            throw new RuntimeException("FTP客户端出错！", e);
        } finally {
// IOUtils.closeQuietly(fis);
            try {
                ftpClient.logout();
                ftpClient.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("关闭FTP连接发生异常！", e);
            }
        }
        return result;
    }

    public void cax(FTPClient ftpClient) throws IOException {


        FTPFile[] ftpFiles = ftpClient.listFiles();

        for (FTPFile ftpFile : ftpFiles) {

            if (ftpFile.isDirectory()) {

                System.out.println("isDirectory " + ftpFile.getName());
                ftpClient.changeWorkingDirectory(ftpFile.getName());
                cax(ftpClient);

            } else {

                System.out.println("isFile  " + ftpFile.getName());
                File f = new File(targetPath, ftpFile.getName());
                if (!f.exists()) {
                    f.createNewFile();
                }

                InputStream inputStream = null;
                FileOutputStream outputStream = null;
                try {
                    outputStream = new FileOutputStream(f);

                    inputStream = ftpClient.retrieveFileStream(ftpFile.getName());
                    //创建一个Buffer字符串
                    byte[] buffer = new byte[1024];
                    //每次读取的字符串长度，如果为-1，代表全部读取完毕
                    int len = 0;
                    //使用一个输入流从buffer里把数据读取出来
                    while ((len = inputStream.read(buffer)) != -1) {
                        //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
                        outputStream.write(buffer, 0, len);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                            //返回流才调用completePendingCommand 如果返回null就是没得流如果调用会卡死
                            if (ftpClient != null && ftpClient.isConnected()) {
                                ftpClient.completePendingCommand();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (outputStream != null) {
                        outputStream.close();
                    }
                }

            }


        }


    }

    /**
     * 下载任务，递归调用，循环下载所有目录下的文件
     * @param path
     * @throws IOException
     */
    public static void doDownload(FTPClient ftpClient,String path) throws IOException{
        //创建本地目录
//        makeDirs(path);
        //切换工作目录
        ftpClient.changeWorkingDirectory(new String(path.getBytes(),"ISO-8859-1"));
        //获取目录下的文件列表
        String[] fileNames = ftpClient.listNames();
        //循环下载FTP目录下的文件
        for(String fname:fileNames){
            if(isDirectory(ftpClient,path+"/"+fname)){
                //递归调用
                doDownload(ftpClient,path+"/"+fname);
            }else{
                //下载单个文件
                downloadFile(ftpClient,path+"/"+fname);
            }
        }
    }

    /**
     * 下载单个文件
     * @param dir
     * @throws IOException
     */
    public static void downloadFile(FTPClient ftpClient,String dir) throws IOException{
        File file = new File(targetPath + dir);
        if(!file.exists()){
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        OutputStream os = new FileOutputStream(file);
        ftpClient.setControlEncoding("GBK");
        ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
        //如果文件名中含有中文，retrieveFile文件时会找不到FTP上的文件，导致保存在本地的是一个空文件，所以也要转换一下
        ftpClient.retrieveFile(new String(file.getName().getBytes(),"ISO-8859-1"), os);
        os.close();
    }

    /**
     * 判断给定的路径是文件还是文件夹
     * @param path
     * @return
     * @throws IOException
     */
    public static boolean isDirectory(FTPClient ftpClient,String path) throws IOException{
        //如果是文件，就会返回false
        //如果文件夹或文件名中含有中文，这里要转换一下，不然会返回false
        return ftpClient.changeWorkingDirectory(new String(path.getBytes(),"ISO-8859-1"));
    }



}
