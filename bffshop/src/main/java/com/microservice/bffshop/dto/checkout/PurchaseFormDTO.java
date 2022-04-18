package com.microservice.bffshop.dto.checkout;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class PurchaseFormDTO {

    private int user_id;

    private int payment_id;

    private List<CartDTO> cart;
}
