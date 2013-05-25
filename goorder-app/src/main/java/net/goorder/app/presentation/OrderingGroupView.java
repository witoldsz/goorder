package net.goorder.app.presentation;

import java.util.List;
import lombok.Data;
import net.goorder.app.domain.*;

/**
 * @author witoldsz
 */
@Data
public class OrderingGroupView {

    private String id;

    private List<OrderingTableView> tables;
}
