package com.learning.backend.basedomains.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Orders {

    private String orderId;
    private String orderName;
    private int qty;
    private Double price;

}
