package com.narxoz.rpg.vault;

import com.narxoz.rpg.artifact.*;
import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.combatant.HeroMemento;
import com.narxoz.rpg.memento.Caretaker;
import com.narxoz.rpg.visitor.CurseDetector;
import com.narxoz.rpg.visitor.EnchantmentScanner;
import com.narxoz.rpg.visitor.GoldAppraiser;
import com.narxoz.rpg.visitor.WeightCalculator;
import java.util.List;

/**
 * Orchestrates the Chronomancer's Vault demo run.
 */
public class ChronomancerEngine {

    /**
     * Runs the vault sequence for the supplied party.
     *
     * @param party the heroes entering the vault
     * @return a placeholder result in the scaffold
     */
    public VaultRunResult runVault(List<Hero> party) {
        if (party == null || party.isEmpty()) {
            throw new IllegalArgumentException("The vault requires at least one hero.");
        }

        System.out.println("\n=== Chronomancer's Vault opens ===");
        printParty("Party before entering the vault", party);

        Inventory vaultInventory = buildVaultInventory();
        Hero activeHero = party.get(0);
        activeHero.setInventory(vaultInventory.copy());

        System.out.println("\nThe vault presents a mixed artifact inventory: " + vaultInventory.size() + " items.");
        System.out.println(activeHero.getName() + " carries a copy of the vault inventory for the rewind demo.");
        System.out.println("Active hero before appraisal: " + activeHero);

        GoldAppraiser goldAppraiser = new GoldAppraiser();
        EnchantmentScanner enchantmentScanner = new EnchantmentScanner();
        CurseDetector curseDetector = new CurseDetector();
        WeightCalculator weightCalculator = new WeightCalculator();

        runVisitorSection("Visitor #1 - Gold appraisal", vaultInventory, goldAppraiser);
        System.out.println("Gold appraisal total: " + goldAppraiser.getTotalValue()
                + " gold across " + goldAppraiser.getVisitedCount() + " artifacts.");

        runVisitorSection("Visitor #2 - Enchantment scan", vaultInventory, enchantmentScanner);
        System.out.println("Enchantment scan count: " + enchantmentScanner.getScanCount() + " artifacts.");

        runVisitorSection("Visitor #3 - Curse detection", vaultInventory, curseDetector);
        System.out.println("Curse scan summary: " + curseDetector.getCursedCount()
                + " cursed, " + curseDetector.getSafeCount() + " safe.");

        runVisitorSection("Visitor #4 - Weight calculation open/closed proof", vaultInventory, weightCalculator);
        System.out.println("Weight summary: " + weightCalculator.getTotalWeight()
                + " kg total, " + weightCalculator.getHeavyItems() + " heavy items.");

        Caretaker caretaker = new Caretaker();
        System.out.println("\n=== Memento phase: time crystal snapshot ===");
        System.out.println("Before snapshot: " + activeHero);
        caretaker.save(activeHero.createMemento());
        int mementosCreated = caretaker.size();
        System.out.println("Snapshot saved. Caretaker history size: " + caretaker.size());

        System.out.println("\n=== Vault event: temporal trap changes hero state ===");
        triggerTemporalTrap(activeHero);
        System.out.println("After trap:  " + activeHero);

        System.out.println("\n=== Rewind phase: restore from opaque memento ===");
        HeroMemento rewindPoint = caretaker.undo();
        int restoredCount = 0;
        if (rewindPoint != null) {
            activeHero.restoreFromMemento(rewindPoint);
            restoredCount++;
            System.out.println("Rewind complete. Caretaker history size: " + caretaker.size());
        } else {
            System.out.println("No rewind point found.");
        }
        System.out.println("After rewind: " + activeHero);

        printParty("Party after vault sequence", party);

        VaultRunResult result = new VaultRunResult(vaultInventory.size(), mementosCreated, restoredCount);
        System.out.println("\nEngine result: " + result);
        return result;
    }

    private Inventory buildVaultInventory() {
        Inventory inventory = new Inventory();
        inventory.addArtifact(new Weapon("Sunblade", 120, 7, 8));
        inventory.addArtifact(new Potion("Phoenix Tonic", 60, 2, 35));
        inventory.addArtifact(new Scroll("Scroll of Dawn", 90, 1, "Radiant Dawn"));
        inventory.addArtifact(new Ring("Ring of Echoes", 150, 1, 6));
        inventory.addArtifact(new Armor("Aegis Plate", 200, 13, 9));
        inventory.addArtifact(new Scroll("Void Doom Contract", 40, 1, "Void Doom"));
        return inventory;
    }

    private void runVisitorSection(String title, Inventory inventory, ArtifactVisitor visitor) {
        System.out.println("\n--- " + title + " ---");
        inventory.accept(visitor);
    }

    private void triggerTemporalTrap(Hero hero) {
        hero.takeDamage(45);
        hero.spendMana(15);
        hero.spendGold(30);

        Inventory distortedInventory = hero.getInventory().copy();
        distortedInventory.addArtifact(new Potion("Cracked Time Crystal", 1, 1, 0));
        hero.setInventory(distortedInventory);

        System.out.println("The trap drains 45 HP, burns 15 mana, steals 30 gold, and adds a corrupted item.");
    }

    private void printParty(String title, List<Hero> party) {
        System.out.println("\n" + title + ":");
        for (Hero hero : party) {
            System.out.println(" - " + hero);
        }
    }
}
