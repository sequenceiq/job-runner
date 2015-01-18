package com.sequenceiq.jobrunner

import com.twitter.scalding.Tool
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.util.ToolRunner

object ScaldingRunner {

  def runJob(configuration: Configuration, args: Array[String]) = {
    ToolRunner.run(configuration, new Tool, args)
  }
  
  def main(args: Array[String]) {
    // TODO
  }

}
