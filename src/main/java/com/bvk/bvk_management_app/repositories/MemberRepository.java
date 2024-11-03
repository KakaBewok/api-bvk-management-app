package com.bvk.bvk_management_app.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bvk.bvk_management_app.models.Member;

public interface MemberRepository extends JpaRepository<Member, UUID> {

	boolean existsById(Member superior);
}
