package com.workintech.dto;

public record ProductResponse(String name, String description, double rating,
                              double price, int stock, String image) {
}
