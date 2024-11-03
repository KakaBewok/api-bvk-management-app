package com.bvk.bvk_management_app.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bvk.bvk_management_app.models.Member;
import com.bvk.bvk_management_app.repositories.MemberRepository;

@Service
public class MemberService {
	@Autowired
    private MemberRepository memberRepository;

    public List<Member> getAllMembers() {
        return memberRepository. findAllByOrderByCreatedAtDesc();
    }

    public Optional<Member> getMemberById(UUID id) {
        return memberRepository.findById(id);
    }

    public Member createMember(Member member) {
    	//logic nyimpen gambar tambahin
    	
        if (member.getSuperior() != null) {
            UUID superiorId = member.getSuperior().getId();
            Optional<Member> superior = memberRepository.findById(superiorId);

            if (superior.isPresent()) {
                member.setSuperior(superior.get());
            } else {
                throw new IllegalArgumentException("Superior with ID " + superiorId + " not found");
            }
        }
    	
        return memberRepository.save(member);
    }
}
