package com.bsuir.user;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:8080")
@RequestMapping("user")
public class UserController {

	@GetMapping("/principal")
	public Object user() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if ( principal instanceof DefaultOidcUser){
			return JSONUser.fromOidcUser((DefaultOidcUser) principal);
		} else {
			return JSONUser.fromUser((User) principal);
		}
	}
}