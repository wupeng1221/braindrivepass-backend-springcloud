package com.wup.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wup.entity.Question;
import com.wup.service.QuestionService;
import com.wup.mapper.QuestionMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author brainwu
 * @description 针对表【question(科目一试题表)】的数据库操作Service实现
 * @createDate 2024-01-29 17:59:17
 */
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question>
        implements QuestionService {
    @Resource
    private QuestionService questionService;

    @Override
    public Question getQuestionByOrder(Integer orderId) {
        Question one = questionService.getOne(new LambdaQueryWrapper<Question>().eq(Question::getOrderId, orderId));
        return one;
    }

    @Override
    public List<Question> getExamPaper(List<Integer> randoms) {
        List<Question> paper = questionService.list(new LambdaQueryWrapper<Question>().in(Question::getOrderId, randoms));
        return paper;
    }
@Override
    public List<String> getRightAnswer() {
    List<Question> list = questionService.list();
    List<String> rightAnswer = list.stream().map(question -> question.getAnswer()).collect(Collectors.toList());
    return rightAnswer;
}
}




