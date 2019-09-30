package common.util;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import common.util.databean.TimeQuantum;


/** 
* @date 2019-08-27 15:40:21
* @author LEI
* TODO
*/

public class TimeQuantumUtil {
	
	
	/**
	 * 判断时间是否在时间段内
	 * @param time
	 * @param timeQuantum
	 * @return
	 */
	public static boolean isBetween(LocalDateTime time,TimeQuantum timeQuantum) {
		if(timeQuantum.getStart().isAfter(timeQuantum.getEnd())) {
			throw new IllegalArgumentException("开始时间大于结束时间");
		}
		long minutes;
		return (minutes=ChronoUnit.MINUTES.between(timeQuantum.getStart(), time))>0?minutes<=ChronoUnit.MINUTES.between(timeQuantum.getStart(), timeQuantum.getEnd()):false;
	}
	/**
	 * 合并时间段
	 * @param timeQuantums
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<TimeQuantum> merge(List<TimeQuantum> timeQuantums){
		if(timeQuantums==null||timeQuantums.size()==0) {
			return Collections.EMPTY_LIST;
		}
		ArrayList<TimeQuantum> list = new ArrayList<TimeQuantum>();
		for (TimeQuantum timeQuantum : timeQuantums) {
			list.add(new TimeQuantum().setStart(timeQuantum.getStart()).setEnd(timeQuantum.getEnd()));
		}
		int startCnt = 0;
		int endCnt = -1;
		while(startCnt!=endCnt) {
			startCnt = list.size();
			for(int i = list.size()-1;i>=0;i--) {
				TimeQuantum source = list.get(i);
				for(int j = i-1;j>=0;j--) {
					if(source.getStart().isAfter(source.getEnd())) {
						list.remove(i);
						break;
					}
					TimeQuantum target = list.get(j);
					if(isCover(source, target)){
						target.setStart(source.getStart().isBefore(target.getStart())?source.getStart():target.getStart());
						target.setEnd(source.getEnd().isAfter(target.getEnd())?source.getEnd():target.getEnd());
						list.remove(i);
						break;
					}
				}
			}
			endCnt = list.size();
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public static List<TimeQuantum> sub(List<TimeQuantum> totalBase,List<TimeQuantum> subBase){
		if(totalBase==null||totalBase.size()==0) {
			return Collections.EMPTY_LIST;
		}
		List<TimeQuantum> total = new ArrayList<TimeQuantum>();
		for (TimeQuantum timeQuantum : totalBase) {
			total.add(new TimeQuantum().setStart(timeQuantum.getStart()).setEnd(timeQuantum.getEnd()));
		}
		if(subBase==null||subBase.size()==0) {
			return merge(total);
		}
		List<TimeQuantum> sub = new ArrayList<TimeQuantum>();
		for (TimeQuantum timeQuantum : subBase) {
			sub.add(new TimeQuantum().setStart(timeQuantum.getStart()).setEnd(timeQuantum.getEnd()));
		}
		boolean change;
		do {
			change = false;
			for(int i = total.size()-1;i>=0;i--) {
				TimeQuantum source = total.get(i);
				for(int j = sub.size()-1;j>=0;j--) {
					if(source.getStart().isAfter(source.getEnd())) {
						total.remove(i);
						change = true;
						break;
					}
					TimeQuantum target = sub.get(j);
					if(target.getStart().isAfter(target.getEnd())) {
						sub.remove(j);
						change = true;
						continue;
					}
					if(isBetween(target.getStart(), source)&&isBetween(target.getEnd(), source)&&!target.getEnd().equals(source.getStart())) {
						total.add(new TimeQuantum().setStart(target.getEnd()).setEnd(source.getEnd()));
						source.setEnd(target.getStart());
						change = true;
					}else if(isBetween(target.getStart(), source)&&!target.getEnd().isBefore(source.getEnd())) {
						source.setEnd(target.getStart());
						change = true;
					}else if(!target.getStart().isAfter(source.getStart())&&isBetween(target.getEnd(), source)&&!target.getEnd().equals(source.getStart())) {
						source.setStart(target.getEnd());
						change = true;
					}else if(!target.getStart().isAfter(source.getStart())&&!target.getEnd().isBefore(source.getEnd())) {
						total.remove(i);
						change = true;
					}
				}
			}
		}while(change);
		return total;
	}
	
	public static long calMinutes(List<TimeQuantum> times) {
		Long minutes = 0L;
		for (TimeQuantum time : times) {
			minutes += ChronoUnit.MINUTES.between(time.getStart(), time.getEnd());
		}
		return minutes;
	}
	
	public static boolean isIndependent(TimeQuantum t1,TimeQuantum t2) {
		Objects.requireNonNull(t1);
		Objects.requireNonNull(t2);
		if(t1.getEnd().isBefore(t1.getStart())||t2.getEnd().isBefore(t2.getStart())) {
			throw new IllegalArgumentException("开始时间大于结束时间");
		}
		long minutes1 = ChronoUnit.MINUTES.between(t1.getStart(), t1.getEnd());
		long minutes2 = ChronoUnit.MINUTES.between(t2.getStart(), t2.getEnd());
		return ChronoUnit.MINUTES.between(t1.getStart(), t2.getStart())>minutes1||ChronoUnit.MINUTES.between(t2.getStart(), t1.getStart())>minutes2;
	}
	public static boolean isCover(TimeQuantum t1,TimeQuantum t2) {
		return !isIndependent(t1, t2);
	}
	
	public static void main(String[] args) {
		LocalDateTime d1 = LocalDateTime.parse("2019-08-27T12:00:00");
		LocalDateTime d2 = LocalDateTime.parse("2019-08-27T15:00:00");
		LocalDateTime d3 = LocalDateTime.parse("2019-08-27T15:30:00");
		LocalDateTime d4 = LocalDateTime.parse("2019-08-27T17:00:00");
		LocalDateTime d5 = LocalDateTime.parse("2019-08-27T11:00:00");
		LocalDateTime d6 = LocalDateTime.parse("2019-08-27T15:00:00");
		TimeQuantum t1 = new TimeQuantum().setStart(d1).setEnd(d2);
		TimeQuantum t2 = new TimeQuantum().setStart(d3).setEnd(d4);
		TimeQuantum t3 = new TimeQuantum().setStart(d5).setEnd(d6);
		List<TimeQuantum> list = new ArrayList<TimeQuantum>();
		List<TimeQuantum> list2 = new ArrayList<TimeQuantum>();
		list.add(t1);
		list.add(t2);
		list2.add(t3);
		System.out.println(isIndependent(new TimeQuantum(d1.plusHours(0), d1.plusHours(2)), new TimeQuantum(d1.plusHours(3), d1.plusHours(4))));
		System.out.println(isIndependent(new TimeQuantum(d1.plusHours(0), d1.plusHours(2)), new TimeQuantum(d1.plusHours(2), d1.plusHours(4))));
		System.out.println(isIndependent(new TimeQuantum(d1.plusHours(0), d1.plusHours(2)), new TimeQuantum(d1.plusHours(1), d1.plusHours(4))));
		System.out.println(isIndependent(new TimeQuantum(d1.plusHours(0), d1.plusHours(2)), new TimeQuantum(d1.plusHours(0), d1.plusHours(4))));
		System.out.println(isIndependent(new TimeQuantum(d1.plusHours(0), d1.plusHours(2)), new TimeQuantum(d1.plusHours(-1), d1.plusHours(4))));
		
		System.out.println(isIndependent(new TimeQuantum(d1.plusHours(3), d1.plusHours(4)), new TimeQuantum(d1.plusHours(0), d1.plusHours(2))));
		System.out.println(isIndependent(new TimeQuantum(d1.plusHours(2), d1.plusHours(4)), new TimeQuantum(d1.plusHours(0), d1.plusHours(2))));
		System.out.println(isIndependent(new TimeQuantum(d1.plusHours(1), d1.plusHours(4)), new TimeQuantum(d1.plusHours(0), d1.plusHours(2))));
		System.out.println(isIndependent(new TimeQuantum(d1.plusHours(0), d1.plusHours(4)), new TimeQuantum(d1.plusHours(0), d1.plusHours(2))));
		System.out.println(isIndependent(new TimeQuantum(d1.plusHours(-1), d1.plusHours(4)), new TimeQuantum(d1.plusHours(0), d1.plusHours(2))));
				
	}

}
