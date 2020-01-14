package com.example.test;

import common.util.DateUtil;

/** 
* @date 2020-01-03 16:53:03
* @author LEI
* TODO
*/

public class UnixTime {
	
	private long value;
	
	public UnixTime() {
		this(System.currentTimeMillis() / 1000L + 2208988800L);
	}
	
	public UnixTime(long value) {
		this.value = value;
	}
	
	public long value() {
		return this.value;
	}
	
	@Override
	public String toString() {
		return DateUtil.timestampToDateTime((this.value-2208988800L)*1000L).toString();
	}

}
