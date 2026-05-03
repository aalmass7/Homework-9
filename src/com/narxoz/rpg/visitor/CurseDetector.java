package com.narxoz.rpg.visitor;

import com.narxoz.rpg.artifact.*;

import java.util.Locale;

public class CurseDetector implements ArtifactVisitor {

    private int cursedCount;
    private int safeCount;

    @Override
    public void visit(Weapon weapon) {
        boolean cursed = weapon.getAttackBonus() >= 10 || containsDarkWord(weapon.getName());
        report(weapon.getName(), cursed, "Weapon curse check");
    }

    @Override
    public void visit(Potion potion) {
        boolean cursed = potion.getHealing() < 20 || containsDarkWord(potion.getName());
        report(potion.getName(), cursed, "Potion curse check");
    }

    @Override
    public void visit(Scroll scroll) {
        boolean cursed = containsDarkWord(scroll.getSpellName()) || containsDarkWord(scroll.getName());
        report(scroll.getName(), cursed, "Scroll curse check. Spell: " + scroll.getSpellName());
    }

    @Override
    public void visit(Ring ring) {
        boolean cursed = ring.getMagicBonus() >= 12 || containsDarkWord(ring.getName());
        report(ring.getName(), cursed, "Ring curse check");
    }

    @Override
    public void visit(Armor armor) {
        boolean cursed = armor.getDefenseBonus() >= 10 || containsDarkWord(armor.getName());
        report(armor.getName(), cursed, "Armor curse check");
    }

    public int getCursedCount() {
        return cursedCount;
    }

    public int getSafeCount() {
        return safeCount;
    }

    private void report(String name, boolean cursed, String note) {
        if (cursed) {
            cursedCount++;
            System.out.println("[CurseDetector] " + name + " -> CURSED | " + note);
        } else {
            safeCount++;
            System.out.println("[CurseDetector] " + name + " -> safe | " + note);
        }
    }

    private boolean containsDarkWord(String text) {
        String normalized = text == null ? "" : text.toLowerCase(Locale.ROOT);

        return normalized.contains("curse")
                || normalized.contains("blood")
                || normalized.contains("doom")
                || normalized.contains("void")
                || normalized.contains("shadow");
    }
}
