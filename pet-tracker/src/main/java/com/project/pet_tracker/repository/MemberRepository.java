package com.project.pet_tracker.repository;

import com.project.pet_tracker.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {


    Optional<Member> findByEmail(String email);

    @Query("SELECT m FROM Member m WHERE m.email = :email AND m.password = :password")
    Optional<Member> findByEmailAndPassword(@Param("email") String email, @Param("password") String password);



}
