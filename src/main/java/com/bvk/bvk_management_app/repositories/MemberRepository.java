package com.bvk.bvk_management_app.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bvk.bvk_management_app.models.Member;

public interface MemberRepository extends JpaRepository<Member, UUID> {
	List<Member> findAllByOrderByCreatedAtDesc();
	boolean existsById(Member superior);
}
