package com.bawei.t0511.cache;

import android.content.Context;
import android.os.Environment;
import android.support.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.module.AppGlideModule;

/**
 * Project_Name: T0511
 * Time: 2019/5/12
 * Data: 晚么
 * Description:
 */
public class GlideCache extends AppGlideModule{

    private String path=null;
    private String sdCachePath=Environment.getDownloadCacheDirectory().getPath();
    private String getStorageDirectory(){

        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)?sdCachePath:path;
    }
    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
        super.applyOptions(context, builder);

        int diskCacheSizeByte=1024*1024*100;
        //手机存储路径
        path = context.getCacheDir().getPath();
        builder.setDiskCache(new DiskLruCacheFactory(getStorageDirectory()+"/GlideCache",diskCacheSizeByte));


    }

    @Override
    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
        super.registerComponents(context, glide, registry);

    }
}
