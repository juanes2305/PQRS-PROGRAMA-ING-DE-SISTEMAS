package com.pqrs_backend.mapper;


import com.pqrs_backend.entity.Usuario;
import com.pqrs_backend.presentation.AuthorizationRequest;
import com.pqrs_backend.presentation.UserResponse;

public class UserMapper {

	private UserMapper() {
	}

	public static UserResponse toResponse(Usuario user) {
		return UserResponse.builder().name(user.getEmail()).id(user.getId_usuario()).build();
	}

	public static Usuario toDomain(AuthorizationRequest authorizationRequest) {
		return Usuario.builder().email(authorizationRequest.getUserName()).password(authorizationRequest.getPassword())
				.build();
	}
}
