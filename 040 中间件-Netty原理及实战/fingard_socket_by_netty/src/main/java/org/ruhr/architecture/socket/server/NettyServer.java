package org.ruhr.architecture.socket.server;

import java.util.ArrayList;

import java.util.List;

import org.ruhr.architecture.config.GlobalConfig;

import org.ruhr.architecture.socket.server.framework.MyNettyChannelInitializer;

import org.ruhr.architecture.socket.server.threadpool.ThreadPoolManager4User;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import org.springframework.stereotype.Component;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.DefaultThreadFactory;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author liuzq
 * @apiNote 增加系统注释
 */
@Component
@Slf4j
@Data
public class NettyServer implements ApplicationContextAware, CommandLineRunner {

	@SuppressWarnings("unused")
	private static volatile Thread LISTEN_THREAD = null;
	public volatile ApplicationContext applicationContext = null;
	@Autowired
	GlobalConfig config;

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		this.applicationContext = context;
	}

	@Override
	public void run(String... args) throws Exception {

		try {

			// 启动前先把业务线程池启动起来
			ThreadPoolManager4User.start();

			// 生成netty服务
			startNettyServer();

		} catch (Exception e) {
			log.error("fail to execute due to {}", e);
			System.exit(-1);
		}

	}

	/**
	 * @param ports
	 * @apiNote-启动netty服务器-注意启动的话会阻塞,所以需要放在一个单独的线程里
	 */

	private void startNettyServer() {
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				startNettyServer0();
			}
		};
		Thread thread = new Thread(runnable);
		thread.setName("GlobalNettyListenerThreadInvoker");
		thread.setDaemon(true);
		thread.start();
		LISTEN_THREAD = thread;
	}

	/**
	 * @param ports
	 * @apiNote Configure the server.
	 */

	private void startNettyServer0() {

		int bossThreadCount = 1;
		int workerThreadCount = Math.min(Runtime.getRuntime().availableProcessors() + 1, 32);
		
		EventLoopGroup bossGroup = new NioEventLoopGroup(bossThreadCount,
				new DefaultThreadFactory("Fingard-Boss", true));
		
		EventLoopGroup workerGroup = new NioEventLoopGroup(workerThreadCount,
				new DefaultThreadFactory("RFingard-Worker", true));
		
		log.info("Thread count-> FingardNettyServerBoss {} , FingardNettyWorker {}", bossThreadCount, workerThreadCount);

		try {
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(bossGroup, workerGroup)
					// 发送缓冲区可以研读-https://segmentfault.com/a/1190000021488755?utm_source=tag-newest
					// options-see https://www.cnblogs.com/googlemeoften/p/6082785.html
					// see https://zhuanlan.zhihu.com/p/88430254
					.channel(NioServerSocketChannel.class)

					.option(ChannelOption.SO_BACKLOG, 1024)//
					.option(ChannelOption.SO_REUSEADDR, Boolean.TRUE)

					// io-socket相关
					.childOption(ChannelOption.SO_REUSEADDR, Boolean.TRUE)// reuse addr
					.childOption(ChannelOption.TCP_NODELAY, Boolean.TRUE)//
					// 主要是考虑海量连接下的内存占用
					.childOption(ChannelOption.SO_SNDBUF, 4 * 1024)// send buffer
					.childOption(ChannelOption.SO_RCVBUF, 4 * 1024)// recv buffer
					// .childOption(ChannelOption.SO_LINGER, 0)// linger
					// .childOption(ChannelOption.SO_KEEPALIVE, true)//
					// handler
					.childHandler(new MyNettyChannelInitializer());

			log.info("try to bind all ports for netty server");
			List<ChannelFuture> bindFutures = new ArrayList<ChannelFuture>();
			bindFutures.add(bootstrap.bind(9999).sync());
			log.info("succeed to bind port {} by netty server", 9999);

			// Wait until all the server socket is closed.
			// 单个端口处问题-不影响其它端口-尽可能减少损失
			for (ChannelFuture future : bindFutures) {
				try {
					// 这里会一直等待
					future.channel().closeFuture().sync();
				} catch (Throwable e) {
					log.error("error due to {}", e);

				}
			}

		} catch (Exception e) {
			log.error("error due to {}", e);

		} finally {
			// 先关闭boss 再关闭worker
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
			log.error("the server is closed , please review the log with ERROR label");
			System.exit(-1);
		}

	}

}
