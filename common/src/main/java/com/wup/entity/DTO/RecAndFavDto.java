package com.wup.entity.DTO;

import lombok.Data;

import java.util.ArrayList;

/**
 * @ClassName : RecAndFavDto  //类名
 * @Description :   //描述
 * @Author : brainwu //作者
 * @Date: 2024/1/30  01:16
 */
@Data
public class RecAndFavDto {
    private String username;
    private ArrayList<String> answerRecord;
    private ArrayList<Integer> favorite;
}
