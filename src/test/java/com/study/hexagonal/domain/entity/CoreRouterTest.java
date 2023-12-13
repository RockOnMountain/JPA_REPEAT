package com.study.hexagonal.domain.entity;

import java.util.HashMap;
import java.util.Map;
import com.study.hexagonal.domain.exception.GenericSpecificationException;
import com.study.hexagonal.domain.vo.IP;
import com.study.hexagonal.domain.vo.Id;
import com.study.hexagonal.domain.vo.Location;
import com.study.hexagonal.domain.vo.Model;
import com.study.hexagonal.domain.vo.RouterType;
import com.study.hexagonal.domain.vo.Vendor;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CoreRouterTest {

    @Test
    @DisplayName("edgeRouter를 coreRouter에 추가한다.")
    void addEdgeRouter() {

        // given
        var location = createLocation("US");
        var edgeRouter = createEdgeRouter(location, "30.0.0.1");
        var coreRouter = createCoreRouter(location, "40.0.0.1");

        // when
        coreRouter.addRouter(edgeRouter);

        // then
        assertThat(coreRouter.getRouters()).hasSize(1);
    }


    @Test
    @DisplayName("edgeRouter 국가가 CoreRouter 국가와 다르면 추가할 수 없다.")
    void addEdgeRouterFailDifferentCountry() {

        // given
        var locationUS = createLocation("US");
        var locationJP = createLocation("JP");
        var edgeRouter = createEdgeRouter(locationUS, "30.0.0.1");
        var coreRouter = createCoreRouter(locationJP, "40.0.0.1");

        // when, then
        assertThatThrownBy(() -> {
            coreRouter.addRouter(edgeRouter);
        }).isInstanceOf(GenericSpecificationException.class).hasMessage("The equipments should be in the same country");
    }


    @Test
    @DisplayName("CoreRouter를 추가한다.")
    void addCoreRouter() {

        // given
        var location = createLocation("US");
        var coreRouter = createCoreRouter(location, "30.0.0.1");
        var newCoreRouter = createCoreRouter(location, "40.0.0.1");

        // when
        coreRouter.addRouter(newCoreRouter);

        // then
        assertThat(coreRouter.getRouters()).hasSize(1);
    }


    @Test
    @DisplayName("같은 IP의 CoreRouter는 추가할 수 없다.")
    void addCoreRouterFailSameIP() {

        // given
        var location = createLocation("US");
        var coreRouter = createCoreRouter(location, "30.0.0.1");
        var newCoreRouter = createCoreRouter(location, "30.0.0.1");

        // when, then
        assertThatThrownBy(() -> {
            coreRouter.addRouter(newCoreRouter);
        }).isInstanceOf(GenericSpecificationException.class)
                .hasMessage("It's not possible to attach routers with the same IP");
    }


    @Test
    @DisplayName("edgeRouter에 switch가 없을 경우에는 edgeRouter를 제거 할 수 있다.")
    void removeEdgeRouter() {

        // given
        var location = createLocation("US");
        var coreRouter = createCoreRouter(location, "30.0.0.1");
        var edgeRouter = createEdgeRouter(location, "40.0.0.1");
        var expectedId = edgeRouter.getId();

        coreRouter.addRouter(edgeRouter);

        // when
        var actualId = coreRouter.removeRouter(edgeRouter).getId();

        // then
        assertThat(expectedId).isEqualTo(actualId);
    }


    private Location createLocation(String country) {
        return Location.builder().address("Test street").city("Test City").state("Test State").country(country)
                .zipCode(00000).latitude(10F).longitude(-10F).build();
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
