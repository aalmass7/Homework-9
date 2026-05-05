package com.narxoz.rpg;

import com.narxoz.rpg.artifact.Inventory;
import com.narxoz.rpg.artifact.Ring;
import com.narxoz.rpg.artifact.Weapon;
import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.vault.ChronomancerEngine;
import com.narxoz.rpg.vault.VaultRunResult;
import java.util.List;

/**
 * Entry point for Homework 9 — Chronomancer's Vault: Visitor + Memento.
 *
 * The scaffold prints the banner only; students fill in the vault demo.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("=== Homework 9 Demo: Visitor + Memento ===");

        Inventory asterionPack = new Inventory();
        asterionPack.addArtifact(new Weapon("Starforged Spear", 50, 6, 5));

        Inventory niraelPack = new Inventory();
        niraelPack.addArtifact(new Ring("Moonphase Band", 65, 1, 4));

        Hero asterion = new Hero("Asterion", 115, 40, 14, 9, 95, asterionPack);
        Hero nirael = new Hero("Nirael", 75, 125, 6, 4, 150, niraelPack);

        ChronomancerEngine engine = new ChronomancerEngine();
        VaultRunResult result = engine.runVault(List.of(asterion, nirael));

        System.out.println("\nFinal VaultRunResult printed by Main: " + result);
    }
}
