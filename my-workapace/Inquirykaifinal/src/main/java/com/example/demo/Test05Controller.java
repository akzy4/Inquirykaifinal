package com.example.demo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Test05Controller {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
    @Autowired
    private NamedParameterJdbcTemplate npJdbcTemplate;
    
	@GetMapping("/")
	public String index(Model model) {
		
		String sql = "SELECT * FROM inquiry";
		
		RowMapper<Inquiry> rowMapper = new RowMapper<Inquiry>() {

			@Override
			public Inquiry mapRow(ResultSet rs, int rowNum) throws SQLException {
				Inquiry inquiry = new Inquiry();
				inquiry.setId(rs.getInt("id"));
				inquiry.setName(rs.getString("name"));
				inquiry.setMail(rs.getString("mail"));
				inquiry.setAge(rs.getInt("age"));
				inquiry.setGender(rs.getString("gender"));
				inquiry.setContents(rs.getString("contents"));
				return inquiry;
			}
			
		};

		List<Inquiry> inquiries = jdbcTemplate.query(sql, rowMapper);
		model.addAttribute("inquiries", inquiries);

		return "index";
		
	}
	
	@GetMapping("/inquiry/{id}")
	public String inquiry(@PathVariable int id, Model model) {
		String sql = "SELECT * FROM inquiry WHERE id = ?";
		
		Object[] args = new Object[] {id};
		RowMapper<Inquiry> rowMapper = BeanPropertyRowMapper.newInstance(Inquiry.class); 
		Inquiry inquiry = jdbcTemplate.queryForObject(sql, args, rowMapper);
		model.addAttribute("inquiry", inquiry);
		return "inquiry";
	}

	@GetMapping("/form")   
	public String form(Inquiry inquiry) {
		return "form";
	}
	
	@PostMapping("/create")
	public String create(Inquiry inquiry) {
		String sql = "INSERT INTO inquiry(name, mail, age, gender, contents) VALUES(:name, :mail, :age, :gender, :contents)";
		
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(inquiry);
		npJdbcTemplate.update(sql, paramSource);
		return "redirect:/";
	}
	
	
	
	
/*
	@GetMapping("/inquiry/{id}")
	public String inquiry(@PathVariable int id, Model model) {
		String sql = "SELECT * FROM inquiry WHERE id = ?";
		
		Object[] args = new Object[] {id};
		RowMapper<Inquiry> rowMapper = BeanPropertyRowMapper.newInstance(Inquiry.class);
		Inquiry inquiry = jdbcTemplate.queryForObject(sql, args, rowMapper);
		model.addAttribute("inquiry", inquiry);
		return "inquiry";
		
	}
*/
	

}

















