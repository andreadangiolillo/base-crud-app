package com.andreadangiolillo.basecrudapp.repository.specifications;

import com.andreadangiolillo.basecrudapp.models.User;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecifications {

    public static Specification<User> hasName(String name) {
        return (root, query, cb) ->
                (name == null || name.isBlank()) ? null :
                        cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    public static Specification<User> hasSurname(String surname) {
        return (root, query, cb) ->
                (surname == null || surname.isBlank()) ? null :
                        cb.like(cb.lower(root.get("surname")), "%" + surname.toLowerCase() + "%");
    }
}
