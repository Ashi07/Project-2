package com.niit.model;

import java.util.Date;

public class ChatOutput extends Chat {
	private Date messageTime;

	public ChatOutput(Chat original, Date msgTime) 
	{
		this.setId(original.getId());
		this.setMessage(original.getMessage());
		messageTime = msgTime;
	}

	public Date getMessageTime() {
		return messageTime;
	}

	public void setMessageTime(Date messageTime)
	{
		this.messageTime = messageTime;
	}
}


