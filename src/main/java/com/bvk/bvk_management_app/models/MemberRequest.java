package com.bvk.bvk_management_app.models;

import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;

public record MemberRequest(@NotBlank(message = "Name cannot be null or empty.") String name,
		@NotBlank(message = "Position cannot be null or empty.") String position,
		@NotBlank(message = "Picture cannot be null or empty.") MultipartFile picture, UUID superior) {
}