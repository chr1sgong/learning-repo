package io.chr1s.concurrent.basic;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RequestIdGenerator implements CircularSeqGenerator {

    /**
     * 唯一实例，单例
     */
    private static final RequestIdGenerator INSTANCE = new RequestIdGenerator();

    private static final short SEQ_UPPER_LIMIT = 999;

    private short sequence = -1;

    /**
     * private constructor
     */
    private RequestIdGenerator() {
        // do nothing
    }

    public static RequestIdGenerator getInstance() {
        return INSTANCE;
    }


    @Override
    public short nextSequence() {
        if (sequence >= SEQ_UPPER_LIMIT) {
            sequence = 0;
        } else {
            sequence ++;
        }
        return sequence;
    }

    /**
     * 生成一个新的requestId
     */
    public String nextID() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String timestamp = sdf.format(new Date());
        DecimalFormat df = new DecimalFormat("000");

        // 生成请求序列
        short sequenceNo = nextSequence();
        return "0049" + timestamp + df.format(sequenceNo);
    }
}
