package com.xuecheng.content.service;

import com.xuecheng.content.model.dto.CourseCategoryTreeDto;

import java.util.List;

/**
 * @author tangcw
 * @Desc 课程分类树形结构查询
 * @date 2023/3/30 9:20
 */
public interface CourseCategoryService {
    public List<CourseCategoryTreeDto> queryTreeNodes(String id);
}
