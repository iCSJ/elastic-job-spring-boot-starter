/*
 * Copyright 1999-2015 dangdang.com.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package com.haiermoney.elasticjob.dynamic.controller;


import com.dangdang.ddframe.job.lite.lifecycle.domain.JobSettings;
import com.haiermoney.elasticjob.dynamic.bean.Job;
import com.haiermoney.elasticjob.dynamic.service.JobAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 作业配置的RESTful API.
 *
 * @author caohao
 */
@RequestMapping("/jobs/config")
@RestController
public final class LiteJobConfigRestfulApi {
    @Autowired
    private JobAPIService jobAPIService;

    /**
     * 获取作业配置.
     *
     * @param jobName 作业名称
     * @return 作业配置
     */
    @GetMapping("/{jobName}")
    public JobSettings getJobSettings(@PathVariable("jobName") String jobName) {
        return jobAPIService.getJobSettingsAPI().getJobSettings(jobName);
    }

    /**
     * 添加动态任务（适用于脚本逻辑已存在的情况，只是动态添加了触发的时间）
     *
     * @param job 任务信息
     * @return
     */
    @PostMapping("/add")
    public void addJob(@RequestBody Job job) {
        jobAPIService.addJob(job);
    }

    /**
     * 修改作业配置.
     *
     * @param jobSettings 作业配置
     */
    @PutMapping("/{jobName}")
    public void updateJobSettings(@RequestBody JobSettings jobSettings) {
        jobAPIService.getJobSettingsAPI().updateJobSettings(jobSettings);
    }

    /**
     * 修改作业执行时间
     *
     * @param jobName
     * @param cron
     * @return
     */
    @PutMapping("/{jobName}/cron")
    public void updateJobCron(@PathVariable("jobName") String jobName, String cron) {
        jobAPIService.updateJobCron(jobName, cron);
    }

    /**
     * 删除作业配置.
     *
     * @param jobName 作业名称
     */
    @DeleteMapping("/{jobName}")
    public void removeJob(@PathVariable("jobName") String jobName) {
        jobAPIService.getJobSettingsAPI().removeJobSettings(jobName);
    }
}
