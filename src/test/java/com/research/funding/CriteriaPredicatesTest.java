package com.research.funding;

import lombok.val;
import org.junit.Test;

import java.util.HashMap;
import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class CriteriaPredicatesTest {

    @Test
    public void isPropertyPresent() {
        val funding = new HashMap<String, String>();
        funding.put("name", "NIH");
        assertTrue(Optional.of(funding).filter(CriteriaPredicates.isPropertyPresent("name")).isPresent());
        assertFalse(Optional.of(funding).filter(CriteriaPredicates.isPropertyPresent("address")).isPresent());
    }

    @Test
    public void isPropertyValueEquals() {
        val funding = new HashMap<String, String>();
        funding.put("name", "NIH");
        assertTrue(Optional.of(funding).filter(CriteriaPredicates.isPropertyValueEquals("name", "NIH")).isPresent());
        assertFalse(Optional.of(funding).filter(CriteriaPredicates.isPropertyValueEquals("name", "Gates")).isPresent());
    }

    @Test
    public void andPredicate() {
        val funding = new HashMap<String, String>();
        funding.put("name", "NIH");
        assertTrue(Optional.of(funding).filter(CriteriaPredicates.andPredicate(CriteriaPredicates.isPropertyPresent("name"),
                CriteriaPredicates.isPropertyValueEquals("name", "NIH"))).isPresent());
        assertFalse(Optional.of(funding).filter(CriteriaPredicates.andPredicate(CriteriaPredicates.isPropertyPresent("name"),
                CriteriaPredicates.isPropertyValueEquals("name", "Gates"))).isPresent());
    }

    @Test
    public void orPredicate() {
        val funding = new HashMap<String, String>();
        funding.put("name", "NIH");
        assertTrue(Optional.of(funding).filter(CriteriaPredicates.andPredicate(CriteriaPredicates.isPropertyPresent("name"),
                CriteriaPredicates.isPropertyValueEquals("name", "NIH"))).isPresent());
        assertTrue(Optional.of(funding).filter(CriteriaPredicates.orPredicate(CriteriaPredicates.isPropertyPresent("name"),
                CriteriaPredicates.isPropertyValueEquals("name", "Gates"))).isPresent());
        assertFalse(Optional.of(funding).filter(CriteriaPredicates.orPredicate(CriteriaPredicates.isPropertyPresent("address"),
                CriteriaPredicates.isPropertyValueEquals("name", "Gates"))).isPresent());
    }

    @Test
    public void notPredicate() {
        val funding = new HashMap<String, String>();
        funding.put("name", "NIH");
        assertTrue(Optional.of(funding).filter(CriteriaPredicates.notPredicate(CriteriaPredicates.isPropertyPresent("address"))).isPresent());
    }
}