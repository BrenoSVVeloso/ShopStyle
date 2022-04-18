package com.microservice.bffshop.dto.history;


import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoryDTO {

    @NotNull
    private UserHistoryDTO user;

    @NotNull
    private PurchaseHistoryDTO purchases;

}
