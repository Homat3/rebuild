package com.tf.npu.blocks.npublocknewclasses;

import com.tf.npu.blocks.NpuBlocks;
import com.tf.npu.blocks.dataofnpublocks.ShapeData;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class HorizontalMultipleDirectionalStructure extends Block {

    //体积映射
    private final ArrayList<VoxelShape> angleShapeList0;
    private final ArrayList<VoxelShape> angleShapeList15;
    private final ArrayList<VoxelShape> angleShapeList30;
    private final ArrayList<VoxelShape> angleShapeList45;
    private final ArrayList<VoxelShape> angleShapeList60;
    private final ArrayList<VoxelShape> angleShapeList75;
    protected VoxelShape shape;
    //加载方式
    public NpuBlocks.LoadMethod loadMethod;
    //方向属性
    public static final IntegerProperty ANGEL = IntegerProperty.create("angle", 0, 165);
    protected int angle;

    //构造
    public HorizontalMultipleDirectionalStructure(Properties properties, NpuBlocks.LoadMethod loadMethod) {
        super(properties);
        this.angleShapeList0 = new ArrayList<>(0);
        this.angleShapeList15 = new ArrayList<>(0);
        this.angleShapeList30 = new ArrayList<>(0);
        this.angleShapeList45 = new ArrayList<>(0);
        this.angleShapeList60 = new ArrayList<>(0);
        this.angleShapeList75 = new ArrayList<>(0);
        this.shape = null;
        this.loadMethod = loadMethod;
        this.angle = 0;
    }
    //与构造并用
    public HorizontalMultipleDirectionalStructure setSHAPE(ShapeData shapeData0, ShapeData shapeData15, ShapeData shapeData30,
                                                           ShapeData shapeData45, ShapeData shapeData60, ShapeData shapeData75)
    {
        loadShape(shapeData0, angleShapeList0);
        loadShape(shapeData15, angleShapeList15);
        loadShape(shapeData30, angleShapeList30);
        loadShape(shapeData45, angleShapeList45);
        loadShape(shapeData60, angleShapeList60);
        loadShape(shapeData75, angleShapeList75);
        return this;
    }
    //旋转坐标变化
    public VoxelShape getShapeByAngle(VoxelShape shape, int angle)
    {
        if (angle < 90) return shape;
        else return Shapes.box(shape.bounds().minZ, shape.bounds().minY, 1-shape.bounds().maxX,
                    shape.bounds().maxZ, shape.bounds().maxY, 1-shape.bounds().minX);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        builder.add(ANGEL);
    }
    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return defaultBlockState().setValue(ANGEL, switch(context.getHorizontalDirection().getOpposite()) {
            case WEST, EAST -> 90;
            default -> 0;
        });
    }
    private void loadShape(ShapeData shapeData, ArrayList<VoxelShape> angleShapeList){
        if (!shapeData.loaderIsObj()) for (List<Double> shape : shapeData.getShapeList())
        {
            angleShapeList.add(Shapes.box(shape.get(0), shape.get(1), shape.get(2), shape.get(3), shape.get(4), shape.get(5)));
        }
    }
    private void loadShape()
    {
        ArrayList<VoxelShape> shapeList = switch (angle / 15){
            case 1 -> angleShapeList15;
            case 2 -> angleShapeList30;
            case 3 -> angleShapeList45;
            case 4 -> angleShapeList60;
            case 5 -> angleShapeList75;
            default -> angleShapeList0;
        };
        shape = NpuBlocks.EmunShape.HALF_SHPAE_BOTTOM.getShape();
        if(!shapeList.isEmpty()) switch (loadMethod)
        {
            case METICULOUS:
                shape = NpuBlocks.EmunShape.NULL_SHPAE.getShape();
                for (VoxelShape voxelShape : shapeList)
                {
                    shape = Shapes.or(shape, getShapeByAngle(voxelShape, angle));
                }
                break;
            case ROUGH:
                shape = shapeList.get(0);
                for (VoxelShape voxelShape : shapeList)
                {
                    AABB a = shape.bounds();
                    AABB b = voxelShape.bounds();
                    shape = Shapes.box(
                            Math.min(a.minX, b.minX), Math.min(a.minY, b.minY), Math.min(a.minZ, b.minZ),
                            Math.max(a.maxX, b.maxX), Math.max(a.maxY, b.maxY), Math.max(a.maxZ, b.maxZ));
                }
                shape = getShapeByAngle(shape, angle);
                break;
        }
    }
    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState pState, @NotNull BlockGetter pGetter, @NotNull BlockPos pPos, @NotNull CollisionContext pContext)
    {
        if (shape == null || angle != pState.getValue(ANGEL))
        {
            angle = pState.getValue(ANGEL);
            loadShape();
        }
        return shape.optimize();
    }
    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos,
                                          @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult res)
    {
        state = state.setValue(ANGEL, (state.getValue(ANGEL) + 15) % 180);
        level.setBlock(pos, state, 10);
        level.gameEvent(player, GameEvent.BLOCK_CHANGE, pos);
        return InteractionResult.sidedSuccess(level.isClientSide);
    }
}
