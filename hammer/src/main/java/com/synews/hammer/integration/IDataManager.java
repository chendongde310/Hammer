/*
 * Copyright 2017 JessYan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.synews.hammer.integration;

import android.content.Context;

import com.synews.hammer.mvp.IModel;

/**
 *
 * 用来管理网络请求层,以及数据缓存层,
 * TODO- 添加数据库请求层
 * 提供给 {@link IModel} 必要的 Api 做数据处理
 *
 */
public interface IDataManager {

    /**
     * 根据传入的 Class 获取对应的 Retrofit service
     *
     * @param service
     * @param <T>
     * @return
     */
    <T> T obtainRetrofitService(Class<T> service);

    /**
     * 根据传入的 Class 获取对应的 RxCache service
     *
     * @param cache
     * @param <T>
     * @return
     */
    <T> T obtainCacheService(Class<T> cache);

    /**
     * 清理所有缓存
     */
    void clearAllCache();

    Context getContext();

    /**
     * 本地储存数据
     * @param key 键
     * @param value 存的数据
     * @return 是否存储成功
     */
    <T> boolean save(String key, T value);

    /**
     * 获取储存的数据
     * @param key 键
     * @return 获取的数据
     */
    <T> T get(String key);

}
