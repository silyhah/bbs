package com.sily.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 系统设置信息
 * </p>
 *
 * @author sily
 * @since 2023-04-24
 */
@TableName("sys_setting")
@Data
public class SysSetting implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    private String code;

    /**
     * 设置信息
     */
    private String jsonContent;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getJsonContent() {
        return jsonContent;
    }

    public void setJsonContent(String jsonContent) {
        this.jsonContent = jsonContent;
    }

    @Override
    public String toString() {
        return "SysSetting{" +
        "code=" + code +
        ", jsonContent=" + jsonContent +
        "}";
    }
}
