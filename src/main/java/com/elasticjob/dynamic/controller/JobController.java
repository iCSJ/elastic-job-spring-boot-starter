package com.elasticjob.dynamic.controller;

import java.util.HashMap;
import java.util.Map;

import com.dangdang.ddframe.job.lite.lifecycle.domain.JobSettings;
import com.elasticjob.base.JobTypeTag;
import com.elasticjob.dynamic.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import com.elasticjob.dynamic.bean.Job;
import com.elasticjob.dynamic.service.JobService;

/**
 * @author chengshijun@kzkj.com
 * @version V1.0.0
 * @date 2018/12/14 10:37
 * @description JobController 动态任务添加,可以用于同一个任务，需要不同的时间来进行触发场景
 * @Copyright 2018 www.kzkj.com Inc. All rights reserved.
 */
@RestController
public class JobController {

    @Autowired
    private JobService jobService;

    /**
     * 获取作业配置
     *
     * @param jobName 任务名称
     * @throws Exception
     */
    @GetMapping("/job/get")
    public Object getJob(String jobName) {
        Map<String, Object> result = new HashMap<>();
        result.put("status", true);
        if (!StringUtils.hasText(jobName)) {
            result.put("status", false);
            result.put("message", "name not null");
            return result;
        }

        try {
            JobSettings jobSettings = jobService.getJob(jobName);
            result.put("data", JsonUtils.toJson(jobSettings));
        } catch (Exception e) {
            result.put("status", false);
            result.put("message", e.getMessage());
        }
        return result;
    }

    /**
     * 添加动态任务（适用于脚本逻辑已存在的情况，只是动态添加了触发的时间）
     *
     * @param job 任务信息
     * @return
     */
    @PostMapping("/job/add")
    public Object addJob(@RequestBody Job job) {
        Map<String, Object> result = new HashMap<>();
        result.put("status", true);

        if (!StringUtils.hasText(job.getJobName())) {
            result.put("status", false);
            result.put("message", "name not null");
            return result;
        }

        if (!StringUtils.hasText(job.getCron())) {
            result.put("status", false);
            result.put("message", "cron not null");
            return result;
        }

        if (!StringUtils.hasText(job.getJobType())) {
            result.put("status", false);
            result.put("message", "getJobType not null");
            return result;
        }

        if (JobTypeTag.SCRIPT.equalsIgnoreCase(job.getJobType())) {
            if (!StringUtils.hasText(job.getScriptCommandLine())) {
                result.put("status", false);
                result.put("message", "scriptCommandLine not null");
                return result;
            }
        } else {
            if (!StringUtils.hasText(job.getJobClass())) {
                result.put("status", false);
                result.put("message", "jobClass not null");
                return result;
            }
        }

        try {
            jobService.addJob(job);
        } catch (Exception e) {
            result.put("status", false);
            result.put("message", e.getMessage());
        }
        return result;
    }

    /**
     * 修改作业配置.
     *
     * @param jobSettings 作业配置
     */
    @PutMapping("/job/update")
    public Object updateJobSettings(@RequestBody JobSettings jobSettings) {
        Map<String, Object> result = new HashMap<>();
        result.put("status", true);
        if (!StringUtils.hasText(jobSettings.getJobName())) {
            result.put("status", false);
            result.put("message", "name not null");
            return result;
        }

        if (!StringUtils.hasText(jobSettings.getCron())) {
            result.put("status", false);
            result.put("message", "cron not null");
            return result;
        }

        if (!StringUtils.hasText(jobSettings.getJobType())) {
            result.put("status", false);
            result.put("message", "getJobType not null");
            return result;
        }
        try {
            jobService.updateJob(jobSettings);
        } catch (Exception e) {
            result.put("status", false);
            result.put("message", e.getMessage());
        }
        return result;
    }

    /**
     * 修改作业执行时间
     *
     * @param jobName
     * @param cron
     * @return
     */
    @GetMapping("/job/updateCron")
    public Object updateJobCron(String jobName, String cron) {
        Map<String, Object> result = new HashMap<>();
        result.put("status", true);
        if (!StringUtils.hasText(jobName)) {
            result.put("status", false);
            result.put("message", "name not null");
            return result;
        }

        if (!StringUtils.hasText(cron)) {
            result.put("status", false);
            result.put("message", "cron not null");
            return result;
        }
        try {
            jobService.updateJobCron(jobName, cron);
        } catch (Exception e) {
            result.put("status", false);
            result.put("message", e.getMessage());
        }
        return result;
    }

    /**
     * 删除动态注册的任务（只删除注册中心中的任务信息）
     *
     * @param jobName 任务名称
     * @throws Exception
     */
    @GetMapping("/job/remove")
    public Object removeJob(String jobName) {
        Map<String, Object> result = new HashMap<>();
        result.put("status", true);
        try {
            jobService.removeJob(jobName);
        } catch (Exception e) {
            result.put("status", false);
            result.put("message", e.getMessage());
        }
        return result;
    }
}
