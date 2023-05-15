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

    public SysSetting4AuditDto getSysSetting4AuditDto() {
        return sysSetting4AuditDto;
    }

    public void setSysSetting4AuditDto(SysSetting4AuditDto sysSetting4AuditDto) {
        this.sysSetting4AuditDto = sysSetting4AuditDto;
    }

    public SysSetting4CommentDto getSysSetting4CommentDto() {
        return sysSetting4CommentDto;
    }

    public void setSysSetting4CommentDto(SysSetting4CommentDto sysSetting4CommentDto) {
        this.sysSetting4CommentDto = sysSetting4CommentDto;
    }

    public SysSetting4EmailDto getSysSetting4EmailDto() {
        return sysSetting4EmailDto;
    }

    public void setSysSetting4EmailDto(SysSetting4EmailDto sysSetting4EmailDto) {
        this.sysSetting4EmailDto = sysSetting4EmailDto;
    }

    public SysSetting4LikeDto getSysSetting4LikeDto() {
        return sysSetting4LikeDto;
    }

    public void setSysSetting4LikeDto(SysSetting4LikeDto sysSetting4LikeDto) {
        this.sysSetting4LikeDto = sysSetting4LikeDto;
    }

    public SysSetting4PostDto getSysSetting4PostDto() {
        return sysSetting4PostDto;
    }

    public void setSysSetting4PostDto(SysSetting4PostDto sysSetting4PostDto) {
        this.sysSetting4PostDto = sysSetting4PostDto;
    }

    public SysSetting4RegisterDto getSysSetting4RegisterDto() {
        return sysSetting4RegisterDto;
    }

    public void setSysSetting4RegisterDto(SysSetting4RegisterDto sysSetting4RegisterDto) {
        this.sysSetting4RegisterDto = sysSetting4RegisterDto;
    }
}
