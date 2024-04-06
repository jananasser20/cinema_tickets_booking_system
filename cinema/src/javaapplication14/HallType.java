/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package javaapplication14;

/**
 *
 * @author ADMIN
 */
public enum HallType {
    CHILDREN(0.7),
    Standard(1),
    VIP(2),
    ali(2.3),
    bakr(3);

    double priceMultiplier;

    HallType(double priceMultiplier) {
        this.priceMultiplier = priceMultiplier;
    }

    public double getPriceMultiplier() {
        return priceMultiplier;
    }
}
