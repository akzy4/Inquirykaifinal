package jp.winschool.spring.Inquiry;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class InquiryController {
	
	@GetMapping("inquiryForm")
	public String inquiryForm(@ModelAttribute InquiryForm inquiryForm) {
		return "inquiryForm";
	}
	
	@PostMapping("inquiryResult")
	public String inquiryResult(@Valid @ModelAttribute InquiryForm inquiryForm, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "inquiryForm";
		}
		return "inquiryResult";
	}
}




