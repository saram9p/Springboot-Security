package com.cos.security1.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.security1.model.User;
import com.cos.security1.repository.UserRepository;

// 시큐리티 설정에서 loginProcessingUrl("/login");
// /login 요청이 오면 자동으로 UserDetailsService 타입으로 Ioc되어 있는 loadUserByUsername 함수가 실행

// @Service를 하면 PrincipalDetailsService가 Ioc로 등록된다, loadUserByUsername가 자동으로 호출된다.
@Service
public class PrincipalDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	// 시큐리티 session(내부 Authentication(내부 UserDetails))
	// 함수 종료시  @AuthenticationPrincipal 어노테이션이 만들어진다. (즉 이 서비스가 호출이 될 때 @AuthenticationPrincipal 어노테이션이 만들어진다.)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("username : " + username);
		User userEntity = userRepository.findByUsername(username);
		if(userEntity != null) {
			// PrincipalDetails가 리턴이 될때 session 안에  Authentication 만들어지면서
			// UserDetails가 리턴이 될때 Authentication에 들어간다.
			// 그러면서 Authentication을 시큐리티 session에 넣어준다.
			// 리턴될때 loadUserByUsername가 알아서 다해준다.
			return new PrincipalDetails(userEntity); 
		}
		return null;
	}

}
