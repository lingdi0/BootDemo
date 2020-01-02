package com.example.demo.scheduler;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/** 
* @date 2019-10-28 11:46:34
* @author LEI
*/
@Component
public class MyScheduler {
	
	private static final Logger log = LoggerFactory.getLogger(MyScheduler.class);
	
	@Scheduled(cron = "0 0/2 * * * ?")
	public void testScheduler() {
		log.info(LocalDateTime.now().toString());
	}

}
