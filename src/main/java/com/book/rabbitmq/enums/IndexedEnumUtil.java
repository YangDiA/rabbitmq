package com.book.rabbitmq.enums;

import java.util.Map;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

public class IndexedEnumUtil {
	
	private IndexedEnumUtil() {
	}
	
	 /**
     * 构建一个基于index的枚举Map
     *
     * @param enums 枚举集
     * @return Map
     */
    public static <T extends IndexedEnum<?>> ImmutableMap<Integer, T> toIndexes(T[] enums) {
        Map<Integer, T> tmp = Maps.newHashMap();
        int curIndex = 0;
        T curVal = null;
        for (T entry : enums) {
            curIndex = entry.getIndex();
            Preconditions.checkArgument(curIndex >= 0, "The index of Enum[%s] must be >= 0.", entry);
            curVal = tmp.put(curIndex, entry);
            Preconditions.checkArgument(curVal == null, "The index of Enum[%s] is not unique.", entry);
        }
        return ImmutableMap.copyOf(tmp);
    }

}
