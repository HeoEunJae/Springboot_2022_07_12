package com.mysite.sbb.Answer.service;

import com.mysite.sbb.Answer.dao.AnswerRepository;
import com.mysite.sbb.Answer.domain.Answer;
import com.mysite.sbb.Question.dao.QuestionRepository;
import com.mysite.sbb.Question.domain.Question;
import com.mysite.sbb.User.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private QuestionRepository questionRepository;

    // 답변을 만들려면 질문이랑 내용을 가져와서 답변을 해줘야함
    public void create(Question question, String content, User author){
        Answer answer = new Answer();
        answer.setContent(content);
        answer.setQuestion(question);
        answer.setCreateDate(LocalDateTime.now());
        answer.setAuthor(author);
//        answer.setReplyLike(false);
        question.setViewcnt(question.getViewcnt() - 1);
        this.answerRepository.save(answer);
//        this.questionRepository.save(question);
    }

    public void setLike(Integer answerId) {
        Answer answer = answerRepository.findById(answerId).get();
        if(answer.getReplyLike() == true) {
            answer.setReplyLike(false);
        } else {
            answer.setReplyLike(true);
        }
        this.answerRepository.save(answer);
    }
}
