package com.example.demo.service.impl;

import com.example.demo.dto.LoginDTO;
import com.example.demo.entity.Login;
import com.example.demo.entity.User;
import com.example.demo.repository.LoginRepo;
import com.example.demo.repository.UserRepo;
import com.example.demo.service.LoginService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private LoginRepo loginRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public boolean login(LoginDTO dto) {
        try {
            User user = userRepo.getLoginByUsernameAndPassword(dto.getName(), dto.getPassword());
            if (user != null) {
                mapper.map(loginRepo.save(mapper.map(dto, Login.class)), LoginDTO.class);
                return true;
            }
            return false;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
