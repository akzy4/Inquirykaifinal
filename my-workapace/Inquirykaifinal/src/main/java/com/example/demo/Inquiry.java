package com.example.demo;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class Inquiry {
	
	private Integer id;
	
	@NotBlank(message = "名前を入力してください。")
	private String name;
	private String mail;
	private Integer age;
	private String gender;
	private String contents;

}
