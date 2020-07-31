package oop_and_functional.method_level;

/**
 * Created on 31 Jul, 2020 - 02:58
 *
 */
public class Asset {
    public enum AssetType {BOND, STOCK}
    private final AssetType assetType;
    private final double value;

    public Asset(AssetType assetType, double value) {
        this.assetType = assetType;
        this.value = value;
    }

    public AssetType getAssetType() {
        return assetType;
    }

    public double getValue() {
        return value;
    }
}
