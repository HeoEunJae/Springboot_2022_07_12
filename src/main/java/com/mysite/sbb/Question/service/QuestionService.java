package com.mysite.sbb.Question.service;

import com.mysite.sbb.Question.dao.QuestionRepository;
import com.mysite.sbb.Question.domain.Question;
import com.mysite.sbb.User.domain.User;
import com.mysite.sbb.uitl.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    // 리포지터리에있는것을 가져오겠다
    public List<Question> getlist(){
        return questionRepository.findAll();
    }

    public Question getQuestion(Integer id){
        Optional<Question> question = this.questionRepository.findById(id);
        if(question.isPresent()){
            return question.get();
        } else {
            throw new DataNotFoundException("question not found");
        }
    }

    public Page<Question> getlist(int page){
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        return this.questionRepository.findAll(pageable);
    }

    public void create(String subject, String content, User user){
        Question question = new Question();
        question.setSubject(subject);
        question.setContent(content);
        question.setAuthor(user);
        question.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(question);
    }

    public void Viewcount(Integer id){
        Question question = getQuestion(id);
        question.setViewcnt(question.getViewcnt() + 1);
        this.questionRepository.save(question);
    }

//    public void likecnt(Integer id){
//        Question question = getQuestion(id);
//        question.setViewcnt(question.getViewcnt() - 1);
//        this.questionRepository.save(question);
//    }
}
