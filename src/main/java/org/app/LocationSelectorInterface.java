package org.app;

import java.util.*;

public interface LocationSelectorInterface {
    
    List<Pair<String, Integer>> getPossibleLocations(String txt);

    Optional<Map<String, String>> getByID(int ID);

}
