package me.hojinchoi.kakaotest1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

class CacheData {
    private String data = "";
    private Date lastUsed;
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }
    public Date getLastUsed() {
        return lastUsed;
    }
    public void setLastUsed(Date lastUsed) {
        this.lastUsed = lastUsed;
    }
    
}

public class Ex3 {
    
    public static void addCache(List<CacheData> caches, CacheData data, int maxCacheSize) {
        
        if(caches.size() == maxCacheSize && maxCacheSize > 0) {
            caches.remove(0);
        }
        caches.add(data);
    }
    
    public static boolean isCacheHit(List<CacheData> caches, String city) {
        boolean isCacheHit = caches.stream().anyMatch((cache) -> city.equalsIgnoreCase(cache.getData()));
        
        caches = caches.stream().map((cache) -> {
            if (cache.getData().equalsIgnoreCase(city)) {
                cache.setLastUsed(new Date());
            }
            return cache;
        }).sorted(Comparator.comparing(CacheData::getLastUsed)).collect(Collectors.toList());
        
        
        
        return isCacheHit;
    }

    public static void main(String[] args) {

        int cacheSize = 0;
        String[] cities = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA"};

        int operatingTime = 0;
        List<CacheData> caches = new ArrayList<>();
        
        for(int inx = 0; inx < cities.length; inx++) {
            if(isCacheHit(caches, cities[inx])) {
                operatingTime += 1;
            }else {
                CacheData cache = new CacheData();
                cache.setData(cities[inx]);
                cache.setLastUsed(new Date());
                addCache(caches, cache, cacheSize);
                
                operatingTime += 5;
            }
        }
        
        System.out.println(operatingTime);
    }

}
