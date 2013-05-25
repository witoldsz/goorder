package net.goorder.app.presentation;

import javax.inject.Inject;
import org.jooq.DSLContext;

/**
 *
 * @author witoldsz
 */
public class OrderingGroupRepo {

    @Inject
    private DSLContext jooq;

    public OrderingGroupView orderingGroup(String groupId) {
        return new OrderingGroupView();
    }
}
