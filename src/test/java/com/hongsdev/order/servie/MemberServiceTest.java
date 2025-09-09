package com.hongsdev.order.servie;

import com.hongsdev.order.domain.Member;
import com.hongsdev.order.domain.dto.MemberInDto;
import com.hongsdev.order.domain.dto.MemberOutDto;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@SpringBootTest
@Slf4j
@RunWith(SpringRunner.class)
@Transactional
@Rollback(false)
public class MemberServiceTest {

    @Autowired
    private MemberService memberService;


    @Test
    public void TEST1() {
        MemberInDto member = new MemberInDto();
        member.setUsername("test3");
        member.setPassword("1234");
        memberService.create(member);
    }
    @Test
    public void TEST2() {

        List<MemberOutDto> all = memberService.findAll();
        for (MemberOutDto member : all) {
            log.debug("test ",  member.getUsername());
            System.out.println("member.getUsername() = " + member.getUsername());
        }
    }
    @Test
    public void TEST3() {



    }
}