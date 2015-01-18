package com.sequenceiq.jobrunner;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class MapReduceRunner {
    
    public void runJob(Configuration configuration, Tool tool, String[] args) throws Exception {
        try {
            ToolRunner.run(configuration, tool, args);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args){
        // TODO
    }
}
