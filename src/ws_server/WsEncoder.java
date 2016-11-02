package ws_server;
/**
 * 
 *
 * @author ZhangXuelian
 * @version 
 *
 */
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.apache.mina.filter.codec.demux.DemuxingProtocolEncoder;
import org.apache.mina.filter.codec.demux.MessageEncoder;

import ws_server.WebSocketUtil;

//编码器
public class WsEncoder extends DemuxingProtocolEncoder {
	public WsEncoder() {
		addMessageEncoder(WsBean.class, new BaseSocketBeanEncoder());
	}

	class BaseSocketBeanEncoder implements MessageEncoder<WsBean> {
		public void encode(IoSession session, WsBean message,
				ProtocolEncoderOutput out) throws Exception {
			byte[] _protocol = null;
			if (message.isWebAccept()) {
				_protocol = message.getContent().getBytes("UTF-8");
			} else {
				_protocol = WebSocketUtil.encode(message.getContent());
			}
			int length = _protocol.length;
			IoBuffer buffer = IoBuffer.allocate(length);
			buffer.put(_protocol);
			buffer.flip();
			out.write(buffer);
		}
	}
}