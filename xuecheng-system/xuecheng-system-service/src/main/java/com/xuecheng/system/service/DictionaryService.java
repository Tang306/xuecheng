package com.xuecheng.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xuecheng.system.model.po.Dictionary;

import java.util.List;

/**
 * @author tangcw
 * @Desc 数据字典 服务类
 * @date 2023/3/29 14:36
 */
public interface DictionaryService extends IService<Dictionary> {

    /**
     * 查询所有数据字典内容
     * @return
     */
    List<Dictionary> queryAll();

    /**
     * 根据code查询数据字典
     * @param code -- String 数据字典Code
     * @return
     */
    Dictionary getByCode(String code);
}
