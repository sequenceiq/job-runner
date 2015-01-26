package com.sequenceiq.jobrunner

import com.twitter.scalding.Tool
import org.apache.hadoop.conf.Configuration

object ScaldingRunner {

  def runJob(configuration: Configuration, args: Array[String]) = {
    var tool = new Tool
    tool.setConf(configuration)
    tool.run(args)
  }
  
  def main(args: Array[String]) {
    // TODO
  }

}
