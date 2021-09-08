package com.yaorange.jk.utils;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import java.util.List;

public class CacheUitl {
    private static  CacheManager cacheManager;
    static {
         cacheManager = CacheManager.create(CacheUitl.class.getClassLoader().getResource("ehcache.xml"));
    }

    /**
     * 根据key获取缓存数据
     * @param key
     * @return
     */
    public static Object get(String key) {

        Cache cache = cacheManager.getCache("ipLimitCache");

        Element element = cache.get(key);

        Object result =null;

        if(element !=null)
        {
            result =  element.getObjectValue();
        }


        return  result;

    }

    /**
     * 将数据存入缓存
     * @param key
     * @param value
     */
    public static void put(String key, Object value) {
        Cache cache = cacheManager.getCache("ipLimitCache");
        Element element = new Element(key,value);
        cache.put(element);
    }
}
