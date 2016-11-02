package ws_server;
/**
 * 
 *
 * @author ZhangXuelian
 * @version 
 *
 */
public class MyProtocolPack {     
    private int length;     
    private byte flag;     
    private String content;     
         
    public MyProtocolPack(){     
             
    }     
         
    public MyProtocolPack(byte flag,String content){     
        this.flag=flag;     
        this.content=content;     
        int len1=content==null?0:content.getBytes().length;     
        this.length=5+len1;     
    }     
         
    public MyProtocolPack(byte[] bs){     
        if(bs!=null && bs.length>=5){     
           // length=GFCommon.bytes2int(GFCommon.bytesCopy(bs, 0, 4));     
           // flag=bs[4];     
           // content=new String(GFCommon.bytesCopy(bs, 5, length-5));     
        }     
    }     
         
    public int getLength() {     
        return length;     
    }     
    public void setLength(int length) {     
        this.length = length;     
    }     
    public byte getFlag() {     
        return flag;     
    }     
    public void setFlag(byte flag) {     
        this.flag = flag;     
    }     
    public String getContent() {     
        return content;     
    }     
    public void setContent(String content) {     
        this.content = content;     
    }     
         
    public String toString(){     
        StringBuffer sb=new StringBuffer();     
        sb.append(" Len:").append(length);     
        sb.append(" flag:").append(flag);     
        sb.append(" content:").append(content);     
        return sb.toString();     
    }     
}    