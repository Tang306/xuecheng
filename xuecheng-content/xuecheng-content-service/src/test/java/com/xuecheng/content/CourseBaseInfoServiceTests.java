package com.xuecheng.content;

import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.model.dto.AddCourseDto;
import com.xuecheng.content.model.dto.CourseBaseInfoDto;
import com.xuecheng.content.model.dto.QueryCourseParamsDto;
import com.xuecheng.content.model.po.CourseBase;
import com.xuecheng.content.service.CourseBaseInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author tangcw
 * @Desc
 * @date 2023/3/30 10:28
 */
@SpringBootTest
public class CourseBaseInfoServiceTests {

    @Autowired
    CourseBaseInfoService courseBaseInfoService;

    @Test
    public void testCourseBaseInfoService() {

        /**
         * 查询基本信息
         */
        //查询条件
        QueryCourseParamsDto courseParamsDto = new QueryCourseParamsDto();
        courseParamsDto.setCourseName("java");//课程名称查询条件
        courseParamsDto.setAuditStatus("202004");//202004表示课程审核通过
        //分页参数对象
        PageParams pageParams = new PageParams();
        pageParams.setPageNo(2L);
        pageParams.setPageSize(2L);

        PageResult<CourseBase> courseBasePageResult = courseBaseInfoService.queryCourseBaseList(pageParams, courseParamsDto);
        System.out.println(courseBasePageResult);

        /**
         * 新增基本信息
         */
        //新增信息
        Long companyId = 1232141425L;

        AddCourseDto addCourseDto = new AddCourseDto();
        addCourseDto.setCharge("201001");
        addCourseDto.setPrice(101F);
        addCourseDto.setOriginalPrice(100F);
        addCourseDto.setQq("22333");
        addCourseDto.setWechat("223344");
        addCourseDto.setPhone("13333333");
        addCourseDto.setValidDays(365);
        addCourseDto.setMt("1-1");
        addCourseDto.setSt("1-1-1");
        addCourseDto.setName("测试课程103");
        addCourseDto.setPic("fdsf");
        addCourseDto.setTeachmode("200002");
        addCourseDto.setUsers("初级人员");
        addCourseDto.setTags("tagstags");
        addCourseDto.setGrade("204001");
        addCourseDto.setDescription("描述描述");

        CourseBaseInfoDto courseBase = courseBaseInfoService.createCourseBase(companyId, addCourseDto);
        System.out.println(courseBase);
    }
}