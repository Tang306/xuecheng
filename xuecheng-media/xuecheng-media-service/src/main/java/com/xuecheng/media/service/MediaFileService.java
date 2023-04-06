package com.xuecheng.media.service;

import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.media.model.dto.QueryMediaParamsDto;
import com.xuecheng.media.model.po.MediaFiles;

/**
 * @author tangcw
 * @Desc 媒资文件管理业务类
 * @date 2023/4/6 14:53
 */
public interface MediaFileService {

    /**
     * 媒资文件查询方法
     * @param companyId 机构id
     * @param pageParams 分页参数
     * @param queryMediaParamsDto 查询条件
     * @return
     */
    public PageResult<MediaFiles> queryMediaFiels(Long companyId, PageParams pageParams, QueryMediaParamsDto queryMediaParamsDto);
}
