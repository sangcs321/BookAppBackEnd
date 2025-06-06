package com.example.demo.dto.response;

public class CheckQuantityResponse {
    private boolean available;
    private int currentQuantity;
    private String message;

    public CheckQuantityResponse(boolean available, int currentQuantity, String message) {
        this.available = available;
        this.currentQuantity = currentQuantity;
        this.message = message;
    }

    // Getters and Setters
    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getCurrentQuantity() {
        return currentQuantity;
    }

    public void setCurrentQuantity(int currentQuantity) {
        this.currentQuantity = currentQuantity;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
