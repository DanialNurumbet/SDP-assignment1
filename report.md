# Assignment 1 – Builder Pattern: Design Under Changing Requirements

## Domain: Computer Configuration

## Product: PCconfig

## Builder: PCconfig.Builder

---

# Part A – Demonstrate the Design Problem

## 1. Domain Description

The selected domain for this assignment is **Computer Configuration**.

The system represents a configurable personal computer called **PCconfig**. A PC configuration consists of required hardware components such as a processor, motherboard, RAM, and storage, as well as optional components and settings such as a graphics card, power supply, cooling system, monitor, operating system, network adapter, RGB lighting, and peripherals.

The configuration contains 30 meaningful properties. Four properties are required, while the remaining properties are optional and have default values in the Builder implementation.

The required properties are:

* Processor
* Motherboard
* RAM capacity
* Storage capacity

The project also contains a nested supporting object called `NetworkAdapter`.

---

## 2. Initial Constructor-Based Implementation

Before introducing the Builder Pattern, the `PCconfig` object was implemented using a conventional constructor.

The constructor receives all 30 configuration properties as parameters.

Example:

```java
NetworkAdapter networkAdapter =
        new NetworkAdapter(
                "Intel",
                "Wi-Fi 6E",
                2500
        );

PCconfig gamingPC = new PCconfig(
        "AMD Ryzen 7 7800X3D",
        "ASUS TUF Gaming B650-Plus",
        32,
        1000,
        "GAMING",
        "NVIDIA RTX 4070 SUPER",
        750,
        "Air",
        "NZXT H5 Flow",
        "NVMe",
        "Windows 11",
        "DDR5",
        2,
        1,
        2,
        27.0,
        "2560x1440",
        "Logitech G Pro Keyboard",
        "Logitech G Pro X Superlight",
        true,
        true,
        2500,
        8,
        4,
        true,
        "Integrated",
        "Logitech C920",
        "HyperX QuadCast",
        networkAdapter,
        1800.0
);
```

This implementation can create a complete `PCconfig` object, but the construction approach creates several design problems.

---

## 3. Design Problems of the Constructor-Based Approach

### Problem 1 – Poor Readability

The constructor contains 30 parameters. Most values do not clearly describe what they represent.

For example:

```java
PCconfig gamingPC = new PCconfig(
        "AMD Ryzen 7 7800X3D",
        "ASUS TUF Gaming B650-Plus",
        32,
        1000,
        "GAMING",
        "NVIDIA RTX 4070 SUPER",
        750,
        "Air"
);
```

The values `32`, `1000`, and `750` do not explain themselves.

A developer must look at the constructor declaration to understand that they represent RAM capacity, storage capacity, and power supply capacity.

This makes the Client code harder to read and understand.

---

### Problem 2 – High Risk of Incorrect Argument Order

Many properties have the same data type.

For example, the configuration contains several `int` properties:

```text
ramGb
storageGb
powerSupplyW
ramModules
storageDevices
monitorCount
ethernetSpeed
usbPorts
fanCount
```

Because these properties have the same type, the compiler cannot always detect when two values are accidentally placed in the wrong positions.

For example:

```java
PCconfig pc = new PCconfig(
        "AMD Ryzen 7 7800X3D",
        "ASUS TUF Gaming B650-Plus",
        1000,
        32,
        "..."
);
```

The program can interpret `1000` as RAM and `32` as storage because both values are integers.

The constructor therefore creates a risk of configuration errors that are difficult to notice from the Client code.

---

### Problem 3 – Difficult to Maintain and Extend

The PC configuration may need additional properties in the future.

For example, a new property could be:

```java
private String bluetoothVersion;
```

Adding this property to the constructor would require changing the constructor signature and updating every existing constructor call.

For a system with many different PC configurations, this increases maintenance effort and the possibility of introducing errors.

---

### Problem 4 – Optional Properties Require Unnecessary Arguments

The constructor requires values for optional properties as well.

For example, an office PC may not need:

* a dedicated graphics card;
* a webcam;
* a microphone;
* RGB lighting.

However, the constructor still requires values for these parameters.

This can result in code containing many `null` and `false` values:

