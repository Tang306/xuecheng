package com.xuecheng.content.controller;

import com.xuecheng.content.model.po.CourseTeacher;
import com.xuecheng.content.service.CourseTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author tangcw
 * @Desc 课程教师接口
 * @date 2023/4/2 20:27
 */
@Api(value = "课程教师编辑接口",tags = "课程教师编辑接口")
@RestController
public class CourseTeacherController {

    @Autowired
    CourseTeacherService courseTeacherService;

    @ApiOperation("查询课程教师信息")
    @ApiImplicitParam(value = "courseId",name = "课程Id",required = true,dataType = "Long",paramType = "path")
    @GetMapping("/courseTeacher/list/{courseId}")
    public List<CourseTeacher> getCourseTeacher(@PathVariable Long courseId){
        return courseTeacherService.queryCourseTeacher(courseId);
    }

    @ApiOperation("添加课程教师")
    @PostMapping("/courseTeacher")
    public void insertCourseTeacher(@RequestBody CourseTeacher courseTeacher){
        courseTeacherService.insertCourseTeacher(courseTeacher);
    }

    @ApiOperation("修改课程教师")
    @PutMapping("/courseTeacher")
    public void updateCourseTeacher(@RequestBody CourseTeacher courseTeacher){
        courseTeacherService.updateCourseTeacher(courseTeacher);
    }

    @ApiOperation("删除课程教师")
    @DeleteMapping("/courseTeacher/course/{courseId}/{id}")
    public void deleteCourseTeacher(@PathVariable("courseId") long courseId, @PathVariable("id") long id){
        courseTeacherService.deleteCourseTeacher(courseId, id);
    }
}
