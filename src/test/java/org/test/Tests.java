package org.test;

import java.util.*;

import org.app.LocationSelector;
import org.jsoup.*;
import org.jsoup.helper.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Tests {

    @Test
    public void test1() {
        final var city_selector = new LocationSelector();
        assertEquals(city_selector.getByID(1380594352)
                .get().get("city_ascii"), "Forli");
    }

}
