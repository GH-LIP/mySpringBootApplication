package com.test.collection;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 使用map做缓存，是一种本地缓存，每台实例都需要保存一份缓存，无法分布式存储，缓存具有不一致性，且实例关闭或宕机缓存都会消失，。
 * 若要使用分布式缓存，可使用 redis 或 memcache。
 */
public class MapCache {
    private static final MapCache mapCache;
    private Map<String, CacheObject> map;

    private MapCache() {
        map = new ConcurrentHashMap<>();
    }

    static {
        mapCache = new MapCache();
    }


    public static MapCache getMapCache() {
        return mapCache;
    }

    /**
     * 设置没有过期时间缓存
     *
     * @param key   键
     * @param value 值
     */
    public void set(String key, Object value) {
        this.set(key, value, -1L);
    }

    /**
     * 设置带时间缓存
     *
     * @param key   键
     * @param value 值
     * @param time  缓存时间
     */
    public void set(String key, Object value, Long time) {
        time = time > 0 ? System.currentTimeMillis() / 1000 + time : time;
        CacheObject cacheObject = new CacheObject(key, value, time);
        map.put(key, cacheObject);
    }

    /**
     * 获取缓存
     *
     * @param key
     * @param <T>
     * @return
     */
    public <T> T get(String key) {
        CacheObject cacheObject = map.get(key);
        if (cacheObject != null) {
            Long currentTime = System.currentTimeMillis() / 1000;
            if (cacheObject.getTime() <= 0 || currentTime > cacheObject.getTime()) {
                return (T) cacheObject.getValue();
            }
        }
        return null;
    }

    /**
     * 删除缓存
     *
     * @param key 键
     */
    public void delete(String key) {
        map.remove(key);
    }

    /**
     * 清空缓存
     */
    public void clear() {
        map.clear();
    }

    class CacheObject {
        private String key;
        private Object value;
        private Long time;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public Long getTime() {
            return time;
        }

        public void setTime(Long time) {
            this.time = time;
        }

        public CacheObject(String key, Object value, Long time) {
            this.key = key;
            this.value = value;
            this.time = time;
        }
    }

}
