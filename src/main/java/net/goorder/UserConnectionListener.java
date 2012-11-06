package net.goorder;

/**
 *
 * @author witoldsz
 */
public interface UserConnectionListener {

    void connected(OrderGroup orderGroup, Object connectionId);
    
    void disconnected(Object connectionId);
}
