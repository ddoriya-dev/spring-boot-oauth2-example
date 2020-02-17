package com.acompany.asystem.web;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.acompany.asystem.domain.User;
import com.acompany.asystem.service.UserService;

import java.util.Optional;

@Controller
public class UserController {
	//
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService service;
	
	@Value("${systemName}")
	private String systemName;
	
	@RequestMapping(value= {"/me"}, method = RequestMethod.GET)
	public String me(HttpServletRequest request, ModelMap map) {
		//
		map.put("systemName", systemName);
		String viewName = "me";
		
		User user = (User) request.getSession().getAttribute("user");
		log.debug("\n## user in session : {}", user);
		
		if (user == null) {
			//
			return viewName;
		}

		Optional<User> userOptional = service.getUser(user.getUserName());
		log.debug("\n## user : {}", userOptional);
		if (userOptional.get().getTokenId() == null) {
			//
			request.getSession().removeAttribute("user");
		}
		else {
			//
			map.put("user", userOptional.get());
		}
		
		return viewName;
	}
}
