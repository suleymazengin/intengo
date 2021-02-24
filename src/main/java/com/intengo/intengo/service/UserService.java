package com.intengo.intengo.service;

import com.intengo.intengo.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User save(User user);



}
