package org.ruhr.architecture.socket.server.framework;

import org.ruhr.architecture.socket.server.NettyServer;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;

public class MyChannelFutureListener implements ChannelFutureListener {

	private final long begin = System.currentTimeMillis();
	private final String subKey;

	public MyChannelFutureListener(String s) {
		subKey = s;
	}

	@Override
	public void operationComplete(ChannelFuture future) throws Exception {
		if (null == future || null == future.channel()) {
			return;
		}
		int writeAndFlushSucceed = 1;
		if (!future.isSuccess()) {
			writeAndFlushSucceed = 0;
			// 非常关键的一步
			future.channel().close();
		}

	}
}
