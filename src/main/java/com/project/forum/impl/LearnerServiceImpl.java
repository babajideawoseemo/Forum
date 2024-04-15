package com.project.forum.impl;

import com.project.forum.DTO.LearnerDTO;
import com.project.forum.DTO.LoginDTO;
import com.project.forum.domain.Learner;
import com.project.forum.payload.response.LoginText;
import com.project.forum.repository.LearnerRepository;
import com.project.forum.service.LearnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LearnerServiceImpl implements LearnerService {

    @Autowired
    private LearnerRepository learnerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Learner addLearner(LearnerDTO l) {
        Learner learner = new Learner();

                learner.setId(l.getId());
                learner.setEmail(l.getEmail());
                learner.setName(l.getName());
                learner.setUsername(l.getUsername());
                learner.setPassword(passwordEncoder.encode((l.getPassword())));


       return learnerRepository.save(learner);
        //return learner.getUsername();
    }


    @Override
    public List<Learner> getLearners(){
        return learnerRepository.findAll();
    }

    @Override
    public LoginText loginLearner(LoginDTO loginDTO) {
    Learner l = new Learner();
        String msg = "";
        l.setEmail(loginDTO.getEmail());
        Learner learner1 = learnerRepository.findByEmail(l.getEmail());

        if(learner1 != null) {
            l.setPassword(loginDTO.getPassword());
            String password = l.getPassword();
            l.setPassword(passwordEncoder.encode((loginDTO.getPassword())));
            String encodedPassword = l.getPassword();
            Boolean isPadRight = passwordEncoder.matches(password, encodedPassword);
            if(isPadRight) {
                Optional<Learner> learner = learnerRepository.findOneByEmailAndPassword(loginDTO.getEmail(), encodedPassword);
                if(learner.isPresent()) {
                    return new LoginText("Login Success", true);
                } else {
                    return new LoginText("Login Failed", true);
                }

            } else {
                return new LoginText("password Not Match", false);
            }
        } else {
            return new LoginText("Email not exists", false);
        }
    }
}
