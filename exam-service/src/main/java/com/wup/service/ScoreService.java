package com.wup.service;

import com.wup.entity.DTO.ScoreDTO;
import com.wup.entity.Score;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author brainwu
* @description 针对表【score(用户模拟考试得分记录)】的数据库操作Service
* @createDate 2024-01-30 16:46:43
*/
public interface ScoreService extends IService<Score> {

    void writeScore(ScoreDTO scoreDTO);

    List<Score> readScore(String username);
}
