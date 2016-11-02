package ws_server;

import java.util.ArrayList;

import org.apache.mina.core.session.IoSession;


/**
 *整个应用通用的常量 
 *<br><b>类描述:</b>
 *<pre>|</pre>
 *@see
 *@since
 */
public class CommonConstant
{
   /**
    * 用户对象放到Session中的键名称
    */
   public static final String USER_CONTEXT = "USER_CONTEXT";
   
   /**
    * 将登录前的URL放到Session中的键名称
    */
   public static final String LOGIN_TO_URL = "toUrl";
   
   /**
    * 每页的记录数
    */
   public static final int PAGE_SIZE = 3;
   
   /**
    * 用户登录超时时间间隔
    */
   public static final int TIME_OUT = 300;
   
   /**
    * 全局的session id 清单
    * 
    */
   public static ArrayList<IoSession> g_session_list = new ArrayList<IoSession>();
   
}
