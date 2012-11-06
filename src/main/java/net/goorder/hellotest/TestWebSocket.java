package net.goorder.hellotest;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import org.apache.catalina.websocket.MessageInbound;
import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WebSocketServlet;
import org.apache.catalina.websocket.WsOutbound;

/**
 *
 * @author witoldsz
 */
@WebServlet(urlPatterns="/hello/websocket")
public class TestWebSocket extends WebSocketServlet {

    @Override
    protected StreamInbound createWebSocketInbound(String subProtocol, HttpServletRequest request) {
        return new MessageInbound() {
            {
                System.out.println("Creating connection " + this.hashCode());
                
            }

            @Override
            protected void onOpen(WsOutbound outbound) {
                super.onOpen(outbound);
            }

            @Override
            protected void onBinaryMessage(ByteBuffer message) throws IOException {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            protected void onTextMessage(CharBuffer message) throws IOException {
                CharBuffer cb = CharBuffer.wrap("Hello from " + getClass().getName());
                getWsOutbound().writeTextMessage(cb);
            }
            @Override
            protected void onClose(int status) {
                System.out.println("Closing connection " + this.hashCode());
            }
        };
    }
    
}
