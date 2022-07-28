package com.mysite.sbb.Answer.controller;


import com.mysite.sbb.Answer.form.AnswerForm;
import com.mysite.sbb.Answer.service.AnswerService;
import com.mysite.sbb.Question.domain.Question;
import com.mysite.sbb.Question.service.QuestionService;
import com.mysite.sbb.User.Service.UserService;
import com.mysite.sbb.User.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/answer")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    // 답변을 하려면 질문에 대한 데이터가 필요하기때문에 사용한다
    @Autowired
    private QuestionService questionService;
    @Autowired
    private UserService userService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create/{id}")
    public String createAnswer(Model model, @PathVariable("id") Integer id, @Valid AnswerForm answerform, BindingResult bindingResult, Principal principal){
        Question question = this.questionService.getQuestion(id);
        User user = this.userService.getUser(principal.getName());
        if(bindingResult.hasErrors()){
            model.addAttribute("question", question);
            return "question_detail";
        }
        this.answerService.create(question, answerform.getContent(), user);
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
