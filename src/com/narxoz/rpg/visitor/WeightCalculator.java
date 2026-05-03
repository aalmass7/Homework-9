package com.narxoz.rpg.visitor;

import com.narxoz.rpg.artifact.*;

import javax.lang.model.type.ArrayType;

public class WeightCalculator implements ArtifactVisitor {

    private int totalWeight;
    private int heavyItems;

    @Override
    public void visit(Weapon weapon) {
        boolean heavy = weapon.getWeight() >= 7;
        record(weapon.getName(), weapon.getWeight(), heavy, "Weapon weight check");
    }

    @Override
    public void visit(Potion potion) {
        boolean heavy = potion.getWeight() >= 3;
        record(potion.getName(), potion.getWeight(), heavy, "Potion weight check");
    }

    @Override
    public void visit(Scroll scroll) {
        boolean heavy = false;
        record(scroll.getName(), scroll.getWeight(), heavy, "Scroll weight check");
    }

    @Override
    public void visit(Ring ring) {
        boolean heavy = false;
        record(ring.getName(), ring.getWeight(), heavy, "Ring weight check");
    }

    @Override
    public void visit(Armor armor) {
        boolean heavy = armor.getWeight() >= 10;
        record(armor.getName(), armor.getWeight(), heavy, "Armor weight check");
    }

    public int getTotalWeight() {
        return totalWeight;
    }

    public int getHeavyItems() {
        return heavyItems;
    }

    private void record(String name, int weight, boolean heavy, String note) {
        totalWeight += weight;

        if (heavy) {
            heavyItems++;
            System.out.println("[WeightCalculator] " + name
                    + " -> " + weight + " kg, heavy | " + note);
        } else {
            System.out.println("[WeightCalculator] " + name
                    + " -> " + weight + " kg, light | " + note);
        }
    }
}
