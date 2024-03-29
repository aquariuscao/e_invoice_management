package com.example.nhanct.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.nhanct.entity.UserEntity;
import com.example.nhanct.repository.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserEntity user = userRepository.findByUserName(username);
		if(user == null) throw new UsernameNotFoundException("Không tìm thấy ! ");
		
//		String tenrole = user.getVaitro().getRoleCode();
		List<String> listRole = userRepository.findListRoleByUserName(username);
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (String role : listRole) {
			authorities.add(new SimpleGrantedAuthority(role));
		}
		
		CustomUserDetail myUser = new CustomUserDetail(user.getUserName(), user.getPassword(), authorities);
		
		myUser.setEmail(user.getEmail());
		myUser.setAddress(user.getAddress());
		myUser.setSex(user.getSex());
		myUser.setDob(user.getDob());
		myUser.setPhone(user.getPhone());
		myUser.setUserName(user.getUserName());
		myUser.setImage(user.getImage());
		myUser.setPassword(user.getPassword());
		myUser.setId(user.getId());
		
		return myUser;
	}
}
