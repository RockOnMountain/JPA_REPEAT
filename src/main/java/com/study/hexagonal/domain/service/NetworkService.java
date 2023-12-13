package com.study.hexagonal.domain.service;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import com.study.hexagonal.domain.vo.Network;

public class NetworkService {

    public static List<Network> filterAndRetrieveNetworks(List<Network> networks, Predicate<Network> networkPredicate) {
        return networks.stream().filter(networkPredicate).collect(Collectors.toList());
    }
}
