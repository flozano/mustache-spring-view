/*
 * Copyright 2014 the original author or authors.
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
package org.springframework.web.servlet.view.mustache.java;

import java.io.IOException;
import java.io.StringWriter;
import java.util.function.Function;

import org.springframework.web.servlet.i18n.MustacheLocalizationMessageInterceptor;

import jakarta.servlet.http.HttpServletRequest;


public class LocalizationMessageInterceptor extends MustacheLocalizationMessageInterceptor {

    @Override
    protected Object createHelper(final HttpServletRequest request) {
        return new Function<String, String>() {
            @Override
            public String apply(String input) {
                final StringWriter out = new StringWriter();
                try {
                    localize(request, input, out);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                return out.toString();
            }
        };
    }
}
