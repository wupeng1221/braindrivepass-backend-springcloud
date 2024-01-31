package com.wup.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wup.entity.AnswerRecord;
import com.wup.service.AnswerRecordService;
import com.wup.mapper.AnswerRecordMapper;
import org.springframework.stereotype.Service;

/**
* @author brainwu
* @description 针对表【answer_record(用户顺序答题记录(仅答案列表))】的数据库操作Service实现
* @createDate 2024-01-29 17:57:37
*/
@Service
public class AnswerRecordServiceImpl extends ServiceImpl<AnswerRecordMapper, AnswerRecord>
    implements AnswerRecordService{

}




