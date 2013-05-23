package net.goorder.app.presentation;

import lombok.Data;
import net.goorder.app.domain.*;

/**
 * A single item of the specific order.
 * @author witoldsz
 */
@Data
public class OrderLineView {

    private Long id;

    private String who, what;

    private Integer price;

    private Integer paid;
}
