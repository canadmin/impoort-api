package com.impoort.impoortapi.repository;

import com.impoort.impoortapi.domain.enums.UserType;
import com.impoort.impoortapi.domain.user.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    User findByActiveGuide(String activeGuide);

    User findByEmail(String email);

    List<User> findAllByUserType(int userType);

    List<User> findAllByFirstNameAndUserType(String firstName,int userType);

    @Query("select user from User user order by user.watcherCount desc ")
    List<User> getSuggestedUser(Pageable pageable);

    List<User> findAllByFullNameContainingAndUserTypeIn(String fullName,List<UserType> userType);

}