```java
PCconfig officePC = new PCconfig(
        "Intel Core i5-14400",
        "MSI B760",
        16,
        512,
        "OFFICE",
        null,
        500,
        "Stock",
        "Standard Office Case",
        "SSD",
        "Windows 11",
        "DDR4",
        2,
        1,
        1,
        24.0,
        "1920x1080",
        "Standard Keyboard",
        "Standard Mouse",
        false,
        false,
        1000,
        6,
        2,
        false,
        "Integrated",
        null,
        null,
        null,
        800.0
);
```

The Client code becomes difficult to understand because it is not immediately clear whether a value is intentionally omitted or simply forgotten.

---
# Part B – Refactor to Builder

## 1. Builder Pattern Solution

The constructor-based implementation was refactored using the **Builder Pattern**.

The main participants are:

* `PCconfig` – Product
* `PCconfig.Builder` – Builder
* `build()` – operation that creates the Product
* `Main` – Client
* `NetworkAdapter` – supporting/value object

The Builder provides a fluent API that allows configuration methods to be chained.

Example:

```java
PCconfig gamingPC =
        new PCconfig.Builder(
                "AMD Ryzen 7 7800X3D",
                "ASUS TUF Gaming B650-Plus",
                32,
                1000
        )
        .usageType("GAMING")
        .graphicsCard("NVIDIA RTX 4070 SUPER")
        .powerSupplyW(750)
        .coolingType("Air")
        .caseModel("NZXT H5 Flow")
        .storageType("NVMe")
        .operatingSystem("Windows 11")
        .ramType("DDR5")
        .ramModules(2)
        .storageDevices(1)
        .monitorCount(2)
        .monitorSize(27.0)
        .monitorResolution("2560x1440")
        .wifiEnabled(true)
        .bluetoothEnabled(true)
        .rgbLighting(true)
        .budget(1800.0)
        .build();
```

The construction process is now much easier to understand because every optional value is associated with a descriptive method.

---

## 2. Required Properties

The Builder constructor requires four properties:

```java
public Builder(
        String processor,
        String motherboard,
        int ramGb,
        int storageGb
) {
    this.processor = processor;
    this.motherboard = motherboard;
    this.ramGb = ramGb;
    this.storageGb = storageGb;
}
```

These properties are required because a basic PC configuration should contain:

* a processor;
* a motherboard;
* RAM;
* storage.

The Client therefore cannot create a Builder without providing these four values.

---

## 3. Optional Properties and Default Values

The remaining properties are optional.

Meaningful default values are defined inside the Builder.

For example:

```java
private String usageType = "OFFICE";
private String graphicsCard = "Integrated Graphics";
private int powerSupplyW = 500;
private String coolingType = "Stock";
private String storageType = "SSD";
private String operatingSystem = "Windows 11";
private String ramType = "DDR4";
private int ramModules = 2;
private int monitorCount = 1;
private double monitorSize = 24.0;
private boolean wifiEnabled = false;
private boolean bluetoothEnabled = false;
private int ethernetSpeed = 1000;
private int usbPorts = 6;
private int fanCount = 2;
private boolean rgbLighting = false;
private String soundCard = "Integrated";
private double budget = 1000.0;
```

This means that the Client only needs to specify properties that are different from the defaults.

For example:

```java
PCconfig officePC =
        new PCconfig.Builder(
                "Intel Core i5-14400",
                "MSI B760",
                16,
                512
        )
        .build();
```

The Builder automatically uses the default values for optional properties.

---

## 4. Fluent API and Method Chaining

Each Builder method changes one property and returns the current Builder object.

Example:

```java
public Builder graphicsCard(String graphicsCard) {
    this.graphicsCard = graphicsCard;
    return this;
}
```

The statement:

```java
return this;
```

allows another Builder method to be called immediately.

Therefore, methods can be chained:

```java
.graphicsCard("NVIDIA RTX 4070 SUPER")
.powerSupplyW(750)
.coolingType("Air")
```


It makes the construction process more readable than passing many unnamed arguments to a constructor.

---

## 5. Product Construction

The `build()` method creates the final `PCconfig` object:

