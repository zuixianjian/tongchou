package cn.exrick.xboot.your.utils;

import cn.exrick.xboot.core.config.interceptor.WebMvcConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

@Component
public class CopyFileComponent implements ServletContextAware {
    @Autowired
    private WebMvcConfig webMvcConfig;
    /**
     * 复制sqlite数据库文件到./sqlite/financial.db。仅当该文件不存在时复制。
     * 在填充普通bean属性之后但在初始化之前调用
     * 类似于initializingbean的afterpropertiesset或自定义init方法的回调
     */
    @Override
    public void setServletContext(ServletContext servletContext) {
        copyFile("ftl/baojia_nopic.zip");
        copyFile("ftl/document_baojia_nopic.xml");
        copyFile("ftl/baojia.zip");
        copyFile("ftl/document_baojia_haspic.xml");
        copyFile("ftl/order_haspic.zip");
        copyFile("ftl/document_order_haspic.xml");
        copyFile("ftl/order_nopic.zip");
        copyFile("ftl/document_order_nopic.xml");
        copyFile("ftl/recieve_evidence.pdf");
        copyFile("ftl/order_edit.pdf");
        copyFile("ftl/simsun.ttc");
    }

    /**
     * 样例  sqlite/financial.db
     * @param filePath
     */
    private void copyFile(String filePath){
        try {

            //创建目录
            File db = new File(webMvcConfig.getFilePath()+filePath.substring(0,filePath.lastIndexOf("/")+1));
            if (!db.exists() || !db.isDirectory()) {
                db.mkdirs();
            }
            File dbFile = new File(webMvcConfig.getFilePath()+filePath);
            if (!dbFile.exists()) {
                dbFile.createNewFile();
            }
            //读取的关键步骤
            InputStream fis = this.getClass().getClassLoader().getResourceAsStream(filePath);
            FileOutputStream fos = new FileOutputStream(webMvcConfig.getFilePath()+filePath);
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = fis.read(bytes)) != -1) {
                fos.write(bytes, 0, len);
            }
            fis.close();
            fos.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
