package com.sequenceiq.submiter.config;

public final class ConfigParam {

    public static final String SOCKS_SERVER_ADDRESS = "hadoop.socks.server";
    public static final String MR_FRAMEWORK_NAME = "mapreduce.framework.name";
    public static final String ABSTRACT_FS_HDFS_IMPL = "fs.AbstractFileSystem.hdfs.impl";
    public static final String RPC_SOCKET_FACTORY_CLASS = "hadoop.rpc.socket.factory.class.default";

    public static final String FS_DEFAULT_NAME = "fs.defaultFS";
    public static final String DFS_CLIENT_LEGACY_BLOCK_READER = "dfs.client.use.legacy.blockreader";
    public static final String HDFS_IMPL = "fs.hdfs.impl";
    public static final String USE_DATANODE_HOSTNAME = "dfs.client.use.datanode.hostname";

    public static final String USER_CLASSPATH_FIRST = "mapreduce.job.user.classpath.first";

    public static final String YARN_RM_ADDRESS = "yarn.resourcemanager.address";
    public static final String YARN_APPLICATION_CLASSPATH = "yarn.application.classpath";
    public static final String YARN_APP_MAPREDUCE_AM_STAGING_DIR = "yarn.app.mapreduce.am.staging-dir";
    public static final String YARN_RESOURCEMANAGER_SCHEDULER_ADDRESS = "yarn.resourcemanager.scheduler.address";
    public static final String YARN_SCHEDULER_ADDRESS = "yarn.resourcemanager.scheduler.address";

    public static final String ZOOKEPER_ZNODE_PARENT = "zookeeper.znode.parent";
    public static final String HBASE_ZOOKEEPER_QUORUM = "hbase.zookeeper.quorum";
    public static final String HBASE_ZOOKEEPER_PORT = "hbase.zookeeper.property.clientPort";

    public static final String MAPREDUCE_JOBHISTORY_ADDRESS = "mapreduce.jobhistory.address";
    public static final String MAPREDUCE_JOBHISTORY_DONE_DIR = "mapreduce.jobhistory.done-dir";
    public static final String MAPREDUCE_JOBHISTORY_INTERMEDIATE_DONE_DIR = "mapreduce.jobhistory.intermediate-done-dir";

    private ConfigParam() {
        throw new IllegalStateException();
    }

}
