package ws_server;
/**
 * 
 *
 * @author ZhangXuelian
 * @version 
 *
 */
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.prefixedstring.PrefixedStringCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import view.IUpdateViewFactory;
import view.MainView;



public final class TransmitServer {
	public static void main(String[] args) {
		new MainView();
		open_ws_svr();
		open_comm_svr();
	}

	// 服务器监听端口
	private static final int WS_PORT = 1984;
	public static void open_ws_svr() {
		// 服务器端的主要对象
		IoAcceptor acceptor = new NioSocketAcceptor();

		// 设置Filter链
		// acceptor.getFilterChain().addLast("ioFilter", new IoFilterAdapter());

		acceptor.getFilterChain().addLast("coder",
				new ProtocolCodecFilter(new WsEncoder(), new WsDecoder()));

		// 设置消息处理类（创建、关闭Session，可读可写等等，继承自接口IoHandler）
		acceptor.setHandler(new WsServerHandler());
		// 设置接收缓存区大小
		acceptor.getSessionConfig().setReadBufferSize(2048);
		acceptor.getSessionConfig().setIdleTime(IdleStatus.READER_IDLE,
				60 * 60 * 2);
		try {
			// 服务器开始监听
			acceptor.bind(new InetSocketAddress(WS_PORT));
			IUpdateViewFactory.getUpdateView().log("WS服务器已启动");
		} catch (Exception e) {
			e.printStackTrace();
			IUpdateViewFactory.getUpdateView().log(e.getMessage());
		}
	}
	
	private static final int COMM_PORT = 1985;
	public static void open_comm_svr() {
		// 服务器端的主要对象
        IoAcceptor acceptor = new NioSocketAcceptor();  
        acceptor.getFilterChain().addLast( "logger", new LoggingFilter() );  
        acceptor.getFilterChain().addLast( "codec", new ProtocolCodecFilter( new MyProtocolCodecFactory(Charset.forName("UTF-8"))));  
        acceptor.setHandler(  new CommServerHandler() );  
          
        acceptor.getSessionConfig().setReadBufferSize( 2048 );  
                acceptor.getSessionConfig().setIdleTime( IdleStatus.BOTH_IDLE, 10 );  
          
        try {
			acceptor.bind(new InetSocketAddress(COMM_PORT));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
	
}
