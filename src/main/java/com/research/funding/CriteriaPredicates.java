package com.research.funding;

import java.math.BigDecimal;
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

    public static Predicate<Map<String, String>> isPropertyValueLessThenValue(String property, String value) {
        return p-> Optional.ofNullable(lessValue(p.get(property),value)).orElse(false);
    }

    public static Predicate<Map<String, String>> isPropertyValueGreaterThenValue(String property, String value) {
        return p-> Optional.ofNullable(greaterValue(p.get(property),value)).orElse(false);
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

    static boolean lessValue(String left, String right){
        BigDecimal leftValue;
        BigDecimal rightValue;
        try{
            leftValue = new BigDecimal(left);
            rightValue = new BigDecimal(right);
            return leftValue.compareTo(rightValue)<0;
        }catch (NumberFormatException ex){
            //log exception
        }
        //otherwise compare as strings
        return left.compareTo(right)<0;
    }

    static boolean greaterValue(String left, String right){
        BigDecimal leftValue;
        BigDecimal rightValue;
        try{
            leftValue = new BigDecimal(left);
            rightValue = new BigDecimal(right);
            return leftValue.compareTo(rightValue)>0;
        }catch (NumberFormatException ex){
            //log exception
        }
        //otherwise compare as strings
        return left.compareTo(right)>0;
    }
}

