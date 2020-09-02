package com.example.demo.zuulsvr.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import brave.Tracer;


@Component
public class ResponseFilter extends ZuulFilter {

	@Autowired
	Tracer tracer;
	
	private static final int FILTER_ORDER=1;
	
	private static final Logger logger = LoggerFactory.getLogger(ResponseFilter.class);
	
	@Override
	public Object run() throws ZuulException {
		// TODO Auto-generated method stub
		RequestContext ctx=	RequestContext.getCurrentContext();
		ctx.getResponse().addHeader("tmx-correlation-id", tracer.currentSpan().context().traceIdString());
		return null;
	}

	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return FILTER_ORDER;
	}

	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return "post";
	}

}