```java
public PCconfig build() {
    return new PCconfig(this);
}
```

The `PCconfig` constructor receives the Builder:

```java
private PCconfig(Builder builder) {
    this.processor = builder.processor;
    this.motherboard = builder.motherboard;
    this.ramGb = builder.ramGb;
    this.storageGb = builder.storageGb;

    this.usageType = builder.usageType;
    this.graphicsCard = builder.graphicsCard;
    this.powerSupplyW = builder.powerSupplyW;
    this.coolingType = builder.coolingType;
    this.caseModel = builder.caseModel;
    this.storageType = builder.storageType;
    this.operatingSystem = builder.operatingSystem;
    this.ramType = builder.ramType;
    this.ramModules = builder.ramModules;
    this.storageDevices = builder.storageDevices;
    this.monitorCount = builder.monitorCount;
    this.monitorSize = builder.monitorSize;
    this.monitorResolution = builder.monitorResolution;
    this.keyboard = builder.keyboard;
    this.mouse = builder.mouse;
    this.wifiEnabled = builder.wifiEnabled;
    this.bluetoothEnabled = builder.bluetoothEnabled;
    this.ethernetSpeed = builder.ethernetSpeed;
    this.usbPorts = builder.usbPorts;
    this.fanCount = builder.fanCount;
    this.rgbLighting = builder.rgbLighting;
    this.soundCard = builder.soundCard;
    this.webcam = builder.webcam;
    this.microphone = builder.microphone;
    this.networkAdapter = builder.networkAdapter;
    this.budget = builder.budget;
}
```

The constructor is private, so the Client cannot directly construct `PCconfig`.

The intended construction process is therefore:


---

## 6. Why the Builder Improves the Design

The Builder Pattern solves the problems identified in Part A.

### Improved readability

Instead of:

```java
new PCconfig(
        "...",
        "...",
        32,
        1000,
        "...",
        "...",
        750
);
```

the Client uses:

```java
new PCconfig.Builder(...)
        .graphicsCard("RTX 4070 SUPER")
        .powerSupplyW(750)
        .coolingType("Air")
        .build();
```

The meaning of each value is visible directly in the method name.

### Reduced argument-order errors

Optional properties are no longer identified only by their position.

For example:

```java
.powerSupplyW(750)
```

clearly identifies the value as the power supply capacity.

### Easier maintenance

A new optional property can be added as a new Builder method without changing the existing required constructor arguments.

For example:

```java
public Builder bluetoothVersion(String bluetoothVersion) {
    this.bluetoothVersion = bluetoothVersion;
    return this;
}
```

Existing Client code can continue to work without specifying the new optional property.

### Better handling of optional properties

Optional properties can simply use their defaults.

For example:

```java
new PCconfig.Builder(
        "Intel Core i5-14400",
        "MSI B760",
        16,
        512
)
.build();
```

does not require the Client to provide values for all optional properties.

---

# Part C – Validation Challenge
    
In order not to create an invalid product - I made 3 single-field and 2 cross-field validation rules:

* RAM Capacity validation - ramGb must be strictly greater than 0
* Storage Capacity validation - storageGb must be strictly greater than 0
* Budget Validation - budged can not be negative

* GPU Power Supply Capacity Validation - A dedicated graphics card requires a sufficient power supply unit
* Gaming Profile Configuration Rules - High-performance gaming builds must meet minimum hardware thresholds (RAM, Power, Cooling)
# Part D – Preset Configurations
I created PCpresetDirector and 3 preset configurations (Basic PC, Gaming PC, Workstation PC)
# Part E - Clean Code
## 1) 
BEFORE

