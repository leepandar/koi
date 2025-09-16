package com.koi.job.client.task;

import com.aizuda.snailjob.client.job.core.annotation.JobExecutor;
import com.aizuda.snailjob.client.job.core.dto.JobArgs;
import com.aizuda.snailjob.common.log.SnailJobLog;
import com.aizuda.snailjob.model.dto.ExecuteResult;
import org.springframework.stereotype.Component;

@Component
@JobExecutor(name = "testJob")
public class TestJob {

    public ExecuteResult jobExecute(JobArgs jobArgs) throws InterruptedException {
        SnailJobLog.REMOTE.info("上下文: {}", jobArgs.getWfContext());
        return ExecuteResult.success();
    }
}
