package com.wup.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.wup.entity.DTO.RecAndFavDto;
import com.wup.entity.Question;
import com.wup.service.impl.PracticeServiceImpl;
import com.wup.service.impl.QuestionServiceImpl;
import com.wup.utils.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName : PracitceController  //类名
 * @Description :   //描述
 * @Author : brainwu //作者
 * @Date: 2024/1/29  18:06
 */
@RestController
@RequestMapping("/practice")
public class PPracticeController {
    @Resource
    private QuestionServiceImpl questionService;
    @Resource
    private PracticeServiceImpl practiceService;
    @GetMapping("/questionByOrderId")
    @ResponseBody
    public Result<Question> getQuestionByOrder(Integer orderId) {
        Question question = questionService.getQuestionByOrder(orderId);
        return question == null ? Result.error("Object Not Found"): Result.success(question);
    }
    @GetMapping("/recAndFav")
    @ResponseBody
    public Result<RecAndFavDto> readRecAndFav(String username) {
        RecAndFavDto recAndFav = practiceService.readRecAndFav(username);
        return recAndFav == null ? Result.error("Object Not Found"): Result.success(recAndFav);
    }
    @PostMapping("/recAndFav")
    public Result<?> writeRecAndFav(@RequestBody String recAndFavJSON){
        System.out.println(recAndFavJSON);
        return practiceService.writeRecAndFav(recAndFavJSON);
    }
    @GetMapping("/paper")
    public List<Question> getExamPaper(@RequestParam("randoms") List<Integer> randoms){
        return questionService.getExamPaper(randoms);
    }
    @GetMapping("/rightAnswer")
    public Result<List<String>> getRightAnswer(){
        List<String> rightAnswer = questionService.getRightAnswer();
        return Result.success(rightAnswer);
    }
}
