/**
 *
 */
package org.springframework.web.servlet.view.mustache.java;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Locale;
import java.util.function.Function;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class LocalizationMessageInterceptorTest {

    private MessageSource messageSource;
    private LocaleResolver localeResolver;

    private LocalizationMessageInterceptor interceptor;
    private HttpServletRequest request;
    private HttpServletResponse response;

    private final Object handler = new Object();

    private static final String messageKey = "foo";

    @Before
    public void setUp() throws Exception {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        localeResolver = mock(LocaleResolver.class);
        messageSource = mock(MessageSource.class);
        interceptor = new LocalizationMessageInterceptor();
        interceptor.setLocaleResolver(localeResolver);
        interceptor.setMessageSource(messageSource);
        interceptor.setMessageKey(messageKey);
    }

    @Test
    public void test() throws Exception {

        final ModelAndView modelAndView = mock(ModelAndView.class);
        final ArgumentCaptor<Function> captor = ArgumentCaptor.forClass(Function.class);

        interceptor.postHandle(request, response, handler, modelAndView);

        verify(modelAndView).addObject(eq(messageKey), captor.capture());

        // exercise the in-lined Lambda
        final Function<String, String> function = captor.getValue();
        assertNotNull(function);


        final String fragResult = "bar";
        final String fragResultWithArgs = "bar  [foo] [baz][burp]";

        when(localeResolver.resolveLocale(request)).thenReturn(Locale.CANADA_FRENCH);

        function.apply(fragResult);

        verify(messageSource, times(1)).getMessage(fragResult, new Object[]{}, Locale.CANADA_FRENCH);

        function.apply(fragResultWithArgs);

        verify(messageSource, times(1)).getMessage(fragResult, new Object[]{"foo", "baz", "burp"}, Locale.CANADA_FRENCH);

        verifyNoMoreInteractions(messageSource);

    }
}
