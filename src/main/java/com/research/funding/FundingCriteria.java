package com.research.funding;


import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

public class FundingCriteria implements Criteria {

    private Predicate<Map<String,String>> predicate;

    public Predicate<Map<String, String>> getPredicate() {
        return predicate;
    }

    @Override
    public boolean matches(Map<String, String> opportunity) {
        return Optional.of(opportunity).filter(predicate).isPresent();
    }

    @Override
    public void shouldContainProperty(String property) {
        predicate = CriteriaPredicates.isPropertyPresent(property);

    }

    @Override
    public void propertyShouldBeEqual(String property, String value) {
        predicate = CriteriaPredicates.isPropertyValueEquals(property, value);

    }

    @Override
    public void propertyShouldBeLessThanValue(String property, String value) {
        predicate = CriteriaPredicates.isPropertyValueLessThenValue(property, value);
    }

    @Override
    public void proeprtyShouldBeGreaterThanValue(String property, String value) {
        predicate = CriteriaPredicates.isPropertyValueGreaterThenValue(property, value);

    }


    @Override
    public void andFulfillCriteria(Criteria criteria) {
        this.predicate = CriteriaPredicates.andPredicate(this.predicate, criteria.getPredicate());

    }

    @Override
    public void orFulfillCriteria(Criteria criteria) {
        this.predicate = CriteriaPredicates.orPredicate(this.predicate, criteria.getPredicate());

    }

    @Override
    public void notCriteria(Criteria criteria) {
        this.predicate = CriteriaPredicates.notPredicate(this.predicate);
    }



}
