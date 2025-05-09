package com.project.catxi.member.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import com.project.catxi.common.domain.BaseTimeEntity;

@Getter
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Member extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 30)
	private String name;

	@Column(nullable = false, length = 30)
	private String nickname;

	@Column(nullable = false, length = 20, unique = true)
	private Long studentNo;

	@Column(nullable = false)
	private int matchCount;

	@Column(nullable = false, updatable = false)
	private LocalDateTime createdAt;

}
