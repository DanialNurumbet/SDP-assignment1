import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PCconfigTest {
        @Test
        void shouldBuildBasicPC() {
            PCconfig basic =
                    new PCpresetDirector().buildBasicPC();
            assertNotNull(basic);
        }
    @Test
    void shouldBuildGamingPC() {
        PCconfig gaming =
                new PCpresetDirector().buildGamingPC();
        assertNotNull(gaming);
    }

    @Test
    void shouldBuildWorkstationPC() {
        PCconfig workstation =
                new PCpresetDirector().buildWorkstationPC();
        assertNotNull(workstation);
    }

    @Test
    void shouldRejectInvalidRam() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new PCconfig.Builder(
                        "Intel Core i5",
                        "MSI B760",
                        0,
                        512
                ).build()
        );
    }

    @Test
    void shouldRejectInvalidStorage() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new PCconfig.Builder(
                        "Intel Core i5",
                        "MSI B760",
                        16,
                        0
                ).build()
        );
    }

    @Test
    void shouldRejectInvalidBudget() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new PCconfig.Builder(
                        "Intel Core i5",
                        "MSI B760",
                        16,
                        512
                )
                        .budget(-100)
                        .build()
        );
    }


    // =========================
    // BOUNDARY CASES
    // =========================

    @Test
    void shouldAcceptMinimumGamingRam() {

        PCconfig gaming = new PCconfig.Builder(
                "AMD Ryzen 7 7800X3D",
                "ASUS B650",
                16,
                1000
        )
                .usageType("GAMING")
                .graphicsCard("RTX 4070")
                .powerSupplyW(750)
                .coolingType("Air")
                .build();

        assertNotNull(gaming);
    }

    @Test
    void shouldAcceptMinimumGamingPowerSupply() {

        PCconfig gaming = new PCconfig.Builder(
                "AMD Ryzen 7 7800X3D",
                "ASUS B650",
                16,
                1000
        )
                .usageType("GAMING")
                .graphicsCard("RTX 4070")
                .powerSupplyW(750)
                .coolingType("Air")
                .build();

        assertNotNull(gaming);
    }

    @Test
    void shouldRejectGamingPCWithInsufficientRam() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new PCconfig.Builder(
                        "AMD Ryzen 7 7800X3D",
                        "ASUS B650",
                        8,
                        1000
                )
                        .usageType("GAMING")
                        .graphicsCard("RTX 4070")
                        .powerSupplyW(750)
                        .coolingType("Air")
                        .build()
        );
    }

    @Test
    void shouldKeepProductIndependentFromBuilder() {

        PCconfig.Builder builder = new PCconfig.Builder(
                "Intel Core i5",
                "MSI B760",
                16,
                512
        );

        PCconfig firstPC = builder
                .usageType("OFFICE")
                .budget(800)
                .build();

        builder
                .usageType("GAMING")
                .graphicsCard("RTX 4070")
                .powerSupplyW(750)
                .coolingType("Air");

        PCconfig secondPC = builder.build();

        assertNotSame(firstPC, secondPC);
        assertNotEquals(
                firstPC.toString(),
                secondPC.toString()
        );
    }
}