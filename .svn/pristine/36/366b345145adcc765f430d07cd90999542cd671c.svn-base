//created by zhang jian xin 2016 @IBM
package com.ibm.cloud.im.config;
import java.security.Principal;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;
public class HandshakeHandler extends DefaultHandshakeHandler{
    private static Log logger = LogFactory.getLog(HandshakeHandler.class);
    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, 
                                      Map<String, Object> attributes) {
        // add your own code to determine the user
        return super.determineUser(request, wsHandler, attributes);
    }   
}
