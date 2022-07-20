package com.mysite.sbb.Question.controller;

import com.mysite.sbb.Answer.form.AnswerForm;
import com.mysite.sbb.Question.domain.Question;
import com.mysite.sbb.Question.form.QuestionForm;
import com.mysite.sbb.Question.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private  QuestionService questionService;

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

    @GetMapping("/create")
    public String questionCreate(QuestionForm questionForm){
        return "question_form";
    }

    @PostMapping("/create")
    public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "question_form";
        }
        this.questionService.create(questionForm.getSubject(), questionForm.getContent());
        return "redirect:/question/list";
    }
}
