package com.mysite.sbb.Answer.controller;


import com.mysite.sbb.Answer.form.AnswerForm;
import com.mysite.sbb.Answer.service.AnswerService;
import com.mysite.sbb.Question.domain.Question;
import com.mysite.sbb.Question.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/answer")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    // 답변을 하려면 질문에 대한 데이터가 필요하기때문에 사용한다
    @Autowired
    private QuestionService questionService;

    @PostMapping("/create/{id}")
    public String createAnswer(Model model, @PathVariable("id") Integer id, @Valid AnswerForm answerform, BindingResult bindingResult){
        Question question = this.questionService.getQuestion(id);
        if(bindingResult.hasErrors()){
            model.addAttribute("question", question);
            return "question_detail";
        }
        this.answerService.create(question, answerform.getContent());
        return String.format("redirect:/question/detail/%s", id);
    }

    @PostMapping("/like/{questionId}/{answerId}")
    public String createAnswer(@PathVariable("questionId") Integer questionId, @PathVariable("answerId") Integer answerId) {
//        this.questionService.likecnt(questionId);
        // 클래스 없이도 가능함
        Question question = questionService.getQuestion(questionId);
        question.setViewcnt(question.getViewcnt() - 1);
        this.answerService.setLike(answerId);
        return String.format("redirect:/question/detail/%s", questionId);
    }

}
