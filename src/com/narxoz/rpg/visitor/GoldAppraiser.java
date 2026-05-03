package com.narxoz.rpg.visitor;

import com.narxoz.rpg.artifact.*;

public class GoldAppraiser implements ArtifactVisitor {

    private int totalValue;
    private int visitedCount;

    @Override
    public void visit(Weapon weapon) {
        int estimate = weapon.getValue() + weapon.getAttackBonus() * 12;
        visitedCount++;
        totalValue += estimate;

        System.out.println("[GoldAppraiser] Weapon: " + weapon.getName()
                + " -> " + estimate + " gold"
                + " | attack bonus: " + weapon.getAttackBonus());
    }

    @Override
    public void visit(Potion potion) {
        int estimate = potion.getValue() + potion.getHealing() * 3;
        visitedCount++;
        totalValue += estimate;

        System.out.println("[GoldAppraiser] Potion: " + potion.getName()
                + " -> " + estimate + " gold"
                + " | healing: " + potion.getHealing());
    }

    @Override
    public void visit(Scroll scroll) {
        int estimate = scroll.getValue() + scroll.getSpellName().length() * 5;
        visitedCount++;
        totalValue += estimate;

        System.out.println("[GoldAppraiser] Scroll: " + scroll.getName()
                + " -> " + estimate + " gold"
                + " | spell: " + scroll.getSpellName());
    }

    @Override
    public void visit(Ring ring) {
        int estimate = ring.getValue() + ring.getMagicBonus() * 25;
        visitedCount++;
        totalValue += estimate;

        System.out.println("[GoldAppraiser] Ring: " + ring.getName()
                + " -> " + estimate + " gold"
                + " | magic bonus: " + ring.getMagicBonus());
    }

    @Override
    public void visit(Armor armor) {
        int estimate = armor.getValue() + armor.getDefenseBonus() * 15;
        visitedCount++;
        totalValue += estimate;

        System.out.println("[GoldAppraiser] Armor: " + armor.getName()
                + " -> " + estimate + " gold"
                + " | defense bonus: " + armor.getDefenseBonus());
    }

    public int getTotalValue() {
        return totalValue;
    }

    public int getVisitedCount() {
        return visitedCount;
    }
}
