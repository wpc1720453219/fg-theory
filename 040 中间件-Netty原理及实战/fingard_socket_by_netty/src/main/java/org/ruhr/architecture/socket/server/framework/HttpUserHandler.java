package org.ruhr.architecture.socket.server.framework;

import org.ruhr.architecture.socket.server.threadpool.ThreadPoolManager4User;

import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.DefaultHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HttpUserHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
		// 耗时逻辑给业务线程池来做
		ThreadPoolManager4User.submitOnceTask(new Runnable() {

			@Override
			public void run() {
				String uri = request.getUri();
				DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
						HttpResponseStatus.OK);
				response.headers().set("content-type", "application/json");
				response.headers().set("connection", "close");
				String json = "{\"time\":";
				for(int index=0;index<10000;index++) {
					json+=""+ System.currentTimeMillis();
				}
				json += "}";
				response.content().writeBytes(json.getBytes());
				ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
			}

		});

	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// 对这条链接上的异常做兜底处理-不传给tail
		log.error("exceptionCaught {}", cause, cause);
		ctx.close();
	}

}
