package com.andreadangiolillo.userapp.services;

import com.andreadangiolillo.userapp.models.User;
import com.andreadangiolillo.userapp.services.parameters.UserSearchParameters;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserService extends CrudService<User, Long> {
    List<User> search(UserSearchParameters parameters);
    void upload(MultipartFile file) throws IOException;
}
