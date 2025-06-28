package com.andreadangiolillo.userapp.repository.specifications;

import com.andreadangiolillo.userapp.models.User;
import com.andreadangiolillo.userapp.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserSpecificationsTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
        userRepository.save(createUser("Mario", "Rossi"));
        userRepository.save(createUser("Luca", "Verdi"));
        userRepository.save(createUser("Maria", "Bianchi"));
    }

    @Test
    void testHasNameSpecification() {
        Specification<User> spec = UserSpecifications.hasName("mario");
        List<User> results = userRepository.findAll(spec);

        assertThat(results).hasSize(1);
        assertThat(results.get(0).getName()).isEqualTo("Mario");
    }

    @Test
    void testHasSurnameSpecification() {
        Specification<User> spec = UserSpecifications.hasSurname("verdi");
        List<User> results = userRepository.findAll(spec);

        assertThat(results).hasSize(1);
        assertThat(results.get(0).getSurname()).isEqualTo("Verdi");
    }

    @Test
    void testHasNameWithNullValue() {
        Specification<User> spec = UserSpecifications.hasName(null);
        List<User> results = userRepository.findAll(spec);

        assertThat(results).hasSize(3);
    }

    private User createUser(String name, String surname){
        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        return user;
    }

}