package com.study.hexagonal.hexagonal_project.domain.specification;

import com.study.hexagonal.hexagonal_project.domain.entity.Equipment;
import com.study.hexagonal.hexagonal_project.domain.exception.GenericSpecificationException;
import com.study.hexagonal.hexagonal_project.domain.specification.common.AbstractSpecification;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SameIpSpecification extends AbstractSpecification<Equipment> {

    private final Equipment equipment;


    @Override
    public boolean isSatisfiedBy(Equipment anyEquipment) {
        return !this.equipment.getIp().equals(anyEquipment.getIp());
    }


    @Override
    public void check(Equipment equipment) throws GenericSpecificationException {

        if(!this.isSatisfiedBy(equipment)) {
            throw new GenericSpecificationException("It's not possible to attach routers with the same IP");
        }
    }


}
