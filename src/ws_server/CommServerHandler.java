package ws_server;

/**
 * 
 *
 * @author ZhangXuelian
 * @version 
 *
 */

import java.util.Date;
import java.util.Iterator;

import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import ws_server.CommonConstant;

public class CommServerHandler  implements IoHandler{

	public void exceptionCaught(IoSession arg0, Throwable arg1) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void inputClosed(IoSession arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void messageReceived(IoSession session, Object message) throws Exception {
		// TODO Auto-generated method stub
		
        String str = message.toString();  
        
        System.out.println("接受到的消息:"+str);  
          
        if( str.trim().equalsIgnoreCase("quit") ) {  
            //session.close(true);  
            return;  
        }  
        Date date = new Date();  
        session.write( date.toString() );  
        System.out.println("Message written...");  
       
        //message = "";
        WsBean minaBean = new WsBean();//(MinaBean) message;
        minaBean.setContent("xxxxxxxxxxxxxxxxxxxx");
        // 
		Iterator<IoSession> funIter = CommonConstant.g_session_list.iterator();  
		while (funIter.hasNext()) {  
			IoSession fun = funIter.next();  
			// fun.write(message + "xxxxxxxxxxxxxxxxxxxx");
			fun.write(minaBean);
			
		}  
	}

	public void messageSent(IoSession arg0, Object arg1) throws Exception {
		// TODO Auto-generated method stub
        System.out.println("鍙戦�佷俊鎭�:"+arg1.toString());  
	}

	public void sessionClosed(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		  System.out.println("IP:"+session.getRemoteAddress().toString()+"鏂紑杩炴帴");  
	}

	public void sessionCreated(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("IP:"+session.getRemoteAddress().toString());  
	}

	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		// TODO Auto-generated method stub
		 System.out.println( "IDLE " + session.getIdleCount( status ));  
	}

	public void sessionOpened(IoSession arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
