package com.leron.models.templates;

import com.leron.models.Status;
import com.leron.models.Type;
import com.leron.models.User;

public class ReimbursementTemplate {
	private double amount;
	private String description;
	private byte[] receipt;
	private User author;
	private Status statusId;
	private Type typeId;
}
