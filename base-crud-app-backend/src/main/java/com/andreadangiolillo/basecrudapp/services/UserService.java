package com.andreadangiolillo.basecrudapp.services;

import com.andreadangiolillo.basecrudapp.models.User;
import com.andreadangiolillo.basecrudapp.services.parameters.UserSearchParameters;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserService extends CrudService<User, Long> {
    List<User> search(UserSearchParameters parameters);
    void upload(MultipartFile file) throws IOException;
}
