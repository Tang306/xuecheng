package com.xuecheng.media.service.impl;

import com.xuecheng.media.mapper.MediaFilesMapper;
import com.xuecheng.media.mapper.MediaProcessMapper;
import com.xuecheng.media.model.po.MediaProcess;
import com.xuecheng.media.service.MediaFileProcessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tangcw
 * @Desc
 * @date 2023/4/10 10:09
 */
@Service
@Slf4j
public class MediaFileProcessServiceImpl implements MediaFileProcessService {

    @Autowired
    MediaFilesMapper mediaFilesMapper;

    @Autowired
    MediaProcessMapper mediaProcessMapper;

    @Override
    public List<MediaProcess> getMediaProcessList(int shardIndex, int shardTotal, int count) {
        List<MediaProcess> mediaProcesses = mediaProcessMapper.selectListByShardIndex(shardTotal, shardIndex, count);
        return mediaProcesses;
    }

    @Override
    public boolean startTask(long id) {
        int result = mediaProcessMapper.startTask(id);
        return result<=0?false:true;
    }
}
