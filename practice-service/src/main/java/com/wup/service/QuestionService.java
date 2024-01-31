package com.wup.service;

import com.wup.entity.Question;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wup.utils.Result;

import java.util.List;

/**
* @author brainwu
* @description 针对表【question(科目一试题表)】的数据库操作Service
* @createDate 2024-01-29 17:59:17
*/
public interface QuestionService extends IService<Question> {

    Question getQuestionByOrder(Integer orderId);
    List<Question> getExamPaper(List<Integer> randoms);

    List<String> getRightAnswer();
}
