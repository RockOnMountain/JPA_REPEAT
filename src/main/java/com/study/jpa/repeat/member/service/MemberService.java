package com.study.jpa.repeat.member.service;

import java.util.List;
import com.study.jpa.repeat.member.domain.Member;
import com.study.jpa.repeat.member.dto.MemberDTO;
import com.study.jpa.repeat.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;


    // 동시성 이슈가 발생할 수 있다. Rock에 대해서 연구해보자.
    public Member join(Member member) {

        this.validateDuplicateMember(member.getName());

        return memberRepository.save(member);
    }


    public void validateDuplicateMember(String name) {

        memberRepository.findByName(name).ifPresent(member -> {
            throw new RuntimeException("이미 존재하는 회원입니다: " + member.getName());
        });
    }


    public List<Member> findMembers() {

        return memberRepository.findAll();
    }


    public MemberDTO findById(Long memberId) {

        Member member = memberRepository.findById(memberId).orElseThrow();

        return modelMapper.map(member, MemberDTO.class);
    }

}
