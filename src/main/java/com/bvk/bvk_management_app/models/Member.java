package com.bvk.bvk_management_app.models;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String position;
    private String pictureUrl;

    @ManyToOne
    @JoinColumn(name = "superior_id")
    private Member superior;

	public Member(String name, String position, String pictureUrl, Member superior) {
		super();
		this.name = name;
		this.position = position;
		this.pictureUrl = pictureUrl;
		this.superior = superior;
	}
}
