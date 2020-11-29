package com.joalbano.auth.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Entity
@Data
public class User implements UserDetails, Serializable{
	
	private static final long serialVersionUID = 4442399431693045484L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "user_name", unique = true)
	private String userName;
	
	@Column
	private String password;
	
	@Column(name = "account_not_expired", unique = true)
	private Boolean accountNonExpired;
	
	@Column(name = "account_not_locked", unique = true)
	private Boolean accountNonLocked;
	
	@Column(name = "credentials_not_expired", unique = true)
	private Boolean credentialsNonExpired;
	
	@Column(name = "enabled", unique = true)
	private Boolean enabled;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_permission", joinColumns = {@JoinColumn(name = "id_user")},
	inverseJoinColumns = {@JoinColumn(name = "id_permissions")})
	private List<Permission> permissions;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.permissions;
	}
	
	public List<String> getRoles() {
		List<String> roles = new ArrayList<>();
		this.permissions.stream()
			.forEach(p -> {
				roles.add(p.getDescription());
			});
		return roles;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

}
