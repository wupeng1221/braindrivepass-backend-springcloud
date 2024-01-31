package com.wup.service;

import com.wup.entity.Question;
import com.wup.utils.Result;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ExamService {
    List<Question> getExamPaper(@RequestParam("randoms") List<Integer> randoms);

}
