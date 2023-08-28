package com.itg.AutomobilePartApp.Mappers.User;

import com.itg.AutomobilePartApp.DTO.User.UserDTO;
import com.itg.AutomobilePartApp.Entities.User;
import com.itg.AutomobilePartApp.Responses.User.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserResponse entityToResponse(User user);

    List<UserResponse> entityListToResponseList(List<User> users);

    UserResponse identifierToResponse(UserDTO userDTOtoResponse);

}
