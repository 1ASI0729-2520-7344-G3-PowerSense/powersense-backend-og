package com.powersense.shared.infrastructure.persistence.jpa.strategy;

import io.github.encryptorcode.pluralize.Pluralize;
import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

import static io.github.encryptorcode.pluralize.Pluralize.pluralize;

/**
 * Snake Case With Pluralized Table Physical Naming Strategy
 * Custom naming strategy for JPA entities:
 * - Tables: snake_case and pluralized (e.g., Device -> devices, InventoryItem -> inventory_items)
 * - Columns: snake_case (e.g., deviceName -> device_name)
 * - Schemas: snake_case
 */
public class SnakeCaseWithPluralizedTablePhysicalNamingStrategy implements PhysicalNamingStrategy {

    @Override
    public Identifier toPhysicalCatalogName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return this.toSnakeCase(identifier);
    }

    @Override
    public Identifier toPhysicalSchemaName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return this.toSnakeCase(identifier);
    }

    @Override
    public Identifier toPhysicalTableName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return this.toSnakeCasePluralized(identifier);
    }

    @Override
    public Identifier toPhysicalSequenceName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return this.toSnakeCase(identifier);
    }

    @Override
    public Identifier toPhysicalColumnName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return this.toSnakeCase(identifier);
    }

    /**
     * Convert identifier to snake_case and pluralize
     */
    private Identifier toSnakeCasePluralized(Identifier identifier) {
        if (identifier == null) {
            return null;
        }

        String name = identifier.getText();
        String snakeCaseName = this.toSnakeCaseString(name);
        String pluralizedName = pluralize(snakeCaseName);

        return Identifier.toIdentifier(pluralizedName, identifier.isQuoted());
    }

    /**
     * Convert identifier to snake_case
     */
    private Identifier toSnakeCase(Identifier identifier) {
        if (identifier == null) {
            return null;
        }

        String name = identifier.getText();
        String snakeCaseName = this.toSnakeCaseString(name);

        return Identifier.toIdentifier(snakeCaseName, identifier.isQuoted());
    }

    /**
     * Convert string from camelCase to snake_case
     */
    private String toSnakeCaseString(String name) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < name.length(); i++) {
            char character = name.charAt(i);

            if (Character.isUpperCase(character)) {
                if (i > 0) {
                    result.append('_');
                }
                result.append(Character.toLowerCase(character));
            } else {
                result.append(character);
            }
        }

        return result.toString();
    }
}