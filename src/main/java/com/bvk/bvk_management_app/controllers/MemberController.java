package com.bvk.bvk_management_app.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bvk.bvk_management_app.entities.Member;
import com.bvk.bvk_management_app.models.MemberRequest;
import com.bvk.bvk_management_app.services.MemberService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/members")
public class MemberController {

	@Autowired
	private MemberService memberService;

	@GetMapping
	public ResponseEntity<List<Member>> getAllMembers() {
		List<Member> members = memberService.getAllMembers();
		return ResponseEntity.status(HttpStatus.OK).body(members);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Member> getMemberById(@PathVariable UUID id) {
		Optional<Member> member = memberService.getMemberById(id);
		return member.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping(path = "/store", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Member> createMember(@Valid @ModelAttribute MemberRequest memberRequest) throws Exception {
		Member createdMember = memberService.createMember(memberRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdMember);
	}
}
