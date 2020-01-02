package com.example.demo.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/** 
* @date 2019-11-08 13:48:08
* @author LEI
*/
@Entity
@Table(name = "log")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Log {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(columnDefinition = "varchar(255) COMMENT '请求全路径'")
	private String url;
	@Column(columnDefinition = "varchar(255) COMMENT '请求路径'")
	private String uri;
	@Column(columnDefinition = "varchar(10) COMMENT '请求方法'")
	private String method;
	@Column(columnDefinition = "text COMMENT '请求参数'")
	private String params;
	@Column(columnDefinition = "text COMMENT '返回结果'")
	private String result;
	@Column(columnDefinition = "int(10) COMMENT '请求耗时'")
	private int cost;
	@Column(columnDefinition = "datetime COMMENT '创建时间'")
	private LocalDateTime createtime;
	@Column(columnDefinition = "varchar(255) COMMENT '请求ip'")
	private String ip;

}
