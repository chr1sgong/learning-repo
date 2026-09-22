package io.chr1s.hash;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

/**
 * @author gongqi <qgong92@gmail.com>
 * Created on 2022-07-12
 */
public class ConsistentHashingWithoutVirtualNode {

    private static final HashFunction HASH_FUNCTION = Hashing.murmur3_32_fixed();

    private static final String[] clusterAddresses = {
            "192.168.0.0:111",  "192.168.0.1:111",   "192.168.0.2:111",   "192.168.0.3:111",   "192.168.0.4:111"
    };

    private static SortedMap<Integer, String> sortedMap = new TreeMap<>();

    /**
     * 注意，当节点比较少的时候，因为hash的随机性，节点在环上的分布大概率不是均匀的
     */
    static {
        for (String address : clusterAddresses) {
            int hash = hash(address);
            System.out.println("[" + address + "] launched @ " + hash);
            sortedMap.put(hash, address);
        }
    }

    public static String getInstanceAddress(String input) {
        int hash = hash(input);
        // 获取所有大于或等于当前hash值的子map
        SortedMap<Integer, String> subMap = sortedMap.tailMap(hash);

        // 因为key空间使用一个hash环表示，落在最后一个区域的key应该使用第一个节点
        if (subMap.isEmpty()) {
            return sortedMap.get(sortedMap.firstKey());
        }
        return subMap.get(subMap.firstKey());
    }

    public void addInstance(String address) {

    }

    public void removeInstance(String address) {}


    public static void main(String[] args) {
        Map<String, Integer> testMap = new HashMap<>();
        for (int i = 0; i < 100000; i++) {
            int weightId = (int) (Math.random() * 10000);
            String serverInstance = getInstanceAddress(String.valueOf(weightId));
            testMap.put(serverInstance, testMap.getOrDefault(serverInstance, 0) + 1);
        }

        testMap.forEach((key, value) -> {
            System.out.println("instance: " + key + " handled " + value + " requests. in percentage is: " + (value / 1000.0) + "%");
        });
    }

    private static int hash(String input) {
        return HASH_FUNCTION.hashUnencodedChars(input).asInt();
    }
}
