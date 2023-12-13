package com.study.hexagonal.domain.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.study.hexagonal.domain.entity.CoreRouter;
import com.study.hexagonal.domain.entity.EdgeRouter;
import com.study.hexagonal.domain.entity.Router;
import com.study.hexagonal.domain.entity.Switch;
import com.study.hexagonal.domain.vo.IP;
import com.study.hexagonal.domain.vo.Id;
import com.study.hexagonal.domain.vo.Location;
import com.study.hexagonal.domain.vo.Model;
import com.study.hexagonal.domain.vo.Network;
import com.study.hexagonal.domain.vo.RouterType;
import com.study.hexagonal.domain.vo.SwitchType;
import com.study.hexagonal.domain.vo.Vendor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class SwitchServiceTest {


    @Test
    void filterSwitchByType() {

        // given
        List<Switch> switches = new ArrayList<>();
        var location = createLocation("US");
        var networkSwitch = createSwitch("30.0.0.0", 8, location);

        switches.add(networkSwitch);

        // when
        var actualSwitchType = SwitchService.filterAndRetrieveSwitch(switches,
                Switch.isSameSwitchType(SwitchType.LAYER3)).get(0).getSwitchType();

        // then
        Assertions.assertThat(actualSwitchType).isEqualTo(SwitchType.LAYER3);
    }


    private Network createTestNetwork(String address, int CIDR) {
        return Network.builder().networkAddress(IP.fromAddress(address)).networkName("NewNetwork").networkCidr(CIDR)
                .build();
    }


    private Location createLocation(String country) {
        return Location.builder().address("Test street").city("Test City").state("Test State").country(country)
                .zipCode(00000).latitude(10F).longitude(-10F).build();
    }


    private List<Network> createNetworks(Network network) {
        List<Network> networks = new ArrayList<>();
        networks.add(network);
        return networks;
    }


    private Switch createSwitch(String address, int cidr, Location location) {
        var newNetwork = createTestNetwork(address, cidr);
        var networks = createNetworks(newNetwork);
        var networkSwitch = createNetworkSwitch(location, networks);
        return networkSwitch;
    }


    private Switch createNetworkSwitch(Location location, List<Network> networks) {
        return Switch.builder().id(Id.withId("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3490")).vendor(Vendor.CISCO)
                .model(Model.XYZ0004).ip(IP.fromAddress("20.0.0.100")).location(location).switchType(SwitchType.LAYER3)
                .switchNetworks(networks).build();
    }


    private EdgeRouter createEdgeRouter(Location location, String address) {
        Map<Id, Switch> switchesOfEdgeRouter = new HashMap<>();
        return EdgeRouter.builder().id(Id.withoutId()).vendor(Vendor.CISCO).model(Model.XYZ0002)
                .ip(IP.fromAddress(address)).location(location).routerType(RouterType.EDGE)
                .switches(switchesOfEdgeRouter).build();
    }


    private CoreRouter createCoreRouter(Location location, String address) {
        Map<Id, Router> routersOfCoreRouter = new HashMap<>();
        return CoreRouter.builder().id(Id.withoutId()).vendor(Vendor.HP).model(Model.XYZ0001)
                .ip(IP.fromAddress(address)).location(location).routerType(RouterType.CORE).routers(routersOfCoreRouter)
                .build();
    }
}
