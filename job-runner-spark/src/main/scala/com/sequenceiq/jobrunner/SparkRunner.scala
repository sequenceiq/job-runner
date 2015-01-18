package com.sequenceiq.jobrunner

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.yarn.api.records.{ApplicationReport, YarnApplicationState}
import org.apache.spark.SparkConf
import org.apache.spark.deploy.yarn.{Client, ClientArguments}

class SparkRunner {

  def main(args: Array[String]) {
    // TODO
  }

  def runJob(conf: Configuration, sparkConf: SparkConf, clientArgs: Array[String], async: Boolean): ApplicationReport = {
    new SparkRunner().run(conf, sparkConf, clientArgs, async)
  }

  private def run(config: Configuration, sparkConf: SparkConf, clientArgs: Array[String], async: Boolean): ApplicationReport = {
    val cArgs = clientArgs.map(e => e.toString)
    val args = new ClientArguments(cArgs, sparkConf)
    val client: Client = new Client(args, config, sparkConf)
    val appId = client.runApp()
    if (!async) {
      val interval = sparkConf.getLong("spark.yarn.report.interval", 1000)
      while (true) {
        Thread.sleep(interval)
        val report = client.getApplicationReport(appId)
        val state = report.getYarnApplicationState
        if (state == YarnApplicationState.FINISHED ||
          state == YarnApplicationState.FAILED ||
          state == YarnApplicationState.KILLED) {
          println("Yarn application is finished with state: " + state)
          return report
        }
      }
      null
    }
    null
  }
}
