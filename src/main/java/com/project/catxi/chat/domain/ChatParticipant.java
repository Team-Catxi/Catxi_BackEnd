package com.project.catxi.chat.domain;

import com.project.catxi.common.domain.BaseTimeEntity;
import com.project.catxi.member.domain.Member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(
	name = "chat_participant",
	uniqueConstraints = {
		@UniqueConstraint(name = "uk_participant_member", columnNames = { "member_id", "active" })
	}
)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ChatParticipant extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "chat_room_id", nullable = false)
	private ChatRoom chatRoom;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id", nullable = false)
	private Member member;

	private boolean isReady;

	@Column(nullable = false)
	private boolean isHost;

	private boolean active;

	public void setActive(boolean isActive) {
		active = isActive;
	}

	public void setReady(boolean ready) {
		isReady=ready;
	}
}
