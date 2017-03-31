package com.wzw.http_framework.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.wzw.http_framework.HttpApplication;
import com.wzw.http_framework.util.LogUtil;

/**
 * 缓存工具类
 * Created by Henry on 2017/3/28.
 */

public class HttpCache {
    private static final String TAG = HttpCache.class.getSimpleName();
    private static CacheDBHelper sDBHelper;
    private HttpCache(){
        sDBHelper = new CacheDBHelper(HttpApplication.getContext());
    }
    private static HttpCache sHttpCache;
    public static HttpCache getCache () {
        if (sHttpCache == null) {
            synchronized (HttpCache.class) {
                if (sHttpCache == null) {
                    sHttpCache = new HttpCache();
                }
            }
        }
        return sHttpCache;
    }
    public void put(String key ,String value) {

        SQLiteDatabase database = null;
        try {
            database = sDBHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(CacheDBHelper.Cache.COLUMN_KEY,key);
            values.put(CacheDBHelper.Cache.COLUMN_DATA,value);
            values.put(CacheDBHelper.Cache.COLUMN_TIME, System.currentTimeMillis());
            database.replaceOrThrow(CacheDBHelper.Cache.TABLE_NAME, null, values);
        } catch (Exception e) {
            LogUtil.e(TAG,"putCache "+key+" error "+e.toString());
        } finally {
            if (database != null)
                database.close();
        }
    }

    public String get(String key) {
        SQLiteDatabase database = null;
        Cursor c = null;
        String data = "";
        try {
            database = sDBHelper.getReadableDatabase();
            c =  database.query(CacheDBHelper.Cache.TABLE_NAME, new String[]{CacheDBHelper.Cache.COLUMN_DATA}, CacheDBHelper.Cache.COLUMN_KEY + "=?", new String[]{key}, null, null, null);
            c.moveToFirst();
            data =  c.getString(c.getColumnIndex(CacheDBHelper.Cache.COLUMN_DATA));

        } catch (Exception e) {
            LogUtil.e(TAG,"getCache "+key+" error "+e.toString());
        } finally {
            if (c != null) {
                c.close();
            }
            if (database != null) {
                database.close();
            }

        }

        return data;
    }

    public void remove(String key) {

        SQLiteDatabase database = null;
        try {
            database = sDBHelper.getWritableDatabase();
            database.delete(CacheDBHelper.Cache.TABLE_NAME, CacheDBHelper.Cache.COLUMN_KEY + "=?", new String[]{key});
        } catch (Exception e) {
            LogUtil.e(TAG,"removeCache "+key+"  error "+e.toString());
        } finally {
            if (database != null) {
                database.close();
            }

        }
    }

    public void clear() {

        SQLiteDatabase database = null;
        try {
            database = sDBHelper.getWritableDatabase();
            database.delete(CacheDBHelper.Cache.TABLE_NAME,null,null);
        } catch (Exception e) {
            LogUtil.e(TAG,"clearCache error "+e.toString());
        } finally {
            if (database != null) {
                database.close();
            }

        }
    }
}
