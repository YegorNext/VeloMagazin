package com.velomagaz.app.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Component;

import com.velomagaz.app.ViewModel.CartItemViewModel;
import com.velomagaz.app.ViewModel.CartViewModel;

@Component
public class CartTotalCalculator {
    public BigDecimal calculateTotalPrice(CartViewModel cartViewModel) {
        List<CartItemViewModel> items = cartViewModel.getCartItems();
        BigDecimal total = BigDecimal.ZERO;

        if (items != null) {
            for (CartItemViewModel item : items) {
                BigDecimal itemTotal = item.getPrice().multiply(BigDecimal.valueOf(item.getCount()));
                total = total.add(itemTotal);
            }
        }

        return total;
    }
}
