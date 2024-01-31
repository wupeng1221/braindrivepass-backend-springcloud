package com.wup.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wup.client.UserClient;
import com.wup.entity.DTO.ScoreDTO;
import com.wup.entity.Score;
import com.wup.service.ScoreService;
import com.wup.mapper.ScoreMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author brainwu
* @description 针对表【score(用户模拟考试得分记录)】的数据库操作Service实现
* @createDate 2024-01-30 16:46:43
*/
@Service
public class ScoreServiceImpl extends ServiceImpl<ScoreMapper, Score>
    implements ScoreService{
    @Resource
    private ScoreService scoreService;
    @Resource
    private UserClient userClient;
    @Override
    public void writeScore(ScoreDTO scoreDTO) {
        Long userId = userClient.getIdByUsername(scoreDTO.getUsername());
        Score score_ = new Score();
        score_.setUserId(userId);
        score_.setScore(scoreDTO.getScore());
        scoreService.save(score_);
    }
@Override
    public List<Score> readScore(String username) {
    Long userId = userClient.getIdByUsername(username);
    List<Score> scores = scoreService.list(new LambdaQueryWrapper<Score>().eq(Score::getUserId, userId));
    return scores;
}
}




