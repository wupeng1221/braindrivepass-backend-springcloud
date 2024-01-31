package com.wup.controller;

import com.wup.entity.DTO.ScoreDTO;
import com.wup.entity.Question;
import com.wup.entity.Score;
import com.wup.service.impl.ExamServiceImpl;
import com.wup.service.impl.ScoreServiceImpl;
import com.wup.utils.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName : ExamController  //类名
 * @Description :   //描述
 * @Author : brainwu //作者
 * @Date: 2024/1/30  16:43
 */
@RestController
@RequestMapping("/exam")
public class ExamController {
    @Resource
    private ScoreServiceImpl scoreService;
    @Resource
    private ExamServiceImpl examService;
    @PostMapping("/score")
    public Result<Object> writeScore(@RequestBody ScoreDTO scoreDTO){
       scoreService.writeScore(scoreDTO);
       return Result.success(null);

    }
    @GetMapping("/score")
    @ResponseBody
    public Result<List<Score>> readScore(@RequestParam("username") String username){
        return Result.success(scoreService.readScore(username));
    }
    @GetMapping("/paper")
    @ResponseBody
    public Result<List<Question>> getExamPaper(@RequestParam("randoms") String randoms){
        String[] str = randoms.split(",");
        List<Integer> list = Arrays.stream(str).map(s -> Integer.parseInt(s)).collect(Collectors.toList());
        return Result.success(examService.getExamPaper(list));
    }
}
