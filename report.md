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

```text
Client
   |
   v
PCconfig.Builder
   |
   | configuration methods
   v
build()
   |
   v
PCconfig
```

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


