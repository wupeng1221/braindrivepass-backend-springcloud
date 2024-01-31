package com.wup.service.impl;

import cn.hutool.json.JSONConfig;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.wup.client.UserClient;
import com.wup.entity.AnswerRecord;
import com.wup.entity.DTO.RecAndFavDto;
import com.wup.entity.Favorite;
import com.wup.service.PracticeService;
import com.wup.utils.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * @ClassName : PracticeServiceImpl  //类名
 * @Description :   //描述
 * @Author : brainwu //作者
 * @Date: 2024/1/30  00:07
 */
@Service
public class PracticeServiceImpl implements PracticeService {
    @Resource
    private UserClient userClient;
    @Resource
    private AnswerRecordServiceImpl answerRecordService;
    @Resource
    private FavoriteServiceImpl favoriteService;

    @Override
    public RecAndFavDto readRecAndFav(String username) {
        Long userId = userClient.getIdByUsername(username);
        AnswerRecord answerRecord =
                answerRecordService.getOne(new LambdaQueryWrapper<AnswerRecord>().eq(AnswerRecord::getUserId, userId));
        Favorite favorite = favoriteService.getOne(new LambdaQueryWrapper<Favorite>().eq(Favorite::getUserId, userId));
        String recordJSON = answerRecord.getAnswerRecord();
        String favoriteJSON = favorite.getFavorite();
        ArrayList<String> records = (ArrayList<String>) JSONUtil.toList(recordJSON, String.class);
        ArrayList<Integer> favorites = (ArrayList<Integer>) JSONUtil.toList(favoriteJSON, Integer.class);
        RecAndFavDto recAndFavDto = new RecAndFavDto();
        recAndFavDto.setAnswerRecord(records);
        recAndFavDto.setFavorite(favorites);

        System.out.println(recAndFavDto);
        return recAndFavDto;
    }

    @Override
    @Transactional
    public Result<?> writeRecAndFav(String recAndFavDtoJSON) {
        RecAndFavDto recAndFavDto = JSONUtil.toBean(recAndFavDtoJSON, RecAndFavDto.class);
        String username = recAndFavDto.getUsername();
        Long userId = userClient.getIdByUsername(username);
        String recordJSON = JSONUtil.toJsonStr(recAndFavDto.getAnswerRecord(),new JSONConfig().setIgnoreNullValue(false));
        String favJSON = JSONUtil.toJsonStr(recAndFavDto.getFavorite());

        AnswerRecord answerRecord = answerRecordService.getOne(new LambdaQueryWrapper<AnswerRecord>().eq(AnswerRecord::getUserId, userId));
        if (answerRecord == null) {
            AnswerRecord answerRecord_ = new AnswerRecord();
            answerRecord_.setUserId(userId);
            answerRecord_.setAnswerRecord(recordJSON);
            answerRecord_.setCreateTime(LocalDateTime.now());

            answerRecordService.save(answerRecord_);
        } else {
            answerRecordService.update(new LambdaUpdateWrapper<AnswerRecord>()
                    .eq(AnswerRecord::getUserId, userId)
                    .set(AnswerRecord::getAnswerRecord, recordJSON)
                    .set(AnswerRecord::getUpdateTime, LocalDateTime.now())
            );
        }
        Favorite favorite = favoriteService.getOne(new LambdaQueryWrapper<Favorite>().eq(Favorite::getUserId, userId));
        if (favorite == null) {
            Favorite favorite_ = new Favorite();
            favorite_.setUserId(userId);
            favorite_.setFavorite(favJSON);
            favorite_.setCreateTime(LocalDateTime.now());

            favoriteService.save(favorite_);
        } else {
            favoriteService.update(new LambdaUpdateWrapper<Favorite>()
                    .eq(Favorite::getUserId, userId)
                    .set(Favorite::getFavorite, favJSON)
                    .set(Favorite::getUpdateTime, LocalDateTime.now())
            );
        }

        return Result.success(null);
    }
}
