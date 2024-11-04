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

//@RestController
//@RequestMapping("/api/v1/members")
//public class MemberController {
//
//    @Value("${upload.path}")
//    private String uploadPath;
//
//    @PostMapping("/upload")
//    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
//        if (file.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is empty");
//        }
//
//        try {
//            // Generate unique file name
//            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
//            Path filePath = Paths.get(uploadPath, fileName);
//
//            // Save the file to the file system
//            Files.createDirectories(filePath.getParent());
//            file.transferTo(filePath.toFile());
//
//            // Return the file URL (in a real case, you might want to use a dedicated file server or cloud storage)
//            String fileUrl = "/images/" + fileName;
//            return ResponseEntity.ok(fileUrl);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload image");
//        }
//    }
//}

