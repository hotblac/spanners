package org.dontpanic.spanners.springmvc.error;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.base.Throwables;

/**
 * General error handler for the application.
 */
@ControllerAdvice
class ExceptionHandler {

	/**
	 * Handle AccessDeniedException thrown by handlers.
	 */
	@org.springframework.web.bind.annotation.ExceptionHandler(value = AccessDeniedException.class)
	public ModelAndView exception(AccessDeniedException exception, WebRequest request) {

		ModelAndView modelAndView = new ModelAndView("403");
		return modelAndView;
	}

	/**
	 * Handle exceptions thrown by handlers.
	 */
	@org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
	public ModelAndView exception(Exception exception, WebRequest request) {

		ModelAndView modelAndView = new ModelAndView("generalError");
		modelAndView.addObject("errorMessage", Throwables.getRootCause(exception));
		return modelAndView;
	}
}