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
		checkTimeQuantum(timeQuantum);
		long minutes;
		return (minutes=ChronoUnit.MINUTES.between(timeQuantum.getStart(), time))>=0?minutes<=ChronoUnit.MINUTES.between(timeQuantum.getStart(), timeQuantum.getEnd()):false;
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
		ArrayList<TimeQuantum> list = new ArrayList<TimeQuantum>(timeQuantums);
		checkTimeQuantums(list);
		int startCnt = 0;
		int endCnt = -1;
		while(startCnt!=endCnt) {
			startCnt = list.size();
			for(int i = list.size()-1;i>=0;i--) {
				TimeQuantum source = list.get(i);
				for(int j = i-1;j>=0;j--) {
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
		List<TimeQuantum> total = new ArrayList<TimeQuantum>(totalBase);
		checkTimeQuantums(total);
		total = merge(total);
		if(subBase==null||subBase.size()==0) {
			return total;
		}
		List<TimeQuantum> sub = new ArrayList<TimeQuantum>(subBase);
		checkTimeQuantums(sub);
		sub = merge(sub);
		boolean change;
		do {
			change = false;
			for(int i = total.size()-1;i>=0;i--) {
				TimeQuantum source = total.get(i);
				if(source.getStart().isAfter(source.getEnd())) {
					total.remove(i);
					change = true;
					break;
				}
				for(int j = sub.size()-1;j>=0;j--) {
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
					}else if(isBetween(target.getStart(), source)&&!target.getEnd().isBefore(source.getEnd())&&!source.getEnd().equals(target.getStart())) {
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
		return !isCover(t1, t2);
	}
	public static boolean isCover(TimeQuantum t1,TimeQuantum t2) {
		Objects.requireNonNull(t1);
		Objects.requireNonNull(t2);
		checkTimeQuantum(t1);
		checkTimeQuantum(t2);
		return isBetween(t1.getStart(), t2)||isBetween(t1.getEnd(), t2)||isBetween(t2.getStart(), t1)||isBetween(t2.getEnd(), t1);
	}
	
	private static void checkTimeQuantum(TimeQuantum t) {
		if(t.getStart().isAfter(t.getEnd())) {
			throw new IllegalArgumentException("开始时间大于结束时间");
		}
	}
	
	private static void checkTimeQuantums(List<TimeQuantum> ts) {
		ts.forEach(TimeQuantumUtil::checkTimeQuantum);
	}
	
	public static void main(String[] args) {
		LocalDateTime d1 = LocalDateTime.parse("2019-08-27T12:00:00");
		LocalDateTime d2 = LocalDateTime.parse("2019-08-27T15:00:00");
		LocalDateTime d3 = LocalDateTime.parse("2019-08-27T14:30:00");
		LocalDateTime d4 = LocalDateTime.parse("2019-08-27T17:00:00");
		LocalDateTime d5 = LocalDateTime.parse("2019-08-27T14:00:00");
		LocalDateTime d6 = LocalDateTime.parse("2019-08-27T19:00:00");
		TimeQuantum t1 = new TimeQuantum().setStart(d1).setEnd(d2);
		TimeQuantum t2 = new TimeQuantum().setStart(d3).setEnd(d4);
		TimeQuantum t3 = new TimeQuantum().setStart(d5).setEnd(d6);
		List<TimeQuantum> list = new ArrayList<TimeQuantum>();
		List<TimeQuantum> list2 = new ArrayList<TimeQuantum>();
		list.add(t1);
		list.add(t2);
		list2.add(t3);
//		System.out.println(isIndependent(new TimeQuantum(d1.plusHours(0), d1.plusHours(2)), new TimeQuantum(d1.plusHours(3), d1.plusHours(4))));
//		System.out.println(isIndependent(new TimeQuantum(d1.plusHours(0), d1.plusHours(2)), new TimeQuantum(d1.plusHours(2), d1.plusHours(4))));
//		System.out.println(isIndependent(new TimeQuantum(d1.plusHours(0), d1.plusHours(2)), new TimeQuantum(d1.plusHours(1), d1.plusHours(4))));
//		System.out.println(isIndependent(new TimeQuantum(d1.plusHours(0), d1.plusHours(2)), new TimeQuantum(d1.plusHours(0), d1.plusHours(4))));
//		System.out.println(isIndependent(new TimeQuantum(d1.plusHours(0), d1.plusHours(2)), new TimeQuantum(d1.plusHours(-1), d1.plusHours(4))));
//		
//		System.out.println(isIndependent(new TimeQuantum(d1.plusHours(3), d1.plusHours(4)), new TimeQuantum(d1.plusHours(0), d1.plusHours(2))));
//		System.out.println(isIndependent(new TimeQuantum(d1.plusHours(2), d1.plusHours(4)), new TimeQuantum(d1.plusHours(0), d1.plusHours(2))));
//		System.out.println(isIndependent(new TimeQuantum(d1.plusHours(1), d1.plusHours(4)), new TimeQuantum(d1.plusHours(0), d1.plusHours(2))));
//		System.out.println(isIndependent(new TimeQuantum(d1.plusHours(0), d1.plusHours(4)), new TimeQuantum(d1.plusHours(0), d1.plusHours(2))));
//		System.out.println(isIndependent(new TimeQuantum(d1.plusHours(-1), d1.plusHours(4)), new TimeQuantum(d1.plusHours(0), d1.plusHours(2))));
		System.out.println(sub(list,list2));
		
	}

}
