package com.sequenceiq.submiter.config;

import com.sequenceiq.ambari.client.AmbariClient;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Hdfs;
import org.apache.hadoop.hdfs.DistributedFileSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class AmbariConfigService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AmbariConfigService.class);

    private final String ambariIp;
    private final String ambariPort;
    private final String ambariUsername;
    private final String ambariPassword;

    public AmbariConfigService(String ambariIp, String ambariPort, String ambariUsername, String ambariPassword){
        this.ambariIp = ambariIp;
        this.ambariPort = ambariPort;
        this.ambariUsername = ambariUsername;
        this.ambariPassword = ambariPassword;
    }

    public Configuration getConfiguration() {
        AmbariClient client = new AmbariClient(ambariIp, ambariPort, ambariUsername, ambariPassword);
        return getConf(client);
    }

    private Configuration getConf(AmbariClient ambariClient) {
        Configuration configuration = new Configuration(false);
        for (Map.Entry<String, Map<String, String>> serviceEntry : ambariClient.getServiceConfigMap().entrySet()) {
            LOGGER.debug("Processing service: {}", serviceEntry.getKey());
            for (Map.Entry<String, String> configEntry : serviceEntry.getValue().entrySet()) {
                if (configList.contains(configEntry.getKey())) {
                    configuration.set(configEntry.getKey(), configEntry.getValue());
                    LOGGER.debug("Adding entry: {}", configEntry);
                }
            }
        }
        decorateConfiguration(configuration);
        return configuration;
    }

    private List<String> configList = Arrays.asList(
            ConfigParam.MR_FRAMEWORK_NAME,
            ConfigParam.YARN_RM_ADDRESS,
            ConfigParam.YARN_RESOURCEMANAGER_SCHEDULER_ADDRESS,
            ConfigParam.YARN_APPLICATION_CLASSPATH,
            ConfigParam.YARN_APP_MAPREDUCE_AM_STAGING_DIR,
            ConfigParam.YARN_SCHEDULER_ADDRESS,
            ConfigParam.FS_DEFAULT_NAME,
            ConfigParam.ZOOKEPER_ZNODE_PARENT,
            ConfigParam.HBASE_ZOOKEEPER_QUORUM,
            ConfigParam.HBASE_ZOOKEEPER_PORT,
            ConfigParam.MAPREDUCE_JOBHISTORY_ADDRESS,
            ConfigParam.MAPREDUCE_JOBHISTORY_DONE_DIR,
            ConfigParam.MAPREDUCE_JOBHISTORY_INTERMEDIATE_DONE_DIR
    );


    private void decorateConfiguration(Configuration configuration) {
        configuration.set(ConfigParam.HDFS_IMPL, DistributedFileSystem.class.getName());
        configuration.set(ConfigParam.ABSTRACT_FS_HDFS_IMPL, Hdfs.class.getName());
        configuration.set(ConfigParam.DFS_CLIENT_LEGACY_BLOCK_READER, "true");
        configuration.set(ConfigParam.USER_CLASSPATH_FIRST, "true");
    }
}
