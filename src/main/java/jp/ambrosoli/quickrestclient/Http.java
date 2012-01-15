/*
 * Copyright (c) 2011-2012 ambrosoli.jp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package jp.ambrosoli.quickrestclient;

import jp.ambrosoli.quickrestclient.request.HttpRequestBuilder;

/**
 * HTTP通信処理のユーザインタフェースを提供するクラスです。
 * 
 * @author willard379
 * @since 0.0.1
 * 
 */
public final class Http implements HttpConstants {

    /**
     * URLを設定します。
     * 
     * @param url
     *            URL
     * @return HttpBuilderオブジェクト
     */
    public static HttpRequestBuilder url(final String url) {
        return new HttpRequestBuilder(url);
    }
}