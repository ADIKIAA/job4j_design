package ru.job4j.generics;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

class RoleStoreTest {

    @Test
    void whenAddAndFindThenRoleIsCustomer() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "customer"));
        Role result = store.findById("1");
        assertThat(result.getRole()).isEqualTo("customer");
    }

    @Test
    void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "seller"));
        Role result = store.findById("10");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindRole() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "director"));
        store.add(new Role("1", "customer"));
        Role result = store.findById("1");
        assertThat(result.getRole()).isEqualTo("director");
    }

    @Test
    void whenReplaceThenRole() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "customer"));
        store.replace("1", new Role("1", "director"));
        Role result = store.findById("1");
        assertThat(result.getRole()).isEqualTo("director");
    }

    @Test
    void whenNoReplaceRoleThenNoChangeRole() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "seller"));
        store.replace("10", new Role("10", "customer"));
        Role result = store.findById("1");
        assertThat(result.getRole()).isEqualTo("seller");
    }

    @Test
    void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "seller"));
        store.delete("1");
        Role result = store.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteRole() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "customer"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getRole()).isEqualTo("customer");
    }

    @Test
    void whenReplaceOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "customer"));
        boolean rsl = store.replace("1", new Role("1", "seller"));
        assertThat(rsl).isTrue();
    }

    @Test
    void whenReplaceNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "director"));
        boolean rsl = store.replace("10", new Role("10", "customer"));
        assertThat(rsl).isFalse();
    }

    @Test
    void whenDeleteOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "director"));
        boolean rsl = store.delete("1");
        assertThat(rsl).isTrue();
    }

    @Test
    void whenDeleteNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "customer"));
        boolean rsl = store.delete("2");
        assertThat(rsl).isFalse();
    }
}