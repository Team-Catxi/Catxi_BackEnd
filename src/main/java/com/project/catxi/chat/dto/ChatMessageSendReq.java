package com.project.catxi.chat.dto;

public record ChatMessageSendReq(
	Long roomId,
	String membername,
	String message
) {
	public ChatMessageSendReq withRoomId(Long newRoomId) {
		return new ChatMessageSendReq(newRoomId, this.membername, this.message);
	}
}