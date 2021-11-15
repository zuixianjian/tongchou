package cn.exrick.xboot.your.utils;


import cn.exrick.xboot.core.config.interceptor.WebMvcConfig;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.*;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * @Author 黎永鹏
 * @Description FreeMarker 生成 .docx文档
 * @Date 2021-09-15 15:45
 */
@Slf4j
@Component
public class FreeMarkUtils {
    @Autowired
    private WebMvcConfig webMvcConfig;


    private static final String DOCUMENT_XML = "document_baojia_nopic.xml";
    private static final String DOCX_PATH = "/%s报价单%s.docx";
    private static final String NAME_BAOJIA_ZIP = "/baojia_nopic.zip";

    private static final String NAME_BAOJIA_ZIP_haspic = "/baojia.zip";
    private static final String DOCUMENT_XML_haspic = "document_baojia_haspic.xml";

    private static final String DOCUMENT_FULLORDER_XML = "document_order_haspic.xml";
    private static final String DOCX_FULLORDER_PATH = "/%s统筹单%s.docx";
    private static final String NAME_FULLORDER_ZIP = "/order_haspic.zip";

    private static final String DOCUMENT_FULLORDER_NOPIC_XML = "document_order_nopic.xml";
    private static final String NAME_FULLORDER_NOPIC_ZIP = "/order_nopic.zip";

    private static final String PDF_ORDEREDIT_PATH = "%s批单%s.pdf";
    private static final String TEMPLATE_PIDAN = "ftl/order_edit.pdf";

