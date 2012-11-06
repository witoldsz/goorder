package net.goorder;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 *
 * @author witoldsz
 */
public class UsersOnlineRegistry implements UserConnectionListener {

    private final Map<Object, OrderGroup> connections = new ConcurrentHashMap<>();
    private final Map<OrderGroup, Object> orderGroups = new ConcurrentHashMap<>(16, 0.75f, 2);
    
    
    @Override
    public void connected(OrderGroup orderGroup, Object connectionId) {
        orderGroups.put(orderGroup, connectionId);
    }

    @Override
    public void disconnected(Object connectionId) {
        orderGroups.remove(connectionId);
    }

    
}
