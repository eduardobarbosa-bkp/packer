package com.mobiquityinc.packer;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.packer.domain.Item;
import com.mobiquityinc.packer.domain.Pack;
import com.mobiquityinc.packer.handler.PackerHandler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class is responsible to get the input values and start analysis of the packages
 * @version 1.0
 * @author eduardo.costa
 * @since 27/10/2017
 */
public class Packer {

    private static PackerHandler handler = new PackerHandler();

    /**
     * Perform the parser and analysis of the packages from file
     * @param path absolute path of the file
     * @return String with the result
     * @throws APIException
     */
    public static String pack(String path) throws APIException {
      try {
          List<Pack> packs = parserFile(path);
          List<Pack> result = handler.process(packs);
          return resultAsString(result);
      }catch (Exception e){
         throw new APIException(e.getMessage(), e);
      }
    }

    private static List<Pack> parserFile(String path) throws IOException {
        try (final Stream<String> stream = Files.lines(Paths.get(path))){
        //parser the file with pattern 81 : (1,53.38,€45)...
        return stream.map(line -> line.split(":"))
                .map(pac -> Pack.builder()
                        .weight(Double.valueOf(pac[0].trim()))
                        .items(
                                Arrays.stream(pac[1].trim().split("[\\(\\)]"))
                                        .filter(v -> v.trim().length() != 0)
                                        .map(item -> Item.builder()
                                                .index(Integer.valueOf(item.split(",")[0].trim()))
                                                .weight(Double.valueOf(item.split(",")[1].trim()))
                                                .cost(Double.valueOf(item.split(",")[2].trim().replaceAll("[^0-9.]", "")))
                                                .build())
                                        .collect(Collectors.toList())
                        )
                        .build())
                .collect(Collectors.toList());
    }}

    private static String resultAsString(List<Pack> solution) {
        return solution.stream()
                .map(pack -> pack.getItems().stream()
                        .map(Item::getIndex).sorted().collect(Collectors.toList())
                        .stream().map(Object::toString).collect(Collectors.joining(",")))
                .map(str -> str.trim().length() == 0 ? "-" : str)
                .collect(Collectors.joining("\n"));
    }
}
