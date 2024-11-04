package com.bvk.bvk_management_app.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bvk.bvk_management_app.entities.Member;
import com.bvk.bvk_management_app.models.MemberRequest;
import com.bvk.bvk_management_app.repositories.MemberRepository;

@Service
public class MemberService {
	@Autowired
	private MemberRepository memberRepository;
	@Value("${upload.path}")
	private String uploadPath;

	public List<Member> getAllMembers() {
		return memberRepository.findAllByOrderByCreatedAtDesc();
	}

	public Optional<Member> getMemberById(UUID id) {
		return memberRepository.findById(id);
	}

	public Member createMember(MemberRequest memberRequest) throws Exception {
		Member member = new Member();

		String path = uploadImage(memberRequest);
		member.setPictureUrl(path);
		member.setName(memberRequest.name());
		member.setPosition(memberRequest.position());

		if (memberRequest.superior() != null) {
			Member superior = memberRepository.findById(memberRequest.superior()).orElseThrow(
					() -> new IllegalArgumentException("Superior not found with ID: " + memberRequest.superior()));
			member.setSuperior(superior);
		}

		return memberRepository.save(member);
	}

	private String uploadImage(MemberRequest memberRequest) throws IOException {
		MultipartFile fileImage = memberRequest.picture();
		String originalFileName = fileImage.getOriginalFilename();

		if (originalFileName != null && !isValidImageType(originalFileName)) {
			throw new IllegalArgumentException("File type is invalid, only  JPG, JPEG, WEBP and PNG.");
		}
		try {
			Path uploadDirectory = Paths.get(uploadPath).toAbsolutePath();
			if (!Files.exists(uploadDirectory)) {
				Files.createDirectories(uploadDirectory);
			}
			String fileName = UUID.randomUUID().toString() + "_" + originalFileName;
			Path filePath = uploadDirectory.resolve(fileName);
			fileImage.transferTo(filePath.toFile());

			return "uploads/" + fileName;
		} catch (IOException e) {
			throw new IOException("Failed to upload picture file", e);
		}
	}

	private Boolean isValidImageType(String fileName) {
		String lowerCaseFileName = fileName.toLowerCase();
		return lowerCaseFileName.endsWith(".jpg") || lowerCaseFileName.endsWith(".jpeg")
				|| lowerCaseFileName.endsWith(".png") || lowerCaseFileName.endsWith(".webp");
	}
}
