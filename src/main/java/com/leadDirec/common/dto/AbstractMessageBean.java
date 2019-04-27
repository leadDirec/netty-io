package com.leadDirec.common.dto;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.nio.charset.Charset;
import java.util.*;
import com.alibaba.fastjson.JSON;

public abstract class AbstractMessageBean {

    /**
     * 类型名String
     */
    protected static final String type_name_String = "String";

    /**
     * 类型名Long
     */
    protected static final String type_name_Long = "Long";

    /**
     * 类型名long
     */
    protected static final String type_name_long = "long";

    /**
     * 类型名Integer
     */
    protected static final String type_name_Integer = "Integer";

    /**
     * 类型名int
     */
    protected static final String type_name_Int = "int";

    /**
     * 类型名Short
     */
    protected static final String type_name_Short = "Short";

    /**
     * 类型名short
     */
    protected static final String type_name_short = "short";

    /**
     * 类型名Double
     */
    protected static final String type_name_Double = "Double";

    /**
     * 类型名double
     */
    protected static final String type_name_double = "double";

    /**
     * 类型名Float
     */
    protected static final String type_name_Float = "Float";

    /**
     * 类型名float
     */
    protected static final String type_name_float = "float";

    /**
     * 类型名Byte
     */
    protected static final String type_name_Byte = "Byte";

    /**
     * 类型名byte
     */
    protected static final String type_name_byte = "byte";

    /**
     * 类型名Boolean
     */
    protected static final String type_name_Boolean = "Boolean";

    /**
     * 类型名boolean
     */
    protected static final String type_name_boolean = "boolean";

    /**
     * 类型名Date
     */
    protected static final String type_name_Date = "Date";

    /**
     * 数字O
     */
    protected static final int ZERO = 0;

    /**
     * 数字1
     */
    protected static final int ONE = 1;

    /**
     * 数字 -1
     */
    protected static final int NEGATIVE_ONE = -1;

    /**
     * 空字符串
     */
    protected static final String EMPTY = "";

    /**
     * UTF-8字符集
     */
    protected static final Charset UTF_8_CHARSET = Charset.forName("UTF-8");

    /**
     * 空
     */
    protected static final byte[] EMPTY_BYTES = new byte[ZERO];

    /**
     * 位标记
     */
    protected int bitField_;

    /**
     * 读取日期
     *
     * @param in
     * @return value
     */
    protected Date readDate(ObjectInput in) throws IOException {
        return new Date(in.readLong());
    }

    /**
     * 写入日期
     *
     * @param out
     * @param value
     * @throws IOException
     */
    protected void writeDate(ObjectOutput out, Date value) throws IOException {
        if (value != null) {
            out.writeLong(value.getTime());
        }
    }

    /**
     * 读取byte数组
     *
     * @param in
     * @return data
     * @throws IOException
     */
    protected byte[] readBytes(ObjectInput in) throws IOException {
        int len = in.readInt();
        if (len < ZERO) {
            return null;
        }
        if (len == ZERO) {
            return EMPTY_BYTES;
        }
        byte[] data = new byte[len];
        in.read(data);
        return data;
    }

    /**
     * 写入byte数组
     *
     * @param out
     * @param value
     * @throws IOException
     */
    protected void writeBytes(ObjectOutput out, byte[] value) throws IOException {
        if (value == null) {
            out.writeInt(NEGATIVE_ONE);
        } else if (value.length == ZERO) {
            out.writeInt(ZERO);
        } else {
            int len = value.length;
            out.writeInt(len);
            out.write(value);
        }
    }

    /**
     * 读取字符串
     *
     * @param in
     * @return value
     */
    protected String readString(ObjectInput in) throws IOException {
        short len = in.readShort();
        if (len < ZERO) {
            return null;
        }
        if (len == ZERO) {
            return EMPTY;
        }
        byte[] data = new byte[len];
        in.read(data);
        return new String(data, UTF_8_CHARSET);
    }

    /**
     * 写入字符串
     *
     * @param out
     * @param value
     * @throws IOException
     */
    protected void writeString(ObjectOutput out, String value) throws IOException {
        if (value == null) {
            out.writeShort(NEGATIVE_ONE);
        } else if (value.length() == ZERO) {
            out.writeShort(ZERO);
        } else {
            byte[] data = value.getBytes(UTF_8_CHARSET);
            int len = data.length;
            if (len > Short.MAX_VALUE) {
                data = Arrays.copyOfRange(data, ZERO, Short.MAX_VALUE);
                len = Short.MAX_VALUE;
            }
            out.writeShort(len);
            out.write(data);
        }
    }

    /**
     * 读取list
     *
     * @param in
     * @param listItemMapper
     * @return list
     * @throws IOException
     */
    protected <T> List<T> readList(ObjectInput in, ListItemMapper<T> listItemMapper) throws IOException,
            ClassNotFoundException {
        short len = in.readShort();
        if (len < ZERO) {
            return null;
        }
        if (len == ZERO) {
            return Collections.emptyList();
        }
        List<T> list = new ArrayList<T>(len);
        for (int i = 0; i < len; i++) {
            if (in.readByte() > ZERO) {
                list.add(listItemMapper.map(in));
            } else {
                list.add(null);
            }
        }
        return list;
    }

