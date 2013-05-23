package net.goorder.app.presentation;

import java.util.List;
import org.joda.time.LocalDateTime;

/**
 * This is a single order of a specific {@link OrderGroup}.
 * @author witoldsz
 */
public class OrderingTableView {

    private Long id;

    private String label;

    private String place;

    private LocalDateTime deadline;

    private String comments;

    private List<OrderLineView> items;
}
