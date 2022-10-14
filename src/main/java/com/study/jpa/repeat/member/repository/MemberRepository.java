package com.study.jpa.repeat.member.repository;

import java.util.List;
import java.util.Optional;
import javax.swing.text.html.Option;
import com.study.jpa.repeat.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByName(String name);
}
