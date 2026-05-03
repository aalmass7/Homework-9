package com.narxoz.rpg.visitor;

import com.narxoz.rpg.artifact.*;

public class EnchantmentScanner implements ArtifactVisitor {

    private int scanCount;

    @Override
    public void visit(Weapon weapon) {
        scanCount++;
        System.out.println("[EnchantmentScanner] Weapon: " + weapon.getName()
                + " has battle magic. Attack bonus: " + weapon.getAttackBonus());
    }

    @Override
    public void visit(Potion potion) {
        scanCount++;
        System.out.println("[EnchantmentScanner] Potion: " + potion.getName()
                + " has healing magic. Healing: " + potion.getHealing());
    }

    @Override
    public void visit(Scroll scroll) {
        scanCount++;
        System.out.println("[EnchantmentScanner] Scroll: " + scroll.getName()
                + " contains spell: " + scroll.getSpellName());
    }

    @Override
    public void visit(Ring ring) {
        scanCount++;
        System.out.println("[EnchantmentScanner] Ring: " + ring.getName()
                + " has mana magic. Magic bonus: " + ring.getMagicBonus());
    }

    @Override
    public void visit(Armor armor) {
        scanCount++;
        System.out.println("[EnchantmentScanner] Armor: " + armor.getName()
                + " has protection magic. Defense bonus: " + armor.getDefenseBonus());
    }

    public int getScanCount() {
        return scanCount;
    }
}
