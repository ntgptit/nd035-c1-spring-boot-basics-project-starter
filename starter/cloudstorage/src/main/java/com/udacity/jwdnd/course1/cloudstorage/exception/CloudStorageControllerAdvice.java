package com.udacity.jwdnd.course1.cloudstorage.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class CloudStorageControllerAdvice {

	@ExceptionHandler(value = { Exception.class })
	public ModelAndView handleGlobalError(HttpServletRequest request, Exception ex) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("error", "error");
		modelAndView.addObject("message", ex.getMessage());
		modelAndView.setViewName("result");
		return modelAndView;
	}

	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ModelAndView handleMaxUploadSizeExceededException(MaxUploadSizeExceededException ex) {
		ModelAndView model = new ModelAndView();
		model.addObject("error", "error");
		model.addObject("message", "File size exceeds limit (1MB)");
		model.setViewName("result");
		return model;
	}

	@ExceptionHandler(BadCredentialsException.class)
	public ModelAndView handleBadCredentialsException(BadCredentialsException ex) {
		ModelAndView model = new ModelAndView();
		model.addObject("error", "error");
		model.addObject("message", ex.getMessage());
		model.setViewName("login");
		return model;
	}

//	@ModelAttribute("successMessage")
//	public String successMessage() {
//		return "";
//	}

}
