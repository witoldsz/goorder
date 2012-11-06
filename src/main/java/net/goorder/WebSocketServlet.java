package net.goorder;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.Map;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import org.apache.catalina.websocket.MessageInbound;
import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WsOutbound;

/**
 *
 * @author witoldsz
 */
@WebServlet(urlPatterns="/websocket")
public class WebSocketServlet extends org.apache.catalina.websocket.WebSocketServlet {
    
    private final UserConnectionListener listener;

    @Override
    protected StreamInbound createWebSocketInbound(String subProtocol, HttpServletRequest request) {
        return new MessageInbound() {

            @Override
            protected void onBinaryMessage(ByteBuffer message) throws IOException {
                throw new UnsupportedOperationException("Not supported.");
            }

            @Override
            protected void onTextMessage(CharBuffer message) throws IOException {
                String m = message.toString();
                int contentIndex = m.indexOf('\n');
                if (contentIndex < 1) {
                    return; //ignore
                }
                String command = m.substring(0, contentIndex - 1);
                String content = m.substring(contentIndex);
                dispatch(command, content);
            }
            @Override
            protected void onClose(int status) {
                System.out.println("Closing connection " + this.hashCode());
            }
            
            private Void dispatch(String command, String content) {
                switch (command) {
                    case "orderGroupId": return orderGroupId(content, this);
                }
                return null;
            }
        };
    }
    
    private Void orderGroupId(String orderGroupId, MessageInbound messageInbound) {
        listener.connected(new OrderGroup(orderGroupId), connectionId(messageInbound));
        return null;
    }
    
    private Object connectionId(MessageInbound messageInbound) {
        return System.identityHashCode(messageInbound);
    }
    
}
