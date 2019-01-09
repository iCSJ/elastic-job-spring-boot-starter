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

package com.haiermoney.dynamic.controller;

import com.dangdang.ddframe.job.lite.lifecycle.domain.JobBriefInfo;
import com.dangdang.ddframe.job.lite.lifecycle.domain.ServerBriefInfo;
import com.haiermoney.dynamic.service.JobAPIService;
import com.google.common.base.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;


/**
 * 服务器维度操作的RESTful API.
 *
 * @author caohao
 */
@RequestMapping("/servers")
@RestController
public final class ServerOperationRestfulApi {
    @Autowired
    private JobAPIService jobAPIService;

    /**
     * 获取服务器总数.
     *
     * @return 服务器总数
     */
    @GetMapping("/count")
    public int getServersTotalCount() {
        return jobAPIService.getServerStatisticsAPI().getServersTotalCount();
    }

    /**
     * 获取服务器详情.
     *
     * @return 服务器详情集合
     */
    @GetMapping
    public Collection<ServerBriefInfo> getAllServersBriefInfo() {
        return jobAPIService.getServerStatisticsAPI().getAllServersBriefInfo();
    }

    /**
     * 禁用作业.
     *
     * @param serverIp 服务器IP地址
     */
    @GetMapping("/{serverIp}/disable")
    public void disableServer(@PathVariable("serverIp") String serverIp) {
        jobAPIService.getJobOperatorAPI().disable(Optional.<String>absent(), Optional.of(serverIp));
    }

    /**
     * 启用作业.
     *
     * @param serverIp 服务器IP地址
     */
    @GetMapping("/{serverIp}/enable")
    public void enableServer(@PathVariable("serverIp") String serverIp) {
        jobAPIService.getJobOperatorAPI().enable(Optional.<String>absent(), Optional.of(serverIp));
    }

    /**
     * 终止作业.
     *
     * @param serverIp 服务器IP地址
     */
    @GetMapping("/{serverIp}/shutdown")
    public void shutdownServer(@PathVariable("serverIp") String serverIp) {
        jobAPIService.getJobOperatorAPI().shutdown(Optional.<String>absent(), Optional.of(serverIp));
    }

    /**
     * 清理作业.
     *
     * @param serverIp 服务器IP地址
     */
    @GetMapping("/{serverIp}/remove")
    public void removeServer(@PathVariable("serverIp") String serverIp) {
        jobAPIService.getJobOperatorAPI().remove(Optional.<String>absent(), Optional.of(serverIp));
    }

    /**
     * 获取该服务器上注册的作业的简明信息.
     *
     * @param serverIp 服务器IP地址
     * @return 作业简明信息对象集合
     */
    @GetMapping("/{serverIp}/jobs")
    public Collection<JobBriefInfo> getJobs(@PathVariable("serverIp") String serverIp) {
        return jobAPIService.getJobStatisticsAPI().getJobsBriefInfo(serverIp);
    }

    /**
     * 禁用作业.
     *
     * @param serverIp 服务器IP地址
     * @param jobName  作业名称
     */
    @GetMapping("/{serverIp}/jobs/{jobName}/disable")
    public void disableServerJob(@PathVariable("serverIp") String serverIp, @PathVariable("jobName") String jobName) {
        jobAPIService.getJobOperatorAPI().disable(Optional.of(jobName), Optional.of(serverIp));
    }

    /**
     * 启用作业.
     *
     * @param serverIp 服务器IP地址
     * @param jobName  作业名称
     */
    @GetMapping("/{serverIp}/jobs/{jobName}/enable")
    public void enableServerJob(@PathVariable("serverIp") String serverIp, @PathVariable("jobName") String jobName) {
        jobAPIService.getJobOperatorAPI().enable(Optional.of(jobName), Optional.of(serverIp));
    }

    /**
     * 终止作业.
     *
     * @param serverIp 服务器IP地址
     * @param jobName  作业名称
     */
    @GetMapping("/{serverIp}/jobs/{jobName}/shutdown")
    public void shutdownServerJob(@PathVariable("serverIp") String serverIp, @PathVariable("jobName") String jobName) {
        jobAPIService.getJobOperatorAPI().shutdown(Optional.of(jobName), Optional.of(serverIp));
    }

    /**
     * 清理作业.
     *
     * @param serverIp 服务器IP地址
     * @param jobName  作业名称
     */
    @GetMapping("/{serverIp}/jobs/{jobName}/remove")
    public void removeServerJob(@PathVariable("serverIp") String serverIp, @PathVariable("jobName") String jobName) {
        jobAPIService.getJobOperatorAPI().remove(Optional.of(jobName), Optional.of(serverIp));
    }
}
