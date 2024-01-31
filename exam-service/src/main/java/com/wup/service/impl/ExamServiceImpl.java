package com.wup.service.impl;

import com.wup.client.PracticeClient;
import com.wup.entity.Question;
import com.wup.service.ExamService;
import com.wup.utils.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName : ExamServiceImpl  //类名
 * @Description :   //描述
 * @Author : brainwu //作者
 * @Date: 2024/1/30  17:42
 */
@Service
public class ExamServiceImpl implements ExamService {
    @Resource
    private PracticeClient practiceClient;
    @Override
    public List<Question> getExamPaper(List<Integer> randoms) {
        return practiceClient.getExamPaper(randoms);
    }
}
