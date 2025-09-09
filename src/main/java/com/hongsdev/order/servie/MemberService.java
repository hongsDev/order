package com.hongsdev.order.servie;

import com.hongsdev.order.domain.Member;
import com.hongsdev.order.domain.dto.MemberInDto;
import com.hongsdev.order.domain.dto.MemberOutDto;
import com.hongsdev.order.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;


    @Transactional
    public void create(MemberInDto member) {
        Member saveMember =  Member.createMember(member.getUsername(), member.getPassword());
        memberRepository.save(saveMember);
    }

    public List<MemberOutDto> findAll() {
        List<Member> members = memberRepository.findAll();
        return members
                .stream()
                .map(member -> new MemberOutDto(member.getUsername(), member.getPassword()))
                .collect(Collectors.toList());
    }



}
