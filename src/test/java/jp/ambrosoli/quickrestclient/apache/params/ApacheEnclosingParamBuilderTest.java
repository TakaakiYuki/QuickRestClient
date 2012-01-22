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
package jp.ambrosoli.quickrestclient.apache.params;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import jp.ambrosoli.quickrestclient.apache.params.ApacheEnclosingParamBuilder;
import jp.ambrosoli.quickrestclient.params.NameValueObject;
import jp.ambrosoli.quickrestclient.params.RequestParams;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ApacheEnclosingParamBuilderTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testGetConformedParams() {

        // Arrange
        List<NameValueObject> params = new ArrayList<NameValueObject>();
        params.add(new NameValueObject("Name1", "Value1"));
        params.add(new NameValueObject("Name2", "Value2"));
        params.add(new NameValueObject("Name3", "Value3"));
        RequestParams reqParams = new RequestParams(params);

        // Act
        ApacheEnclosingParamBuilder builder = new ApacheEnclosingParamBuilder();
        UrlEncodedFormEntity entity = reqParams.getConformedParams(builder);

        // Assert
        assertThat(entity, is(notNullValue()));

    }

    @Test
    public void testGetConformedParams_UTF8() {

        // Arrange
        List<NameValueObject> params = new ArrayList<NameValueObject>();
        params.add(new NameValueObject("Name1", "Value1"));
        params.add(new NameValueObject("Name2", "Value2"));
        params.add(new NameValueObject("Name3", "Value3"));
        RequestParams reqParams = new RequestParams(params);

        // Act
        ApacheEnclosingParamBuilder builder = new ApacheEnclosingParamBuilder();
        UrlEncodedFormEntity entity = reqParams.getConformedParams(builder, "UTF-8");

        // Assert
        assertThat(entity, is(notNullValue()));
    }

    @Test
    public void testCreateConformedParams() {

        // Arrange
        List<NameValueObject> params = new ArrayList<NameValueObject>();
        params.add(new NameValueObject("Name1", "Value1"));
        params.add(new NameValueObject("Name2", "Value2"));
        params.add(new NameValueObject("Name3", "Value3"));

        // Act
        ApacheEnclosingParamBuilder builder = new ApacheEnclosingParamBuilder();
        UrlEncodedFormEntity entity = builder.createConformedParams(params, "UTF-8");

        // Assert
        assertThat(entity, is(notNullValue()));
    }

    @Test
    public void testCreateConformedParams_NullParams() {

        // Act
        ApacheEnclosingParamBuilder builder = new ApacheEnclosingParamBuilder();
        UrlEncodedFormEntity entity = builder.createConformedParams(null, "UTF-8");

        // Assert
        assertThat(entity, is(notNullValue()));

    }

    @Test
    public void testCreateConformedParams_EmptyParam() {

        // Arrange
        ArrayList<NameValueObject> params = new ArrayList<NameValueObject>();
        String charset = "UTF-8";

        // Act
        ApacheEnclosingParamBuilder builder = new ApacheEnclosingParamBuilder();
        UrlEncodedFormEntity entity = builder.createConformedParams(params, charset);

        // Assert
        assertThat(entity, is(notNullValue()));

    }

    @Test
    public void testCreateConformedParams_NullCharset() throws Exception {

        // Arrange
        List<NameValueObject> params = new ArrayList<NameValueObject>();
        params.add(new NameValueObject("Name1", "Value1"));
        params.add(new NameValueObject("Name2", "Value2"));
        params.add(new NameValueObject("Name3", "Value3"));

        // Act
        ApacheEnclosingParamBuilder builder = new ApacheEnclosingParamBuilder();
        UrlEncodedFormEntity entity = builder.createConformedParams(params, null);

        // Assert
        assertThat(entity, is(notNullValue()));
    }

    @Test
    public void testCreateConformedParams_InvalidCharset() throws Exception {

        // Arrange
        List<NameValueObject> params = new ArrayList<NameValueObject>();
        params.add(new NameValueObject("Name1", "Value1"));
        params.add(new NameValueObject("Name2", "Value2"));
        params.add(new NameValueObject("Name3", "Value3"));

        String charset = "UTF-48";

        // Expected
        this.expectedException.expect(IllegalArgumentException.class);

        // Act
        ApacheEnclosingParamBuilder builder = new ApacheEnclosingParamBuilder();
        builder.createConformedParams(params, charset);

    }

    @Test
    public void testCreateNameValuePairList() {

        // Arrange
        List<NameValueObject> params = new ArrayList<NameValueObject>();
        params.add(new NameValueObject("Name1", "Value1"));
        params.add(new NameValueObject("Name2", "Value2"));
        params.add(new NameValueObject("Name3", "Value3"));

        // Act
        ApacheEnclosingParamBuilder builder = new ApacheEnclosingParamBuilder();
        List<NameValuePair> dest = builder.createNameValuePairList(params);

        // Assert
        assertThat(dest, is(notNullValue()));
        assertThat(dest.size(), is(params.size()));

    }

    @Test
    public void testCreateNameValuePairList_NullParam() {

        // Act
        ApacheEnclosingParamBuilder builder = new ApacheEnclosingParamBuilder();
        List<NameValuePair> dest = builder.createNameValuePairList(null);

        // Assert
        assertThat(dest, is(notNullValue()));
        assertThat(dest.isEmpty(), is(true));

    }

    @Test
    public void testCreateNameValuePairList_EmptyParam() {

        // Arrange
        ArrayList<NameValueObject> params = new ArrayList<NameValueObject>();

        // Act
        ApacheEnclosingParamBuilder builder = new ApacheEnclosingParamBuilder();
        List<NameValuePair> dest = builder.createNameValuePairList(params);

        // Assert
        assertThat(dest, is(notNullValue()));
        assertThat(dest.isEmpty(), is(true));

    }

    @Test
    public void testCreateNameValuePairList_ContainsNull() {

        // Arrange
        List<NameValueObject> params = new ArrayList<NameValueObject>();
        params.add(null);
        params.add(null);
        params.add(null);

        // Act
        ApacheEnclosingParamBuilder builder = new ApacheEnclosingParamBuilder();
        List<NameValuePair> dest = builder.createNameValuePairList(params);

        // Assert
        assertThat(dest, is(notNullValue()));
        assertThat(dest.isEmpty(), is(true));

    }

    @Test
    public void testToNameValuePair() {

        // Arrange
        NameValueObject nvo = new NameValueObject("name", "value");

        // Act
        ApacheEnclosingParamBuilder builder = new ApacheEnclosingParamBuilder();
        NameValuePair nameValuePair = builder.toNameValuePair(nvo);

        // Assert
        assertThat(nameValuePair, is(notNullValue()));
        assertThat(nameValuePair.getName(), is(equalTo(nvo.getName())));
        assertThat(nameValuePair.getValue(), is(equalTo(nvo.getValue())));
        assertThat(builder.toNameValuePair(null), is(nullValue()));
    }

    @Test
    public void testToNameValuePair_NullParam() {

        // Act
        ApacheEnclosingParamBuilder builder = new ApacheEnclosingParamBuilder();
        NameValuePair nameValuePair = builder.toNameValuePair(null);

        // Assert
        assertThat(nameValuePair, is(nullValue()));
    }

}