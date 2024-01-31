package com.wup.entity.DTO;

import com.wup.entity.Score;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName : ScoreDTO  //类名
 * @Description :   //描述
 * @Author : brainwu //作者
 * @Date: 2024/1/30  17:19
 */
@Getter
@Setter
public class ScoreDTO extends Score {
    private String username;
}
