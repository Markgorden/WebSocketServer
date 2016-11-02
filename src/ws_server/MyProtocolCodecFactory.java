package ws_server;
/**
 * 
 *
 * @author ZhangXuelian
 * @version 
 *
 */
import java.nio.charset.Charset;     
import org.apache.mina.core.session.IoSession;     
import org.apache.mina.filter.codec.ProtocolCodecFactory;     
import org.apache.mina.filter.codec.ProtocolDecoder;     
import org.apache.mina.filter.codec.ProtocolEncoder;     
public class MyProtocolCodecFactory   implements ProtocolCodecFactory {     
        //private final MyProtocalEncoder encoder;     
        private MyProtocolParse decoder = null;     
             
        public MyProtocolCodecFactory(Charset charset) {     
           // encoder=new MyProtocalEncoder(charset);     
            decoder=new MyProtocolParse(charset);     
        }     
              
        public ProtocolEncoder getEncoder(IoSession session) {     
            return null;     
        }     
        public ProtocolDecoder getDecoder(IoSession session) {     
            return decoder;     
        }     
             
}    