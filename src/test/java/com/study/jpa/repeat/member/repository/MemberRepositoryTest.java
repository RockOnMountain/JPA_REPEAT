package com.study.jpa.repeat.member.repository;

import com.study.jpa.repeat.member.domain.Address;
import com.study.jpa.repeat.member.domain.Member;
import org.assertj.core.api.Assertions;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;


    @ParameterizedTest
    @ValueSource(strings = {"park", "kim", "song", "son", "lee"})
    void member들_저장(String name) throws Exception {

        // given
        Member member = this.createMember(name);

        // when
        Member savedMember = memberRepository.save(member);

        // then
        assertThat(member.getName()).isEqualTo(savedMember.getName());
    }


    private Member createMember(String name) {

        Address address = new Address("testCity", "testStreet", "testZipCode");

        return Member.builder().name(name).address(address).build();
    }

}
