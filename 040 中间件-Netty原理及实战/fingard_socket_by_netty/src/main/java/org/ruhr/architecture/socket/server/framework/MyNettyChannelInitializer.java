package org.ruhr.architecture.socket.server.framework;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.util.AttributeKey;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyNettyChannelInitializer extends ChannelInitializer<SocketChannel> {

	public MyNettyChannelInitializer() {

	}

	@Override
	protected void initChannel(SocketChannel channel) throws Exception {
		
		ChannelPipeline pipeline=channel.pipeline();
		//这里以HTTP服务器为例
		pipeline.addLast("http_decoder", new HttpRequestDecoder());
		pipeline.addLast("http_aggregator", new HttpObjectAggregator(65536));
		pipeline.addLast("http_encoder", new HttpResponseEncoder());
		pipeline.addLast("http_chunked", new ChunkedWriteHandler());
		// 真正处理用户级业务逻辑的地方
		pipeline.addLast("http_user_defined", new HttpUserHandler());
		

		
	}

}