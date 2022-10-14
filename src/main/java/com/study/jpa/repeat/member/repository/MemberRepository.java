package com.study.jpa.repeat.member.repository;

import com.study.jpa.repeat.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
