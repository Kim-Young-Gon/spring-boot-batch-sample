package com.example.job.jobtask.tasklet;

import com.example.mapper.SampleOrgMapper;
import com.example.mapper.SampleTagMapper;
import com.example.model.SampleOrgModel;
import com.example.model.SampleTagModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
public class SampleTasklet implements Tasklet {
    @Autowired
    private SampleOrgMapper sampleOrgMapper;
    @Autowired
    private SampleTagMapper sampleTagMapper;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        log.debug("Master connection start");
        final List<SampleOrgModel> list1 = sampleOrgMapper.selectSampleOrg();
        log.debug("Master connection end");
        log.debug("Slave connection start");
        final List<SampleTagModel> list2 = sampleTagMapper.selectSampleTag();
        log.debug("Slave connection end");
        return null;
    }
}
