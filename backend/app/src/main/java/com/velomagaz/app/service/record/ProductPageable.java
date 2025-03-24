package com.velomagaz.app.service.record;

import java.util.LinkedList;

import com.velomagaz.app.service.component.ProductRow;

public record ProductPageable(int totalPages, int endPage, LinkedList<ProductRow> productGrid) {

}
