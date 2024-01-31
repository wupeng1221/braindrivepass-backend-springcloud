package com.wup.service;

import com.wup.entity.DTO.RecAndFavDto;
import com.wup.entity.Question;
import com.wup.utils.Result;

import java.util.ArrayList;

public interface PracticeService {
    RecAndFavDto readRecAndFav(String username);

    Result<?> writeRecAndFav(String recAndFavDtoJSON);
}
