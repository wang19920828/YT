package org.fh.general.ecom.common.utils;//package org.fh.general.ecom.common.utils;
//
//import org.quartz.CronTrigger;
//import org.quartz.JobDetail;
//import org.quartz.Scheduler;
//import org.quartz.SchedulerException;
//import org.quartz.SchedulerFactory;
//import org.quartz.impl.StdSchedulerFactory;
//
//public class QuartzUtil {
//
//
//	private static String JOB_GROUP_NAME = "WHP_JOBGROUP_NAME";
//	private static String TRIGGER_GROUP_NAME = "WHP_TRIGGERGROUP_NAME";
//
//
//	@SuppressWarnings("rawtypes")
//	public static void addJob(Class cls,String jobName, String time) {
//        try {
//        	Scheduler sched=getScheduler();
////        	String jobName=cls.getSimpleName();
//            JobDetail jobDetail = new JobDetail(jobName, JOB_GROUP_NAME, cls);// 任务名，任务组，任务执行类
//            // 触发器
//            CronTrigger trigger = new CronTrigger(jobName, TRIGGER_GROUP_NAME);// 触发器名,触发器组
//            trigger.setCronExpression(time);// 触发器时间设定
////            removeJob(jobName);
//            sched.scheduleJob(jobDetail, trigger);
//            // 启动
//            if (!sched.isShutdown()) {
//                sched.start();
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//
//	@SuppressWarnings("rawtypes")
//	public void addJob(String jobGroupName,
//			String triggerName, String triggerGroupName, Class jobClass,
//			String time) {
//        try {Scheduler sched=getScheduler();
//        	String jobName=jobClass.getSimpleName();
//            JobDetail jobDetail = new JobDetail(jobName, jobGroupName, jobClass);// 任务名，任务组，任务执行类
//            // 触发器
//            CronTrigger trigger = new CronTrigger(triggerName, triggerGroupName);// 触发器名,触发器组
//            trigger.setCronExpression(time);// 触发器时间设定
//            removeJob(jobName);
//            sched.scheduleJob(jobDetail, trigger);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//
//	@SuppressWarnings("rawtypes")
//	public void modifyJobTime(String jobName, String time) {
//        try {
//        	Scheduler sched=getScheduler();
//            CronTrigger trigger = (CronTrigger) sched.getTrigger(jobName, TRIGGER_GROUP_NAME);
//            if (trigger == null) {
//                return;
//            }
//            String oldTime = trigger.getCronExpression();
//            if (!oldTime.equalsIgnoreCase(time)) {
//                JobDetail jobDetail = sched.getJobDetail(jobName, JOB_GROUP_NAME);
//                Class objJobClass = jobDetail.getJobClass();
//                removeJob(jobName);
//                addJob(objJobClass,jobName, time);
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//
//	public void modifyJobTime(String triggerName,
//			String triggerGroupName, String time) {
//        try {
//        	Scheduler sched=getScheduler();
//            CronTrigger trigger = (CronTrigger) sched.getTrigger(triggerName, triggerGroupName);
//            if (trigger == null) {
//                return;
//            }
//            String oldTime = trigger.getCronExpression();
//            if (!oldTime.equalsIgnoreCase(time)) {
//                CronTrigger ct = (CronTrigger) trigger;
//                // 修改时间
//                ct.setCronExpression(time);
//                // 重启触发器
//                sched.resumeTrigger(triggerName, triggerGroupName);
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//
//	public static void removeJob(String jobName) {
//        try {
//        	Scheduler sched=getScheduler();
//            sched.pauseTrigger(jobName, TRIGGER_GROUP_NAME);// 停止触发器
//            sched.unscheduleJob(jobName, TRIGGER_GROUP_NAME);// 移除触发器
//            sched.deleteJob(jobName, JOB_GROUP_NAME);// 删除任务
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//
//	public void removeJob(String jobName, String jobGroupName,
//			String triggerName, String triggerGroupName) {
//        try {
//        	Scheduler sched=getScheduler();
//            sched.pauseTrigger(triggerName, triggerGroupName);// 停止触发器
//            sched.unscheduleJob(triggerName, triggerGroupName);// 移除触发器
//            sched.deleteJob(jobName, jobGroupName);// 删除任务
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//
//	public void startJobs() {
//        try {
//        	Scheduler sched=getScheduler();
//            sched.start();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//
//	public void shutdownJobs() {
//        try {
//        	Scheduler sched=getScheduler();
//            if (!sched.isShutdown()) {
//                sched.shutdown();
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//	private static Scheduler getScheduler() throws SchedulerException{
//		 SchedulerFactory gSchedulerFactory = new StdSchedulerFactory();
//       Scheduler sche = gSchedulerFactory.getScheduler();
//		return sche;
//	}
//
//}
