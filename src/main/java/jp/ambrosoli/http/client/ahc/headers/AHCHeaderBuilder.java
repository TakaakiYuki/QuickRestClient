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
package jp.ambrosoli.http.client.ahc.headers;

import java.util.List;

import jp.ambrosoli.http.client.headers.HttpHeaderBuilder;
import jp.ambrosoli.http.client.params.NameValueObject;

import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

/**
 * Apache HttpComponents用のHTTPヘッダーを生成するクラスです。
 * 
 * @author willard379
 * @since 0.0.1
 * 
 */
public class AHCHeaderBuilder implements HttpHeaderBuilder<Header[]> {

    /*
     * (non-Javadoc)
     * 
     * @seejp.ambrosoli.http.client.headers.ConformedHeaderBuilder#
     * createConformedHeaders(jp.ambrosoli.http.client.bean.NameValueObject[],
     * java.lang.String)
     */
    public Header[] createConformedHeaders(final List<NameValueObject> headers) {
        if (headers == null || headers.isEmpty()) {
            return null;
        }

        int size = headers.size();
        Header[] dest = new Header[size];
        for (int i = 0; i < size; i++) {
            NameValueObject nvo = headers.get(i);
            if (nvo == null) {
                continue;
            }
            dest[i] = new BasicHeader(nvo.getName(), nvo.getValue());
        }
        return dest;
    }

}