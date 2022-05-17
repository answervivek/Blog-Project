package com.vtripathi.blogproject.Entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class RoleTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Role#Role()}
     *   <li>{@link Role#setId(int)}
     *   <li>{@link Role#setName(String)}
     *   <li>{@link Role#getId()}
     *   <li>{@link Role#getName()}
     * </ul>
     */
    @Test
    void testConstructor() {
        Role actualRole = new Role();
        actualRole.setId(1);
        actualRole.setName("Name");
        assertEquals(1, actualRole.getId());
        assertEquals("Name", actualRole.getName());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Role#Role(int, String)}
     *   <li>{@link Role#setId(int)}
     *   <li>{@link Role#setName(String)}
     *   <li>{@link Role#getId()}
     *   <li>{@link Role#getName()}
     * </ul>
     */
    @Test
    void testConstructor2() {
        Role actualRole = new Role(1, "Name");
        actualRole.setId(1);
        actualRole.setName("Name");
        assertEquals(1, actualRole.getId());
        assertEquals("Name", actualRole.getName());
    }
}

