package com.study.hexagonal.domain.entity;

import java.util.ArrayList;
import java.util.List;
import com.study.hexagonal.hexagonal_project.domain.entity.Switch;
import com.study.hexagonal.hexagonal_project.domain.exception.GenericSpecificationException;
import com.study.hexagonal.hexagonal_project.domain.vo.IP;
import com.study.hexagonal.hexagonal_project.domain.vo.Id;
import com.study.hexagonal.hexagonal_project.domain.vo.Location;
import com.study.hexagonal.hexagonal_project.domain.vo.Model;
import com.study.hexagonal.hexagonal_project.domain.vo.Network;
import com.study.hexagonal.hexagonal_project.domain.vo.SwitchType;
import com.study.hexagonal.hexagonal_project.domain.vo.Vendor;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SwitchTest {

    @Test
    @DisplayName("스위치가 네트워크 추가 가능한 경우 네트워크가 추가된다.")
    void addNetwork() {

        // given
        Location location = this.createLocation("US");
        Network newNetwork = this.createTestNetwork("30.0.0.1", 8);

        // when
        Switch networkSwitch = this.createSwitch("30.0.0.0", 8, location);

        // then
        assertThat(networkSwitch.addNetwork(newNetwork)).isTrue();
    }


    @Test
    @DisplayName("Ip가 같은 네트워크는 추가가 되지 않는다.")
    void addNetworkFailSameIp() {

        // given
        Location location = this.createLocation("US");
        Network newNetwork = this.createTestNetwork("30.0.0.0", 8);

        // when
        Switch networkSwitch = this.createSwitch("30.0.0.0", 8, location);

        // then
        assertThatThrownBy(() -> {
            networkSwitch.addNetwork(newNetwork);
        }).isInstanceOf(GenericSpecificationException.class).hasMessage("This network already exists");
    }


    @Test
    void removeNetwork() {

        // given
        var location = createLocation("US");
        var network = createTestNetwork("30.0.0.0", 8);
        var networkSwitch = createSwitch("30.0.0.0", 8, location);

        int networkAmount = networkSwitch.getSwitchNetworks().size();

        // when
        networkSwitch.removeNetwork(network);

        // then
        assertThat(networkAmount).isEqualTo(1);
        assertThat(networkSwitch.getSwitchNetworks()).hasSize(0);
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

}
