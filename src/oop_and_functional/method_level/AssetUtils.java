package oop_and_functional.method_level;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created on 31 Jul, 2020 - 03:00
 *
 */
public class AssetUtils {
    //Bad design of methods to illustrate how we can improve. This methods although using streams, can be
    //greatly improved to reduce code duplication. The methods share two common operations. Receive a list, create a stream
    //and map the total. The differences como on mapping criteria, what to total.

    public static double assetsTotal(final List<Asset> list) {
        return list.stream()
                .mapToDouble(Asset::getValue)
                .sum();
    }

    public static double bondAssetsTotal(final List<Asset> list) {
        return list.stream()
                .mapToDouble(a -> a.getAssetType() == Asset.AssetType.BOND ? a.getValue() : 0.0)
                .sum();
    }

    public static double stockAssetsTotal(final List<Asset> list) {
        return list.stream()
                .mapToDouble(a -> a.getAssetType() == Asset.AssetType.STOCK ? a.getValue() : 0.0)
                .sum();
    }

    //Improvements to reduce code duplication. Now the method receives both the list and the criteria in the form
    //of a predicate, which in simple terms returns a boolean condition.

    public static double totalAssetValue(final List<Asset> list, final Predicate<Asset> assetSelector) {
        return list.stream()
                .filter(assetSelector)
                .mapToDouble(Asset::getValue)
                .sum();
    }


    public static void main(String[] args) {
        final List<Asset> list = Arrays.asList(
                new Asset(Asset.AssetType.BOND, 3000),
                new Asset(Asset.AssetType.STOCK, 1000),
                new Asset(Asset.AssetType.BOND, 200),
                new Asset(Asset.AssetType.STOCK, 4000));

        //This is what the code repetitive methods return.
        System.out.println(assetsTotal(list));
        System.out.println(bondAssetsTotal(list));
        System.out.println(stockAssetsTotal(list));

        System.out.println();

        //Calls the improved method
        System.out.printf("The total of the assets using the predicate is: %s%n",
                totalAssetValue(list, asset -> true));

        System.out.printf("The total of the BOND assets using the predicate is: %s%n",
                totalAssetValue(list, asset -> asset.getAssetType() == Asset.AssetType.BOND));

        System.out.printf("The total of the STOCK assets using the predicate is: %s%n",
                totalAssetValue(list, asset -> asset.getAssetType() == Asset.AssetType.STOCK));

    }
}