```java
private void validateGPUpower(){
    if (graphicsCard == null){
        return;
    }
    int neededPower = getNeededPower();
    if (powerSupplyW < neededPower){
        throw new IllegalArgumentException(
                graphicsCard + " requires a power supply of at least " + neededPower + " W"
        );
    }
}

private int getNeededPower(){
    if (graphicsCard.contains("4090")){
        return 850;
    }
    if (graphicsCard.contains("4080")){
        return 750;
    }
    if (graphicsCard.contains("4070")){
        return 650;
    }
    return 500;
}

private boolean isHighPerformGPU(){
    return graphicsCard != null && (graphicsCard.contains("4090") || graphicsCard.contains("4080") ||
            graphicsCard.contains("4070"));
}
```
AFTER
```java
private void validatePowerSupplyCapacity() {
    if (graphicsCard == null) {
        return;
    }
    int requiredWattage = calculateRequiredPowerSupplyWattage();
    if (powerSupplyW < requiredWattage) {
        throw new IllegalArgumentException(
                graphicsCard + " requires a power supply of at least " + requiredWattage + " W"
        );
    }
}

private int calculateRequiredPowerSupplyWattage() {
    if (isGpuModel("4090")) return 850;
    if (isGpuModel("4080")) return 750;
    if (isGpuModel("4070")) return 650;
    return 500;
}

private boolean isHighPerformanceGpu() {
    return isGpuModel("4090") || isGpuModel("4080") || isGpuModel("4070");
}

private boolean isGpuModel(String model) {
    return graphicsCard != null && graphicsCard.contains(model);
}
```
**What was wrong?**

* Duplicated Code: The *null-check* and *contains()* call were duplicated across *getNeededPower()* and *isHighPerformGPU()*
* Inaccurate/Abbreviated Naming: *validateGPUpower* used non-standard abbreviations, and *getNeededPower* sounded like a standard getter despite performing logic.

**Which principle applied?**
* DRY: Extracted string matching and null safety into a reusable predicate method *isGpuModel(String model)*
* Descriptive Naming: Renamed methods to reveal intent clearly (validatePowerSupplyCapacity, calculateRequiredPowerSupplyWattage, isHighPerformanceGpu).

**Why is it better?**
* Null checks are encapsulated in a single helper method, reducing the risk of NullPointerException
* Method names describe their exact behavior without requiring abbreviations.

## 2)
BEFORE

```java
private void validateConfig(){
    if (!"GAMING".equalsIgnoreCase(usageType)){
        return;
    }
    if (isHighPerformGPU()){
        if (ramGb < 16){
            throw new IllegalArgumentException(
                    "Gaming configuration with a high-preformance GPU require at least 16GB of RAM"
            );
        }
        if (powerSupplyW < 750) {
            throw new IllegalArgumentException(
                    "Gaming configurations with a high-performance " +
                            "graphics card require at least a 750W power supply."
            );
        }
        if ("Stock".equalsIgnoreCase(coolingType)) {
            throw new IllegalArgumentException(
                    "Gaming configurations with a high-performance " +
                            "graphics card require non-stock cooling."
            );
        }
    }
}
```
AFTER
```java
private void validateGamingConfig() {
    if (!isGamingProfile()) {
        return;
    }
    if (isHighPerformanceGpu()) {
        validateGamingRam();
        validateGamingPowerSupply();
        validateGamingCooling();
    }
}

private boolean isGamingProfile() {
    return "GAMING".equalsIgnoreCase(usageType);
}

private void validateGamingRam() {
    if (ramGb < 16) {
        throw new IllegalArgumentException(
                "Gaming configuration with a high-performance GPU requires at least 16GB of RAM"
        );
    }
}

private void validateGamingPowerSupply() {
    if (powerSupplyW < 750) {
        throw new IllegalArgumentException(
                "Gaming configurations with a high-performance graphics card require at least a 750W power supply."
        );
    }
}

private void validateGamingCooling() {
    if ("Stock".equalsIgnoreCase(coolingType)) {
        throw new IllegalArgumentException(
                "Gaming configurations with a high-performance graphics card require non-stock cooling."
        );
    }
}
```
**What was wrong?**

* *validateConfig()* handled RAM, PSU, and cooling validations inside a single monolithic block*
* High-level domain checks (usageType) were directly mixed with low-level numeric assertions (ramGb < 16)

**Which principle applied?**
* One Function - One Responsibility: Split each validation assertion into its own dedicated method.*
* One Level of Abstraction per Function: *validateGamingConfig()* operates purely as a high-level coordinator.

**Why is it better?**
* Reading validateGamingConfig() gives an immediate top-level overview of gaming build constraints
* Modifying rules for cooling or memory does not impact unrelated validation logic


