package com.xuecheng.content.service;

import com.xuecheng.content.model.dto.SaveTeachplanDto;
import com.xuecheng.content.model.dto.TeachplanDto;

import java.util.List;

/**
 * @author tangcw
 * @Desc 课程基本信息管理业务接口
 * @date 2023/4/2 14:40
 */
public interface TeachplanService {

    /**
     * 查询课程计划树型结构
     * @param courseId 课程id
     * @return
     */
    List<TeachplanDto> findTeachplanTree(long courseId);

    /**
     * 保存课程计划
     * @param teachplanDto 课程计划信息
     */
    void saveTeachplan(SaveTeachplanDto teachplanDto);

    /**
     * 删除课程计划
     * @param courseId 课程信息
     */
    void deleteTeachplan(long courseId);
}
