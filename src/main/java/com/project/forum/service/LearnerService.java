package com.project.forum.service;

import com.project.forum.DTO.LearnerDTO;
import com.project.forum.DTO.LoginDTO;
import com.project.forum.domain.Learner;
import com.project.forum.payload.response.LoginText;

import java.util.List;

public interface LearnerService {

    Learner addLearner(LearnerDTO learnerDTO);

    List<Learner> getLearners();
    LoginText loginLearner(LoginDTO loginDTO);
}
