package com.tracker.tracker.mappers.user;

import com.tracker.tracker.dto.user.UserDTO;
import com.tracker.tracker.entities.User;
import com.tracker.tracker.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements Mapper<UserDTO, User> {
    private final ModelMapper modelMapper;

    @Autowired
    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public User mapTo(UserDTO source) {
        return modelMapper.map(source, User.class);
    }

    @Override
    public UserDTO mapFrom(User destination) {
        return modelMapper.map(destination, UserDTO.class);
    }
}
