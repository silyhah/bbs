package com.sily.entity.dto;

import lombok.Data;

@Data
public class SysSettingDto {

    private SysSetting4AuditDto sysSetting4AuditDto;

    private SysSetting4CommentDto sysSetting4CommentDto;

    private SysSetting4EmailDto sysSetting4EmailDto;

    private SysSetting4LikeDto sysSetting4LikeDto;

    private SysSetting4PostDto sysSetting4PostDto;

    private SysSetting4RegisterDto sysSetting4RegisterDto;
}
