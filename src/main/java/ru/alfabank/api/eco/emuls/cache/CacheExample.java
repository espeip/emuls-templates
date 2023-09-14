package ru.alfabank.api.eco.emuls.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@SuppressWarnings("all")
public class CacheExample {
    private CacheManager cacheManager;
    private Cache someCache;

    public CacheExample(@Autowired CacheManager cacheManager) {
        this.cacheManager = cacheManager;
        someCache = cacheManager.getCache("cache_example");
        assert someCache != null;
    }

    public void putSomeCache(String key, String value) {
        someCache.put(key, value);
    }

    public String getStringFromSomeCache(String key) {
        return someCache.get(key, String.class);
    }
}
