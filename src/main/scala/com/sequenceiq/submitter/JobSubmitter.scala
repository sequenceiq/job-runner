package com.sequenceiq.submitter

import com.sequenceiq.submitter.spark.SparkRunner
import com.twitter.scalding.Tool
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.util.ToolRunner
import org.apache.hadoop.yarn.api.records.ApplicationReport
import org.apache.mahout.common.AbstractJob
import org.apache.spark.SparkConf

object JobSubmitter {
  def main(args: Array[String]) {
    // TODO
  }

  def runMahoutJob(conf: Configuration, args: Array[String], job: AbstractJob): Unit = {
    ToolRunner.run(conf, job, args)
  }

  def runScaldingJob(conf: Configuration, args: Array[String]): Unit = {
    ToolRunner.run(conf, new Tool, args)
  }

  def runSparkJob(conf: Configuration, sparkConf: SparkConf, clientArgs: Array[String], async: Boolean): ApplicationReport = {
    new SparkRunner().run(conf, sparkConf, clientArgs, async)
  }

}
