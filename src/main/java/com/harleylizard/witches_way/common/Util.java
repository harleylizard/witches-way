package com.harleylizard.witches_way.common;

import com.mojang.math.Axis;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public final class Util {
    public static final int[] HORIZONTAL = {0, 2, 1, 3};

    public static VoxelShape rotateShape(VoxelShape shape, Quaternionf rotation) {
        var matrix4f = new Matrix4f();
        matrix4f.identity();
        matrix4f.translate(0.5f, 0.5f, 0.5f);
        matrix4f.rotate(rotation);
        matrix4f.translate(-0.5f, -0.5f, -0.5f);

        var min = new Vector3f();
        var max = new Vector3f();

        var result = Shapes.empty();
        for (var aabb : shape.toAabbs()) {
            matrix4f.transformAab(
                    (float) aabb.minX,
                    (float) aabb.minY,
                    (float) aabb.minZ,

                    (float) aabb.maxX,
                    (float) aabb.maxY,
                    (float) aabb.maxZ,
                    min,
                    max
            );

            aabb = new AABB(min.x, min.y, min.z, max.x, max.y, max.z);
            result = Shapes.or(result, Shapes.create(aabb));

        }

        return result;
    }

    public static VoxelShape rotateShape(VoxelShape shape, int direction) {
        return rotateShape(shape, Axis.YP.rotationDegrees(HORIZONTAL[direction - 2] * 90.0f));
    }

}
