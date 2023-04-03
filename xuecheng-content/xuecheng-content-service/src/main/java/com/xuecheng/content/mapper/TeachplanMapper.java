package com.xuecheng.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xuecheng.content.model.dto.TeachplanDto;
import com.xuecheng.content.model.po.Teachplan;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 课程计划 Mapper 接口
 * </p>
 *
 * @author itcast
 */
public interface TeachplanMapper extends BaseMapper<Teachplan> {

    /**
     * 查询某课程的课程计划，组成树型结构
     * @param courseId
     * @return
     */
    public List<TeachplanDto> selectTreeNodes(long courseId);

    /**
     * 根据id查看课程计划
     * @param id
     * @return
     */
    public TeachplanDto getTeachplanTreeNodes(long id);

    /**
     * 查找排序对应的课程计划
     * @param parentid
     * @param courseId
     * @param orderby
     * @return
     */
    public Teachplan getTeachplanByOrder(@Param("parentid") long parentid, @Param("courseId") long courseId, @Param("orderby") int orderby);
}
