package com.sequenceiq.submitter.config

import com.sequenceiq.ambari.client.AmbariClient
import org.apache.hadoop.conf.Configuration
import org.slf4j.LoggerFactory
import scala.collection.JavaConverters._

class AmbariConfigService(val ambariIp: String, val ambariPort: String, val ambariUsername: String, val ambariPassword: String,
                                val clientConfigs: java.util.List[String], val decorateConfigs: java.util.Map[String, String]){

  val LOGGER = LoggerFactory.getLogger(classOf[AmbariConfigService])
  val AZURE_ADDRESS = "cloudapp.net"

  def getConfiguration : Configuration = {
    val client = new AmbariClient(ambariIp, ambariPort, ambariUsername, ambariPassword)
    fillConfiguration(client)
  }

  private def fillConfiguration(client: AmbariClient): Configuration = {
    val configuration = new Configuration(false)
    val serviceConfigMap = mapAsScalaMapConverter(client.getServiceConfigMap).asScala
    for ((serviceConfKey, serviceConfValue) <- serviceConfigMap) {
      LOGGER.debug("Processing service: {}", serviceConfKey)
      val serviceConf = mapAsScalaMapConverter(serviceConfValue).asScala
        for ((configKey, configValue) <- serviceConf if clientConfigs.contains(configKey)) {
          configuration.set(configKey, replaceHostName(client, configKey, configValue))
          LOGGER.debug("Adding entry: {} - {}", Array(configKey, configValue))
        }
    }
    decorateConfiguration(configuration)
  }

  private def decorateConfiguration(configuration: Configuration): Configuration = {
    mapAsScalaMapConverter(decorateConfigs).asScala.foreach{case (k, v) => configuration.set(k, v)}
    configuration
  }

  private def replaceHostName(client: AmbariClient, key: String, value: String): String = {
    val result = key.startsWith("yarn.resourcemanager") match {
      case true => {
        val portStartIndex = value.indexOf(":")
        val internalAddress = value.substring(0, portStartIndex)
        val publicAddress = resolveInternalAddressToPublic(client, internalAddress)
        publicAddress + value.substring(portStartIndex)
      }
      case false => value
    }
    result
  }

  private def resolveInternalAddressToPublic(client: AmbariClient, internalAddress: String): String = {
    var publicAddress: String = client.resolveInternalHostName(internalAddress)
    if (internalAddress == publicAddress) {
      if (internalAddress.contains(AZURE_ADDRESS)) {
        publicAddress = internalAddress.substring(0, internalAddress.indexOf(".") + 1) + AZURE_ADDRESS
      }
    }
    publicAddress
  }
}