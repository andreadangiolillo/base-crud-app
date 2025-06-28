package com.andreadangiolillo.userapp.services.impl;

import com.andreadangiolillo.userapp.models.User;
import com.andreadangiolillo.userapp.repository.UserRepository;
import com.andreadangiolillo.userapp.repository.specifications.UserSpecifications;
import com.andreadangiolillo.userapp.services.UserService;
import com.andreadangiolillo.userapp.services.parameters.UserSearchParameters;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@Service
public class UserServiceImpl extends AbstractCrudServiceImpl<User, Long> implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        super(userRepository);
        this.userRepository = userRepository;
    }

    @Override
    public List<User> search(UserSearchParameters parameters) {
        Specification<User> userSpecification =
                UserSpecifications.hasName(parameters.getName())
                .and(UserSpecifications.hasSurname(parameters.getSurname()));
        return userRepository.findAll(userSpecification);
    }

    @Override
    public void upload(MultipartFile file) throws IOException {
        try (Reader reader = new InputStreamReader(file.getInputStream())) {
            List<User> users = new CsvToBeanBuilder<User>(reader)
                    .withType(User.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build()
                    .parse();

            users.forEach(this::save);
        }
    }
}
