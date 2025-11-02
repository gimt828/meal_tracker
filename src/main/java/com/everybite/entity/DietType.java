package com.everybite.entity;

public enum DietType {
    BULK_UP(50, 30, 20),
    MAINTAIN(45, 30, 25),
    DIET(35, 40, 25);

    private final double carbRatio;
    private final double proteinRatio;
    private final double fatRatio;

    DietType(double carbRatio, double proteinRatio, double fatRatio) {
        this.carbRatio = carbRatio;
        this.proteinRatio = proteinRatio;
        this.fatRatio = fatRatio;
    }

    public double getCarbRatio() { return carbRatio; }
    public double getProteinRatio() { return proteinRatio; }
    public double getFatRatio() { return fatRatio; }
}

// 운동 종류가 정해지면 고쳐야 함.