    /**
     * 写入list
     *
     * @param out
     * @param list
     * @throws IOException
     */
    protected <T> void writeList(ObjectOutput out, List<T> list) throws IOException {
        if (list == null) {
            out.writeShort(NEGATIVE_ONE);
        } else if (list.isEmpty()) {
            out.writeShort(ZERO);
        } else {
            out.writeShort(list.size());
            for (T t : list) {
                if (t == null) {
                    out.writeByte(ZERO);
                } else {
                    out.writeByte(ONE);
                    writeObj(out, t);
                }
            }
        }
    }

    /**
     * 读出对象
     *
     * @param in
     * @param typeName
     * @throws IOException
     */
    protected Object readBaseObj(ObjectInput in, String typeName) throws IOException {
        if (type_name_String.equals(typeName)) {
            return readString(in);
        } else if (type_name_Long.equals(typeName) || type_name_long.equals(typeName)) {
            return in.readLong();
        } else if (type_name_Integer.equals(typeName) || type_name_Int.equals(typeName)) {
            return in.readInt();
        } else if (type_name_Short.equals(typeName) || type_name_short.equals(typeName)) {
            return in.readShort();
        } else if (type_name_Double.equals(typeName) || type_name_double.equals(typeName)) {
            return in.readDouble();
        } else if (type_name_Float.equals(typeName) || type_name_float.equals(typeName)) {
            return in.readFloat();
        } else if (type_name_Byte.equals(typeName) || type_name_byte.equals(typeName)) {
            return in.readByte();
        } else if (type_name_Boolean.equals(typeName) || type_name_boolean.equals(typeName)) {
            return in.readBoolean();
        } else if (type_name_Date.equals(typeName)) {
            return readDate(in);
        }
        return null;// TODO 应该抛出异常
    }

    /**
     * 读取Map
     *
     * @param in
     * @param mapItemMapper
     * @return map
     * @throws IOException
     */
    protected <K, V> Map<K, V> readMap(ObjectInput in, MapItemMapper<K, V> mapItemMapper) throws IOException,
            ClassNotFoundException {
        short len = in.readShort();
        if (len < ZERO) {
            return null;
        }
        if (len == ZERO) {
            return Collections.emptyMap();
        }
        Map<K, V> map = new HashMap<K, V>(len);
        for (int i = ZERO; i < len; i++) {
            K key = null;
            if (in.readByte() > ZERO) {
                key = mapItemMapper.kMap(in);
            }
            V value = null;
            if (in.readByte() > ZERO) {
                value = mapItemMapper.vMap(in);
            }
            map.put(key, value);
        }
        return map;
    }

    /**
     * 写入map
     *
     * @param out
     * @param map
     * @throws IOException
     */
    protected <K, V> void writeMap(ObjectOutput out, Map<K, V> map) throws IOException {
        if (map == null) {
            out.writeShort(NEGATIVE_ONE);
        } else if (map.isEmpty()) {
            out.writeShort(ZERO);
        } else {
            out.writeShort(map.size());
            for (K k : map.keySet()) {
                if (k == null) {
                    out.writeByte(ZERO);
                } else {
                    out.writeByte(ONE);
                    writeObj(out, k);
                }
                V v = map.get(k);
                if (v == null) {
                    out.writeByte(ZERO);
                } else {
                    out.writeByte(ONE);
                    writeObj(out, v);
                }
            }
        }
    }


    /**
     * 写入对象
     *
     * @param out
     * @param t
     * @throws IOException
     */
    private <T> void writeObj(ObjectOutput out, T t) throws IOException {
        if (t instanceof MessageBean) {
            ((MessageBean) t).writeExternal(out);
        } else if (t instanceof String) {
            writeString(out, (String) t);
        } else if (t instanceof Long) {
            out.writeLong((Long) t);
        } else if (t instanceof Integer) {
            out.writeInt((Integer) t);
        } else if (t instanceof Short) {
            out.writeShort((Short) t);
        } else if (t instanceof Double) {
            out.writeDouble((Double) t);
        } else if (t instanceof Float) {
            out.writeFloat((Float) t);
        } else if (t instanceof Byte) {
            out.writeByte((Byte) t);
        } else if (t instanceof Boolean) {
            out.writeBoolean((Boolean) t);
        } else if (t instanceof Date) {
            writeDate(out, (Date) t);
        } else {
            throw new IllegalArgumentException("暂不支持此类型:" + t.getClass());
        }
    }

    /**
     * 将对象转成Json格式
     *
     * @return JSONString
     */
    public final String toJson() {
        return JSON.toJSONString(this);
    }

    /**
     * list中元素的Mapper
     *
     * @author xiangdao
     *
     * @param <T>
     */
    public static interface ListItemMapper<T> {
        public T map(ObjectInput in) throws IOException, ClassNotFoundException;
    }

    /**
     * map中元素的Mapper
     *
     * @author xiagdao
     *
     * @param <K>
     * @param <V>
     */
    public static interface MapItemMapper<K, V> {
        public K kMap(ObjectInput in) throws IOException, ClassNotFoundException;

        public V vMap(ObjectInput in) throws IOException, ClassNotFoundException;
    }


}
