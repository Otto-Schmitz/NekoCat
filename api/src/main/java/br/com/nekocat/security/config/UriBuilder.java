package br.com.nekocat.security.config;

import org.springframework.util.MultiValueMap;

import java.net.URI;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

public class UriBuilder implements org.springframework.web.util.UriBuilder {
    @Override
    public org.springframework.web.util.UriBuilder scheme(String scheme) {
        return null;
    }

    @Override
    public org.springframework.web.util.UriBuilder userInfo(String userInfo) {
        return null;
    }

    @Override
    public org.springframework.web.util.UriBuilder host(String host) {
        return null;
    }

    @Override
    public org.springframework.web.util.UriBuilder port(int port) {
        return null;
    }

    @Override
    public org.springframework.web.util.UriBuilder port(String port) {
        return null;
    }

    @Override
    public org.springframework.web.util.UriBuilder path(String path) {
        return null;
    }

    @Override
    public org.springframework.web.util.UriBuilder replacePath(String path) {
        return null;
    }

    @Override
    public org.springframework.web.util.UriBuilder pathSegment(String... pathSegments) throws IllegalArgumentException {
        return null;
    }

    @Override
    public org.springframework.web.util.UriBuilder query(String query) {
        return null;
    }

    @Override
    public org.springframework.web.util.UriBuilder replaceQuery(String query) {
        return null;
    }

    @Override
    public org.springframework.web.util.UriBuilder queryParam(String name, Object... values) {
        return null;
    }

    @Override
    public org.springframework.web.util.UriBuilder queryParam(String name, Collection<?> values) {
        return null;
    }

    @Override
    public org.springframework.web.util.UriBuilder queryParamIfPresent(String name, Optional<?> value) {
        return null;
    }

    @Override
    public org.springframework.web.util.UriBuilder queryParams(MultiValueMap<String, String> params) {
        return null;
    }

    @Override
    public org.springframework.web.util.UriBuilder replaceQueryParam(String name, Object... values) {
        return null;
    }

    @Override
    public org.springframework.web.util.UriBuilder replaceQueryParam(String name, Collection<?> values) {
        return null;
    }

    @Override
    public org.springframework.web.util.UriBuilder replaceQueryParams(MultiValueMap<String, String> params) {
        return null;
    }

    @Override
    public org.springframework.web.util.UriBuilder fragment(String fragment) {
        return null;
    }

    @Override
    public URI build(Object... uriVariables) {
        return null;
    }

    @Override
    public URI build(Map<String, ?> uriVariables) {
        return null;
    }
}
