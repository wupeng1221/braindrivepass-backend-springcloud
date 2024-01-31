package com.wup.client;

import com.wup.FeignInterceptor;
import com.wup.entity.Question;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @ClassName : PracticeClient  //类名
 * @Description :   //描述
 * @Author : brainwu //作者
 * @Date: 2024/1/30  15:36
 */
@FeignClient(
        name = "practice-service",
        configuration = FeignInterceptor.class,// 请求拦截器 （关键代码）
        decode404 = true
)
public interface PracticeClient {
    @GetMapping("/practice/paper")
    List<Question> getExamPaper(@RequestParam("randoms") List<Integer> randoms);


}
