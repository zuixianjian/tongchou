package cn.exrick.xboot.your.controller;

import cn.exrick.xboot.core.common.utils.Base64DecodeMultipartFile;
import cn.exrick.xboot.core.common.utils.QiniuUtil;
import cn.exrick.xboot.core.common.utils.ResultUtil;
import cn.exrick.xboot.core.common.vo.Result;
import cn.exrick.xboot.core.config.interceptor.WebMvcConfig;
import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Slf4j
@RestController
@Api(description = "本地文件上传接口")
@RequestMapping("/xboot/mineupload")
@Transactional
public class MineUploadController {
    @Autowired
    private WebMvcConfig webMvcConfig;

    @RequestMapping(value = "/file",method = RequestMethod.POST)
    @ApiOperation(value = "文件上传")
    public Result<Object> upload(@RequestParam(required = false) MultipartFile file,
                                 @RequestParam(required = false) String base64,
                                 HttpServletRequest request) {

        if(StrUtil.isNotBlank(base64)){
            // base64上传
            file = Base64DecodeMultipartFile.base64Convert(base64);
        }
        String result = null;
        String filename = file.getOriginalFilename();
        String filetype = filename.substring(filename.lastIndexOf("."));
        String newfile = UUID.randomUUID()+filetype;
        File file1 = new File(webMvcConfig.getFilePath()+"/"+newfile);
        if(!file1.exists()){
            file1.mkdirs();
        }
        try {
            file.transferTo(file1);
            return ResultUtil.data(newfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResultUtil.error("上传文件失败");
    }

    @RequestMapping(path = "/getPdfFile", method = RequestMethod.GET)
    public void getFile(HttpServletResponse response,String fileName) throws IOException {
        String str =webMvcConfig.getFilePath()+"/"+fileName;
        FileInputStream fileInputStream= new FileInputStream(str);
        response.addHeader("Content-Disposition", "attachment; filename=" + str.substring(str.lastIndexOf("/") + 1));
        response.setContentType("application/pdf");
        byte[] bytes = new byte[fileInputStream.available()];
        int i;
        if ( (i = fileInputStream.read(bytes)) > 0){
            response.getOutputStream().write(bytes, 0, i);
        }
    }
}
