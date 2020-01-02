package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.jpa.LogDao;
import com.example.demo.entity.Log;
import com.example.demo.service.ILogService;

/** 
* @date 2019-11-08 14:00:44
* @author LEI
* TODO
*/
@Service
public class LogService implements ILogService {
	
	@Autowired
	private LogDao logDao;

	@Override
	public void save(Log log) {
		logDao.save(log);
	}


}
