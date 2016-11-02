package ws_server;
/**
 * 
 *
 * @author ZhangXuelian
 * @version 
 *
 */
import java.util.Collection;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import ws_server.CommonConstant;
import ws_server.WebSocketUtil;
import view.IUpdateViewFactory;

public class WsServerHandler extends IoHandlerAdapter {

	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		IUpdateViewFactory.getUpdateView().log(
				"[exceptionCaught] "
						+ (cause != null ? cause.getMessage() : ""));
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		super.messageSent(session, message);
		IUpdateViewFactory.getUpdateView().log(
				"[messageSent] [" + session.getRemoteAddress() + "] "
						+ message.toString());
	}

	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		IUpdateViewFactory.getUpdateView().log(
				"[messageReceived] " + message.toString());
		WsBean minaBean = (WsBean) message;
		if (minaBean.isWebAccept()) {
			WsBean sendMessage = minaBean;
			sendMessage.setContent(WebSocketUtil.getSecWebSocketAccept(minaBean
					.getContent()));
			session.write(sendMessage);
		} else {
			Collection<IoSession> ioSessionSet = session.getService()
					.getManagedSessions().values();
			for (IoSession is : ioSessionSet) {
				is.write(message);
			}
		}
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		super.sessionClosed(session);
		IUpdateViewFactory.getUpdateView().updateLineNumber(
				session.getService().getManagedSessionCount());
		IUpdateViewFactory.getUpdateView().log("[sessionClosed]");
		CommonConstant.g_session_list.remove(session);
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
		super.sessionIdle(session, status);
		IUpdateViewFactory.getUpdateView().log(
				"[sessionIdle] " + status.toString() + ","
						+ session.getRemoteAddress());
		session.close(false);
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		super.sessionCreated(session);
		IUpdateViewFactory.getUpdateView().updateLineNumber(
				session.getService().getManagedSessionCount());
		IUpdateViewFactory.getUpdateView().log("[sessionCreated]");
		// add it to list
		CommonConstant.g_session_list.add(session);
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		super.sessionOpened(session);
		IUpdateViewFactory.getUpdateView().log(
				"[sessionOpened] " + session.getRemoteAddress());
	}
}
