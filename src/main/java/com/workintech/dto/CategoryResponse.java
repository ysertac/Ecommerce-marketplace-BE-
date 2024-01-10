package com.workintech.dto;

import com.workintech.entity.Gender;

public record CategoryResponse(String title, String img, double rating ,Gender gender) {
}
