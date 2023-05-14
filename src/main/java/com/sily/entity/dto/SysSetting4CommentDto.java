package com.sily.entity.dto;

import lombok.Data;

@Data
public class SysSetting4CommentDto {

    private Integer commentDayCountThreshold;

    private Integer commentIntegral;

    private Boolean commentOpen;
}
