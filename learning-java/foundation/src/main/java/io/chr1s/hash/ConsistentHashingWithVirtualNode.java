package io.chr1s.hash;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

/**
 * @author gongqi <qgong92@gmail.com>
 * Created on 2022-07-13
 */
public class ConsistentHashingWithVirtualNode {

    /**
     * 每个真实节点对应的虚拟节点数
     */
    private static final int VIRTUAL_NODE_NUM = 1000;

    private static final HashFunction HASH_FUNCTION = Hashing.murmur3_32_fixed();

    private static final String[] serverAddresses = {
            "192.168.0.0:111", "192.168.0.1:111", "192.168.0.2:111", "192.168.0.3:111", "192.168.0.4:111"
    };

    /**
     * 记录hash后的值与虚拟节点的映射
     */
    private static SortedMap<Integer, String> vNodeMap = new TreeMap<>();

    static {
        for (String serverAddress : serverAddresses) {
            for (int i = 0; i < VIRTUAL_NODE_NUM; i++) {
                String vNodeName = getVirtualNodeName(serverAddress, i);
                int hash = hash(vNodeName);
                System.out.println("[" + vNodeName + "] launched @ " + hash);
                vNodeMap.put(hash, vNodeName);
            }
        }
    }

    /**
     * 虚拟节点名 = 节点实例 + 虚拟节点编号
     */
    private static String getVirtualNodeName(String serverAddress, int vNodeIndex) {
        return serverAddress + "&&VN" + vNodeIndex;
    }

    /**
     * 节点实例 = 按照连接字符分隔即可
     */
    private static String getServerAddress(String vNodeName) {
        return vNodeName.split("&&")[0];
    }

    /**
     * 对于一个输入的key，先计算hash值，再根据vNodeMap确定落在哪个虚拟节点上，最后从虚拟节点中解析出节点实例
     * @param key
     * @return
     */
    private static String getServerInstance(String key) {
        int hash = hash(key);
        SortedMap<Integer, String> subMap = vNodeMap.tailMap(hash);
        String vNodeName;
        if (subMap.isEmpty()) {
            vNodeName = vNodeMap.get(vNodeMap.firstKey());
        } else {
            vNodeName = subMap.get(subMap.firstKey());
        }
        return getServerAddress(vNodeName);
    }

    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < 100000; i++) {
            int vNodeId = i;
            String vNodeName = getServerInstance(String.valueOf(vNodeId));
            map.put(vNodeName, map.getOrDefault(vNodeName, 0) + 1);
        }

        map.forEach((k, v) -> {
            System.out.println("server " + k + ": " + v + "(" + v / 1000D + "%)");
        });
    }

    private static int hash(String input) {
        return HASH_FUNCTION.hashUnencodedChars(input).asInt();
    }
}
