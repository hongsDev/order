package com.hongsdev.order.repository;

import com.hongsdev.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    public List<Order> findByMemberId(@Param("memberId") Long memberId);

    @Query("select o from Order o join fetch o.member join fetch o.delivery where o.member.id = :memberId")
    public List<Order> findByMemberId2(@Param("memberId") Long memberId);


}
