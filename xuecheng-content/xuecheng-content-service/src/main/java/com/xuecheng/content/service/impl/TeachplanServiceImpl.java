package com.xuecheng.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xuecheng.base.exception.XueChengException;
import com.xuecheng.content.mapper.TeachplanMapper;
import com.xuecheng.content.model.dto.SaveTeachplanDto;
import com.xuecheng.content.model.dto.TeachplanDto;
import com.xuecheng.content.model.po.Teachplan;
import com.xuecheng.content.service.TeachplanService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author tangcw
 * @Desc
 * @date 2023/4/2 14:41
 */
@Service
public class TeachplanServiceImpl implements TeachplanService {

    @Autowired
    TeachplanMapper teachplanMapper;

    @Override
    public List<TeachplanDto> findTeachplanTree(long courseId) {
        return teachplanMapper.selectTreeNodes(courseId);
    }

    @Transactional
    @Override
    public void saveTeachplan(SaveTeachplanDto saveTeachplanDto) {
        /**
         * 通过课程计划的id判断是新增还是修改
         */
        Long teachplanId = saveTeachplanDto.getId();
        if (teachplanId == null) {
            //新增课程计划
            Teachplan teachplan = new Teachplan();
            BeanUtils.copyProperties(saveTeachplanDto, teachplan);
            //确定排序字段，找到它的同级节点个数，排序字段就是个数+1
            Long parentid = saveTeachplanDto.getParentid();
            Long courseId = saveTeachplanDto.getCourseId();
            int teachplanCount = getTeachplanCount(courseId, parentid);
            teachplan.setOrderby(teachplanCount+1);
            teachplanMapper.insert(teachplan);
        } else {
            //修改课程计划
            Teachplan teachplan = teachplanMapper.selectById(teachplanId);
            //将参数复制到teachplan
            BeanUtils.copyProperties(saveTeachplanDto, teachplan);
            teachplanMapper.updateById(teachplan);
        }
    }

    @Transactional
    @Override
    public void deleteTeachplan(long id) {
        /**
         * 大章节下有小章节时不允许删除大章节
         */
        TeachplanDto teachplanDtos = teachplanMapper.getTeachplanTreeNodes(id);
        if (teachplanDtos.getTeachPlanTreeNodes() == null || teachplanDtos.getTeachPlanTreeNodes().size() == 0) {
            //没有子节点
            Long parentid = teachplanDtos.getParentid();
            Long courseId = teachplanDtos.getCourseId();
            int teanplanorderby = teachplanDtos.getOrderby();
            int teachplanCount = getTeachplanCount(courseId, parentid);
            teachplanMapper.deleteById(id);
            if (teanplanorderby != teachplanCount){
                for (int i = teanplanorderby + 1; i <= teachplanCount; i++) {
                    Teachplan teachplan1 = teachplanMapper.getTeachplanByOrder(parentid, courseId, i);
                    teachplan1.setOrderby(i-1);
                    teachplanMapper.updateById(teachplan1);
                }
            }
        } else {
            XueChengException.cast("课程计划信息还有子级信息，无法操作");
        }
    }

    @Transactional
    @Override
    public void moveDownTeachplan(long id) {
        Teachplan teachplan = teachplanMapper.selectById(id);
        Long parentid = teachplan.getParentid();
        Long courseId = teachplan.getCourseId();
        int teanplanorderby = teachplan.getOrderby();
        /**
         * 判断课程计划的位置，如果已经在最下方了，则不移动
         */
        //确定排序字段，找到它的同级节点个数
        int teachplanCount = getTeachplanCount(courseId, parentid);
        if (teachplanCount != teanplanorderby) {
            //与下面一行课程计划交换
            Teachplan teachplan1 = teachplanMapper.getTeachplanByOrder(parentid, courseId, teanplanorderby+1);
            int temp = teachplan1.getOrderby();
            teachplan1.setOrderby(teachplan.getOrderby());
            teachplan.setOrderby(temp);
            teachplanMapper.updateById(teachplan);
            teachplanMapper.updateById(teachplan1);
        }
    }

    @Override
    public void moveUpTeachplan(long id) {
        Teachplan teachplan = teachplanMapper.selectById(id);
        Long parentid = teachplan.getParentid();
        Long courseId = teachplan.getCourseId();
        int teanplanorderby = teachplan.getOrderby();
        /**
         * 判断课程计划的位置，如果已经在最上方了，则不移动
         */
        if (teanplanorderby != 1) {
            //与上面一行课程计划交换
            Teachplan teachplan2 = teachplanMapper.getTeachplanByOrder(parentid, courseId, teanplanorderby - 1);
            int temp = teachplan2.getOrderby();
            teachplan2.setOrderby(teachplan.getOrderby());
            teachplan.setOrderby(temp);
            teachplanMapper.updateById(teachplan);
            teachplanMapper.updateById(teachplan2);
        }
    }

    /**
     * 获取最新的排序号
     * @param courseId 课程id
     * @param parentId 父课程id
     * @return
     */
    private int getTeachplanCount(long courseId,long parentId){
        LambdaQueryWrapper<Teachplan> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Teachplan::getCourseId,courseId);
        queryWrapper.eq(Teachplan::getParentid,parentId);
        Integer count = teachplanMapper.selectCount(queryWrapper);
        return count;
    }
}
