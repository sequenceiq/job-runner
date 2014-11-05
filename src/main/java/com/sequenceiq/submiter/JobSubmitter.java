package com.sequenceiq.submiter;

import com.sequenceiq.submiter.config.AmbariConfigService;
import org.apache.hadoop.conf.Configuration;

public class JobSubmitter {

    public static void main(String[] args){
        Configuration configuration = new AmbariConfigService(
                System.getProperty("ambariIp"), System.getProperty("ambariPort"),
                System.getProperty("ambariUsername"), System.getProperty("ambariPassword")).getConfiguration();

        // TODO: submissions
    }

    public void runScaldingJob(Configuration configuration, String[] args) {
        // TODO
    }

    public void runSparkJob(Configuration configuration, String[] args) {
        // TODO
    }
}
