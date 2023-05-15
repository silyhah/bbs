package com.sily.controller;

import com.sily.Utils.StringTools;
import com.sily.annoation.GlobalInterceptor;
import com.sily.common.BusinessException;
import com.sily.common.R;
import com.sily.entity.constants.Constants;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
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


    /**
     * 上传图片文件
     *
     * @param file
     * @return
     */
    @RequestMapping("/file/uploadImage")
    public R uploadImage(MultipartFile file) {
        if (file == null) {
            return R.error("未上传图片文件");
        }
        String filename = file.getOriginalFilename();
        String suffixName = StringTools.getSuffixFile(filename);
        if (!ArrayUtils.contains(Constants.IMAGE_SUFFIX, suffixName)) {
            return R.error("这不是一个图片文件");
        }
        String path = copyFile(file);
        Map<String, String> fileMap = new HashMap<>();
        fileMap.put("fileName", path);
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
            return Constants.FILE_FOLDER_TEMP_2 + "/" + realName;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("上传失败");
        }
    }


    /**
     * 获取图片
     * @param response
     * @param imageFolder
     * @param imageName
     */
    @RequestMapping("/file/getImage/{imageFolder}/{imageName}")
    public void getImage(HttpServletResponse response,
                         @PathVariable String imageFolder,
                         @PathVariable String imageName) {
        readImage(response, imageFolder, imageName);
    }


    @RequestMapping("/file/getAvatar/{userId}")
    public void getAvatar(HttpServletResponse response, @PathVariable String userId) {
        String avatarFolderName = Constants.FILE_FOLDER +"/"+ Constants.FILE_FOLDER_AVATAR;
        String avatarPath = "D:/" + avatarFolderName + userId + Constants.FILE_AVATAR_SUFFIX;
        File avatarFile = new File(avatarFolderName);
        if(!avatarFile.exists()){
            avatarFile.mkdirs();
        }
        File file = new File(avatarPath);
        String imageName = userId + Constants.FILE_AVATAR_SUFFIX;
        if (!file.exists()){
            imageName = Constants.FILE_AVATAR_DEFAULT;
        }
        readImage(response,Constants.FILE_FOLDER_AVATAR, imageName);
    }


    private void readImage(HttpServletResponse response, String imageFolder, String imageName) {

        ServletOutputStream sos = null;
        FileInputStream in = null;
        ByteArrayOutputStream baos = null;

        try {
            if (StringTools.isEmpty(imageFolder) || StringTools.isEmpty(imageName)) {
                return;
            }
            String filePath = "D:/" + Constants.FILE_FOLDER + Constants.FILE_FOLDER_IMAGE + imageFolder + "/" + imageName;
            if (imageFolder.equals(Constants.FILE_FOLDER_TEMP_2) || imageFolder.equals(Constants.FILE_FOLDER_AVATAR)) {
                filePath = "D:/" + Constants.FILE_FOLDER + "/" + imageFolder + "/" + imageName;
            }
            File file = new File(filePath);
            if (!file.exists()) {
                return;
            }
            String suffixImage = StringTools.getSuffixFile(imageName);
            suffixImage = suffixImage.replace(".", "");
            if (!Constants.FILE_FOLDER_AVATAR.equals(imageFolder)) {
                response.setHeader("Cache-Control", "max-age=2592000");
            }
            response.setContentType("image/" + suffixImage);
            in = new FileInputStream(file);
            sos = response.getOutputStream();
            baos = new ByteArrayOutputStream();
            int ch;
            byte[] bytes = new byte[1024];
            while ((ch = in.read(bytes)) != -1) {
                baos.write(bytes, 0, ch);
            }
            sos.write(baos.toByteArray());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sos != null) {
                try {
                    sos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
