package com.harleylizard.witches_way.common.tree;

import java.util.List;

public record RandomShapeList(List<Entry> entries) implements Shape {

    public record Entry(Shape shape, int weight) {

    }

}
