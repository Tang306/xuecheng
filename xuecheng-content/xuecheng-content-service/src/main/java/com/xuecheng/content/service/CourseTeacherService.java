package com.xuecheng.content.service;

import com.xuecheng.content.model.po.CourseTeacher;

import java.util.List;

/**
 * @author tangcw
 * @Desc 课程教师
 * @date 2023/4/2 20:28
 */
public interface CourseTeacherService {

    /**
     * 根据课程id查询课程教师
     * @param courseId 课程id
     * @return
     */
    public List<CourseTeacher> queryCourseTeacher(long courseId);

    /**
     * 添加课程教师信息
     * @param courseTeacher 课程教师信息
     */
    void insertCourseTeacher(CourseTeacher courseTeacher);

    /**
     * 编辑课程教师信息
     * @param courseTeacher 课程教师信息
     */
    void updateCourseTeacher(CourseTeacher courseTeacher);

    /**
     * 删除课程教师信息
     * @param courseId 课程id
     * @param id 教师id
     */
    void deleteCourseTeacher(long courseId, long id);
}
