package com.xuecheng.content.service.impl;

import com.xuecheng.base.exception.XueChengException;
import com.xuecheng.content.mapper.CourseTeacherMapper;
import com.xuecheng.content.model.po.CourseTeacher;
import com.xuecheng.content.service.CourseTeacherService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author tangcw
 * @Desc
 * @date 2023/4/2 20:35
 */
@Service
public class CourseTeacherServiceImpl implements CourseTeacherService {

    @Autowired
    CourseTeacherMapper courseTeacherMapper;

    @Transactional
    @Override
    public List<CourseTeacher> queryCourseTeacher(long courseId) {
        return courseTeacherMapper.queryByCourseId(courseId);
    }

    @Transactional
    @Override
    public void insertCourseTeacher(CourseTeacher courseTeacher) {
        try {
            courseTeacherMapper.insert(courseTeacher);
        } catch (Exception e) {
            XueChengException.cast("请勿重复添加");
        }
    }

    @Transactional
    @Override
    public void updateCourseTeacher(CourseTeacher courseTeacher) {
        CourseTeacher courseTeacherExist = courseTeacherMapper.selectById(courseTeacher.getId());
        BeanUtils.copyProperties(courseTeacher, courseTeacherExist);
        courseTeacherMapper.updateById(courseTeacherExist);
    }

    @Transactional
    @Override
    public void deleteCourseTeacher(long courseId, long id) {
        CourseTeacher courseTeacherExist = courseTeacherMapper.selectById(id);
        if (courseTeacherExist == null) {
            XueChengException.cast("不能删除没有的课程教师");
        } else {
            courseTeacherMapper.deleteById(id);
        }
    }
}
