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
package jp.ambrosoli.quickrestclient.response;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import jp.ambrosoli.quickrestclient.util.InputStreamUtil;
import jp.ambrosoli.quickrestclient.util.StringUtil;

/**
 * HTTP通信のレスポンスボディをバイト配列で保持するクラスです。
 * 
 * @author willard379
 * @since 0.1.0
 */
public class ByteArrayResponseContent implements ResponseContent {

    /** レスポンスデータ */
    protected byte[] data;

    /**
     * BasicResponseContentを生成します。
     */
    public ByteArrayResponseContent() {
        super();
    }

    /**
     * BasicResponseContentを生成します。
     * 
     * @param data
     *            レスポンスデータ
     */
    public ByteArrayResponseContent(final byte[] data) {
        super();
        this.data = data;
    }

    /**
     * レスポンスデータを設定します。
     */
    public void setData(final byte[] data) {
        this.data = data;
    }

    /*
     * (non-Javadoc)
     * 
     * @see jp.ambrosoli.http.client.content.ResponseContent#getAsByteArray()
     */
    public byte[] getAsByteArray() {
        return this.data;
    }

    /*
     * (non-Javadoc)
     * 
     * @see jp.ambrosoli.http.client.content.ResponseContent#getAsInputStream()
     */
    public InputStream getAsInputStream() {
        if (this.data == null) {
            return null;
        }
        return new ByteArrayInputStream(this.data);
    }

    /*
     * (non-Javadoc)
     * 
     * @see jp.ambrosoli.http.client.content.ResponseContent#getAsString()
     */
    public String getAsString() {
        return this.getAsString(StringUtil.DEFAULT_ENCODING);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * jp.ambrosoli.http.client.content.ResponseContent#getAsString(java.lang
     * .String)
     */
    public String getAsString(final String encoding) {
        return StringUtil.toString(this.data, encoding);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * jp.ambrosoli.quickrestclient.response.ResponseContent#writeTo(java.io
     * .ByteArrayOutputStream)
     */
    public void writeTo(final OutputStream output) {
        InputStream input = this.getAsInputStream();
        InputStreamUtil.copy(input, output);
    }
}
