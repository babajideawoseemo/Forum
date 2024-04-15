package com.project.forum.controller;

import com.project.forum.DTO.LearnerDTO;
import com.project.forum.DTO.LoginDTO;
import com.project.forum.domain.Learner;
import com.project.forum.domain.Post;
import com.project.forum.payload.response.LoginText;
import com.project.forum.service.LearnerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("api/v1/learner")
public class LearnerController {


    private final LearnerService learnerService;

    @PostMapping("/save")
    public Learner saveLearner(@RequestBody LearnerDTO learnerDTO) {
        return learnerService.addLearner(learnerDTO);
    }

    @GetMapping
    public List<Learner> getLearners() {
        return learnerService.getLearners();
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> loginLearner(@RequestBody LoginDTO loginDTO){
        LoginText loginText = learnerService.loginLearner(loginDTO);
        return ResponseEntity.ok(loginText);
    }
}