    private static final String FONT_NAME = "ftl/simsun.ttc,1";
    public Configuration getConfiguration() throws IOException {
        //创建配置实例
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_28);
        //设置编码
        configuration.setDefaultEncoding("utf-8");
        //换成自己对应的目录
        configuration.setDirectoryForTemplateLoading(new File(webMvcConfig.getFilePath()+"ftl"));
        return configuration;
    }





    /**
     * 获取模板字符串输入流
     *
     * @param dataMap      参数
     * @param templateName 模板名称
     * @return
     */
    public  ByteArrayInputStream getFreemarkerContentInputStream(Map<String, Object> dataMap, String templateName) {
        ByteArrayInputStream byteArrayInputStream = null;
        try {
            //获取模板
            Template template = getConfiguration().getTemplate(templateName);
            StringWriter strWriter = new StringWriter();
            //生成文件
            template.process(dataMap, strWriter);
            //这里一定要设置utf-8编码 否则导出的word中中文会是乱码
            byteArrayInputStream = new ByteArrayInputStream(strWriter.toString().getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            log.error("模板生成错误！");
        }
        return byteArrayInputStream;
    }

    public  String getFreemarkerContentInputStreamForBaojia(Map<String, Object> dataMap, String orderId) throws Exception {
        File docxHasPic = createDocx(dataMap, orderId, DOCUMENT_XML_haspic, DOCX_PATH, NAME_BAOJIA_ZIP_haspic);
        File docxNoPic = createDocxNoPic(dataMap, orderId, DOCUMENT_XML, DOCX_PATH, NAME_BAOJIA_ZIP);
        return  docxHasPic.getName()+","+docxNoPic.getName();
    }

    public  String getFreemarkerContentInputStreamForInsurance(Map<String, Object> dataMap, String orderId) throws Exception {
        File docxHasPic = createDocx(dataMap, orderId, DOCUMENT_FULLORDER_XML, DOCX_FULLORDER_PATH, NAME_FULLORDER_ZIP);
        File docxNoPic = createDocxNoPic(dataMap, orderId, DOCUMENT_FULLORDER_NOPIC_XML, DOCX_FULLORDER_PATH, NAME_FULLORDER_NOPIC_ZIP);
        return  docxHasPic.getName()+","+docxNoPic.getName();
    }
    /**
     * FreeMarker生成报价单.docx文档，临时保存到本地
     *
     * @param dataMap 数据
     */
    public  File createDocx(Map<String, Object> dataMap, String orderId,String xmlTemplate,String docxzipPath,String ZipName) throws Exception {
        ZipOutputStream zipOutputStream = null;
        OutputStream outputStream = null;

        try {
            //内容模板
            ByteArrayInputStream documentInput =getFreemarkerContentInputStream(dataMap, xmlTemplate);

            String staticPath = webMvcConfig.getFilePath()+"ftl";
            //docx文件输出
            File file = new File(webMvcConfig.getFilePath() + String.format(docxzipPath, orderId, System.currentTimeMillis()));
            outputStream = new FileOutputStream(file);

            //读取模板zip包，等待覆写
            File docxFile = new File(staticPath + ZipName);
            ZipFile zipFile = new ZipFile(docxFile);
            Enumeration<? extends ZipEntry> zipEntry = zipFile.entries();
            zipOutputStream = new ZipOutputStream(outputStream);
            //开始覆盖文档------------------
            int len = -1;
            byte[] buffer = new byte[1024];
            while (zipEntry.hasMoreElements()) {
                ZipEntry next = zipEntry.nextElement();
                InputStream inputStream = zipFile.getInputStream(next);
//                if (!next.toString().contains("media")) {
                    zipOutputStream.putNextEntry(new ZipEntry(next.getName()));
                    //如果是zip包中word/document.xml由我们覆盖输入
                    if ("word/document.xml".equals(next.getName())) {
                        if (documentInput != null) {
                            while ((len = documentInput.read(buffer)) != -1) {
                                zipOutputStream.write(buffer, 0, len);
                            }
                            documentInput.close();
                        }
                    } else {
                        while ((len = inputStream.read(buffer)) != -1) {
                            zipOutputStream.write(buffer, 0, len);
                        }
                        inputStream.close();
                    }
//                }
            }

            return file;
        } catch (Exception e) {
            log.error("生成doc失败", e);
            throw e;
        } finally {
            if (zipOutputStream != null) {
                try {
                    zipOutputStream.close();
                } catch (IOException e) {
                    log.error("生成word1-io异常");

                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    log.error("生成word2-io异常");
                }
            }
        }
    }


    /**
     * FreeMarker生成报价单.docx文档，临时保存到本地
     *
     * @param dataMap 数据
     */
    public  File createDocxNoPic(Map<String, Object> dataMap, String orderId,String xmlTemplate,String docxzipPath,String ZipName) throws Exception {
        ZipOutputStream zipOutputStream = null;
        OutputStream outputStream = null;

        try {
            //内容模板
            ByteArrayInputStream documentInput =getFreemarkerContentInputStream(dataMap, xmlTemplate);

            String staticPath = webMvcConfig.getFilePath()+"ftl";
            //docx文件输出
            File file = new File(webMvcConfig.getFilePath() + String.format(docxzipPath, orderId, System.currentTimeMillis()));
            outputStream = new FileOutputStream(file);

            //读取模板zip包，等待覆写
            File docxFile = new File(staticPath + ZipName);
            ZipFile zipFile = new ZipFile(docxFile);
            Enumeration<? extends ZipEntry> zipEntry = zipFile.entries();
            zipOutputStream = new ZipOutputStream(outputStream);
            //开始覆盖文档------------------
            int len = -1;
            byte[] buffer = new byte[1024];
            while (zipEntry.hasMoreElements()) {
                ZipEntry next = zipEntry.nextElement();
                InputStream inputStream = zipFile.getInputStream(next);
                if (!next.toString().contains("media")) {
                zipOutputStream.putNextEntry(new ZipEntry(next.getName()));
                //如果是zip包中word/document.xml由我们覆盖输入
                if ("word/document.xml".equals(next.getName())) {
                    if (documentInput != null) {
                        while ((len = documentInput.read(buffer)) != -1) {
                            zipOutputStream.write(buffer, 0, len);
                        }
                        documentInput.close();
                    }
                } else {
                    while ((len = inputStream.read(buffer)) != -1) {
                        zipOutputStream.write(buffer, 0, len);
                    }
                    inputStream.close();
                }
                }
            }

            return file;
        } catch (Exception e) {
            log.error("生成doc失败", e);
            throw e;
        } finally {
            if (zipOutputStream != null) {
                try {
                    zipOutputStream.close();
                } catch (IOException e) {
                    log.error("生成word1-io异常");

                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    log.error("生成word2-io异常");
                }
            }
        }
    }

    //批单pdf
    public  String generateOrderEditPdf(Map<String, String> dataMap, String orderId) throws Exception {
        String fileName = generatePdfStream(TEMPLATE_PIDAN, PDF_ORDEREDIT_PATH, FONT_NAME, dataMap, orderId);
        return  fileName;
    }
    //生成pdf
    public  String generatePdfStream(String template,String pdfOutputPath, String fontName, Map<String, String> data,String outFileName) throws  Exception{
        String staticPath = webMvcConfig.getFilePath();
        PdfReader reader = new PdfReader(staticPath+template);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        /* 将要生成的目标PDF文件名称 */
        PdfStamper ps = new PdfStamper(reader, bos);
        PdfContentByte under = ps.getUnderContent(1);
        /* 使用中文字体 */
        BaseFont bf = BaseFont.createFont(fontName, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        ArrayList<BaseFont> fontList = new ArrayList<BaseFont>();
        fontList.add(bf);
        /* 取出报表模板中的所有字段 */
        AcroFields fields = ps.getAcroFields();
        fields.setSubstitutionFonts(fontList);
        fillData(fields, data);
        /* 必须要调用这个，否则文档不会生成的 */
        ps.setFormFlattening(true);
        ps.close();
        String fileName=String.format(pdfOutputPath, outFileName, System.currentTimeMillis());
        OutputStream fos = new FileOutputStream(webMvcConfig.getFilePath()+"/" + fileName);
        fos.write(bos.toByteArray());
        fos.flush();
        fos.close();
        bos.close();
        return fileName;
    }

    public  void fillData(AcroFields fields, Map<String, String> data)
            throws IOException, DocumentException {
        for (String key : data.keySet()) {
            String value = data.get(key);
            fields.setField(key, value); // 为字段赋值,注意字段名称是区分大小写
        }
    }
}
