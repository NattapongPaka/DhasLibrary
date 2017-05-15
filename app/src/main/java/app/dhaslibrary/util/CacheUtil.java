package app.dhaslibrary.util;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import rx.Observable;
import rx.Subscription;

/**
 * Created by Dev on 6/12/2559.
 */

public class CacheUtil {
    //Static KEY
    public static final String KEY_BAR_CODE = "BAR_CODE";
    public static final String KEY_PHONE_NUMBER = "PHONE_NUMBER";

    private static CacheUtil instance;
    private final LruCache<String, Bitmap> lruCache;
    private final LruCache<String, String> cache;
    private final LruCache<String, Observable<String>> observableLruCache;
    private final LruCache<String, Subscription> cacheSubscription;

    public static CacheUtil getInstance() {
        if (instance == null) {
            instance = new CacheUtil();
        }
        return instance;
    }

    private CacheUtil() {
        int cacheSize = 4 * 1024 * 1024; // 4MiB
        lruCache = new LruCache<String, Bitmap>(cacheSize) {
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount();
            }
        };
        cache = new LruCache<>(1024);
        observableLruCache = new LruCache<>(1024);
        cacheSubscription = new LruCache<>(1024);
    }

    public void put(String key, Observable<String> observable) {
        synchronized (observableLruCache) {
            observableLruCache.put(key, observable);
        }
    }

    public void put(String key, Bitmap bitmap) {
        synchronized (lruCache) {
            if (lruCache.get(key) == null) {
                lruCache.put(key, bitmap);
            }
        }
    }

    public void putSubscription(String key, Subscription subscription) {
        synchronized (cacheSubscription) {
            if (cacheSubscription.get(key) == null) {
                cacheSubscription.put(key, subscription);
            }
        }
    }

    public boolean hasStringKey(Object key) {
        synchronized (cache) {
            return cache.equals(key);
        }
    }

    public boolean removeString(String key) {
        synchronized (cache) {
            if (hasStringKey(key)) {
                cache.remove(key);
                return true;
            }
            return false;
        }
    }

//    public void calTotalProduct(String key, int value, String opt) {
//        synchronized (lruCache) {
//            if (opt.equals(Config.KEY_ADD_OPT)) {
//                int result = (cache.get(key) != null ? Integer.parseInt(cache.get(key)) : -1);
//                int resultAdd = result + value;
//                put(key, String.valueOf(resultAdd));
//            } else if (opt.equals(Config.KEY_DEL_OPT)) {
//                int result = (cache.get(key) != null ? Integer.parseInt(cache.get(key)) : -1);
//                if(result > 0) {
//                    int resultDel = result - value;
//                    put(key, String.valueOf(resultDel));
//                }else{
//                    put(key, String.valueOf(0));
//                }
//            }
//        }
//    }

    public void put(String key, String value) {
        synchronized (cache) {
            cache.put(key, value);
        }
    }

//    public int getTotal(String key){
//        synchronized (cache){
//            if (cache.get(key) != null) {
//                return (cache.get(key) != null ? Integer.parseInt(cache.get(key)) : -1);
//            }
//        }
//        return -1;
//    }

    public void removeSubscription(String key){
        synchronized (cacheSubscription){
            if(hasStringKey(key)){
                cacheSubscription.remove(key);
            }
        }
    }

    public Subscription getSubscription(String key) {
        synchronized (cacheSubscription) {
            if (cacheSubscription.get(key) != null) {
                return cacheSubscription.get(key);
            }
        }
        return null;
    }

    public String getString(String key) {
        synchronized (cache) {
            if (cache.get(key) != null) {
                return cache.get(key);
            }
        }
        return null;
    }

    public Observable<String> getObservable(String key) {
        synchronized (observableLruCache) {
            if (observableLruCache.get(key) != null) {
                return observableLruCache.get(key);
            }
            return null;
        }
    }

    public Bitmap get(String key) {
        synchronized (lruCache) {
            if (lruCache.get(key) != null) {
                return lruCache.get(key);
            }
        }
        return null;
    }
}