## 3)
BEFORE

```java
public class Main {
    public static void main(String[] args) {
        NetworkAdapter networkAdapter = new NetworkAdapter("Intel", "Wi-Fi 6E", 2500);

        PCpresetDirector director = new PCpresetDirector();

        PCconfig basic = director.PCBasic();
        PCconfig gaming = director.PCGaming();
        PCconfig workstation = director.PCWorkstation();
        // ...
    }
}

public class PCpresetDirector {
    public PCconfig PCBasic(){ ... }
    public PCconfig PCGaming(){ ... }
    public PCconfig PCWorkstation(){ ... }
}
```
AFTER
```java
    public class Main {
        public static void main(String[] args) {
            PCPresetDirector director = new PCPresetDirector();

            PCconfig basicPC = director.buildBasicPC();
            PCconfig gamingPC = director.buildGamingPC();
            PCconfig workstationPC = director.buildWorkstationPC();
            ...
        }
    }

    public class PCPresetDirector {
        public PCconfig buildBasicPC() { ... }
        public PCconfig buildGamingPC() { ... }
        public PCconfig buildWorkstationPC() { ... }
    }
```
**What was wrong?**

* Unused Variables: *The networkAdapter* variable was instantiated in main() but never used or passed anywhere

**Which principle applied?**
* Use Verb Forms for Method Names: Renamed build methods to start with active verbs (*buildBasicPC()*)
* Clean Up Unused Variables: Removed *networkAdapter* from main()

**Why is it better?**
* Method names clearly communicate the construction action
* The main() method is clutter-free and contains no unused references


# Part F - Design Decision

## Decision
Introduce a dedicated *PCPresetDirector* class to encapsulate standard assembly recipes (*buildBasicPC()*, *buildGamingPC()*, *buildWorkstationPC()*)
## Alternative
Omit the *Director* entirely and require the *Client* to construct standard configurations manually via long Builder method chains
## Reasoning
While the Builder provides fine-grained flexibility, repeating 15+ method calls across client code for standard setups violates the DRY principle. The *PCPresetDirector* encapsulates common build recipes into clean, single-method invocations, separating how to build (Builder) from what standard profiles exist (Director)

# Part G - UML diagram
### in the docs file

* PCpresetDirector - is responsible for ready-made build configurations, such as Basic, Gaming, and Workstation
* PCconfig.Builder - is responsible for the step-by-step creation of the object
* PCconfig - is the final product
* NetworkAdapter - is used as a separate supporting object within the configuration

## Table
| Builder Role          | Class              | Responsibility                                                         |
|-----------------------|:-------------------| ---------------------------------------------------------------------- |
| Product               | `PCconfig`         | Represents the final computer configuration                            |
| Builder               | `PCconfig.Builder` | Stores configuration values and provides fluent construction methods   |
| Director              | `PCpresetDirector` | Defines reusable Basic, Gaming, and Workstation construction sequences |
| Client                | `Main`             | Requests predefined configurations from the Director                   |

# Part H – Automated Testing

JUnit 5 was used to verify the correctness of the `PCconfig` Builder implementation

The test suite contains 10 automated tests covering valid configurations, invalid configurations, boundary cases, the individual cross-field constraint, and Builder reuse

## Valid Construction Tests

Three tests verify that the predefined configurations created by `PCpresetDirector` can be built successfully

* `shouldBuildBasicPC()` verifies the Basic PC configuration.
* `shouldBuildGamingPC()` verifies the Gaming PC configuration.
* `shouldBuildWorkstationPC()` verifies the Workstation PC configuration.

## Invalid Construction Tests

Three tests verify that invalid individual values are rejected

* `shouldRejectInvalidRam()` verifies that RAM equal to `0` is rejected
* `shouldRejectInvalidStorage()` verifies that storage equal to `0` is rejected
* `shouldRejectInvalidBudget()` verifies that a negative budget is rejected

The tests use `assertThrows()` because an `IllegalArgumentException` is expected when an invalid configuration is built

## Boundary Tests

Two boundary tests verify the minimum values accepted by the configuration rules

