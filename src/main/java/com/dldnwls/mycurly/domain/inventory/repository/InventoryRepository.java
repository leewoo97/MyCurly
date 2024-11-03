package com.dldnwls.mycurly.domain.inventory.repository;

import com.dldnwls.mycurly.domain.ingredient.repository.custom.IngredientRepositoryCustom;
import com.dldnwls.mycurly.domain.inventory.entity.Inventory;
import com.dldnwls.mycurly.domain.inventory.repository.custom.InventoryRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long>, InventoryRepositoryCustom {
}
