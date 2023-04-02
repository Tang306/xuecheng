package com.xuecheng.content.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author tangcw
 * @Desc 修改课程dto
 * @date 2023/4/2 12:22
 */
@Data
@ApiModel(value="EditCourseDto", description="修改课程基本信息")
public class EditCourseDto extends AddCourseDto{
    @ApiModelProperty(value = "课程id", required = true)
    private Long id;

}
