package com.wup.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wup.entity.Favorite;
import com.wup.service.FavoriteService;
import com.wup.mapper.FavoriteMapper;
import org.springframework.stereotype.Service;

/**
* @author brainwu
* @description 针对表【favorite(用户收藏的题目的编号)】的数据库操作Service实现
* @createDate 2024-01-29 17:58:27
*/
@Service
public class FavoriteServiceImpl extends ServiceImpl<FavoriteMapper, Favorite>
    implements FavoriteService{

}




