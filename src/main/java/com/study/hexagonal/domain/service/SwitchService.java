package com.study.hexagonal.domain.service;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import com.study.hexagonal.domain.entity.Switch;
import com.study.hexagonal.domain.vo.Id;

public class SwitchService {

    public static List<Switch> filterAndRetrieveSwitch(List<Switch> switches, Predicate<Switch> switchPredicate) {
        return switches.stream().filter(switchPredicate).collect(Collectors.toList());
    }


    public static Switch findById(Map<Id, Switch> switches, Id id) {
        return switches.get(id);
    }

}
