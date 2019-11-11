package com.xiongba.ams.protocol;

import com.xiongba.ams.utils.StreamUtil;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;

public class MessageOutputStream extends FilterOutputStream implements Serializable {
    public OutputStream out;
    public byte[] output;
    public int point;
    final static int MSG_LEN_SEGMENT = 4;
    protected int count;
    private static int max_size = 2048;

    public MessageOutputStream(final OutputStream out) {
        super(out);
        this.out = out;
        this.output = new byte[max_size];
        this.point = 0;
    }

    public void write(byte value) {
        output[point++] = value;
    }

    public void write(short value) {
        StreamUtil.appendShort(output, point, value);
        point += 2;
    }

    public void write(int value) {
        StreamUtil.appendInt(output, point, value);
        point += 4;
    }

    public void write(long value) {
        StreamUtil.appendLong(output, point, value);
//		write((int) x);
//		write((int) (x >> 32) & 0xffffffff);
        point += 8;
    }

    public void write(String value) {

        try {
//			int i;
//			if (point + value.getBytes("utf-8").length > max_size){
//				for (i  = max_size; point + value.getBytes("utf-8").length > i; i = i + 1024) {
//
//				}
//				output = Arrays.copyOf(output, i);
//			}
            point += StreamUtil.appendString(output, point, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeLen() {
        StreamUtil.appendShort(output, MSG_LEN_SEGMENT, point);
    }

    public byte[] getBuf() {
        byte[] result = new byte[point];
        System.arraycopy(output, 0, result, 0, point);
        return result;
    }

    public int getLen() {
        return point;
    }

    public void clear() {
        output = new byte[max_size];
        point = 0;
    }

    public void flush() throws IOException {
        flushBuffer();
        out.flush();
    }

    private void flushBuffer() throws IOException {
        if (count > 0) {
            out.write(output, 0, count);
            count = 0;
        }
    }
}
