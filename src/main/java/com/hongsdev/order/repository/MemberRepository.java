package com.hongsdev.order.repository;

import com.hongsdev.order.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {


}


