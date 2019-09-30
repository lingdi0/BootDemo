package common.util.databean;

import java.time.LocalDateTime;

/** 
* @date 2019-09-29 16:24:24
* @author LEI
* TODO
*/
public class TimeQuantum{
	private LocalDateTime start;
	private LocalDateTime end;
	
	public TimeQuantum() {
	}
	public TimeQuantum(LocalDateTime start,LocalDateTime end) {
		this.start = start;
		this.end = end;
	}
	
	public LocalDateTime getStart() {
		return start;
	}
	public TimeQuantum setStart(LocalDateTime start) {
		this.start = start;
		return this;
	}
	public LocalDateTime getEnd() {
		return end;
	}
	public TimeQuantum setEnd(LocalDateTime end) {
		this.end = end;
		return this;
	}
	@Override
	public String toString() {
		return "TimeQuantum [start=" + start + ", end=" + end + "]";
	}
}