package com.study.hexagonal.domain.specification;

import com.study.hexagonal.domain.entity.CoreRouter;
import com.study.hexagonal.domain.entity.Equipment;
import com.study.hexagonal.domain.exception.GenericSpecificationException;
import com.study.hexagonal.domain.specification.common.AbstractSpecification;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SameCountrySpecification extends AbstractSpecification<Equipment> {

    private final Equipment equipment;


    @Override
    public boolean isSatisfiedBy(Equipment anyEquipment) {

        if(anyEquipment instanceof CoreRouter) {
            return true;
        } else if(anyEquipment != null && this.equipment != null) {
            return this.equipment.getLocation().getCountry().equals(anyEquipment.getLocation().getCountry());
        } else {
            return false;
        }
    }


    @Override
    public void check(Equipment equipment) throws GenericSpecificationException {

        if(!this.isSatisfiedBy(equipment)) {
            throw new GenericSpecificationException("The equipments should be in the same country");
        }
    }
}
