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
package com.synews.hammer.http;

import com.synews.hammer.di.module.AppConfigModule;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 *
 * 处理 Http 请求和响应结果的处理类
 * 使用 {@link AppConfigModule.Builder#httpHandler(AppHttpHandler)} 方法配置
 *
 */
public interface AppHttpHandler {
    /**
     * 这里可以提供一个全局处理Http请求和响应结果的处理类,
     * 这里可以比客户端提前一步拿到服务器返回的结果,可以做一些操作,比如token超时,重新获取
     * @param httpResult  String
     * @param chain Chain
     * @param response Response
     * @return
     */
    Response onHttpResultResponse(String httpResult, Interceptor.Chain chain, Response response);

    /**
     * 这里可以在请求服务器之前可以拿到request,做一些操作比如给request统一添加token或者header以及参数加密等操作
     * @param chain Chain
     * @param request Request
     * @return
     */
    Request onHttpRequestBefore(Interceptor.Chain chain, Request request);

    //空实现
    AppHttpHandler EMPTY = new AppHttpHandler() {
        @Override
        public Response onHttpResultResponse(String httpResult, Interceptor.Chain chain, Response response) {
            //不管是否处理,都必须将response返回出去
            return response;
        }

        @Override
        public Request onHttpRequestBefore(Interceptor.Chain chain, Request request) {
            //不管是否处理,都必须将request返回出去
            return request;
        }
    };

}
