package com.research.funding;

import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

public class CriteriaPredicates {

    public static Predicate<Map<String, String>> isPropertyPresent(String property) {
        return p->p.containsKey(property);
    }
    public static Predicate<Map<String, String>> isPropertyValueEquals(String property, String value) {
        return p-> Optional.ofNullable(p.get(property).equals(value)).orElse(false);
    }


    public static Predicate<Map<String, String>> andPredicate(Predicate<Map<String, String>> predicateLeft, Predicate<Map<String, String>> predicateRight){
        return predicateLeft.and(predicateRight);
    }

    public static Predicate<Map<String, String>> orPredicate(Predicate<Map<String, String>> predicateLeft, Predicate<Map<String, String>> predicateRight){
        return predicateLeft.or(predicateRight);
    }

    public static Predicate<Map<String, String>> notPredicate(Predicate<Map<String, String>> predicate){
        return predicate.negate();
    }
}

