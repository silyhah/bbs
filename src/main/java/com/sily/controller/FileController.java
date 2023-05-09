package com.sily.controller;

import com.sily.Utils.StringTools;
import com.sily.common.BusinessException;
import com.sily.common.R;
import com.sily.entity.constants.Constants;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.HashMap;
import java.util.Map;


/**
 * <p>
 * 文件 前端控制器
 * </p>
 *
 * @author sily
 * @since 2023-05-09
 */
@RestController
public class FileController {


    @RequestMapping("/file/uploadImage")
    public R uploadImage(MultipartFile file) {
        if (file == null) {
            return R.error("未上传文件");
        }
        String filename = file.getOriginalFilename();
        String suffixName = StringTools.getSuffixFile(filename);
        if (ArrayUtils.contains(Constants.IMAGE_SUFFIX, suffixName)) {
            return R.error("这不是一个图片文件");
        }
        String path = copyFile(file);
        Map<String, String> fileMap = new HashMap<>();
        fileMap.put("fileName",path);
        return R.success(fileMap);
    }


    private String copyFile(MultipartFile file) {
        try {
            String originalFilename = file.getOriginalFilename();
            String suffixName = StringTools.getSuffixFile(originalFilename);
            String realName = StringTools.getRandomString(Constants.LENGTH_30) + suffixName;
            String folderPath = "D://" + Constants.FILE_FOLDER + Constants.FILE_FOLDER_TEMP;
            File folder = new File(folderPath);
            if (folder.exists()) {
                folder.mkdirs();
            }
            File file1 = new File(folderPath + "/" + realName);
            file.transferTo(file1);
            return Constants.FILE_FOLDER_TEMP_2 + realName;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("上传失败");
        }
    }

}
