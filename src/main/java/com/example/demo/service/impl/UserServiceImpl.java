package com.example.demo.service.impl;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepo;
import com.example.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserRepo userRepo;

    @Override
    public boolean save(UserDTO dto) {
        try {
            if (userRepo.getUserByName(dto.getName()) == null) {
                mapper.map(userRepo.save(mapper.map(dto, User.class)), UserDTO.class);
                return true;
            }
            return false;
        } catch (Exception e) {
            throw e;
        }
    }
}
