package ru.job4j.generic;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RoleStoreTest {

    @Test
    void whenAddAndFindThenPermissionIsAdmin() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "admin"));
        Role result = store.findById("1");
        assertThat(result.getPermission()).isEqualTo("admin");
    }

    @Test
    void whenAddDuplicate() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "admin"));
        store.add(new Role("1", "engineers"));
        Role result = store.findById("1");
        assertThat(result.getPermission()).isEqualTo("admin");
    }

    @Test
    void whenAddAndFindOtherThenPermissionIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "admin"));
        Role result = store.findById("10");
        assertThat(result).isNull();
    }

    @Test
    void whenReplaceThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "admin"));
        boolean result = store.replace("1", new Role("1", "qa"));
        assertThat(result).isTrue();
    }

    @Test
    void whenNoReplaceThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "admin"));
        boolean result = store.replace("10", new Role("1", "qa"));
        assertThat(result).isFalse();
    }

    @Test
    void whenNoReplaceThenNoChangePermission() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "admin"));
        store.replace("10", new Role("1", "qa"));
        Role result = store.findById("1");
        assertThat(result.getPermission()).isEqualTo("admin");
    }

    @Test
    void whenDeleteThenNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "admin"));
        store.delete("1");
        Role result = store.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteThenPermission() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "admin"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getPermission()).isEqualTo("admin");
    }

    @Test
    void whenDeleteThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "admin"));
        boolean result = store.delete("1");
        assertThat(result).isTrue();
    }

    @Test
    void whenDeleteThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "admin"));
        boolean result = store.delete("10");
        assertThat(result).isFalse();
    }

}