package com.study.hexagonal.domain.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.study.hexagonal.domain.exception.GenericSpecificationException;
import com.study.hexagonal.domain.vo.IP;
import com.study.hexagonal.domain.vo.Id;
import com.study.hexagonal.domain.vo.Location;
import com.study.hexagonal.domain.vo.Model;
import com.study.hexagonal.domain.vo.Network;
import com.study.hexagonal.domain.vo.RouterType;
import com.study.hexagonal.domain.vo.SwitchType;
import com.study.hexagonal.domain.vo.Vendor;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EdgeRouterTest {

    @Test
    @DisplayName("switch 를 추가한다.")
    void addSwitch() {

        // given
        Location location = createLocation("US");
        Switch networkSwitch = createSwitch("30.0.0.0", 8, location);
        EdgeRouter edgeRouter = createEdgeRouter(location, "30.0.0.1");

        // when
        edgeRouter.addSwitch(networkSwitch);

        // then
        assertThat(edgeRouter.getSwitches().size()).isEqualTo(1);
    }


    @Test
    @DisplayName("EdgeRouter의 국가와 switch의 국가가 다른 경우 추가할 수 없다.")
    void addSwitchFailDifferentCountry() {

        // given
        Location locationUS = createLocation("US");
        Location locationJP = createLocation("JP");
        Switch networkSwitch = createSwitch("30.0.0.0", 8, locationUS);
        EdgeRouter edgeRouter = createEdgeRouter(locationJP, "30.0.0.1");

        // when, then
        assertThatThrownBy(() -> {
            edgeRouter.addSwitch(networkSwitch);
        }).isInstanceOf(GenericSpecificationException.class).hasMessage("The equipments should be in the same country");

    }


    @Test
    void removeSwitch() {

        // given
        var location = createLocation("US");
        var network = createTestNetwork("30.0.0.0", 8);
        var networkSwitch = createSwitch("30.0.0.0", 8, location);
        var edgeRouter = createEdgeRouter(location, "40.0.0.1");

        networkSwitch.removeNetwork(network);
        edgeRouter.addSwitch(networkSwitch);

        // when
        Switch removedSwitch = edgeRouter.removeSwitch(networkSwitch);

        // then
        assertThat(removedSwitch.getId()).isEqualTo(networkSwitch.getId());
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
        return createNetworkSwitch(location, networks);
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

}
