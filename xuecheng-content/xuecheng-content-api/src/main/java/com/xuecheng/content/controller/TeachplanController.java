package com.xuecheng.content.controller;

import com.xuecheng.content.model.dto.SaveTeachplanDto;
import com.xuecheng.content.model.dto.TeachplanDto;
import com.xuecheng.content.service.TeachplanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author tangcw
 * @Desc 课程计划编辑接口
 * @date 2023/4/2 14:34
 */
@Api(value = "课程计划编辑接口",tags = "课程计划编辑接口")
@RestController
public class TeachplanController {

    @Autowired
    TeachplanService teachplanService;

    @ApiOperation("查询课程计划树形结构")
    @ApiImplicitParam(value = "courseId",name = "课程Id",required = true,dataType = "Long",paramType = "path")
    @GetMapping("/teachplan/{courseId}/tree-nodes")
    public List<TeachplanDto> getTreeNodes(@PathVariable Long courseId){
        return teachplanService.findTeachplanTree(courseId);
    }

    @ApiOperation("课程计划创建或修改")
    @PostMapping("/teachplan")
    public void saveTeachplan(@RequestBody SaveTeachplanDto teachplan){
        teachplanService.saveTeachplan(teachplan);
    }

    @ApiOperation("删除课程计划树形结构")
    @ApiImplicitParam(value = "id",name = "id",required = true,dataType = "Long",paramType = "path")
    @DeleteMapping("/teachplan/{id}")
    public void deleteTeachplan(@PathVariable Long id){
        teachplanService.deleteTeachplan(id);
    }

    @ApiOperation("向下移动课程计划树形结构")
    @GetMapping("/teachplan/movedown/{id}")
    public void moveDownTeachplan(@PathVariable Long id) {
        teachplanService.moveDownTeachplan(id);
    }

    @ApiOperation("向上移动课程计划树形结构")
    @GetMapping("/teachplan/moveup/{id}")
    public void moveUpTeachplan(@PathVariable Long id) {
        teachplanService.moveUpTeachplan(id);
    }
}
