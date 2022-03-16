package com.wwx.zero2one.huawei;

import java.util.Map;

public class Solution {
    static final String qosPath = "D:\\ideaProject\\coding\\src\\huawei\\qos.csv";
    static final String bandwidthPath = "D:\\ideaProject\\coding\\src\\huawei\\site_bandwidth.csv";
    static final String demandPath = "D:\\ideaProject\\coding\\src\\huawei\\demand.csv";
    static final String configPath = "D:\\ideaProject\\coding\\src\\huawei\\config.ini";

    int QOS_CONSTRAINT;
    Map<String, Map<String, Integer>> qos;
    Map<String, Integer> bandwidth;
    int[][] demand;

    public Solution() {
        QOS_CONSTRAINT = LoadData.readConfig(configPath);
        qos = LoadData.readQos(qosPath);
        bandwidth = LoadData.readBandwidth(bandwidthPath);
        demand = LoadData.readDemand(demandPath);
    }
    public static void main(String[] args) {

    }
}
