package com.example.demo.dao.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Log;

/** 
* @date 2019-11-08 13:55:22
* @author LEI
* TODO
*/

public interface LogDao extends JpaRepository<Log, Long>{
	
}
