package jp.co.namihira.townbookweb.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	public String encrypt(final String raw) {
		return passwordEncoder.encode(raw);
	}
	
}
