package com.xuecheng.content.model.dto;

import com.xuecheng.base.execption.ValidationGroups;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author tangcw
 * @Descc 添加课程dto
 * @date 2023/3/31 9:38
 */
@Data
@ApiModel(value = "AddCourseDto", description = "新增课程基本信息")
public class AddCourseDto {

    @NotEmpty(groups = {ValidationGroups.Insert.class},message = "添加课程名称不能为空")
    @NotEmpty(groups = {ValidationGroups.Update.class},message = "修改课程名称不能为空")
    @ApiModelProperty(value = "课程名称", required = true)
    private String name;

    @NotEmpty(groups = {ValidationGroups.Insert.class},message = "添加课程适用人群不能为空")
    @NotEmpty(groups = {ValidationGroups.Update.class},message = "修改课程适用人群不能为空")
    @Size(message = "适用人群内容过少",min = 10)
    private String users;

    @ApiModelProperty(value = "课程标签")
    private String tags;

    @NotEmpty(groups = {ValidationGroups.Insert.class},message = "添加课程分类不能为空")
    @NotEmpty(groups = {ValidationGroups.Update.class},message = "修改课程分类不能为空")
    @ApiModelProperty(value = "大分类", required = true)
    private String mt;

    @NotEmpty(groups = {ValidationGroups.Insert.class},message = "添加课程分类不能为空")
    @NotEmpty(groups = {ValidationGroups.Update.class},message = "修改课程分类不能为空")
    @ApiModelProperty(value = "小分类", required = true)
    private String st;

    @NotEmpty(groups = {ValidationGroups.Insert.class},message = "添加课程等级不能为空")
    @NotEmpty(groups = {ValidationGroups.Update.class},message = "修改课程等级不能为空")
    @ApiModelProperty(value = "课程等级", required = true)
    private String grade;

    @ApiModelProperty(value = "教学模式（普通，录播，直播等）", required = true)
    private String teachmode;

    @ApiModelProperty(value = "课程介绍")
    private String description;

    @ApiModelProperty(value = "课程图片", required = true)
    private String pic;

    @NotEmpty(groups = {ValidationGroups.Insert.class},message = "添加课程收费规则不能为空")
    @NotEmpty(groups = {ValidationGroups.Update.class},message = "修改课程收费规则不能为空")
    @ApiModelProperty(value = "收费规则，对应数据字典", required = true)
    private String charge;

    @ApiModelProperty(value = "价格")
    private Float price;

    @ApiModelProperty(value = "原价")
    private Float originalPrice;

    @ApiModelProperty(value = "qq")
    private String qq;

    @ApiModelProperty(value = "微信")
    private String wechat;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "有效期")
    private Integer validDays;
}