* `shouldAcceptMinimumGamingRam()` verifies that a Gaming PC with exactly 16 GB of RAM is accepted
* `shouldAcceptMinimumGamingPowerSupply()` verifies that a Gaming PC with exactly 750W of power supply is accepted

## Individual Cross-Field Constraint

The test `shouldRejectGamingPCWithInsufficientRam()` verifies the project's individual constraint

A Gaming configuration with a high-performance graphics card requires at least 16 GB of RAM. The test attempts to create a Gaming PC with 8 GB of RAM and expects an `IllegalArgumentException`

This demonstrates that the Builder validates relationships between multiple configuration parameters rather than checking only individual values.

## Builder Reuse and Product Independence

The test `shouldKeepProductIndependentFromBuilder()` verifies that an already-created `PCconfig` object is independent from later changes to the Builder.

First, a Basic configuration is created. The same Builder is then modified and used to create another configuration.

The test verifies that:

* the two products are different objects;
* their configurations are different;
* changing the Builder after the first `build()` does not modify the first product.

This demonstrates that the Builder creates a separate `PCconfig` object during each `build()` operation.
# Sample Program Output
```
-----BASIC-----
PC Configuration:
Processor: Intel Core i3-12100
Motherboard: MSI H610M
RAM: 8 GB
Storage: 256 GB
Usage type: OFFICE
Graphics Card: Integrated Graphics
Power Supply: 550 W
Cooling: Stock
Case: Standard ATX Case
Storage Type: SSD
Operating System: Windows 11
RAM Type: DDR4
RAM Modules: 2
Storage Devices: 1
Monitor Count: 1
Monitor Size: 24.0"
Monitor Resolution: 1920x1080
Keyboard: Standard Keyboard
Mouse: Standard Mouse
Wi-Fi: false
Bluetooth: false
Ethernet Speed: 1000 Mbps
USB Ports: 6
Fan Count: 2
RGB Lighting: false
Sound Card: Integrated
Webcam: null
Microphone: null
Network Adapter: null
Budget: $450.0

-----GAMING-----
PC Configuration:
Processor: AMD Ryzen 7 7800X3D
Motherboard: ASUS ROG STRIX B650
RAM: 32 GB
Storage: 2000 GB
Usage type: GAMING
Graphics Card: NVIDIA RTX 4080 Super
Power Supply: 850 W
Cooling: Liquid 360mm
Case: NZXT H7 Flow
Storage Type: NVMe SSD
Operating System: Windows 11
RAM Type: DDR5
RAM Modules: 2
Storage Devices: 1
Monitor Count: 2
Monitor Size: 27.0"
Monitor Resolution: 2560x1440
Keyboard: Standard Keyboard
Mouse: Standard Mouse
Wi-Fi: true
Bluetooth: true
Ethernet Speed: 1000 Mbps
USB Ports: 6
Fan Count: 6
RGB Lighting: true
Sound Card: Integrated
Webcam: null
Microphone: null
Network Adapter: null
Budget: $2500.0

-----WORKSTATION-----
PC Configuration:
Processor: Intel Core i9-14900K
Motherboard: ASUS ProArt Z790
RAM: 64 GB
Storage: 4000 GB
Usage type: WORKSTATION
Graphics Card: NVIDIA RTX 4090
Power Supply: 1000 W
Cooling: Custom Liquid
Case: Fractal Design Meshify 2 XL
Storage Type: NVMe SSD
Operating System: Windows 11
RAM Type: DDR5
RAM Modules: 4
Storage Devices: 3
Monitor Count: 3
Monitor Size: 32.0"
Monitor Resolution: 3840x2160
Keyboard: Standard Keyboard
Mouse: Standard Mouse
Wi-Fi: true
Bluetooth: true
Ethernet Speed: 10000 Mbps
USB Ports: 10
Fan Count: 7
RGB Lighting: false
Sound Card: External Audio Interface
Webcam: 4K Pro Webcam
Microphone: Studio XLR Microphone
Network Adapter: null
Budget: $5000.0
```

# Github repository link 
https://github.com/DanialNurumbet/SDP-assignment1.git