package jp.co.namihira.townbookweb.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jp.co.namihira.townbookweb.consts.AuthorityEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class UserDto implements UserDetails {
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="user_seq", initialValue=1000)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="user_seq")
	public Integer id;

	@Column(nullable = false)
	public String userId;
	
	@Column(nullable = false)
	public String password;

	@Column(nullable = false)
	public Integer prefectureCode;
	
	@Column(nullable = false)
	public String email;
	
	@Column(nullable = true)
	@Enumerated(EnumType.STRING)
	private AuthorityEnum authority;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	    List<GrantedAuthority> authorities = new ArrayList<>();
	    authorities.add(new SimpleGrantedAuthority(authority.toString()));
	    return authorities;
	}

	@Override
	public String getUsername() {
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
