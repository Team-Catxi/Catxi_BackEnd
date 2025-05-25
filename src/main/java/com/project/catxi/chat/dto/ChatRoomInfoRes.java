package com.project.catxi.chat.dto;

import java.time.LocalDateTime;

import com.project.catxi.chat.domain.ChatRoom;
import com.project.catxi.common.domain.Location;
import com.project.catxi.common.domain.RoomStatus;

public record ChatRoomInfoRes (
	Location startPoint,
	Location endPoint,
	LocalDateTime departAt,
	RoomStatus status,
	long currentParticipant,
	long maxCapacity
){
	public static ChatRoomInfoRes from(ChatRoom room, long currentParticipants) {
		return new ChatRoomInfoRes(
			room.getStartPoint(),
			room.getEndPoint(),
			room.getDepartAt(),
			room.getStatus(),
			currentParticipants,
			room.getMaxCapacity()
		);
	}
}
