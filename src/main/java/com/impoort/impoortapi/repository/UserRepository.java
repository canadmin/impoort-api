package com.impoort.impoortapi.repository;

import com.impoort.impoortapi.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    User findByActiveGuide(String activeGuide);
    User findByEmail(String email);
}
