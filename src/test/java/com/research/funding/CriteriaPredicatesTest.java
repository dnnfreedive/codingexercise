package com.research.funding;

import lombok.val;
import org.junit.Test;

import java.util.HashMap;
import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class CriteriaPredicatesTest {

    @Test
    public void isPropertyPresentTest() {
        val funding = new HashMap<String, String>();
        funding.put("name", "NIH");
        assertTrue(Optional.of(funding).filter(CriteriaPredicates.isPropertyPresent("name")).isPresent());
        assertFalse(Optional.of(funding).filter(CriteriaPredicates.isPropertyPresent("address")).isPresent());
    }

    @Test
    public void isPropertyValueEqualsTest() {
        val funding = new HashMap<String, String>();
        funding.put("name", "NIH");
        assertTrue(Optional.of(funding).filter(CriteriaPredicates.isPropertyValueEquals("name", "NIH")).isPresent());
        assertFalse(Optional.of(funding).filter(CriteriaPredicates.isPropertyValueEquals("name", "Gates")).isPresent());
    }


    @Test
    public void andPredicateTest() {
        val funding = new HashMap<String, String>();
        funding.put("name", "NIH");
        assertTrue(Optional.of(funding).filter(CriteriaPredicates.andPredicate(CriteriaPredicates.isPropertyPresent("name"),
                CriteriaPredicates.isPropertyValueEquals("name", "NIH"))).isPresent());
        assertFalse(Optional.of(funding).filter(CriteriaPredicates.andPredicate(CriteriaPredicates.isPropertyPresent("name"),
                CriteriaPredicates.isPropertyValueEquals("name", "Gates"))).isPresent());
    }

    @Test
    public void orPredicateTest() {
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
    public void notPredicateTest() {
        val funding = new HashMap<String, String>();
        funding.put("name", "NIH");
        assertTrue(Optional.of(funding).filter(CriteriaPredicates.notPredicate(CriteriaPredicates.isPropertyPresent("address"))).isPresent());
    }

    @Test
    public void isPropertyValueLessThenValueTest() {
        val funding = new HashMap<String, String>();
        funding.put("amount", "1000");
        funding.put("name", "Healthcare");
        assertTrue(Optional.of(funding).filter(CriteriaPredicates.isPropertyValueLessThenValue("amount", "50000")).isPresent());
        assertTrue(Optional.of(funding).filter(CriteriaPredicates.isPropertyValueLessThenValue("name", "Healthcare technology research")).isPresent());
    }

    @Test
    public void isPropertyValueGreaterThenValue() {
        val funding = new HashMap<String, String>();
        funding.put("amount", "1000");
        funding.put("name", "Healthcare");
        assertFalse(Optional.of(funding).filter(CriteriaPredicates.isPropertyValueGreaterThenValue("amount", "50000")).isPresent());
        assertFalse(Optional.of(funding).filter(CriteriaPredicates.isPropertyValueGreaterThenValue("name", "Healthcare technology research")).isPresent());
    }

    @Test
    public void lessValue() {
        assertTrue(CriteriaPredicates.lessValue("1000", "5000"));
    }

    @Test
    public void greaterValue() {
        assertTrue(CriteriaPredicates.greaterValue("Healthcare technology research", "Healthcare"));
    }
}