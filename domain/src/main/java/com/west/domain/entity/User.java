package com.west.domain.entity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import springfox.documentation.annotations.ApiIgnore;

@Data
@NoArgsConstructor
public class User {

	@JsonIgnore
	private Long id;
	
	/**
	 * 创建时间
	 */

	@ApiModelProperty(value = "日期")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	private Date date;

	@ApiModelProperty(value = "时间")
	private LocalDateTime time;

	@JsonIgnore
	private String type;

	@ApiModelProperty(value = "姓名", notes = "填写自己姓名")
	private String name;

	@JsonIgnore
	private String parentType;

	@JsonIgnore
	private List<User> sub;

	public User(Long id) {
		this.id = id;
		this.type = "user";
		this.date = new Date();
	}

	
}
