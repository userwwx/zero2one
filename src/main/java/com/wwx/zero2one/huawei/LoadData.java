package com.wwx.zero2one.huawei;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoadData {

    public static int readConfig(String filePath) {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))) {
            String DELIMITER = "=";
            String line;
            line = reader.readLine();
            line = reader.readLine();
            String[] config = line.split(DELIMITER);
            return Integer.parseInt(config[1]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static Map<String, Map<String, Integer>> readQos(String filePath) {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))){
            String DELIMITER = ",";
            String line;
            boolean head = true;
            Map<String, Map<String, Integer>> qos = new HashMap<>();
            String[] colNames = new String[0];
            while((line = reader.readLine()) != null) {
                String[] column = line.split(DELIMITER);
                if (head) {
                    colNames = new String[column.length-1];
                    for(int i = 1; i < column.length; ++i) {
                        qos.put(column[i], new HashMap<>());
                        colNames[i-1] = column[i];
                    }
                    head = false;
                } else {
                    String rowNames = column[0];
                    for(int i = 1; i < column.length; ++i) {
                        qos.get(colNames[i-1]).put(rowNames, Integer.valueOf(column[i]));
                    }
                }
            }
            return qos;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Map<String, Integer> readBandwidth(String filePath) {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))){
            String DELIMITER = ",";
            String line;
            boolean head = true;
            Map<String, Integer> bandwidth = new HashMap<>();
            while((line = reader.readLine()) != null) {
                String[] column = line.split(DELIMITER);
                if (head) {
                    head = false;
                } else {
                    bandwidth.put(column[0], Integer.valueOf(column[1]));
                }
            }
            return bandwidth;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int[][] readDemand(String filePath) {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))){
            String DELIMITER = ",";
            String line;
            line = reader.readLine();
            String[] columns = line.split(DELIMITER);
//            String[] userNames = new String[columns.length-1];
//            System.arraycopy(columns, 1, userNames, 0, columns.length - 1);
            List<List<Integer>> demand = new ArrayList<>();
            while((line = reader.readLine()) != null) {
                columns = line.split(DELIMITER);
                List<Integer> temp = new ArrayList<>();
                for(int i = 1 ; i < columns.length; ++i) {
                    temp.add(Integer.valueOf(columns[i]));
                }
                demand.add(new ArrayList<>(temp));
            }
            int[][] ans = new int[demand.size()][];
            for(int i = 0 ; i < demand.size(); ++i) {
                ans[i] = demand.get(i).stream().mapToInt(Integer::valueOf).toArray();
            }
            return ans;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String qosPath = "D:\\ideaProject\\coding\\src\\huawei\\qos.csv";
        String bandwidthPath = "D:\\ideaProject\\coding\\src\\huawei\\site_bandwidth.csv";
        String demandPath = "D:\\ideaProject\\coding\\src\\huawei\\demand.csv";
        String configPath = "D:\\ideaProject\\coding\\src\\huawei\\config.ini";
        Map<String, Map<String, Integer>> qos = LoadData.readQos(qosPath);
        Map<String, Integer> bandwidth = LoadData.readBandwidth(bandwidthPath);
        int[][] demand = LoadData.readDemand(demandPath);
        System.out.println(LoadData.readConfig(configPath));
    }
}
