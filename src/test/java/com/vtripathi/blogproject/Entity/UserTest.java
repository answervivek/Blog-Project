package com.vtripathi.blogproject.Entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

class UserTest {
    /**
     * Method under test: {@link User#getAuthorities()}
     */
    @Test
    void testGetAuthorities() {
        assertTrue((new User()).getAuthorities().isEmpty());
    }

    /**
     * Method under test: {@link User#getAuthorities()}
     */
    @Test
    void testGetAuthorities2() {
        Role role = new Role();
        role.setId(1);
        role.setName("Name");

        HashSet<Role> roleSet = new HashSet<>();
        roleSet.add(role);
        Collection<? extends GrantedAuthority> actualAuthorities = (new User(1, "Name", "jane.doe@example.org", "iloveyou",
                "About", new ArrayList<>(), roleSet)).getAuthorities();
        assertEquals(1, actualAuthorities.size());
        assertEquals("Name", ((List<? extends GrantedAuthority>) actualAuthorities).get(0).getAuthority());
    }

    /**
     * Method under test: {@link User#getAuthorities()}
     */
    @Test
    void testGetAuthorities3() {
        Role role = new Role();
        role.setId(1);
        role.setName("Name");

        Role role1 = new Role();
        role1.setId(1);
        role1.setName("Name");

        HashSet<Role> roleSet = new HashSet<>();
        roleSet.add(role1);
        roleSet.add(role);
        Collection<? extends GrantedAuthority> actualAuthorities = (new User(1, "Name", "jane.doe@example.org", "iloveyou",
                "About", new ArrayList<>(), roleSet)).getAuthorities();
        assertEquals(2, actualAuthorities.size());
        assertEquals("Name", ((List<? extends GrantedAuthority>) actualAuthorities).get(0).toString());
        assertEquals("Name", ((List<? extends GrantedAuthority>) actualAuthorities).get(1).toString());
    }

    /**
     * Method under test: {@link User#getAuthorities()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetAuthorities4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: A granted authority textual representation is required
        //       at org.springframework.util.Assert.hasText(Assert.java:289)
        //       at org.springframework.security.core.authority.SimpleGrantedAuthority.<init>(SimpleGrantedAuthority.java:39)
        //       at com.vtripathi.blogproject.Entity.User.lambda$getAuthorities$0(User.java:46)
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        //       at java.util.HashMap$KeySpliterator.forEachRemaining(HashMap.java:1707)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:921)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:682)
        //       at com.vtripathi.blogproject.Entity.User.getAuthorities(User.java:46)
        //   In order to prevent getAuthorities()
        //   from throwing IllegalArgumentException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   getAuthorities().
        //   See https://diff.blue/R013 to resolve this issue.

        Role role = new Role();
        role.setId(1);
        role.setName("");

        HashSet<Role> roleSet = new HashSet<>();
        roleSet.add(role);
        (new User(1, "Name", "jane.doe@example.org", "iloveyou", "About", new ArrayList<>(), roleSet)).getAuthorities();
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link User#User()}
     *   <li>{@link User#getUsername()}
     *   <li>{@link User#isAccountNonExpired()}
     *   <li>{@link User#isAccountNonLocked()}
     *   <li>{@link User#isCredentialsNonExpired()}
     *   <li>{@link User#isEnabled()}
     * </ul>
     */
    @Test
    void testConstructor() {
        User actualUser = new User();
        assertNull(actualUser.getUsername());
        assertTrue(actualUser.isAccountNonExpired());
        assertTrue(actualUser.isAccountNonLocked());
        assertTrue(actualUser.isCredentialsNonExpired());
        assertTrue(actualUser.isEnabled());
    }
}

