package com.xuecheng.content.service;

import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.model.dto.AddCourseDto;
import com.xuecheng.content.model.dto.CourseBaseInfoDto;
import com.xuecheng.content.model.dto.EditCourseDto;
import com.xuecheng.content.model.dto.QueryCourseParamsDto;
import com.xuecheng.content.model.po.CourseBase;

/**
 * @author tangcw
 * @Desc 课程基本信息管理业务接口
 * @date 2023/3/28 11:35
 */
public interface CourseBaseInfoService {

    /**
     * 课程查询接口
     * @param pageParams 分页参数
     * @param queryCourseParamsDto 查询条件
     * @return
     */
    PageResult<CourseBase> queryCourseBaseList(PageParams pageParams, QueryCourseParamsDto queryCourseParamsDto);

    /**
     * 添加课程基本信息
     * @param companyId 教学机构id
     * @param addCourseDto 课程基本信息
     * @return
     */
    CourseBaseInfoDto createCourseBase(Long companyId, AddCourseDto addCourseDto);

    /**
     * 根据id查询课程基本信息
     * @param courseId 课程id
     * @return
     */
    CourseBaseInfoDto getCourseBaseInfo(long courseId);

    /**
     * 修改课程信息
     * @param companyId 机构id
     * @param editCourseDto 课程信息
     * @return
     */
    CourseBaseInfoDto updateCourseBase(long companyId, EditCourseDto editCourseDto);

    /**
     * 删除课程信息
     * @param companyId 机构id
     * @param courseId 课程id
     * @return
     */
    void deleteCourseBase(long companyId, long courseId);
}
