package com.joalbano.auth.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserDTO implements Serializable {
	private static final long serialVersionUID = -3492574967133337458L;

	private String userName;
	private String password;
}
