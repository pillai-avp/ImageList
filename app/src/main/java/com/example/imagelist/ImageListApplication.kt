package com.example.imagelist

import android.app.Application
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.SingletonImageLoader
import coil3.disk.DiskCache
import coil3.disk.directory
import coil3.memory.MemoryCache
import coil3.network.okhttp.OkHttpNetworkFetcherFactory
import coil3.request.crossfade
import com.example.imagelist.domin.di.networkModule
import com.example.imagelist.domin.di.provideOkHttpClient
import com.example.imagelist.domin.di.providesAuthInterceptor
import com.example.imagelist.domin.di.repositoryModule
import com.example.imagelist.ui.imageListModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ImageListApplication() : Application(), SingletonImageLoader.Factory {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@ImageListApplication)
            modules(networkModule, imageListModule, repositoryModule)
        }
    }

    override fun newImageLoader(context: PlatformContext): ImageLoader {
        return ImageLoader.Builder(context)
            .crossfade(true)
            .components {
                add(
                    OkHttpNetworkFetcherFactory(callFactory = {
                        provideOkHttpClient(authInterceptor = providesAuthInterceptor())
                    })
                )
            }.memoryCache {
                MemoryCache.Builder()
                    .maxSizePercent(context, 0.25)
                    .build()
            }.diskCache {
                DiskCache.Builder()
                    .directory(directory = context.cacheDir.resolve("image_cache"))
                    .maxSizePercent(0.02)
                    .build()
            }
            .build()
    }
}