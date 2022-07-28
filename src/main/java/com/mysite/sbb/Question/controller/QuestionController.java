package com.mysite.sbb.Question.controller;

import com.mysite.sbb.Answer.form.AnswerForm;
import com.mysite.sbb.Question.domain.Question;
import com.mysite.sbb.Question.form.QuestionForm;
import com.mysite.sbb.Question.service.QuestionService;
import com.mysite.sbb.User.Service.UserService;
import com.mysite.sbb.User.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private  QuestionService questionService;
    @Autowired
    private UserService userService;


    @RequestMapping("/list")
    public String showQuestions(Model model, @RequestParam(value = "page", defaultValue = "0") int page){
        Page<Question> paging = this.questionService.getlist(page);
        model.addAttribute("paging", paging);
        return "question_list";
    }

    @RequestMapping("/detail/{id}")
    public String showQuestion(Model model, @PathVariable("id") Integer id, AnswerForm answerForm){
        Question question = this.questionService.getQuestion(id);
        questionService.Viewcount(id);
        model.addAttribute("question", question);
        return "question_detail";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public String questionCreate(QuestionForm questionForm){
        return "question_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult, Principal principal){
        if(bindingResult.hasErrors()){
            return "question_form";
        }
        User user = this.userService.getUser(principal.getName());
        this.questionService.create(questionForm.getSubject(), questionForm.getContent(), user);
        return "redirect:/question/list";
    }
}
