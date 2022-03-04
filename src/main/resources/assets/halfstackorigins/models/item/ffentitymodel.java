import net.minecraft.client.render.entity.model.EntityModel;

public class ffentitymodel extends EntityModel<fairyflossentity> {
    private final ModelPart base;

    public ffentitymodel()

    public static TexturedModelData getTexturedModelData()

    @Override
    public void setAngles(fairyflossentity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        ImmutableList.of(this.base).forEach((modelRenderer) -> {
            modelRenderer.render(matrices, vertices, light, overlay, red, green, blue, alpha);
        });
    }
}