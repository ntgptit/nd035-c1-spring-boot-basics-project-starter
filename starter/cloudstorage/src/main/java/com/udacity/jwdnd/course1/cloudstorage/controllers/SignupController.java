package com.udacity.jwdnd.course1.cloudstorage.controllers;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserServices;

@Controller
public class SignupController {

	@Autowired
	private UserServices userServices;

	@GetMapping("/signup")
	public String getSignup() {
		return "signup";

	}

	@PostMapping("/signup")
	public String addUser(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes, Model model)
			throws InterruptedException {

		String username = user.getUsername();

		if (StringUtils.isEmpty(username)) {
			redirectAttributes.addFlashAttribute("error", "Username already existed");
//			Thread.sleep(5000);
			return "redirect:/signup";
		}

		if (userServices.isUsernameExisted(username)) {
			redirectAttributes.addFlashAttribute("error", "Username already existed");
//			Thread.sleep(5000);
			return "redirect:/signup";
		}

		if (userServices.addUser(user) > 0) {

			redirectAttributes.addFlashAttribute("success", "You successfully signed up!");
			return "redirect:/login";
		}

		return "signup";

	}
}
